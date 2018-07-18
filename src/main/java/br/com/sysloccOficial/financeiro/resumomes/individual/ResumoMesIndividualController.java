package br.com.sysloccOficial.financeiro.resumomes.individual;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.analitico.individual.CarregaSaldosBancarios;
import br.com.sysloccOficial.financeiro.contaspagar.services.ServiceContasPagar;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDAO;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualMovimentoFinanceiro;
import br.com.sysloccOficial.financeiro.dao.MontaContasPagarDAO;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.financeiro.model.AnaliticoTotalBancos;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;
import br.com.sysloccOficial.model.producao.DtPgtoFornecedor;
import br.com.sysloccOficial.model.producao.ValorPagtoFornecedor;

@Controller
public class ResumoMesIndividualController extends CarregaSaldosBancarios{
	
	@Autowired RelatorioEventoDAO relatorioEventoDAO;
	@Autowired DadosEventosMes dadosEvento;
	@Autowired UtilitariaDatas utilDatas;
	@Autowired MontaContasPagarDAO montaObjeto;
	@Autowired AnaliticoIndividualMovimentoFinanceiro analiticoMovFinanceiroDAO;
	@Autowired private AnaliticoIndividualDAO analiticoIndDAO;
	

	@RequestMapping("resumoMesIndividual")
	public ModelAndView resumoMesIndex(Integer mes, Integer ano){
		
		// int mesDoAno = mes;
		
		List<RelatorioEventos> infoEvento = relatorioEventoDAO.relatorioEventoPorMesReferencia(mes,ano);

		if(!infoEvento.isEmpty()){
			
		ModelAndView MV = new ModelAndView("financeiro/resumoMes/individual/resumoMesIndividual");
		
		String anoMes = ( mes < 10 ) ? ano+"-0"+mes  : ano+"-"+mes;
		
		String nomeMes = utilDatas.nomeMesPorDigito(mes);
		
		
		Integer idAnalitico = pegaIdAnalitico(nomeMes, ano.toString());
		
		// Carrega cabecalho de saldos Bancarios ---------------------------------------------- //
		
		AnaliticoTotalBancos movimentoItau = carregaSaldosBancarios(idAnalitico,1);
		AnaliticoTotalBancos movimentoCef = carregaSaldosBancarios(idAnalitico,2);
		AnaliticoTotalBancos movimentoBradesco = carregaSaldosBancarios(idAnalitico,3);
		AnaliticoTotalBancos movimentoSantander = carregaSaldosBancarios(idAnalitico,4);
		
		
		BigDecimal totalTarifasBanco = movimentoItau.getTotalTarifas()
								       .add(movimentoCef.getTotalTarifas())
								       .add(movimentoBradesco.getTotalTarifas())
								       .add(movimentoSantander.getTotalTarifas());
		
		
		MV.addObject("movimentoItau", movimentoItau);
		MV.addObject("movimentoCef", movimentoCef);
		MV.addObject("movimentoBradesco", movimentoBradesco);
		MV.addObject("movimentoSantander", movimentoSantander);
		
		
		
		
		List<Lista> infoLista = relatorioEventoDAO.relatorioListasIdLista(infoEvento);
		
//		Integer impostoInterna = relatorioEventoDAO.pegaImpostoInterna(idLista);
		
		
		MV.addObject("infoEvento", infoEvento);
		MV.addObject("infoLista", infoLista);
		MV.addObject("somaTotalEventos", dadosEvento.somaTotalEventos(infoEvento));
/**
 * ABAIXO ESÁ COMENTADO PORQUE ESTOU CRIANDO RELATÓRIO INTERNO QUE FAZ ND APARECER AQUI, DESCOMENTAR DEPOIS !!!
 * 
 * 
 * 
 */
		MV.addObject("custoTerceiros", dadosEvento.custoTerceiros(infoEvento));
		MV.addObject("pgtoExternas", dadosEvento.pgtoExternas(infoEvento));
		MV.addObject("faturamentoMes", dadosEvento.faturamentoMes(dadosEvento.somaTotalEventos(infoEvento),dadosEvento.pgtoExternas(infoEvento)));
		
		// ----------------------------------------		
		BigDecimal impostos = dadosEvento.impostosSobreValorLoccoAgencia(infoEvento);
		MV.addObject("impostos", impostos);
		// ----------------------------------------		
		
		MV.addObject("totalCustosFaturamentos", 
							dadosEvento.totalCustosFaturamentos(
							dadosEvento.faturamentoMes(dadosEvento.somaTotalEventos(infoEvento),dadosEvento.pgtoExternas(infoEvento)),
							dadosEvento.impostosSobreValorLoccoAgencia(infoEvento)));
		
		
		MV.addObject("somaCacheEquipe", dadosEvento.somaCacheEquipe(infoEvento));
		MV.addObject("somaCacheDiretoria", dadosEvento.somaCacheDiretoria(infoEvento));
		
		BigDecimal somaTotalCache = dadosEvento.somaCacheTotal(dadosEvento.somaCacheEquipe(infoEvento),dadosEvento.somaCacheDiretoria(infoEvento));
		MV.addObject("somaCacheTotal", somaTotalCache);
		
		//
		BigDecimal lucroOperacional = dadosEvento.lucroOperacional(
				dadosEvento.faturamentoMes(dadosEvento.somaTotalEventos(infoEvento),dadosEvento.pgtoExternas(infoEvento)),
				dadosEvento.impostosSobreValorLoccoAgencia(infoEvento),
				dadosEvento.somaCacheTotal(dadosEvento.somaCacheEquipe(infoEvento),dadosEvento.somaCacheDiretoria(infoEvento))
				);
		MV.addObject("lucroOperacional", lucroOperacional);

		BigDecimal somaDespesasFixas = dadosEvento.SomaDespFixas(relatorioEventoDAO.despesasFixas("FinancImpostos",anoMes),
				relatorioEventoDAO.despesasFixas("FinancEscritorio",anoMes),	
				relatorioEventoDAO.despesasFixas("FinancTelefone",anoMes),
				relatorioEventoDAO.despesasFixas("FinancFolhaPgto",anoMes));
		MV.addObject("SomaDespFixas", somaDespesasFixas);
		MV.addObject("outrosImpostosContador", relatorioEventoDAO.despesasFixas("FinancImpostos", anoMes));
		MV.addObject("outrosEscritorio", relatorioEventoDAO.despesasFixas("FinancEscritorio",anoMes));
		MV.addObject("outrosTelefones", relatorioEventoDAO.despesasFixas("FinancTelefone",anoMes));
		MV.addObject("outrosFolhaPgto", relatorioEventoDAO.despesasFixas("FinancFolhaPgto",anoMes));
		

		BigDecimal finanDespesas = relatorioEventoDAO.despesasFixas("FinancDespesas",anoMes);
		MV.addObject("despCaixasProjetos", finanDespesas);
		BigDecimal somaDespVariaveis = dadosEvento.SomaDespVariaveis(
				totalTarifasBanco
				, finanDespesas
				, relatorioEventoDAO.despesasFixas("FinancOutrasDespesas",anoMes));
		
		MV.addObject("somaDespVariaveis", somaDespVariaveis);
		MV.addObject("outrasDespesas", relatorioEventoDAO.despesasFixas("FinancOutrasDespesas",anoMes));

		BigDecimal creditoAplic = dadosEvento.somaCreditosAplicacoes(somaDespesasFixas, somaDespVariaveis);
		MV.addObject("creditosAplicacoes",creditoAplic);
		
		BigDecimal MOContrib = relatorioEventoDAO.MOMargemContribuicao(ano.toString(),nomeMes);
		MV.addObject("MOmargemContribuicao", MOContrib);
		
		MV.addObject("giroDeficitAvit", dadosEvento.giroDeficitAvit(lucroOperacional,creditoAplic,MOContrib));
		
		MV.addObject("contasReceber", relatorioEventoDAO.contasReceberTotalResumoMes(ano.toString(),nomeMes));

/**
 * 		Concentrar os esforços para diminuir o gargalo nesse carregamento
 */
		/*List<Object[]> idListas = montaObjeto.pegaListasMesAtual(); 
		List<Object[]> listaAtual = montaObjeto.constroiObjetoTeste(idListas);
		List<Object[]> listaAnteriores = montaObjeto.constroiObjeto();
		BigDecimal somaTotal = montaObjeto.somaTotalMeses(listaAtual, listaAnteriores);
		MV.addObject("eventosContasPagar", somaTotal);*/
		
////////// FIM GARGALO //////////
		
		MV.addObject("salarios", relatorioEventoDAO.salarios(anoMes));

		MV.addObject("outrosImpostos", relatorioEventoDAO.outrosImpostos(anoMes));
		
		/*MV.addObject("totalPagar", dadosEvento.totalPagar(somaTotal,
				relatorioEventoDAO.salarios(anoMes),
				somaTotalCache, impostos, relatorioEventoDAO.outrosImpostos(anoMes)
				));*/
		// Total Giro Itau ( soma dos emprestimos cadastrados )
	
		/*BigDecimal giroItau = new BigDecimal("11781.48");
		MV.addObject("giroItau", giroItau);*/
		
		// Total conta Garantia Itau - ITAU LIMITE ( soma dos emprestimos cadastrados )
		BigDecimal contaGarantiaItau = analiticoMovFinanceiroDAO.contaGarantiaItau(idAnalitico);
		MV.addObject("contaGarantiaItau", contaGarantiaItau);

		// Total outros Itau - ITAU LIMITE ( soma dos emprestimos cadastrados )
		BigDecimal outrosItau = analiticoMovFinanceiroDAO.outrosItau(idAnalitico);
		MV.addObject("outrosItau", outrosItau);

		// Total giroSantander ( soma dos emprestimos cadastrados )
		BigDecimal giroSantander = analiticoMovFinanceiroDAO.giroSantander(idAnalitico);
		MV.addObject("giroSantander", giroSantander);

		// Total giroSantander ( soma dos emprestimos cadastrados )
		BigDecimal outrosSantander = analiticoMovFinanceiroDAO.outrosSantander(idAnalitico);
		MV.addObject("outrosSantander", outrosSantander);
		
		
		
		
		
		//Rodapé links para outras páginas
		MV.addObject("quantRelatorioEventos", analiticoIndDAO.quantEventosMes(ano, mes));
		MV.addObject("idsRelatorioEventos", analiticoIndDAO.idsRelatorioEventosMes(ano, mes));

		
		return MV;
	    }
		 else{
			
			ModelAndView MV = new ModelAndView("financeiro/resumoMes/individual/pageErroInfoIncompletas");

			return MV;
		}
		
	}
	
	@Autowired ServiceContasPagar serviceContasPagar;
	
	@RequestMapping("testeGargalo")
	public ModelAndView testeGargalo(){
		
		ModelAndView MV = new ModelAndView("testes/data");
		
	/*	List<Object[]> idListas = montaObjeto.pegaListasMesAtual(); 
		List<Object[]> listaAtual = montaObjeto.constroiObjetoTeste(idListas);
	*/
		
		
		List<DtPgtoFornecedor> pegarContasPendente = serviceContasPagar.pegarContasPendente();
		
		
		//List<ValorPagtoFornecedor> pegarValoresPendente = serviceContasPagar.pegarValoresPendente(pegarContasPendente);
		
		
		System.out.println();
		//List<Object[]> listaAnteriores = montaObjeto.constroiObjeto();
		/*BigDecimal somaTotal = montaObjeto.somaTotalMeses(listaAtual, listaAnteriores);
		MV.addObject("eventosContasPagar", somaTotal);*/
		
		
		
		return MV;
		
	}
	
	
}
