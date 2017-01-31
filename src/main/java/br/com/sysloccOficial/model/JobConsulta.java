package br.com.sysloccOficial.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class JobConsulta {

	@Temporal(TemporalType.TIMESTAMP) private Date localEventoDataInicio;
	@Temporal(TemporalType.TIMESTAMP) private List<Date> ListalocalEventoDataInicio;
	@Temporal(TemporalType.TIMESTAMP) private List<Date> ListalocalEventoDataTermino;
	@Temporal(TemporalType.TIMESTAMP) private List<String> listaLocalEvento;
	
	
	@Temporal(TemporalType.TIMESTAMP) private Date localEventoDataTermino;
	@Temporal(TemporalType.TIMESTAMP) private Calendar criadoEm;  // Data de Criação do Job
	@Temporal(TemporalType.TIMESTAMP) private Date propostaData;  // 	
	@Temporal(TemporalType.TIMESTAMP) private Date prazoConclusao; // Prazo Interno

	
	
	private Integer idJob;
	private Integer codJob;
	private Integer idEmpresa;
	
	
	private String contato;
	private String titulo;
	private String empresa;
	private String estatus;
	
	private String localEvento;
	
	
	private String produtor1;
	private String produtor2;
	private String obs;
	
	
	
// ---------------------------------------------------------------------------------------------------------- //	
	
	
	public Date getPrazoConclusao() {
		return prazoConclusao;
	}
	public List<String> getListaLocalEvento() {
		return listaLocalEvento;
	}
	public void setListaLocalEvento(List<String> listaLocalEvento) {
		this.listaLocalEvento = listaLocalEvento;
	}
	public List<Date> getListalocalEventoDataTermino() {
		return ListalocalEventoDataTermino;
	}
	public void setListalocalEventoDataTermino(
			List<Date> listalocalEventoDataTermino) {
		ListalocalEventoDataTermino = listalocalEventoDataTermino;
	}
	public List<Date> getListalocalEventoDataInicio() {
		return ListalocalEventoDataInicio;
	}
	public void setListalocalEventoDataInicio(List<Date> listalocalEventoDataInicio) {
		ListalocalEventoDataInicio = listalocalEventoDataInicio;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getProdutor1() {
		return produtor1;
	}
	public void setProdutor1(String produtor1) {
		this.produtor1 = produtor1;
	}
	public String getProdutor2() {
		return produtor2;
	}
	public void setProdutor2(String produtor2) {
		this.produtor2 = produtor2;
	}
	public String getLocalEvento() {
		return localEvento;
	}
	public void setLocalEvento(String localEvento) {
		this.localEvento = localEvento;
	}
	public Date getLocalEventoDataTermino() {
		return localEventoDataTermino;
	}
	public void setLocalEventoDataTermino(Date localEventoDataTermino) {
		this.localEventoDataTermino = localEventoDataTermino;
	}
	public void setPrazoConclusao(Date prazoConclusao) {
		this.prazoConclusao = prazoConclusao;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Calendar getCriadoEm() {
		return criadoEm;
	}
	public void setCriadoEm(Calendar date) {
		this.criadoEm = date;
	}
	public Date getPropostaData() {
		return propostaData;
	}
	public void setPropostaData(Date propostaData) {
		this.propostaData = propostaData;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Integer getCodJob() {
		return codJob;
	}
	public void setCodJob(Integer codJob) {
		this.codJob = codJob;
	}
	public Date getLocalEventoDataInicio() {
		return localEventoDataInicio;
	}
	public void setLocalEventoDataInicio(Date localEventoDataInicio) {
		this.localEventoDataInicio = localEventoDataInicio;
	}
	public Integer getIdJob() {
		return idJob;
	}
	public void setIdJob(Integer idJob) {
		this.idJob = idJob;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	
}
