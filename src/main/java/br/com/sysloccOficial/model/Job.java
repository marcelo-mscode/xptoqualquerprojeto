package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Job {
	
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Integer idJob;
	    private Integer codJob;
	    private Integer estrategiaConcluidoPor;
	 //   private Integer idStatusAtual;
	    private String titulo;
	    
		private String descricao;
	    private boolean concluido;
	    private boolean estrategiaConcluido;
	    
// -----Transientes ------ //	    

	    @Transient private String pd;
	    @Transient private String ph;
	    @Transient private String dataProposta;
		@Transient private String apresHoraProposta;
	    @Transient private Integer idEmp;
	    @Transient private Integer idContato;
	    @Transient private Integer idJobEditar;
	    @Transient private Integer codJobTransient;

// ----- Datas ------ //	    
	    
	    @Temporal(TemporalType.TIMESTAMP)private Date propostaData;
	    @Temporal(TemporalType.TIMESTAMP)private Date apresPropostaData;
	    @Temporal(TemporalType.TIMESTAMP)private Calendar criadoEm;//Data de Criação do Job
	    @Temporal(TemporalType.TIMESTAMP)private Calendar estrategiaConcluidoData;

//------- Relacionamentos -------//	    
	    
	    @ManyToOne
		@JoinColumn(name="idEmpresa")
		private Empresa empresa;
	    
	    /*@OneToMany(mappedBy = "idJob")
	    private List<JobStatus> jobStatus;*/
	 
	    
	    @ManyToOne @JoinColumn(name="idStatusAtual") private JobStatus idStatusAtual; // Do tipo EAGER 
	    
	    
	    @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="idContatoResponsavel")
		private Contato contatos;
	    
	    @OneToMany(mappedBy = "idJob", cascade=CascadeType.REMOVE, fetch = FetchType.LAZY)
	    private List<LocalEvento> localEvento;
	    
	    @OneToMany(mappedBy = "idJob")
	    private List<Interno> idJOb;
	    
	    @OneToMany(mappedBy="AnexoIdOrigem", fetch = FetchType.LAZY)
	    private List<Anexos> Anexos;

	    @OneToMany(mappedBy="idOrigem", fetch = FetchType.LAZY)
		private List<Interacao> idOrigem;
		
	    @OneToMany(mappedBy="idJob", fetch = FetchType.LAZY)
	    private List<NotificaEquipe> notificaEquipe;
	    
	    @OneToMany(mappedBy = "idJob")
	    private List<Lista> idJobLista;
	    
//----- Fim Relacionamentos ------//
	    
	    
	    
	    
	    public String getPd() {
			return pd;
		}
		
		public Integer getCodJobTransient() {
			return codJobTransient;
		}

		public void setCodJobTransient(Integer codJobTransient) {
			this.codJobTransient = codJobTransient;
		}

		public JobStatus getIdStatusAtual() {
			return idStatusAtual;
		}

		public void setIdStatusAtual(JobStatus idStatusAtual) {
			this.idStatusAtual = idStatusAtual;
		}

		public List<Lista> getIdJobLista() {
			return idJobLista;
		}

		public void setIdJobLista(List<Lista> idJobLista) {
			this.idJobLista = idJobLista;
		}

		/*public List<JobStatus> getJobStatus() {
			return jobStatus;
		}

		public void setJobStatus(List<JobStatus> jobStatus) {
			this.jobStatus = jobStatus;
		}*/

		public List<NotificaEquipe> getNotificaEquipe() {
			return notificaEquipe;
		}

		public void setNotificaEquipe(List<NotificaEquipe> notificaEquipe) {
			this.notificaEquipe = notificaEquipe;
		}

		public List<Anexos> getAnexos() {
			return Anexos;
		}
		public void setAnexos(List<Anexos> anexos) {
			Anexos = anexos;
		}
		public void setPd(String pd) {
			this.pd = pd;
		}
		public String getPh() {
			return ph;
		}
		public void setPh(String ph) {
			this.ph = ph;
		}

		
	    public String getDataProposta() {
			return dataProposta;
		}
		public void setDataProposta(String dataProposta) {
			this.dataProposta = dataProposta;
		}

		public Contato getContato() {
			return contatos;
		}
		public void setContato(Contato contato) {
			this.contatos = contato;
		}

	    
	    /*public Integer getIdStatusAtual() {
			return idStatusAtual;
		}
		public void setIdStatusAtual(Integer idStatusAtual) {
			this.idStatusAtual = idStatusAtual;
		}*/
		
		public Calendar getCriadoEm() {
			return criadoEm;
		}
		public void setCriadoEm(Calendar criadoEm) {
			this.criadoEm = criadoEm;
		}
	    	    
	    public Integer getEstrategiaConcluidoPor() {
			return estrategiaConcluidoPor;
		}
		public void setEstrategiaConcluidoPor(Integer estrategiaConcluidoPor) {
			this.estrategiaConcluidoPor = estrategiaConcluidoPor;
		}
	    
	    public Calendar getEstrategiaConcluidoData() {
			return estrategiaConcluidoData;
		}
		public void setEstrategiaConcluidoData(Calendar estrategiaConcluidoData) {
			this.estrategiaConcluidoData = estrategiaConcluidoData;
		}
		public boolean isEstrategiaConcluido() {
			return estrategiaConcluido;
		}
		public void setEstrategiaConcluido(boolean estrategiaConcluido) {
			this.estrategiaConcluido = estrategiaConcluido;
		}
		public boolean isConcluido() {
			return concluido;
		}
		public void setConcluido(boolean concluido) {
			this.concluido = concluido;
		}
		public Integer getCodJob() {
			return codJob;
		}
		public void setCodJob(Integer codJob) {
			this.codJob = codJob;
		}

		private BigDecimal verba;
	    
	    public BigDecimal getVerba() {
			return verba;
		}
		public void setVerba(BigDecimal verba) {
			this.verba = verba;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	    
	    public Date getApresPropostaData() {
			return apresPropostaData;
		}
		public void setApresPropostaData(Date date) {
			this.apresPropostaData = date;
		}
		public String getApresHoraProposta() {
			return apresHoraProposta;
		}
		public void setApresHoraProposta(String apresHoraProposta) {
			this.apresHoraProposta = apresHoraProposta;
		}
		public String getHoraproposta() {
			return dataProposta;
		}
		public void setHoraproposta(String horaproposta) {
			this.dataProposta = horaproposta;
		}
		  
		public Integer getIdEmp() {
				return idEmp;
		}
			public void setIdEmp(Integer idEmp) {
				this.idEmp = idEmp;
		}
		
		public Integer getIdContato() {
			return idContato;
		}
		public void setIdContato(Integer idContato) {
			this.idContato = idContato;
		}

		public Empresa getEmpresa() {
			return empresa;
		}
		public void setEmpresa(Empresa empresa) {
			this.empresa = empresa;
		}
  
		public Integer getIdJob() {
			return idJob;
		}
		public void setIdJob(Integer idJob) {
			this.idJob = idJob;
		}
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public Date getPropostaData() {
			return propostaData;
		}
		public void setPropostaData(Date date) {
			this.propostaData = date;
		}
		public Contato getContatos() {
			return contatos;
		}
		public void setContatos(Contato contatos) {
			this.contatos = contatos;
		}
		public Integer getIdJobEditar() {
			return idJobEditar;
		}
		public void setIdJobEditar(Integer idJobEditar) {
			this.idJobEditar = idJobEditar;
		}

		public List<LocalEvento> getLocalEvento() {
			return localEvento;
		}

		public void setLocalEvento(List<LocalEvento> localEvento) {
			this.localEvento = localEvento;
		}
		
		
 
}









/*package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Job {
	
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Integer idJob;
	    private Integer codJob;
	    private Integer estrategiaConcluidoPor;
	    private Integer idStatusAtual;
	    private String titulo;
	    
		private String descricao;
	    private boolean concluido;
	    private boolean estrategiaConcluido;
	    
// -----Transientes ------ //	    

	    @Transient private String pd;
	    @Transient private String ph;
	    @Transient private String dataProposta;
		@Transient private String apresHoraProposta;
	    @Transient private Integer idEmp;
	    @Transient private Integer idContato;
	    @Transient private Integer idJobEditar;

// ----- Datas ------ //	    
	    
	    @Temporal(TemporalType.TIMESTAMP)private Date propostaData;
	    @Temporal(TemporalType.TIMESTAMP)private Date apresPropostaData;
	    @Temporal(TemporalType.TIMESTAMP)private Calendar criadoEm;//Data de Criação do Job
	    @Temporal(TemporalType.TIMESTAMP)private Calendar estrategiaConcluidoData;

//------- Relacionamentos -------//	    
	    
	    @ManyToOne
		@JoinColumn(name="idEmpresa")
		private Empresa empresa;
	    
	    @OneToMany(mappedBy = "idJob")
	    private List<JobStatus> jobStatus;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="idContatoResponsavel")
		private Contato contatos;
	    
	    @OneToMany(mappedBy = "idJob", cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
	    private List<LocalEvento> localEvento;
	    
	    @OneToMany(mappedBy = "idJob")
	    private List<Interno> idJOb;
	    
	    @OneToMany(mappedBy="AnexoIdOrigem")
	    private List<Anexos> Anexos;

	    @OneToMany(mappedBy="idOrigem")
		private List<Interacao> idOrigem;
		
	    @OneToMany(mappedBy="idJob")
	    private List<NotificaEquipe> notificaEquipe;
	    
	    @OneToMany(mappedBy = "idJob")
	    private List<Lista> idJobLista;
	    
//----- Fim Relacionamentos ------//
	    
	    
	    public String getPd() {
			return pd;
		}
		
		public List<Lista> getIdJobLista() {
			return idJobLista;
		}

		public void setIdJobLista(List<Lista> idJobLista) {
			this.idJobLista = idJobLista;
		}

		public List<JobStatus> getJobStatus() {
			return jobStatus;
		}

		public void setJobStatus(List<JobStatus> jobStatus) {
			this.jobStatus = jobStatus;
		}

		public List<NotificaEquipe> getNotificaEquipe() {
			return notificaEquipe;
		}

		public void setNotificaEquipe(List<NotificaEquipe> notificaEquipe) {
			this.notificaEquipe = notificaEquipe;
		}

		public List<Anexos> getAnexos() {
			return Anexos;
		}
		public void setAnexos(List<Anexos> anexos) {
			Anexos = anexos;
		}
		public void setPd(String pd) {
			this.pd = pd;
		}
		public String getPh() {
			return ph;
		}
		public void setPh(String ph) {
			this.ph = ph;
		}

		
	    public String getDataProposta() {
			return dataProposta;
		}
		public void setDataProposta(String dataProposta) {
			this.dataProposta = dataProposta;
		}

		public Contato getContato() {
			return contatos;
		}
		public void setContato(Contato contato) {
			this.contatos = contato;
		}

	    
	    public Integer getIdStatusAtual() {
			return idStatusAtual;
		}
		public void setIdStatusAtual(Integer idStatusAtual) {
			this.idStatusAtual = idStatusAtual;
		}
		
		public Calendar getCriadoEm() {
			return criadoEm;
		}
		public void setCriadoEm(Calendar criadoEm) {
			this.criadoEm = criadoEm;
		}
	    	    
	    public Integer getEstrategiaConcluidoPor() {
			return estrategiaConcluidoPor;
		}
		public void setEstrategiaConcluidoPor(Integer estrategiaConcluidoPor) {
			this.estrategiaConcluidoPor = estrategiaConcluidoPor;
		}
	    
	    public Calendar getEstrategiaConcluidoData() {
			return estrategiaConcluidoData;
		}
		public void setEstrategiaConcluidoData(Calendar estrategiaConcluidoData) {
			this.estrategiaConcluidoData = estrategiaConcluidoData;
		}
		public boolean isEstrategiaConcluido() {
			return estrategiaConcluido;
		}
		public void setEstrategiaConcluido(boolean estrategiaConcluido) {
			this.estrategiaConcluido = estrategiaConcluido;
		}
		public boolean isConcluido() {
			return concluido;
		}
		public void setConcluido(boolean concluido) {
			this.concluido = concluido;
		}
		public Integer getCodJob() {
			return codJob;
		}
		public void setCodJob(Integer codJob) {
			this.codJob = codJob;
		}

		private BigDecimal verba;
	    
	    public BigDecimal getVerba() {
			return verba;
		}
		public void setVerba(BigDecimal verba) {
			this.verba = verba;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	    
	    public Date getApresPropostaData() {
			return apresPropostaData;
		}
		public void setApresPropostaData(Date date) {
			this.apresPropostaData = date;
		}
		public String getApresHoraProposta() {
			return apresHoraProposta;
		}
		public void setApresHoraProposta(String apresHoraProposta) {
			this.apresHoraProposta = apresHoraProposta;
		}
		public String getHoraproposta() {
			return dataProposta;
		}
		public void setHoraproposta(String horaproposta) {
			this.dataProposta = horaproposta;
		}
		  
		public Integer getIdEmp() {
				return idEmp;
		}
			public void setIdEmp(Integer idEmp) {
				this.idEmp = idEmp;
		}
		
		public Integer getIdContato() {
			return idContato;
		}
		public void setIdContato(Integer idContato) {
			this.idContato = idContato;
		}

		public Empresa getEmpresa() {
			return empresa;
		}
		public void setEmpresa(Empresa empresa) {
			this.empresa = empresa;
		}
  
		public Integer getIdJob() {
			return idJob;
		}
		public void setIdJob(Integer idJob) {
			this.idJob = idJob;
		}
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public Date getPropostaData() {
			return propostaData;
		}
		public void setPropostaData(Date date) {
			this.propostaData = date;
		}
		public Contato getContatos() {
			return contatos;
		}
		public void setContatos(Contato contatos) {
			this.contatos = contatos;
		}
		public Integer getIdJobEditar() {
			return idJobEditar;
		}
		public void setIdJobEditar(Integer idJobEditar) {
			this.idJobEditar = idJobEditar;
		}

		public List<LocalEvento> getLocalEvento() {
			return localEvento;
		}

		public void setLocalEvento(List<LocalEvento> localEvento) {
			this.localEvento = localEvento;
		}
		
		
 
}
*/