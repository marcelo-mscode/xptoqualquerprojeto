package br.com.sysloccOficial.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
public class Departamento implements Serializable{

	@Id
	private Integer idDepartamento;
	private String  departamento;
	
	
	@OneToMany(mappedBy = "departamento")
	private List<Usuario> usuario;
	
    @OneToMany(mappedBy= "idDepartamento")
	private List<JobStatus> jobStatus;
	
// ----------------------------------------------------------- //
    
    
	public List<JobStatus> getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(List<JobStatus> jobStatus) {
		this.jobStatus = jobStatus;
	}
	public List<Usuario> getUsuario() {
		return usuario;
	}
	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}
	
	
	public Integer getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	
	
	
	
}
