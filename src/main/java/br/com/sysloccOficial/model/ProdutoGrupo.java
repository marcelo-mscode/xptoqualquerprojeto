package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sysloccOficial.model.producao.CartaContFornecedor;
import br.com.sysloccOficial.model.producao.ProducaoP;

@Entity
public class ProdutoGrupo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProdutoGrupo;
	
	
	private BigDecimal precoProduto;
	private BigDecimal precoProdutoQuantidade;
	private BigDecimal bv;
	private BigDecimal bvFornecedorValor;
	
	private BigDecimal custoProduto;
	
	private BigDecimal bvFornecedor;
	private Integer status;
	
	private double diarias;     // Alterado para suportar ponto e virgula
	@Transient private String diariasTransiente;

	private double quantidade; // Alterado para suportar ponto e virgula
	@Transient private String quantidadeTransiente;
	
	private double quantidade2; // Alterado para suportar ponto e virgula
	@Transient private String quantidade2Transiente;

	
	
	
	private String observacoes;
	private String produto;
	private String tidProdutoGrupo;
	
	private boolean margemPadrao;
	private boolean imposto;
	private boolean custoComBvFornecedor;
	private boolean bvFornecedorEmPorcentagem;

	
	
// ------------------------------------------ //	
	@Transient private boolean salvarEfechar;
	@Transient private Integer idprodutoTransiente;
	@Transient private Integer idGrupoTransiente;
	@Transient private Integer idEmpresaTransiente;
	@Transient private String bvTransiente;
	@Transient private String custoProdutoTransiente;
	@Transient private String bvFornecedorTransiente;
	
// ------------------ Datas ----------------- //
	@Temporal(TemporalType.TIMESTAMP) private Calendar alteradoEm;
	
// --------------------------------------- //	
	@ManyToOne @JoinColumn(name="idGrupo")   private Grupo idGrupo;
	@ManyToOne @JoinColumn(name="idProduto") private Produto idProduto;
	@ManyToOne @JoinColumn(name="fornecedor_item") private Empresa empresa;
	
	@OneToOne @JoinColumn(name="alteradoPor") private Usuario usuario;
	@OneToOne(mappedBy="produtoGrupo", cascade=CascadeType.REMOVE) private DeterminaQuantidades determQuantidade;

	
	@OneToOne(mappedBy="prodGrupo")private CartaContFornecedor cartaFornecedor;
//  @OneToOne(mappedBy="produtoGrupo")	 	 private Producao producao;
    @OneToOne(mappedBy="produtoGrupo", fetch = FetchType.LAZY)	private ProducaoP producaop;
	
// --------------------------------------- //	
	
    
    
	public Integer getIdProdutoGrupo() {
		return idProdutoGrupo;
	}

	public DeterminaQuantidades getDetermQuantidade() {
		return determQuantidade;
	}

	public void setDetermQuantidade(DeterminaQuantidades determQuantidade) {
		this.determQuantidade = determQuantidade;
	}

	public String getDiariasTransiente() {
		return diariasTransiente;
	}

	public void setDiariasTransiente(String diariasTransiente) {
		this.diariasTransiente = diariasTransiente;
	}

	public String getQuantidade2Transiente() {
		return quantidade2Transiente;
	}

	public void setQuantidade2Transiente(String quantidade2Transiente) {
		this.quantidade2Transiente = quantidade2Transiente;
	}

	public String getQuantidadeTransiente() {
		return quantidadeTransiente;
	}

	public void setQuantidadeTransiente(String quantidadeTransiente) {
		this.quantidadeTransiente = quantidadeTransiente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTidProdutoGrupo() {
		return tidProdutoGrupo;
	}

	public void setTidProdutoGrupo(String tidProdutoGrupo) {
		this.tidProdutoGrupo = tidProdutoGrupo;
	}

	public Integer getIdEmpresaTransiente() {
		return idEmpresaTransiente;
	}

	public void setIdEmpresaTransiente(Integer idEmpresaTransiente) {
		this.idEmpresaTransiente = idEmpresaTransiente;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public BigDecimal getPrecoProdutoQuantidade() {
		return precoProdutoQuantidade;
	}

	public void setPrecoProdutoQuantidade(BigDecimal precoProdutoQuantidade) {
		this.precoProdutoQuantidade = precoProdutoQuantidade;
	}

	public boolean isSalvarEfechar() {
		return salvarEfechar;
	}

	public void setSalvarEfechar(boolean salvarEfechar) {
		this.salvarEfechar = salvarEfechar;
	}

	public String getBvFornecedorTransiente() {
		return bvFornecedorTransiente;
	}

	public void setBvFornecedorTransiente(String bvFornecedorTransiente) {
		this.bvFornecedorTransiente = bvFornecedorTransiente;
	}

	public String getCustoProdutoTransiente() {
		return custoProdutoTransiente;
	}

	public void setCustoProdutoTransiente(String custoProdutoTransiente) {
		this.custoProdutoTransiente = custoProdutoTransiente;
	}

	public String getBvTransiente() {
		return bvTransiente;
	}

	public void setBvTransiente(String bvTransiente) {
		this.bvTransiente = bvTransiente;
	}

	public BigDecimal getCustoProduto() {
		return custoProduto;
	}

	public void setCustoProduto(BigDecimal custoProduto) {
		this.custoProduto = custoProduto;
	}

	public Integer getIdGrupoTransiente() {
		return idGrupoTransiente;
	}

	public void setIdGrupoTransiente(Integer idGrupoTransiente) {
		this.idGrupoTransiente = idGrupoTransiente;
	}

	public Integer getIdprodutoTransiente() {
		return idprodutoTransiente;
	}

	public void setIdprodutoTransiente(Integer idprodutoTransiente) {
		this.idprodutoTransiente = idprodutoTransiente;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Produto getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}

		public boolean isBvFornecedorEmPorcentagem() {
		return bvFornecedorEmPorcentagem;
	}

	public void setBvFornecedorEmPorcentagem(boolean bvFornecedorEmPorcentagem) {
		this.bvFornecedorEmPorcentagem = bvFornecedorEmPorcentagem;
	}

	public boolean isCustoComBvFornecedor() {
		return custoComBvFornecedor;
	}

	public void setCustoComBvFornecedor(boolean custoComBvFornecedor) {
		this.custoComBvFornecedor = custoComBvFornecedor;
	}

	public BigDecimal getBvFornecedorValor() {
		return bvFornecedorValor;
	}

	public void setBvFornecedorValor(BigDecimal bvFornecedorValor) {
		this.bvFornecedorValor = bvFornecedorValor;
	}

	public BigDecimal getBvFornecedor() {
		return bvFornecedor;
	}
	public void setBvFornecedor(BigDecimal bvFornecedor) {
		this.bvFornecedor = bvFornecedor;
	}
	public void setIdProdutoGrupo(Integer idProdutoGrupo) {
		this.idProdutoGrupo = idProdutoGrupo;
	}
	
	
	public boolean isImposto() {
		return imposto;
	}


	public void setImposto(boolean imposto) {
		this.imposto = imposto;
	}


	public BigDecimal getBv() {
		return bv;
	}


	public void setBv(BigDecimal bv) {
		this.bv = bv;
	}


	public boolean isMargemPadrao() {
		return margemPadrao;
	}


	public void setMargemPadrao(boolean margemPadrao) {
		this.margemPadrao = margemPadrao;
	}


	public Calendar getAlteradoEm() {
		return alteradoEm;
	}


	public void setAlteradoEm(Calendar alteradoEm) {
		this.alteradoEm = alteradoEm;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	public double getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}


	public double getQuantidade2() {
		return quantidade2;
	}


	public void setQuantidade2(double quantidade2) {
		this.quantidade2 = quantidade2;
	}


	public double getDiarias() {
		return diarias;
	}


	public void setDiarias(double diarias) {
		this.diarias = diarias;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	/*public Producao getProducao() {
		return producao;
	}
	public void setProducao(Producao producao) {
		this.producao = producao;
	}*/
		
	public CartaContFornecedor getCartaFornecedor() {
		return cartaFornecedor;
	}

	public void setCartaFornecedor(CartaContFornecedor cartaFornecedor) {
		this.cartaFornecedor = cartaFornecedor;
	}

	/*public Producao getProducaop() {
		return producaop;
	}

	public void setProducaop(Producao producaop) {
		this.producaop = producaop;
	}*/

	public ProducaoP getProducaop() {
		return producaop;
	}

	public void setProducaop(ProducaoP producaop) {
		this.producaop = producaop;
	}

	public BigDecimal getPrecoProduto() {
		return precoProduto;
	}
	public void setPrecoProduto(BigDecimal precoProduto) {
		this.precoProduto = precoProduto;
	}
	public Grupo getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Grupo idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	
	
	
	
	
	
	/*
	 idProdutoGrupo


idGrupo

margem

precoProduto

diarias








	 */
	
	
	
	
}
