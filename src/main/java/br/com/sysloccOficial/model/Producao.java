package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sysloccOficial.model.producao.CartaContFornecedor;
import br.com.sysloccOficial.model.producao.FornecedorFinanceiro;
import br.com.sysloccOficial.model.producao.StatusProducao;

@Entity
public class Producao {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idProducao;
	
	private Integer diasPrazo; //Prazo (em dias) para pagamento, será calculado a data para o pagamento
	
	private Integer contatoFornecedor;
	private boolean status;
	private String tipoPagamento;
	private String obs;
	private String localEntrega;
	private String referenciaEntrega;
    private BigDecimal valorDePagamentoContratacao;
    private BigDecimal valorDePagamentoContratacaoOutroFornecedor;
	private double imposto;
	private double impostoOutroForn;
	
	@Enumerated(EnumType.STRING)
	private StatusProducao statusProducao;
	

	private BigDecimal valorItem;
	private BigDecimal valorContratacao;
	private BigDecimal diferenca;
	private Integer numParcelas;
	private String responsavelContratacao;
	private boolean temContratacao;
	private boolean temOutroFornecedor;
	private boolean temMesmoFornecedor;

	
	
// ------------------- Relacionamentos  ---------------------//	

	@OneToOne @JoinColumn(name="idProdutoGrupo") private ProdutoGrupo produtoGrupo;
	@OneToOne @JoinColumn(name="responsavel") private Usuario usuario; // Responsável Contratação
	@OneToOne @JoinColumn(name="idLista") private Lista lista;
	@OneToOne @JoinColumn(name="idCartaContracao") private CartaContratacao cartaContracao;
	@OneToMany (mappedBy="idproducao") private List<CartaContFornecedor> idCartaContracaoForn;
	
	@OneToOne(mappedBy="idProducao")private FornecedorFinanceiro fornecedorFinanceiro;
	
	@OneToOne @JoinColumn(name="idEmpFornecedor") private Empresa idEmpFornecedor;
	
	
	
// -------------- Transiente ---------------------- //	
	@Transient private String valorItemTrans;
	@Transient private String valorDePagamentoContratacaoTrans;
	@Transient private String diferencaTrans;
	@Transient private String impostoTrans;
	@Transient private String valorContratacaoTrans;

	@Transient private Integer idFornecedorOutroTrans;
	@Transient private String impostoOutroTrans;
	@Transient private String valorDePagamentoContratacaoOutroFornecedorTrans;
	@Transient private String dataPgtoOutroFornecedorTrans;
	
	@Transient private String pPagamento;
	@Transient private String pPrazo;
	@Transient private String pEntrega;
	@Transient private Integer idLista;
	@Transient private Integer idProdutoGrupo;
	@Transient private Integer idUsuario;

	@Transient private String itemValor1;
	@Transient private String contratacaoValor1;
	@Transient private String diferencaValor1;
	@Transient private String valorDePagamentoContratacao1;
	
	@Transient private Integer idCarta;
	@Transient private Integer idEmpresaTransiente;
	
	@Transient private String infoPag;
	@Transient private String infoForn;

	@Transient private List<Integer> parcela;
	@Transient private List<Integer> prazo;
	@Transient private List<String> data;
	@Transient private List<String> valor;
	
// ------------------- Datas  ---------------------//	

    @Temporal(TemporalType.TIMESTAMP)  private Date prazoEntrega;
	@Temporal(TemporalType.DATE)  private Date prazoPagamento;
	@Temporal(TemporalType.TIMESTAMP)  private Calendar dataConsolidado;
	
	
// -----------------------------------------------//	
	
	public Integer getDiasPrazo() {
		return diasPrazo;
	}
	
	public boolean isTemMesmoFornecedor() {
		return temMesmoFornecedor;
	}

	public void setTemMesmoFornecedor(boolean temMesmoFornecedor) {
		this.temMesmoFornecedor = temMesmoFornecedor;
	}

	public boolean isTemContratacao() {
		return temContratacao;
	}

	public void setTemContratacao(boolean temContratacao) {
		this.temContratacao = temContratacao;
	}

	public boolean isTemOutroFornecedor() {
		return temOutroFornecedor;
	}

	public void setTemOutroFornecedor(boolean temOutroFornecedor) {
		this.temOutroFornecedor = temOutroFornecedor;
	}

	public Integer getIdFornecedorOutroTrans() {
		return idFornecedorOutroTrans;
	}

	public void setIdFornecedorOutroTrans(Integer idFornecedorOutroTrans) {
		this.idFornecedorOutroTrans = idFornecedorOutroTrans;
	}

	public String getInfoForn() {
		return infoForn;
	}

	public void setInfoForn(String infoForn) {
		this.infoForn = infoForn;
	}

	public String getDataPgtoOutroFornecedorTrans() {
		return dataPgtoOutroFornecedorTrans;
	}

	public void setDataPgtoOutroFornecedorTrans(String dataPgtoOutroFornecedorTrans) {
		this.dataPgtoOutroFornecedorTrans = dataPgtoOutroFornecedorTrans;
	}

	public String getImpostoTrans() {
		return impostoTrans;
	}

	public void setImpostoTrans(String impostoTrans) {
		this.impostoTrans = impostoTrans;
	}

	public String getImpostoOutroTrans() {
		return impostoOutroTrans;
	}

	public void setImpostoOutroTrans(String impostoOutroTrans) {
		this.impostoOutroTrans = impostoOutroTrans;
	}

	public String getValorDePagamentoContratacaoOutroFornecedorTrans() {
		return valorDePagamentoContratacaoOutroFornecedorTrans;
	}

	public void setValorDePagamentoContratacaoOutroFornecedorTrans(
			String valorDePagamentoContratacaoOutroFornecedorTrans) {
		this.valorDePagamentoContratacaoOutroFornecedorTrans = valorDePagamentoContratacaoOutroFornecedorTrans;
	}

	public String getValorDePagamentoContratacaoTrans() {
		return valorDePagamentoContratacaoTrans;
	}

	public void setValorDePagamentoContratacaoTrans(
			String valorDePagamentoContratacaoTrans) {
		this.valorDePagamentoContratacaoTrans = valorDePagamentoContratacaoTrans;
	}

	public String getDiferencaTrans() {
		return diferencaTrans;
	}

	public void setDiferencaTrans(String diferencaTrans) {
		this.diferencaTrans = diferencaTrans;
	}

	public String getValorContratacaoTrans() {
		return valorContratacaoTrans;
	}

	public void setValorContratacaoTrans(String valorContratacaoTrans) {
		this.valorContratacaoTrans = valorContratacaoTrans;
	}

	public String getValorItemTrans() {
		return valorItemTrans;
	}

	public void setValorItemTrans(String valorItemTrans) {
		this.valorItemTrans = valorItemTrans;
	}

	public BigDecimal getValorDePagamentoContratacaoOutroFornecedor() {
		return valorDePagamentoContratacaoOutroFornecedor;
	}

	public void setValorDePagamentoContratacaoOutroFornecedor(
			BigDecimal valorDePagamentoContratacaoOutroFornecedor) {
		this.valorDePagamentoContratacaoOutroFornecedor = valorDePagamentoContratacaoOutroFornecedor;
	}

	public double getImpostoOutroForn() {
		return impostoOutroForn;
	}

	public void setImpostoOutroForn(double impostoOutroForn) {
		this.impostoOutroForn = impostoOutroForn;
	}

	public double getImposto() {
		return imposto;
	}

	public void setImposto(double imposto) {
		this.imposto = imposto;
	}

	public BigDecimal getValorDePagamentoContratacao() {
		return valorDePagamentoContratacao;
	}

	public void setValorDePagamentoContratacao(
			BigDecimal valorDePagamentoContratacao) {
		this.valorDePagamentoContratacao = valorDePagamentoContratacao;
	}

	public String getValorDePagamentoContratacao1() {
		return valorDePagamentoContratacao1;
	}

	public void setValorDePagamentoContratacao1(String valorDePagamentoContratacao1) {
		this.valorDePagamentoContratacao1 = valorDePagamentoContratacao1;
	}

	public List<Integer> getParcela() {
		return parcela;
	}

	public void setParcela(List<Integer> parcela) {
		this.parcela = parcela;
	}

	public List<Integer> getPrazo() {
		return prazo;
	}

	public void setPrazo(List<Integer> prazo) {
		this.prazo = prazo;
	}

	public List<String> getValor() {
		return valor;
	}

	public void setValor(List<String> valor) {
		this.valor = valor;
	}

	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	
	public String getInfoPag() {
		return infoPag;
	}
	public void setInfoPag(String infoPag) {
		this.infoPag = infoPag;
	}
	public Integer getIdEmpresaTransiente() {
		return idEmpresaTransiente;
	}
	public void setIdEmpresaTransiente(Integer idEmpresaTransiente) {
		this.idEmpresaTransiente = idEmpresaTransiente;
	}
	public StatusProducao getStatusProducao() {
		return statusProducao;
	}
	public void setStatusProducao(StatusProducao statusProducao) {
		this.statusProducao = statusProducao;
	}
	public Integer getNumParcelas() {
		return numParcelas;
	}
	public void setNumParcelas(Integer numParcelas) {
		this.numParcelas = numParcelas;
	}
	public String getResponsavelContratacao() {
		return responsavelContratacao;
	}
	public void setResponsavelContratacao(String responsavelContratacao) {
		this.responsavelContratacao = responsavelContratacao;
	}
	public List<CartaContFornecedor> getIdCartaContracaoForn() {
		return idCartaContracaoForn;
	}
	public void setIdCartaContracaoForn(
			List<CartaContFornecedor> idCartaContracaoForn) {
		this.idCartaContracaoForn = idCartaContracaoForn;
	}
	
	public Empresa getIdEmpFornecedor() {
		return idEmpFornecedor;
	}
	public void setIdEmpFornecedor(Empresa idEmpFornecedor) {
		this.idEmpFornecedor = idEmpFornecedor;
	}
	public Calendar getDataConsolidado() {
		return dataConsolidado;
	}
	public void setDataConsolidado(Calendar dataConsolidado) {
		this.dataConsolidado = dataConsolidado;
	}
	public FornecedorFinanceiro getFornecedorFinanceiro() {
		return fornecedorFinanceiro;
	}
	public void setFornecedorFinanceiro(FornecedorFinanceiro fornecedorFinanceiro) {
		this.fornecedorFinanceiro = fornecedorFinanceiro;
	}
	/*public List<FornecedorFinanceiro> getFornecedorFinanceiro() {
		return fornecedorFinanceiro;
	}
	public void setFornecedorFinanceiro(
			List<FornecedorFinanceiro> fornecedorFinanceiro) {
		this.fornecedorFinanceiro = fornecedorFinanceiro;
	}*/
	public Integer getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(Integer idCarta) {
		this.idCarta = idCarta;
	}
	public Integer getContatoFornecedor() {
		return contatoFornecedor;
	}
	public void setContatoFornecedor(Integer contatoFornecedor) {
		this.contatoFornecedor = contatoFornecedor;
	}
	public String getpPrazo() {
		return pPrazo;
	}
	public void setpPrazo(String pPrazo) {
		this.pPrazo = pPrazo;
	}
	public String getReferenciaEntrega() {
		return referenciaEntrega;
	}
	public void setReferenciaEntrega(String referenciaEntrega) {
		this.referenciaEntrega = referenciaEntrega;
	}
	public String getItemValor1() {
		return itemValor1;
	}
	public void setItemValor1(String itemValor1) {
		this.itemValor1 = itemValor1;
	}
	public String getContratacaoValor1() {
		return contratacaoValor1;
	}
	public void setContratacaoValor1(String contratacaoValor1) {
		this.contratacaoValor1 = contratacaoValor1;
	}
	public String getDiferencaValor1() {
		return diferencaValor1;
	}
	public void setDiferencaValor1(String diferencaValor1) {
		this.diferencaValor1 = diferencaValor1;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setDiasPrazo(Integer diasPrazo) {
		this.diasPrazo = diasPrazo;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getLocalEntrega() {
		return localEntrega;
	}
	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}
	public BigDecimal getValorItem() {
		return valorItem;
	}
	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}
	public BigDecimal getValorContratacao() {
		return valorContratacao;
	}
	public void setValorContratacao(BigDecimal valorContratacao) {
		this.valorContratacao = valorContratacao;
	}
	public BigDecimal getDiferenca() {
		return diferenca;
	}
	public void setDiferenca(BigDecimal diferenca) {
		this.diferenca = diferenca;
	}
	public Lista getLista() {
		return lista;
	}
	public void setLista(Lista lista) {
		this.lista = lista;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Integer getIdProdutoGrupo() {
		return idProdutoGrupo;
	}
	public void setIdProdutoGrupo(Integer idProdutoGrupo) {
		this.idProdutoGrupo = idProdutoGrupo;
	}
	public ProdutoGrupo getProdutoGrupo() {
		return produtoGrupo;
	}
	public void setProdutoGrupo(ProdutoGrupo produtoGrupo) {
		this.produtoGrupo = produtoGrupo;
	}
	public Integer getIdProducao() {
		return idProducao;
	}
	public String getpEntrega() {
		return pEntrega;
	}
	public void setpEntrega(String pEntrega) {
		this.pEntrega = pEntrega;
	}
	public Integer getIdLista() {
		return idLista;
	}
	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}
	public String getpPagamento() {
		return pPagamento;
	}
	public void setpPagamento(String pPagamento) {
		this.pPagamento = pPagamento;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public void setIdProducao(Integer idProducao) {
		this.idProducao = idProducao;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	/*public Date getPrazoEntrega() {
		return prazoEntrega;
	}
	public void setPrazoEntrega(Date prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}*/
	public Date getPrazoPagamento() {
		return prazoPagamento;
	}
	public void setPrazoPagamento(Date prazoPagamento) {
		this.prazoPagamento = prazoPagamento;
	}
	public Date getPrazoEntrega() {
		return prazoEntrega;
	}
	public void setPrazoEntrega(Date prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}
	public CartaContratacao getCartaContracao() {
		return cartaContracao;
	}
	public void setCartaContracao(CartaContratacao cartaContracao) {
		this.cartaContracao = cartaContracao;
	}
	
	
	
	
	
	

}
