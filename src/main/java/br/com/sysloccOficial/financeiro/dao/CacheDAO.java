package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.CachePadraoAnalitico;

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
	
	
	public List<CachePadraoAnalitico> listaCachesPorMesAno(){
		
		List<Integer> idsRelatorios =  relatorioDAO.idsRelatoriosEventosPorMesAno(01, 2017);

		String consultaCache =  util.limpaSqlComList("FROM CacheEvento where relatorioEvento in ("+idsRelatorios+")");
		
		TypedQuery<CacheEvento> caches = manager.createQuery(consultaCache, CacheEvento.class);
		List<CacheEvento> listaCaches = caches.getResultList();
		
		
		// CACHES EVENTO POR IDSRELATORIOS ORDERNADO POR CACHE PADRAO
		String consultaIdsCachePadrao =  util.limpaSqlComList("SELECT distinct(cachePadrao.idCachePadrao) FROM CacheEvento where relatorioEvento in ("+idsRelatorios+")");
		TypedQuery<Integer> ids = manager.createQuery(consultaIdsCachePadrao, Integer.class);
		List<Integer> idsFuncionarioCache = ids.getResultList();
		
		
		List<CachePadraoAnalitico> listaDeCachesMontada = new ArrayList<CachePadraoAnalitico>();
		
		for (int i = 0; i < idsFuncionarioCache.size(); i++) {
			
			CachePadraoAnalitico padrao = new CachePadraoAnalitico();
			BigDecimal valor = new BigDecimal("0");
			
			for (int j = 0; j < listaCaches.size(); j++) {
				if(idsFuncionarioCache.get(i) ==  listaCaches.get(j).getCachePadrao().getIdCachePadrao()){
					valor = valor.add(listaCaches.get(j).getValor());
					padrao.setNomeFuncionario(listaCaches.get(j).getCachePadrao().getNomeFunc());
				}
			}
			padrao.setIdCacheFuncionario(idsFuncionarioCache.get(i));
			padrao.setValor(valor);
			listaDeCachesMontada.add(padrao);
		}
		return listaDeCachesMontada;
	}
	
	
	
	
}
