package br.com.sysloccOficial.financeiro.relatorioeventos.salvaatualizacache;

import java.math.BigDecimal;
import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;

public class SalvaCache implements SalvaAtualizaCache {

	@Override
	public CacheEvento salva(RelatorioEventos relatorioEvento, CachePadrao cachePadrao) {
		
		BigDecimal valorParaDiretoria = relatorioEvento.getTotalDiferenca().subtract(relatorioEvento.getTotalCachesIntExt());
		
		CacheEvento novoCacheEvento = new CacheEvento();
		
		if(cachePadrao.getTipoCache().equals(TipoCache.FUNCIONARIO)){
			novoCacheEvento.setRazaoPorcentagem(cachePadrao.getRazaoPorcentagem());
			novoCacheEvento.setValor(relatorioEvento.getTotalDiferenca().multiply((cachePadrao.getRazaoPorcentagem())));
			novoCacheEvento.setRelatorioEvento(relatorioEvento);
			novoCacheEvento.setCachePadrao(cachePadrao);
		}
		
		if(cachePadrao.getTipoCache().equals(TipoCache.DIRETORIA1) || cachePadrao.getTipoCache().equals(TipoCache.DIRETORIA2)){
			novoCacheEvento.setRazaoPorcentagem(cachePadrao.getRazaoPorcentagem());
			novoCacheEvento.setValor(valorParaDiretoria.multiply((cachePadrao.getRazaoPorcentagem())));
			novoCacheEvento.setRelatorioEvento(relatorioEvento);
			novoCacheEvento.setCachePadrao(cachePadrao);
        }
		
		return novoCacheEvento;
	
	}

	@Override
	public CacheEvento atualiza(RelatorioEventos relatorioEvento, CacheEvento cacheParaAtualizar) {
		// TODO Auto-generated method stub
		return null;
	}

}
