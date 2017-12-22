package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class ValorFornecedorTemNegociacao implements CalculoFornecedorInterna {

	private CalculoFornecedorInterna proximo;


	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {
		
		int verifica = producaoP.getValorItem().compareTo(producaoP.getValorContratacao());
		
		if(verifica == -1 || verifica == 1){
			
			BigDecimal diferenca = producaoP.getValorItem().subtract(producaoP.getValorContratacao());
			BigDecimal diferencaComImposto = diferenca.multiply(new BigDecimal(producaoP.getImposto())
					                                  .divide(new BigDecimal("100")));
			BigDecimal valorFinal = diferencaComImposto.add(producaoP.getValorContratacao());

			producaoP.setValorFornecedor(valorFinal);
			producaoP.setDiferencaParaLocco(calculaDiferenca(producaoP));
			
			
			return valorFinal;
		}else{
			return proximo.calculaValorFornecedor(producaoP);
		}
	}

	@Override
	public BigDecimal calculaDiferenca(ProducaoP producaoP) {
		BigDecimal valorDiferencaCalculado = producaoP.getValorFornecedor().subtract(producaoP.getValorItem());
		return valorDiferencaCalculado;
	}

	
	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {
		this.proximo = proximo;
	}
	
	
}
