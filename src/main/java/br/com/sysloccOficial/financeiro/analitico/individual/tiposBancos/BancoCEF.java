package br.com.sysloccOficial.financeiro.analitico.individual.tiposBancos;

public class BancoCEF implements SelecionaBancos{
	
private SelecionaBancos proximo;

public String[] tipoBanco(Integer idBanco){
		
		String bancos[] =  new String[2]; 
		
		if(idBanco == 2){
			bancos[0] = "/cef/cefEntrada";
			bancos[1] = "entradasCEF";
			
			return bancos;
		}
		
		return null;
	}

	@Override
	public void setProximo(SelecionaBancos proximo) {
		this.proximo = proximo;
		
	}
	
	

}


