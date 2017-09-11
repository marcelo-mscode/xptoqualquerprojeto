package br.com.sysloccOficial.financeiro.atualizaInterna;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CachePadrao;

public class CalculoCacheDiretoria {

	public CalculoCacheDiretoria() {
		super();
	}

	public BigDecimal calculacacheDiretoria(List<CachePadrao> listaRelatorioCaches, BigDecimal totalDiferencaComTelefone, TipoCache tipoCache) {
		BigDecimal totalCache = new BigDecimal("0");
		BigDecimal totalCacheFuncionarios = calculacacheEquipeInterna(listaRelatorioCaches, totalDiferencaComTelefone);
		BigDecimal totalCacheCalculo = totalDiferencaComTelefone.subtract(totalCacheFuncionarios);
		
		for (int i = 0; i < listaRelatorioCaches.size(); i++) {
			if(listaRelatorioCaches.get(i).getTipoCache() == tipoCache){
				totalCache = totalCache.add(totalCacheCalculo.multiply(
						listaRelatorioCaches.get(i).getRazaoPorcentagem()));
			}
			
		}
		return totalCache;
	}
	
	
	public BigDecimal calculacacheEquipeInterna(List<CachePadrao> listaRelatorioCaches, BigDecimal totalDiferencaComTelefone){
		BigDecimal totalCache = new BigDecimal("0");
		
		for (int i = 0; i < listaRelatorioCaches.size(); i++) {
			if(listaRelatorioCaches.get(i).getTipoCache() == TipoCache.FUNCIONARIO){
				totalCache = totalCache.add(totalDiferencaComTelefone.multiply(
						listaRelatorioCaches.get(i).getRazaoPorcentagem()));
			}
			
		}
		return totalCache;
	}

}