package br.com.sysloccOficial.model;


import java.util.Date;

import javax.persistence.*;


@Entity
public class LocalEvento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLocalEvento;
	private String localEventoNome;
	private String localEventoEndereco;	
	private Integer localEventoQtdPessoas;
    private String localEventoObservacoes;	
    private String local;	
	
// -----Transientes ------ //    
    @Transient private Integer JobId;
    @Transient private Integer idEmpresa;
    @Transient private String dataInicio;
    @Transient private String horaInicio;
    @Transient private String dataTermino;
    @Transient private String horaTermino;
   
// ----- Datas ------ //	
    
	@Temporal(TemporalType.TIMESTAMP) private Date localEventoDataInicio;
	@Temporal(TemporalType.TIMESTAMP) private Date localEventoDataTermino;	

//------- Relacionamentos -------//
	
	@ManyToOne @JoinColumn(name="idJob") private Job idJob;
	
// --------------------------------------//
	
	
	public Integer getIdLocalEvento() {
		return idLocalEvento;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public void setIdLocalEvento(Integer idLocalEvento) {
		this.idLocalEvento = idLocalEvento;
	}

	public String getLocalEventoNome() {
		return localEventoNome;
	}

	public void setLocalEventoNome(String localEventoNome) {
		this.localEventoNome = localEventoNome;
	}

	public String getLocalEventoEndereco() {
		return localEventoEndereco;
	}

	public void setLocalEventoEndereco(String localEventoEndereco) {
		this.localEventoEndereco = localEventoEndereco;
	}

	public Integer getLocalEventoQtdPessoas() {
		return localEventoQtdPessoas;
	}

	public void setLocalEventoQtdPessoas(Integer localEventoQtdPessoas) {
		this.localEventoQtdPessoas = localEventoQtdPessoas;
	}

	public String getLocalEventoObservacoes() {
		return localEventoObservacoes;
	}

	public void setLocalEventoObservacoes(String localEventoObservacoes) {
		this.localEventoObservacoes = localEventoObservacoes;
	}

	public Integer getJobId() {
		return JobId;
	}

	public void setJobId(Integer jobId) {
		JobId = jobId;
	}

	public Date getLocalEventoDataInicio() {
		return localEventoDataInicio;
	}

	public void setLocalEventoDataInicio(Date localEventoDataInicio) {
		this.localEventoDataInicio = localEventoDataInicio;
	}

	public Date getLocalEventoDataTermino() {
		return localEventoDataTermino;
	}

	public void setLocalEventoDataTermino(Date localEventoDataTermino) {
		this.localEventoDataTermino = localEventoDataTermino;
	}

	public Job getIdJob() {
		return idJob;
	}

	public void setIdJob(Job idJob) {
		this.idJob = idJob;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
	
}
