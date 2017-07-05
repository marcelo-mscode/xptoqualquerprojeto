package br.com.sysloccOficial.calculos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.ProdutoGrupo;

public class CalculaFeeReduzido {
	
	@Autowired ProdutoGrupoDAO produtoDAO;
	
	private static BigDecimal valor = new BigDecimal("0");
	
	public static BigDecimal calculaFeeReduzido(Integer idLista){
		
		
		
		valor = valorFee(idLista);
		
		
		return teste;
	}
	
	
	private BigDecimal valorFee(Integer idLista){
		ProdutoGrupoDAO prod  = new ProdutoGrupoDAO();
		
		
		List<ProdutoGrupo> teste = prod.listaProdutosPorIdLista(2806);
		
		BigDecimal somaFeeLista = prod.calculaFee(teste, true);
		
		
		return somaFeeLista;
		
	}
	
	
	
	
	
	
	
	
	
}
