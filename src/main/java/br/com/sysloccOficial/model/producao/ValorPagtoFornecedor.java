package br.com.sysloccOficial.model.producao;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.sysloccOficial.model.Empresa;

@Entity
public class ValorPagtoFornecedor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idValorFinancForn;
	
	private Integer parcela;
	private BigDecimal valor;
	private Integer diasPrazoParaPagamento;
	
//  private StatusFinanceiro status;
	
	
// --------------------------------------------------- //	
//	@OneToOne @JoinColumn(name="idFornFinanceiro", referencedColumnName = "idFornecedor") private FornecedorFinanceiro idFornFinanceiro;
	@OneToOne @JoinColumn(name="idFornecedorFinanceiro", referencedColumnName = "idFornecedor") private FornecedorFinanceiro idFornecedorFinanceiro;
	@OneToOne @JoinColumn(name="idEmpresa")		   private Empresa idEmpresa;	
	@OneToOne (mappedBy="valorPgtoForn")		   private DtPgtoFornecedor dtPgotFornecedor;
	
  /*@OneToOne @JoinColumn(name="idProducao") 	   private Producao idProducao;*/	
/*	@OneToOne @JoinColumn(name="idLista")		   private Lista idLista;*/	
// --------------------------------------------------- //	
	
	
	public Integer getIdValorFinancForn() {
		return idValorFinancForn;
	}
	public FornecedorFinanceiro getIdFornecedorFinanceiro() {
		return idFornecedorFinanceiro;
	}
	public void setIdFornecedorFinanceiro(
			FornecedorFinanceiro idFornecedorFinanceiro) {
		this.idFornecedorFinanceiro = idFornecedorFinanceiro;
	}
	public DtPgtoFornecedor getDtPgotFornecedor() {
		return dtPgotFornecedor;
	}
	public void setDtPgotFornecedor(DtPgtoFornecedor dtPgotFornecedor) {
		this.dtPgotFornecedor = dtPgotFornecedor;
	}
	public Integer getDiasPrazoParaPagamento() {
		return diasPrazoParaPagamento;
	}
	public void setDiasPrazoParaPagamento(Integer diasPrazoParaPagamento) {
		this.diasPrazoParaPagamento = diasPrazoParaPagamento;
	}
	public void setIdValorFinancForn(Integer idValorFinancForn) {
		this.idValorFinancForn = idValorFinancForn;
	}
	public Integer getParcela() {
		return parcela;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	/*public FornecedorFinanceiro getIdFornFinanceiro() {
		return idFornFinanceiro;
	}
	public void setIdFornFinanceiro(FornecedorFinanceiro idFornFinanceiro) {
		this.idFornFinanceiro = idFornFinanceiro;
	}*/
	public Empresa getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	/*public Producao getIdProducao() {
		return idProducao;
	}
	public void setIdProducao(Producao idProducao) {
		this.idProducao = idProducao;
	}*/
	/*public Lista getIdLista() {
		return idLista;
	}
	public void setIdLista(Lista idLista) {
		this.idLista = idLista;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
