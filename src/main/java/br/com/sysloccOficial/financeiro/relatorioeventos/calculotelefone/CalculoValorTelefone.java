package br.com.sysloccOficial.financeiro.relatorioeventos.calculotelefone;

import java.math.BigDecimal;

public interface CalculoValorTelefone {
	
	public BigDecimal calculoValorTelefone(BigDecimal giroSemTelefoneEvento, Integer idRelatorioAtual,String mes,String ano);

}