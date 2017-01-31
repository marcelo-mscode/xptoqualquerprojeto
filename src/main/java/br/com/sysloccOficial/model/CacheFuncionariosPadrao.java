package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;


@Entity
public class CacheFuncionariosPadrao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCachePadrao;
	private String nomeFunc;
	private String porcentagem;
	private BigDecimal valorPorcentagem;
	private String tipoCache;
	
	
	
// ----------------------------------------------------------------- //

	public Integer getIdCachePadrao() {
		return idCachePadrao;
	}
	public void setIdCachePadrao(Integer idCachePadrao) {
		this.idCachePadrao = idCachePadrao;
	}
	public String getNomeFunc() {
		return nomeFunc;
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
	public String getTipoCache() {
		return tipoCache;
	}
	public void setTipoCache(String tipoCache) {
		this.tipoCache = tipoCache;
	}
	
	/*public TipoCache getTipoCache() {
		return tipoCache;
	}
	public void setTipoCache(TipoCache tipoCache) {
		this.tipoCache = tipoCache;
	}*/
	
}
