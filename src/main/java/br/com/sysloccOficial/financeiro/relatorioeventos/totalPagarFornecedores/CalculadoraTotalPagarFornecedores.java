package br.com.sysloccOficial.financeiro.relatorioeventos.totalPagarFornecedores;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioBVS;

public class CalculadoraTotalPagarFornecedores {
	
	public static BigDecimal calculaTotal(List<RelatorioBVS> relatorioBVS){
		TotalPagarFornecedores total = new CalculaTotalFornecedores();
		return total.totalAPagarFornecedores(relatorioBVS);
	}
}
