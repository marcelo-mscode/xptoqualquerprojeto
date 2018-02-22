package br.com.sysloccOficial.financeiro.relatorioeventos.calculotelefone;

import java.math.BigDecimal;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDAO;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;

public class CalculadoraTelefone {
	
	public static BigDecimal calculaValorTelefone(AnaliticoIndividualDAO analiticoDAO,RelatorioEventoDAO relatorioDAO,BigDecimal giroSemTelefoneEvento,Integer idRelatorioAtual, String mes, String ano){
		CalculoValorTelefone calculaTelefone = new CalculaValorTelefone(analiticoDAO,relatorioDAO);
		return calculaTelefone.calculoValorTelefone(giroSemTelefoneEvento, idRelatorioAtual, mes, ano);
	}

}
