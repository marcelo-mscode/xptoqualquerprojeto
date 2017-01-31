package br.com.sysloccOficial.financeiro.analitico.individual;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDespesasDAO;
import br.com.sysloccOficial.financeiro.model.FinancDespesas;
import br.com.sysloccOficial.financeiro.model.FinancFolhaPgto;

@Controller
public class AnaliticoIndividualDespesasController {

@Autowired private AnaliticoIndividualDespesasDAO analiticoIndDAO;
	
	@RequestMapping("salvaNovoDespesas")
	@ResponseBody
	private ModelAndView salvaNovoDespesas(Integer idAnalitico,String DataPgto, String valor,String descricao) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/despesasAjax");
		
		analiticoIndDAO.salvaNovoDespesas(idAnalitico,DataPgto,valor,descricao);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancDespesas> analitico2 = analiticoIndDAO.carregaAnaliticoDespesas(idAnalitico);
		MV.addObject("despesas",analitico2);
		
		return MV;
	}

	@RequestMapping("editaDespesas")
	@ResponseBody
	private ModelAndView editaDespesa(Integer idTabela,String dataPgto,String valor,String tipoCampo) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/despesasAjax");
		
		Integer idAnalitico = analiticoIndDAO.editaDespesas(idTabela,dataPgto,valor,tipoCampo);
		
		MV.addObject("idAnalitico",analiticoIndDAO.editaDespesas(idTabela,dataPgto,valor,tipoCampo));
		MV.addObject("despesas",analiticoIndDAO.carregaAnaliticoDespesas(idAnalitico));
		
		return MV;
	}
	
	
	
}
