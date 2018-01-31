package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class CalculaValorFornecedor {
	
	
	
	public CalculaValorFornecedor(List<ProducaoP> internIndividual){
		
	}

	
	public static BigDecimal calculaValores(ProducaoP producaoP){
		
		
				
		
		
		CalculoFornecedorInterna c1 = new FornecedorTemNegociacaoComContratacaoMaiorQUeValorItem();

		
		//Sempre o último
		CalculoFornecedorInterna c2 = new ValorFornecedorSemNegociacao();
		
		c1.setProximo(c2);
		
		return c1.calculaValorFornecedor(producaoP);
	}
	
}
