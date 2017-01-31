package br.com.sysloccOficial.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProdutoAtuacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idprodutoAtuacao;

	
// --------- Relacionamentos ------------ //	
	
	@ManyToOne @JoinColumn(name="idAtuacao") private Atuacao idAtuacao;

	@ManyToOne @JoinColumn(name="idProduto") private Produto idProduto;
	
	@OneToMany@JoinColumn(name="idProduto")  private List<Produto> produtoAtucao;
	

// -------------------------------------- //
	public Integer getIdprodutoAtuacao() {
		return idprodutoAtuacao;
	}

	public List<Produto> getProdutoAtucao() {
		return produtoAtucao;
	}

	public void setProdutoAtucao(List<Produto> produtoAtucao) {
		this.produtoAtucao = produtoAtucao;
	}

	public void setIdprodutoAtuacao(Integer idprodutoAtuacao) {
		this.idprodutoAtuacao = idprodutoAtuacao;
	}

	public Atuacao getIdAtuacao() {
		return idAtuacao;
	}

	public void setIdAtuacao(Atuacao idAtuacao) {
		this.idAtuacao = idAtuacao;
	}

	public Produto getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}
	
}
