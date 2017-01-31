package br.com.sysloccOficial.model;

import java.math.BigDecimal;

public class DetalhesItemProducao {
	
	private String custo;
	private String custoTotalQuantidadePordiaria;
	private String custoToTalNSF;
	
	
	//Usados para calcular o BV dos itens
	private String custoItemunitario;
	private String bvFornecedorValorEdita;
	private String custoTotalSFNCalculado;	
	private String custouniTiraBv;
	private String CustounitarioTotalconvertido;
	private String resultadoFinalComImpostoCalculado;	
	private String resultadoFinalSNF;
	private String bvValor;	
	private String unidade;	
	private String qtdxqtd;
	
	private String administracaoValor;
	private String impostoValor;
	private String valorTotal;
	
	private String determinaQuant;
	private String precoTotal;
	private String valorUnit;

	private String x;
	private String y;
	private String z;
	

	
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public String getDeterminaQuant() {
		return determinaQuant;
	}

	public void setDeterminaQuant(String determinaQuant) {
		this.determinaQuant = determinaQuant;
	}

	public String getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}

	public String getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(String valorUnit) {
		this.valorUnit = valorUnit;
	}

	public String getAdministracaoValor() {
		return administracaoValor;
	}

	public void setAdministracaoValor(String administracaoValor) {
		this.administracaoValor = administracaoValor;
	}

	public String getImpostoValor() {
		return impostoValor;
	}

	public void setImpostoValor(String impostoValor) {
		this.impostoValor = impostoValor;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getQtdxqtd() {
		return qtdxqtd;
	}

	public void setQtdxqtd(String qtdxqtd) {
		this.qtdxqtd = qtdxqtd;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	private BigDecimal valorTotalImpostoLista;
	private BigDecimal valorTotalLista;
	
	public BigDecimal getValorTotalImpostoLista() {
		return valorTotalImpostoLista;
	}

	public void setValorTotalImpostoLista(BigDecimal valorTotalImpostoLista) {
		this.valorTotalImpostoLista = valorTotalImpostoLista;
	}

	public BigDecimal getValorTotalLista() {
		return valorTotalLista;
	}

	public void setValorTotalLista(BigDecimal valorTotalLista) {
		this.valorTotalLista = valorTotalLista;
	}

	public String getBvValor() {
		return bvValor;
	}

	public void setBvValor(String bvValor) {
		this.bvValor = bvValor;
	}

	public String getResultadoFinalSNF() {
		return resultadoFinalSNF;
	}

	public void setResultadoFinalSNF(String resultadoFinalSNF) {
		this.resultadoFinalSNF = resultadoFinalSNF;
	}

	public String getCustounitarioTotalconvertido() {
		return CustounitarioTotalconvertido;
	}

	public void setCustounitarioTotalconvertido(String custounitarioTotalconvertido) {
		CustounitarioTotalconvertido = custounitarioTotalconvertido;
	}

	public String getCustoToTalNSF() {
		return custoToTalNSF;
	}

	public void setCustoToTalNSF(String custoToTalNSF) {
		this.custoToTalNSF = custoToTalNSF;
	}

	public String getResultadoFinalComImpostoCalculado() {
		return resultadoFinalComImpostoCalculado;
	}

	public void setResultadoFinalComImpostoCalculado(
			String resultadoFinalComImpostoCalculado) {
		this.resultadoFinalComImpostoCalculado = resultadoFinalComImpostoCalculado;
	}

	public String getCustouniTiraBv() {
		return custouniTiraBv;
	}

	public void setCustouniTiraBv(String custouniTiraBv) {
		this.custouniTiraBv = custouniTiraBv;
	}

	public String getCustoTotalSFNCalculado() {
		return custoTotalSFNCalculado;
	}

	public void setCustoTotalSFNCalculado(String custoTotalSFNCalculado) {
		this.custoTotalSFNCalculado = custoTotalSFNCalculado;
	}

	public String getCustoItemunitario() {
		return custoItemunitario;
	}

	public void setCustoItemunitario(String custoItemunitario) {
		this.custoItemunitario = custoItemunitario;
	}

	public String getBvFornecedorValorEdita() {
		return bvFornecedorValorEdita;
	}

	public void setBvFornecedorValorEdita(String bvFornecedorValorEdita) {
		this.bvFornecedorValorEdita = bvFornecedorValorEdita;
	}

	public String getCustoTotalQuantidadePordiaria() {
		return custoTotalQuantidadePordiaria;
	}

	public void setCustoTotalQuantidadePordiaria(
			String custoTotalQuantidadePordiaria) {
		this.custoTotalQuantidadePordiaria = custoTotalQuantidadePordiaria;
	}

	public String getCusto() {
		return custo;
	}

	public void setCusto(String custo) {
		this.custo = custo;
	}
	
	
	
	
	
	
	

}
