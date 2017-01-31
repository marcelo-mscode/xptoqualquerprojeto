package br.com.sysloccOficial.model.producao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
public class ProducaoPDespesas {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idProducaoPDespesas;
	
	private String servicos;
	private BigDecimal fatLocco;
	private BigDecimal fatDireto;
	private BigDecimal valor;
	private boolean imposto;
	private boolean fee;
	private boolean custoLocco;
	
	private BigDecimal valorFornecedor;
	private BigDecimal valorNF;
	private BigDecimal diferenca;
	private String difimposto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento;
	
	private String nfRecibo;
	private String dadosBancarios;
	
	
// -------------------------------------------------------------------------- //	
	
	
	
// -------------------------------------------------------------------------- //	
	@Transient private Integer idLista;
	@Transient private String dataVencimentoTransiente;
	
	
	@Transient private String fatLoccoTransiente;
	@Transient private String fatDiretoTransiente;
	@Transient private String valorTransiente;
	
	@Transient private String valorFornecedorTransiente;
	@Transient private String valorNFTransiente;
	@Transient private String diferencaTransiente;
	
// -------------------------------------------------------------------------- //	
	
	public Integer getIdProducaoPDespesas() {
		return idProducaoPDespesas;
	}
	public boolean isCustoLocco() {
		return custoLocco;
	}
	public void setCustoLocco(boolean custoLocco) {
		this.custoLocco = custoLocco;
	}
	public String getFatLoccoTransiente() {
		return fatLoccoTransiente;
	}
	public void setFatLoccoTransiente(String fatLoccoTransiente) {
		this.fatLoccoTransiente = fatLoccoTransiente;
	}
	public String getFatDiretoTransiente() {
		return fatDiretoTransiente;
	}
	public void setFatDiretoTransiente(String fatDiretoTransiente) {
		this.fatDiretoTransiente = fatDiretoTransiente;
	}
	public String getValorTransiente() {
		return valorTransiente;
	}
	public void setValorTransiente(String valorTransiente) {
		this.valorTransiente = valorTransiente;
	}
	public String getValorFornecedorTransiente() {
		return valorFornecedorTransiente;
	}
	public void setValorFornecedorTransiente(String valorFornecedorTransiente) {
		this.valorFornecedorTransiente = valorFornecedorTransiente;
	}
	public String getValorNFTransiente() {
		return valorNFTransiente;
	}
	public void setValorNFTransiente(String valorNFTransiente) {
		this.valorNFTransiente = valorNFTransiente;
	}
	public String getDiferencaTransiente() {
		return diferencaTransiente;
	}
	public void setDiferencaTransiente(String diferencaTransiente) {
		this.diferencaTransiente = diferencaTransiente;
	}
	public boolean isFee() {
		return fee;
	}
	public void setFee(boolean fee) {
		this.fee = fee;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public boolean isImposto() {
		return imposto;
	}
	public void setImposto(boolean imposto) {
		this.imposto = imposto;
	}
	public String getDataVencimentoTransiente() {
		return dataVencimentoTransiente;
	}
	public void setDataVencimentoTransiente(String dataVencimentoTransiente) {
		this.dataVencimentoTransiente = dataVencimentoTransiente;
	}
	public Integer getIdLista() {
		return idLista;
	}
	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}
	public void setIdProducaoPDespesas(Integer idProducaoPDespesas) {
		this.idProducaoPDespesas = idProducaoPDespesas;
	}
	public String getServicos() {
		return servicos;
	}
	public void setServicos(String servicos) {
		this.servicos = servicos;
	}
	
	public BigDecimal getFatLocco() {
		return fatLocco;
	}
	public void setFatLocco(BigDecimal fatLocco) {
		this.fatLocco = fatLocco;
	}
	public BigDecimal getFatDireto() {
		return fatDireto;
	}
	public void setFatDireto(BigDecimal fatDireto) {
		this.fatDireto = fatDireto;
	}
	public BigDecimal getValorFornecedor() {
		return valorFornecedor;
	}
	public void setValorFornecedor(BigDecimal valorFornecedor) {
		this.valorFornecedor = valorFornecedor;
	}
	public BigDecimal getValorNF() {
		return valorNF;
	}
	public void setValorNF(BigDecimal valorNF) {
		this.valorNF = valorNF;
	}
	public BigDecimal getDiferenca() {
		return diferenca;
	}
	public void setDiferenca(BigDecimal diferenca) {
		this.diferenca = diferenca;
	}
	
	public String getDifimposto() {
		return difimposto;
	}
	public void setDifimposto(String difimposto) {
		this.difimposto = difimposto;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getNfRecibo() {
		return nfRecibo;
	}
	public void setNfRecibo(String nfRecibo) {
		this.nfRecibo = nfRecibo;
	}
	public String getDadosBancarios() {
		return dadosBancarios;
	}
	public void setDadosBancarios(String dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}
	
	
	
	
	
	
	
	
	
}
