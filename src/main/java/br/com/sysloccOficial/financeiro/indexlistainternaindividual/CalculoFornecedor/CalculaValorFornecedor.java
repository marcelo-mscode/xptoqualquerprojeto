package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.model.producao.ProducaoP;

public class CalculaValorFornecedor {
	
	
	public static BigDecimal calculaValores(ProducaoP producaoP){
		
		//Sempre o último
		CalculoFornecedorInterna c1 = new ValorFornecedorSemNegociacao();

		CalculoFornecedorInterna c2 = new ValorFornecedorTemNegociacao();

		
		
		c1.setProximo(c2);
		
		return c1.calculaValorFornecedor(producaoP);
	}
	
}
