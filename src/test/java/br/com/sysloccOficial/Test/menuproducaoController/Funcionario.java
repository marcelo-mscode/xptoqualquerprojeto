package br.com.sysloccOficial.Test.menuproducaoController;

public class Funcionario {

	public String nome;
	public double salario;
	public String cargo;

	public Funcionario(String nome,double salario,String cargo){
		this.nome = nome;
		this.salario = salario;
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public double getSalario() {
		return salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}
