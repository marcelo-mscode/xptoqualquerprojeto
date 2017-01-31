package br.com.sysloccOficial.model;

import java.util.ArrayList;
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

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.conf.Utilitaria;

@Entity
public class CriacaoItemHistorico {
	
	
	/*@Autowired private Utilitaria util;*/

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer idItemHistorico;
	public Integer versao;
	public String obs;
	
// ------------------- Relacionamentos ------------------------------ //	
	@OneToOne @JoinColumn(name="responsavelItem") Usuario responsavelItem;
	@OneToOne @JoinColumn(name="iniciadoPor") Usuario iniciadoPor;
	@OneToOne @JoinColumn(name="finalizadoPor") Usuario finalizadoPor;
	@ManyToOne @JoinColumn(name="idCriacaoItem") CriacaoItemLista criacaoItemLista;
	@ManyToOne @JoinColumn(name="idCriacaoItemStatus") private CriacaoItemStatus criacaoItemStatus;
// ----------------------- Datas ------------------------------------ //	
	@Temporal(TemporalType.TIMESTAMP) private Calendar dataInicio;
 	@Temporal(TemporalType.TIMESTAMP) private Calendar dataTermino;
// ------------------------------------------------------------------ //
// Tipo Faz
 	
/*   public Integer somaTempo(){
	   Integer pegaHora;
	   Long pegaaHora = Utilitaria.diferencaDatasEmMilisegundos(getDataInicio().getTime(), getDataTermino().getTime());
	   pegaHora = util.converteLongParaInteger(pegaaHora);
	   return pegaHora;
   }
   */
 	
 	
// ------------------------------------------------------------------ //
 	
 	
 	
 	
 	
 	
 	
 	
 	public Integer getIdItemHistorico() {
		return idItemHistorico;
	}
	public Usuario getIniciadoPor() {
		return iniciadoPor;
	}
	public void setIniciadoPor(Usuario iniciadoPor) {
		this.iniciadoPor = iniciadoPor;
	}
	public Usuario getFinalizadoPor() {
		return finalizadoPor;
	}
	public void setFinalizadoPor(Usuario finalizadoPor) {
		this.finalizadoPor = finalizadoPor;
	}
	public Integer getVersao() {
		return versao;
	}
	public void setVersao(Integer versao) {
		this.versao = versao;
	}
	public void setIdItemHistorico(Integer idItemHistorico) {
		this.idItemHistorico = idItemHistorico;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Usuario getResponsavelItem() {
		return responsavelItem;
	}
	public void setResponsavelItem(Usuario responsavelItem) {
		this.responsavelItem = responsavelItem;
	}
	public CriacaoItemLista getCriacaoItemLista() {
		return criacaoItemLista;
	}
	public void setCriacaoItemLista(CriacaoItemLista criacaoItemLista) {
		this.criacaoItemLista = criacaoItemLista;
	}
	public CriacaoItemStatus getCriacaoItemStatus() {
		return criacaoItemStatus;
	}
	public void setCriacaoItemStatus(CriacaoItemStatus criacaoItemStatus) {
		this.criacaoItemStatus = criacaoItemStatus;
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
	
 // -------------------- Transientes -------------------------------- //	
 	
 	
 	
 	
	
	
}
