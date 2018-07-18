package br.com.testeexcecoes;

import java.io.FileNotFoundException;

public class TesteErro {

	public static void main(String[] args) throws FileNotFoundException {
		
		metodo1();
		
	}
	private static void metodo1() throws java.io.FileNotFoundException {
	
		new java.io.FileInputStream("arquivo.txt");
	
	}
		

}
