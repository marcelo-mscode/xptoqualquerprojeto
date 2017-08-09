package br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos;

public class BancoCEF implements SelecionaBancos{
	
private SelecionaBancos proximo;
private SelecionaBancos proximoSaida;
private SelecionaBancos proximoTarifas;

public String[] tipoBancoEntrada(Integer idBanco){
		
		String bancos[] =  new String[2]; 
		
		if(idBanco == 2){
			bancos[0] = "/cef/cefEntrada";
			bancos[1] = "entradasCEF";
			
			return bancos;
		}else{
			return proximo.tipoBancoEntrada(idBanco);
		}
	}

	@Override
	public void setProximo(SelecionaBancos proximo) {
		this.proximo = proximo;
		
	}

	@Override
	public String[] tipoBancoSaida(Integer idBanco) {
		String bancos[] =  new String[2]; 
		if(idBanco == 2){
			bancos[0] = "/cef/cefSaida";
			bancos[1] = "saidasCEF";
			return bancos;
		}else{
			return proximoSaida.tipoBancoSaida(idBanco);
		}
	}

	@Override
	public void setProximoSaida(SelecionaBancos proximoSaida) {
		this.proximoSaida = proximoSaida;
		
	}

	@Override
	public String[] tipoBancoTarifas(Integer idBanco) {
		String bancos[] =  new String[2]; 
		if(idBanco == 2){
			bancos[0] = "/cef/cefTarifas";
			bancos[1] = "tarifasCEF";
			return bancos;
		}else{
			return proximoTarifas.tipoBancoTarifas(idBanco);
		}
	}

	@Override
	public void setProximoTarifas(SelecionaBancos proximoTarifas) {
		this.proximoTarifas = proximoTarifas;
	}
}


