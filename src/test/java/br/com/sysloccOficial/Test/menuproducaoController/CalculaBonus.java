package br.com.sysloccOficial.Test.menuproducaoController;

public class CalculaBonus {
	
	private String cargo;
	protected double salario;

	public CalculaBonus(String cargo, double salario){
		this.cargo = cargo;
		this.salario = salario;
	}
	
	public double calcula(){
		porSalario();
		porCargo();
		return salario;
	}

	private void porSalario() {
		if(salario < 3100){
			salarioMenorQue();
		}else if (salario > 3500){
			salarioMaiorQue();
		}else{
			System.out.println("Não atende requistos para bonus de salario");
		}
	}

	private void porCargo() {
		if(cargo.equals("Programador")){
			cargoProgramador();
		}else if(cargo.equals("Analista")){
			cargoAnalista();
		}else{
			System.out.println("Não atende requistos para bonus de Cargo");
		}
	}

	
	private void cargoAnalista() {
		salario = salario + salario*0.15;
		System.out.println("Você é Analista, ganhou mais um Bonus por cargo de 15%, seu salario agora é: "+ salario);
	}

	private void cargoProgramador() {
		salario = salario + salario*0.05;
		System.out.println("Você é Programador, ganhou mais um Bonus por cargo de 5%, seu salario agora é: "+salario);
	}

	private void salarioMaiorQue() {salario = salario + salario*0.15;
	System.out.println("Ganhou bonus por salario de 15%");}

	private void salarioMenorQue() {salario = salario + salario*0.05;
	System.out.println("Ganhou bonus por salario de 5%");}
	
	
	
}
