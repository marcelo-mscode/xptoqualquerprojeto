package br.com.sysloccOficial.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sysloccOficial.daos.ConfiguracaoDAO;
import br.com.sysloccOficial.model.Configuracao;

@Controller
@Transactional
public class ConfiguracaoController {
	@PersistenceContext
	private EntityManager manager;
	@Autowired ImpostoController impostoController;
	@Autowired ConfiguracaoDAO configuracaoDAO;
	
	
	
	
	@RequestMapping("/configuracaoImposto")
	public String configuracaoImposto(){
		return null;
	}
	
	@RequestMapping("/salvaConfiguracao")
	public String salvaConfiguracao(Configuracao c){
		configuracaoDAO.salvaConfiguracao(c);
		return "redirect:imposto";
	}
	
	
	
	
}
