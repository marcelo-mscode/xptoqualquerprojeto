package br.com.sysloccOficial.financeiro.analitico.individual;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDAO;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualMovimentoFinanceiro;
import br.com.sysloccOficial.financeiro.dao.CacheDAO;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;
import br.com.sysloccOficial.financeiro.resumomes.individual.DadosEventosMes;
import br.com.sysloccOficial.model.RelatorioEventos;
import br.com.sysloccOficial.model.VideosYt;



@Controller
public class AnaliticoIndividualController extends CarregaSaldosBancarios {
	
	//@Autowired AnaliticoIndividualMovimentoFinanceiro analiticoMovFinanceiroDAO;

	
	
	@Autowired private AnaliticoIndividualDAO analiticoIndDAO;
	@Autowired DadosEventosMes dadosEvento;
	@Autowired RelatorioEventoDAO relatorioEventoDAO;
	@Autowired CacheDAO cacheDAO;
	
	@RequestMapping("analiticoIndividual")
	private ModelAndView analiticoIndividual(Integer idAnalitico){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/analiticoindividual");
		
		
		// Carrega cabecalho de saldos Bancarios ---------------------------------------------- //
		
		MV.addObject("movimentoItau", carregaSaldosBancarios(idAnalitico,1));
		MV.addObject("movimentoCef", carregaSaldosBancarios(idAnalitico,2));
		MV.addObject("movimentoBradesco", carregaSaldosBancarios(idAnalitico,3));
		MV.addObject("movimentoSantander", carregaSaldosBancarios(idAnalitico,4));
		
		// --------------------------------------------------------------------- //
		
		
		
		FinancAnalitico analitico = analiticoIndDAO.carregaAnaliticoIndividual(idAnalitico);
		MV.addObject("InfoAnalitico",analitico);
		MV.addObject("idAnalitico",analitico.getIdAnalitico());
		
		MV.addObject("escritorio",analiticoIndDAO.carregaAnaliticoIndividualEscritorio(idAnalitico));
		MV.addObject("telefone",analiticoIndDAO.carregaAnaliticoIndividualTelefones(idAnalitico));
		MV.addObject("folha",analiticoIndDAO.carregaAnaliticoIndividualFolha(idAnalitico));
		MV.addObject("despesas",analiticoIndDAO.carregaAnaliticoIndividualDespesas(idAnalitico));
		MV.addObject("outrasdespesas",analiticoIndDAO.carregaAnaliticoIndividualOutrasDespesas(idAnalitico));
		MV.addObject("impostos",analiticoIndDAO.carregaAnaliticoIndividualFinancImpostos(idAnalitico));
		
		List<RelatorioEventos> infoEvento = relatorioEventoDAO.relatorioEventoPorMesReferencia(01,2017);
		
		MV.addObject("DemostrativoImpostos", dadosEvento.impostosSobreValorLoccoAgencia(infoEvento));
		MV.addObject("somaCacheTotal", dadosEvento.somaCacheTotal(dadosEvento.somaCacheEquipe(infoEvento),dadosEvento.somaCacheDiretoria(infoEvento)));
		MV.addObject("ListaCacheTotal", cacheDAO.listaCachesPorMesAno(idAnalitico));

// ----------------------------------------------------->		
		
		// ---- Entradas/Saidas ITAU ---- //		
		MV.addObject("entradasItau", analiticoMovFinanceiroDAO.carregaAnaliticoEntradas(idAnalitico,1));
		MV.addObject("saidasItau", analiticoMovFinanceiroDAO.carregaAnaliticoSaidas(idAnalitico,1));
		MV.addObject("tarifasItau", analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,1));
		// ---- Entradas/Saidas CEF ---- //		
		MV.addObject("entradasCEF", analiticoMovFinanceiroDAO.carregaAnaliticoEntradas(idAnalitico,2));
		MV.addObject("saidasCEF", analiticoMovFinanceiroDAO.carregaAnaliticoSaidas(idAnalitico,2));
		MV.addObject("tarifasCEF", analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,2));
		// ---- Entradas/Saidas BRADESCO ---- //		
		MV.addObject("entradasBradesco", analiticoMovFinanceiroDAO.carregaAnaliticoEntradas(idAnalitico,3));
		MV.addObject("saidasBradesco", analiticoMovFinanceiroDAO.carregaAnaliticoSaidas(idAnalitico,3));
		MV.addObject("tarifasBradesco", analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,3));
		// ---- Entradas/Saidas SANTANDER ---- //		
		MV.addObject("entradasSantader", analiticoMovFinanceiroDAO.carregaAnaliticoEntradas(idAnalitico,4));
		MV.addObject("saidasSantander", analiticoMovFinanceiroDAO.carregaAnaliticoSaidas(idAnalitico,4));
		MV.addObject("tarifasSantander", analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,4));
		
		// ---- EMPRESTIMOS ---- //		
		MV.addObject("emprestimosBancos", analiticoMovFinanceiroDAO.carregaEmprestimos(idAnalitico));

		return MV;
	}


	@RequestMapping("videos")
	public ModelAndView videos (){
		
		ModelAndView MV = new ModelAndView("videos/videos");
		
		return MV;
	}
	
	@RequestMapping("salvarVideos")
	public ModelAndView salvarVideos(VideosYt videos){
		ModelAndView MV = new ModelAndView("videos/videos");
		analiticoIndDAO.salvaVideo(videos);
		return MV;
	}
	
}
