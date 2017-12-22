package br.com.sysloccOficial.financeiro.indexlistainternaindividual.CalculoFornecedor;

import java.math.BigDecimal;

import br.com.sysloccOficial.model.producao.ProducaoP;

public interface CalculoFornecedorInterna {

	public ProducaoP calculaValorFornecedor(ProducaoP producaoP);
	public BigDecimal calculaDiferenca(ProducaoP producaoP);
	
	void setProximo(CalculoFornecedorInterna proximo);
	
	
	
	
	
	
	
}
