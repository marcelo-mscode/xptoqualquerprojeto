package br.com.sysloccOficial.financeiro.model;

import java.math.BigDecimal;
import java.util.Calendar;
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
public class FinancDespesas {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idFinancDespesas;
	private BigDecimal valor;
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
// ------------------------------------------------------------------ //	
	@OneToOne @JoinColumn(name="analitico") private FinancAnalitico analitico;	
// ------------------------------------------------------------------ //	

	public Integer getIdFinancDespesas() {
		return idFinancDespesas;
	}

	public void setIdFinancDespesas(Integer idFinancDespesas) {
		this.idFinancDespesas = idFinancDespesas;
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
