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
public class FinancItauEntrada {

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idFinancItauEntrada;
	private BigDecimal valor;
	private String descricao;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;


	
	
	public Integer getIdFinancItauEntrada() {
		return idFinancItauEntrada;
	}


	public void setIdFinancItauEntrada(Integer idFinancItauEntrada) {
		this.idFinancItauEntrada = idFinancItauEntrada;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}
	
}
