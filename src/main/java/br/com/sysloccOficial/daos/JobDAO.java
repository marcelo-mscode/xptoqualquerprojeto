package br.com.sysloccOficial.daos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;























import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.model.Anexos;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Departamento;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.User;
import br.com.sysloccOficial.model.Usuario;

@Repository
public class JobDAO {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired private UtilitariaDatas utilDatas;
	
	
	public void salva(Job j){
		Empresa e = manager.find(Empresa.class, j.getIdEmp());
		j.setEmpresa(e);
		
		Contato c = manager.find(Contato.class, j.getIdContato());
		j.setContato(c);
		
		
		manager.persist(j);
		
	}
	
//--- Preenche um novo job na tela de Edição ----//
	
	public List<Job> mostraPorNome(String titulo) {
		return manager.createQuery("select j from Job j join fetch j.empresa order by idJob desc",Job.class) 		
		.setMaxResults(1)
		.getResultList();
    }
//----------------------------------------------//
	
	
	
//------- Lista os Jobs na página principal ----//

	public List<Integer>listaIdoJobs() throws ParseException{  // Seleciona Ids de jobs Acima de uma data
		Date dataMeses = utilDatas.subtraiMeses(12);
		String data = utilDatas.converteDateParaStringInternacional(dataMeses); 
		Query query = manager.createQuery("select j.idJob from Job j where criadoEm > '"+ data +"' order by criadoEm desc");
		return query.getResultList();
	}
	
	
	
// --------------------------------------------//	
	
	public List<Job> mostraPorId(Integer idJob) {
			String consulta = "select j from Job j join fetch j.empresa e join fetch j.contatos where j.idJob=" + idJob;
		
		    Query query = (Query) manager.createQuery(consulta);
		    return query.getResultList();
		    
	    }
	
	public void atualiza(Job j){
		Empresa e = manager.find(Empresa.class, j.getIdEmp());
		j.setEmpresa(e);
		Contato c = manager.find(Contato.class, j.getIdContato());
		j.setContato(c);
		
		manager.merge(j);
	}
	
	public List<Job> dataCriadoEm(Integer idJob){
		Query query = manager.createQuery("from Job where idJob=:idJob",Job.class);
		query.setParameter("idJob",idJob);
		return query.getResultList();
	}
	
	public List<Job>ParaEmail(Integer idJob){
		String consulta = "select j from Job j where idJob=:idJob";
		Query query = manager.createQuery(consulta, Job.class);
		query.setParameter("idJob", idJob);
		return query.getResultList();
	}
	
	
	
	public Number codJob(){
		
		Calendar c= Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM");  
	    String a = s.format(c.getTime());
		
		String consulta =  "Select count(j) FROM Job j where criadoem like '%"+a+"%'";
		TypedQuery<Number> query1 = manager.createQuery(consulta, Number.class);
		Number result = query1.getSingleResult();
		return result;
	}
	public Integer pegaCodigoJob(Integer idJob){
		String consulta ="select j.codJob from Job j where idJob="+idJob;
		TypedQuery<Integer> query = manager.createQuery(consulta, Integer.class);
		return query.getSingleResult();
	}
	
	public void saveAnexo(Anexos a){
		manager.persist(a);
	}

	public void limpaVirgula(){
		 String limpa = "UPDATE anexos SET anexoDiretorio = replace( anexoDiretorio, ',', '') where idAnexo > 1";
		 manager.createNativeQuery(limpa).executeUpdate();
	}
	
	
	public Job recebeObjeto(Integer i){
    	Job j = manager.find(Job.class, i);
    	return j;
    	
    }
	public Integer pegaIdUltimoJobSalvo(){
		String consulta = "select j.idJob from Job j  order by idJob desc";
		TypedQuery<Integer> idJob = manager.createQuery(consulta, Integer.class).setMaxResults(1);
		return idJob.getSingleResult();
	}
	
	public List<Job> listaJobStatus(){
		return manager.createQuery("select distinct(j) from Job j join fetch j.jobStatus join fetch j.contatos", Job.class).getResultList();
	}
	
	public List<Object[]> listaJobIdENome(String nomeEmpresa) {
		Integer idEmpresaCOnsultada = 0;
		String consulta = "select e.idEmpresa from Empresa e where empresa = '"+nomeEmpresa+"'";
		
		try {
			TypedQuery<Integer>idEmpresa = manager.createQuery(consulta, Integer.class).setMaxResults(1);
			idEmpresaCOnsultada = idEmpresa.getSingleResult();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	    String consulta2 = "select j.idJob,j.codJob,j.titulo from Job j where idEmpresa="+idEmpresaCOnsultada+" order by codJob desc";
		Query query = manager.createQuery(consulta2);	
		List<Object[]> lista = query.getResultList();
		return lista;
    }

	
}



