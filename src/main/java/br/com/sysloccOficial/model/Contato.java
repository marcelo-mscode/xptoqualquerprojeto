package br.com.sysloccOficial.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idContato;
	
	@OrderBy
	private String contato;
	
	
	private String cargo;
	private boolean habilitado;
	private String obs;
	/*private String telefone;
	private String email;*/
	

// -------------- Transientes ------------------------//	
	@Transient
	private Integer idEmp;

// ---------------- Datas ----------------------------//	
	@ManyToOne @JoinColumn(name= "idEmpresa")private Empresa empresa;
	@OneToMany(mappedBy="idUsuarioInterno")	private List<Interacao> idContatoInteracao;
	@OneToMany(mappedBy="contatos")	private List<Job> job;
	@OneToMany(mappedBy="contato")  private List<Comunicador> comunicador;
	
// ---------------Relacionamentos --------------------//	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}

	
	
	public List<Comunicador> getComunicador() {
		return comunicador;
	}

	public void setComunicador(List<Comunicador> comunicador) {
		this.comunicador = comunicador;
	}

	public Integer getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(Integer idEmp) {
		this.idEmp = idEmp;
	}

	
	
	/*public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}*/

	public List<Interacao> getIdContatoInteracao() {
		return idContatoInteracao;
	}

	public void setIdContatoInteracao(List<Interacao> idContatoInteracao) {
		this.idContatoInteracao = idContatoInteracao;
	}

	public Integer getIdContato() {
		return idContato;
	}

	public void setIdContato(Integer idContato) {
		this.idContato = idContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
	
	
	
}
