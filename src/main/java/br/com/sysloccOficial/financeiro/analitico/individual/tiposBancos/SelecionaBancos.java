package br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos;

public interface SelecionaBancos {
	
	public String[] tipoBancoEntrada(Integer idBanco);
	void setProximo(SelecionaBancos proximo);
	public String[] tipoBancoSaida(Integer idBanco);
	void setProximoSaida(SelecionaBancos proximoSaida);
}
