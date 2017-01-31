package br.com.sysloccOficial.controllerExcel;

import java.math.BigDecimal;

public class CorpoCategoria {
	
	private Integer idCategoria;
	private String categoria;
	private BigDecimal fee;
	private BigDecimal somaImposto;
	private BigDecimal somaCustoAgencia;
	private BigDecimal somaSubContratado;
	private BigDecimal somaFatDireto;
	private BigDecimal SubTotal;
	private BigDecimal total;
	
	
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getSubTotal() {
		return SubTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		SubTotal = subTotal;
	}
	public BigDecimal getSomaFatDireto() {
		return somaFatDireto;
	}
	public void setSomaFatDireto(BigDecimal somaFatDireto) {
		this.somaFatDireto = somaFatDireto;
	}
	public BigDecimal getSomaSubContratado() {
		return somaSubContratado;
	}
	public void setSomaSubContratado(BigDecimal somaSubContratado) {
		this.somaSubContratado = somaSubContratado;
	}
	public BigDecimal getSomaCustoAgencia() {
		return somaCustoAgencia;
	}
	public void setSomaCustoAgencia(BigDecimal somaCustoAgencia) {
		this.somaCustoAgencia = somaCustoAgencia;
	}
	public BigDecimal getSomaImposto() {
		return somaImposto;
	}
	public void setSomaImposto(BigDecimal somaImposto) {
		this.somaImposto = somaImposto;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
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

	
	
	
	
}
