package br.com.sysloccOficial.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Idmarca;
	private String marca;
	
// ----------- Relacionamentos ---------- //	
	
	@OneToMany(mappedBy="idAtuacao", cascade = CascadeType.ALL)
	private List<ProdutoAtuacao> atuacaoProduto;
		
// -------------------------------------- //
			
	
	public Integer getIdmarca() {
		return Idmarca;
	}
	public List<ProdutoAtuacao> getAtuacaoProduto() {
		return atuacaoProduto;
	}
	public void setAtuacaoProduto(List<ProdutoAtuacao> atuacaoProduto) {
		this.atuacaoProduto = atuacaoProduto;
	}
	public void setIdmarca(Integer idmarca) {
		Idmarca = idmarca;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
	
	
	
}
