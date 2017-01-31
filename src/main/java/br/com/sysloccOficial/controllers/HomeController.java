package br.com.sysloccOficial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.DepartamentoDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;

@Controller
public class HomeController {
	
	
	@Autowired private UsuarioDAO usuarioDAO;
	@Autowired private DepartamentoDAO departamentoDAO;
	@Autowired private NotificacaoController notifica;

	ModelAndView MV = new ModelAndView();

	@RequestMapping("/")
	public ModelAndView index() {
		MV.setViewName("index");
		MV.addObject("teste", notifica.qtdPendencias());
		return MV;
	}
	
/*	@RequestMapping("/")
	public String index() {
		return "index-usarquandomanutencao";
	}
*/
	@RequestMapping("/index")
	public String index1() {
		return "index";
	}

	

	@RequestMapping("/configuravalores")
	public ModelAndView configuraValores() {
		MV.setViewName("configuravalores");
	return MV;
	}

	
	
}

























