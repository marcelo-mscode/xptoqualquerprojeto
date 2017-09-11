package br.com.sysloccOficial.financeiro.atualizaInterna;

import java.math.BigDecimal;

public class CalculoGiraSemTelefone extends CalculoCacheDiretoria {

	public CalculoGiraSemTelefone() {
		super();
	}

	public BigDecimal caculaGiroSemTelefone(BigDecimal valorLiquido, BigDecimal cacheSemTelefone, BigDecimal externas) {
		
		
		BigDecimal giroSemTelefone = new BigDecimal("0");
		BigDecimal bvs = new BigDecimal("0");
		BigDecimal internas = new BigDecimal("0");
		giroSemTelefone = valorLiquido.subtract(cacheSemTelefone).subtract(externas).subtract(internas).add(bvs);
		
		
		return giroSemTelefone;
	}

}