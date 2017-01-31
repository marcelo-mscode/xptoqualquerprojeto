package br.com.sysloccOficial.prospeccao.controllerProspeccao.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.prospeccao.dao.ProspeccaoDAO;

@Controller
public class ProspeccaoIndexController {
	
	@Autowired private ProspeccaoDAO prospeccaoDAO;
	@Autowired EmpresaDAO empresaDAO;
	
	@RequestMapping("prospeccoes")
	public ModelAndView prospeccoes(){
		ModelAndView MV = new ModelAndView();
		MV.addObject("prospeccoes", prospeccaoDAO.listaProspeccoes());
		MV.addObject("empresas", empresaDAO.mostraEmpresaInteracao());
		MV.setViewName("prospeccao/index/prospeccaoIndex");
		return MV;
	}
	
	
	
}
