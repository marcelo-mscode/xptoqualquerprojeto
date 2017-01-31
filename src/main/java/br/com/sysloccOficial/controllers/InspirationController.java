package br.com.sysloccOficial.controllers;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class InspirationController {
		
	ModelAndView MV = new ModelAndView();
	
	@RequestMapping("/inspiration")
	public ModelAndView inspiration(){
		MV.setViewName("inspiration/inspiration");
		
		
		
		return MV;
	}
	
	
	
	
}


