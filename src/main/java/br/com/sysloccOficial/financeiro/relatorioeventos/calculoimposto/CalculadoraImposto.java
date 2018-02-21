package br.com.sysloccOficial.financeiro.relatorioeventos.calculoimposto;

import java.math.BigDecimal;

public class CalculadoraImposto {
	
	
	public static BigDecimal calculaImpostoSobrevalorLoccoAgencia(BigDecimal valor,BigDecimal porcentagemSobreImposto){
		CalculoImposto calculaImposto = new CalculaImpostoSobreValorLoccoAgencia();
		return calculaImposto.calculaImposto(valor, porcentagemSobreImposto);
	}
	
	
	
}
