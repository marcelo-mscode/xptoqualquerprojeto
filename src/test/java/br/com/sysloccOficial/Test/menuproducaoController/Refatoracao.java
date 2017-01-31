package br.com.sysloccOficial.Test.menuproducaoController;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class Refatoracao {
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMetodoGrande() {
		Funcionario f = new Funcionario("Marcelo",4000,"Programador");
		Funcionario f2 = new Funcionario("Carla",4500,"Analista");
		Funcionario f3 = new Funcionario("Carla",3300,"Secretária");
		
		CalculaBonus c = new CalculaBonus(f.getCargo(), f.getSalario());
		CalculaBonus c2 = new CalculaBonus(f2.getCargo(), f2.getSalario());
		CalculaBonus c3 = new CalculaBonus(f3.getCargo(), f3.getSalario());
		
		assertEquals(4830, c.calcula(),0);
		assertEquals(5951.25, c2.calcula(),0);
		assertEquals(3300, c3.calcula(),0);

		
		
		
		
	}

}
