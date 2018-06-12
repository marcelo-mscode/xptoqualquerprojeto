package br.com.sysloccOficial.model.producao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Producao;

@Entity
public class FornecedorFinanceiro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idFornecedor;
	
	private boolean contratacao;
	private double  imposto;
	private Integer condicaoPagamento;  // numero de parcelas
	private String tipoPagamento;
//  private StatusFinanceiro status;
	
		
// -------------------------------------------------------- //	
	@OneToOne @JoinColumn(name="idProducao")private ProducaoP idProducao;
	@OneToOne @JoinColumn(name="idEmpresa") private Empresa  idEmpresa;
	
	@OneToMany (mappedBy="idFornecedorFinanceiro", fetch=FetchType.LAZY) private List<ValorPagtoFornecedor> idValorPgtoFornecedor;
	
// -------------------------------------------------------- //	
	
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public Integer getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public List<ValorPagtoFornecedor> getIdValorPgtoFornecedor() {
		return idValorPgtoFornecedor;
	}
	public void setIdValorPgtoFornecedor(
			List<ValorPagtoFornecedor> idValorPgtoFornecedor) {
		this.idValorPgtoFornecedor = idValorPgtoFornecedor;
	}
	
	public boolean isContratacao() {
		return contratacao;
	}
	public void setContratacao(boolean contratacao) {
		this.contratacao = contratacao;
	}
	public double getImposto() {
		return imposto;
	}
	public void setImposto(double imposto) {
		this.imposto = imposto;
	}
	public Integer getCondicaoPagamento() {
		return condicaoPagamento;
	}
	public void setCondicaoPagamento(Integer condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}
	/*public Producao getIdProducao() {
		return idProducao;
	}
	public void setIdProducao(Producao idProducao) {
		this.idProducao = idProducao;
	}*/
	public Empresa getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public ProducaoP getIdProducao() {
		return idProducao;
	}
	public void setIdProducao(ProducaoP idProducao) {
		this.idProducao = idProducao;
	}

}
