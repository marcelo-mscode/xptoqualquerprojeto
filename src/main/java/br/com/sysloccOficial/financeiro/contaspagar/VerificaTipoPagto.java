package br.com.sysloccOficial.financeiro.contaspagar;

import java.util.List;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class VerificaTipoPagto {

	private List<ProducaoP> listaProducaoP;
	private int i;


	public VerificaTipoPagto(){}

	public VerificaTipoPagto(List<ProducaoP> listaProducaoP, int i){
		this.listaProducaoP = listaProducaoP;
		this.i = i;
	}
	
	public String verifica(String tipo){
		
		TipoPgtoForn T1 = new ChainDeposito(listaProducaoP, i);
		TipoPgtoForn T2 = new ChainCheque();
		TipoPgtoForn T3 = new ChainBoleto();
		TipoPgtoForn T4 = new ChainDinheiro();
		
		T1.setProximo(T2);
		T2.setProximo(T3);
		T3.setProximo(T4);
		
		return T1.tipo(tipo);
	}
}
