package br.com.sysloccOficial.financeiro.analitico.individual;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualFolhaDAO;
import br.com.sysloccOficial.financeiro.model.FinancEscritorio;
import br.com.sysloccOficial.financeiro.model.FinancFolhaPgto;



@Controller
public class AnaliticoIndividualFolhaController {
	
	@Autowired private AnaliticoIndividualFolhaDAO analiticoIndDAO;
	
	@RequestMapping("salvaNovoFolha")
	@ResponseBody
	private ModelAndView salvaNovoEscritorio(Integer idAnalitico,String valor,String descricao){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/folhaAjax");
		
		analiticoIndDAO.salvaNovoFolha(idAnalitico,valor,descricao);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancFolhaPgto> analitico2 = analiticoIndDAO.carregaAnaliticoFolha(idAnalitico);
		MV.addObject("folha",analitico2);
		
		return MV;
	}

	@RequestMapping("editaFolha")
	@ResponseBody
	private ModelAndView editaFolha(Integer idTabela,String valor,String tipoCampo){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/folhaAjax");
		
		Integer idAnalitico = analiticoIndDAO.editaFolha(idTabela,valor,tipoCampo);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancFolhaPgto> analitico2 = analiticoIndDAO.carregaAnaliticoFolha(idAnalitico);
		MV.addObject("folha",analitico2);
		
		return MV;
	}
	
	
	
	
	
}

