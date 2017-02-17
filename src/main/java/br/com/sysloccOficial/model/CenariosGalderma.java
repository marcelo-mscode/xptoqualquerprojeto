package br.com.sysloccOficial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CenariosGalderma {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idCenarios;
	private Integer planilhaMae;
	private Integer planilhaFilha;
	private Integer cenarioFilha;
	
	
	
	public Integer getIdCenarios() {
		return idCenarios;
	}
	public void setIdCenarios(Integer idCenarios) {
		this.idCenarios = idCenarios;
	}
	public Integer getPlanilhaMae() {
		return planilhaMae;
	}
	public void setPlanilhaMae(Integer planilhaMae) {
		this.planilhaMae = planilhaMae;
	}
	public Integer getPlanilhaFilha() {
		return planilhaFilha;
	}
	public void setPlanilhaFilha(Integer planilhaFilha) {
		this.planilhaFilha = planilhaFilha;
	}
	public Integer getCenarioFilha() {
		return cenarioFilha;
	}
	public void setCenarioFilha(Integer cenarioFilha) {
		this.cenarioFilha = cenarioFilha;
	}
	
}
