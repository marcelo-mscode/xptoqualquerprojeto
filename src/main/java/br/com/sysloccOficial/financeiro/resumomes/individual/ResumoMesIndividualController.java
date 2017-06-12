package br.com.sysloccOficial.financeiro.resumomes.individual;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;

@Controller
public class ResumoMesIndividualController {
	
	@Autowired RelatorioEventoDAO relatorioEventoDAO;
	@Autowired DadosEventosMes dadosEvento;
	

	@RequestMapping("resumoMesIndividual")
	public ModelAndView resumoMesIndex(){
		ModelAndView MV = new ModelAndView("financeiro/resumoMes/individual/resumoMesIndividual");
		
		List<RelatorioEventos> infoEvento = relatorioEventoDAO.relatorioEventoPorMesReferencia(5);
		List<Lista> infoLista = relatorioEventoDAO.relatorioListasIdLista(infoEvento);
		
		MV.addObject("infoEvento", infoEvento);
		MV.addObject("infoLista", infoLista);
		MV.addObject("somaTotalEventos", dadosEvento.somaTotalEventos(infoEvento));
		MV.addObject("custoTerceiros", dadosEvento.custoTerceiros(infoEvento));
		MV.addObject("pgtoExternas", dadosEvento.pgtoExternas(infoEvento));
		MV.addObject("faturamentoMes", dadosEvento.faturamentoMes(dadosEvento.somaTotalEventos(infoEvento),dadosEvento.pgtoExternas(infoEvento)));
		MV.addObject("impostos", dadosEvento.impostos(dadosEvento.somaTotalEventos(infoEvento)));
		MV.addObject("totalCustosFaturamentos", 
							dadosEvento.totalCustosFaturamentos(
							dadosEvento.faturamentoMes(dadosEvento.somaTotalEventos(infoEvento),dadosEvento.pgtoExternas(infoEvento)),
							dadosEvento.impostos(dadosEvento.somaTotalEventos(infoEvento))));
		
		
		MV.addObject("somaCacheEquipe", dadosEvento.somaCacheEquipe(infoEvento));
		MV.addObject("somaCacheDiretoria", dadosEvento.somaCacheDiretoria(infoEvento));
		MV.addObject("somaCacheTotal", dadosEvento.somaCacheTotal(dadosEvento.somaCacheEquipe(infoEvento),dadosEvento.somaCacheDiretoria(infoEvento)));
		
		//
		BigDecimal lucroOperacional = dadosEvento.lucroOperacional(
				dadosEvento.faturamentoMes(dadosEvento.somaTotalEventos(infoEvento),dadosEvento.pgtoExternas(infoEvento)),
				dadosEvento.impostos(dadosEvento.somaTotalEventos(infoEvento)),
				dadosEvento.somaCacheTotal(dadosEvento.somaCacheEquipe(infoEvento),dadosEvento.somaCacheDiretoria(infoEvento))
				);
		MV.addObject("lucroOperacional", lucroOperacional);
		
		MV.addObject("outrosImpostosContador", relatorioEventoDAO.despesasFixas("FinancImpostos","2016-05"));
		MV.addObject("outrosEscritorio", relatorioEventoDAO.despesasFixas("FinancEscritorio","2016-10"));
		MV.addObject("outrosTelefones", relatorioEventoDAO.despesasFixas("FinancTelefone","2016-10"));
		MV.addObject("outrosFolhaPgto", relatorioEventoDAO.despesasFixas("FinancFolhaPgto","2016-10"));
		
		//
		BigDecimal somaDespesasFixas = dadosEvento.SomaDespFixas(relatorioEventoDAO.despesasFixas("FinancImpostos","2016-05"),
				relatorioEventoDAO.despesasFixas("FinancEscritorio","2016-10"),	
				relatorioEventoDAO.despesasFixas("FinancTelefone","2016-10"),
				relatorioEventoDAO.despesasFixas("FinancFolhaPgto","2016-10"));
		MV.addObject("SomaDespFixas", somaDespesasFixas);
		
		
		
		MV.addObject("despCaixasProjetos", relatorioEventoDAO.despesasFixas("FinancDespesas","2016-05"));
		
		//
		BigDecimal somaDespVariaveis = dadosEvento.SomaDespVariaveis(
				new BigDecimal("2289.04")
				, relatorioEventoDAO.despesasFixas("FinancDespesas","2016-05")
				, relatorioEventoDAO.despesasFixas("FinancOutrasDespesas","2016-05"));
		MV.addObject("somaDespVariaveis", somaDespVariaveis);
				
		MV.addObject("outrasDespesas", relatorioEventoDAO.despesasFixas("FinancOutrasDespesas","2016-05"));
		
		//
		BigDecimal creditoAplic = dadosEvento.somaCreditosAplicacoes(somaDespesasFixas, somaDespVariaveis);
		MV.addObject("creditosAplicacoes",creditoAplic);
		
		//
		BigDecimal MOContrib = relatorioEventoDAO.MOMargemContribuicao("2016","MAIO");
		MV.addObject("MOmargemContribuicao", MOContrib);

		MV.addObject("giroDeficitAvit", dadosEvento.giroDeficitAvit(lucroOperacional,creditoAplic,MOContrib));

		MV.addObject("contasReceber", relatorioEventoDAO.contasReceber("2016","MAIO"));
		
		//
		MV.addObject("eventosContasPagar", relatorioEventoDAO.contasReceber("2016","MAIO"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return MV;
	}
	
	
}
