package br.com.sysloccOficial.model.producao;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Producao;
import br.com.sysloccOficial.model.ProdutoGrupo;

@Entity
public class CartaContFornecedor {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer idCartaForn;
	
	private String condicaofaturamentoDias;	
	private String contatoResponsavel;
	private String dataCabecalho;
	private String dataEntrega;
	private String dataFaturamento;	
	private String descItem;
	private String empCab3; // Tipo contratacao: locação, compra, serviço promotoria
	private String empCab5; // Nome do evento
	private String empCab7; // Local do evento
	private String empresa;
	private String fornecedorContato;
	private String fornecedorContatoEmail;
	private String fornecedorContratado;
	private String localEntregaTexto;
	private String responsavelContratacao;
	private String valorTotal;	
	private String observacoes;	
	private String atualizadoPor;	
	
	private boolean gerarCarta;
	private boolean localEntregaAgencia; // Se o local de entrega for na agencia, pegar endereço locco 
	private boolean outroFornecedor;     // Indica que foi feito uma negociacao com outro fornecedor
	private Integer idLista;

	
// ----------------------------------------------------- //
	
    @OneToOne @JoinColumn(name="produtoGrupo") private ProdutoGrupo prodGrupo;
    @OneToOne @JoinColumn(name="fornecedor") private Empresa fornecedor;
    @ManyToOne @JoinColumn(name="idProducao") private ProducaoP idproducao;
    
// ----------------------------------------------------- //

    @Transient private Integer idFornecedorTrans;
    
// ----------------------------------------------------- //

    @Temporal(TemporalType.TIMESTAMP)private Calendar atualizacao;

// ----------------------------------------------------- //
    
    
    public Integer getIdCartaForn() {
		return idCartaForn;
	}

    /*public Producao getIdproducao() {
		return idproducao;
	}

	public void setIdproducao(Producao idproducao) {
		this.idproducao = idproducao;
	}*/

	public Integer getIdFornecedorTrans() {
		return idFornecedorTrans;
	}

	public void setIdFornecedorTrans(Integer idFornecedorTrans) {
		this.idFornecedorTrans = idFornecedorTrans;
	}

	public String getAtualizadoPor() {
		return atualizadoPor;
	}



	public void setAtualizadoPor(String atualizadoPor) {
		this.atualizadoPor = atualizadoPor;
	}



	public Integer getIdLista() {
		return idLista;
	}



	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}



	public ProducaoP getIdproducao() {
		return idproducao;
	}



	public void setIdproducao(ProducaoP idproducao) {
		this.idproducao = idproducao;
	}



	public boolean isLocalEntregaAgencia() {
		return localEntregaAgencia;
	}

	public void setLocalEntregaAgencia(boolean localEntregaAgencia) {
		this.localEntregaAgencia = localEntregaAgencia;
	}

	public String getEmpCab3() {
		return empCab3;
	}

	public void setEmpCab3(String empCab3) {
		this.empCab3 = empCab3;
	}

	public boolean isOutroFornecedor() {
		return outroFornecedor;
	}

	public void setOutroFornecedor(boolean outroFornecedor) {
		this.outroFornecedor = outroFornecedor;
	}

	public ProdutoGrupo getProdGrupo() {
		return prodGrupo;
	}

	public void setProdGrupo(ProdutoGrupo prodGrupo) {
		this.prodGrupo = prodGrupo;
	}

	public Empresa getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Empresa fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public void setIdCartaForn(Integer idCartaForn) {
		this.idCartaForn = idCartaForn;
	}

	public String getCondicaofaturamentoDias() {
		return condicaofaturamentoDias;
	}

	public void setCondicaofaturamentoDias(String condicaofaturamentoDias) {
		this.condicaofaturamentoDias = condicaofaturamentoDias;
	}

	public String getContatoResponsavel() {
		return contatoResponsavel;
	}

	public void setContatoResponsavel(String contatoResponsavel) {
		this.contatoResponsavel = contatoResponsavel;
	}

	public String getDataCabecalho() {
		return dataCabecalho;
	}

	public void setDataCabecalho(String dataCabecalho) {
		this.dataCabecalho = dataCabecalho;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getDataFaturamento() {
		return dataFaturamento;
	}

	public void setDataFaturamento(String dataFaturamento) {
		this.dataFaturamento = dataFaturamento;
	}

	public String getDescItem() {
		return descItem;
	}

	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}

	public String getEmpCab5() {
		return empCab5;
	}

	public void setEmpCab5(String empCab5) {
		this.empCab5 = empCab5;
	}

	public String getEmpCab7() {
		return empCab7;
	}

	public void setEmpCab7(String empCab7) {
		this.empCab7 = empCab7;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getFornecedorContato() {
		return fornecedorContato;
	}

	public void setFornecedorContato(String fornecedorContato) {
		this.fornecedorContato = fornecedorContato;
	}

	public String getFornecedorContatoEmail() {
		return fornecedorContatoEmail;
	}

	public void setFornecedorContatoEmail(String fornecedorContatoEmail) {
		this.fornecedorContatoEmail = fornecedorContatoEmail;
	}

	public String getFornecedorContratado() {
		return fornecedorContratado;
	}

	public void setFornecedorContratado(String fornecedorContratado) {
		this.fornecedorContratado = fornecedorContratado;
	}

	public String getLocalEntregaTexto() {
		return localEntregaTexto;
	}

	public void setLocalEntregaTexto(String localEntregaTexto) {
		this.localEntregaTexto = localEntregaTexto;
	}

	public String getResponsavelContratacao() {
		return responsavelContratacao;
	}

	public void setResponsavelContratacao(String responsavelContratacao) {
		this.responsavelContratacao = responsavelContratacao;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isGerarCarta() {
		return gerarCarta;
	}

	public void setGerarCarta(boolean gerarCarta) {
		this.gerarCarta = gerarCarta;
	}

	public Calendar getAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(Calendar atualizacao) {
		this.atualizacao = atualizacao;
	} 

// ----------------------------------------------------- //
	
	
	
	
}
