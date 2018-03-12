package br.com.sysloccOficial.financeiro.relatorioeventos.salvaatualizacache;

import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;

public class AtualizaCache implements SalvaAtualizaCache {

	@Override
	public CacheEvento atualiza(RelatorioEventos relatorioEvento, CacheEvento cacheParaAtualizar) {
		
		
		cacheParaAtualizar.setValor(relatorioEvento.getTotalDiferenca().multiply((cacheParaAtualizar.getRazaoPorcentagem())));
		
		
		if(cacheParaAtualizar.getTipoCache().equals(TipoCache.FUNCIONARIO)){
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
		
		
		return cacheParaAtualizar;
		
	}

	@Override
	public CacheEvento salva(RelatorioEventos relatorioEvento, CachePadrao cachePadrao) {
		// TODO Auto-generated method stub
		return null;
	}

}
