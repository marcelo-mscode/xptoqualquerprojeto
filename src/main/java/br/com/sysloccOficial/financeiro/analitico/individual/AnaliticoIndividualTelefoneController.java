package br.com.sysloccOficial.financeiro.analitico.individual;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualTelefoneDAO;
import br.com.sysloccOficial.financeiro.model.FinancTelefone;

@Controller
public class AnaliticoIndividualTelefoneController {

	@Autowired private AnaliticoIndividualTelefoneDAO analiticoIndDAO;
	
	
	@RequestMapping("salvaNovoTelefone")
	@ResponseBody
	private ModelAndView salvaNovoTelefone(Integer idAnalitico,String valor,String descricao){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/telefonesAjax");
		
		analiticoIndDAO.salvaNovoTelefone(idAnalitico,valor,descricao);
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancTelefone> analitico2 = analiticoIndDAO.carregaAnaliticoTelefone(idAnalitico);
		MV.addObject("telefone",analitico2);
		return MV;
	}

	@RequestMapping("editaTelefone")
	@ResponseBody
	private ModelAndView editaTelefone(Integer idTabela,String valor,String tipoCampo){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/telefonesAjax");
		
		Integer idAnalitico = analiticoIndDAO.editaTelefone(idTabela,valor,tipoCampo);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancTelefone> analitico2 = analiticoIndDAO.carregaAnaliticoTelefone(idAnalitico);
		MV.addObject("telefone",analitico2);
		return MV;
	}
	
	
	
}
