package br.com.sysloccOficial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Configuracao {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer idconfiguracao;

private	double  margemPadrao;
private double  administracaoPadrao;
private	String  textoFinalPadrao;
private	String  textoFinalPadrao2;

	public Integer getIdconfiguracao() {
		return idconfiguracao;
	}
	public void setIdconfiguracao(Integer idconfiguracao) {
		this.idconfiguracao = idconfiguracao;
	}
	public double getMargemPadrao() {
		return margemPadrao;
	}
	public void setMargemPadrao(double margemPadrao) {
		this.margemPadrao = margemPadrao;
	}
	public double getAdministracaoPadrao() {
		return administracaoPadrao;
	}
	public void setAdministracaoPadrao(double administracaoPadrao) {
		this.administracaoPadrao = administracaoPadrao;
	}
	public String getTextoFinalPadrao() {
		return textoFinalPadrao;
	}
	public void setTextoFinalPadrao(String textoFinalPadrao) {
		this.textoFinalPadrao = textoFinalPadrao;
	}
	public String getTextoFinalPadrao2() {
		return textoFinalPadrao2;
	}
	public void setTextoFinalPadrao2(String textoFinalPadrao2) {
		this.textoFinalPadrao2 = textoFinalPadrao2;
	}
	
}
