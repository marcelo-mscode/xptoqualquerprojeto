package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.atualizaInterna.AtualizaInternaRelatoriosEmMassa;
import br.com.sysloccOficial.financeiro.atualizaInterna.AtualizaRelatorioEventoApoio;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;


@Controller
public class RelatorioEventoIndividualController extends AtualizaInternaRelatoriosEmMassa{
	
	@Autowired RelatorioEventoIndividualApoio relApoio;
	@Autowired RelatorioEventoDAO relatorioEventoDAO;
	@Autowired RelatorioEventoDAO relatorioDAO;
	@Autowired AtualizaRelatorioEventoApoio relatorioApoio;
	
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
	
	@RequestMapping("atualizaCacheRelatorioEvento")
	public String atualizaCacheRelatorioEvento(Integer idRelatorio, Integer idCachePadrao, String novoValorCache,Integer idLista) throws ParseException{
		
		    relatorioEventoDAO.atualizaCacheEvento(idRelatorio, idCachePadrao, novoValorCache);

			
			 ArrayList<String> datas =  relatorioDAO.dataRelatoriosEventosCadastrados(idLista);
			 String mes = datas.get(1).toUpperCase().toString();
			 String ano = datas.get(2).toUpperCase().toString();
		
			 /**
			  * 
			  * Método herdado que Faz a atualização em massa dos relatórios de eventos passando os ids das listas
			  */
			 atualizaInternaRelatoriosEventosEmMassa(relatorioDAO.idsListaRelatoriosEventosPorMesAno(mes, ano));
		 
		 return "redirect:relatorioEventoIndividual?idLista="+idLista;
	}
	
}
