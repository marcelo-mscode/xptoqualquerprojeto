package br.com.sysloccOficial.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.ConfiguracaoDAO;
import br.com.sysloccOficial.daos.ImpostoDAO;
import br.com.sysloccOficial.model.Imposto;



@Controller
@Transactional
public class ImpostoController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired ImpostoDAO impostoDAO; 
	@Autowired ConfiguracaoDAO configuracaoDAO; 
	
	ModelAndView MV = new ModelAndView();
	
	
	
	/*@RequestMapping(value="/imposto",method=RequestMethod.GET)*/
	@RequestMapping("/imposto")
	public ModelAndView listaImposto(){
		MV.setViewName("imposto/configuravalores");
		MV.addObject("imposto", impostoDAO.listaImposto());
		MV.addObject("configuracao", configuracaoDAO.listaConfiguracao());
		return MV;
	}
	
	@RequestMapping("/salvaImposto")
	public String salvaImposto(Imposto imposto){
		impostoDAO.salvaImposto(imposto);
		return "redirect:imposto";
		
	}
	@RequestMapping("/ChamaJSPEditaImpostoPorAjax")
	public ModelAndView ChamaJSPEditaImpostoPorAjax(Integer idImposto){
		MV.setViewName("imposto/impostoEdita");
		MV.addObject("impostoEdita", impostoDAO.exibeImpostoEditar(idImposto));
		return MV;
	}
	@RequestMapping("/editaImposto")
	public String EditaImposto(Imposto imposto){
		impostoDAO.editaImposto(imposto);
		return "redirect:imposto";
	}
	
	
	
	
	
	
	
	
	
	
}
