package br.com.sysloccOficial.financeiro.relatorioeventos.totalPagarFornecedores;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioBVS;

public interface TotalPagarFornecedores {
	
	public BigDecimal totalAPagarFornecedores(List<RelatorioBVS> relatorioBVS);

}
