package br.com.sysloccOficial.financeiro.contaspagar;

public interface TipoPgtoForn {
	public String tipo(String tipo);
	void setProximo(TipoPgtoForn proximo);
}
