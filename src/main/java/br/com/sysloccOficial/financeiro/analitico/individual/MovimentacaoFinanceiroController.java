package br.com.sysloccOficial.financeiro.analitico.individual;

import java.text.ParseException;
import java.util.List;

import org.hibernate.boot.registry.selector.spi.StrategySelectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualMovimentoFinanceiro;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancos;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancosSaidas;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancosTarifas;

@Controller
public class MovimentacaoFinanceiroController {

	@Autowired private AnaliticoIndividualMovimentoFinanceiro analiticoMovFinanceiroDAO;
	
	@RequestMapping("salvaNovaEntrada")
	@ResponseBody
	private ModelAndView salvaNovaEntrada(Integer idAnalitico,String DataPgto, String valor,String descricao,String ndnf,Integer idBanco) throws ParseException{
		
		analiticoMovFinanceiroDAO.novaEntrada(idAnalitico,DataPgto,valor,descricao,ndnf,idBanco);

		String bancos[] = tiposBancosEntradas(idBanco);

		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro"+bancos[0]);
		MV.addObject("idAnalitico",idAnalitico);
		MV.addObject(bancos[1], analiticoMovFinanceiroDAO.carregaAnaliticoEntradas(idAnalitico,idBanco));
		return MV;
	}
	
	
	@RequestMapping("editaMovimentacaoFinanceira")
	@ResponseBody
	private ModelAndView editaMovimentacaoFinanceira(Integer idTabela,String valor,String tipoCampo,Integer idBanco) throws ParseException{
		
		Integer idAnalitico = analiticoMovFinanceiroDAO.editaNovaEntrada(idTabela,valor,tipoCampo);

		String bancos[] = tiposBancosEntradas(idBanco);
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro"+bancos[0]);
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
		MV.addObject("saidasItau", analiticoMovFinanceiroDAO.carregaAnaliticoSaidas(idAnalitico,idBanco));
		
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
	
	@RequestMapping("salvaNovaTarifa")
	@ResponseBody
	private ModelAndView salvaNovaTarifa(Integer idAnalitico,String DataPgto, String valor,String descricao,Integer idBanco) throws ParseException{
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro/itau/itauTarifas");

		System.out.println(valor);
		
		analiticoMovFinanceiroDAO.novaTarifa(idAnalitico,DataPgto,valor,descricao,idBanco);
		
		MV.addObject("InfoAnalitico.idAnalitico",idAnalitico);
		MV.addObject("tarifasItau", analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,idBanco));

		return MV;
	}
	
	
	@RequestMapping("editaTarifasItau")
	@ResponseBody
	private ModelAndView editaTarifasItau(Integer idTabela,String valor,String tipoCampo,Integer idBanco) throws ParseException{
		
		String tipoBanco = "";
		
		if(idBanco == 1){
			tipoBanco = "/itauTarifas";
		}
		if(idBanco == 2){
			tipoBanco = "/itauTarifas";
		}
		
		
		
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro/itau"+tipoBanco);
		
		Integer idAnalitico = analiticoMovFinanceiroDAO.editaNovaTarifa(idTabela,valor,tipoCampo);
		
		MV.addObject("InfoAnalitico.idAnalitico",idAnalitico);
		List<MovimentacaoBancosTarifas> analitico2 = analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,idBanco);
		MV.addObject("tarifasItau",analitico2);
		return MV;
	}
	
	
	public String[] tiposBancosEntradas(Integer idBanco){
		
		String bancos[] =  new String[2]; 
		
		if(idBanco == 1){
			bancos[0] = "/itau/itauEntrada";
			bancos[1] = "entradasItau";
		}
		
		
		
		
		
		if(idBanco == 2){
			bancos[0] = "/cef/cefEntrada";
			bancos[1] = "entradasCEF";
		}
		
		return bancos;
	}
	
	
	
	
}
