package br.com.sysloccOficial.consultas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.consultas.consultasAvancadas.ConsultasPassaSql;
import br.com.sysloccOficial.daos.JobListaJobDAO;

@Repository
public class JobRelatorioApoio extends ConsultaJob{
	
	@PersistenceContext private EntityManager manager;
	@Autowired private ConsultasPassaSql c;
	@Autowired private JobListaJobDAO jobDAO;
	
	
	
	  public List<Integer> todosMetodos(String consulta){
		  
		    // 1
			List<Integer> idJobsNaoOrdenados =  pegaIdJobsPorStatus(consulta); 
			// 2
			List<Integer> idJobsPrioridade =  pegaIdsOrdemEvento(idJobsNaoOrdenados);
			// 3 
			ArrayList<Integer> naoTenho =  pegaIdsJobsSemEvento(idJobsNaoOrdenados, idJobsPrioridade);
			// 4
			List<Integer> idsJobsFinal = adicionaIdsJobsSemEventoAoFimListaFinal(naoTenho, idJobsPrioridade);
		  
		  return idsJobsFinal;
	  }
		
	  public List<Integer> adicionarFinal(List<Integer> c, List<Integer> idsJobsFinal){
		  for(int i = 0; i < c.size(); i++){
			  idsJobsFinal.add(c.get(i));
		  }
		  return idsJobsFinal;
	  }
	  
		// 1
		public List<Integer> pegaIdJobsPorStatus(String consulta1){
			List<Integer> idJobsNaoOrdenados =  (List<Integer>) c.retornaLista(consulta1); 
			return idJobsNaoOrdenados;
		}
			
			
		// 2
		public List<Integer> pegaIdsOrdemEvento(List<Integer> idJobsNaoOrdenados){
			String t = montaConsultaInteger(idJobsNaoOrdenados, "idJob = ");
			String consultaDataLocalInicio = "SELECT distinct(l.idJob.idJob) FROM LocalEvento l where "+ t + " order by localEventoDataInicio";
			List<Integer> idJobsPrioridade =  (List<Integer>) c.retornaLista(consultaDataLocalInicio);
			return idJobsPrioridade;
		}
		
			
		// 3 
		public ArrayList<Integer> pegaIdsJobsSemEvento (List<Integer> idJobsNaoOrdenados,List<Integer> idJobsPrioridade) {
			ArrayList<Integer> naoTenho = comparaOrdenacaoPorEvento(idJobsNaoOrdenados,idJobsPrioridade);
			return naoTenho;
		}
		
		
		// 4
		public List<Integer> adicionaIdsJobsSemEventoAoFimListaFinal(ArrayList<Integer> naoTenho, List<Integer> idJobsPrioridade ){
			
			for (Integer integer : naoTenho) {
				idJobsPrioridade.add(integer);
			}
			return idJobsPrioridade;
		}
			
			
		public ArrayList<Integer> comparaOrdenacaoPorEvento(List<Integer> idJobsNaoOrdenados, List<Integer> idJobsPrioridade){
			
			ArrayList<Integer> naoTenho = new ArrayList<Integer>();
			
			Integer confere = 0;
			for(int i = 0; i < idJobsNaoOrdenados.size(); i++){
		
					Integer r = idJobsNaoOrdenados.get(i);
					
						    for(int j = 0; j < idJobsPrioridade.size(); j++) {
						    	Integer s = idJobsPrioridade.get(j);
						    	if(r.equals(s)){
						    		confere = confere + 1;
						    	}else{
						    	}
						    }
				    if(confere == 1){
				    }else{
				    	naoTenho.add(r);
				    }   
				    confere = 0;
			}
			
			return naoTenho;
		}
	
		public List<Object> testeVazioConsulta(String consulta){
			
			Query q = manager.createQuery(consulta);
			
			List<Object> teste = q.getResultList();
			
			return teste;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}

