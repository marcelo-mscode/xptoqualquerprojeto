package br.com.sysloccOficial.model;

import java.text.ParseException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class Busca {
	
	
	public static void main(String[] args) {
		System.out.println("");
	}
	
	/*@RequestMapping("/relatorio")
	public ModelAndView relatorio(String valorPor) throws ParseException{
		
		// Dar select em StatusJob por status e pegar o id do job
		
		
		
		
	//	String termo = "  order by " + valorPor;
		
		
		String consulta = "select j.idJob from Job j          " + termo;
		String consulta = "select j.idJob from Job j where j.idStatusAtual.idStatus.idEstatus= 1"+
		" or  j.idStatusAtual.idStatus.idEstatus=5" +
		" or  j.idStatusAtual.idStatus.idEstatus=7"+
		" or  j.idStatusAtual.idStatus.idEstatus=6"+
		" or  j.idStatusAtual.idStatus.idEstatus=8"+
		" order by j.idStatusAtual.idStatus.idEstatus asc";
		
		List<Integer> idJobs =  (List<Integer>) c.retornaLista(consulta);
		
		String t = "";
		String termo = "idJob = ";
		int num = 0;
		
		
		for (int i = 0 ; i < idJobs.size(); i++){
			num = num +1;
		}
		
		num = num - 1;
		
		for (int i = 0 ; i < idJobs.size(); i++){
			
			String s = idJobs.get(i).toString();
			
			t = " "+t + termo + s +" ";
			
			if(i < num){
				t = t + " or ";
			}
			
			
		}
		
		
		
		
		System.out.println(t);
		
		
		
		
		String consultaDataLocalInicio = "SELECT distinct(l.idJob.idJob) FROM LocalEvento l"
									   + " where "+ t
									   + " order by localEventoDataInicio";
		
		Query q = manager.createQuery(consultaDataLocalInicio);
		
		
		List<Integer> idJobsOrdenados = q.getResultList();

		
		
		
		
		
		
		
		return montaConsultaJob(consulta);
	}*/
	
	
	
	
	
	
	
	
	
	
	
}
