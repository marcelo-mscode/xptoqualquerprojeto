package br.com.sysloccOficial.financeiro.contaspagar;

public class ChainCheque implements TipoPgtoForn{

	private TipoPgtoForn proximo;

	@Override
	public String tipo(String tipo) {
		if(tipo.equals("Retirar Cheque")){
			return tipo = "Retira Cheque";
		}else{
			return proximo.tipo(tipo);
		}
	}

	@Override
	public void setProximo(TipoPgtoForn proximo) {
		this.proximo = proximo;
	}

}
