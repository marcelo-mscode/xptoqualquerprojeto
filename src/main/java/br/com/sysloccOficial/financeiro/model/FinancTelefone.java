package br.com.sysloccOficial.financeiro.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class FinancTelefone {
	

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idFinancTelefone;
	private BigDecimal valor;
	private String semCategoria;
	private boolean fixo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
// ------------------------------------------------------------------ //	
	@OneToOne @JoinColumn(name="analitico") private FinancAnalitico analitico;
	/*@OneToOne @JoinColumn(name="categoria") private FinancCategTelef categoria;*/
// ------------------------------------------------------------------ //	
	
	public Integer getIdFinancTelefone() {
		return idFinancTelefone;
	}
	/*public FinancCategTelef getCategoria() {
		return categoria;
	}

	public void setCategoria(FinancCategTelef categoria) {
		this.categoria = categoria;
	}*/

	public boolean isFixo() {
		return fixo;
	}

	public void setFixo(boolean fixo) {
		this.fixo = fixo;
	}

	public String getSemCategoria() {
		return semCategoria;
	}

	public void setSemCategoria(String semCategoria) {
		this.semCategoria = semCategoria;
	}

	public FinancAnalitico getAnalitico() {
		return analitico;
	}

	public void setAnalitico(FinancAnalitico analitico) {
		this.analitico = analitico;
	}

	public void setIdFinancTelefone(Integer idFinancTelefone) {
		this.idFinancTelefone = idFinancTelefone;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
}
