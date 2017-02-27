package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Grupo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idgrupo;
	private String grupo;
	private String informacoes;
	private BigDecimal grupoValorNaoIncideImposto;
	private BigDecimal grupoValorIncideImposto;
	private BigDecimal grupoCusto;
	private BigDecimal grupoAdministracaoValor;
	private BigDecimal grupoFeeReduzido;
	private BigDecimal grupoImpostoValor;
	private String necessidades;
	private String categoria;
	private String grupoCod;
	private boolean opcional;
	private boolean opcionalGalderma;
	private boolean incideAdministracao;
	private boolean feeReduzido;
	private boolean criacao;
	private Integer ordemGrupo;
	private double txServico;
	private double txISS;
	
	
	
// ------------- Transientes ----------------//
	@Transient private Integer idListaTransiente; 
	@Transient private Integer idCategoriaTransiente; 
	@Transient private Integer idgrupoCategoriaBayerTransiente; 
	@Transient private Integer idCategoriaGaldermaTransiente; 
	
// --------- Relacionamentos -------------- //	
	

	@ManyToOne @JoinColumn(name="idCategoria") private Categoria idCategoria;
	@OneToMany(mappedBy="idGrupo", fetch = FetchType.EAGER) private List<ProdutoGrupo> produtoGrupo;
	@ManyToOne @JoinColumn(name="idLista")  private Lista idLista;
	
	@OneToOne @JoinColumn(name="categoriaBayer")private GrupoCategoriaBayer grupoCategoriaBayer;
	@OneToOne @JoinColumn(name="categoriaGalderma")private GrupoCategoriaGalderma grupoCategoriaGalderma;
	
	@OneToOne(mappedBy="grupo",cascade = CascadeType.REMOVE)private DeterminaQuantidades determ;
	@OneToOne(mappedBy="grupo",cascade = CascadeType.REMOVE)private DeterQuantpadrao determPadrao;

// ---------------------------------------- //

	public Integer getIdCategoriaGaldermaTransiente() {
		return idCategoriaGaldermaTransiente;
	}
	
	public double getTxServico() {
		return txServico;
	}

	public void setTxServico(double txServico) {
		this.txServico = txServico;
	}

	public double getTxISS() {
		return txISS;
	}

	public void setTxISS(double txISS) {
		this.txISS = txISS;
	}

	public boolean isOpcionalGalderma() {
		return opcionalGalderma;
	}

	public void setOpcionalGalderma(boolean opcionalGalderma) {
		this.opcionalGalderma = opcionalGalderma;
	}

	public void setIdCategoriaGaldermaTransiente(
			Integer idCategoriaGaldermaTransiente) {
		this.idCategoriaGaldermaTransiente = idCategoriaGaldermaTransiente;
	}
	
	public Integer getIdgrupo() {
		return idgrupo;
	}

	public GrupoCategoriaGalderma getGrupoCategoriaGalderma() {
		return grupoCategoriaGalderma;
	}

	public void setGrupoCategoriaGalderma(GrupoCategoriaGalderma grupoCategoriaGalderma) {
		this.grupoCategoriaGalderma = grupoCategoriaGalderma;
	}

	public BigDecimal getGrupoFeeReduzido() {
		return grupoFeeReduzido;
	}

	public void setGrupoFeeReduzido(BigDecimal grupoFeeReduzido) {
		this.grupoFeeReduzido = grupoFeeReduzido;
	}

	public Integer getIdgrupoCategoriaBayerTransiente() {
		return idgrupoCategoriaBayerTransiente;
	}

	public void setIdgrupoCategoriaBayerTransiente(
			Integer idgrupoCategoriaBayerTransiente) {
		this.idgrupoCategoriaBayerTransiente = idgrupoCategoriaBayerTransiente;
	}

	public GrupoCategoriaBayer getGrupoCategoriaBayer() {
		return grupoCategoriaBayer;
	}

	public void setGrupoCategoriaBayer(GrupoCategoriaBayer grupoCategoriaBayer) {
		this.grupoCategoriaBayer = grupoCategoriaBayer;
	}

	public boolean isFeeReduzido() {
		return feeReduzido;
	}

	public void setFeeReduzido(boolean feeReduzido) {
		this.feeReduzido = feeReduzido;
	}

	public DeterQuantpadrao getDetermPadrao() {
		return determPadrao;
	}

	public void setDetermPadrao(DeterQuantpadrao determPadrao) {
		this.determPadrao = determPadrao;
	}

	public DeterminaQuantidades getDeterm() {
		return determ;
	}

	public void setDeterm(DeterminaQuantidades determ) {
		this.determ = determ;
	}

	public boolean isCriacao() {
		return criacao;
	}

	public void setCriacao(boolean criacao) {
		this.criacao = criacao;
	}

	public String getGrupoCod() {
		return grupoCod;
	}

	public void setGrupoCod(String grupoCod) {
		this.grupoCod = grupoCod;
	}

	public BigDecimal getGrupoImpostoValor() {
		return grupoImpostoValor;
	}

	public void setGrupoImpostoValor(BigDecimal grupoImpostoValor) {
		this.grupoImpostoValor = grupoImpostoValor;
	}

	public BigDecimal getGrupoAdministracaoValor() {
		return grupoAdministracaoValor;
	}

	public void setGrupoAdministracaoValor(BigDecimal grupoAdministracaoValor) {
		this.grupoAdministracaoValor = grupoAdministracaoValor;
	}

	public BigDecimal getGrupoCusto() {
		return grupoCusto;
	}

	public void setGrupoCusto(BigDecimal grupoCusto) {
		this.grupoCusto = grupoCusto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getIdListaTransiente() {
		return idListaTransiente;
	}

	public void setIdListaTransiente(Integer idListaTransiente) {
		this.idListaTransiente = idListaTransiente;
	}

	public Integer getIdCategoriaTransiente() {
		return idCategoriaTransiente;
	}

	public void setIdCategoriaTransiente(Integer idCategoriaTransiente) {
		this.idCategoriaTransiente = idCategoriaTransiente;
	}

	public Lista getIdLista() {
		return idLista;
	}

	public void setIdLista(Lista idLista) {
		this.idLista = idLista;
	}

	public boolean isIncideAdministracao() {
		return incideAdministracao;
	}

	public void setIncideAdministracao(boolean incideAdministracao) {
		this.incideAdministracao = incideAdministracao;
	}

	public List<ProdutoGrupo> getProdutoGrupo() {
		return produtoGrupo;
	}

	public void setProdutoGrupo(List<ProdutoGrupo> produtoGrupo) {
		this.produtoGrupo = produtoGrupo;
	}

	public boolean isOpcional() {
		return opcional;
	}

	public void setOpcional(boolean opcional) {
		this.opcional = opcional;
	}

	public BigDecimal getGrupoValorIncideImposto() {
		return grupoValorIncideImposto;
	}

	public void setGrupoValorIncideImposto(BigDecimal grupoValorIncideImposto) {
		this.grupoValorIncideImposto = grupoValorIncideImposto;
	}

	public String getNecessidades() {
		return necessidades;
	}

	public void setNecessidades(String necessidades) {
		this.necessidades = necessidades;
	}

	public BigDecimal getGrupoValorNaoIncideImposto() {
		return grupoValorNaoIncideImposto;
	}

	public void setGrupoValorNaoIncideImposto(BigDecimal grupoValorNaoIncideImposto) {
		this.grupoValorNaoIncideImposto = grupoValorNaoIncideImposto;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public void setIdgrupo(Integer idgrupo) {
		this.idgrupo = idgrupo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public Categoria getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getOrdemGrupo() {
		return ordemGrupo;
	}

	public void setOrdemGrupo(Integer ordemGrupo) {
		this.ordemGrupo = ordemGrupo;
	}

	
	
	
	
	
}
