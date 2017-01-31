package br.com.sysloccOficial.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ListaEstatus {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idlistaEstatus;
	private String listaEstatus;
	
// --------------------- Relacionamentos ------------------------ //
	
	@OneToMany(mappedBy = "idlistaEstatus") private List<Lista> listaTeste;

	public Integer getIdlistaEstatus() {
		return idlistaEstatus;
	}

	public void setIdlistaEstatus(Integer idlistaEstatus) {
		this.idlistaEstatus = idlistaEstatus;
	}

	public String getListaEstatus() {
		return listaEstatus;
	}

	public void setListaEstatus(String listaEstatus) {
		this.listaEstatus = listaEstatus;
	}

	public List<Lista> getListaTeste() {
		return listaTeste;
	}

	public void setListaTeste(List<Lista> listaTeste) {
		this.listaTeste = listaTeste;
	}
	
// -------------------------------------------------------------- //	

	
	
	
	
	
}
