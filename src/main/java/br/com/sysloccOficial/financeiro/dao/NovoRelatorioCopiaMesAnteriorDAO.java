package br.com.sysloccOficial.financeiro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.model.EmprestimoBancario;



@Repository
@Transactional
public class NovoRelatorioCopiaMesAnteriorDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private UtilitariaDatas utilDatas;
	
	public List<Object> copiaOutrosImpostosReflection(int idAnalitico, String nomeTabela){
		
		Integer idAnaliticoAnterior = idAnaliticoAnterior(idAnalitico);

		String buscaImpostos = "FROM "+nomeTabela+" where analitico = "+idAnaliticoAnterior+" and fixo = true";
		
		TypedQuery<Object> list = manager.createQuery(buscaImpostos, Object.class);
		manager.close();
		return list.getResultList();
	}
	
	public int idAnaliticoAnterior(int idAnalitico){
	
		try {
			String buscaIdAnaliticoAnterior = "SELECT idAnalitico FROM FinancAnalitico where idAnalitico <> "+idAnalitico+" order by idAnalitico desc";
			System.out.println("buscaIdAnaliticoAnterior: "+buscaIdAnaliticoAnterior);
			
			TypedQuery<Integer> buscaidAnaliticoAnterior = manager.createQuery(buscaIdAnaliticoAnterior, Integer.class).setMaxResults(1);
			return buscaidAnaliticoAnterior.getSingleResult();
			
		} catch (Exception e) {
			System.out.println("Erro ao pegar idAnalitico anterior: "+e);
			return 0;
		}
		
		
		
	}
	
	
	
	public List<EmprestimoBancario> copiaTabelaEmprestimos(int idAnalitico){
		
		Integer idAnaliticoAnterior = idAnaliticoAnterior(idAnalitico);
		
		String consulta = "FROM EmprestimoBancario where analitico = "+ idAnaliticoAnterior +" AND PAGO <> 1";
		TypedQuery<EmprestimoBancario> list = manager.createQuery(consulta, EmprestimoBancario.class);
		return list.getResultList();
	}	

	public void persisteFinancImpostoReflection(Object  novoFinanc){
		manager.persist(novoFinanc);
		manager.close();
	}
	
	public void persisteEmprestimos(EmprestimoBancario novoEmprestimo){
		manager.persist(novoEmprestimo);
		manager.close();
	}
	
	
}
