package br.com.sysloccOficial.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


@Entity
public class CacheEvento {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idCacheEvento;
	
	private BigDecimal valor;
	private BigDecimal razaoPorcentagem;
	
	@Transient private String razaoPorcentagemFormato;
	
	
// ------------------------------------------------------ //
	@OneToOne @JoinColumn(name="relatorioEvento") private RelatorioEventos relatorioEvento;
	@OneToOne @JoinColumn(name="cachePadrao") private CachePadrao cachePadrao;
	
// ------------------------------------------------------ //

	public Integer getIdCacheEvento() {
		return idCacheEvento;
	}

	public String getRazaoPorcentagemFormato() {
		return razaoPorcentagemFormato;
	}

	public void setRazaoPorcentagemFormato(String razaoPorcentagemFormato) {
		this.razaoPorcentagemFormato = razaoPorcentagemFormato;
	}

	public CachePadrao getCachePadrao() {
		return cachePadrao;
	}

	public void setCachePadrao(CachePadrao cachePadrao) {
		this.cachePadrao = cachePadrao;
	}

	public void setIdCacheEvento(Integer idCacheEvento) {
		this.idCacheEvento = idCacheEvento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getRazaoPorcentagem() {
		return razaoPorcentagem;
	}

	public void setRazaoPorcentagem(BigDecimal razaoPorcentagem) {
		this.razaoPorcentagem = razaoPorcentagem;
	}

	public RelatorioEventos getRelatorioEvento() {
		return relatorioEvento;
	}

	public void setRelatorioEvento(RelatorioEventos relatorioEvento) {
		this.relatorioEvento = relatorioEvento;
	}
	

	
	
	
}
