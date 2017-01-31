 package br.com.sysloccOficial.model;

import javax.persistence.*;



@Entity
public class Comunicador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idComunicador;
	private String  comunicador;
	private String  comunicadorDesc;
	private String  comunicadorOrigem;
	private boolean comunicadorTelefone;
	private boolean comunicadorEmail;

	
	@Transient private Integer idCont;

// ---------------------- Relacionamentos ------------------------------//
	
	@ManyToOne @JoinColumn(name="idContato") private Contato contato;
	
	
	public Integer getIdCont() {
		return idCont;
	}
	public void setIdCont(Integer idCont) {
		this.idCont = idCont;
	}	
	
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public Integer getIdComunicador() {
		return idComunicador;
	}
	public void setIdComunicador(Integer idComunicador) {
		this.idComunicador = idComunicador;
	}
	public String getComunicador() {
		return comunicador;
	}
	public void setComunicador(String comunicador) {
		this.comunicador = comunicador;
	}
	public String getComunicadorDesc() {
		return comunicadorDesc;
	}
	public void setComunicadorDesc(String comunicadorDesc) {
		this.comunicadorDesc = comunicadorDesc;
	}
	public String getComunicadorOrigem() {
		return comunicadorOrigem;
	}
	public void setComunicadorOrigem(String comunicadorOrigem) {
		this.comunicadorOrigem = comunicadorOrigem;
	}
	public boolean isComunicadorTelefone() {
		return comunicadorTelefone;
	}
	public void setComunicadorTelefone(boolean comunicadorTelefone) {
		this.comunicadorTelefone = comunicadorTelefone;
	}
	public boolean isComunicadorEmail() {
		return comunicadorEmail;
	}
	public void setComunicadorEmail(boolean comunicadorEmail) {
		this.comunicadorEmail = comunicadorEmail;
	}
	
	
	
	
	

	
	
		
	
	
	
	
}
