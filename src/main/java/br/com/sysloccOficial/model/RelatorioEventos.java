package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RelatorioEventos {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idRelatorioEvento;
	private String mesEvento;
	private String anoEvento;
	
	private BigDecimal cacheEquipIn;
	private BigDecimal cacheEquipEx;
	private BigDecimal diretoria1;
	private BigDecimal diretoria2;
	
	private BigDecimal liqImpostoTotalPagto;
	private BigDecimal totalCachesIntExt;
	private BigDecimal totalCache;
	private BigDecimal diferencaCacheFuncionariosTotalPgto;

	private BigDecimal valorLoccoAgenc;
	private BigDecimal impostoSobreValorLoccoAgencia;

	//Liquido Imposto -> valor LoccoAgencia - Imposto 15.95%
	private BigDecimal liquidoImposto; 
	
	private BigDecimal servicos;
	private BigDecimal valorLiquido;

	private BigDecimal totalCachesComTelefone;
	private BigDecimal totalCachesSemTelefone;
	private BigDecimal bvs;
	
	private BigDecimal pgtoInternas;
	private BigDecimal pgtoExternas;
	

	private String nf;
	
	private BigDecimal margemContribuicao;
	private BigDecimal custoTelefone;
	private BigDecimal fee;
	private BigDecimal feeReduzido;
	private BigDecimal impostoCliente;
	private BigDecimal impostoClienteDiferenca;
	
	
	
	
	// Total relacionado a fornecedores
	private BigDecimal totalAPagarFornecedores;
	private BigDecimal totalDiferenca;
	private BigDecimal totalComTelefone;
	private BigDecimal totalSemTelefone;
	private	Integer idLista;
	private	Integer mesReferencia;
	
	private boolean recebido;

	
// ------------------------------------------------------------------------- //

	@Temporal(TemporalType.TIMESTAMP)private Calendar dataRecebido;
	@Temporal(TemporalType.TIMESTAMP)private Date dataVencimento;
	@Temporal(TemporalType.TIMESTAMP)private Calendar dataAtualizacao;
	
// ------------------------------------------------------------------------- //
    @OneToOne (mappedBy="relatorioEvento") private GiroEvento giroEvento;
    @OneToMany (mappedBy = "relatorioEvento") private List<CacheEvento> cacheEvento;
    
// --------------------------------------------------------------------- //	
	
    public Integer getIdRelatorioEvento() {
		return idRelatorioEvento;
	}
	public BigDecimal getFeeReduzido() {
		return feeReduzido;
	}
	public void setFeeReduzido(BigDecimal feeReduzido) {
		this.feeReduzido = feeReduzido;
	}
	public Integer getMesReferencia() {
		return mesReferencia;
	}
	public void setMesReferencia(Integer mesReferencia) {
		this.mesReferencia = mesReferencia;
	}
	public Calendar getDataRecebido() {
		return dataRecebido;
	}
	public void setDataRecebido(Calendar dataRecebido) {
		this.dataRecebido = dataRecebido;
	}
	public boolean isRecebido() {
		return recebido;
	}
	public void setRecebido(boolean recebido) {
		this.recebido = recebido;
	}
	public List<CacheEvento> getCacheEvento() {
		return cacheEvento;
	}
	public void setCacheEvento(List<CacheEvento> cacheEvento) {
		this.cacheEvento = cacheEvento;
	}
	public BigDecimal getDiferencaCacheFuncionariosTotalPgto() {
		return diferencaCacheFuncionariosTotalPgto;
	}
	public void setDiferencaCacheFuncionariosTotalPgto(
			BigDecimal diferencaCacheFuncionariosTotalPgto) {
		this.diferencaCacheFuncionariosTotalPgto = diferencaCacheFuncionariosTotalPgto;
	}
	public GiroEvento getGiroEvento() {
		return giroEvento;
	}
	public void setGiroEvento(GiroEvento giroEvento) {
		this.giroEvento = giroEvento;
	}
	public BigDecimal getTotalCachesSemTelefone() {
		return totalCachesSemTelefone;
	}
	public void setTotalCachesSemTelefone(BigDecimal totalCachesSemTelefone) {
		this.totalCachesSemTelefone = totalCachesSemTelefone;
	}
	public BigDecimal getTotalAPagarFornecedores() {
		return totalAPagarFornecedores;
	}
	public void setTotalAPagarFornecedores(BigDecimal totalAPagarFornecedores) {
		this.totalAPagarFornecedores = totalAPagarFornecedores;
	}
	public BigDecimal getTotalDiferenca() {
		return totalDiferenca;
	}
	public void setTotalDiferenca(BigDecimal totalDiferenca) {
		this.totalDiferenca = totalDiferenca;
	}
	public BigDecimal getTotalComTelefone() {
		return totalComTelefone;
	}
	public void setTotalComTelefone(BigDecimal totalComTelefone) {
		this.totalComTelefone = totalComTelefone;
	}
	public BigDecimal getTotalSemTelefone() {
		return totalSemTelefone;
	}
	public void setTotalSemTelefone(BigDecimal totalSemTelefone) {
		this.totalSemTelefone = totalSemTelefone;
	}
	public Integer getIdLista() {
		return idLista;
	}
	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}
	public BigDecimal getImpostoClienteDiferenca() {
		return impostoClienteDiferenca;
	}
	public void setImpostoClienteDiferenca(BigDecimal impostoClienteDiferenca) {
		this.impostoClienteDiferenca = impostoClienteDiferenca;
	}
	public void setIdRelatorioEvento(Integer idRelatorioEvento) {
		this.idRelatorioEvento = idRelatorioEvento;
	}
	public String getMesEvento() {
		return mesEvento;
	}
	public void setMesEvento(String mesEvento) {
		this.mesEvento = mesEvento;
	}
	public String getAnoEvento() {
		return anoEvento;
	}
	public void setAnoEvento(String anoEvento) {
		this.anoEvento = anoEvento;
	}
	public BigDecimal getCacheEquipIn() {
		return cacheEquipIn;
	}
	public void setCacheEquipIn(BigDecimal cacheEquipIn) {
		this.cacheEquipIn = cacheEquipIn;
	}
	public BigDecimal getCacheEquipEx() {
		return cacheEquipEx;
	}
	public void setCacheEquipEx(BigDecimal cacheEquipEx) {
		this.cacheEquipEx = cacheEquipEx;
	}
	public BigDecimal getDiretoria1() {
		return diretoria1;
	}
	public void setDiretoria1(BigDecimal diretoria1) {
		this.diretoria1 = diretoria1;
	}
	public BigDecimal getDiretoria2() {
		return diretoria2;
	}
	public void setDiretoria2(BigDecimal diretoria2) {
		this.diretoria2 = diretoria2;
	}
	public BigDecimal getLiqImpostoTotalPagto() {
		return liqImpostoTotalPagto;
	}
	public void setLiqImpostoTotalPagto(BigDecimal liqImpostoTotalPagto) {
		this.liqImpostoTotalPagto = liqImpostoTotalPagto;
	}
	public BigDecimal getTotalCachesIntExt() {
		return totalCachesIntExt;
	}
	public void setTotalCachesIntExt(BigDecimal totalCachesIntExt) {
		this.totalCachesIntExt = totalCachesIntExt;
	}
	public BigDecimal getTotalCache() {
		return totalCache;
	}
	public void setTotalCache(BigDecimal totalCache) {
		this.totalCache = totalCache;
	}
	public BigDecimal getValorLoccoAgenc() {
		return valorLoccoAgenc;
	}
	public void setValorLoccoAgenc(BigDecimal valorLoccoAgenc) {
		this.valorLoccoAgenc = valorLoccoAgenc;
	}
	public BigDecimal getImpostoSobreValorLoccoAgencia() {
		return impostoSobreValorLoccoAgencia;
	}
	public void setImpostoSobreValorLoccoAgencia(
			BigDecimal impostoSobreValorLoccoAgencia) {
		this.impostoSobreValorLoccoAgencia = impostoSobreValorLoccoAgencia;
	}
	public BigDecimal getLiquidoImposto() {
		return liquidoImposto;
	}
	public void setLiquidoImposto(BigDecimal liquidoImposto) {
		this.liquidoImposto = liquidoImposto;
	}
	public BigDecimal getServicos() {
		return servicos;
	}
	public void setServicos(BigDecimal servicos) {
		this.servicos = servicos;
	}
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	
	public BigDecimal getTotalCachesComTelefone() {
		return totalCachesComTelefone;
	}
	public void setTotalCachesComTelefone(BigDecimal totalCachesComTelefone) {
		this.totalCachesComTelefone = totalCachesComTelefone;
	}
	public BigDecimal getBvs() {
		return bvs;
	}
	public void setBvs(BigDecimal bvs) {
		this.bvs = bvs;
	}
	public BigDecimal getPgtoInternas() {
		return pgtoInternas;
	}
	public void setPgtoInternas(BigDecimal pgtoInternas) {
		this.pgtoInternas = pgtoInternas;
	}
	public BigDecimal getPgtoExternas() {
		return pgtoExternas;
	}
	public void setPgtoExternas(BigDecimal pgtoExternas) {
		this.pgtoExternas = pgtoExternas;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public String getNf() {
		return nf;
	}
	public void setNf(String nf) {
		this.nf = nf;
	}
	public BigDecimal getMargemContribuicao() {
		return margemContribuicao;
	}
	public void setMargemContribuicao(BigDecimal margemContribuicao) {
		this.margemContribuicao = margemContribuicao;
	}
	public BigDecimal getCustoTelefone() {
		return custoTelefone;
	}
	public void setCustoTelefone(BigDecimal custoTelefone) {
		this.custoTelefone = custoTelefone;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getImpostoCliente() {
		return impostoCliente;
	}
	public void setImpostoCliente(BigDecimal impostoCliente) {
		this.impostoCliente = impostoCliente;
	}
	
	// ----------------------------------------- //
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
