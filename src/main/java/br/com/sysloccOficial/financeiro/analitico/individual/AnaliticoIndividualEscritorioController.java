package br.com.sysloccOficial.financeiro.analitico.individual;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualEscritorioDAO;
import br.com.sysloccOficial.financeiro.model.FinancEscritorio;


@Controller
public class AnaliticoIndividualEscritorioController {

	
	@Autowired private AnaliticoIndividualEscritorioDAO analiticoIndDAO;
	
	@RequestMapping("salvaNovoEscritorio")
	@ResponseBody
	private ModelAndView salvaNovoEscritorio(Integer idAnalitico,String valor,String descricao, int chkFixoOutrosImpostos){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/escritorioAjax");
		analiticoIndDAO.salvaNovoEscritorio(idAnalitico,valor,descricao, chkFixoOutrosImpostos);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancEscritorio> analitico2 = analiticoIndDAO.carregaAnaliticoEscritorio(idAnalitico);
		MV.addObject("escritorio",analitico2);
		
		return MV;
	}

	@RequestMapping("editaEscritorio")
	@ResponseBody
	private ModelAndView editaEscritorio(Integer idTabela,String valor,String tipoCampo){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/escritorioAjax");
		
		Integer idAnalitico = analiticoIndDAO.editaEscritorio(idTabela,valor,tipoCampo);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancEscritorio> analitico2 = analiticoIndDAO.carregaAnaliticoEscritorio(idAnalitico);
		MV.addObject("escritorio",analitico2);
		
		return MV;
	}
	
	
	
	
	
	
	
	
}
