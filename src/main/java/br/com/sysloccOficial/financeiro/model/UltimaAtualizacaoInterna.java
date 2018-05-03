package br.com.sysloccOficial.financeiro.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UltimaAtualizacaoInterna implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idUltimaAtualizacao;
	private String Usuario;
	private int idLista;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAtualizacao;

	public int getIdUltimaAtualizacao() {
		return idUltimaAtualizacao;
	}

	public void setIdUltimaAtualizacao(int idUltimaAtualizacao) {
		this.idUltimaAtualizacao = idUltimaAtualizacao;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}
}
