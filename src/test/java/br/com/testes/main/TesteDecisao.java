package br.com.testes.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TesteDecisao {
	
	
	
		  public static void main(String[] args) {
			
			  
			  teste();	
			  
		  }
	
		  
		  public static void teste(){
			  
			  
			  BigDecimal valor = new BigDecimal("0.00");
			  
			  
			  
			  if(valor.compareTo(BigDecimal.ZERO) == -1){
				  System.out.println("Valor é menor");
			  }
			  if(valor.compareTo(BigDecimal.ZERO) == 0){
				  System.out.println("Valor é igual");
			  }
			  if(valor.compareTo(BigDecimal.ZERO) == 1){
				  System.out.println("Valor é maior");
			  }

			  
		  }
		  
}
