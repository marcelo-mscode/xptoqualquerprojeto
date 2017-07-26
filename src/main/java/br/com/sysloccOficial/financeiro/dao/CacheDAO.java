package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.CachePadrao;

@Repository
@Transactional
public class CacheDAO {

	@PersistenceContext	private EntityManager manager;
	@Autowired RelatorioEventoDAO relatorioDAO;
	@Autowired Utilitaria util;
	
	
	public List<CachePadrao> listaTodosCaches(){
		TypedQuery<CachePadrao> q = manager.createQuery("from CachePadrao where habilitado = true order by tipoCache",CachePadrao.class);
		return q.getResultList();
	}
	
	public void salvaNovoCache(CachePadrao novoCache){
		String porc = novoCache.getPorcentagem().replace(",",".");
		novoCache.setHabilitado(true);
		novoCache.setPorcentagem(porc);
		novoCache.setRazaoPorcentagem(new BigDecimal(novoCache.getPorcentagem()).divide(new BigDecimal("100"),12,RoundingMode.UP));
		manager.persist(novoCache);
	}
	
	public void editaCache(String valor,String porcentagem, Integer idCachePadrao){
		CachePadrao cache = manager.find(CachePadrao.class, idCachePadrao);
		cache.setNomeFunc(valor);
		cache.setPorcentagem(porcentagem);
		cache.setRazaoPorcentagem(new BigDecimal(cache.getPorcentagem()).divide(new BigDecimal("100"),12,RoundingMode.UP));
		manager.merge(cache);
	}

	public void deletaCache(Integer idCachePadrado) {
		CachePadrao cache =  manager.find(CachePadrao.class, idCachePadrado);
		cache.setHabilitado(false);
		manager.merge(cache);
	}
	
	
	public void listaCachesPorMesAno(){
		
		
		List<Integer> idsRelatorios =  relatorioDAO.idsRelatoriosEventosPorMesAno(01, 2017);

//		String consultaCache =  util.limpaSqlComList("SELECT distinct(cachePadrao) FROM CacheEvento where relatorioEvento in ("+idsRelatorios+")");
		String consultaCache =  util.limpaSqlComList("SELECT distinct(cachePadrao) FROM CacheEvento where relatorioEvento in ("+idsRelatorios+")");
		
		Set<E>
		
		// CACHES EVENTO POR IDSRELATORIOS ORDERNADO POR CACHE PADRAO
		
		
		
		System.out.println();
		
	}
	
	
	
	
}
