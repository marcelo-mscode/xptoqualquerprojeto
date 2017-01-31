package br.com.sysloccOficial.financeiro.resumomes.individual;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResumoMesIndividual {

	@RequestMapping("resumoMesIndividual")
	public ModelAndView resumoMesIndex(){
		ModelAndView MV = new ModelAndView("financeiro/resumoMes/individual/resumoMesIndividual");
		
		return MV;
	}
	
	
}
