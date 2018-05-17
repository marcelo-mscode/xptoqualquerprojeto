package br.com.sysloccOficial.financeiro.analitico.individual;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualOutrasDespesasDAO;
import br.com.sysloccOficial.financeiro.model.FinancOutrasDespesas;

@Controller
public class AnaliticoIndividualOutrasDespesasController {
@Autowired private AnaliticoIndividualOutrasDespesasDAO analiticoIndDAO;
	
	@RequestMapping("salvaNovoOutrasDespesas")
	@ResponseBody
	private ModelAndView salvaNovoDespesas(Integer idAnalitico,String DataPgto, String valor,String descricao, int chkFixo) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/outrasDespesasAjax");
		
		analiticoIndDAO.salvaNovoOutrasDespesas(idAnalitico,DataPgto,valor,descricao,chkFixo);
		
		MV.addObject("idAnalitico",idAnalitico);
	
		List<FinancOutrasDespesas> analitico2 = analiticoIndDAO.carregaAnaliticoOutrasDespesas(idAnalitico);
		MV.addObject("outrasdespesas",analitico2);
		
		return MV;
	}

	@RequestMapping("editaOutrasDespesas")
	@ResponseBody
	private ModelAndView editaDespesa(Integer idTabela,String dataPgto,String valor,String tipoCampo) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/outrasDespesasAjax");
		
		Integer idAnalitico = analiticoIndDAO.editaOutrasDespesas(idTabela,dataPgto,valor,tipoCampo);
		
		MV.addObject("idAnalitico",analiticoIndDAO.editaOutrasDespesas(idTabela,dataPgto,valor,tipoCampo));
		MV.addObject("outrasdespesas",analiticoIndDAO.carregaAnaliticoOutrasDespesas(idAnalitico));
		
		return MV;
	}

}
