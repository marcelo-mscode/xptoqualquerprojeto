package br.com.sysloccOficial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmpresaAtuacao {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmpresaAtuacao;

	
// --------- Relacionamentos ------------ //	
	
	@ManyToOne
	@JoinColumn(name="idAtuacao")
	private Atuacao idAtuacao;

	@ManyToOne
	@JoinColumn(name="idEmpresa")
	private Empresa idEmpresa;

	public Integer getIdEmpresaAtuacao() {
		return idEmpresaAtuacao;
	}

	public void setIdEmpresaAtuacao(Integer idEmpresaAtuacao) {
		this.idEmpresaAtuacao = idEmpresaAtuacao;
	}

	public Atuacao getIdAtuacao() {
		return idAtuacao;
	}

	public void setIdAtuacao(Atuacao idAtuacao) {
		this.idAtuacao = idAtuacao;
	}

	public Empresa getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

// -------------------------------------- //
	
	

}
