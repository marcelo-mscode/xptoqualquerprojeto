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
public class FinancOutrasDespesas {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idFinancOutrasDespesas;
	private BigDecimal valor;
	private String descricao;
	private boolean fixo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
// ------------------------------------------------------------------ //	
	@OneToOne @JoinColumn(name="analitico") private FinancAnalitico analitico;	
// ------------------------------------------------------------------ //	
	
	public Integer getIdFinancOutrasDespesas() {
		return idFinancOutrasDespesas;
	}

	public boolean isFixo() {
		return fixo;
	}

	public void setFixo(boolean fixo) {
		this.fixo = fixo;
	}

	public void setIdFinancOutrasDespesas(Integer idFinancOutrasDespesas) {
		this.idFinancOutrasDespesas = idFinancOutrasDespesas;
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
