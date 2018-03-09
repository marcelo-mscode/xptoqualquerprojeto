package br.com.sysloccOficial.financeiro.relatorioeventos.salvaatualizacache;

import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;

public interface SalvaAtualizaCache {
	public CacheEvento salva(RelatorioEventos relatorioEvento, CachePadrao cachePadrao);
	public CacheEvento atualiza(RelatorioEventos relatorioEvento, CacheEvento cacheParaAtualizar);
}
