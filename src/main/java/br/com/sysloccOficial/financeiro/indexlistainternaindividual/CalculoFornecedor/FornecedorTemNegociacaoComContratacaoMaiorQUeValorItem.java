package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import br.com.sysloccOficial.model.producao.ProducaoP;

/*  Obs compareTo:
 * 
 *  -1 menor que
 *   0 igual a 
 *   1 maior que
 *  
 *  */

public class FornecedorTemNegociacaoComContratacaoMaiorQUeValorItem implements CalculoFornecedorInterna {

	private CalculoFornecedorInterna proximo;


	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {
		
		int verifica = producaoP.getValorContratacao().compareTo(producaoP.getValorItem());
		
 		if(verifica == 1){
			
			BigDecimal valorFinal =   producaoP.getValorContratacao();

			producaoP.setValorFornecedor(valorFinal);
			
			producaoP.setDiferencaParaLocco(calculaDiferenca(producaoP));
			
			return valorFinal;
		}else{
			return proximo.calculaValorFornecedor(producaoP);
		}
	}

	@Override
	public BigDecimal calculaDiferenca(ProducaoP producaoP) {
		BigDecimal valorDiferencaCalculado = producaoP.getValorItem().subtract(producaoP.getValorFornecedor());
		return valorDiferencaCalculado;
	}
	
	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {
		this.proximo = proximo;
	}
}
