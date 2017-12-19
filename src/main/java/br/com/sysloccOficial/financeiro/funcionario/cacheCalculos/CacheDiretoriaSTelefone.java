package br.com.sysloccOficial.financeiro.funcionario.cacheCalculos;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CachePadrao;

public class CacheDiretoriaSTelefone implements CacheFuncionarios {

	@Override
	public BigDecimal calculaCacheSemtelefone(List<CachePadrao> relatorio,BigDecimal totalDiferencaSTelefone) {
		CacheFuncionarios totalCacheFuncionarios = new CacheFuncionarioSTelefone();
		BigDecimal totalCalculoParaDiretoria = totalDiferencaSTelefone.subtract(totalCacheFuncionarios.calculaCacheSemtelefone(relatorio, totalDiferencaSTelefone));
		BigDecimal totalCachesDiretoria = new BigDecimal("0");
		for (int i = 0; i < relatorio.size(); i++) {
			if(relatorio.get(i).getTipoCache() == TipoCache.DIRETORIA1 || relatorio.get(i).getTipoCache() == TipoCache.DIRETORIA2){
				totalCachesDiretoria = totalCachesDiretoria.add(totalCalculoParaDiretoria
						.multiply( new BigDecimal(relatorio.get(i).getPorcentagem())
						.divide(   new BigDecimal("100"))));
			}
		}
		return totalCachesDiretoria;
	}
}
