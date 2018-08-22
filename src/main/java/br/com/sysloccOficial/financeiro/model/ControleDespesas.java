package br.com.sysloccOficial.financeiro.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ControleDespesas {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idControleDespesas;
	private String descricao;
	private String cliente;
	private BigDecimal marceloValor;
	private BigDecimal pedroValor;
	private BigDecimal celiaValor;
	private BigDecimal loccoValor;
	private BigDecimal ccLoccoValor;
	private String obs;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;


	public Integer getIdControleDespesas() {
		return idControleDespesas;
	}


	public void setIdControleDespesas(Integer idControleDespesas) {
		this.idControleDespesas = idControleDespesas;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public BigDecimal getMarceloValor() {
		return marceloValor;
	}


	public void setMarceloValor(BigDecimal marceloValor) {
		this.marceloValor = marceloValor;
	}


	public BigDecimal getPedroValor() {
		return pedroValor;
	}


	public void setPedroValor(BigDecimal pedroValor) {
		this.pedroValor = pedroValor;
	}

	public BigDecimal getCeliaValor() {
		return celiaValor;
	}


	public void setCeliaValor(BigDecimal celiaValor) {
		this.celiaValor = celiaValor;
	}


	public BigDecimal getLoccoValor() {
		return loccoValor;
	}


	public void setLoccoValor(BigDecimal loccoValor) {
		this.loccoValor = loccoValor;
	}


	public BigDecimal getCcLoccoValor() {
		return ccLoccoValor;
	}


	public void setCcLoccoValor(BigDecimal ccLoccoValor) {
		this.ccLoccoValor = ccLoccoValor;
	}


	public String getObs() {
		return obs;
	}


	public void setObs(String obs) {
		this.obs = obs;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}

}
