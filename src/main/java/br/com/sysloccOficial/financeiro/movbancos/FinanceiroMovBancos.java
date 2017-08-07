package br.com.sysloccOficial.financeiro.movbancos;

import java.text.ParseException;

public interface FinanceiroMovBancos {
	
	public void novaEntrada(Integer idAnalitico,String DataPgto,String valor,String descricao,String ndnf,Integer idBanco) throws ParseException;
	public Integer editaNovaEntrada(Integer idTabela,String valor, String tipoCampo) throws ParseException;
	public void novaSaida(Integer idAnalitico, String dataPgto,String valor, String descricao, Integer idBanco) throws ParseException;
	public Integer editaNovaSaida(Integer idTabela,String valor, String tipoCampo) throws ParseException;
	/*public void novaTarifa(Integer idAnalitico, String dataPgto,String valor, String descricao, Integer idBanco) throws ParseException;
	public Integer editaNovaTarifa(Integer idTabela,String valor, String tipoCampo) throws ParseException;*/
}
