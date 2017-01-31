package br.com.testes.main;

public class ContaCorrenteTeste implements Comparable<ContaCorrenteTeste>{
	
	
	private double depositaValor;

	public double getDepositaValor() {
		return depositaValor;
	}

	public void setDepositaValor(double depositaValor) {
		this.depositaValor = depositaValor;
	}

	@Override
	public int compareTo(ContaCorrenteTeste outra) {
		
		if(this.depositaValor < outra.depositaValor){
			return -1;
		}
		if(this.depositaValor > outra.depositaValor){
			return 1;
		}
		return 0;
	}
	
}
