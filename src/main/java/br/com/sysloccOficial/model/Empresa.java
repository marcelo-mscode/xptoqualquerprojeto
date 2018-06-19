package br.com.sysloccOficial.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import br.com.sysloccOficial.model.producao.CartaContFornecedor;
import br.com.sysloccOficial.model.producao.ProducaoP;
import br.com.sysloccOficial.model.producao.ValorPagtoFornecedor;
import br.com.sysloccOficial.model.prospeccao.Prospeccao;

@Entity
public class Empresa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmpresa;
	private String empresa;
	private String cnpj;
	private String endereco;
	private String RazaoSocial;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private String telefone;
	private String telefoneDesc;
	private String email;
	private String emailDesc;
	private String origem;
	private String obs;
	private String logotipo;
	private String descricao;
	private boolean habilitado;
	private boolean cliente;
	private boolean fornecedor;
	private boolean prospect;
	
	
// -------------- Datas ----------------- //	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCadastro;
	
// ---------------- Transientes ----------------- //	

	@Transient private Integer tipoPagamento;
	@Transient private Integer idEmpresaAtualiza;
	@Transient private String banco;
	@Transient private String agencia;
	@Transient private String conta;
	
	
// ----------- Relacionamentos ---------- //		
	@OneToMany(mappedBy="idEmpresa", cascade = CascadeType.ALL)private List<EmpresaAtuacao> empresaAtuacao;
	@OneToMany(mappedBy= "empresa", cascade = CascadeType.ALL)private List<Contato> contato;
	@OneToMany(mappedBy="empresa", cascade = CascadeType.ALL)private List<Job>job;
	@OneToMany(mappedBy="idEmpresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)private List<Pagamento> pagamento;
	@OneToMany(mappedBy="idEmpresa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)private List<Pagamento> pagamentoProducao;
	@OneToMany(mappedBy="empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)private List<ProdutoGrupo> produtoGrupo;
	/*@OneToOne(mappedBy="fornecedor")private CartaContFornecedor cartaFornecedor;*/
	@OneToMany(mappedBy="idEmpresa", fetch = FetchType.LAZY) private List<ValorPagtoFornecedor> idValorPgtoFornecedor;
	@OneToMany(mappedBy="idEmpresa", fetch = FetchType.LAZY) private List<Prospeccao> idProspeccao;
	@OneToMany(mappedBy="idEmpFornecedor", fetch = FetchType.LAZY) private List<ProducaoP> idProducao;
	
// -------------------------------------- //	
	
	
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	/*public CartaContFornecedor getCartaFornecedor() {
		return cartaFornecedor;
	}
	public void setCartaFornecedor(CartaContFornecedor cartaFornecedor) {
		this.cartaFornecedor = cartaFornecedor;
	}*/
	public List<ValorPagtoFornecedor> getIdValorPgtoFornecedor() {
		return idValorPgtoFornecedor;
	}
	public void setIdValorPgtoFornecedor(
			List<ValorPagtoFornecedor> idValorPgtoFornecedor) {
		this.idValorPgtoFornecedor = idValorPgtoFornecedor;
	}
	
	public List<ProducaoP> getIdProducao() {
		return idProducao;
	}
	public void setIdProducao(List<ProducaoP> idProducao) {
		this.idProducao = idProducao;
	}
	/*public List<Producao> getIdProducao() {
		return idProducao;
	}
	public void setIdProducao(List<Producao> idProducao) {
		this.idProducao = idProducao;
	}*/
	public List<ProdutoGrupo> getProdutoGrupo() {
		return produtoGrupo;
	}
	public void setProdutoGrupo(List<ProdutoGrupo> produtoGrupo) {
		this.produtoGrupo = produtoGrupo;
	}
	public List<Pagamento> getPagamentoProducao() {
		return pagamentoProducao;
	}
	public void setPagamentoProducao(List<Pagamento> pagamentoProducao) {
		this.pagamentoProducao = pagamentoProducao;
	}
	public Integer getIdEmpresaAtualiza() {
		return idEmpresaAtualiza;
	}
	public void setIdEmpresaAtualiza(Integer idEmpresaAtualiza) {
		this.idEmpresaAtualiza = idEmpresaAtualiza;
	}
	public Integer getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public List<Contato> getContato() {
		return contato;
	}
	public List<Pagamento> getPagamento() {
		return pagamento;
	}
	public void setPagamento(List<Pagamento> pagamento) {
		this.pagamento = pagamento;
	}
	public List<EmpresaAtuacao> getEmpresaAtuacao() {
		return empresaAtuacao;
	}
	public void setEmpresaAtuacao(List<EmpresaAtuacao> empresaAtuacao) {
		this.empresaAtuacao = empresaAtuacao;
	}
	public void setContato(List<Contato> contato) {
		this.contato = contato;
	}

	
	public List<Job> getJob() {
		return job;
	}
	public void setJob(List<Job> job) {
		this.job = job;
	}
	
	
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public boolean isCliente() {
		return cliente;
	}
	public void setCliente(boolean cliente) {
		this.cliente = cliente;
	}
	public boolean isFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(boolean fornecedor) {
		this.fornecedor = fornecedor;
	}
	public boolean isProspect() {
		return prospect;
	}
	public void setProspect(boolean prospect) {
		this.prospect = prospect;
	}
	
	public String getEmailDesc() {
		return emailDesc;
	}
	public void setEmailDesc(String emailDesc) {
		this.emailDesc = emailDesc;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogotipo() {
		return logotipo;
	}
	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTelefoneDesc() {
		return telefoneDesc;
	}
	public void setTelefoneDesc(String telefoneDesc) {
		this.telefoneDesc = telefoneDesc;
	}
	public String getRazaoSocial() {
		return RazaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		RazaoSocial = razaoSocial;
	}
	
	
	
	
	
	
	
}
