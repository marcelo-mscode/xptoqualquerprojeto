package br.com.sysloccOficial.financeiro.relatorioeventos.calculogiro;

import java.math.BigDecimal;

class CalculaGiroSemTelefone implements CalculoGiro {

	@Override
	public BigDecimal calculaGiro(BigDecimal valorLiquido, BigDecimal cacheSemTelefone, BigDecimal externas) {
		
		
		BigDecimal valorLiquidoComScala = valorLiquido.setScale(2,2);
		BigDecimal cacheSemTelefoneComScala = cacheSemTelefone.setScale(2,2);
		BigDecimal externasComScala = externas.setScale(2,2);
		
		
		BigDecimal giroSemTelefone = new BigDecimal("0.00");
		BigDecimal bvs = new BigDecimal("0.00");
		BigDecimal internas = new BigDecimal("0.00");
//		giroSemTelefone = valorLiquido.subtract(cacheSemTelefone).subtract(externas).subtract(internas).add(bvs);
		giroSemTelefone = valorLiquidoComScala
						  .subtract(cacheSemTelefoneComScala)
						  .subtract(externasComScala)
						  .subtract(internas)
						  .add(bvs)
						  .subtract(new BigDecimal("79.00"))
						  ;
		
		return giroSemTelefone;
	}

}
