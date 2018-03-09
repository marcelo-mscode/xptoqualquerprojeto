package br.com.sysloccOficial.financeiro.relatorioeventos.salvaatualizacache;

import br.com.sysloccOficial.model.RelatorioEventos;

public class MontaCacheEvento {
	
	
	public static void salvaCache(RelatorioEventos relatorioEvento){
		SalvaAtualizaCache salva = new SalvaCache();
		salva.salva(relatorioEvento);
		
	}

	public static void atualizaCache(RelatorioEventos relatorioEvento){

		SalvaAtualizaCache atualiza = new AtualizaCache();
		atualiza.salva(relatorioEvento);
		
	}
	
}
