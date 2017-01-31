package br.com.sysloccOficial.Test.menuproducaoController;

public class SalarioMaiorQue implements Bonus{
	
	private double salario;

	public SalarioMaiorQue(double s){
		this.salario = s;
	}

	public void calculaBonusSalario() {
		if(salario > 3100){
			salario = salario + salario * 0.15;
			System.out.println("Ganhou bonus por salario de 15%");
		}else{
	//		return 0;
		}
	}

	public double getSalario() {
		return salario;
	}

	@Override
	public void setProximoBonus() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
