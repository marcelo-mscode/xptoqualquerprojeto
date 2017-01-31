package br.com.sysloccOficial.model.prospeccao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DespesasInternas {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer iddespinterna;
	
	private String desc;

	public Integer getIddespinterna() {
		return iddespinterna;
	}

	public void setIddespinterna(Integer iddespinterna) {
		this.iddespinterna = iddespinterna;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
	
	

}
