package br.com.sysloccOficial.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysloccOficial.model.producao.ProducaoP;

@Entity
public class CartaContratacao {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idCarta;
	
	private String dataCabecalho;
	private String empresaCabecalho;
	private String contatoResponsavel;
	private String obs;
	private String a;
	private String empresa;
	private String fornecedorContratado;
	private String fornecedorContato;
	private String fornecedorContatoEmail;

	private String empCab1;
	private String empCab2;
	private String empCab3;
	private String empCab4;
	private String empCab5;
	private String empCab6;
	private String empCab7;
	private String empCab8;
	private String empCab9;
	private String descricao;
	private String descItem;
	private String dataEntregaTexto;
	private String dataEntrega;
	private String localEntregaTexto;
	private String localEntrega;
	private String valorTotalTexto;	
	private String valorTotal;	
	private String valorTotalTexto2;	
	private String condicaofaturamento;	
	private String condicaofaturamentoDias;	
	private String dataFaturamento;	
	private String dadosEmissaoNota;
	private String dadosEmissaoNotaResponsavel;
	private String loccoRazaoSocial;
	private String loccoEndereco;
	private String loccoCnpj;
	private String informacoesImportantes;
	private String esclarecimentos;
	private String atenciosamente;
	private String responsavelContratacao;
	private String deAcordo;
	private String usuario;

	
	private boolean gerarCarta;
	
	private String dadosLocco;
	
	private String enderecoLocco;

	
// ----------------------------------------------------- //
	
    @OneToOne(mappedBy="cartaContracao")private ProducaoP producao;

// ----------------------------------------------------- //

    @Temporal(TemporalType.TIMESTAMP)private Calendar atualizacao; 

// ----------------------------------------------------- //
    
    public Integer getIdCarta() {
		return idCarta;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Calendar getAtualizacao() {
		return atualizacao;
	}
	public void setAtualizacao(Calendar atualizacao) {
		this.atualizacao = atualizacao;
	}
	public boolean isGerarCarta() {
		return gerarCarta;
	}
	public void setGerarCarta(boolean gerarCarta) {
		this.gerarCarta = gerarCarta;
	}
	public String getFornecedorContatoEmail() {
		return fornecedorContatoEmail;
	}
	public void setFornecedorContatoEmail(String fornecedorContatoEmail) {
		this.fornecedorContatoEmail = fornecedorContatoEmail;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getFornecedorContratado() {
		return fornecedorContratado;
	}
	public void setFornecedorContratado(String fornecedorContratado) {
		this.fornecedorContratado = fornecedorContratado;
	}
	public String getFornecedorContato() {
		return fornecedorContato;
	}
	public void setFornecedorContato(String fornecedorContato) {
		this.fornecedorContato = fornecedorContato;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getLocalEntregaTexto() {
		return localEntregaTexto;
	}
	public void setLocalEntregaTexto(String localEntregaTexto) {
		this.localEntregaTexto = localEntregaTexto;
	}
	public String getContatoResponsavel() {
		return contatoResponsavel;
	}
	public void setContatoResponsavel(String contatoResponsavel) {
		this.contatoResponsavel = contatoResponsavel;
	}
	public void setIdCarta(Integer idCarta) {
		this.idCarta = idCarta;
	}
	public String getDataCabecalho() {
		return dataCabecalho;
	}
	public void setDataCabecalho(String dataCabecalho) {
		this.dataCabecalho = dataCabecalho;
	}
	public String getEmpresaCabecalho() {
		return empresaCabecalho;
	}
	public void setEmpresaCabecalho(String empresaCabecalho) {
		this.empresaCabecalho = empresaCabecalho;
	}
	public String getDadosEmissaoNota() {
		return dadosEmissaoNota;
	}
	public void setDadosEmissaoNota(String dadosEmissaoNota) {
		this.dadosEmissaoNota = dadosEmissaoNota;
	}
	public String getDadosLocco() {
		return dadosLocco;
	}
	public void setDadosLocco(String dadosLocco) {
		this.dadosLocco = dadosLocco;
	}
	public String getInformacoesImportantes() {
		return informacoesImportantes;
	}
	public void setInformacoesImportantes(String informacoesImportantes) {
		this.informacoesImportantes = informacoesImportantes;
	}
	public String getEsclarecimentos() {
		return esclarecimentos;
	}
	public void setEsclarecimentos(String esclarecimentos) {
		this.esclarecimentos = esclarecimentos;
	}
	public String getAtenciosamente() {
		return atenciosamente;
	}
	public void setAtenciosamente(String atenciosamente) {
		this.atenciosamente = atenciosamente;
	}
	public String getResponsavelContratacao() {
		return responsavelContratacao;
	}
	public void setResponsavelContratacao(String responsavelContratacao) {
		this.responsavelContratacao = responsavelContratacao;
	}
	public String getDeAcordo() {
		return deAcordo;
	}
	public void setDeAcordo(String deAcordo) {
		this.deAcordo = deAcordo;
	}
	public String getEnderecoLocco() {
		return enderecoLocco;
	}
	public void setEnderecoLocco(String enderecoLocco) {
		this.enderecoLocco = enderecoLocco;
	}
	public String getEmpCab1() {
		return empCab1;
	}
	public void setEmpCab1(String empCab1) {
		this.empCab1 = empCab1;
	}
	public String getEmpCab2() {
		return empCab2;
	}
	public void setEmpCab2(String empCab2) {
		this.empCab2 = empCab2;
	}
	public String getEmpCab3() {
		return empCab3;
	}
	public void setEmpCab3(String empCab3) {
		this.empCab3 = empCab3;
	}
	public String getEmpCab4() {
		return empCab4;
	}
	public void setEmpCab4(String empCab4) {
		this.empCab4 = empCab4;
	}
	public String getEmpCab5() {
		return empCab5;
	}
	public void setEmpCab5(String empCab5) {
		this.empCab5 = empCab5;
	}
	public String getEmpCab6() {
		return empCab6;
	}
	public void setEmpCab6(String empCab6) {
		this.empCab6 = empCab6;
	}
	public String getEmpCab7() {
		return empCab7;
	}
	public void setEmpCab7(String empCab7) {
		this.empCab7 = empCab7;
	}
	public String getEmpCab8() {
		return empCab8;
	}
	public void setEmpCab8(String empCab8) {
		this.empCab8 = empCab8;
	}
	public String getEmpCab9() {
		return empCab9;
	}
	public void setEmpCab9(String empCab9) {
		this.empCab9 = empCab9;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescItem() {
		return descItem;
	}
	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}
	public String getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getLocalEntrega() {
		return localEntrega;
	}
	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}
	public String getDataEntregaTexto() {
		return dataEntregaTexto;
	}
	public void setDataEntregaTexto(String dataEntregaTexto) {
		this.dataEntregaTexto = dataEntregaTexto;
	}
	public String getValorTotalTexto() {
		return valorTotalTexto;
	}
	public void setValorTotalTexto(String valorTotalTexto) {
		this.valorTotalTexto = valorTotalTexto;
	}
	public String getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getValorTotalTexto2() {
		return valorTotalTexto2;
	}
	public void setValorTotalTexto2(String valorTotalTexto2) {
		this.valorTotalTexto2 = valorTotalTexto2;
	}
	public String getCondicaofaturamento() {
		return condicaofaturamento;
	}
	public void setCondicaofaturamento(String condicaofaturamento) {
		this.condicaofaturamento = condicaofaturamento;
	}
	public String getCondicaofaturamentoDias() {
		return condicaofaturamentoDias;
	}
	public void setCondicaofaturamentoDias(String condicaofaturamentoDias) {
		this.condicaofaturamentoDias = condicaofaturamentoDias;
	}
	public String getDataFaturamento() {
		return dataFaturamento;
	}
	public void setDataFaturamento(String dataFaturamento) {
		this.dataFaturamento = dataFaturamento;
	}
	public String getDadosEmissaoNotaResponsavel() {
		return dadosEmissaoNotaResponsavel;
	}
	public void setDadosEmissaoNotaResponsavel(String dadosEmissaoNotaResponsavel) {
		this.dadosEmissaoNotaResponsavel = dadosEmissaoNotaResponsavel;
	}
	public String getLoccoRazaoSocial() {
		return loccoRazaoSocial;
	}
	public void setLoccoRazaoSocial(String loccoRazaoSocial) {
		this.loccoRazaoSocial = loccoRazaoSocial;
	}
	public String getLoccoEndereco() {
		return loccoEndereco;
	}
	public void setLoccoEndereco(String loccoEndereco) {
		this.loccoEndereco = loccoEndereco;
	}
	public String getLoccoCnpj() {
		return loccoCnpj;
	}
	public void setLoccoCnpj(String loccoCnpj) {
		this.loccoCnpj = loccoCnpj;
	}
	/*public Producao getProducao() {
		return producao;
	}
	public void setProducao(Producao producao) {
		this.producao = producao;
	}
	
	*/

	public ProducaoP getProducao() {
		return producao;
	}

	public void setProducao(ProducaoP producao) {
		this.producao = producao;
	}
	
	
	
	
	
	
}
