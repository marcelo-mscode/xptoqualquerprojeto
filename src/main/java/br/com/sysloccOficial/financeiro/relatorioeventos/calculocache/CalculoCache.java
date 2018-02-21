package br.com.sysloccOficial.financeiro.relatorioeventos.calculocache;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CachePadrao;

public interface CalculoCache {
	public BigDecimal calculoCache(List<CachePadrao> listaRelatorioCaches, BigDecimal totalDiferencaComTelefone);
	public BigDecimal calculoCacheDiretoria(List<CachePadrao> listaRelatorioCaches,BigDecimal totalDiferencaComTelefone,TipoCache tipoCache);
}
