package br.com.sysloccOficial.financeiro.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class MovimentacaoBancos {
	

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idMovBancos;
	private String ndnf;
	private String descricao;
	private BigDecimal valor;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	
// ------------------------------------------------------------------ //	
	@OneToOne @JoinColumn(name="analitico") private FinancAnalitico analitico;
	@OneToOne @JoinColumn(name="banco") private BancosAnalitico banco;
// ------------------------------------------------------------------ //	

	public Integer getIdMovBancos() {
		return idMovBancos;
	}
	public void setIdMovBancos(Integer idMovBancos) {
		this.idMovBancos = idMovBancos;
	}
	public String getNdnf() {
		return ndnf;
	}
	public void setNdnf(String ndnf) {
		this.ndnf = ndnf;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public FinancAnalitico getAnalitico() {
		return analitico;
	}
	public void setAnalitico(FinancAnalitico analitico) {
		this.analitico = analitico;
	}
	public BancosAnalitico getBanco() {
		return banco;
	}
	public void setBanco(BancosAnalitico banco) {
		this.banco = banco;
	}
}
