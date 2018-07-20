package br.com.sysloccOficial.financeiro.funcionario.cacheCalculos;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.model.CachePadrao;

public class CalculadoraCachesTotais {
	
	
	public static BigDecimal totalCachesSemTelefone(List<CachePadrao> listaRelatorioCaches,BigDecimal totalDiferencaSemTelefone){
		
		BigDecimal calculoFinal = new BigDecimal("0.00");
		
		if(totalDiferencaSemTelefone.equals(new BigDecimal("0.00"))){
			return calculoFinal;
		}else{
			
			CacheFuncionarios funcionarioSTelefone = new CacheFuncionarioSTelefone();
			CacheFuncionarios diretoriaSTelefone = new CacheDiretoriaSTelefone();
			
			BigDecimal totalCacheFuncionariosSemTelefone = funcionarioSTelefone.calculaCacheSemtelefone(listaRelatorioCaches, totalDiferencaSemTelefone);
			BigDecimal totalCacheDiretoriaSemTelefone = diretoriaSTelefone.calculaCacheSemtelefone(listaRelatorioCaches, totalDiferencaSemTelefone);
			
			
			calculoFinal = totalCacheDiretoriaSemTelefone.add(totalCacheFuncionariosSemTelefone);
			
			return calculoFinal;
		}
		
		
	}
	
	public static BigDecimal totalCacheFuncionario(List<CachePadrao> relatorio,BigDecimal totalDiferencaSemTelefone){
		CacheFuncionarios funcionarioSemTelefone = new CacheFuncionarioSTelefone();
		return funcionarioSemTelefone.calculaCacheSemtelefone(relatorio, totalDiferencaSemTelefone);
	}

	public static BigDecimal totalCacheDiretoria(List<CachePadrao> relatorio,BigDecimal totalDiferencaSemTelefone){
		CacheFuncionarios diretoriaSemTelefone = new CacheDiretoriaSTelefone();
		return diretoriaSemTelefone.calculaCacheSemtelefone(relatorio, totalDiferencaSemTelefone);
	}
	
	
	

}
