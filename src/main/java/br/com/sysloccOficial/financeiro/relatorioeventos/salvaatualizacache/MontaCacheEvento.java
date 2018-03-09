package br.com.sysloccOficial.financeiro.relatorioeventos.salvaatualizacache;

import java.util.List;

import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;

public class MontaCacheEvento {
	
	
	public static CachePadrao salvaCache(RelatorioEventos relatorioEvento, CachePadrao listaCachePadrao){
		SalvaAtualizaCache salva = new SalvaCache();

		return salva.salva(relatorioEvento,listaCachePadrao);
		
	}

	public static void atualizaCache(RelatorioEventos relatorioEvento){

		SalvaAtualizaCache atualiza = new AtualizaCache();
		atualiza.atualiza(relatorioEvento);
		
	}
	
}
