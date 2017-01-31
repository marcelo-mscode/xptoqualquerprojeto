package br.com.sysloccOficial.financeiro;

import org.junit.Test;

import br.com.sysloccOficial.financeiro.contaspagar.VerificaTipoPagto;

public class TesteChainOfResponsabilit {

	
	
	
	@Test
	public void testeIfs(){
		VerificaTipoPagto tipo = new VerificaTipoPagto();
		String teste = tipo.verifica("Boleto Bancário");
		System.out.println(teste);
		
	}
	
}
