package br.com.sysloccOficial.financeiro.contaspagar;

import java.util.List;

import br.com.sysloccOficial.model.producao.ProducaoP;

public interface Interna {
	
	public List<ProducaoP> carregaListasFornecedores(/*List<Integer> idsListas*/); 
	public List<ContasPagar> preencheContasAPagar(List<ProducaoP> listaProducaoP,List<Integer> idsListas);
	public List<Object[]> carregaIdsFornecedoresDistintos(List<Integer> idsListas);
	
	
}
