package br.com.sysloccOficial.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class JobStatus {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer	idJobStatus;

private String	jobStatusObservacao;
	
// ---------- Transientes ------------- //	
  @Transient private String prazoEstatusData;
  @Transient private String prazoEstatusHora;
  @Transient private String prazoData;
  @Transient private String prazoHora;
  @Transient private Integer idStatusTransiente;
  @Transient private Integer idUsuarioTransiente;
  @Transient private Integer idProdutor2Transiente;
  @Transient private Integer idJobTransiente;
  @Transient private Integer idEmpTransiente;
	
// -----------Relacionamentos --------- //	

  @ManyToOne @JoinColumn(name="idStatus") private Estatus idStatus;	
  @ManyToOne @JoinColumn(name="idUsuario") private Usuario idUsuario; // Produtor 1
  @ManyToOne @JoinColumn(name="produtor2") private Usuario produtor2; // Produtor 2
  @ManyToOne @JoinColumn(name="idJob") private Job idJob;
  @ManyToOne @JoinColumn(name="criadoPor")private Usuario criadoPor;
  
  @ManyToOne @JoinColumn(name="idDepartamento") private Departamento idDepartamento;
  
// ------------ Datas ----------------- //	

  @Temporal(TemporalType.TIMESTAMP)private Calendar criadoData;
  @Temporal(TemporalType.TIMESTAMP)private Date prazoConclusao;

// ---------------------------------------------------------------- //

  
  
public Integer getIdJobTransiente() {
	return idJobTransiente;
}
public Integer getIdProdutor2Transiente() {
	return idProdutor2Transiente;
}
public void setIdProdutor2Transiente(Integer idProdutor2Transiente) {
	this.idProdutor2Transiente = idProdutor2Transiente;
}
public Usuario getProdutor2() {
	return produtor2;
}
public void setProdutor2(Usuario produtor2) {
	this.produtor2 = produtor2;
}
public void setIdJobTransiente(Integer idJobTransiente) {
	this.idJobTransiente = idJobTransiente;
}
public Integer getIdEmpTransiente() {
	return idEmpTransiente;
}
public void setIdEmpTransiente(Integer idEmpTransiente) {
	this.idEmpTransiente = idEmpTransiente;
}
public Integer getIdUsuarioTransiente() {
	return idUsuarioTransiente;
}
public void setIdUsuarioTransiente(Integer idUsuarioTransiente) {
	this.idUsuarioTransiente = idUsuarioTransiente;
}
public Integer getIdStatusTransiente() {
	return idStatusTransiente;
}
public void setIdStatusTransiente(Integer idStatusTransiente) {
	this.idStatusTransiente = idStatusTransiente;
}
public String getPrazoEstatusData() {
	return prazoEstatusData;
}
public void setPrazoEstatusData(String prazoEstatusData) {
	this.prazoEstatusData = prazoEstatusData;
}
public String getPrazoEstatusHora() {
	return prazoEstatusHora;
}
public void setPrazoEstatusHora(String prazoEstatusHora) {
	this.prazoEstatusHora = prazoEstatusHora;
}
public String getPrazoData() {
	return prazoData;
}
public void setPrazoData(String prazoData) {
	this.prazoData = prazoData;
}
public String getPrazoHora() {
	return prazoHora;
}
public void setPrazoHora(String prazoHora) {
	this.prazoHora = prazoHora;
}
public Departamento getIdDepartamento() {
	return idDepartamento;
}
public void setIdDepartamento(Departamento idDepartamento) {
	this.idDepartamento = idDepartamento;
}
public Usuario getCriadoPor() {
	return criadoPor;
}
public void setCriadoPor(Usuario criadoPor) {
	this.criadoPor = criadoPor;
}
public Job getIdJob() {
	return idJob;
}
public void setIdJob(Job idJob) {
	this.idJob = idJob;
}
public Usuario getIdUsuario() {
	return idUsuario;
}
public void setIdUsuario(Usuario idUsuario) {
	this.idUsuario = idUsuario;
}
public Integer getIdJobStatus() {
	return idJobStatus;
}
public void setIdJobStatus(Integer idJobStatus) {
	this.idJobStatus = idJobStatus;
}
public String getJobStatusObservacao() {
	return jobStatusObservacao;
}
public void setJobStatusObservacao(String jobStatusObservacao) {
	this.jobStatusObservacao = jobStatusObservacao;
}
public Estatus getIdStatus() {
	return idStatus;
}
public void setIdStatus(Estatus idStatus) {
	this.idStatus = idStatus;
}
/*public Job getIdJob() {
	return idJob;
}
public void setIdJob(Job idJob) {
	this.idJob = idJob;
}*/
/*public List<Usuario> getIdUsuario() {
	return idUsuario;
}
public void setIdUsuario(List<Usuario> idUsuario) {
	this.idUsuario = idUsuario;
}*/
/*public List<Departamento> getIdDepartamento() {
	return idDepartamento;
}
public void setIdDepartamento(List<Departamento> idDepartamento) {
	this.idDepartamento = idDepartamento;
}*/
public Calendar getCriadoData() {
	return criadoData;
}
public void setCriadoData(Calendar criadoData) {
	this.criadoData = criadoData;
}
public Date getPrazoConclusao() {
	return prazoConclusao;
}
public void setPrazoConclusao(Date date) {
	this.prazoConclusao = date;
}

// ------------------------------------ // 
	
	
	
	
	
	
}
