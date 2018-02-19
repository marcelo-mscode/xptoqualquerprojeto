package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class CalculaValorFornecedor {
	
	
	
	public CalculaValorFornecedor(List<ProducaoP> internIndividual){
		
	}

	
	public static BigDecimal calculaValores(ProducaoP producaoP){
		
		CalculoFornecedorInterna c1 = new FornecedorTemNegociacaoComContratacaoMaiorQUeValorItem();
		CalculoFornecedorInterna c2 = new FornecedorTemNegociacaoComContratacaoIgualQUeValorItem();

		
		//Sempre o último
		CalculoFornecedorInterna c3 = new ValorFornecedorSemNegociacao();
		
		c1.setProximo(c2);
		c2.setProximo(c3);
		
		return c1.calculaValorFornecedor(producaoP);
	}
	
}
