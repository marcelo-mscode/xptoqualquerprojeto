package br.com.sysloccOficial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class GrupoCategoriaGalderma {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCategoriaGalderma;
	private String categoria;
	
	public Integer getIdCategoriaGalderma() {
		return idCategoriaGalderma;
	}
	public void setIdCategoriaGalderma(Integer idCategoriaGalderma) {
		this.idCategoriaGalderma = idCategoriaGalderma;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}		

}
