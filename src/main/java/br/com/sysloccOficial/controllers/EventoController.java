package br.com.sysloccOficial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaMsg;
import br.com.sysloccOficial.daos.LocalEventoDAO;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.LocalEvento;


@Controller
@Transactional
public class EventoController {
	
	@Autowired private Utilitaria util;
	@Autowired private UtilitariaMsg utilMsg;
	@Autowired private LocalEventoDAO localeventoDAO;
	
	ModelAndView MV = new ModelAndView();
	
	// ---------------------------------------------------- Métodos de Local do Evento -------------------------------------------------------------//

		@RequestMapping("/cadLocalEvento")
		public String CadLocalEvento(LocalEvento l, RedirectAttributes rd){
			Job j = new Job();
			
			String dataInicio= l.getDataInicio()+ " "+l.getHoraInicio();
	        String dataTermino= l.getDataTermino()+ " "+l.getHoraTermino();
			
	       if(l.getDataInicio() == null || l.getDataInicio().isEmpty() && l.getHoraInicio() == null || l.getHoraInicio().isEmpty()){
			
	       }
			else{l.setLocalEventoDataInicio(util.formataDatasStringParaCalendar(dataInicio));}
			
			if(l.getDataTermino() == null || l.getDataTermino().isEmpty() && l.getHoraTermino() == null || l.getHoraTermino().isEmpty()){
			}
			else{l.setLocalEventoDataTermino(util.formataDatasStringParaCalendar(dataTermino));}
			
			if(l.getIdLocalEvento() == null ){
			
				localeventoDAO.salva(l);
			}else{
				localeventoDAO.atualiza(l);
			}
			
			
			rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		
			return "redirect:editaJob?idJob=" + l.getJobId()+ "&idEmpresa="
					+ l.getIdEmpresa()+ "&idContato=" + j.getIdContato();
		}

		
	//-------------------- Método para exibir local do Evento ------------------------------//	
		
		@RequestMapping("/ExibeLocalEvento")
		public ModelAndView exibe(Integer idLocalEvento){
			MV.setViewName("job/modalLocalEvento");	
			MV.addObject("evento", localeventoDAO.mostraLocalModal(idLocalEvento));
			
			return MV;
		}

	// ---------------------------------------------------Fim Métodos Local Evento ------------------------------------------------------------- //	
		

	
	
	
	
	

}
