package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class ValorFornecedorSemNegociacao implements CalculoFornecedorInterna {

	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {
		
		BigDecimal valorFinal = producaoP.getValorItem();
		
		producaoP.setValorFornecedor(valorFinal);
		
		
		if(producaoP.getIdProdutoGrupo() == 98723){
			System.out.println("Valor do Fornecedor sem negociação: "+producaoP.getValorFornecedor());
		}
		
		
		return valorFinal;
	}

	@Override
	public BigDecimal calculaDiferenca(ProducaoP producaoP) {
		
		BigDecimal diferencaCalculada = new BigDecimal("0.00");
		
		return diferencaCalculada;
	}

	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {

	}

}
