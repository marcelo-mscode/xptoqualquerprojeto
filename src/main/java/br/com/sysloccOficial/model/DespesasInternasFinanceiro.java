package br.com.sysloccOficial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DespesasInternasFinanceiro {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idDesp;
	private String desc;
	public Integer getIdDesp() {
		return idDesp;
	}
	public void setIdDesp(Integer idDesp) {
		this.idDesp = idDesp;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
	
	
}
