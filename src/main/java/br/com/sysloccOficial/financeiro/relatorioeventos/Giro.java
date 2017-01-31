package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.math.BigDecimal;

public interface Giro {
	
	//valor liqquido
	//valor total de caches
	//valor total internas
	//valor total externas
	
	public BigDecimal calculoGiro();
	public BigDecimal calculoTotalDiferenca();

}
