package br.com.sysloccOficial.calculos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.ProdutoGrupo;

public class CalculaFeeReduzido {
	
	public static BigDecimal calculaFeeReduzido(Integer idLista){
		
		BigDecimal valor = valorFee(idLista);
		
		return valor;
	}
	
	
	private static BigDecimal valorFee(Integer idLista){
		ProdutoGrupoDAO prod  = new ProdutoGrupoDAO();
		
		
		List<ProdutoGrupo> teste = prod.listaProdutosPorIdLista(2806);
		
		//BigDecimal somaFeeLista = prod.calculaFee(teste, true);
		
		
		return null;
		
	}
	
	
	
	
	
	
	
	
	
}
