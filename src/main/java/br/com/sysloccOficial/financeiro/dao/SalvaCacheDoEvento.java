package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.relatorioeventos.CacheDoEventoApoio;
import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;



@Repository
@Transactional
public class SalvaCacheDoEvento {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired CacheDoEventoApoio cacheEvento;
	@Autowired UtilitariaDatas utildatas;
	
	
	public void salvaCacheDoEvento(RelatorioEventos relatorioEvento){
		
		// ----------- Porque estou deletando a tabela e criando outra ?
		
		TypedQuery<CacheEvento> cacheTeste = manager.createQuery("SELECT c FROM CacheEvento c WHERE relatorioEvento="+relatorioEvento.getIdRelatorioEvento(), CacheEvento.class);
		
		List<CacheEvento> caches = cacheTeste.getResultList();
		
		
		manager.createQuery("DELETE FROM CacheEvento WHERE relatorioEvento="+relatorioEvento.getIdRelatorioEvento()).executeUpdate();
		
		List<CachePadrao> cachePadrao =  cacheEvento.listaRelatorioCaches(relatorioEvento.getIdLista());
		
		BigDecimal valorParaDiretoria = relatorioEvento.getTotalDiferenca().subtract(relatorioEvento.getTotalCachesIntExt());

		
		for (int i = 0; i < cachePadrao.size(); i++) {
				CacheEvento novoCacheEvento = new CacheEvento();
				if(cachePadrao.get(i).getTipoCache().equals(TipoCache.FUNCIONARIO)){
					novoCacheEvento.setRazaoPorcentagem(cachePadrao.get(i).getRazaoPorcentagem());
					novoCacheEvento.setValor(relatorioEvento.getTotalDiferenca().multiply((cachePadrao.get(i).getRazaoPorcentagem())));
					novoCacheEvento.setRelatorioEvento(relatorioEvento);
					novoCacheEvento.setCachePadrao(cachePadrao.get(i));
					manager.merge(novoCacheEvento);
				}
				
				if(cachePadrao.get(i).getTipoCache().equals(TipoCache.DIRETORIA1) || cachePadrao.get(i).getTipoCache().equals(TipoCache.DIRETORIA2)){
					novoCacheEvento.setRazaoPorcentagem(cachePadrao.get(i).getRazaoPorcentagem());
					novoCacheEvento.setValor(valorParaDiretoria.multiply((cachePadrao.get(i).getRazaoPorcentagem())));
					novoCacheEvento.setRelatorioEvento(relatorioEvento);
					novoCacheEvento.setCachePadrao(cachePadrao.get(i));
				//	System.out.println(cachePadrao.get(i).getNomeFunc()+"- valor:"+novoCacheEvento.getValor());
					
					manager.merge(novoCacheEvento);
				}
		}
	
	}
	
	

}
