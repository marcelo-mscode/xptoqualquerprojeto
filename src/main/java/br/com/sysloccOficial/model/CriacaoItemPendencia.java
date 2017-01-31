package br.com.sysloccOficial.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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
public class CriacaoItemPendencia {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdCriacaoItemHistoricoPendencia;
	private String descPendencia;
	private String obs;
	
	
// ------------------- Relacionamentos ------------------------------ //	
	@OneToOne @JoinColumn(name="responsavelItem") Usuario responsavelItem;
	@OneToOne @JoinColumn(name="enviadoPor") Usuario enviadoPor;
	@ManyToOne @JoinColumn(name="idCriacaoItem") CriacaoItemLista criacaoItemLista;
	@ManyToOne @JoinColumn(name="idCriacaoItemStatus") private CriacaoItemStatus criacaoItemStatus;
	@OneToMany(mappedBy="criacaoItemPendencia")private List<CriacaoItemPendenciaHistorico> criacaoItemPendenciaHistorico ;
	
// ----------------------- Datas ------------------------------------ //	
 	@Temporal(TemporalType.TIMESTAMP) private Calendar dataEnvioPendencia;
 	@Temporal(TemporalType.TIMESTAMP) private Date dataLimite;
 	@Temporal(TemporalType.TIMESTAMP) private Calendar dataPendenciaResolvida;
 	@Temporal(TemporalType.TIMESTAMP) private Calendar dataTermino;
 	@Temporal(TemporalType.TIMESTAMP) private Calendar dataInicio;
// -------------------- Transientes -------------------------------- //	

 	@Transient private Integer criacaoItemStatusTransiente;
 	@Transient private Integer IdCriacaoItemHistoricoPendenciaTransiente;
 	@Transient private Integer criacaoItemListaTransiente;
 	@Transient private String dataLimiteTransiente;
	@Transient private String HoraLimiteTransiente;
	@Transient private String obsTransiente;
	@Transient private Integer idItemHistoricoPendenciaTransiente;
 	
// ----------------------------------------------------------------- //	
		
	
		public Integer getIdCriacaoItemHistoricoPendencia() {
			return IdCriacaoItemHistoricoPendencia;
		}
		
		public Usuario getEnviadoPor() {
			return enviadoPor;
		}

		public void setEnviadoPor(Usuario enviadoPor) {
			this.enviadoPor = enviadoPor;
		}

		public Integer getIdItemHistoricoPendenciaTransiente() {
			return idItemHistoricoPendenciaTransiente;
		}

		public void setIdItemHistoricoPendenciaTransiente(
				Integer idItemHistoricoPendenciaTransiente) {
			this.idItemHistoricoPendenciaTransiente = idItemHistoricoPendenciaTransiente;
		}

		public String getObsTransiente() {
			return obsTransiente;
		}
		public void setObsTransiente(String obsTransiente) {
			this.obsTransiente = obsTransiente;
		}
		public Calendar getDataPendenciaResolvida() {
			return dataPendenciaResolvida;
		}
		public void setDataPendenciaResolvida(Calendar dataPendenciaResolvida) {
			this.dataPendenciaResolvida = dataPendenciaResolvida;
		}
		public List<CriacaoItemPendenciaHistorico> getCriacaoItemPendenciaHistorico() {
			return criacaoItemPendenciaHistorico;
		}
		public void setCriacaoItemPendenciaHistorico(
				List<CriacaoItemPendenciaHistorico> criacaoItemPendenciaHistorico) {
			this.criacaoItemPendenciaHistorico = criacaoItemPendenciaHistorico;
		}
		public String getDataLimiteTransiente() {
			return dataLimiteTransiente;
		}
		public void setDataLimiteTransiente(String dataLimiteTransiente) {
			this.dataLimiteTransiente = dataLimiteTransiente;
		}
		public String getHoraLimiteTransiente() {
			return HoraLimiteTransiente;
		}
		public void setHoraLimiteTransiente(String horaLimiteTransiente) {
			HoraLimiteTransiente = horaLimiteTransiente;
		}
		public Date getDataLimite() {
			return dataLimite;
		}
		public void setDataLimite(Date dataLimite) {
			this.dataLimite = dataLimite;
		}
		public Integer getCriacaoItemListaTransiente() {
			return criacaoItemListaTransiente;
		}
		public void setCriacaoItemListaTransiente(Integer criacaoItemListaTransiente) {
			this.criacaoItemListaTransiente = criacaoItemListaTransiente;
		}
		public Integer getIdCriacaoItemHistoricoPendenciaTransiente() {
			return IdCriacaoItemHistoricoPendenciaTransiente;
		}
		public void setIdCriacaoItemHistoricoPendenciaTransiente(
				Integer idCriacaoItemHistoricoPendenciaTransiente) {
			IdCriacaoItemHistoricoPendenciaTransiente = idCriacaoItemHistoricoPendenciaTransiente;
		}
		public Integer getCriacaoItemStatusTransiente() {
			return criacaoItemStatusTransiente;
		}
		public void setCriacaoItemStatusTransiente(Integer criacaoItemStatusTransiente) {
			this.criacaoItemStatusTransiente = criacaoItemStatusTransiente;
		}
		public CriacaoItemStatus getCriacaoItemStatus() {
			return criacaoItemStatus;
		}
		public void setCriacaoItemStatus(CriacaoItemStatus criacaoItemStatus) {
			this.criacaoItemStatus = criacaoItemStatus;
		}
		public CriacaoItemLista getCriacaoItemLista() {
			return criacaoItemLista;
		}
		public void setCriacaoItemLista(CriacaoItemLista criacaoItemLista) {
			this.criacaoItemLista = criacaoItemLista;
		}
		public Usuario getResponsavelItem() {
			return responsavelItem;
		}
		public void setResponsavelItem(Usuario responsavelItem) {
			this.responsavelItem = responsavelItem;
		}
		public void setIdCriacaoItemHistoricoPendencia(
				Integer idCriacaoItemHistoricoPendencia) {
			IdCriacaoItemHistoricoPendencia = idCriacaoItemHistoricoPendencia;
		}
		public String getDescPendencia() {
			return descPendencia;
		}
		public void setDescPendencia(String descPendencia) {
			this.descPendencia = descPendencia;
		}
		public String getObs() {
			return obs;
		}
		public void setObs(String obs) {
			this.obs = obs;
		}
		public Calendar getDataEnvioPendencia() {
			return dataEnvioPendencia;
		}
		public void setDataEnvioPendencia(Calendar dataEnvioPendencia) {
			this.dataEnvioPendencia = dataEnvioPendencia;
		}
		public Calendar getDataInicio() {
			return dataInicio;
		}
		public void setDataInicio(Calendar dataInicio) {
			this.dataInicio = dataInicio;
		}
		public Calendar getDataTermino() {
			return dataTermino;
		}
		public void setDataTermino(Calendar dataTermino) {
			this.dataTermino = dataTermino;
		}
	
	 	
	
	
}

