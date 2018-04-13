package br.com.sysloccOficial.financeiro.relatorioeventos.calculogiro;

import java.math.BigDecimal;

public interface CalculoGiro {
	public BigDecimal calculaGiro(BigDecimal valorLiquido, BigDecimal cacheSemTelefone, BigDecimal externas, BigDecimal DespesasEvento);
}
