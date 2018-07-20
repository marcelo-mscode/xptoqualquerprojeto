package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.financeiro.relatorioeventos.CacheDoEventoApoio;
import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;

@Repository
@Transactional
public class RelatorioEventoSalvaCacheNDDAO {
	
		@PersistenceContext	private EntityManager manager;
		@Autowired CacheDoEventoApoio cacheEvento;
	
	
public void salvaCacheDoEventoND(RelatorioEventos relatorioEvento){
		
		
		TypedQuery<CacheEvento> cacheTeste = manager.createQuery("SELECT c FROM CacheEvento c WHERE relatorioEvento="+relatorioEvento.getIdRelatorioEvento(), CacheEvento.class);
		List<CacheEvento> caches = cacheTeste.getResultList();
		
		if(caches.isEmpty() || caches.equals(null)){
			
		}else{
			manager.createQuery("DELETE FROM CacheEvento WHERE relatorioEvento="+relatorioEvento.getIdRelatorioEvento()).executeUpdate();
			manager.clear();
		}
		
		List<CachePadrao> cachePadrao =  cacheEvento.listaRelatorioCachesPorND(relatorioEvento.getIdLista());
		
		BigDecimal valorParaDiretoria = relatorioEvento.getTotalDiferenca().subtract(relatorioEvento.getTotalCachesIntExt());
		
		try {
			for (int i = 0; i < cachePadrao.size(); i++) {
				CacheEvento novoCacheEvento = new CacheEvento();
				if(cachePadrao.get(i).getTipoCache().equals(TipoCache.FUNCIONARIO)){
					
					BigDecimal valor = new BigDecimal("0.000");
					if(valorParaDiretoria.equals(new BigDecimal("0.000"))){
						novoCacheEvento.setValor(valor);
					}else{
						novoCacheEvento.setValor(relatorioEvento.getTotalDiferenca().multiply((cachePadrao.get(i).getRazaoPorcentagem())));
					}
					
					novoCacheEvento.setRazaoPorcentagem(cachePadrao.get(i).getRazaoPorcentagem());
					novoCacheEvento.setValor(relatorioEvento.getTotalDiferenca().multiply((cachePadrao.get(i).getRazaoPorcentagem())));
					novoCacheEvento.setRelatorioEvento(relatorioEvento);
					novoCacheEvento.setCachePadrao(cachePadrao.get(i));
					manager.merge(novoCacheEvento);
					manager.close();
				}
				
				if(cachePadrao.get(i).getTipoCache().equals(TipoCache.DIRETORIA1) || cachePadrao.get(i).getTipoCache().equals(TipoCache.DIRETORIA2)){
					BigDecimal valor = new BigDecimal("0.000");
					if(valorParaDiretoria.equals(new BigDecimal("0.000"))){
						novoCacheEvento.setValor(valor);
					}else{
						novoCacheEvento.setValor(valorParaDiretoria.multiply((cachePadrao.get(i).getRazaoPorcentagem())));
					}
					novoCacheEvento.setRazaoPorcentagem(cachePadrao.get(i).getRazaoPorcentagem());
					novoCacheEvento.setRelatorioEvento(relatorioEvento);
					novoCacheEvento.setCachePadrao(cachePadrao.get(i));
					//	System.out.println(cachePadrao.get(i).getNomeFunc()+"- valor:"+novoCacheEvento.getValor());
					
					manager.merge(novoCacheEvento);
					manager.close();
				}
			}
			
		} catch (Exception e) {
			System.out.println("Deu um erro: "+e);
		}
		
	
	}
	
	
	
	

}
