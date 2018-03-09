package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.sysloccOficial.financeiro.relatorioeventos.salvaatualizacache.MontaCacheEvento;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;

@Component
@Transactional
public class CacheDoEventoApoio {
	
	
	@PersistenceContext	private EntityManager manager;
	
	
	public List<CachePadrao> listaRelatorioCaches(Integer idLista){
		
		try {
			// Verificar se tem relatorio evento
			Integer id = verificaSeTemRelatorioEventoPorIdLista(idLista);
			
			// Se tiver o relatorio 
			if(id != null){

				List<CacheEvento> listaCaches = pegaCacheExistenteDoRelatorio(id);
				// Verificar se tem cacheEvento desse relatorio
					
					// Se não tiver
					// Pega Cache Padrão
					if(listaCaches.isEmpty()){
						
						return pegaCachePadrao();
						
					// Se tiver
					// Se tiver pega caches do evento	
					}else{
						
						return preencheListaCacheComCacheRelatorioEventoExistente(listaCaches);
					
					}
				
			}else{

				return pegaCachePadrao();
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao pegar cache Evento:" + e);
			return null;
		}
	}
	
	private Integer verificaSeTemRelatorioEventoPorIdLista(Integer idLista) {
		
		try {
			String idRelatorioEvento = "select idRelatorioEvento from RelatorioEventos where idLista = " +idLista;
			TypedQuery<Integer> query = manager.createQuery(idRelatorioEvento,Integer.class);
			Integer id = query.getSingleResult();
			return id;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	private List<CacheEvento> pegaCacheExistenteDoRelatorio(Integer id) {
		TypedQuery<CacheEvento> cacheEvento = manager.createQuery("from CacheEvento where relatorioEvento = "+ id, CacheEvento.class);
		List<CacheEvento> listaCaches = cacheEvento.getResultList();
		return listaCaches;
	}
	
	private List<CachePadrao> preencheListaCacheComCacheRelatorioEventoExistente(List<CacheEvento> listaCaches) {
		List<CachePadrao> cachePadrao = new ArrayList<CachePadrao>();
		
		for (int i = 0; i < listaCaches.size(); i++) {
			
			CachePadrao transfCache = new CachePadrao();
			
			System.out.println(listaCaches.get(i).getCachePadrao().getNomeFunc());
			
			
			transfCache.setHabilitado(true);
			transfCache.setNomeFunc(listaCaches.get(i).getCachePadrao().getNomeFunc());
			transfCache.setRazaoPorcentagem(listaCaches.get(i).getRazaoPorcentagem());
			transfCache.setTipoCache(listaCaches.get(i).getCachePadrao().getTipoCache());
			
			String porcentagem = listaCaches.get(i).getRazaoPorcentagem().multiply(new BigDecimal("100")).toString();
			
			transfCache.setPorcentagem(porcentagem);
			
			cachePadrao.add(transfCache);
			
		}
		
		return cachePadrao;
	}
	
	private List<CachePadrao> pegaCachePadrao() {
		String consulta = "from CachePadrao where habilitado = 1 order by idCachePadrao";
		TypedQuery<CachePadrao> q = manager.createQuery(consulta,CachePadrao.class);

		return q.getResultList();
	}

}
