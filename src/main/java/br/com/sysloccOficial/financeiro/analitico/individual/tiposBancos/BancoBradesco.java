package br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos;

public class BancoBradesco implements SelecionaBancos{

	private SelecionaBancos proximo;

	@Override
	public String[] tipoBancoEntrada(Integer idBanco) {
		String bancos[] =  new String[2]; 
		if(idBanco == 3){
			bancos[0] = "/bradesco/bradEntrada";
			bancos[1] = "entradasBradesc";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProximoSaida(SelecionaBancos proximoSaida) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] tipoBancoTarifas(Integer idBanco) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProximoTarifas(SelecionaBancos proximoTarifas) {
		// TODO Auto-generated method stub
		
	}

}
