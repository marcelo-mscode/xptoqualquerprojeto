package br.com.sysloccOficial.daos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.consultas.consultasAvancadas.ConsultasPassaSql;
import br.com.sysloccOficial.model.JobConsulta;
import br.com.sysloccOficial.model.JobStatus;


@Repository
public class JobListaJobDAO {

	@PersistenceContext	private EntityManager manager;
	
	@Autowired private UtilitariaDatas utilDatas;
	@Autowired private ConsultasPassaSql c;
	@Autowired private JobDAO jobDAO;
	

	public List<JobConsulta> lista(List<Integer> idJobsNovos) throws ParseException{
		
		List<JobConsulta> listaContatos = new ArrayList<JobConsulta>();
		
		for(int i = 0; i < idJobsNovos.size(); i++){
			
			Integer idJob = idJobsNovos.get(i);
			JobConsulta jobc = new JobConsulta();

			jobc.setIdJob(idJob);
			jobc.setIdEmpresa((Integer)condicao(idJob,"j.empresa.idEmpresa"));
			jobc.setCodJob((Integer)condicao(idJob,"j.codJob"));
			jobc.setTitulo((String)condicao(idJob,"j.titulo"));
			jobc.setContato((String)condicao(idJob,"j.contatos.contato"));
			jobc.setEmpresa((String)condicao(idJob,"j.empresa.empresa"));
			jobc.setCriadoEm((Calendar)condicao(idJob,"j.criadoEm"));
			jobc.setPropostaData((Date)condicao(idJob,"j.propostaData"));
			jobc.setPrazoConclusao((Date)condicao(idJob,"j.idStatusAtual.prazoConclusao"));
			jobc.setLocalEventoDataInicio((Date)DataEvento(idJob,"d.localEventoDataInicio"));

			jobc.setListalocalEventoDataInicio((List<Date>)ListaDataEvento(idJob,"d.localEventoDataInicio"));
			jobc.setListalocalEventoDataTermino((List<Date>)ListaDataEvento(idJob,"d.localEventoDataTermino"));
			jobc.setListaLocalEvento((List<String>)ListaDataEvento(idJob,"d.local"));
			
			jobc.setEstatus(estatus(idJob,"j.idStatus.idEstatus"));
			jobc.setLocalEventoDataTermino((Date)DataEvento(idJob,"d.localEventoDataTermino"));
			jobc.setLocalEvento((String)DataEvento(idJob,"d.local"));
//			jobc.setLocalEvento((String)DataEvento(idJob,"d.localEventoEndereco"));
			
			
			String Produtor1 = (String)produtor(idJob,"j.idUsuario.nome");
			String primeiroNomeProdutor1 = Produtor1.split(" ")[0];
			jobc.setProdutor1(primeiroNomeProdutor1);

			String Produtor2 = (String)produtor(idJob,"j.produtor2.nome");
			
			if(Produtor2.equals("A definir") || Produtor2 == ""){
				jobc.setProdutor2("A definir ");
			}else{
				String primeiroNomeProdutor2 = Produtor2.split(" ")[0];
				jobc.setProdutor2(primeiroNomeProdutor2);
			}

//			jobc.setProdutor2((String)produtor(idJob,"j.produtor2.nome"));
//			jobc.setProdutor1((String)produtor(idJob,"j.idUsuario.nome"));
			
			
			
			
			jobc.setObs((String)produtor(idJob,"j.jobStatusObservacao"));
			
			

			listaContatos.add(jobc);
		}
		return listaContatos;
		
	}
	
	
	public Object condicao(Integer idJob, String condicao) {
		String consulta = "select "+ condicao +" from Job j where j.idJob =" +idJob;
		return c.retorna(consulta);
	}
	
	/**
	 * Método para selecionar o último local do evento
	 * 
	 */
	public Object DataEvento(Integer idJob,String termo){
		String consulta = "select "+ termo +" from LocalEvento d where d.idJob="+ idJob + " order by localEventoDataInicio";
		return c.ObjetoCompletoLimit(consulta);
	}
	
	
	/** * Método para selecionar todos os locais de evento do Job * */
	public Object ListaDataEvento(Integer idJob,String termo){
		String consulta = "select "+ termo +" from LocalEvento d where d.idJob="+ idJob + " order by localEventoDataInicio";
		return c.ObjetoCompletoRetornaLista(consulta);
	}
	
	
	
	
	public String estatus(Integer idJob, String termo){
		String consulta = "select "+ termo +" from JobStatus j where j.idJob=" +idJob + " order by idJobStatus desc";
		try {
			Query query = manager.createQuery(consulta).setMaxResults(1);
			Integer estatus = (Integer) query.getSingleResult();
			Query query2 = manager.createQuery("select s.estatus from Estatus s where idEstatus = " + estatus).setMaxResults(1);
			String m = (String) query2.getSingleResult();
			return m;
		
		} catch (Exception e) {
			return null;
		}
	}
	
	public Integer porStatus(Integer idJob){
		String consulta = "select j.idStatus.idEstatus from JobStatus j where j.idJob=" +idJob + " order by idJobStatus desc";
		try {
			Query query = manager.createQuery(consulta).setMaxResults(1);
			Integer idStatus = (Integer) query.getSingleResult();
			return idStatus;
		
		} catch (Exception e) {
			return null;
		}
	}
	
	public String produtor(Integer idJob, String termo){
		String consulta = "select "+ termo +" from JobStatus j where j.idJob=" +idJob + " order by idJobStatus desc";
		try {
			Query query = manager.createQuery(consulta).setMaxResults(1);
			String idStatus = (String) query.getSingleResult();
			return idStatus;
		
		} catch (Exception e) {
			return "A definir";
		}
	}
	
	
	
	
	public List<Integer> listaTeste() throws ParseException{
		
		List<Integer> idJobs =  jobDAO.listaIdoJobs(); // Lista de Ids Criados acima de uma data
		
		List<Integer> idJobsNovos =  new ArrayList<Integer>();
		for(int i =0; i < idJobs.size(); i++){
			Integer idJob = idJobs.get(i);
			Integer idStatus = porStatus(idJobs.get(i));
			if(idStatus == null){
			} else 
				if(idStatus == 7  || idStatus == 1 || idStatus == 9 || idStatus == 8 || idStatus == 5 || idStatus == 6){
				idJobsNovos.add(idJob);	
			}
		}
		return idJobsNovos;
	}
	
}	
	
