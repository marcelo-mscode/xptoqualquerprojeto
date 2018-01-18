package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class ValorFornecedorSemNegociacao implements CalculoFornecedorInterna {

	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {
		
		BigDecimal valorFinal = producaoP.getValorItem();
		
		producaoP.setValorFornecedor(valorFinal);
		
		return null;
	}

	@Override
	public BigDecimal calculaDiferenca(ProducaoP producaoP) {
		
		BigDecimal diferencaCalculada = new BigDecimal("50.00");
		
		return diferencaCalculada;
	}

	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {

	}

}
