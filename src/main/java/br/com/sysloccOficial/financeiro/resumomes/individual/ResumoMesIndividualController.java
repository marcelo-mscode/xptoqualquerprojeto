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
		
		MV.addObject("lucroOperacional", dadosEvento.lucroOperacional(
				dadosEvento.faturamentoMes(dadosEvento.somaTotalEventos(infoEvento),dadosEvento.pgtoExternas(infoEvento)),
				dadosEvento.impostos(dadosEvento.somaTotalEventos(infoEvento)),
				dadosEvento.somaCacheTotal(dadosEvento.somaCacheEquipe(infoEvento),dadosEvento.somaCacheDiretoria(infoEvento))
				));
		
		MV.addObject("outrosImpostosContador", relatorioEventoDAO.despesasFixas("FinancImpostos","2016-10"));
		MV.addObject("outrosEscritorio", relatorioEventoDAO.despesasFixas("FinancEscritorio","2016-10"));
		MV.addObject("outrosTelefones", relatorioEventoDAO.despesasFixas("FinancTelefone","2016-10"));
		
		
		
		return MV;
	}
	
	
}
