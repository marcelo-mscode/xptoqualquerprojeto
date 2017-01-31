package br.com.sysloccOficial.consultas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.consultas.consultasAvancadas.ConsultasPassaSql;
import br.com.sysloccOficial.daos.JobListaJobDAO;
import br.com.sysloccOficial.model.JobConsulta;
import br.com.sysloccOficial.model.LocalEvento;


@Controller
public class JobRelatorio extends ConsultaJob{

	@PersistenceContext private EntityManager manager;
	@Autowired private ConsultasPassaSql c;
	@Autowired private JobListaJobDAO jobDAO;
	@Autowired private JobRelatorioApoio jobApoio;
	
	/*@RequestMapping("printRelatorio")
	public ModelAndView printRelatorio(){
		
	}*/
	
	
	@RequestMapping("/relatorio")
	public ModelAndView relatorio(String tipo) throws ParseException{
		
		
		String select = "select j.idJob from Job j where ";
		String condicao = "j.idStatusAtual.idStatus.idEstatus= ";
		String orderBy = " order by j.idStatusAtual.idStatus.idEstatus";

		String consulta1 = select + condicao + "1" + orderBy;
		String consulta5 = select + condicao + "5" + orderBy;
		String consulta6 = select + condicao + "6" + orderBy;
		String consulta7 = select + condicao + "7" + orderBy;
		String consulta8 = select + condicao + "8" + orderBy;
		
		
		// Passe uma consulta e receba Um Lista<Final>
		List<Integer> idsJobsFinal = new ArrayList<Integer>();
		
		List<Object> teste1 = jobApoio.testeVazioConsulta(consulta1);
		if(teste1.isEmpty()){
		}else{
			List<Integer> c1 = jobApoio.todosMetodos(consulta1);
			for(int i = 0; i < c1.size(); i++){
				idsJobsFinal.add(c1.get(i));
			}	
		}
		
		
		List<Object> teste2 = jobApoio.testeVazioConsulta(consulta5);
		if(teste2.isEmpty()){
		}else{
			List<Integer> c2 = jobApoio.todosMetodos(consulta5);
			for(int i = 0; i < c2.size(); i++){
				idsJobsFinal.add(c2.get(i));
			}
		}
		
		List<Object> teste3 = jobApoio.testeVazioConsulta(consulta6);
		if(teste3.isEmpty()){
		}else{
			List<Integer> c3 = jobApoio.todosMetodos(consulta6);
			for(int i = 0; i < c3.size(); i++){
				idsJobsFinal.add(c3.get(i));
			}
		}

		
		List<Object> teste4 = jobApoio.testeVazioConsulta(consulta7);
		if(teste4.isEmpty()){
		}else{	
			List<Integer> c4 = jobApoio.todosMetodos(consulta7);
			for(int i = 0; i < c4.size(); i++){
				idsJobsFinal.add(c4.get(i));
			}
		}
		
		List<Object> teste5 = jobApoio.testeVazioConsulta(consulta8);
		if(teste5.isEmpty()){
		}else{
			List<Integer> c5 = jobApoio.todosMetodos(consulta8);
			for(int i = 0; i < c5.size(); i++){
				idsJobsFinal.add(c5.get(i));
			}
		}

		if(tipo.equals("relatorio")){
			return montaConsultaJob2(idsJobsFinal);
		}else{
			return printRelatorio(idsJobsFinal);
		}
		
		
		
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
	
	
	public ModelAndView montaConsultaJob2(List<Integer> idJobs) throws ParseException{
		MV.setViewName("job/relatorioJob");
 		List<JobConsulta> listaDeJobs = jobDAO.lista(idJobs);
 		MV.addObject("jobs", listaDeJobs);
		return MV;
	}
	
	public ModelAndView printRelatorio(List<Integer> idJobs) throws ParseException{ // Gera lista para Imprimir no relatorio
		MV.setViewName("job/printRelatorioJob");
		List<JobConsulta> listaDeJobs = jobDAO.lista(idJobs);
			
		MV.addObject("data", Calendar.getInstance());
		MV.addObject("jobs", listaDeJobs);
		
		
		
		/*List<LocalEvento> localEventoTotal = new ArrayList<LocalEvento>();
		
		
		
		
		for(int i = 0;i < idJobs.size();i++){
			
          List<LocalEvento> localEventos = (List<LocalEvento>) localEventoTeste(idJobs.get(i));
          		
          	for (int j = 0; j < localEventos.size(); j++){
          			
          		localEventoTotal.add(localEventos.get(j));
          	}
		}
		
		
		
		
		
		
		for (int i = 0;i < localEventoTotal.size();i++) {
			System.out.println("Id do Job: "+localEventoTotal.get(i).getIdJob().getIdJob());
			System.out.println("Nome Evento: "+localEventoTotal.get(i).getLocalEventoNome());
			System.out.println("Endereço do Evento: "+localEventoTotal.get(i).getLocalEventoEndereco());
			System.out.println("Data inicio: "+localEventoTotal.get(i).getLocalEventoDataInicio());
			System.out.println("------------------------------------------------------------------------");
		}
		
		
		
		MV.addObject("locaisEventos", listaDeJobs);*/
		
		
		
		return MV;
	}
	
	public List<LocalEvento> localEventoTeste(Integer idJob){
		String consulta = "SELECT l FROM LocalEvento l where idJob = "+ idJob +" order by localEventoDataInicio";
		
		Query q = manager.createQuery(consulta);
		
		return q.getResultList();
	}
	
	
	
	
	
	
	
	
}
