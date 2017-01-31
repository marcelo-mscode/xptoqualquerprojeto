package br.com.sysloccOficial.model.prospeccao;

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

import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.Usuario;


@Entity
public class InteracaoProspeccao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInteracao;
	private String interacao; 
	private boolean interno; //1 - Se a interacao foi internamente ou 0 - com o cliente
	private String interacaoForma;

	private boolean agendar;
	
	private String automatico;
	private String interacaoOrigem;
	
	
// ------------ Relacionamentos ---------//
	@ManyToOne @JoinColumn(name="idUsuario") private Usuario idUsuario;
	@ManyToOne @JoinColumn(name="idUsuarioInterno") private Usuario idUsuarioInterno;
//	@ManyToOne @JoinColumn(name="idOrigem")  private Job idOrigem;   
	@ManyToOne @JoinColumn(name="idOrigem")  private Prospeccao idProspeccao;   
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
	@Transient private Integer idProspeccaoTrans;
	
	@Transient private List<Integer> multiplo;
	@Transient private String codInterno;
// -------------------------------------//
	
	
	public Integer getIdInteracao() {
		return idInteracao;
	}
	public Integer getIdProspeccaoTrans() {
		return idProspeccaoTrans;
	}
	public void setIdProspeccaoTrans(Integer idProspeccaoTrans) {
		this.idProspeccaoTrans = idProspeccaoTrans;
	}
	public void setIdInteracao(Integer idInteracao) {
		this.idInteracao = idInteracao;
	}
	public String getInteracao() {
		return interacao;
	}
	public void setInteracao(String interacao) {
		this.interacao = interacao;
	}
	public boolean isInterno() {
		return interno;
	}
	public void setInterno(boolean interno) {
		this.interno = interno;
	}
	public String getInteracaoForma() {
		return interacaoForma;
	}
	public void setInteracaoForma(String interacaoForma) {
		this.interacaoForma = interacaoForma;
	}
	public boolean isAgendar() {
		return agendar;
	}
	public void setAgendar(boolean agendar) {
		this.agendar = agendar;
	}
	public String getAutomatico() {
		return automatico;
	}
	public void setAutomatico(String automatico) {
		this.automatico = automatico;
	}
	public String getInteracaoOrigem() {
		return interacaoOrigem;
	}
	public void setInteracaoOrigem(String interacaoOrigem) {
		this.interacaoOrigem = interacaoOrigem;
	}
	public Usuario getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Usuario getIdUsuarioInterno() {
		return idUsuarioInterno;
	}
	public void setIdUsuarioInterno(Usuario idUsuarioInterno) {
		this.idUsuarioInterno = idUsuarioInterno;
	}
	public Prospeccao getIdProspeccao() {
		return idProspeccao;
	}
	public void setIdProspeccao(Prospeccao idProspeccao) {
		this.idProspeccao = idProspeccao;
	}
	public Contato getIdContato() {
		return idContato;
	}
	public void setIdContato(Contato idContato) {
		this.idContato = idContato;
	}
	public Date getDataProximaInteracao() {
		return dataProximaInteracao;
	}
	public void setDataProximaInteracao(Date dataProximaInteracao) {
		this.dataProximaInteracao = dataProximaInteracao;
	}
	public Calendar getDataInteracao() {
		return dataInteracao;
	}
	public void setDataInteracao(Calendar dataInteracao) {
		this.dataInteracao = dataInteracao;
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
	public Integer getIdContatoContato() {
		return idContatoContato;
	}
	public void setIdContatoContato(Integer idContatoContato) {
		this.idContatoContato = idContatoContato;
	}
	public Integer getIdUsuarioUsuario() {
		return idUsuarioUsuario;
	}
	public void setIdUsuarioUsuario(Integer idUsuarioUsuario) {
		this.idUsuarioUsuario = idUsuarioUsuario;
	}
	public List<Integer> getMultiplo() {
		return multiplo;
	}
	public void setMultiplo(List<Integer> multiplo) {
		this.multiplo = multiplo;
	}
	public String getCodInterno() {
		return codInterno;
	}
	public void setCodInterno(String codInterno) {
		this.codInterno = codInterno;
	}
	
}
