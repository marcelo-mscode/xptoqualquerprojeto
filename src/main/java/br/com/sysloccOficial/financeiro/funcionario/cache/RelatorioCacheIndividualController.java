package br.com.sysloccOficial.financeiro.funcionario.cache;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.CacheDAO;
import br.com.sysloccOficial.model.CachePadrao;


@Controller
@Transactional
public class RelatorioCacheIndividualController {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired CacheDAO cacheDAO;
	
	@RequestMapping("relatorioCacheIndividual")
	public ModelAndView relatorioCacheIndividual(){
		
		ModelAndView MV = new ModelAndView("financeiro/cache/relatorioIndividualCache");
	/*	List<CachePadrao> lista = cacheDAO.listaTodosCaches();
		MV.addObject("caches", lista);*/
		return MV;
	}
	
	/*@RequestMapping("cadastraNovoCache")
	public String cadastraNovoCache(CachePadrao novoCache){
		cacheDAO.salvaNovoCache(novoCache);
	return "redirect:cachePadrao";	
	}
	
	@ResponseBody
	@RequestMapping("editaCacheNome")
	public String editaCacheNome(String valor,String porcentagem,String tipo,Integer idCachePadrao){
		cacheDAO.editaCache(valor,porcentagem,idCachePadrao);
		return "sucesso";	
	}
	
	@RequestMapping("deletaCache")
	public String deletaCache(Integer idCachePadrao){
		cacheDAO.deletaCache(idCachePadrao);
		return "redirect:cachePadrao";	
	}
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
