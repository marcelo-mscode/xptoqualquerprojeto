package br.com.sysloccOficial.financeiro.relatorioeventos.salvaatualizacache;

import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;

public class AtualizaCache implements SalvaAtualizaCache {

	@Override
	public CacheEvento atualiza(RelatorioEventos relatorioEvento, CacheEvento cacheParaAtualizar) {
		cacheParaAtualizar.setValor(relatorioEvento.getTotalDiferenca().multiply((cacheParaAtualizar.getRazaoPorcentagem())));
		return cacheParaAtualizar;
		
	}

	@Override
	public CacheEvento salva(RelatorioEventos relatorioEvento, CachePadrao cachePadrao) {
		// TODO Auto-generated method stub
		return null;
	}

}
