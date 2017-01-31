package br.com.sysloccOficial.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class Anexos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAnexo;
	@JoinColumn(name="AnexoCod")
	private String anexoCod;
	@JoinColumn(name="AnexoCod")
	private String anexoTitulo;
	
	
	@JoinColumn(name="AnexoArea")
	private String anexoarea;
	
	
	private String AnexoOrigem;
	private String anexoStatus;
	private Calendar criadoData;
	private Integer alteradoPor;
	private Calendar alteradoData;
    private String anexoArquivo;
	private String anexoDiretorio;

// ------------ Relacionamentos ---------------- //	
	@ManyToOne
	@JoinColumn(name="Criadopor")
	private Usuario criadopor;	
	
	@ManyToOne
	@JoinColumn(name="AnexoIdOrigem")
	private Job AnexoIdOrigem;
	
// ---------- Transiente ----------------------//
	@Transient private String CodInterno;
	@Transient private Integer idCriadoPor;
	@Transient private Integer idJob;
	@Transient private Integer idEmp;
	@Transient private String pastaIntermediaria;
	
	
// --------------------------------------------//	
	
	
	
	
	public Integer getIdAnexo() {
		return idAnexo;
	}
	public String getPastaIntermediaria() {
		return pastaIntermediaria;
	}
	public void setPastaIntermediaria(String pastaIntermediaria) {
		this.pastaIntermediaria = pastaIntermediaria;
	}
	public void setIdAnexo(Integer idAnexo) {
		this.idAnexo = idAnexo;
	}
	public String getAnexoCod() {
		return anexoCod;
	}
	public void setAnexoCod(String anexoCod) {
		this.anexoCod = anexoCod;
	}
	public String getAnexoTitulo() {
		return anexoTitulo;
	}
	public void setAnexoTitulo(String anexoTitulo) {
		this.anexoTitulo = anexoTitulo;
	}
	
	public String getAnexoarea() {
		return anexoarea;
	}
	public void setAnexoarea(String anexoarea) {
		this.anexoarea = anexoarea;
	}
	public String getAnexoOrigem() {
		return AnexoOrigem;
	}
	public void setAnexoOrigem(String anexoOrigem) {
		AnexoOrigem = anexoOrigem;
	}
	public String getAnexoStatus() {
		return anexoStatus;
	}
	public void setAnexoStatus(String anexoStatus) {
		this.anexoStatus = anexoStatus;
	}
	public Calendar getCriadoData() {
		return criadoData;
	}
	public void setCriadoData(Calendar criadoData) {
		this.criadoData = criadoData;
	}
	public Integer getAlteradoPor() {
		return alteradoPor;
	}
	public void setAlteradoPor(Integer alteradoPor) {
		this.alteradoPor = alteradoPor;
	}
	public Calendar getAlteradoData() {
		return alteradoData;
	}
	public void setAlteradoData(Calendar alteradoData) {
		this.alteradoData = alteradoData;
	}
	public String getAnexoArquivo() {
		return anexoArquivo;
	}
	public void setAnexoArquivo(String anexoArquivo) {
		this.anexoArquivo = anexoArquivo;
	}
	public String getAnexoDiretorio() {
		return anexoDiretorio;
	}
	public void setAnexoDiretorio(String anexoDiretorio) {
		this.anexoDiretorio = anexoDiretorio;
	}
	public Usuario getCriadopor() {
		return criadopor;
	}
	public void setCriadopor(Usuario criadopor) {
		this.criadopor = criadopor;
	}
	public Job getAnexoIdOrigem() {
		return AnexoIdOrigem;
	}
	public void setAnexoIdOrigem(Job anexoIdOrigem) {
		AnexoIdOrigem = anexoIdOrigem;
	}
	public String getCodInterno() {
		return CodInterno;
	}
	public void setCodInterno(String codInterno) {
		CodInterno = codInterno;
	}
	public Integer getIdCriadoPor() {
		return idCriadoPor;
	}
	public void setIdCriadoPor(Integer idCriadoPor) {
		this.idCriadoPor = idCriadoPor;
	}
	public Integer getIdJob() {
		return idJob;
	}
	public void setIdJob(Integer idJob) {
		this.idJob = idJob;
	}
	public Integer getIdEmp() {
		return idEmp;
	}
	public void setIdEmp(Integer idEmp) {
		this.idEmp = idEmp;
	}
	
// -------------------------------------------//	
	
	
	
	
	
}
