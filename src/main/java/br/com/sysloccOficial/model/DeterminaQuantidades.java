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
public class DeterminaQuantidades {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDeterm;
	
	@Column(columnDefinition = "DECIMAL(19,12)")
	private BigDecimal valorUnit;
	
	@Column(columnDefinition = "DECIMAL(19,12)")
	private BigDecimal precoTotal;
	
	@Column(columnDefinition = "DECIMAL(19,12)")
	private double quantDeterm;

	private double diarias;
	
	@Column(columnDefinition = "DECIMAL(19,12)")
	private BigDecimal  campoAux;
	
	
// ------------------------------ //
	
	@OneToOne private @JoinColumn(name="produtoGrupo") ProdutoGrupo produtoGrupo;
	@OneToOne private @JoinColumn(name="grupo") Grupo grupo;
	
// ------------------------------ //
	
	
	public Integer getIdDeterm() {
		return idDeterm;
	}
	public BigDecimal getCampoAux() {
		return campoAux;
	}
	public void setCampoAux(BigDecimal campoAux) {
		this.campoAux = campoAux;
	}
	public double getDiarias() {
		return diarias;
	}
	public void setDiarias(double diarias) {
		this.diarias = diarias;
	}
	public BigDecimal getValorUnit() {
		return valorUnit;
	}
	public void setValorUnit(BigDecimal valorUnit) {
		this.valorUnit = valorUnit;
	}
	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}
	public double getQuantDeterm() {
		return quantDeterm;
	}
	public void setQuantDeterm(double quantDeterm) {
		this.quantDeterm = quantDeterm;
	}
	public void setIdDeterm(Integer idDeterm) {
		this.idDeterm = idDeterm;
	}
	public ProdutoGrupo getProdutoGrupo() {
		return produtoGrupo;
	}
	public void setProdutoGrupo(ProdutoGrupo produtoGrupo) {
		this.produtoGrupo = produtoGrupo;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
}
