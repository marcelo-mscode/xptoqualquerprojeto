package br.com.sysloccOficial.Test.menuproducaoController;

public class SalarioMenorQue implements Bonus{
	
	private double salario;

	public SalarioMenorQue(double salario){
		this.salario = salario;
	}
	
	
	public void calculaBonusSalario() {
		salario = salario + salario * 0.15;
		System.out.println("Ganhou bonus por salario de 15%");
	}
	public double getSalario() {
		return salario;
	}


	@Override
	public void setProximoBonus() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
