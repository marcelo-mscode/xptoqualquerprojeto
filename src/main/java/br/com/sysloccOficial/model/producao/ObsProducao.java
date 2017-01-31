package br.com.sysloccOficial.model.producao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class ObsProducao {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idObs;
	private String obs;
	
// ------------------------------------ //
	
	@OneToOne @JoinColumn(name="producaoP") private ProducaoP producaoP;
	
// ------------------------------------ //
	
	public Integer getIdObs() {
		return idObs;
	}
	public ProducaoP getProducaoP() {
		return producaoP;
	}
	public void setProducaoP(ProducaoP producaoP) {
		this.producaoP = producaoP;
	}
	public void setIdObs(Integer idObs) {
		this.idObs = idObs;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
	
}
