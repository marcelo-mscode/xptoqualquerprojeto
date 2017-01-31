package br.com.sysloccOficial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GrupoCategoriaBayer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idGrupoCategoria;
	private String categoria;
	
	
// --------------------------------------------------------------------- //	

	public Integer getIdGrupoCategoria() {
		return idGrupoCategoria;
	}
	public void setIdGrupoCategoria(Integer idGrupoCategoria) {
		this.idGrupoCategoria = idGrupoCategoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
	
	
	
}
