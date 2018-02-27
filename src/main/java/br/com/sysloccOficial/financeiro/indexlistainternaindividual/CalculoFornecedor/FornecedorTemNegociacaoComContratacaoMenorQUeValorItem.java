package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class FornecedorTemNegociacaoComContratacaoMenorQUeValorItem implements CalculoFornecedorInterna {

	private CalculoFornecedorInterna proximo;
	
	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {
		
			boolean zero = producaoP.getValorContratacao().compareTo(BigDecimal.ZERO) <= 0;
		
			int verifica = producaoP.getValorContratacao().compareTo(producaoP.getValorItem());
			
			if(verifica == -1 && zero != true){
				
				CalculaImpostoValorFornecedor calculaValorFornecedor = 
					new CalculadoraImpostoValorFornecedor(producaoP.getValorItem(),producaoP.getImposto(),producaoP.getValorContratacao());
				
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
		return valorDiferencaCalculado;
	}

	@Override
	public void setProximo(CalculoFornecedorInterna proximo) {
		this.proximo = proximo;
	}

}
