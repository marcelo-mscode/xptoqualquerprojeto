package br.com.sysloccOficial.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.JobDAO;



@Controller
@Transactional
public class LocalEventoController {
	
	@Autowired
	private JobDAO jobDAO;
	@Autowired
	private EmpresaDAO empresaDAO;
	@Autowired
	private ContatoDAO contatoDAO;
	@Autowired
	private Utilitaria util;
	
	ModelAndView MV = new ModelAndView();
	
	
	
// --------------- Método usado para preencher a tela de job ------------ //	

	@RequestMapping("/atualizaLocalEvento")
	public ModelAndView EditaJob(String titulo,Integer idEmpresa,Integer idJob) {
		MV.setViewName("job/job");
		
		return MV;
	}
// ------------------------------------------------------------------------ //	

	
}
