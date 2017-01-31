package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import br.com.sysloccOficial.model.Estatus;
import br.com.sysloccOficial.model.ListaEstatus;

@Repository
public class EstatusDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public List<Estatus> ListaTodosEstatus(){
		String consulta = "select e from Estatus e order by estatus";
		TypedQuery<Estatus> query = manager.createQuery(consulta,Estatus.class);
		return query.getResultList(); 
	}
	
	public List<ListaEstatus> ListaEstatusProducao(){
		String consulta = "select e from ListaEstatus e order by idlistaEstatus";
		Query query = manager.createQuery(consulta);
		return query.getResultList(); 
	}
	
	
	
	
	
/*	public void salva(Job j){
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

	public List<Job> listaJob(){
		
		return manager.createQuery("from Job j join fetch j.contatos where idContatoResponsavel >= 0 order by criadoem desc",Job.class)
				.setMaxResults(1000)
				.getResultList();
	}
// --------------------------------------------//	
	
	
	public List<Job> mostraPorId(Integer idJob) {
		
		    Query query = (Query) manager.createQuery("select j from Job j join fetch j.empresa where j.idJob=:Job",Job.class);
		    query.setParameter("Job", idJob);
		    
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
	public Job recebeObjeto(Integer i){
    	Job j = manager.find(Job.class, i);
    	return j;
    	
    }
	
*/}
