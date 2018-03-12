package br.com.sysloccOficial.financeiro.relatorioeventos.salvaatualizacache;

import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;

public class MontaCacheEvento {
	
	public static CacheEvento salvaCache(RelatorioEventos relatorioEvento, CachePadrao listaCachePadrao){
		SalvaAtualizaCache salva = new SalvaCache();
		return salva.salva(relatorioEvento,listaCachePadrao);
	}

	public static CacheEvento atualizaCache(RelatorioEventos relatorioEvento, CacheEvento listaCachesDoEventoParaAtualizar){

		SalvaAtualizaCache atualiza = new AtualizaCache();
		return atualiza.atualiza(relatorioEvento, listaCachesDoEventoParaAtualizar);
		
	}
	
}
