package br.com.sysloccOficial.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class GiroEvento {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGiroEvento;
	private BigDecimal giroSemTelefone;
	private BigDecimal giroComTelefone;
	
	private String mesEvento;
	private String anoEvento;
	
	
// --------------------------------------------------------- //
	@OneToOne @JoinColumn(name="relatorioEvento") private RelatorioEventos relatorioEvento;
// --------------------------------------------------------- //


	public Integer getIdGiroEvento() {
		return idGiroEvento;
	}


	public void setIdGiroEvento(Integer idGiroEvento) {
		this.idGiroEvento = idGiroEvento;
	}


	public BigDecimal getGiroSemTelefone() {
		return giroSemTelefone;
	}


	public void setGiroSemTelefone(BigDecimal giroSemTelefone) {
		this.giroSemTelefone = giroSemTelefone;
	}


	public BigDecimal getGiroComTelefone() {
		return giroComTelefone;
	}


	public void setGiroComTelefone(BigDecimal giroComTelefone) {
		this.giroComTelefone = giroComTelefone;
	}


	public String getMesEvento() {
		return mesEvento;
	}


	public void setMesEvento(String mesEvento) {
		this.mesEvento = mesEvento;
	}


	public String getAnoEvento() {
		return anoEvento;
	}


	public void setAnoEvento(String anoEvento) {
		this.anoEvento = anoEvento;
	}


	public RelatorioEventos getRelatorioEvento() {
		return relatorioEvento;
	}


	public void setRelatorioEvento(RelatorioEventos relatorioEvento) {
		this.relatorioEvento = relatorioEvento;
	}

	
	
	
	

}
