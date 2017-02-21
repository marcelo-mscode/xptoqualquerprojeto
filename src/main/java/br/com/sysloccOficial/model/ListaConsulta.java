package br.com.sysloccOficial.model;

public class ListaConsulta {

	private Integer idLista;
	private Integer revisao;
	
	private Integer numCenarioGalderma;
	
	private String lista;
	private String empresa;
	private String job;
	private String status;
	private String listaCod;
	
	
	public Integer getNumCenarioGalderma() {
		return numCenarioGalderma;
	}

	public void setNumCenarioGalderma(Integer numCenarioGalderma) {
		this.numCenarioGalderma = numCenarioGalderma;
	}

	public String getListaCod() {
		return listaCod;
	}

	public void setListaCod(String listaCod) {
		this.listaCod = listaCod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Integer getRevisao() {
		return revisao;
	}

	public void setRevisao(Integer revisao) {
		this.revisao = revisao;
	}

	public Integer getIdLista() {
		return idLista;
	}

	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}

	public String getLista() {
		return lista;
	}

	public void setLista(String lista) {
		this.lista = lista;
	}
	
	
}
