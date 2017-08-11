package br.com.sysloccOficial.financeiro.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MovimentacaoBancosSaldoAnterior {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idMovBancos;
	private BigDecimal valorAbertura;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAberturaCaixa;
	
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
	public BigDecimal getValorAbertura() {
		return valorAbertura;
	}
	public void setValorAbertura(BigDecimal valorAbertura) {
		this.valorAbertura = valorAbertura;
	}
	public Date getDataAberturaCaixa() {
		return dataAberturaCaixa;
	}
	public void setDataAberturaCaixa(Date dataAberturaCaixa) {
		this.dataAberturaCaixa = dataAberturaCaixa;
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
