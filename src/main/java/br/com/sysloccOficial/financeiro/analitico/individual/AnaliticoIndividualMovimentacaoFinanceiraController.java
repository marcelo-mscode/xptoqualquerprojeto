package br.com.sysloccOficial.financeiro.analitico.individual;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDAO;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualMovimentoFinanceiro;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancos;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancosSaidas;

@Controller
public class AnaliticoIndividualMovimentacaoFinanceiraController {

	@Autowired private AnaliticoIndividualMovimentoFinanceiro analiticoMovFinanceiroDAO;
	
	@RequestMapping("salvaNovaEntrada")
	@ResponseBody
	private ModelAndView salvaNovaEntrada(Integer idAnalitico,String DataPgto, String valor,String descricao,String ndnf,Integer idBanco) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro/itau/itauEntradaAjax");
		
		analiticoMovFinanceiroDAO.novaEntrada(idAnalitico,DataPgto,valor,descricao,ndnf,idBanco);
		MV.addObject("idAnalitico",idAnalitico);
		MV.addObject("entradasItau", analiticoMovFinanceiroDAO.carregaAnaliticoEntradas(idAnalitico,idBanco));
		return MV;
	}
	
	
	@RequestMapping("editaMovimentacaoFinanceira")
	@ResponseBody
	private ModelAndView editaMovimentacaoFinanceira(Integer idTabela,String valor,String tipoCampo,Integer idBanco) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro/itau/itauEntradaAjax");
		
		Integer idAnalitico = analiticoMovFinanceiroDAO.editaNovaEntrada(idTabela,valor,tipoCampo);
		MV.addObject("idAnalitico",idAnalitico);

		List<MovimentacaoBancos> analitico2 = analiticoMovFinanceiroDAO.carregaMovimentaBancos(idAnalitico,idBanco);
		MV.addObject("entradasItau",analitico2);
		return MV;
	}
	
	@RequestMapping("salvaNovaSaida")
	@ResponseBody
	private ModelAndView salvaNovaSaida(Integer idAnalitico,String DataPgto, String valor,String descricao,Integer idBanco) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro/itau/itauSaidaAjax");
		analiticoMovFinanceiroDAO.novaSaida(idAnalitico,DataPgto,valor,descricao,idBanco);
		
		MV.addObject("idAnalitico",idAnalitico);
		MV.addObject("saidasItau", analiticoMovFinanceiroDAO.carregaAnaliticoSaidas(idAnalitico,1));
		
		return MV;
	}
	
	@RequestMapping("editaMovimentacaoFinanceiraSaidas")
	@ResponseBody
	private ModelAndView editaMovimentacaoFinanceiraSaidas(Integer idTabela,String valor,String tipoCampo,Integer idBanco) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro/itau/itauSaidaAjax");
		
		Integer idAnalitico = analiticoMovFinanceiroDAO.editaNovaSaida(idTabela,valor,tipoCampo);
		MV.addObject("InfoAnalitico.idAnalitico",idAnalitico);

		List<MovimentacaoBancosSaidas> analitico2 = analiticoMovFinanceiroDAO.carregaMovimentaBancosSaidas(idAnalitico,idBanco);
		MV.addObject("saidasItau",analitico2);
		return MV;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
