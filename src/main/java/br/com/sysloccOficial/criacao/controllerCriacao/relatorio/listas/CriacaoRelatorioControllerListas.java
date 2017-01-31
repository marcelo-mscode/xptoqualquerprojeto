package br.com.sysloccOficial.criacao.controllerCriacao.relatorio.listas;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.criacao.controllerCriacao.dao.RelatorioDAO;
import br.com.sysloccOficial.criacao.controllerCriacao.relatorio.apoio.ApoioRelatorio;
import br.com.sysloccOficial.model.CriacaoLista;

@Controller
public class CriacaoRelatorioControllerListas extends ApoioRelatorio{

	
	@Autowired private RelatorioDAO relatorioDAO;
	@Autowired private UtilitariaDatas utilData;
	@Autowired private ApoioRelatorio apoioRelatorio;
	ModelAndView MV = new ModelAndView();
	
	
	@RequestMapping("porLista")
//	@Cacheable(value="listaCriacaoCriacao")
	public ModelAndView porLista() throws ParseException{
		MV.setViewName("criacao/relatorio/porLista/porLista");
	
		
//		List<String> listaAno = relatorioDAO.dataCriacaoLista();
//		List<String[]> mes = selecionaMesPorCriacaoLista("dataCriacao");
//		List<CriacaoLista> listas = relatorioDAO.ultimasListasFechadas();

		MV.addObject("empresa",relatorioDAO.idEmpresas());
		
		List<CriacaoLista> listas = relatorioDAO.ultimasListasFechadas();
		
	    MV.addObject("totalHorasListas", apoioRelatorio.somaTempoItensCadaLista(listas));
		
		MV.addObject("dataCriacao",relatorioDAO.dataCriacaoLista());
		MV.addObject("mesCriacao",selecionaMesPorCriacaoLista("dataCriacao"));
		MV.addObject("lista",relatorioDAO.ultimasListasFechadas());
	

		return MV;
	}

	@RequestMapping("porListaIndividual")
	public ModelAndView porListaIndividual(Integer idCriacaoLista){
		
		MV.setViewName("criacao/relatorio/porLista/listaIndividual");
		
		MV.addObject("itens", relatorioDAO.itensListaProIdCriacao(idCriacaoLista));
		
		MV.addObject("totalHorasItens", apoioRelatorio.somaTempoVariosItens(idCriacaoLista));
		
		CriacaoLista lista = relatorioDAO.lista(idCriacaoLista);
		
		if(lista.getTempoTotal() == null){
			MV.addObject("totalHorasListas", apoioRelatorio.somaTempoUmaLista(lista.getIdCriacaoLista()));
		}
		
		return MV;
	}
	
	@RequestMapping("filtraListaCriacao")
	public ModelAndView filtraListaCriacao(Integer t, Integer s, Integer r) throws ParseException{
		
		// t = idEmpresa
		// s = 
		
		String data="";
		if(s < 10){
			data = r+"-0"+s;
		}else{
			data = r+"-"+s;
		}
		
		List<CriacaoLista> lista= relatorioDAO.exibeCriacaoListasFechadasPorEmpresa(t, data);
		
		MV.setViewName("criacao/relatorio/porLista/listaAjax");
		
		MV.addObject("lista",lista);
		
		MV.addObject("totalHorasListas", apoioRelatorio.somaTempoItensCadaLista(lista));
		
		return MV;
		
	}
}
