package br.com.sysloccOficial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sysloccOficial.model.producao.ProducaoP;

@Entity
public class Lista implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Integer idLista;
	private Integer concluido;	
	private Integer revisao;		
	
	private Integer numCenarioGalderma;
	
	
	private String lista;
	private String listaCod;	
	private String observacoes;
	private String formaPagamento;
	private String infoConsolidadoGalderma;
	
	private BigDecimal margemPadrao;
	private BigDecimal administracao;
	private BigDecimal feeReduzido;
	
	private BigDecimal SubTotalCusto;
	private BigDecimal SubTotalVendaNaoIncideImposto;
	private BigDecimal subTotalVendaIncideImposto;
	private BigDecimal administracaoValor;
	private BigDecimal impostoValor;
	private BigDecimal valorTotal;
	private BigDecimal ListaBvFornecedorValor;
	
// -------------- Transientes -------------------------- //
	@Transient private Integer idJobLista;
	@Transient private Integer idListaTransiente;
	@Transient private Integer listaEstatus;
	@Transient private String feeReduzidoTransiente;
	@Transient private String administracaoTransiente;
	
	
// -------------- Relacionamentos ---------------------- //
	@ManyToOne @JoinColumn(name="idJob") private Job idJob;
	@ManyToOne @JoinColumn(name="criadoPorUser") private User user;
	@ManyToOne @JoinColumn(name="criadoPor") private Usuario usuario;
	@ManyToOne @JoinColumn(name="aprovadoPor") private Usuario usuarioAprova;
	@ManyToOne @JoinColumn(name="idlistaEstatus") private ListaEstatus idlistaEstatus;
	@OneToOne(mappedBy="listaProducao", fetch = FetchType.LAZY) CriacaoLista criacaoLista;
	@OneToMany(mappedBy="lista") List<ProducaoP> producaoP;
	@OneToOne(mappedBy="lista") InfoInterna infoInterna;
	
	//	@OneToMany(mappedBy="idLista") private List<Grupo> grupo;
	

// ------------------- Datas --------------------------- //
	@Temporal(TemporalType.TIMESTAMP) private Calendar dataCriacao;
	@Temporal(TemporalType.TIMESTAMP) private Calendar dataAprovacao;
	@Temporal(TemporalType.TIMESTAMP) private Calendar dataDoEvento;
// ----------------------------------------------------- //	
	
	public Integer getIdLista() {
		return idLista;
	}
	public Calendar getDataDoEvento() {
		return dataDoEvento;
	}
	public void setDataDoEvento(Calendar dataDoEvento) {
		this.dataDoEvento = dataDoEvento;
	}
	public String getInfoConsolidadoGalderma() {
		return infoConsolidadoGalderma;
	}
	public void setInfoConsolidadoGalderma(String infoConsolidadoGalderma) {
		this.infoConsolidadoGalderma = infoConsolidadoGalderma;
	}
	public Integer getNumCenarioGalderma() {
		return numCenarioGalderma;
	}
	public void setNumCenarioGalderma(Integer numCenarioGalderma) {
		this.numCenarioGalderma = numCenarioGalderma;
	}
	public String getAdministracaoTransiente() {
		return administracaoTransiente;
	}
	public void setAdministracaoTransiente(String administracaoTransiente) {
		this.administracaoTransiente = administracaoTransiente;
	}
	public String getFeeReduzidoTransiente() {
		return feeReduzidoTransiente;
	}
	public void setFeeReduzidoTransiente(String feeReduzidoTransiente) {
		this.feeReduzidoTransiente = feeReduzidoTransiente;
	}
	public BigDecimal getFeeReduzido() {
		return feeReduzido;
	}
	public void setFeeReduzido(BigDecimal feeReduzido) {
		this.feeReduzido = feeReduzido;
	}
	public InfoInterna getInfoInterna() {
		return infoInterna;
	}
	public void setInfoInterna(InfoInterna infoInterna) {
		this.infoInterna = infoInterna;
	}
	public List<ProducaoP> getProducaoP() {
		return producaoP;
	}
	public void setProducaoP(List<ProducaoP> producaoP) {
		this.producaoP = producaoP;
	}
	public CriacaoLista getCriacaoLista() {
		return criacaoLista;
	}
	public void setCriacaoLista(CriacaoLista criacaoLista) {
		this.criacaoLista = criacaoLista;
	}
	public Usuario getUsuarioAprova() {
		return usuarioAprova;
	}
	public void setUsuarioAprova(Usuario usuarioAprova) {
		this.usuarioAprova = usuarioAprova;
	}
	public Calendar getDataAprovacao() {
		return dataAprovacao;
	}
	public void setDataAprovacao(Calendar dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}
	/*public List<Grupo> getGrupo() {
		return grupo;
	}
	public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}*/
	public ListaEstatus getIdlistaEstatus() {
		return idlistaEstatus;
	}
	public void setIdlistaEstatus(ListaEstatus idlistaEstatus) {
		this.idlistaEstatus = idlistaEstatus;
	}
	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}
	
	public Integer getIdJobLista() {
		return idJobLista;
	}
	public void setIdJobLista(Integer idJobLista) {
		this.idJobLista = idJobLista;
	}
	
	public String getLista() {
		return lista;
	}
	public void setLista(String lista) {
		this.lista = lista;
	}
	public Job getIdJob() {
		return idJob;
	}
	public void setIdJob(Job idJob) {
		this.idJob = idJob;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Integer getListaEstatus() {
		return listaEstatus;
	}
	public void setListaEstatus(Integer listaEstatus) {
		this.listaEstatus = listaEstatus;
	}
	public BigDecimal getMargemPadrao() {
		return margemPadrao;
	}
	public void setMargemPadrao(BigDecimal margemPadrao) {
		this.margemPadrao = margemPadrao;
	}
	public BigDecimal getAdministracao() {
		return administracao;
	}
	public void setAdministracao(BigDecimal administracao) {
		this.administracao = administracao;
	}
	public BigDecimal getSubTotalCusto() {
		return SubTotalCusto;
	}
	public void setSubTotalCusto(BigDecimal subTotalCusto) {
		SubTotalCusto = subTotalCusto;
	}
	public BigDecimal getSubTotalVendaNaoIncideImposto() {
		return SubTotalVendaNaoIncideImposto;
	}
	public void setSubTotalVendaNaoIncideImposto(
			BigDecimal subTotalVendaNaoIncideImposto) {
		SubTotalVendaNaoIncideImposto = subTotalVendaNaoIncideImposto;
	}
	public BigDecimal getSubTotalVendaIncideImposto() {
		return subTotalVendaIncideImposto;
	}
	public void setSubTotalVendaIncideImposto(BigDecimal subTotalVendaIncideImposto) {
		this.subTotalVendaIncideImposto = subTotalVendaIncideImposto;
	}
	public BigDecimal getAdministracaoValor() {
		return administracaoValor;
	}
	public void setAdministracaoValor(BigDecimal administracaoValor) {
		this.administracaoValor = administracaoValor;
	}
	public BigDecimal getImpostoValor() {
		return impostoValor;
	}
	public void setImpostoValor(BigDecimal impostoValor) {
		this.impostoValor = impostoValor;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getListaBvFornecedorValor() {
		return ListaBvFornecedorValor;
	}
	public void setListaBvFornecedorValor(BigDecimal listaBvFornecedorValor) {
		ListaBvFornecedorValor = listaBvFornecedorValor;
	}
	public Calendar getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Integer getConcluido() {
		return concluido;
	}
	public void setConcluido(Integer concluido) {
		this.concluido = concluido;
	}
	public Integer getRevisao() {
		return revisao;
	}
	public void setRevisao(Integer revisao) {
		this.revisao = revisao;
	}
	public String getListaCod() {
		return listaCod;
	}
	public void setListaCod(String listaCod) {
		this.listaCod = listaCod;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getIdListaTransiente() {
		return idListaTransiente;
	}
	public void setIdListaTransiente(Integer idListaTransiente) {
		this.idListaTransiente = idListaTransiente;
	}
	
	
// ------------------------------------------- //	
	
	
	
	
	
}
