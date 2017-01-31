package br.com.sysloccOficial.daos;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;

public class Testes{
	public static void main(String[] args) {
		/*String valor = NumberFormat.getCurrencyInstance().format(12345678.90);
		
		System.out.println(valor);*/
		
		String telefone = "1.100,25";   
        
		String telFone = telefone.replace('.', ' ').replace(',', '.').replaceAll(" ", ""); 
		System.out.println(telFone);
		
	
	}
}

