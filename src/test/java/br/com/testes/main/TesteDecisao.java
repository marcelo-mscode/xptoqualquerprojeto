package br.com.testes.main;

import javax.swing.JOptionPane;

public class TesteDecisao {
	
	
	
		  public static void main(String[] args) {
			
			  
			  teste();	
			  
		  }
	
		  
		  public static void teste(){
			  
			String nome = "Calculadora";
			int quantidade = 1; 
			int unidade = 1; 
			int diaria = 3; 
			int valor = 360;
			
			
			int resultado;
			
			System.out.println("Iniciando a "+nome);
			
			resultado = quantidade * unidade * diaria * valor;

			JOptionPane.showMessageDialog(null, "O resultado é: "+resultado);
			  
		  }
		  
}
