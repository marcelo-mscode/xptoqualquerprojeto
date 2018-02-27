package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class FornecedorNaoTemNegociacaoComContratacaoIgualZeroDiferIgualvalorItem
		implements CalculoFornecedorInterna {

	private CalculoFornecedorInterna proximo;
	
	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {
		boolean verificaContratacao = producaoP.getValorContratacao().equals(new BigDecimal("0.00"));
		int diferencaIgualItem = producaoP.getDiferenca().compareTo(producaoP.getValorItem());
		
		if(verificaContratacao == true && producaoP.isTemContratacao() == false && diferencaIgualItem == 0 ){
			
			BigDecimal valorFinal = producaoP.getValorItem();
			producaoP.setValorFornecedor(valorFinal);
			
			producaoP.setDiferencaParaLocco(calculaDiferenca(producaoP));
			
			return valorFinal;
		}else{
			return proximo.calculaValorFornecedor(producaoP);
		}
	}

	@Override
	public BigDecimal calculaDiferenca(ProducaoP producaoP) {
		BigDecimal valorDiferencaCalculado = new BigDecimal("0.00");
		return valorDiferencaCalculado;
	}

	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {
		this.proximo = proximo;
	}

}
