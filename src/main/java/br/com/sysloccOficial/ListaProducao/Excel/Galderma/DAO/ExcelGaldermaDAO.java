package br.com.sysloccOficial.ListaProducao.Excel.Galderma.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

import br.com.sysloccOficial.model.CenariosGalderma;

@Component
public class ExcelGaldermaDAO {
	
	@PersistenceContext private EntityManager manager;
	
	
	public CenariosGalderma verificaPlanilhaFilha(Integer idLista){
	        // planilha filha ?
    		TypedQuery<CenariosGalderma> cenariosFilha = manager.createQuery("from CenariosGalderma where planilhaFilha = "+idLista, CenariosGalderma.class);
    		cenariosFilha.getSingleResult();
    		// chamar método para verificar qts filhas tem
    		System.out.println("É planilha filha, chamando método para verificar qts filhas tem");
    		// Espelhar essa Planilha
    		System.out.println("Espelhando essa Planilha");
    		// Salvar no BD id da nova planilha espelhada junto com idPlanilha Mãe, somar 1 na qtd de planilhas filhas e salvar no cenario filha
    		System.out.println("Salvando no BD id da nova planilha espelhada junto com idPlanilha Mãe, somar 1 na qtd de planilhas filhas e salvar no cenario filha");
    		return cenariosFilha.getSingleResult();
	}
	
	public CenariosGalderma verificaPlanilhaMae(Integer idLista){
		
		TypedQuery<CenariosGalderma> cenariosMae = manager.createQuery("from CenariosGalderma where planilhaMae = "+idLista, CenariosGalderma.class);
		cenariosMae.getSingleResult();
		// chamar método para verificar qts filhas tem
		System.out.println("É planilha Mãe, chamando método para verificar qts filhas tem");
		// Espelhar essa Planilha
		System.out.println("Espelhando Planilha Mãe");
		// Salvar no BD id da nova planilha espelhada junto com idPlanilha Mãe, somar 1 na qtd de planilhas filhas e salvar no cenario filha
		System.out.println("É planilha Mãe, Salvando no BD id da nova planilha espelhada junto com idPlanilha Mãe, somar 1 na qtd de planilhas filhas e salvar no cenario filha");
		
		return cenariosMae.getSingleResult();
		
	}
	
	public void espelhaPlanilhaCriaCenario(Integer idLista){
		
		// Espelhar essa Planilha
		System.out.println("NÃO é planilha Mãe, espelhando essa Planilha para virar Mãe");
		// Salvar no BD id da nova planilha espelhada junto com idPlanilha Mãe, salvar no cenario filha 2
		System.out.println("NÃO é planilha Mãe, Salvando no BD id da nova planilha espelhada junto com idPlanilha Mãe, salvar no cenario filha como 2");
	}
	
	
	public void salvaidsNovoCenario(Integer idPlanilhaMae, Integer idPlanilhaFilha,Integer numeroCenario){
		
		CenariosGalderma novoCenario = new CenariosGalderma();
		novoCenario.setPlanilhaMae(idPlanilhaMae);
		novoCenario.setPlanilhaFilha(idPlanilhaFilha);
		novoCenario.setCenarioFilha(numeroCenario);
		manager.persist(novoCenario);
	}
	
	
	
	
}
