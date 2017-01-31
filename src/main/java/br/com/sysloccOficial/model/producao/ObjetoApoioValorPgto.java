package br.com.sysloccOficial.model.producao;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ObjetoApoioValorPgto {

	private Integer parcela;
	private Integer parcelado;
	private String dias;
	@Temporal(TemporalType.DATE) private Date dataPagar;
	private String valorPagar;
// --------------------------------------------- //

	
	public Integer getParcela() {
		return parcela;
	}
	public Integer getParcelado() {
		return parcelado;
	}
	public void setParcelado(Integer parcelado) {
		this.parcelado = parcelado;
	}
	public String getValorPagar() {
		return valorPagar;
	}
	public void setValorPagar(String valorPagar) {
		this.valorPagar = valorPagar;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}
	public String getDias() {
		return dias;
	}
	public void setDias(String dias) {
		this.dias = dias;
	}
	public Date getDataPagar() {
		return dataPagar;
	}
	public void setDataPagar(Date dataPagar) {
		this.dataPagar = dataPagar;
	}
	
// --------------------------------------------- //
	
	
	
}
