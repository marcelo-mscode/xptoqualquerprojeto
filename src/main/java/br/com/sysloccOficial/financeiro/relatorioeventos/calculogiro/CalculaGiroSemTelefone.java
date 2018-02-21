package br.com.sysloccOficial.financeiro.relatorioeventos.calculogiro;

import java.math.BigDecimal;

class CalculaGiroSemTelefone implements CalculoGiro {

	@Override
	public BigDecimal calculaGiro(BigDecimal valorLiquido, BigDecimal cacheSemTelefone, BigDecimal externas) {
		BigDecimal giroSemTelefone = new BigDecimal("0");
		BigDecimal bvs = new BigDecimal("0");
		BigDecimal internas = new BigDecimal("0");
		giroSemTelefone = valorLiquido.subtract(cacheSemTelefone).subtract(externas).subtract(internas).add(bvs);
		
		return giroSemTelefone;
	}

}
