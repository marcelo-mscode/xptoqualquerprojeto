package br.com.sysloccOficial.model;

import java.util.List;
import javafx.print.PrinterJob.JobStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Estatus {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idEstatus;
	private String  estatus;
	
// ------------------------------ //
	
/*  @OneToMany(mappedBy="idStatus")  private List<JobStatus> jobEstatus; */
    
    @OneToMany(mappedBy="") private List<br.com.sysloccOficial.model.JobStatus>jobEstatus;
	
	public List<br.com.sysloccOficial.model.JobStatus> getJobEstatus() {
		return jobEstatus;
	}
	public void setJobEstatus(List<br.com.sysloccOficial.model.JobStatus> jobEstatus) {
		this.jobEstatus = jobEstatus;
	}
	public Integer getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
}
