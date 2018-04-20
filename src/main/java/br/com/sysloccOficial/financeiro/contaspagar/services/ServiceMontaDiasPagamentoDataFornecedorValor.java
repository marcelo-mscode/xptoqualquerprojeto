package br.com.sysloccOficial.financeiro.contaspagar.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.conf.Utilitaria;

@Service
public class ServiceMontaDiasPagamentoDataFornecedorValor {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	
	/**
	 * Pega todas as datas para pagar a Lord
	 * 45 - 2016-07-25
	 * 60 - 2016-08-09
	 */
	public List<Object[]> montaDiasPagamentoDataFornecedorValor(List<Integer> listaUmFinanceiro) {
		try {
			String consultaDiasPrazoPagamento = util.limpaSqlComList("SELECT"
					+ " distinct(diasPrazoParaPagamento),"
					+ " dtPgotFornecedor.dataPagar, "
					+ " idEmpresa.empresa,"
					+ " valor,"
					+ " idEmpresa.idEmpresa,"
					+ " idFornecedorFinanceiro.contratacao,"
					+ " idFornecedorFinanceiro.idProducao.temMesmoFornecedor,"
					+ " idFornecedorFinanceiro.idProducao.valorDePagamentoContratacao,"
					+ " idFornecedorFinanceiro.idProducao.valorContratacao"
					+ " FROM ValorPagtoFornecedor where idFornecedorFinanceiro in("
					+ listaUmFinanceiro+") and dtPgotFornecedor.Status = 'PENDENTE' order by diasPrazoParaPagamento");
			TypedQuery<Object[]> listaT = manager.createQuery(consultaDiasPrazoPagamento,Object[].class);
			List<Object[]> lista = listaT.getResultList();
			return lista;
			
		} catch (Exception e) {
			System.out.println("Deu um erro: "+e);
			System.out.println("Com os ids: "+listaUmFinanceiro);
			return null;
		}
		
	}
	
	
	
}
