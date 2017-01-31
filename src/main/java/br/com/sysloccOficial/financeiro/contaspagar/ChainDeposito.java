package br.com.sysloccOficial.financeiro.contaspagar;

import java.util.List;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class ChainDeposito implements TipoPgtoForn{

	private TipoPgtoForn proximo;
	private List<ProducaoP> listaProducaoP;
	private int i;

	public ChainDeposito(List<ProducaoP> listaProducaoP, int i){
		this.listaProducaoP = listaProducaoP;
		this.i = i;
	}
	
	@Override
	public String tipo(String tipo) {
		if(tipo.equals("Depósito Bancário")){
			for (int j = 0; j < listaProducaoP.get(i).getIdEmpFornecedor().getPagamento().size(); j++) {
				tipo = listaProducaoP.get(i).getIdEmpFornecedor().getPagamento().get(j).getBanco();
	        }
			return tipo;
		}else{
			return proximo.tipo(tipo);
		}
	}

	@Override
	public void setProximo(TipoPgtoForn proximo) {
		this.proximo = proximo;
	}

	
	
	
	
	
}
