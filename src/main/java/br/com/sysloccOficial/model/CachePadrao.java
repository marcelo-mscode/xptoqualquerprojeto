package br.com.sysloccOficial.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;


@Entity
public class CachePadrao {
	
	

	public CachePadrao(){}
	
	public CachePadrao(BigDecimal totalDiferenca){
		this.totalDiferenca = totalDiferenca;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCachePadrao;
	private String nomeFunc;
	private String porcentagem;
	private boolean habilitado;
	private BigDecimal razaoPorcentagem;
	
	@Enumerated(EnumType.STRING)
	private TipoCache tipoCache;
	
// ------------------------------------------------- //	
	@Transient private BigDecimal valorPorcentagem;
	@Transient private BigDecimal determinaPorcentagem = new BigDecimal("100");
	@Transient private BigDecimal totalDiferenca;
// ------------------------------------------------- //	

	
	public String getNomeFunc() {
		return nomeFunc;
	}

	public BigDecimal getRazaoPorcentagem() {
		return razaoPorcentagem;
	}

	public void setRazaoPorcentagem(BigDecimal razaoPorcentagem) {
		this.razaoPorcentagem = razaoPorcentagem;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Integer getIdCachePadrao() {
		return idCachePadrao;
	}

	public void setIdCachePadrao(Integer idCachePadrao) {
		this.idCachePadrao = idCachePadrao;
	}

	public void setNomeFunc(String nomeFunc) {
		this.nomeFunc = nomeFunc;
	}

	public String getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}

	public BigDecimal getValorPorcentagem() {
		return valorPorcentagem;
	}

	public void setValorPorcentagem(BigDecimal valorPorcentagem) {
		this.valorPorcentagem = valorPorcentagem;
	}

	public TipoCache getTipoCache() {
		return tipoCache;
	}

	public void setTipoCache(TipoCache tipoCache) {
		this.tipoCache = tipoCache;
	}
	
	
	
	
	
	
}
