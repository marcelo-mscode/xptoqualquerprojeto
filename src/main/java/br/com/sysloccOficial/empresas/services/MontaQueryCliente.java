package br.com.sysloccOficial.empresas.services;

public class MontaQueryCliente {
	
	public static String montaQueryCliente(Integer tipo){
		
		String resultado = "";
		
		if(tipo == 1){ resultado = " cliente != 0 ";	}
		
		if(tipo == 2){ resultado = " prospect != 0 "; }

		if(tipo == 3){ resultado = " cliente != 0 and prospect != 0 "; }

		if(tipo == 5){ resultado = " fornecedor != 0 and cliente != 0 "; }

		if(tipo == 6){ resultado = " fornecedor != 0 and prospect != 0 "; }

		if(tipo == 7){ resultado = " cliente != 0 and prospect != 0 and fornecedor != 0 "; }
		
		if(tipo == 4){ resultado = " fornecedor != 0 ";}
		return resultado;
	}
}
