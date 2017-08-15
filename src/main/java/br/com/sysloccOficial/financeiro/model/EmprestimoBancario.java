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
public class EmprestimoBancario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idEmprestimo;
	private BigDecimal valorParcela;
	private int diaMesPagamento;
	private int quantidadeParcelas;
	private String descricao;
	private boolean pago;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPrimeiroPagamento;
	
	
// ------------------------------------------------------------------ //	
	@OneToOne @JoinColumn(name="analitico") private FinancAnalitico analitico;
	@OneToOne @JoinColumn(name="banco") private BancosAnalitico banco;
// ------------------------------------------------------------------ //	

	
	public Integer getIdEmprestimo() {
		return idEmprestimo;
	}
	public void setIdEmprestimo(Integer idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	public int getDiaMesPagamento() {
		return diaMesPagamento;
	}
	public void setDiaMesPagamento(int diaMesPagamento) {
		this.diaMesPagamento = diaMesPagamento;
	}
	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	public Date getDataPrimeiroPagamento() {
		return dataPrimeiroPagamento;
	}
	public void setDataPrimeiroPagamento(Date dataPrimeiroPagamento) {
		this.dataPrimeiroPagamento = dataPrimeiroPagamento;
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
