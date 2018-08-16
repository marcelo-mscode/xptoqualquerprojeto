package br.com.sysloccOficial.financeiro.analitico.controledespesas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControleDespesasController {
	
	@RequestMapping("controleDespesas")
	public ModelAndView ControleDespesas(Integer idAnalitico){
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/controleDespesas/controleDespesas");
		
		return MV;
		
	}
	
	

}
