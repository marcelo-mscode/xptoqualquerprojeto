package br.com.sysloccOficial.financeiro;

import java.math.BigDecimal;

public class CalculadoraValorFornecedor implements CalculoFornecedorInterna {

	@Override
	public BigDecimal calculaValorFornecedor() {
		BigDecimal valorFornecedorCalculado = new BigDecimal("500");
		return valorFornecedorCalculado;
	}

	@Override
	public BigDecimal calculaDiferenca() {
		BigDecimal diferencaCalculada = new BigDecimal("500");
		return diferencaCalculada;
	}

}
