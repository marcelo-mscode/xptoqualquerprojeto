package br.com.sysloccOficial.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;



@Entity
public class NivelAcesso implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer idAcesso;
	private String nivel;
	
	
	
	@OneToMany(mappedBy="nivelAcesso") private List<Usuario> nivelAcessoUsuario;
	
	
	
	public List<Usuario> getNivelAcessoUsuario() {
		return nivelAcessoUsuario;
	}
	public void setNivelAcessoUsuario(List<Usuario> nivelAcessoUsuario) {
		this.nivelAcessoUsuario = nivelAcessoUsuario;
	}
	public Integer getIdAcesso() {
		return idAcesso;
	}
	public void setIdAcesso(Integer idAcesso) {
		this.idAcesso = idAcesso;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	
	
	
	
}
