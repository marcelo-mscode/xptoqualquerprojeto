package br.com.sysloccOficial.financeiro.contaspagar.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.conf.Utilitaria;

@Service
public class ServicePegaDiasPagamento {

	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	
	
	/**
	 * Pega os dias que foram parcelados 
	 * 
	 * 
	 * @param listaUmFinanceiro
	 * @return
	 */
	public List<Integer> pegaDiasPagamento(List<Integer> listaUmFinanceiro) {
		
		try {
			String consultaDiasPrazoPagamento = util.limpaSqlComList("SELECT distinct(diasPrazoParaPagamento)"
					+ " FROM ValorPagtoFornecedor where idFornecedorFinanceiro in("
					+ listaUmFinanceiro+") and dtPgotFornecedor.Status = 'PENDENTE' order by diasPrazoParaPagamento");
			TypedQuery<Integer> listaT = manager.createQuery(consultaDiasPrazoPagamento,Integer.class);
			List<Integer> lista = listaT.getResultList();
			
			
			return lista;
			
		} catch (Exception e) {
			
			System.out.println("Erro: "+e);
			
			return null;
			
		}
		
	}
	
	
	
}
