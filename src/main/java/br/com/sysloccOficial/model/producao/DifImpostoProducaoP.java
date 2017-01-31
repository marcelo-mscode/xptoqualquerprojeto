package br.com.sysloccOficial.model.producao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class DifImpostoProducaoP {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idDifImposto;
	private String obs;
	
// ------------------------------------ //
	@OneToOne @JoinColumn(name="producaoP") private ProducaoP producaoP;
// ------------------------------------ //

	public Integer getIdDifImposto() {
		return idDifImposto;
	}

	public void setIdDifImposto(Integer idDifImposto) {
		this.idDifImposto = idDifImposto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public ProducaoP getProducaoP() {
		return producaoP;
	}

	public void setProducaoP(ProducaoP producaoP) {
		this.producaoP = producaoP;
	}
	
}
