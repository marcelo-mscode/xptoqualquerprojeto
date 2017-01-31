package br.com.sysloccOficial.financeiro.contaspagar;

import java.math.BigDecimal;
import java.util.Calendar;

public class ContasPagar {
	
	public int idLista;
	public String nomeLista;
	public String tipoPagto;
	public String nomeFornecedor;
	
	public Calendar dataPagto; 
	public BigDecimal valorPagamento;
	
	
// ---------------------------------------------------- //
	
	public int getIdLista() {
		return idLista;
	}
	public String getNomeLista() {
		return nomeLista;
	}
	public void setNomeLista(String nomeLista) {
		this.nomeLista = nomeLista;
	}
	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}
	public String getTipoPagto() {
		return tipoPagto;
	}
	public void setTipoPagto(String tipoPagto) {
		this.tipoPagto = tipoPagto;
	}
	public Calendar getDataPagto() {
		return dataPagto;
	}
	public void setDataPagto(Calendar dataPagto) {
		this.dataPagto = dataPagto;
	}
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	
	
	
	
}
