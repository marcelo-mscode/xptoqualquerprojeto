package br.com.sysloccOficial.financeiro.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

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
	
	public BigDecimal getTotalTarifas() {
		return totalTarifas;
	}
	
	public void setTotalTarifas(HashSet<MovimentacaoBancosTarifas> _totalTarifas) {
		
		BigDecimal valores = new BigDecimal("0");
		
        Iterator<MovimentacaoBancosTarifas> it = _totalTarifas.iterator();
        while(it.hasNext()){
        	MovimentacaoBancosTarifas valorCliente = (MovimentacaoBancosTarifas)it.next();
           
        	valores = valores.add(valorCliente.getValor());
        }
		
		this.totalTarifas = valores;
	}
	public BigDecimal getTotalCreditos() {
		return totalCreditos;
	}
	public void setTotalCreditos(HashSet<MovimentacaoBancos> _totalCreditos) {
		
		BigDecimal valores = new BigDecimal("0");
        Iterator<MovimentacaoBancos> it = _totalCreditos.iterator();
        while(it.hasNext()){
        	MovimentacaoBancos valorCliente = (MovimentacaoBancos)it.next();
        	valores = valores.add(valorCliente.getValor());
        }
		
		this.totalCreditos = valores;
	}
	public BigDecimal getTotalDebitos() {
		return totalDebitos;
	}

	public void setTotalDebitos(HashSet<MovimentacaoBancosSaidas> _totalDebitos) {
		BigDecimal valores = new BigDecimal("0");
        Iterator<MovimentacaoBancosSaidas> it = _totalDebitos.iterator();
        while(it.hasNext()){
        	MovimentacaoBancosSaidas valorCliente = (MovimentacaoBancosSaidas)it.next();
        	valores = valores.add(valorCliente.getValor());
        }
		this.totalDebitos = valores;
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
