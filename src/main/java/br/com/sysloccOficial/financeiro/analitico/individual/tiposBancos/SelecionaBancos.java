package br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos;

public interface SelecionaBancos {
	
	public String[] tipoBanco(Integer idBanco);
	void setProximo(SelecionaBancos proximo);
}
