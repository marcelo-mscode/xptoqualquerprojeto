package br.com.sysloccOficial.financeiro.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AnaliticoTotalBancos {
	
	private String nomeBanco;
	private Date dataAberturaCaixa;
	private Date dataFechamentoCaixa;
	private BigDecimal valorAbertura;
	private BigDecimal totalTarifas;
	private BigDecimal totalCreditos;
	private BigDecimal totalDebitos;
	private BigDecimal totalSaldo;
	private BigDecimal valoresDefinir;

	
	public String getNomeBanco() {
		return nomeBanco;
	}
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	public Date getDataAberturaCaixa() {
		return dataAberturaCaixa;
	}
	public void setDataAberturaCaixa(Date dataAberturaCaixa) {
		this.dataAberturaCaixa = dataAberturaCaixa;
	}
	public Date getDataFechamentoCaixa() {
		return dataFechamentoCaixa;
	}
	public void setDataFechamentoCaixa(Date dataFechamentoCaixa) {
		this.dataFechamentoCaixa = dataFechamentoCaixa;
	}
	public BigDecimal getValorAbertura() {
		return valorAbertura;
	}
	public void setValorAbertura(BigDecimal valorAbertura) {
		this.valorAbertura = valorAbertura;
	}
	public BigDecimal getTotalTarifas(List<MovimentacaoBancos> movBancos) {
		
		BigDecimal valor = new BigDecimal("0");
		
		
		for (int i = 0; i < movBancos.si; i++) {
			
		}
		
		
		return totalTarifas;
	}
	public void setTotalTarifas(BigDecimal totalTarifas) {
		this.totalTarifas = totalTarifas;
	}
	public BigDecimal getTotalCreditos() {
		return totalCreditos;
	}
	public void setTotalCreditos(BigDecimal totalCreditos) {
		this.totalCreditos = totalCreditos;
	}
	public BigDecimal getTotalDebitos() {
		return totalDebitos;
	}
	public void setTotalDebitos(BigDecimal totalDebitos) {
		this.totalDebitos = totalDebitos;
	}
	public BigDecimal getTotalSaldo() {
		return totalSaldo;
	}
	public void setTotalSaldo(BigDecimal totalSaldo) {
		this.totalSaldo = totalSaldo;
	}
	public BigDecimal getValoresDefinir() {
		return valoresDefinir;
	}
	public void setValoresDefinir(BigDecimal valoresDefinir) {
		this.valoresDefinir = valoresDefinir;
	}
}
