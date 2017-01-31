package br.com.sysloccOficial.financeiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




@Entity
public class DespesasAuxiliar {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idDespesasAux;
	private String descricaoAux;
	
	
	public String getDescricaoAux() {
		return descricaoAux;
	}

	public void setDescricaoAux(String descricaoAux) {
		this.descricaoAux = descricaoAux;
	}

	public Integer getIdDespesasAux() {
		return idDespesasAux;
	}

	public void setIdDespesasAux(Integer idDespesasAux) {
		this.idDespesasAux = idDespesasAux;
	}
	
	
	
	
	
	
}
