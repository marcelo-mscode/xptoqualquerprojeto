package br.com.sysloccOficial.financeiro.relatorioeventos.salvaatualizacache;

import java.util.List;

import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;

public interface SalvaAtualizaCache {
	public void salva(RelatorioEventos relatorioEvento, List<CachePadrao> cachePadrao);
	public void atualiza(RelatorioEventos relatorioEvento);
}
