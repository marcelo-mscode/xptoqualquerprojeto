package br.com.sysloccOficial.financeiro.calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Calculadora {
	
	private BigDecimal calculoGeralDeImposto = new BigDecimal("0");
	private BigDecimal somaDoisValores = new BigDecimal("0");
	private BigDecimal subtraiDoisValores = new BigDecimal("0");
	private BigDecimal somaListaDeValores;
	private BigDecimal formulaDeImpostoPorFora;

	protected BigDecimal getCalculoGeralDeImposto(BigDecimal _imposto, BigDecimal _valor) {
		BigDecimal impostoFracional = _imposto.divide(new BigDecimal("100"));
		calculoGeralDeImposto = _valor.multiply(impostoFracional);
		return calculoGeralDeImposto;
	}
	protected BigDecimal somaDoisValores(BigDecimal _valor1, BigDecimal _valor2){
		somaDoisValores = _valor1.add(_valor2);
		return somaDoisValores;
	}
	protected BigDecimal somaListaDeValores(List<BigDecimal> _somaListaValores){
		somaListaDeValores = new BigDecimal("0");
		for (BigDecimal bigDecimal : _somaListaValores){
			somaListaDeValores = somaListaDeValores.add(bigDecimal);
		}
		
		return somaListaDeValores;
	}
	protected double multiplicaQuantPorDiarias(double quant1, double quant2, double diaria){
		return quant1*quant2*diaria;
	}
	protected BigDecimal calculoImpostoPorFora(BigDecimal _impostoCategoria, BigDecimal _valor){
		BigDecimal cem = new BigDecimal("100");
		BigDecimal impostoFracionalParte1 = cem.subtract(_impostoCategoria);
		BigDecimal impostoFracionalParte2 = impostoFracionalParte1.divide(cem,12,RoundingMode.UP);

		BigDecimal impostoFracionalParte3 = _valor.divide(impostoFracionalParte2,12,RoundingMode.UP);
		formulaDeImpostoPorFora = impostoFracionalParte3.subtract(_valor);
		
		return formulaDeImpostoPorFora;
	}
	protected BigDecimal subtraiDoisValores(BigDecimal _valor1, BigDecimal _valor2){
		subtraiDoisValores = _valor1.subtract(_valor2);
		return subtraiDoisValores;
	}
	
	
	
	
	
}
