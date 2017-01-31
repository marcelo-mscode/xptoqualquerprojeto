package br.com.sysloccOficial.model.prospeccao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.com.sysloccOficial.model.Empresa;

@Entity
public class Prospeccao {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idProspeccao;
	private String titulo;
	private Integer probabilidade;
	private boolean concluido;
	
	
// ----------------------------------------------------------- //	
	
	@OneToMany(mappedBy = "idProspeccao") private List<InteracaoProspeccao> interacao;
	@OneToOne @JoinColumn(name="idEmpresa") private Empresa idEmpresa;
	
// ----------------------------------------------------------- //	

	@Transient private Integer idEmpresaTrans;
// ----------------------------------------------------------- //	
	
	public Integer getIdProspeccao() {
		return idProspeccao;
	}
	public List<InteracaoProspeccao> getInteracao() {
		return interacao;
	}
	public void setInteracao(List<InteracaoProspeccao> interacao) {
		this.interacao = interacao;
	}
	public Integer getIdEmpresaTrans() {
		return idEmpresaTrans;
	}
	public void setIdEmpresaTrans(Integer idEmpresaTrans) {
		this.idEmpresaTrans = idEmpresaTrans;
	}
	public void setIdProspeccao(Integer idProspeccao) {
		this.idProspeccao = idProspeccao;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getProbabilidade() {
		return probabilidade;
	}
	public void setProbabilidade(int probabilidade) {
		this.probabilidade = probabilidade;
	}
	public boolean isConcluido() {
		return concluido;
	}
	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
	public Empresa getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public void setProbabilidade(Integer probabilidade) {
		this.probabilidade = probabilidade;
	}
	
}
