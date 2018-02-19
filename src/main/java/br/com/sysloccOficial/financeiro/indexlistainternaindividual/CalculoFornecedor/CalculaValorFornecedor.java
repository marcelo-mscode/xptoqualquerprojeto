package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class CalculaValorFornecedor {
	
	
	
	public CalculaValorFornecedor(List<ProducaoP> internIndividual){
		
	}

	
	public static BigDecimal calculaValores(ProducaoP producaoP){
		
		CalculoFornecedorInterna c1 = new FornecedorTemNegociacaoComContratacaoMaiorQUeValorItem();
		CalculoFornecedorInterna c2 = new FornecedorTemNegociacaoComContratacaoMenorQUeValorItem();
		CalculoFornecedorInterna c3 = new FornecedorTemNegociacaoComContratacaoIgualQUeValorItem();

		
		//Sempre o último
		CalculoFornecedorInterna c4 = new ValorFornecedorSemNegociacao();
		
		c1.setProximo(c2);
		c2.setProximo(c3);
		c3.setProximo(c4);
		
		return c1.calculaValorFornecedor(producaoP);
	}
	
}
