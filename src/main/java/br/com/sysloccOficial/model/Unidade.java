package br.com.sysloccOficial.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Unidade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7057471431569962739L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idunidade;
	private String unidade;
	
	@OneToMany(mappedBy = "unidade")
	private List<Produto> produto;
	
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	
	
	public Integer getId() {
		return idunidade;
	}
	public void setId(Integer id) {
		this.idunidade = id;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	
	
	
	
	
	
}
