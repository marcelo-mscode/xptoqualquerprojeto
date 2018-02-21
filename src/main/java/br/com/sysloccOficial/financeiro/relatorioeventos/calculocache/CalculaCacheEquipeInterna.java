package br.com.sysloccOficial.financeiro.relatorioeventos.calculocache;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CachePadrao;

public class CalculaCacheEquipeInterna implements CalculoCache {

	@Override
	public BigDecimal calculoCache(List<CachePadrao> listaRelatorioCaches,BigDecimal totalDiferencaComTelefone) {
		BigDecimal totalCache = new BigDecimal("0");
		
		for (int i = 0; i < listaRelatorioCaches.size(); i++) {
			if(listaRelatorioCaches.get(i).getTipoCache() == TipoCache.FUNCIONARIO){
				totalCache = totalCache.add(totalDiferencaComTelefone.multiply(
						listaRelatorioCaches.get(i).getRazaoPorcentagem()));
			}
			
		}
		return totalCache;	}

	@Override
	public BigDecimal calculoCacheDiretoria(
			List<CachePadrao> listaRelatorioCaches,
			BigDecimal totalDiferencaComTelefone, TipoCache tipoCache) {
		// TODO Auto-generated method stub
		return null;
	}
}
