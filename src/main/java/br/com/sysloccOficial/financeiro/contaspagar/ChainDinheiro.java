package br.com.sysloccOficial.financeiro.contaspagar;

public class ChainDinheiro implements TipoPgtoForn{

	private TipoPgtoForn proximo;

	@Override
	public String tipo(String tipo) {
		if(tipo.equals("Dinheiro")){
			return tipo = "Dinheiro";
		}else{
			return proximo.tipo(tipo);
		}
	}

	@Override
	public void setProximo(TipoPgtoForn proximo) {
		this.proximo = proximo;
	}
}
