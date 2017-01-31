package br.com.sysloccOficial.controllers;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.DataDAO;
import br.com.sysloccOficial.model.DataTeste;




@Controller
@Transactional
public class DataController {
	
	@Autowired
	private DataDAO dataDAO;

	@Autowired
	private Utilitaria util;
	
	
	@RequestMapping("/TesteData")
	public ModelAndView data(){
		ModelAndView MV = new ModelAndView();
		
		MV.setViewName("testes/data");
		return MV;
	}
	
	@RequestMapping("/salvaData")
	public ModelAndView salva(String h, String d){
		
		String data = d +" "+ h;
		String hora = h;
		System.out.println("data e hora: "+data);
		
		DataTeste t = new DataTeste();
	
		
		t.setDate(util.formataDatasStringParaCalendar(data));
		
		t.setHora(util.formataHoraStringParaCalendar(hora));
			
		
		dataDAO.salva(t);
		
		ModelAndView mv = new ModelAndView("forward:TesteData");
		mv.addObject("datas", dataDAO.mostra());
		
		return mv;
	}
	
}
