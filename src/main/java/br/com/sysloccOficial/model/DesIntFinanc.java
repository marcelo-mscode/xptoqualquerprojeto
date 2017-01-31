package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class DesIntFinanc {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idDesp;
	private Integer idLista;
	
	private String descricao;
	private String descricao2;
	private String desc2;
	private String observacao;
	private BigDecimal valor1;
	private BigDecimal valor2;
	private BigDecimal valor3;
	private BigDecimal locco;
	private BigDecimal cCredito;
	private Date data;

	
// ----------------------------------------------------------- //	
	@Transient private Integer IdListaTransiente;
	@Transient private String dataTransiente;
	@Transient private String valor1Transiente;
	@Transient private String valor2Transiente;
	@Transient private String valor3Transiente;
	@Transient private String loccoTransiente;
	@Transient private String cCreditoTransiente;
// ----------------------------------------------------------- //	
	
	
	
	public String getObservacao() {
		return observacao;
	}
	public String getValor1Transiente() {
		return valor1Transiente;
	}
	public void setValor1Transiente(String valor1Transiente) {
		this.valor1Transiente = valor1Transiente;
	}
	public String getValor2Transiente() {
		return valor2Transiente;
	}
	public void setValor2Transiente(String valor2Transiente) {
		this.valor2Transiente = valor2Transiente;
	}
	public String getValor3Transiente() {
		return valor3Transiente;
	}
	public void setValor3Transiente(String valor3Transiente) {
		this.valor3Transiente = valor3Transiente;
	}
	public String getLoccoTransiente() {
		return loccoTransiente;
	}
	public void setLoccoTransiente(String loccoTransiente) {
		this.loccoTransiente = loccoTransiente;
	}
	public String getcCreditoTransiente() {
		return cCreditoTransiente;
	}
	public void setcCreditoTransiente(String cCreditoTransiente) {
		this.cCreditoTransiente = cCreditoTransiente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDataTransiente() {
		return dataTransiente;
	}
	public void setDataTransiente(String dataTransiente) {
		this.dataTransiente = dataTransiente;
	}
	public Integer getIdLista() {
		return idLista;
	}
	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public BigDecimal getcCredito() {
		return cCredito;
	}
	public void setcCredito(BigDecimal cCredito) {
		this.cCredito = cCredito;
	}
	public String getDescricao2() {
		return descricao2;
	}
	public void setDescricao2(String descricao2) {
		this.descricao2 = descricao2;
	}
	public BigDecimal getLocco() {
		return locco;
	}

	public void setLocco(BigDecimal locco) {
		this.locco = locco;
	}




	public Integer getIdDesp() {
		return idDesp;
	}




	public void setIdDesp(Integer idDesp) {
		this.idDesp = idDesp;
	}




	public String getDesc() {
		return descricao;
	}




	public void setDesc(String desc) {
		this.descricao = desc;
	}




	public String getDesc2() {
		return desc2;
	}




	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}




	public BigDecimal getValor1() {
		return valor1;
	}




	public void setValor1(BigDecimal valor1) {
		this.valor1 = valor1;
	}




	public BigDecimal getValor2() {
		return valor2;
	}




	public void setValor2(BigDecimal valor2) {
		this.valor2 = valor2;
	}




	public BigDecimal getValor3() {
		return valor3;
	}




	public void setValor3(BigDecimal valor3) {
		this.valor3 = valor3;
	}




	public Integer getIdListaTransiente() {
		return IdListaTransiente;
	}




	public void setIdListaTransiente(Integer idListaTransiente) {
		IdListaTransiente = idListaTransiente;
	}
	
	
	
	
}
