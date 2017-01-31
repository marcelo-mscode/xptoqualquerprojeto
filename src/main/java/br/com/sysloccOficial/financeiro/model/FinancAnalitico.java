package br.com.sysloccOficial.financeiro.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class FinancAnalitico {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idAnalitico;
	private String mesA;
	private String anoA;
	private Integer mesReferencia;
	
// ------------------------------------------------------------ //	

	@OneToMany (mappedBy="analitico")private List<FinancTelefone> telefone;
	@OneToMany (mappedBy="analitico")private List<FinancEscritorio> escritorio; 
	@OneToMany (mappedBy="analitico")private List<FinancFolhaPgto> folha; 
	@OneToMany (mappedBy="analitico")private List<FinancDespesas> despesas; 
	@OneToMany (mappedBy="analitico")private List<FinancImpostos> impostos; 
	
// ------------------------------------------------------------ //	
	
	public Integer getIdAnalitico() {
		return idAnalitico;
	}

	public Integer getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(Integer mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public List<FinancImpostos> getImpostos() {
		return impostos;
	}

	public void setImpostos(List<FinancImpostos> impostos) {
		this.impostos = impostos;
	}

	public List<FinancDespesas> getDespesas() {
		return despesas;
	}


	public void setDespesas(List<FinancDespesas> despesas) {
		this.despesas = despesas;
	}


	public List<FinancFolhaPgto> getFolha() {
		return folha;
	}

	public void setFolha(List<FinancFolhaPgto> folha) {
		this.folha = folha;
	}

	public List<FinancEscritorio> getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(List<FinancEscritorio> escritorio) {
		this.escritorio = escritorio;
	}

	public List<FinancTelefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<FinancTelefone> telefone) {
		this.telefone = telefone;
	}


	public void setIdAnalitico(Integer idAnalitico) {
		this.idAnalitico = idAnalitico;
	}
	public String getMesA() {
		return mesA;
	}
	public void setMesA(String mesA) {
		this.mesA = mesA;
	}
	public String getAnoA() {
		return anoA;
	}
	public void setAnoA(String anoA) {
		this.anoA = anoA;
	}
	
	
	
	

}
