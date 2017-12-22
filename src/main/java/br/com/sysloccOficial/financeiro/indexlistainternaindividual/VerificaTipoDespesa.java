package br.com.sysloccOficial.financeiro.indexlistainternaindividual;
import br.com.sysloccOficial.model.producao.ProducaoPDespesas;


public class VerificaTipoDespesa {

	private ProducaoPDespesas despesa;

	public VerificaTipoDespesa(){}
	public VerificaTipoDespesa(ProducaoPDespesas despesa){
		this.despesa = despesa;
	}
	
	
	
    public boolean verifica(boolean tipo){
		
	ChainDespesaInterface T1 = new ChainComImposto(despesa);
	ChainDespesaInterface T2 = new ChainSemImposto(despesa);
	ChainDespesaInterface T3 = new ChainCustoParaLocco(despesa);
		
		T1.setProximo(T2);
		T2.setProximo(T3);
		
		return T1.tipo(tipo);
	}
	
	
	
	
	
	
	
}
