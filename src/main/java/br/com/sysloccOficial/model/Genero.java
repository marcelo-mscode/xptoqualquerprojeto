package br.com.sysloccOficial.model;

import java.util.List;

import javax.persistence.*;


@Entity
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idgenero;
	private String genero;
	
	@OneToMany(mappedBy = "genero")
	private List<Produto> produto;
		
	public List<Produto> getProdutos() {
		return produto;
	}
	public void setProdutos(List<Produto> produto) {
		this.produto = produto;
	}
	
	
	public Integer getIdgenero() {
		return idgenero;
	}
	public void setIdgenero(Integer idgenero) {
		this.idgenero = idgenero;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
}
