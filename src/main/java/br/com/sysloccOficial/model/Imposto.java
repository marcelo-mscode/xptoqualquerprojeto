package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Imposto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idimposto;
	private String  impostoTitulo;
	private BigDecimal imposto;
	
// --------------------- Relacionamentos --------------------- //
	
	@OneToMany(mappedBy="idImposto") private List<Categoria> categoria;
	

// ---------------------------------------------------------- //	
	
	
	public Integer getIdimposto() {
		return idimposto;
	}
	public List<Categoria> getCategoria() {
		return categoria;
	}
	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}
	public void setIdimposto(Integer idimposto) {
		this.idimposto = idimposto;
	}
	public String getImpostoTitulo() {
		return impostoTitulo;
	}
	public void setImpostoTitulo(String impostoTitulo) {
		this.impostoTitulo = impostoTitulo;
	}
	public BigDecimal getImposto() {
		return imposto;
	}
	public void setImposto(BigDecimal imposto) {
		this.imposto = imposto;
	}
}
