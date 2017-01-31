package br.com.sysloccOficial.model.prospeccao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DespTesteFinanc {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer iddesp;
	private String desc;

	
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getIddesp() {
		return iddesp;
	}

	public void setIddesp(Integer iddesp) {
		this.iddesp = iddesp;
	}
	
	
	
	
	
}
