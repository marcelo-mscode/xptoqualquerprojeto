package br.com.sysloccOficial.Test.menuproducaoController;

public class PorSalario extends CalculaBonus{

	public PorSalario(String cargo, double salario) {
		super(cargo, salario);
	}
	
	SalarioMaiorQue salarioMais = new SalarioMaiorQue(salario);
	
	

}
