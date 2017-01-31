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
public class Interno {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private	Integer idInterno;
		private	String codInterno;
		private	String internoTipo;
		private	String internoTitulo;
		private	String internoDescricao;
		private	Boolean aprovado;
		private boolean notificaResponsavel;
		
		
		/*private Integer notificadoIdUsuario;*/
		
		
// --------Datas ------------ //		
	
		@Temporal(TemporalType.TIMESTAMP) private Date prazoConclusao;
		@Temporal(TemporalType.TIMESTAMP) private Calendar criadoData;
		@Temporal(TemporalType.TIMESTAMP) private Calendar concluidoData;
		@Temporal(TemporalType.TIMESTAMP) private Calendar notificadoEm;	

// --------Trasientes -------//
		
		@Transient private Integer JobId;
		@Transient private String  pConclusao;
		@Transient private String  hConclusao;
		@Transient private Integer idResponsavel;
		@Transient private Integer idEmp;
		@Transient private Integer concluido;
		
		
		
// --------Relacionamentos --//
		
		@ManyToOne @JoinColumn(name="idJob")private	Job  idJob;
		@ManyToOne @JoinColumn(name="criadoPor")private Usuario criadoPor;
		@ManyToOne @JoinColumn(name="responsavel")private Usuario responsavel;
		@ManyToOne @JoinColumn(name="concluidoPor")private Usuario concluidoPor;
    /*  @ManyToOne @JoinColumn(name="notificadoIdUsuario")private Usuario notificadoIdUsuario;*/
		
// -------------------------------------------------------------------//		
		
		
		
		
		
		
		
		public Integer getIdInterno() {
			return idInterno;
		}
		public boolean isNotificaResponsavel() {
			return notificaResponsavel;
		}
		public void setNotificaResponsavel(boolean notificaResponsavel) {
			this.notificaResponsavel = notificaResponsavel;
		}
		/*public Integer getNotificadoIdUsuario() {
			return notificadoIdUsuario;
		}
		public void setNotificadoIdUsuario(Integer notificadoIdUsuario) {
			this.notificadoIdUsuario = notificadoIdUsuario;
		}*/
		public Integer getConcluido() {
			return concluido;
		}
		public void setConcluido(Integer concluido) {
			this.concluido = concluido;
		}
		public void setIdInterno(Integer idInterno) {
			this.idInterno = idInterno;
		}
		public String getCodInterno() {
			return codInterno;
		}
		public void setCodInterno(String codInterno) {
			this.codInterno = codInterno;
		}
		public String getInternoTipo() {
			return internoTipo;
		}
		public void setInternoTipo(String internoTipo) {
			this.internoTipo = internoTipo;
		}
		public String getInternoTitulo() {
			return internoTitulo;
		}
		public void setInternoTitulo(String internoTitulo) {
			this.internoTitulo = internoTitulo;
		}
		public String getInternoDescricao() {
			return internoDescricao;
		}
		public void setInternoDescricao(String internoDescricao) {
			this.internoDescricao = internoDescricao;
		}
		public Boolean getAprovado() {
			return aprovado;
		}
		public void setAprovado(Boolean aprovado) {
			this.aprovado = aprovado;
		}
		public Date getPrazoConclusao() {
			return prazoConclusao;
		}
		public void setPrazoConclusao(Date prazoConclusao) {
			this.prazoConclusao = prazoConclusao;
		}
		public Calendar getCriadoData() {
			return criadoData;
		}
		public void setCriadoData(Calendar criadoData) {
			this.criadoData = criadoData;
		}
		public Calendar getConcluidoData() {
			return concluidoData;
		}
		public void setConcluidoData(Calendar concluidoData) {
			this.concluidoData = concluidoData;
		}
		public Calendar getNotificadoEm() {
			return notificadoEm;
		}
		public void setNotificadoEm(Calendar cal) {
			this.notificadoEm = cal;
		}
		public Job getIdJob() {
			return idJob;
		}
		public void setIdJob(Job idJob) {
			this.idJob = idJob;
		}
		public Usuario getCriadoPor() {
			return criadoPor;
		}
		public void setCriadoPor(Usuario criadoPor) {
			this.criadoPor = criadoPor;
		}
		public Usuario getResponsavel() {
			return responsavel;
		}
		public void setResponsavel(Usuario responsavel) {
			this.responsavel = responsavel;
		}
		public Usuario getConcluidoPor() {
			return concluidoPor;
		}
		public void setConcluidoPor(Usuario concluidoPor) {
			this.concluidoPor = concluidoPor;
		}
		/*public Usuario getNotificadoIdUsuario() {
			return notificadoIdUsuario;
		}
		public void setNotificadoIdUsuario(Usuario notificadoIdUsuario) {
			this.notificadoIdUsuario = notificadoIdUsuario;
		}*/
		public Integer getJobId() {
			return JobId;
		}
		public void setJobId(Integer jobId) {
			JobId = jobId;
		}
		public String getpConclusao() {
			return pConclusao;
		}
		public void setpConclusao(String pConclusao) {
			this.pConclusao = pConclusao;
		}
		public String gethConclusao() {
			return hConclusao;
		}
		public void sethConclusao(String hConclusao) {
			this.hConclusao = hConclusao;
		}
		public Integer getIdResponsavel() {
			return idResponsavel;
		}
		public void setIdResponsavel(Integer idResponsavel) {
			this.idResponsavel = idResponsavel;
		}
		public Integer getIdEmp() {
			return idEmp;
		}
		public void setIdEmp(Integer idEmp) {
			this.idEmp = idEmp;
		}
		
		
		
}
