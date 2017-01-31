package br.com.sysloccOficial.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CriacaoStatus {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCriacaoStatus;
	private String Status;
	
	
	@OneToMany(mappedBy="criacaoStatus")private List<CriacaoLista> criacaoLista;
	
	
	public List<CriacaoLista> getCriacaoLista() {
		return criacaoLista;
	}
	public void setCriacaoLista(List<CriacaoLista> criacaoLista) {
		this.criacaoLista = criacaoLista;
	}
	public Integer getIdCriacaoStatus() {
		return idCriacaoStatus;
	}
	public void setIdCriacaoStatus(Integer idCriacaoStatus) {
		this.idCriacaoStatus = idCriacaoStatus;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
	
	
	
	
}
