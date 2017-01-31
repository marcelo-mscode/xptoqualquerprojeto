package br.com.sysloccOficial.model.prospeccao;

import javax.swing.JOptionPane;

public class FiltraInteracao {
	
//	private String idProspeccao;
	private String idEmpresa;
	private boolean idEmpresaConfere;
	
	private boolean tituloConfere;
	private String titulo;
	
	private boolean dataConfere;
	private String dataInicio;
	
	private String tipoData;
	
	private String qtoDias;
	
	private boolean concluidos;
	
// -------------------------------------------- //	

	
	public String testaTitulo(String consulta, FiltraInteracao f) {
		if(f.getTitulo() != null && f.isTituloConfere() == true){
			consulta = consulta + " and i.idProspeccao.titulo = '"+titulo+"'";
		}
		return consulta;
	}

	public String testaConcluido(String consulta, boolean concluido) {
		if(concluido == true){
			consulta = consulta + " and i.idProspeccao.concluido = 1 ";
		}
		return consulta;
	}
	
	public String testaEmpresa(String consulta, FiltraInteracao f) {
		if(f.getIdEmpresa()!= null && f.isIdEmpresaConfere() == true){
			consulta = consulta + "and i.idProspeccao.idEmpresa.idEmpresa = "+idEmpresa;
		}
		return consulta;
	}
	
	public String testaData(String consulta, FiltraInteracao f){
		if(f.isDataConfere() == true && f.getData() != null && f.getQtoDias() != null){
			if(f.getTipoData().equals("interacao")){
				consulta = consulta + "and dataInteracao >= '"+f.getData()+"'";
			}
			if(f.getTipoData().equals("agendamento")){
				consulta = consulta + "and dataInteracao >= '"+f.getData()+"' and dataProximaInteracao != '0001-01-01 00:00:00'";
			}
		}
		return consulta;
	}
	
	
// -------------------------------------------- //	
	
	
	public boolean isConcluidos() {
		return concluidos;
	}

	public boolean isDataConfere() {
		return dataConfere;
	}

	public void setDataConfere(boolean dataConfere) {
		this.dataConfere = dataConfere;
	}

	public String getTipoData() {
		return tipoData;
	}

	public void setTipoData(String tipoData) {
		this.tipoData = tipoData;
	}

	public String getQtoDias() {
		return qtoDias;
	}

	public void setQtoDias(String qtoDias) {
		this.qtoDias = qtoDias;
	}

	public boolean isTituloConfere() {
		return tituloConfere;
	}

	public void setTituloConfere(boolean tituloConfere) {
		this.tituloConfere = tituloConfere;
	}

	public boolean isIdEmpresaConfere() {
		return idEmpresaConfere;
	}

	public void setIdEmpresaConfere(boolean idEmpresaConfere) {
		this.idEmpresaConfere = idEmpresaConfere;
	}

	public void setConcluidos(boolean concluidos) {
		this.concluidos = concluidos;
	}

	public String getData() {
		return dataInicio;
	}

	public void setData(String data) {
		this.dataInicio = data;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

// ------------------------------------------------------------------------- //	
	
}
