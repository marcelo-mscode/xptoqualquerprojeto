package br.com.sysloccOficial.daos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Producao;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.producao.FornecedorFinanceiro;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Repository
public class MenuProducaoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public Producao listaProducao(Integer idProdutoGrupo){
		
		try {
			String consulta = "select p from Producao p where idProdutoGrupo = "+idProdutoGrupo;
			Query query = manager.createQuery(consulta, Producao.class);
			return (Producao) query.getSingleResult();
			
		} catch (Exception e) {
			return null;// TODO: handle exception
		}
	}
	
	public Set<FornecedorFinanceiro> pegaFornecedorFinanceiro(Integer idProdutoGrupo, Integer idEmpresa){
		String consulta = "select idProducao from ProducaoP where idProdutoGrupo = "+idProdutoGrupo+ " and idEmpFornecedor=" +idEmpresa;
	
		try {
			TypedQuery<Integer> q = manager.createQuery(consulta, Integer.class);
			Integer idProducao = q.getSingleResult();
			String consultaFornFinac = "select f from FornecedorFinanceiro f join fetch f.idValorPgtoFornecedor v"
									 + " join fetch v.dtPgotFornecedor where idProducao="+idProducao;
			
			TypedQuery<FornecedorFinanceiro> f = manager.createQuery(consultaFornFinac, FornecedorFinanceiro.class);
			
			Set<FornecedorFinanceiro> listaDeProdutoGrupo = new HashSet<FornecedorFinanceiro>(f.getResultList());
			
			return listaDeProdutoGrupo;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	public List<Producao> ConfereItemProducao(Integer idProdutoGrupo){
		String consulta = "select p from Producao p where idProdutoGrupo = "+idProdutoGrupo;
		TypedQuery<Producao> query = manager.createQuery(consulta, Producao.class);

		try {
			 return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	public ProducaoP pegaProducao(Integer idProducao){
		try {
			ProducaoP p = manager.find(ProducaoP.class, idProducao);
			return p;
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
}
