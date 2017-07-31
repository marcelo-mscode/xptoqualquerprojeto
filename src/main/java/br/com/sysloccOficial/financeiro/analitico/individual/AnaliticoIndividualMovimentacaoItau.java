package br.com.sysloccOficial.financeiro.analitico.individual;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualItauDAO;

@Controller
public class AnaliticoIndividualMovimentacaoItau {

	@Autowired private AnaliticoIndividualItauDAO analiticoItauDAO;
	
	
	@RequestMapping("salvaNovaEntrada")
	@ResponseBody
	private ModelAndView salvaNovoTelefone(Integer idAnalitico,String DataPgto, String valor,String descricao,String ndnf) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/outrasDespesasAjax");
		
		
		
		
		
		analiticoItauDAO.salvaNovaEntrada(idAnalitico,DataPgto,valor,descricao,ndnf);
		
		
		System.out.println("Entrada Salva !");
		
		
		
		
	//	MV.addObject("idAnalitico",idAnalitico);
	
	/*	List<FinancOutrasDespesas> analitico2 = analiticoIndDAO.carregaAnaliticoOutrasDespesas(idAnalitico);
		MV.addObject("outrasdespesas",analitico2);*/
		
		

		
		/*analiticoIndDAO.salvaNovoTelefone(idAnalitico,valor,descricao);
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancTelefone> analitico2 = analiticoIndDAO.carregaAnaliticoTelefone(idAnalitico);
		MV.addObject("telefone",analitico2);*/
		return null;
	}

	/*@RequestMapping("editaTelefone")
	@ResponseBody
	private ModelAndView editaTelefone(Integer idTabela,String valor,String tipoCampo){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/telefonesAjax");
		
		Integer idAnalitico = analiticoIndDAO.editaTelefone(idTabela,valor,tipoCampo);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancTelefone> analitico2 = analiticoIndDAO.carregaAnaliticoTelefone(idAnalitico);
		MV.addObject("telefone",analitico2);
		return MV;
	}*/
	
	
	
}
