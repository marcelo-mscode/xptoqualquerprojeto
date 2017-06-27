package br.com.sysloccOficial.financeiro.indexlistasinternas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.InternaListasDAO;
import br.com.sysloccOficial.model.Lista;


@Controller
public class IndexInterna {

	@Autowired InternaListasDAO internaListasDAO;

	@RequestMapping("listasInternas")
	public ModelAndView indexListasInternas(){
		ModelAndView MV = new ModelAndView("financeiro/interna/listasInternas/index");
		List<Lista> listaInternas = internaListasDAO.listasInternasComItensFechados();
		MV.addObject("listasInternas", listaInternas);
		return MV;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
