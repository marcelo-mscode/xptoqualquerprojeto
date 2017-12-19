package br.com.sysloccOficial.financeiro.funcionario.cacheCalculos;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.model.CachePadrao;

public interface CacheFuncionarios {
	
	public BigDecimal calculaCacheSemtelefone(List<CachePadrao> relatorio,BigDecimal totalDiferencaSemTelefone);
	
}
