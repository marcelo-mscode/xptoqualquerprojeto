package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class FornecedorTemNegociacaoComContratacaoIgualQUeValorItem implements
		CalculoFornecedorInterna {

	private CalculoFornecedorInterna proximo;
	
	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {
		
		int verifica = producaoP.getValorContratacao().compareTo(producaoP.getValorItem());
		
		if(verifica == 0){
			
			BigDecimal valorFinal = producaoP.getValorItem();
			
			producaoP.setValorFornecedor(valorFinal);
			
			return valorFinal;
			
		}else{
			return proximo.calculaValorFornecedor(producaoP);
		}
	}

	@Override
	public BigDecimal calculaDiferenca(ProducaoP producaoP) {
		BigDecimal diferencaCalculada = new BigDecimal("0.00");
		
		return diferencaCalculada;
	}

	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {
		this.proximo = proximo;
	}

}
