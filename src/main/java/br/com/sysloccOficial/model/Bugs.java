package br.com.sysloccOficial.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bugs {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idBugs;
	private String urlAnterior;
	private String urlAtual;
	private String solucao;
	private String tipoErro;

// -------------------------------------------------------------------------------------------- //	
	
	@ManyToOne @JoinColumn(name="enviadoPor") private Usuario enviadoPor;
	@ManyToOne @JoinColumn(name="solucaoPor") private Usuario solucaoPor;

// -------------------------------------------------------------------------------------------- //	

	@Temporal(TemporalType.TIMESTAMP)private Calendar criadoEm;//Data de envio do bugs
	@Temporal(TemporalType.TIMESTAMP)private Calendar dataSolucao;//Data de envio do bugs

// -------------------------------------------------------------------------------------------- //	
	
	
	public String acrescenta(){
		String novaUrl = urlAnterior += " outraUrl";
		return novaUrl;
	}
	
	public Integer getIdBugs() {
		return idBugs;
	}
	public String getTipoErro() {
		return tipoErro;
	}
	public void setTipoErro(String tipoErro) {
		this.tipoErro = tipoErro;
	}
	public void setIdBugs(Integer idBugs) {
		this.idBugs = idBugs;
	}
	public String getUrlAnterior() {
		return urlAnterior;
	}
	public void setUrlAnterior(String urlAnterior) {
		this.urlAnterior = urlAnterior;
	}
	public String getUrlAtual() {
		return urlAtual;
	}
	public void setUrlAtual(String urlAtual) {
		this.urlAtual = urlAtual;
	}
	public String getSolucao() {
		return solucao;
	}
	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}
	public Usuario getEnviadoPor() {
		return enviadoPor;
	}
	public void setEnviadoPor(Usuario enviadoPor) {
		this.enviadoPor = enviadoPor;
	}
	public Usuario getSolucaoPor() {
		return solucaoPor;
	}
	public void setSolucaoPor(Usuario solucaoPor) {
		this.solucaoPor = solucaoPor;
	}
	public Calendar getCriadoEm() {
		return criadoEm;
	}
	public void setCriadoEm(Calendar criadoEm) {
		this.criadoEm = criadoEm;
	}
	public Calendar getDataSolucao() {
		return dataSolucao;
	}
	public void setDataSolucao(Calendar dataSolucao) {
		this.dataSolucao = dataSolucao;
	}

	
	//	@Temporal(TemporalType.TIMESTAMP)private Calendar dataSolucao;//Data de envio do bugs
 	
	
	
	
	
}
