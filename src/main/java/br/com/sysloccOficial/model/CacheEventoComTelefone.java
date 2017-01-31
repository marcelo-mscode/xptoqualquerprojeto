package br.com.sysloccOficial.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CacheEventoComTelefone {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idCacheEventoComTelef;
	private BigDecimal valor;
	
	
	
	
	
// -------------------------------------------------------------------------- //	
	
	public Integer getIdCacheEventoComTelef() {
		return idCacheEventoComTelef;
	}
	public void setIdCacheEventoComTelef(Integer idCacheEventoComTelef) {
		this.idCacheEventoComTelef = idCacheEventoComTelef;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
	
	
	
	
}
