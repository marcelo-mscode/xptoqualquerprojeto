package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class Produto_old {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproduto;
	private String produto;
	private String produtodescricao;
	private boolean habilitado;
	private BigDecimal custoPadrao;
	private BigDecimal precoPadrao;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAtualizacao;
	
	public Calendar getData() {
		return dataAtualizacao;
	}
	public void setData(Calendar data) {
		this.dataAtualizacao = data;
	}



	@ManyToMany
	@JoinTable(name="produtos_unidades",
		joinColumns = @JoinColumn (name= "produto_idproduto"),
		inverseJoinColumns = @JoinColumn(name="unidade_id"))
	private List<Unidade> unidade;
	
	public List<Unidade> getUnidade() {
		return unidade;
	}
	public void setUnidade(List<Unidade> unidade) {
		this.unidade = unidade;
	}
	
	
	
	@ManyToMany
	@JoinTable(name="produtos_generos",
		joinColumns = @JoinColumn (name= "produto_idproduto"),
		inverseJoinColumns = @JoinColumn(name="genero_id"))
	private List<Genero> genero;
	
	
	public List<Genero> getGenero() {
		return genero;
	}
	public void setGenero(List<Genero> genero) {
		this.genero = genero;
	}
	
	
	public Integer getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(Integer idproduto) {
		this.idproduto = idproduto;
	}
	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getProdutodescricao() {
		return produtodescricao;
	}

	public void setProdutodescricao(String produtodescricao) {
		this.produtodescricao = produtodescricao;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public BigDecimal getCustoPadrao() {
		return custoPadrao;
	}
	public void setCustoPadrao(BigDecimal custoPadrao) {
		this.custoPadrao = custoPadrao;
	}
	
	public BigDecimal getPrecoPadrao() {
		return precoPadrao;
	}
	public void setPrecoPadrao(BigDecimal precoPadrao) {
		this.precoPadrao = precoPadrao;
	}
	
}
