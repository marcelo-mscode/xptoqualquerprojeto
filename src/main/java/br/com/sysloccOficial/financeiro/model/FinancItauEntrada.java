package br.com.sysloccOficial.financeiro.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class FinancItauEntrada {

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idFinancItauEntrada;
	private BigDecimal valor;
	private String descricao;
	private String nfnd;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	// ------------------------------------------------------------------ //	
		@OneToOne @JoinColumn(name="analitico") private FinancAnalitico analitico;	
	// ------------------------------------------------------------------ //	

	public String getNfnd() {
		return nfnd;
	}


	public void setNfnd(String nfnd) {
		this.nfnd = nfnd;
	}


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


	public FinancAnalitico getAnalitico() {
		return analitico;
	}


	public void setAnalitico(FinancAnalitico analitico) {
		this.analitico = analitico;
	}
	
}
