package br.com.sysloccOficial.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
public class NotificaEquipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
// ----- Transientes ------ //	 

	@Transient List<Integer> idUsuarioNotifica;
	@Transient Integer idJobJob;
	@Transient Integer codInterno;
	@Transient Integer idEmp;
	@Transient boolean multiploNotifica;
	
// --------- Datas- ------ //
	@Temporal(TemporalType.TIMESTAMP)private Calendar enviadoEm;
	
//------- Relacionamentos -------//	
	
	@ManyToOne private Job idJob;
	private Integer idUsuarioNotificaEquipe;
	
// -------------------------------------------------------------------------- //	
	
	
	public Integer getId() {
		return id;
	}
	
	public Integer getCodInterno() {
		return codInterno;
	}

	public void setCodInterno(Integer codInterno) {
		this.codInterno = codInterno;
	}

	public Integer getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(Integer idEmp) {
		this.idEmp = idEmp;
	}

	public Calendar getEnviadoEm() {
		return enviadoEm;
	}

	public void setEnviadoEm(Calendar enviadoEm) {
		this.enviadoEm = enviadoEm;
	}

	public boolean isMultiploNotifica() {
		return multiploNotifica;
	}
	public void setMultiploNotifica(boolean multiploNotifica) {
		this.multiploNotifica = multiploNotifica;
	}
	public Integer getIdJobJob() {
		return idJobJob;
	}
	public void setIdJobJob(Integer idJobJob) {
		this.idJobJob = idJobJob;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Integer> getIdUsuarioNotifica() {
		return idUsuarioNotifica;
	}
	public void setIdUsuarioNotifica(List<Integer> idUsuarioNotifica) {
		this.idUsuarioNotifica = idUsuarioNotifica;
	}
	public Job getIdJob() {
		return idJob;
	}
	public void setIdJob(Job idJob) {
		this.idJob = idJob;
	}
	public Integer getIdUsuarioNotificaEquipe() {
		return idUsuarioNotificaEquipe;
	}
	public void setIdUsuarioNotificaEquipe(Integer idUsuarioNotificaEquipe) {
		this.idUsuarioNotificaEquipe = idUsuarioNotificaEquipe;
	}

// --------------------------------------------//
	
	
	
	
}


