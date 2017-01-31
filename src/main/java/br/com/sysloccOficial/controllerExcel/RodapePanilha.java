package br.com.sysloccOficial.controllerExcel;

import java.math.BigDecimal;

public class RodapePanilha {
	
	private String textoSubTotal;
	private BigDecimal custoAgencia;
	private BigDecimal subContratado;
	private BigDecimal fatDireto;
	private BigDecimal subTotal;
	private BigDecimal Fee;
	private BigDecimal imposto;
	private BigDecimal total;
	
	
	
	public String getTextoSubTotal() {
		return textoSubTotal;
	}
	public void setTextoSubTotal(String textoSubTotal) {
		this.textoSubTotal = textoSubTotal;
	}
	public BigDecimal getCustoAgencia() {
		return custoAgencia;
	}
	public void setCustoAgencia(BigDecimal custoAgencia) {
		this.custoAgencia = custoAgencia;
	}
	public BigDecimal getSubContratado() {
		return subContratado;
	}
	public void setSubContratado(BigDecimal subContratado) {
		this.subContratado = subContratado;
	}
	public BigDecimal getFatDireto() {
		return fatDireto;
	}
	public void setFatDireto(BigDecimal fatDireto) {
		this.fatDireto = fatDireto;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getFee() {
		return Fee;
	}
	public void setFee(BigDecimal fee) {
		Fee = fee;
	}
	public BigDecimal getImposto() {
		return imposto;
	}
	public void setImposto(BigDecimal imposto) {
		this.imposto = imposto;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
	
	

}
