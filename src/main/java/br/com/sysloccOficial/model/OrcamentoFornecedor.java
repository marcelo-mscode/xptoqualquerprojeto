package br.com.sysloccOficial.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import br.com.sysloccOficial.model.ProdutoGrupo;

@Entity
public class OrcamentoFornecedor {
	
	

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idOrcamento;
	private BigDecimal valorOrcamento; 
	

	
// ------------------------------------------------- //
	@OneToOne private @JoinColumn(name="grupo") Grupo grupo;
	@OneToOne private @JoinColumn(name="empresa") Empresa empresa;
	@OneToOne private @JoinColumn(name="produto") ProdutoGrupo produto;
// ------------------------------------------------- //
	
	public Integer getIdOrcamento() {
		return idOrcamento;
	}
	public ProdutoGrupo getProduto() {
		return produto;
	}
	public void setProduto(ProdutoGrupo produto) {
		this.produto = produto;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public void setIdOrcamento(Integer idOrcamento) {
		this.idOrcamento = idOrcamento;
	}
	public BigDecimal getValorOrcamento() {
		return valorOrcamento;
	}
	public void setValorOrcamento(BigDecimal valorOrcamento) {
		this.valorOrcamento = valorOrcamento;
	}
}
