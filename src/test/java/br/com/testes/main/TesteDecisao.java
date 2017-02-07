package br.com.testes.main;

import javax.swing.JOptionPane;

public class TesteDecisao {
	
	
	
		  public static void main(String[] args) {
			
			  
			  teste();	
			  
		  }
	
		  
		  public static void teste(){
			  
			String nome = "Calculadora";
			int idade = 15; 
			int idade2 = 15; 
			int resultado;
			
			System.out.println("Iniciando a "+nome);
			
			resultado = idade - idade2;

			JOptionPane.showMessageDialog(null, "O resultado é: "+resultado);
			  
		  }
		  
}
