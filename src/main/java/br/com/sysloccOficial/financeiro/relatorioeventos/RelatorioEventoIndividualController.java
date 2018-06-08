package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.financeiro.atualizaInterna.AtualizaInternaRelatoriosEmMassa;
import br.com.sysloccOficial.financeiro.atualizaInterna.AtualizaRelatorioEventoApoio;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDAO;
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
	@Autowired UsuarioDAO usuarios;
	@Autowired private AnaliticoIndividualDAO analiticoIndDAO;
	
	
	@RequestMapping("relatorioEventoIndividual")
	public ModelAndView relatorioEventoIndividual(Integer idLista){
		ModelAndView MV = new ModelAndView("financeiro/relatorioEventos/relatorioIndividual/relatorioIndividual");
		
		Lista infoLista = relatorioEventoDAO.listaPorIdLista(idLista);
		
		RelatorioEventos relatorioEventos = relatorioEventoDAO.relatorioEventoPorIdLista(idLista);
		
		List<RelatorioBVS> relatorioBVS = relApoio.relatorioBVS(idLista);
		
	 	List<CacheEvento> relatorio = relatorioEventoDAO.listaCacheEventoPorEvento(relatorioEventos.getIdRelatorioEvento());
		
	 	
	 	InfoInterna infoInterna = relatorioEventoDAO.infoInterna(idLista);
	 	
	    MV.addObject("relatorio",relatorioEventos);
		MV.addObject("relatorioCaches", relatorio);
		MV.addObject("relBVS",relatorioBVS);
		MV.addObject("infoLista", infoLista);

		MV.addObject("infoInterna", infoInterna);

		MV.addObject("depesasEventos", relatorioEventoDAO.somaDespesasProjeto(idLista));
		
		
		
		
		MV.addObject("usuarios", usuarios.cachePadraoNomes(relatorioEventos.getIdRelatorioEvento()));
		
		
		int ano =  Integer.parseInt(relatorioEventos.getAnoEvento());
		int mes = relatorioEventos.getMesReferencia();
		
		MV.addObject("quantRelatorioEventos", analiticoIndDAO.quantEventosMes(ano, mes));
		MV.addObject("idsRelatorioEventos", analiticoIndDAO.idsRelatorioEventosMes(ano, mes));

		MV.addObject("mes", mes);
		MV.addObject("ano", ano);
		MV.addObject("idAnalitico", analiticoIndDAO.retornaIdAnaliticoPorMesAnoEvento(mes, ano));
		
		
		
		
		
		return MV;
	}
	
	@RequestMapping("atualizaCacheRelatorioEvento")
	@ResponseBody
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
		 
		 return "ok";
	}
	
	@RequestMapping("salvaNovoCache")
	@ResponseBody
	public String salvaNovoCache(int idRelatorioEvento, int idNovoUsuario, double novaPorcentagemCaches, int idLista) throws ParseException{
		
		relatorioDAO.salvaNovoCacheNoRelatorio(idRelatorioEvento, idNovoUsuario, novaPorcentagemCaches, idLista);
		
		
		 ArrayList<String> datas =  relatorioDAO.dataRelatoriosEventosCadastrados(idLista);
		 String mes = datas.get(1).toUpperCase().toString();
		 String ano = datas.get(2).toUpperCase().toString();
	
		 /**
		  * 
		  * Método herdado que Faz a atualização em massa dos relatórios de eventos passando os ids das listas
		  */
		 atualizaInternaRelatoriosEventosEmMassa(relatorioDAO.idsListaRelatoriosEventosPorMesAno(mes, ano));
		
		return "ok";
	}
	
	
	
}
