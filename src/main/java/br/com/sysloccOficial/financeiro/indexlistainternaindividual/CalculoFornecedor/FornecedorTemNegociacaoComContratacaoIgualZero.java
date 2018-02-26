package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class FornecedorTemNegociacaoComContratacaoIgualZero implements CalculoFornecedorInterna {

	private CalculoFornecedorInterna proximo;
	
	@Override
	public BigDecimal calculaValorFornecedor(ProducaoP producaoP) {
		
		/*BigDecimal valorFinal = producaoP.getValorItem();
		producaoP.setValorFornecedor(valorFinal);
		return valorFinal;*/
		
		boolean verifica = producaoP.getValorContratacao().equals(new BigDecimal("0.00"));
		
		if(verifica == true && producaoP.isTemContratacao() == false){
			
			JOptionPane.showMessageDialog(null, "Valor: "+ producaoP.getValorContratacao());
			
			BigDecimal valorFinal =  producaoP.getValorItem().subtract(producaoP.getValorContratacao());
			producaoP.setValorFornecedor(valorFinal);
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
