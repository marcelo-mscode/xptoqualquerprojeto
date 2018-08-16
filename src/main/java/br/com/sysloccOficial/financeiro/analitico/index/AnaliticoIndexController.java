package br.com.sysloccOficial.financeiro.analitico.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoDAO;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;

@Controller
public class AnaliticoIndexController {
	@Autowired private AnaliticoDAO analiticoDAO;
	@RequestMapping("indexAnalitico")
	public ModelAndView indexAnalitico(){
		ModelAndView MV = new ModelAndView("financeiro/analitico/index/indexAnalitico");
		List<String> indexAnaliticoAno = analiticoDAO.indexAnaliticoAno();
		MV.addObject("analiticoAno", indexAnaliticoAno);
		List<FinancAnalitico> indexAnalitico = analiticoDAO.indexAnalitico();
		MV.addObject("analitico", indexAnalitico);
		
		return MV;
	}
}
