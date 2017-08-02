package br.com.sysloccOficial.financeiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BancosAnalitico {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idBanco;
	private String nomebanco;

	
	public Integer getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}
	public String getNomebanco() {
		return nomebanco;
	}
	public void setNomebanco(String nomebanco) {
		this.nomebanco = nomebanco;
	}
	
}
