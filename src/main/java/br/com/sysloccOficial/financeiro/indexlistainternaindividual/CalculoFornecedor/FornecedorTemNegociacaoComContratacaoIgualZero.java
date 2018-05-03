package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;
import br.com.sysloccOficial.model.producao.ProducaoP;

public class FornecedorTemNegociacaoComContratacaoIgualZero implements CalculoFornecedorInterna {

	private CalculoFornecedorInterna proximo;
	
	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {
	
		boolean verificaContratacao = producaoP.getValorContratacao().equals(new BigDecimal("0.00"));
		int verifica = producaoP.getDiferenca().compareTo(producaoP.getValorItem());

		if(verificaContratacao == true && producaoP.isTemContratacao() == true && verifica == 0 ){
			BigDecimal valorFinal =  new BigDecimal("0.00");
			producaoP.setValorFornecedor(valorFinal);
			
			producaoP.setDiferencaParaLocco(calculaDiferenca(producaoP));
			
			return valorFinal;
			
		}else{
			return proximo.calculaValorFornecedor(producaoP);
		}
	}

	@Override
	public BigDecimal calculaDiferenca(ProducaoP producaoP) {
		BigDecimal valorDiferencaCalculado = producaoP.getDiferenca();
		return valorDiferencaCalculado;
	}

	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {
		this.proximo = proximo;
	}

}
