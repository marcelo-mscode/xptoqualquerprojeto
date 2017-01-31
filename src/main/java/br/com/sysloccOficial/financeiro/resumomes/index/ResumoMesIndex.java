package br.com.sysloccOficial.financeiro.resumomes.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ResumoMesIndex {
	
	
	@RequestMapping("resumoMesIndex")
	public ModelAndView resumoMesIndex(){
		ModelAndView MV = new ModelAndView("financeiro/resumoMes/index/resumoMesIndex");
		
		return MV;
	}
	
	
	
}
