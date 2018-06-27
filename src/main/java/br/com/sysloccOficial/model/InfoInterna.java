package br.com.sysloccOficial.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysloccOficial.model.producao.ProducaoP;


@Entity
public class InfoInterna {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idInfoInterna;
	private String nfInterna;
	private Date dataPagamento;
	private boolean recebido;
	private double impostoInterna;
	private boolean ndInterna;
	
	
// ---------------------------------------------------------------- //	

	@OneToOne @JoinColumn(name="producaoP") private ProducaoP producaoP;
	@OneToOne @JoinColumn(name="idLista") private Lista lista;
	@Temporal(TemporalType.TIMESTAMP)private Calendar dataRecebido;
	
// ---------------------------------------------------------------- //	
	public Integer getIdInfoInterna() {
		return idInfoInterna;
	}
	public boolean isNdInterna() {
		return ndInterna;
	}
	public void setNdInterna(boolean ndInterna) {
		this.ndInterna = ndInterna;
	}
	public double getImpostoInterna() {
		return impostoInterna;
	}
	public void setImpostoInterna(double impostoInterna) {
		this.impostoInterna = impostoInterna;
	}
	public Calendar getDataRecebido() {
		return dataRecebido;
	}
	public void setDataRecebido(Calendar dataRecebido) {
		this.dataRecebido = dataRecebido;
	}
	public boolean isRecebido() {
		return recebido;
	}
	public void setRecebido(boolean recebido) {
		this.recebido = recebido;
	}
	public Lista getLista() {
		return lista;
	}
	public void setLista(Lista lista) {
		this.lista = lista;
	}
	public ProducaoP getProducaoP() {
		return producaoP;
	}
	public void setProducaoP(ProducaoP producaoP) {
		this.producaoP = producaoP;
	}
	public void setIdInfoInterna(Integer idInfoInterna) {
		this.idInfoInterna = idInfoInterna;
	}
	public String getNfInterna() {
		return nfInterna;
	}
	public void setNfInterna(String nfInterna) {
		this.nfInterna = nfInterna;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	
	
	
	
	
	
	
}
