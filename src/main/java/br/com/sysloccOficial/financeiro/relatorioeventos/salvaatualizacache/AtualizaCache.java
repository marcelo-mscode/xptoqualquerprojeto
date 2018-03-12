package br.com.sysloccOficial.financeiro.relatorioeventos.salvaatualizacache;

import java.math.BigDecimal;

import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;

public class AtualizaCache implements SalvaAtualizaCache {

	@Override
	public CacheEvento atualiza(RelatorioEventos relatorioEvento, CacheEvento cacheParaAtualizar) {
		
		BigDecimal valorParaDiretoria = relatorioEvento.getTotalDiferenca().subtract(relatorioEvento.getTotalCachesIntExt());
		
		if(cacheParaAtualizar.getCachePadrao().getTipoCache().equals(TipoCache.FUNCIONARIO)){
			cacheParaAtualizar.setValor(relatorioEvento.getTotalDiferenca().multiply((cacheParaAtualizar.getRazaoPorcentagem())));
		}
		
		if(cacheParaAtualizar.getCachePadrao().getTipoCache().equals(TipoCache.DIRETORIA1) || cacheParaAtualizar.getCachePadrao().getTipoCache().equals(TipoCache.DIRETORIA2)){
			cacheParaAtualizar.setValor(valorParaDiretoria.multiply((cacheParaAtualizar.getRazaoPorcentagem())));
        }
		return cacheParaAtualizar;
		
	}

	@Override
	public CacheEvento salva(RelatorioEventos relatorioEvento, CachePadrao cachePadrao) {
		// TODO Auto-generated method stub
		return null;
	}

}
