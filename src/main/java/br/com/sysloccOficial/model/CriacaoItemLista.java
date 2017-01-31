package br.com.sysloccOficial.model;

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

@Entity
public class CriacaoItemLista {
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCriacaoItemLista;
	private String tituloItem;
	private String informaoesItem;
	private String obs;
	private String infoUteis;
	private Integer versao;
	private Integer quantidade;
	
	private String tempoTotalItem;

	private boolean dependencia;

	private boolean item3D;         // Indica que um item é 3D
	private boolean demandaInterna; // Item usado para identificar que o item é uma demanda interna
	private boolean itemDelegado;   // atributo será usado para verificar quando um item for delegado por alguém 

	
	
	private Integer apres;
	
// ------------------- Relacionamentos ------------------------------ // 	
	@ManyToOne @JoinColumn(name="idCriacaoLista") private CriacaoLista criacaoLista;
	@OneToOne @JoinColumn(name="IdGrupo") private Grupo grupo;
	@ManyToOne @JoinColumn(name="idCriacaoItemStatus") private CriacaoItemStatus criacaoItemStatus;
	@OneToOne @JoinColumn(name="liderCriacao") Usuario liderCriacao; 
 	@OneToOne @JoinColumn(name="responsavelItem") Usuario responsavelItem;
 	@OneToOne @JoinColumn(name="criadoPor") Usuario criadoPor;
 	@OneToMany(mappedBy="criacaoItemLista")private List<CriacaoItemHistorico> criacaoItemHistorico;

 	@OneToMany(mappedBy="criacaoItemLista", fetch = FetchType.EAGER)private List<CriacaoItemPendencia> criacaoItemPendencia; 
 	
 	
// ----------------------- Datas ------------------------------------ //		
	@Temporal(TemporalType.TIMESTAMP) private Calendar dataCriacao;
	@Temporal(TemporalType.TIMESTAMP) private Calendar inicio;
	@Temporal(TemporalType.TIMESTAMP) private Calendar termino;
// -------------------- Transientes -------------------------------- //	
	@Transient private Integer criacaoItemStatusTransiente;
	@Transient private Integer responsavelItemTransiente;
	@Transient private String historicoPendenciaTransiente;
	@Transient private Integer criacaoItemHistoricoTransiente;
	@Transient private Integer idCriacaoListaTransiente;
	@Transient private Integer idGrupoTransiente;
	@Transient private Integer quantidadeTransiente;
	
// ------------------------------------------------------------------ //	
	
	
	public Integer getIdCriacaoItemLista() {
		return idCriacaoItemLista;
	}

	public String getTempoTotalItem() {
		return tempoTotalItem;
	}

	public void setTempoTotalItem(String tempoTotalItem) {
		this.tempoTotalItem = tempoTotalItem;
	}

	public boolean isItem3D() {
		return item3D;
	}

	public void setItem3D(boolean item3d) {
		item3D = item3d;
	}

	public Usuario getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}

	public boolean isDemandaInterna() {
		return demandaInterna;
	}

	public void setDemandaInterna(boolean demandaInterna) {
		this.demandaInterna = demandaInterna;
	}

	public boolean isItemDelegado() {
		return itemDelegado;
	}

	public void setItemDelegado(boolean itemDelegado) {
		this.itemDelegado = itemDelegado;
	}

	public List<CriacaoItemPendencia> getCriacaoItemPendencia() {
		return criacaoItemPendencia;
	}

	public void setCriacaoItemPendencia(
			List<CriacaoItemPendencia> criacaoItemPendencia) {
		this.criacaoItemPendencia = criacaoItemPendencia;
	}

	public Integer getApres() {
		return apres;
	}

	public Integer getQuantidadeTransiente() {
		return quantidadeTransiente;
	}

	public void setQuantidadeTransiente(Integer quantidadeTransiente) {
		this.quantidadeTransiente = quantidadeTransiente;
	}

	public Integer isApres() {
		return apres;
	}

	public void setApres(Integer apres) {
		this.apres = apres;
	}

	public Integer getIdGrupoTransiente() {
		return idGrupoTransiente;
	}

	public void setIdGrupoTransiente(Integer idGrupoTransiente) {
		this.idGrupoTransiente = idGrupoTransiente;
	}

	public Integer getIdCriacaoListaTransiente() {
		return idCriacaoListaTransiente;
	}

	public void setIdCriacaoListaTransiente(Integer idCriacaoListaTransiente) {
		this.idCriacaoListaTransiente = idCriacaoListaTransiente;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getInfoUteis() {
		return infoUteis;
	}

	public void setInfoUteis(String infoUteis) {
		this.infoUteis = infoUteis;
	}

	public Integer getCriacaoItemHistoricoTransiente() {
		return criacaoItemHistoricoTransiente;
	}

	public void setCriacaoItemHistoricoTransiente(
			Integer criacaoItemHistoricoTransiente) {
		this.criacaoItemHistoricoTransiente = criacaoItemHistoricoTransiente;
	}

	public List<CriacaoItemHistorico> getCriacaoItemHistorico() {
		return criacaoItemHistorico;
	}

	public void setCriacaoItemHistorico(
			List<CriacaoItemHistorico> criacaoItemHistorico) {
		this.criacaoItemHistorico = criacaoItemHistorico;
	}

	public String getHistoricoPendenciaTransiente() {
		return historicoPendenciaTransiente;
	}

	public void setHistoricoPendenciaTransiente(String historicoPendenciaTransiente) {
		this.historicoPendenciaTransiente = historicoPendenciaTransiente;
	}

	public boolean isDependencia() {
		return dependencia;
	}

	public void setDependencia(boolean dependencia) {
		this.dependencia = dependencia;
	}

	public Integer getResponsavelItemTransiente() {
		return responsavelItemTransiente;
	}

	public void setResponsavelItemTransiente(Integer responsavelItemTransiente) {
		this.responsavelItemTransiente = responsavelItemTransiente;
	}

	public Usuario getLiderCriacao() {
		return liderCriacao;
	}

	public void setLiderCriacao(Usuario liderCriacao) {
		this.liderCriacao = liderCriacao;
	}

	public Usuario getResponsavelItem() {
		return responsavelItem;
	}

	public void setResponsavelItem(Usuario responsavelItem) {
		this.responsavelItem = responsavelItem;
	}

	public Integer getCriacaoItemStatusTransiente() {
		return criacaoItemStatusTransiente;
	}

	public void setCriacaoItemStatusTransiente(Integer criacaoItemStatusTransiente) {
		this.criacaoItemStatusTransiente = criacaoItemStatusTransiente;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Calendar getInicio() {
		return inicio;
	}

	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}

	public Calendar getTermino() {
		return termino;
	}

	public void setTermino(Calendar termino) {
		this.termino = termino;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public CriacaoItemStatus getCriacaoItemStatus() {
		return criacaoItemStatus;
	}

	public void setCriacaoItemStatus(CriacaoItemStatus criacaoItemStatus) {
		this.criacaoItemStatus = criacaoItemStatus;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getInformaoesItem() {
		return informaoesItem;
	}

	public void setInformaoesItem(String informaoesItem) {
		this.informaoesItem = informaoesItem;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void setIdCriacaoItemLista(Integer idCriacaoItemLista) {
		this.idCriacaoItemLista = idCriacaoItemLista;
	}

	public String getTituloItem() {
		return tituloItem;
	}

	public void setTituloItem(String tituloItem) {
		this.tituloItem = tituloItem;
	}

	public CriacaoLista getCriacaoLista() {
		return criacaoLista;
	}

	public void setCriacaoLista(CriacaoLista criacaoLista) {
		this.criacaoLista = criacaoLista;
	}
	
// ----------------------- Datas ------------------------------------ //	
	
// -------------------- Transientes -------------------------------- //	
	
// ----------------------------------------------------------------- //
	
	
	
	
}
