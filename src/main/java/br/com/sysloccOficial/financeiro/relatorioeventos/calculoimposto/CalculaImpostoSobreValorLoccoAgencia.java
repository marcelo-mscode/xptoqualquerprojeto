package br.com.sysloccOficial.financeiro.relatorioeventos.calculoimposto;

import java.math.BigDecimal;

public class CalculaImpostoSobreValorLoccoAgencia implements CalculoImposto {

	@Override
	public BigDecimal calculaImposto(BigDecimal valor,BigDecimal porcentagemSobreImposto) {
		return valor.multiply(porcentagemSobreImposto);
	}

}
