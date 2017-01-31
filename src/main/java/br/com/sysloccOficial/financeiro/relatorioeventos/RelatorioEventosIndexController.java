package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;


@Controller
public class RelatorioEventosIndexController {
	
	
	@Autowired private RelatorioEventoDAO relatorioEventoDAO;
	
	@RequestMapping("relatorioEventosIndex")
	public ModelAndView relatorioEventosIndex(){
		ModelAndView MV = new ModelAndView("financeiro/relatorioEventos/relatorioEventoIndex");
		
		
		List<String> listaAnoRelatorioEventos = relatorioEventoDAO.listaAnoRelatorioEventos();
		MV.addObject("ano",listaAnoRelatorioEventos );

		List<RelatorioEventos> listaRelatorioEventos = relatorioEventoDAO.listaRelatorioEventos();
		MV.addObject("listaRelatorioEventos",listaRelatorioEventos);

		List<RelatorioEventos> listaRelatorioEventos2 = relatorioEventoDAO.listaRelatorioEventos();
		MV.addObject("listaRelatorioEventos2",listaRelatorioEventos2);
		
		List<Lista> listasProducao = relatorioEventoDAO.ListaProducao();
		MV.addObject("listasProducao",listasProducao);
		
		return MV;
	}
	
	
	

}
