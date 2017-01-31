package br.com.sysloccOficial.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class DeterQuantpadrao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetermPadrao;
	
	@Column(columnDefinition = "DECIMAL(19,12)")
	private BigDecimal valorUnitPadrao;
	
	@Column(columnDefinition = "DECIMAL(19,12)")
	private BigDecimal precoTotalPadrao;
	
	@Column(columnDefinition = "DECIMAL(19,12)")
	private double quantDetermPadrao;

	private double diariasPadrao;
	
	
// ------------------------------ //
	
	@OneToOne private @JoinColumn(name="grupo") Grupo grupo;

// ------------------------------ //

	public Integer getIdDetermPadrao() {
		return idDetermPadrao;
	}


	public void setIdDetermPadrao(Integer idDetermPadrao) {
		this.idDetermPadrao = idDetermPadrao;
	}


	public BigDecimal getValorUnitPadrao() {
		return valorUnitPadrao;
	}


	public void setValorUnitPadrao(BigDecimal valorUnitPadrao) {
		this.valorUnitPadrao = valorUnitPadrao;
	}


	public BigDecimal getPrecoTotalPadrao() {
		return precoTotalPadrao;
	}


	public void setPrecoTotalPadrao(BigDecimal precoTotalPadrao) {
		this.precoTotalPadrao = precoTotalPadrao;
	}


	public double getQuantDetermPadrao() {
		return quantDetermPadrao;
	}


	public void setQuantDetermPadrao(double quantDetermPadrao) {
		this.quantDetermPadrao = quantDetermPadrao;
	}


	public double getDiariasPadrao() {
		return diariasPadrao;
	}


	public void setDiariasPadrao(double diariasPadrao) {
		this.diariasPadrao = diariasPadrao;
	}


	public Grupo getGrupo() {
		return grupo;
	}


	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

// ------------------------------ //
	
	
	
	
	
	
	
	
}
