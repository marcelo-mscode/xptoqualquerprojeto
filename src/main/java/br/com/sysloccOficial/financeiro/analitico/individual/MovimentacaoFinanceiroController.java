package br.com.sysloccOficial.financeiro.analitico.individual;

import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos.MontaTiposbancos;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualMovimentoFinanceiro;
import br.com.sysloccOficial.financeiro.model.EmprestimoBancario;
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

		MontaTiposbancos tipos = new MontaTiposbancos();
		String bancos[] = tipos.montaTipoBancos(idBanco);
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro"+bancos[0]);
		
		MV.addObject("idAnalitico",idAnalitico);
		MV.addObject(bancos[1], analiticoMovFinanceiroDAO.carregaAnaliticoEntradas(idAnalitico,idBanco));
		return MV;
	}
	
	
	@RequestMapping("editaMovimentacaoFinanceira")
	@ResponseBody
	private ModelAndView editaMovimentacaoFinanceira(Integer idTabela,String valor,String tipoCampo,Integer idBanco) throws ParseException{
		
		Integer idAnalitico = analiticoMovFinanceiroDAO.editaNovaEntrada(idTabela,valor,tipoCampo);
		List<MovimentacaoBancos> analitico2 = analiticoMovFinanceiroDAO.carregaMovimentaBancos(idAnalitico,idBanco);

		MontaTiposbancos tipos = new MontaTiposbancos();
		String bancos[] = tipos.montaTipoBancos(idBanco);
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro"+bancos[0]);
		MV.addObject("idAnalitico",idAnalitico);

		MV.addObject(bancos[1],analitico2);
		return MV;
	}
	
	@RequestMapping("salvaNovaSaida")
	@ResponseBody
	private ModelAndView salvaNovaSaida(Integer idAnalitico,String DataPgto, String valor,String descricao,Integer idBanco) throws ParseException{
		analiticoMovFinanceiroDAO.novaSaida(idAnalitico,DataPgto,valor,descricao,idBanco);
		
		MontaTiposbancos tipos = new MontaTiposbancos();
		String bancos[] = tipos.montaTipoBancosSaidas(idBanco);
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro"+bancos[0]);
		MV.addObject("idAnalitico",idAnalitico);
		MV.addObject(bancos[1], analiticoMovFinanceiroDAO.carregaAnaliticoSaidas(idAnalitico,idBanco));
		
		return MV;
	}
	
	@RequestMapping("editaMovimentacaoFinanceiraSaidas")
	@ResponseBody
	private ModelAndView editaMovimentacaoFinanceiraSaidas(Integer idTabela,String valor,String tipoCampo,Integer idBanco) throws ParseException{
		
		Integer idAnalitico = analiticoMovFinanceiroDAO.editaNovaSaida(idTabela,valor,tipoCampo);

		MontaTiposbancos tipos = new MontaTiposbancos();
		String bancos[] = tipos.montaTipoBancosSaidas(idBanco);
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro"+bancos[0]);
		MV.addObject("idAnalitico",idAnalitico);
		
		List<MovimentacaoBancosSaidas> analitico2 = analiticoMovFinanceiroDAO.carregaMovimentaBancosSaidas(idAnalitico,idBanco);
		MV.addObject(bancos[1],analitico2);
		return MV;
	}
	
	@RequestMapping("salvaNovaTarifa")
	@ResponseBody
	private ModelAndView salvaNovaTarifa(Integer idAnalitico,String DataPgto, String valor,String descricao,Integer idBanco) throws ParseException{

		analiticoMovFinanceiroDAO.novaTarifa(idAnalitico,DataPgto,valor,descricao,idBanco);
		
		MontaTiposbancos tipos = new MontaTiposbancos();
		String bancos[] = tipos.montaTipoBancosTarifas(idBanco);
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro"+bancos[0]);
		MV.addObject("idAnalitico",idAnalitico);
		MV.addObject(bancos[1], analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,idBanco));

		return MV;
	}
	
	
	@RequestMapping("editaTarifas")
	@ResponseBody
	private ModelAndView editaTarifas(Integer idTabela,String valor,String tipoCampo,Integer idBanco) throws ParseException{
		
		MontaTiposbancos tipos = new MontaTiposbancos();
		String bancos[] = tipos.montaTipoBancosTarifas(idBanco);
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro"+bancos[0]);
		
		Integer idAnalitico = analiticoMovFinanceiroDAO.editaNovaTarifa(idTabela,valor,tipoCampo);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<MovimentacaoBancosTarifas> analitico2 = analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,idBanco);
		MV.addObject(bancos[1],analitico2);
		return MV;
	}
	
	@RequestMapping("editaSaldosBancos")
	@ResponseBody
	private ModelAndView editaSaldosBancos(String valor,Integer idAnalitico,String tipoCampo,Integer idBanco) throws ParseException{
		
		MontaTiposbancos tipos = new MontaTiposbancos();
		String bancos[] = tipos.montaTipoBancosTarifas(idBanco);
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/movimentoFinanceiro"+bancos[0]);
		
		analiticoMovFinanceiroDAO.editaSaldosBancos(valor,idAnalitico,tipoCampo,idBanco);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<MovimentacaoBancosTarifas> analitico2 = analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,idBanco);
		MV.addObject(bancos[1],analitico2);
		return MV;
	}
	
	
	@RequestMapping("salvaNovoEmprestimo")
	@ResponseBody
	private ModelAndView salvaNovoEmprestimo(Integer idAnalitico,String DataPgto, String valor,String descricao,Integer idBanco) throws ParseException{
		
		analiticoMovFinanceiroDAO.novoEmprestimo(idAnalitico,DataPgto,valor,descricao,idBanco);

		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/emprestimos");
		MV.addObject("idAnalitico",idAnalitico);
		MV.addObject("emprestimosBancos", analiticoMovFinanceiroDAO.carregaEmprestimos(idAnalitico));

		return MV;
		}
	
	
	@RequestMapping("editaEmprestimo")
	@ResponseBody
	private ModelAndView editaEmprestimo(String valor,Integer idAnalitico,String tipoCampo,Integer idEmprestimo) throws ParseException{
		
		analiticoMovFinanceiroDAO.editaEmprestimo(idEmprestimo,valor,tipoCampo);

		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/emprestimos");
		
		MV.addObject("idAnalitico",idAnalitico);
		
		LinkedHashSet<EmprestimoBancario> emprestimos = analiticoMovFinanceiroDAO.carregaEmprestimos(idAnalitico);
		MV.addObject("emprestimosBancos",emprestimos);
		return MV;
	}
	
	@RequestMapping("sqlFormata")
	public ModelAndView sqlFormata(String texto){
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/sqlFormata");
		MV.addObject("descricao", texto);
		
		System.out.println(texto);
		
		return MV;
	}
	
	
	@RequestMapping("sqlFormataTexto")
	public String sqlFormataTexto(String dia,String descricao,String valor){
		
		
		String dias = "02";
		
		
		String valorSplit1 = valor.replace(".", "");
		String valorSplit2 = valorSplit1.replace(",", ".");
		
		
		String base = "INSERT INTO `locomotivos`.`movimentacaobancostarifas` (`data`, `descricao`, `valor`, `analitico`, `banco`) VALUES (";
		String diaZ = base +"'"+" 2018-01-"+dias+" 14:25:51"+"','";
		String descricaoZ = diaZ + descricao+"','";
		
		String base2 = "INSERT INTO `locomotivos`.`emprestimobancario` (`dataPrimeiroPagamento`, `descricao`, `diaMesPagamento`, `pago`, `quantidadeParcelas`, `valorParcela`, `analitico`, `banco`) VALUES ('2018-02-02 00:00:00', 'teste', '0', '0', '0', '523.00', '12', '1');";
		
		
		String valorZ = descricaoZ + valorSplit2;
		
		String finalSQL = ", '12', '1');";
		
		String consulta = valorZ +"'"+ finalSQL;
		 
		return "redirect:sqlFormata?texto="+consulta;
	}

	@RequestMapping("sqlFormataTexto")
	public String sqlFormataTexto(String dia,String descricao,String valor){
		
		
		String dias = "02";
		
		
		String valorSplit1 = valor.replace(".", "");
		String valorSplit2 = valorSplit1.replace(",", ".");
		
		
		String base = "INSERT INTO `locomotivos`.`movimentacaobancostarifas` (`data`, `descricao`, `valor`, `analitico`, `banco`) VALUES (";
		String diaZ = base +"'"+" 2018-01-"+dias+" 14:25:51"+"','";
		String descricaoZ = diaZ + descricao+"','";
		
		String base2 = "INSERT INTO `locomotivos`.`emprestimobancario` (`dataPrimeiroPagamento`, `descricao`, `diaMesPagamento`, `pago`, `quantidadeParcelas`, `valorParcela`, `analitico`, `banco`) VALUES ('2018-02-02 00:00:00', 'teste', '0', '0', '0', '523.00', '12', '1');";
		
		
		String valorZ = descricaoZ + valorSplit2;
		
		String finalSQL = ", '12', '1');";
		
		String consulta = valorZ +"'"+ finalSQL;
		
		return "redirect:sqlFormata?texto="+consulta;
	}
	
	
	
	
	
	
	
	
	
	
	
}
