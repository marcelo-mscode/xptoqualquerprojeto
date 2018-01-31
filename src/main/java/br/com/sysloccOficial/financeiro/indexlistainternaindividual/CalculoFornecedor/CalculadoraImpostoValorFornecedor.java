package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

public class CalculadoraImpostoValorFornecedor implements CalculaImpostoValorFornecedor{

	private BigDecimal valorItem;
	private double imposto;
	private BigDecimal valorContratacao;


	public CalculadoraImpostoValorFornecedor(BigDecimal valorItem,double imposto, BigDecimal valorContratacao) {
		this.valorItem = valorItem;
		this.imposto = imposto;
		this.valorContratacao = valorContratacao;
	}
	
	@Override
	public BigDecimal calculaImpostoValorFornecedor() {
		
		BigDecimal diferenca = valorItem.subtract(valorContratacao);
		BigDecimal diferencaComImposto = diferenca.multiply(new BigDecimal(imposto).divide(new BigDecimal("100")));
		BigDecimal valorFinal = diferencaComImposto.add(valorContratacao);
		
		return valorFinal;
	}

}
