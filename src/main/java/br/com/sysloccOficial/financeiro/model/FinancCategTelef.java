package br.com.sysloccOficial.financeiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class FinancCategTelef {
	

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idFinancCategTelef;
	private String tipoTelefone;
	private String semCategoria;
	
// ------------------------------------------------------ //
	/*@OneToOne (mappedBy="categoria") private FinancTelefone telefone;*/
// ------------------------------------------------------ //
	
	public Integer getIdFinancCategTelef() {
		return idFinancCategTelef;
	}
	/*public FinancTelefone getTelefone() {
		return telefone;
	}
	public void setTelefone(FinancTelefone telefone) {
		this.telefone = telefone;
	}*/
	public void setIdFinancCategTelef(Integer idFinancCategTelef) {
		this.idFinancCategTelef = idFinancCategTelef;
	}
	public String getTipoTelefone() {
		return tipoTelefone;
	}
	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
	public String getSemCategoria() {
		return semCategoria;
	}
	public void setSemCategoria(String semCategoria) {
		this.semCategoria = semCategoria;
	}
	
	
	
	
}
