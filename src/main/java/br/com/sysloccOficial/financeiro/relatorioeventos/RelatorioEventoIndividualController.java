package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;


@Controller
public class RelatorioEventoIndividualController {
	
	@Autowired RelatorioEventoIndividualApoio relApoio;
	@Autowired RelatorioEventoDAO relatorioEventoDAO;
	
	@RequestMapping("relatorioEventoIndividual")
	public ModelAndView relatorioEventoIndividual(Integer idLista){
		ModelAndView MV = new ModelAndView("financeiro/relatorioEventos/relatorioIndividual/relatorioIndividual");
		
		Lista infoLista = relatorioEventoDAO.listaPorIdLista(idLista);
		
		RelatorioEventos relatorioEventos = relatorioEventoDAO.relatorioEventoPorIdLista(idLista);
		
		List<RelatorioBVS> relatorioBVS = relApoio.relatorioBVS(idLista);
		
	 	LinkedHashSet<CacheEvento> relatorio = relatorioEventoDAO.listaCacheEventoPorEvento(relatorioEventos.getIdRelatorioEvento());
		
	 	
	 	InfoInterna infoInterna = relatorioEventoDAO.infoInterna(idLista);
	 	
	    MV.addObject("relatorio",relatorioEventos);
		MV.addObject("relatorioCaches", relatorio);
		MV.addObject("relBVS",relatorioBVS);
		MV.addObject("infoLista", infoLista);

		MV.addObject("infoInterna", infoInterna);
		
		
		
		return MV;
	}
	
	
}
