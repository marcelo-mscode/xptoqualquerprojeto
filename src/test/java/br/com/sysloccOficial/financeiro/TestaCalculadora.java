package br.com.sysloccOficial.financeiro;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.sysloccOficial.financeiro.calculadora.Calculadora;
import br.com.sysloccOficial.financeiro.model.CalculoValoresInterna;

public class TestaCalculadora extends Calculadora{

	@Test
	public void testaSomaListaDeValoresVazio() {
		
		List<BigDecimal> valores = new ArrayList<BigDecimal>();
		BigDecimal valorReal = new BigDecimal("0");
		assertEquals(valorReal, somaListaDeValores(valores));
		
	}

	@Test
	public void testaSomaListaDeValores() {
		List<BigDecimal> valores = new ArrayList<BigDecimal>();
		valores.add(new BigDecimal("100"));
		valores.add(new BigDecimal("50"));
		valores.add(new BigDecimal("100"));
		valores.add(new BigDecimal("50"));
		
		BigDecimal valorReal = new BigDecimal("300");
		assertEquals(valorReal, somaListaDeValores(valores));
	}

	@Test
	public void testaSomaListaDeValoresIncompletos() {
		List<BigDecimal> valores = new ArrayList<BigDecimal>();
		
		valores.add(new BigDecimal("0"));
		valores.add(new BigDecimal("50"));
		valores.add(new BigDecimal("0"));
		valores.add(new BigDecimal("50"));
		
		BigDecimal valorReal = new BigDecimal("100");
		assertEquals(valorReal, somaListaDeValores(valores));
	}
	

	@Test
	public void somaDoisValores() {
		
		BigDecimal v1 =  new BigDecimal("100");
		BigDecimal v2 =  new BigDecimal("50");

		BigDecimal valorReal = new BigDecimal("150");
		assertEquals(valorReal, somaDoisValores(v1,v2));
	}
	

	@Test
	public void formulaDeImposto() {
		BigDecimal imposto =  new BigDecimal("22.9");
		BigDecimal v2 =  new BigDecimal("6878.18");
		BigDecimal valorReal = new BigDecimal("2042.935434500649");
	
		assertEquals(valorReal, calculoImpostoPorFora(imposto,v2));
	}
	
	
	
}
