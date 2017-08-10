package br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos;

public class BancoBradesco implements SelecionaBancos{

	private SelecionaBancos proximo;
	private SelecionaBancos proximoSaida;
	private SelecionaBancos proximoTarifas;

	@Override
	public String[] tipoBancoEntrada(Integer idBanco) {
		String bancos[] =  new String[2]; 
		if(idBanco == 3){
			bancos[0] = "/bradesco/bradEntrada";
			bancos[1] = "entradasBradesco";
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
		if(idBanco == 3){
			bancos[0] = "/bradesco/bradSaida";
			bancos[1] = "saidasBradesco";
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
			bancos[0] = "/bradesco/bradTarifas";
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
