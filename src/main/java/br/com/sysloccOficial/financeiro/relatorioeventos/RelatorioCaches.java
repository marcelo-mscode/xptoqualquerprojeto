package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class RelatorioCaches {
	
	public RelatorioCaches(){}
	
	private String nomeFunc;
	private String porcentagem;
	private BigDecimal valorPorcentagem;
	private TipoCache tipoCache;


//------------------------------------------- //
	
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
	public TipoCache getTipoCache() {
		return tipoCache;
	}
	public void setTipoCache(TipoCache tipoCache) {
		this.tipoCache = tipoCache;
	}
	
}
