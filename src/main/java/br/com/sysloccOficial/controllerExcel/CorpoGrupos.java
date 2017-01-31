package br.com.sysloccOficial.controllerExcel;

import java.math.BigDecimal;

public class CorpoGrupos {

	private Integer idCategoria;
	private String linha;
	private String categoria;
	private double quant;
	private BigDecimal custounit;
	private double diarias;
	private BigDecimal custosAgencia;
	private BigDecimal subContratado;
	private BigDecimal faturamentoDiretoND;
	private BigDecimal subTotal;
	private BigDecimal fee;
	private BigDecimal Impostos;
	private BigDecimal total;
	private String informacoes;
	private String naoInclusosCusto;
	private int grupoCategoriaBayer;
	
	
	private boolean temFee;
	private boolean temReduzido;
	
// --------------------------------------------------------------------------- //

	
	public int getGrupoCategoriaBayer() {
		return grupoCategoriaBayer;
	}
	public boolean isTemFee() {
		return temFee;
	}
	public void setTemFee(boolean temFee) {
		this.temFee = temFee;
	}
	public boolean isTemReduzido() {
		return temReduzido;
	}
	public void setTemReduzido(boolean temReduzido) {
		this.temReduzido = temReduzido;
	}
	public void setGrupoCategoriaBayer(int grupoCategoriaBayer) {
		this.grupoCategoriaBayer = grupoCategoriaBayer;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getLinha() {
		return linha;
	}
	public void setLinha(String linha) {
		this.linha = linha;
	}
	public double getQuant() {
		return quant;
	}
	public void setQuant(double quant) {
		this.quant = quant;
	}
	public BigDecimal getCustounit() {
		return custounit;
	}
	public void setCustounit(BigDecimal bigDecimal) {
		this.custounit = bigDecimal;
	}
	public double getDiarias() {
		return diarias;
	}
	public void setDiarias(double diarias) {
		this.diarias = diarias;
	}
	public BigDecimal getCustosAgencia() {
		return custosAgencia;
	}
	public void setCustosAgencia(BigDecimal custosAgencia) {
		this.custosAgencia = custosAgencia;
	}
	public BigDecimal getSubContratado() {
		return subContratado;
	}
	public void setSubContratado(BigDecimal subContratado) {
		this.subContratado = subContratado;
	}
	public BigDecimal getFaturamentoDiretoND() {
		return faturamentoDiretoND;
	}
	public void setFaturamentoDiretoND(BigDecimal faturamentoDiretoND) {
		this.faturamentoDiretoND = faturamentoDiretoND;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getImpostos() {
		return Impostos;
	}
	public void setImpostos(BigDecimal impostos) {
		Impostos = impostos;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	public String getNaoInclusosCusto() {
		return naoInclusosCusto;
	}
	public void setNaoInclusosCusto(String naoInclusosCusto) {
		this.naoInclusosCusto = naoInclusosCusto;
	}
}
