package br.com.sysloccOficial.controllers;

import org.springframework.beans.factory.annotation.Autowired;

public class TestaArquivos {
	@Autowired
	
	
	
	
	public static String teste(Integer num){
	return num == 0 ? "Feminino" : "Masculino";
	}
		
	
	
	
	public static void main(String args[]) throws java.text.ParseException{ 
	    
	System.out.println(teste(1));
	

    }
	
}
