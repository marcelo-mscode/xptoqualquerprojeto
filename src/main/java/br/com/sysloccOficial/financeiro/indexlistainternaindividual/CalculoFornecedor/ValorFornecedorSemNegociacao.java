package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class ValorFornecedorSemNegociacao implements CalculoFornecedorInterna {

	@Override
	public ProducaoP calculaValorFornecedor(ProducaoP producaoP) {
		return null;
	}

	@Override
	public BigDecimal calculaDiferenca(ProducaoP producaoP) {
		BigDecimal diferencaCalculada = new BigDecimal("500");
		return diferencaCalculada;
	}

	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {

	}

}
