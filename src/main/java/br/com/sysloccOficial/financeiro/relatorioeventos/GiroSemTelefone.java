package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.Lista;


@Component
public class GiroSemTelefone implements Giro{
	
	@Autowired RelatorioEventoDAO relatorioEventoDAO;
	

	private Lista lista;
	private List<RelatorioBVS> relatorioBVS;
	private List<CachePadrao> relatorio2;


	public GiroSemTelefone(){}
	
	public GiroSemTelefone(Lista lista,List<RelatorioBVS> relatorioBVS,List<CachePadrao> relatorio2){
		this.lista = lista;
		this.relatorioBVS = relatorioBVS;
		this.relatorio2 = relatorio2;
	}
	
	
	
	@Override
	public BigDecimal calculoGiro() {
		// valorLiquido
		BigDecimal valorLiquido = lista.getValorTotal().subtract(lista.getValorTotal().multiply(new BigDecimal("0.1595"))); 
		// Caches
		BigDecimal totalCaches  = calculaValorTotalCaches();
		// BVs
		BigDecimal bvs = new BigDecimal("0");
		// Internas
		BigDecimal internas = new BigDecimal("0");
		// Externas
//		BigDecimal externas = calculoTotalExternas();
		BigDecimal externas = new BigDecimal("5257.09");
		
		BigDecimal giroSemTelefone = valorLiquido.add(bvs).subtract(totalCaches).subtract(internas).subtract(externas);

		return giroSemTelefone;
	}

	@Override
	public BigDecimal calculoTotalDiferenca() {
		BigDecimal totalDiferenca  = new BigDecimal("2241.11");
		/*for (int i = 0; i < relatorioBVS.size(); i++) {
			totalDiferenca = totalDiferenca.add(relatorioBVS.get(i).getDiferenca());
		}*/
		return totalDiferenca;
	}
	
	
	public BigDecimal calculoTotalExternas(){
		BigDecimal calculoExternas = new BigDecimal("0");
		for (int i = 0; i < relatorioBVS.size(); i++) {
			calculoExternas = calculoExternas.add(relatorioBVS.get(i).getValorParaPagar());
		}
		
		return calculoExternas;
	}
	
	
	public BigDecimal calculaValorTotalCaches(){
		
		
		/*for (int i = 0; i < relatorio.size(); i++) {
			System.out.println(relatorio.get(i).getNomeFunc()+"\n"+relatorio.get(i).getValorPorcentagem());
		}*/
		
		
	/*	List<CachePadrao> relatorios1 = relatorioEventoDAO.relatorioCaches(new BigDecimal("2241.11"));*/
		
	//	System.out.println("teste");
		
		RelatorioCachesOutros relatorioCachesOutros = new RelatorioCachesOutros(relatorio2, calculoTotalDiferenca());
		BigDecimal totalCaches = relatorioCachesOutros.getTotalCaches();
		return totalCaches;
		
	}
	
	
}
