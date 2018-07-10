package br.com.sysloccOficial.financeiro.contaspagar.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.model.producao.DtPgtoFornecedor;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Repository
@Transactional
public class ContasPagarRepository {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	
	public List<Object[]> pegaListasMesAtual() {
		String dataHoje =  UtilitariaDatas.pegaDataAtualEmStringPassandoFormato("yyyy-MM");
		String idsListasIndiv = "select distinct(lista.idLista), lista.lista from ProducaoP where lista.dataDoEvento like '%"+dataHoje+"%' order by lista.dataDoEvento desc";
		
		TypedQuery<Object[]> listaIds = manager.createQuery(idsListasIndiv,Object[].class);
		List<Object[]> idListas = listaIds.getResultList();
		return idListas;
	}
	
	public List<Integer> pegaIdsFornecedoresPorIdLista(Integer idListas) {
		String consulta = "select distinct(idEmpFornecedor.idEmpresa) from ProducaoP where idLista ="+idListas;
		TypedQuery<Integer> conLista = manager.createQuery(consulta, Integer.class);
		List<Integer> idsFornecedores = conLista.getResultList();
		return idsFornecedores;
	}

	/**
	 * Pega idsProducaoP de um Fornecedor Pelo id da Lista
	 * 
	 */

	public List<Integer> listaIdsProducaoPDeUmFornecedorPorLista(Integer idLista,Integer idFornecedor){
		String idsProducaoPDeUmFornecedorPorLista = "SELECT idProducao FROM ProducaoP where lista.idLista ="+idLista+" and idEmpFornecedor ="+idFornecedor;
		try {
			TypedQuery<Integer> listaProdPUmForn = manager.createQuery(idsProducaoPDeUmFornecedorPorLista, Integer.class);
			return listaProdPUmForn.getResultList();
			
		} catch (Exception e) {
			System.out.println("Erro aqui:" + e);
			return null;
		}
		
	}
	
	public List<Integer> pegaListaFornecedorFinanceiroPorIdsProducao(List<Integer> listaUmFornecedor){
		
		String consultaLimpaFfinanceiro = util.limpaSqlComList("SELECT idFornecedor FROM"
				+ " FornecedorFinanceiro where idProducao in ("+listaUmFornecedor+")");
		
		try {
			TypedQuery<Integer> lista2 = manager.createQuery(consultaLimpaFfinanceiro,Integer.class);
			return lista2.getResultList();
			
		} catch (Exception e) {
			System.out.println("Erro em MontaContasPagarDAO, linha 169 : "+ e);
			return null;
		}
	}
	
	public ProducaoP pegaProducaoP (Integer idLista,Integer idFornecedor){
		
		try {
			String consultaModoPagamento = "FROM ProducaoP where lista.idLista ="+idLista+" and idEmpFornecedor ="+idFornecedor;
			TypedQuery<ProducaoP> consultaProducaP = manager.createQuery(consultaModoPagamento, ProducaoP.class).setMaxResults(1);
			return consultaProducaP.getSingleResult();
		} catch (Exception e) {
			System.out.println("Erro : " +e);
			return null;
		}
	}

	public List<DtPgtoFornecedor> pegarContasPendente() {
		TypedQuery<DtPgtoFornecedor> query = manager.createQuery("FROM DtPgtoFornecedor where status = 'Pendente'", DtPgtoFornecedor.class);
		return query.getResultList();
	}
	
}
