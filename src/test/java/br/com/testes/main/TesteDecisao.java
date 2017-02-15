package br.com.testes.main;

import java.util.ArrayList;
import java.util.List;

public class TesteDecisao {
	
	
	
		  public static void main(String[] args) {
			
			  
			  teste();	
			  
		  }
	
		  
		  public static void teste(){
			  
			
			  List<Integer[]> teste = new ArrayList<Integer[]>();
			  
			  
			  Integer[] reg = new Integer[2];
			  
			  reg[0] = 1;
			  reg[1] = 10;
			  
			  
			  teste.add(reg);
			  
			  
			  for(int i = 0; i <  teste.size();i++){
				  if(teste.get(i)[1] == 10)
				  System.out.println(teste.get(i)[1]);
			  }
			  
			  
			  
			  
			  
		  }
		  
}
