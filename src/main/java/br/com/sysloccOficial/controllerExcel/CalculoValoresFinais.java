package br.com.sysloccOficial.controllerExcel;

import java.math.BigDecimal;

public class CalculoValoresFinais {

	
	private BigDecimal totalServicosSubcontratados;	
	private BigDecimal feeNF1;	
	private BigDecimal custosInternosAgencia;	
	private BigDecimal subTotal;	
	private BigDecimal impostos;	
	private BigDecimal totalNF1;	
	private BigDecimal previsaoExtrasSobreSubcontratadosNF2;	
	private BigDecimal feeNF2;
	private BigDecimal previsaoExtrasSobreCustosInternos;	
	private BigDecimal subTotalNF2;	
	private BigDecimal impostosNF2;
	private BigDecimal totalNF2;	
	private BigDecimal totalGeral;	
	private double feeLista;	
	private double impostoLista;	
	private double porcentagemSubContratados;	
	private double porcentagemCustosInternos;	

	
	
	
	
// ----------------- gts setts ------------------- //	
	
	
	
	public BigDecimal getTotalNF2() {
		return totalNF2;
	}
	public double getPorcentagemCustosInternos() {
		return porcentagemCustosInternos;
	}
	public void setPorcentagemCustosInternos(double porcentagemCustosInternos) {
		this.porcentagemCustosInternos = porcentagemCustosInternos;
	}
	public double getPorcentagemSubContratados() {
		return porcentagemSubContratados;
	}
	public void setPorcentagemSubContratados(double porcentagemSubContratados) {
		this.porcentagemSubContratados = porcentagemSubContratados;
	}
	public double getFeeLista() {
		return feeLista;
	}
	public void setFeeLista(double feeLista) {
		this.feeLista = feeLista;
	}
	public double getImpostoLista() {
		return impostoLista;
	}
	public void setImpostoLista(double impostoLista) {
		this.impostoLista = impostoLista;
	}
	public BigDecimal getTotalGeral() {
		return totalGeral;
	}
	public void setTotalGeral(BigDecimal totalGeral) {
		this.totalGeral = totalGeral;
	}
	public BigDecimal getPrevisaoExtrasSobreSubcontratadosNF2() {
		return previsaoExtrasSobreSubcontratadosNF2;
	}
	public void setPrevisaoExtrasSobreSubcontratadosNF2(
			BigDecimal previsaoExtrasSobreSubcontratadosNF2) {
		this.previsaoExtrasSobreSubcontratadosNF2 = previsaoExtrasSobreSubcontratadosNF2;
	}
	public void setTotalNF2(BigDecimal totalNF2) {
		this.totalNF2 = totalNF2;
	}
	public BigDecimal getTotalServicosSubcontratados() {
		return totalServicosSubcontratados;
	}
	public void setTotalServicosSubcontratados(
			BigDecimal totalServicosSubcontratados) {
		this.totalServicosSubcontratados = totalServicosSubcontratados;
	}
	public BigDecimal getFeeNF1() {
		return feeNF1;
	}
	public void setFeeNF1(BigDecimal feeNF1) {
		this.feeNF1 = feeNF1;
	}
	public BigDecimal getCustosInternosAgencia() {
		return custosInternosAgencia;
	}
	public void setCustosInternosAgencia(BigDecimal custosInternosAgencia) {
		this.custosInternosAgencia = custosInternosAgencia;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getImpostos() {
		return impostos;
	}
	public void setImpostos(BigDecimal impostos) {
		this.impostos = impostos;
	}
	public BigDecimal getTotalNF1() {
		return totalNF1;
	}
	public void setTotalNF1(BigDecimal totalNF1) {
		this.totalNF1 = totalNF1;
	}
	public BigDecimal getFeeNF2() {
		return feeNF2;
	}
	public void setFeeNF2(BigDecimal feeNF2) {
		this.feeNF2 = feeNF2;
	}
	public BigDecimal getPrevisaoExtrasSobreCustosInternos() {
		return previsaoExtrasSobreCustosInternos;
	}
	public void setPrevisaoExtrasSobreCustosInternos(
			BigDecimal previsaoExtrasSobreCustosInternos) {
		this.previsaoExtrasSobreCustosInternos = previsaoExtrasSobreCustosInternos;
	}
	public BigDecimal getSubTotalNF2() {
		return subTotalNF2;
	}
	public void setSubTotalNF2(BigDecimal subTotalNF2) {
		this.subTotalNF2 = subTotalNF2;
	}
	public BigDecimal getImpostosNF2() {
		return impostosNF2;
	}
	public void setImpostosNF2(BigDecimal impostosNF2) {
		this.impostosNF2 = impostosNF2;
	}	
	
	
	
	
	
	
	
	
	
	
}
