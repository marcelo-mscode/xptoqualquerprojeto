package br.com.sysloccOficial.calculos;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.daos.ProdutoGrupoDAO;

public class CalculaFeeReduzido {
	
	
	@Autowired ProdutoGrupoDAO produtoDAO;
	
	public static BigDecimal calculaFeeReduzido(){
		
		BigDecimal teste = new BigDecimal("1000.00");
		
		
		return teste;
	}
	
	
	private BigDecimal valorFee(Integer idLista){
		
		BigDecimal teste = produtoDAO.listaProdutosPorIdLista(2806);
		
		return null;
		
	}
	
	
	
	
	
}
