package br.com.sysloccOficial.model;

import java.math.BigDecimal;

public class CachePadraoAnalitico {

	private Integer idCacheFuncionario;
	private String nomeFuncionario;
	private BigDecimal valor;

	
	public Integer getIdCacheFuncionario() {
		return idCacheFuncionario;
	}
	public void setIdCacheFuncionario(Integer idCacheFuncionario) {
		this.idCacheFuncionario = idCacheFuncionario;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
