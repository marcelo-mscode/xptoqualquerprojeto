package br.com.sysloccOficial.financeiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DespesasFinanc {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idDespesasFinanc;
	private String desc;
	
	
	public Integer getIdDespesasFinanc() {
		return idDespesasFinanc;
	}
	public void setIdDespesasFinanc(Integer idDespesasFinanc) {
		this.idDespesasFinanc = idDespesasFinanc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
