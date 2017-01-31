package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
public class Produto implements Comparable<Produto>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproduto;
	private String produto;
	private String produtoDescricao;
	private boolean Habilitado;
	private BigDecimal custoPadrao;
	private BigDecimal vendaPadrao;

// --------	Transientes ------------------ //
	@Transient private Integer idUnid;
	@Transient private Integer idGenero;
	@Transient private Integer idprodutoAtuacao;
	@Transient private Integer idAtuacao;
	@Transient private Integer idGrupo;
	@Transient private String  idAtuacaoEmLista;

	
// ------------ Datas ------------------- //  
	@Temporal(TemporalType.TIMESTAMP)
	@JoinColumn(name="dataAtualizado")
	private Calendar  dataAtualizado;

// --------- Relacionamentos ------------ //	
	@ManyToOne @JoinColumn(name="idUnidade") private Unidade unidade;
	@ManyToOne @JoinColumn(name="idGenero")	private Genero genero;
	@OneToMany(mappedBy="idProduto") private List<ProdutoAtuacao> produtoAtuacao;
	@OneToMany(mappedBy="idProduto", cascade = CascadeType.ALL)	private List<MarcaProduto> marcaProduto;
	//@OneToMany private List<Atuacao> atuacao;

	
	// select p from Produto p join fetch p.produtoAtuacao a where a.idAtuacao = 3 order by p.produto desc;
	
	@ManyToMany @JoinTable(name="ProdutoAtuacao",
			joinColumns = @JoinColumn(name="idProduto"),
			inverseJoinColumns = @JoinColumn(name="idAtuacao"))
	private List<Atuacao> listaDeAtuacao;
	
	@OneToMany(mappedBy="produto") private List<ProdutoGrupo> produtogrupo;
	
// -------------------------------------- //
	
	
	
	public Integer getIdproduto() {
		return idproduto;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getIdAtuacaoEmLista() {
		return idAtuacaoEmLista;
	}
	public void setIdAtuacaoEmLista(String idAtuacaoEmLista) {
		this.idAtuacaoEmLista = idAtuacaoEmLista;
	}
	public List<ProdutoGrupo> getProdutogrupo() {
		return produtogrupo;
	}
	public void setProdutogrupo(List<ProdutoGrupo> produtogrupo) {
		this.produtogrupo = produtogrupo;
	}
	public List<Atuacao> getListaDeAtuacao() {
		return listaDeAtuacao;
	}
	public void setListaDeAtuacao(List<Atuacao> listaDeAtuacao) {
		this.listaDeAtuacao = listaDeAtuacao;
	}
	public List<MarcaProduto> getMarcaProduto() {
		return marcaProduto;
	}
	public void setMarcaProduto(List<MarcaProduto> marcaProduto) {
		this.marcaProduto = marcaProduto;
	}
	/*public List<Atuacao> getAtuacao() {
		return atuacao;
	}
	public void setAtuacao(List<Atuacao> atuacao) {
		this.atuacao = atuacao;
	}*/
	public List<ProdutoAtuacao> getProdutoAtuacao() {
		return produtoAtuacao;
	}
	public void setProdutoAtuacao(List<ProdutoAtuacao> produtoAtuacao) {
		this.produtoAtuacao = produtoAtuacao;
	}
	public Integer getIdprodutoAtuacao() {
		return idprodutoAtuacao;
	}
	public void setIdprodutoAtuacao(Integer idprodutoAtuacao) {
		this.idprodutoAtuacao = idprodutoAtuacao;
	}
	public Integer getIdAtuacao() {
		return idAtuacao;
	}
	public void setIdAtuacao(Integer idAtuacao) {
		this.idAtuacao = idAtuacao;
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
		return produtoDescricao;
	}
	public void setProdutodescricao(String produtodescricao) {
		this.produtoDescricao = produtodescricao;
	}
	public boolean isHabilitado() {
		return Habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.Habilitado = habilitado;
	}
	public BigDecimal getCustoPadrao() {
		return custoPadrao;
	}
	public void setCustoPadrao(BigDecimal custoPadrao) {
		this.custoPadrao = custoPadrao;
	}
	public BigDecimal getPrecoPadrao() {
		return vendaPadrao;
	}
	public void setPrecoPadrao(BigDecimal precoPadrao) {
		this.vendaPadrao = precoPadrao;
	}
	
	public Integer getIdUnid() {
		return idUnid;
	}
	public void setIdUnid(Integer idUnid) {
		this.idUnid = idUnid;
	}
	public Integer getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}
	
	public Calendar getDataAtualizacao() {
		return dataAtualizado;
	}
	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizado = dataAtualizacao;
	}
	
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	
	
	
	@Override
	public int compareTo(Produto o) {
		
		
		if (this.produto == o.produto) {
		      return -1;
		    }

		    if (this.produto != o.produto) {
		      return 1;
		    }

		    return 0;
		  
	}

	
}
