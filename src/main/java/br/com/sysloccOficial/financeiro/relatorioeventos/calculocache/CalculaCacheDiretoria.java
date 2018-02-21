package br.com.sysloccOficial.financeiro.relatorioeventos.calculocache;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CachePadrao;

public class CalculaCacheDiretoria implements CalculoCache {

	@Override
	public BigDecimal calculoCacheDiretoria(List<CachePadrao> listaRelatorioCaches,BigDecimal totalDiferencaComTelefone,TipoCache tipoCache) {

		CalculoCache calculacacheEquipeInterna = new CalculaCacheEquipeInterna();
		
		BigDecimal totalCache = new BigDecimal("0");
		BigDecimal totalCacheFuncionarios = calculacacheEquipeInterna.calculoCache(listaRelatorioCaches, totalDiferencaComTelefone);
		BigDecimal totalCacheCalculo = totalDiferencaComTelefone.subtract(totalCacheFuncionarios);
		
		for (int i = 0; i < listaRelatorioCaches.size(); i++) {
			if(listaRelatorioCaches.get(i).getTipoCache() == tipoCache){
				totalCache = totalCache.add(totalCacheCalculo.multiply(
						listaRelatorioCaches.get(i).getRazaoPorcentagem()));
			}
			
		}
		return totalCache;
	}

	@Override
	public BigDecimal calculoCache(List<CachePadrao> listaRelatorioCaches,
			BigDecimal totalDiferencaComTelefone) {
		// TODO Auto-generated method stub
		return null;
	}

}
