package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class FornecedorTemNegociacaoComContratacaoMaiorQUeValorItem implements CalculoFornecedorInterna {

	private CalculoFornecedorInterna proximo;


	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {
		
		int verifica = producaoP.getValorContratacao().compareTo(producaoP.getValorItem());
		
		/*  Obs compareTo:
		 * 
		 *  -1 menor que
		 *   0 igual a 
		 *   1 maior que
		 *  
		 *  */
		
		if(verifica == -1){
			
			CalculaImpostoValorFornecedor calculaValorFornecedor = 
				new CalculadoraImpostoValorFornecedor(producaoP.getValorItem(),producaoP.getImposto(),producaoP.getValorContratacao() );
			
			/*
			BigDecimal diferenca = producaoP.getValorItem().subtract(producaoP.getValorContratacao());
			BigDecimal diferencaComImposto = diferenca.multiply(new BigDecimal(producaoP.getImposto())
					                                  .divide(new BigDecimal("100")));
			*/
			
			BigDecimal valorFinal = calculaValorFornecedor.calculaImpostoValorFornecedor();
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
//		producaoP.setDiferencaParaLocco(valorDiferencaCalculado);
		
		return valorDiferencaCalculado;
	}

	
	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {
		this.proximo = proximo;
	}
	
	
}
