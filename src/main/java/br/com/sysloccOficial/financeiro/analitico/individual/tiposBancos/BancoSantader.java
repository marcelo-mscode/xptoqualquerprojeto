package br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos;

public class BancoSantader implements SelecionaBancos{

	private SelecionaBancos proximo;
	private SelecionaBancos proximoSaida;
	private SelecionaBancos proximoTarifas;
	
	
	@Override
	public String[] tipoBancoEntrada(Integer idBanco) {
		String bancos[] =  new String[2]; 
		if(idBanco == 4){
			bancos[0] = "/santander/santEntrada";
			bancos[1] = "entradasSantader";
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
		if(idBanco == 4){
			bancos[0] = "/santander/itauSaida";
			bancos[1] = "saidasItau";
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
		if(idBanco == 4){
			bancos[0] = "/itau/itauTarifas";
			bancos[1] = "tarifasItau";
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
