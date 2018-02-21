package br.com.sysloccOficial.financeiro.relatorioeventos.calculocache;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CachePadrao;

public class CalculadoraCaches {
	
	public static BigDecimal calculaCacheDiretoria(List<CachePadrao> listaRelatorioCaches,BigDecimal totalDiferencaComTelefone,TipoCache tipoCache){
		CalculoCache calculaCacheDiret = new CalculaCacheDiretoria();
		return  calculaCacheDiret.calculoCacheDiretoria(listaRelatorioCaches, totalDiferencaComTelefone, tipoCache);
	}
	
	public static BigDecimal calculaCacheEquipeInterna(List<CachePadrao> listaRelatorioCaches, BigDecimal totalDiferencaComTelefone){
		CalculoCache calculaCacheDiret = new CalculaCacheEquipeInterna();
		return calculaCacheDiret.calculoCache(listaRelatorioCaches, totalDiferencaComTelefone);
	}
	
}
