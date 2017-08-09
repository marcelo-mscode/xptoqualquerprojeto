package br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos;

public class MontaTiposbancos {
	
	
	public String[] montaTipoBancos(Integer idBanco){
		
		SelecionaBancos b1 = new BancoItau();
		SelecionaBancos b2 = new BancoCEF();
		SelecionaBancos b3 = new UltimoTipoBanco();
		
		b1.setProximo(b2);
		b2.setProximo(b3);
		
		return b1.tipoBancoEntrada(idBanco);
	}

	public String[] montaTipoBancosSaidas(Integer idBanco){
		
		SelecionaBancos b1 = new BancoItau();
		SelecionaBancos b2 = new BancoCEF();
		SelecionaBancos b3 = new UltimoTipoBanco();
		
		b1.setProximoSaida(b2);
		b2.setProximoSaida(b3);
		
		return b1.tipoBancoSaida(idBanco);
	}

	public String[] montaTipoBancosTarifas(Integer idBanco){
		
		SelecionaBancos b1 = new BancoItau();
		SelecionaBancos b2 = new BancoCEF();
		SelecionaBancos b3 = new UltimoTipoBanco();
		
		b1.setProximoSaida(b2);
		b2.setProximoSaida(b3);
		
		return b1.tipoBancoTarifas(idBanco);
	}
	
	
}
