package br.com.sysloccOficial.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sysloccOficial.model.prospeccao.Prospeccao;

@Entity
public class Interacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInteracao;
	private String interacao;
	private boolean interno;
	private String interacaoForma;

	private boolean agendar;
	
	private String automatico;
	private String interacaoOrigem;
	
	
// ------------ Relacionamentos ---------//
	@ManyToOne @JoinColumn(name="idUsuario") private Usuario idUsuario;
	@ManyToOne @JoinColumn(name="idUsuarioInterno") private Usuario idUsuarioInterno;
	@ManyToOne @JoinColumn(name="idOrigem")  private Job idOrigem;   
//	@ManyToOne @JoinColumn(name="idOrigem")  private Prospeccao idProspeccao;   
	@ManyToOne @JoinColumn(name="idContato") private Contato idContato;
	
	
	
// ------------ Datas ------------------//
    @Temporal(TemporalType.TIMESTAMP) private Date dataProximaInteracao;
    @Temporal(TemporalType.TIMESTAMP) private Calendar dataInteracao;
    
// ------------ Transiente -------------//
    @Transient private Integer idEmp;
    @Transient private String pd;
    @Transient private String ph;
    
	@Transient private Integer idContatoContato;
	@Transient private Integer idUsuarioUsuario;
	@Transient private Integer idJobJob;
	@Transient private String tituloJob;
	
	@Transient private List<Integer> multiplo;
	@Transient private String codInterno;
	
	
	
// ----------------------------------------------------------- //
	
	/*public Prospeccao getIdProspeccao() {
		return idProspeccao;
	}
	public void setIdProspeccao(Prospeccao idProspeccao) {
		this.idProspeccao = idProspeccao;
	}*/
	public String getCodInterno() {
		return codInterno;
	}
	public void setCodInterno(String codInterno) {
		this.codInterno = codInterno;
	}
	public String getTituloJob() {
		return tituloJob;
	}
	public void setTituloJob(String tituloJob) {
		this.tituloJob = tituloJob;
	}
	public List<Integer> getMultiplo() {
		return multiplo;
	}
	public void setMultiplo(List<Integer> multiplo) {
		this.multiplo = multiplo;
	}
	public Usuario getIdUsuarioInterno() {
		return idUsuarioInterno;
	}
	public void setIdUsuarioInterno(Usuario idUsuarioInterno) {
		this.idUsuarioInterno = idUsuarioInterno;
	}
	public Integer getIdJobJob() {
		return idJobJob;
	}
	public void setIdJobJob(Integer idJobJob) {
		this.idJobJob = idJobJob;
	}
	public Integer getIdUsuarioUsuario() {
		return idUsuarioUsuario;
	}
	public void setIdUsuarioUsuario(Integer idUsuarioUsuario) {
		this.idUsuarioUsuario = idUsuarioUsuario;
	}
    public Integer getIdContatoContato() {
		return idContatoContato;
	}
	public void setIdContatoContato(Integer idContatoContato) {
		this.idContatoContato = idContatoContato;
	}
	public Integer getIdEmp() {
		return idEmp;
	}
	public void setIdEmp(Integer idEmp) {
		this.idEmp = idEmp;
	}

	public String getPd() {
		return pd;
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
	public Integer getIdInteracao() {
		return idInteracao;
	}
	public void setIdInteracao(Integer idInteracao) {
		this.idInteracao = idInteracao;
	}
		
	
	public boolean isAgendar() {
		return agendar;
	}
	public void setAgendar(boolean agendar) {
		this.agendar = agendar;
	}
	
	public boolean isInterno() {
		return interno;
	}
	public void setInterno(boolean interno) {
		this.interno = interno;
	}
	public String getAutomatico() {
		return automatico;
	}
	public void setAutomatico(String automatico) {
		this.automatico = automatico;
	}
	public String getInteracao() {
		return interacao;
	}
	public void setInteracao(String interacao) {
		this.interacao = interacao;
	}
	public String getInteracaoOrigem() {
		return interacaoOrigem;
	}
	public void setInteracaoOrigem(String interacaoOrigem) {
		this.interacaoOrigem = interacaoOrigem;
	}
	public String getInteracaoForma() {
		return interacaoForma;
	}
	public void setInteracaoForma(String interacaoForma) {
		this.interacaoForma = interacaoForma;
	}
	
	
	public Usuario getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	public Job getIdOrigem() {
		return idOrigem;
	}
	public void setIdOrigem(Job idOrigem) {
		this.idOrigem = idOrigem;
	}

	public Contato getIdContato() {
		return idContato;
	}

	public void setIdContato(Contato idContato) {
		this.idContato = idContato;
	}
	
	public Calendar getDataInteracao() {
		return dataInteracao;
	}
	
	public void setDataInteracao(Calendar dataInteracao) {
		this.dataInteracao = dataInteracao;
	}


	public Date getDataProximaInteracao() {
		return dataProximaInteracao;
	}
	
	
	public void setDataProximaInteracao(Date dataProximaInteracao) {
		this.dataProximaInteracao = dataProximaInteracao;
	}
	
// ------------ Transiente --------------//
    
	
	
	
	
}
