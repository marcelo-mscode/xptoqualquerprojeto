package br.com.sysloccOficial.financeiro.analitico.individual;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDAO;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualMovimentoFinanceiro;
import br.com.sysloccOficial.financeiro.dao.CacheDAO;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;
import br.com.sysloccOficial.financeiro.resumomes.individual.DadosEventosMes;
import br.com.sysloccOficial.model.CachePadraoAnalitico;
import br.com.sysloccOficial.model.RelatorioEventos;
import br.com.sysloccOficial.model.VideosYt;



@Controller
public class AnaliticoIndividualController {
	
	@Autowired private AnaliticoIndividualMovimentoFinanceiro analiticoMovFinanceiroDAO;

	
	
	@Autowired private AnaliticoIndividualDAO analiticoIndDAO;
	@Autowired DadosEventosMes dadosEvento;
	@Autowired RelatorioEventoDAO relatorioEventoDAO;
	@Autowired CacheDAO cacheDAO;
	
	@RequestMapping("analiticoIndividual")
	private ModelAndView analiticoIndividual(Integer idAnalitico){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/analiticoindividual");
		
		
		List<RelatorioEventos> infoEvento = relatorioEventoDAO.relatorioEventoPorMesReferencia(01,2017);
		
		
		FinancAnalitico analitico = analiticoIndDAO.carregaAnaliticoIndividual(idAnalitico);
		MV.addObject("InfoAnalitico",analitico);
		MV.addObject("idAnalitico",analitico.getIdAnalitico());
		
		MV.addObject("escritorio",analiticoIndDAO.carregaAnaliticoIndividualEscritorio(idAnalitico));
		MV.addObject("telefone",analiticoIndDAO.carregaAnaliticoIndividualTelefones(idAnalitico));
		MV.addObject("folha",analiticoIndDAO.carregaAnaliticoIndividualFolha(idAnalitico));
		MV.addObject("despesas",analiticoIndDAO.carregaAnaliticoIndividualDespesas(idAnalitico));
		MV.addObject("outrasdespesas",analiticoIndDAO.carregaAnaliticoIndividualOutrasDespesas(idAnalitico));
		MV.addObject("impostos",analiticoIndDAO.carregaAnaliticoIndividualFinancImpostos(idAnalitico));
		MV.addObject("DemostrativoImpostos", dadosEvento.impostosSobreValorLoccoAgencia(infoEvento));
		
		MV.addObject("somaCacheTotal", dadosEvento.somaCacheTotal(dadosEvento.somaCacheEquipe(infoEvento),dadosEvento.somaCacheDiretoria(infoEvento)));
		
		MV.addObject("ListaCacheTotal", cacheDAO.listaCachesPorMesAno());
		
		// ---- Entradas/Saidas Itau ---- //		
		MV.addObject("entradasItau", analiticoMovFinanceiroDAO.carregaAnaliticoEntradas(idAnalitico,1));
		MV.addObject("saidasItau", analiticoMovFinanceiroDAO.carregaAnaliticoSaidas(idAnalitico,1));
		MV.addObject("tarifasItau", analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,1));
		// ---- Entradas/Saidas CEF ---- //		
		MV.addObject("entradasCEF", analiticoMovFinanceiroDAO.carregaAnaliticoEntradas(idAnalitico,2));
		/*MV.addObject("saidasCEF", analiticoMovFinanceiroDAO.carregaAnaliticoSaidas(idAnalitico,1));
		MV.addObject("tarifasCEF", analiticoMovFinanceiroDAO.carregaAnaliticoTarifas(idAnalitico,1));*/
		
		
		
		
		
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
