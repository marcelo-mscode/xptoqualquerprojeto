package br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos;

public class BancoItau implements SelecionaBancos{
	
	private SelecionaBancos proximo;
	private SelecionaBancos proximoSaida;

	public String[] tipoBancoEntrada(Integer idBanco){
		String bancos[] =  new String[2]; 
		if(idBanco == 1){
			bancos[0] = "/itau/itauEntrada";
			bancos[1] = "entradasItau";
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
		if(idBanco == 1){
			bancos[0] = "/itau/itauSaida";
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
		if(idBanco == 1){
			bancos[0] = "/itau/itauTarifas";
			bancos[1] = "tarifasItau";
			return bancos;
		}else{
			return proximoSaida.tipoBancoTarifas(idBanco);
		}
	}

	@Override
	public void setProximoTarifas(SelecionaBancos proximoTarifas) {
		// TODO Auto-generated method stub
		
	}
}
