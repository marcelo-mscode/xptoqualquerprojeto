package br.com.sysloccOficial.prospeccao.controllerProspeccao.interacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.prospeccao.dao.InteracoesDAO;

@Controller 
public class ProspeccaoInteracoes {

	@Autowired InteracoesDAO interacaoDAO;
	@Autowired EmpresaDAO empresaDAO;
	@RequestMapping("interacoes")
	
	
	public ModelAndView interacoes(){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("prospeccao/interacao/interacao");
		MV.addObject("interacoes", interacaoDAO.listaInteracoes());
		MV.addObject("empresas", empresaDAO.mostraEmpresaInteracao());
		return MV;
	}
	
	
}
