package br.com.sysloccOficial.financeiro.contaspagar;

public class ChainBoleto implements TipoPgtoForn{

	private TipoPgtoForn proximo;

	@Override
	public String tipo(String tipo) {
		if(tipo.equals("Boleto Bancário")){
			return tipo = "Bol";
		}else{
			return proximo.tipo(tipo);
		}
	}

	@Override
	public void setProximo(TipoPgtoForn proximo) {
		this.proximo = proximo;
	}
}
