//
//
//Utilizado no sistema como o nome de TAGS
//
//

package br.com.sysloccOficial.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Atuacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAtuacao;
	private String atuacao;
	
// ----------- Relacionamentos ---------- //	
	
	@OneToMany(mappedBy="idAtuacao", cascade = CascadeType.ALL)
	private List<ProdutoAtuacao> atuacaoProduto;
	
	@ManyToMany(mappedBy="listaDeAtuacao", fetch = FetchType.EAGER)
	private List<Produto> listaDeProdutos;
	
// -------------------------------------- //
	@Transient private Integer idEmpresaTag;
	
// -------------------------------------- //
	
	public Integer getIdAtuacao() {
		return idAtuacao;
	}
	
	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}

	public Integer getIdEmpresaTag() {
		return idEmpresaTag;
	}

	public void setIdEmpresaTag(Integer idEmpresaTag) {
		this.idEmpresaTag = idEmpresaTag;
	}

	public List<ProdutoAtuacao> getAtuacaoProduto() {
		return atuacaoProduto;
	}

	public void setAtuacaoProduto(List<ProdutoAtuacao> atuacaoProduto) {
		this.atuacaoProduto = atuacaoProduto;
	}

	public void setIdAtuacao(Integer idAtuacao) {
		this.idAtuacao = idAtuacao;
	}
	public String getAtuacao() {
		return atuacao;
	}
	public void setAtuacao(String atuacao) {
		this.atuacao = atuacao;
	}
	

}
