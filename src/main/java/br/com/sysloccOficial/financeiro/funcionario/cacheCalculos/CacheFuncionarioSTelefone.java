package br.com.sysloccOficial.financeiro.funcionario.cacheCalculos;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CachePadrao;

public class CacheFuncionarioSTelefone implements CacheFuncionarios {

	@Override
	public BigDecimal calculaCacheSemtelefone(List<CachePadrao> relatorio,BigDecimal totalDif) {
		BigDecimal totalCachesFunc = new BigDecimal("0");
		
		for (int i = 0; i < relatorio.size(); i++) {
			if(relatorio.get(i).getTipoCache() == TipoCache.FUNCIONARIO){
				totalCachesFunc = totalCachesFunc.add(totalDif
						.multiply(relatorio.get(i).getRazaoPorcentagem()));
			}
		}
		return totalCachesFunc;
	}

}
