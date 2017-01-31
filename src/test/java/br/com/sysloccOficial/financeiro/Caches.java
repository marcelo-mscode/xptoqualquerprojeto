package br.com.sysloccOficial.financeiro;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioCaches;

public class Caches {

	@Test
	public void testePorcentagem() {
		BigDecimal totalDif = new BigDecimal("1909.41");
		
		RelatorioCaches relatorio = new RelatorioCaches();
		relatorio.setPorcentagem("0.025");
		assertEquals(new BigDecimal("47.73525"), relatorio.getValorPorcentagem());
	}

}
