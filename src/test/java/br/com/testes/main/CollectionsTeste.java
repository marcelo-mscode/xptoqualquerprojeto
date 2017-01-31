package br.com.testes.main;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.swing.JOptionPane;

public class CollectionsTeste {

	
	public static void main(String[] args) {
		
		
		System.out.println("Iniciando ...");
	
		Collection<Integer> teste = new LinkedHashSet<Integer>();
		
		long inicio = System.currentTimeMillis();
		int total = 30;
		
		for (int i = 0; i < total; i++) {
			teste.add(i);
		}
		
		for (int i = 0; i < total; i++) {
			teste.contains(i);
		}
		
		long fim = System.currentTimeMillis();
		
		long tempo = fim - inicio;
		
		System.out.println("Tempo gasto: "+tempo);
		
		
		testaBigdecmaicl(new BigDecimal("0.01"));
		
		
		
		
		
	}
	
	
	
	
	public static void testaBigdecmaicl(BigDecimal b){
		
		BigDecimal zero = new BigDecimal("0.00");
		
		
		if(b.equals(zero)){
			JOptionPane.showMessageDialog(null, "Sou igual");
		}else{
			JOptionPane.showMessageDialog(null, "Sou Diferente");
		}
			
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
