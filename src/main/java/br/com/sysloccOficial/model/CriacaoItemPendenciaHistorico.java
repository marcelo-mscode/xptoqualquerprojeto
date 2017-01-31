package br.com.sysloccOficial.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CriacaoItemPendenciaHistorico {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idItemHistoricoPendencia;
	private String obs;
	private Integer versao;
	
// ------------------- Relacionamentos ------------------------------ //	

	@ManyToOne @JoinColumn(name="idCriacaoItem")private CriacaoItemPendencia criacaoItemPendencia;
	@ManyToOne @JoinColumn(name="idCriacaoItemStatus") private CriacaoItemStatus criacaoItemStatus;
	@OneToOne @JoinColumn(name="responsavelItem") Usuario responsavelItem;
	
// ----------------------- Datas ------------------------------------ //	
	@Temporal(TemporalType.TIMESTAMP) private Calendar dataInicio;
	@Temporal(TemporalType.TIMESTAMP) private Calendar dataTermino;
// ------------------------------------------------------------------ //
	
	public Integer getIdItemHistoricoPendencia() {
		return idItemHistoricoPendencia;
	}
	public CriacaoItemPendencia getCriacaoItemPendencia() {
		return criacaoItemPendencia;
	}
	public void setCriacaoItemPendencia(CriacaoItemPendencia criacaoItemPendencia) {
		this.criacaoItemPendencia = criacaoItemPendencia;
	}
	public void setIdItemHistoricoPendencia(Integer idItemHistoricoPendencia) {
		this.idItemHistoricoPendencia = idItemHistoricoPendencia;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
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
	public Usuario getResponsavelItem() {
		return responsavelItem;
	}
	public void setResponsavelItem(Usuario responsavelItem) {
		this.responsavelItem = responsavelItem;
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
