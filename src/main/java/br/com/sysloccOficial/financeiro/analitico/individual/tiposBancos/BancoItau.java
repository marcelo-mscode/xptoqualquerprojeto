package br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos;

public class BancoItau implements SelecionaBancos{
	
	private SelecionaBancos proximo;

	public String[] tipoBanco(Integer idBanco){
		String bancos[] =  new String[2]; 
		if(idBanco == 1){
			bancos[0] = "/itau/itauEntrada";
			bancos[1] = "entradasItau";
			return bancos;
		}else{
			return proximo.tipoBanco(idBanco);
		}
	}

	@Override
	public void setProximo(SelecionaBancos proximo) {
		this.proximo = proximo;
	}
}
