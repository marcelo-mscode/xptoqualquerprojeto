package br.com.sysloccOficial.financeiro.relatorioeventos.calculoimposto;

import java.math.BigDecimal;

public interface CalculoImposto {
	public BigDecimal calculaImposto(BigDecimal valor, BigDecimal porcentagemSobreImposto);
}
