package br.com.sysloccOficial.financeiro.relatorioeventos.calculogiro;

import java.math.BigDecimal;

public class CalculadoraGiro {
	
	public static BigDecimal calculadoraGiroSemTelefone(BigDecimal valorLiquido, BigDecimal cacheSemTelefone, BigDecimal externas){
		CalculoGiro calcGiroSemTelefone = new CalculaGiroSemTelefone();
		return calcGiroSemTelefone.calculaGiro(valorLiquido, cacheSemTelefone, externas);
	}
	
	public static BigDecimal calculadoraGiroComTelefone(){
		return null;
	}
	
}
