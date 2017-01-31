package br.com.sysloccOficial.consultas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.consultas.consultasAvancadas.ConsultasPassaSql;
import br.com.sysloccOficial.daos.JobListaJobDAO;
import br.com.sysloccOficial.model.JobConsulta;

@Controller
public class JobConsultasAjax extends ConsultaJob{
	
	@PersistenceContext private EntityManager manager;
	@Autowired private ConsultasPassaSql c;
	@Autowired private JobListaJobDAO jobDAO;
	
	
	@RequestMapping("/ConsultaPorEmpresaComJob")
	public ModelAndView ConsultaPorEmpresaComJob(Integer idEmpresa){
		return setaParametroAjax(" j.empresa.idEmpresa= "+idEmpresa+" order by codJob desc");
	}
	
	@RequestMapping("/ConsultaPorJob")
	public ModelAndView ConsultaPorJob(Integer idJob){
		return setaParametro(" j.idJob="+idJob);
	}

	
	@RequestMapping("/ConsultaPorEmpresa")
	public ModelAndView ConsultaPorEmpresa(Integer idEmpresa) throws ParseException{
		String consulta = "select j.idJob from Job j where j.empresa.idEmpresa = " +idEmpresa+" order by codJob desc";
		return montaConsultaJob(consulta);
	}
	
	
	@RequestMapping("/ConsultaPorStatus")
	public ModelAndView ConsultaPorStatus(String teste,Integer idEmpresa) throws ParseException{

		String termo = " s.idStatus =";
		String consulta2 = montaConsulta(teste, termo);
		
		String consulta3 = "select j.idJob from Job j where j.empresa.idEmpresa = "
		   	  + idEmpresa +" and idStatusAtual in (SELECT s.idJobStatus FROM JobStatus s where "+
	            consulta2 +" order by s.idJobStatus desc) order by j.codJob desc";
		return montaConsultaJob(consulta3);
	}

	
	@RequestMapping("/ConsultaTodosPorStatus")
	public ModelAndView ConsultaTodosPorStatus(String teste) throws InterruptedException, ParseException{
		
		String termo = " j.idStatusAtual.idStatus.idEstatus=";
		String consulta2 = montaConsulta(teste, termo);
		String consulta4 = " order by criadoem desc";
		
		String consulta = "select j.idJob from Job j where" + consulta2+consulta4;
		return montaConsultaJob(consulta);
	}
	
	@RequestMapping("/ClassificaTodosPorStatus")
	public ModelAndView ClassificaTodosPorStatus(String teste, String valorPor) throws InterruptedException, ParseException{
		
		String termo = " j.idStatusAtual.idStatus.idEstatus=";
		String consulta2 = montaConsulta(teste, termo);
		String consulta4 = " order by "+ valorPor;
		String consulta = "select j.idJob from Job j where" + consulta2+consulta4;
		
		/*System.out.println(consulta);
		JOptionPane.showMessageDialog(null, ""+consulta);*/
		return montaConsultaJob(consulta);
	}
	
	@RequestMapping("/classificaPorEmpresa")
	public ModelAndView classificaPorEmpresa(Integer idEmpresa, String valorPor) throws ParseException{
		String termo = " j.empresa.idEmpresa= "+idEmpresa+" order by "+valorPor;
		String consulta = "select j.idJob from Job j where" + termo;
		return montaConsultaJob(consulta);
	}
	
	@RequestMapping("/classificaPorEmpresaComCondicao")
	public ModelAndView classificaPorEmpresaComCondicao(String teste, Integer idEmpresa, String valorPor) throws ParseException{
		
		// teste --> São as condições do Status
		
		String termo = " s.idStatus =";
		String consulta2 = montaConsulta(teste, termo);
		String c = " j.empresa.idEmpresa = "+ idEmpresa +" and idStatusAtual in (SELECT s.idJobStatus FROM JobStatus s where "+
 	    consulta2 +" order by s.idJobStatus desc) order by "+ valorPor;
		String consulta = "select j.idJob from Job j where" + c;
		return montaConsultaJob(consulta);
	}
	
	@RequestMapping("/classificaTodosJobs")
	public ModelAndView classificaTodosJobs(String valorPor) throws ParseException{
		String termo = "  order by " + valorPor;
		String consulta = "select j.idJob from Job j " + termo;
		return montaConsultaJob(consulta);
	}

	
	public ModelAndView montaConsultaJob(String consulta) throws ParseException{
		MV.setViewName("job/listaStatusJob");
		List<Integer> idJobs =  (List<Integer>) c.retornaLista(consulta);
 		List<JobConsulta> listaDeJobs = jobDAO.lista(idJobs);
 		MV.addObject("jobs", listaDeJobs);
		return MV;
	}
	
}
