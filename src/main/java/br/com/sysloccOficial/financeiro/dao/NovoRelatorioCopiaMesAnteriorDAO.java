package br.com.sysloccOficial.financeiro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.model.FinancImpostos;



@Repository
@Transactional
public class NovoRelatorioCopiaMesAnteriorDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private UtilitariaDatas utilDatas;
	
	public List<FinancImpostos> copiaOutrosImpostos(int idAnalitico){
		
		String buscaIdAnaliticoAnterior = "SELECT idAnalitico FROM FinancAnalitico where idAnalitico <> "+idAnalitico+" order by idAnalitico desc";
		TypedQuery<Integer> buscaidAnaliticoAnterior = manager.createQuery(buscaIdAnaliticoAnterior, Integer.class).setMaxResults(1);
		Integer idAnaliticoAnterior = buscaidAnaliticoAnterior.getSingleResult();
		
		String buscaImpostos = "FROM FinancImpostos where analitico = "+idAnaliticoAnterior+" and fixo = true";
		
		TypedQuery<FinancImpostos> list = manager.createQuery(buscaImpostos, FinancImpostos.class);
		manager.close();
		return list.getResultList();
	}

	public List<Object> copiaOutrosImpostosReflection(int idAnalitico, String nomeTabela){
		
		String buscaIdAnaliticoAnterior = "SELECT idAnalitico FROM FinancAnalitico where idAnalitico <> "+idAnalitico+" order by idAnalitico desc";
		TypedQuery<Integer> buscaidAnaliticoAnterior = manager.createQuery(buscaIdAnaliticoAnterior, Integer.class).setMaxResults(1);
		Integer idAnaliticoAnterior = buscaidAnaliticoAnterior.getSingleResult();
		
		String buscaImpostos = "FROM "+nomeTabela+" where analitico = "+idAnaliticoAnterior+" and fixo = true";
		
		TypedQuery<Object> list = manager.createQuery(buscaImpostos, Object.class);
		manager.close();
		return list.getResultList();
	}
	
	public void persisteFinancImposto(FinancImpostos  novoFinanc){
		manager.persist(novoFinanc);
		manager.close();
	}

	public void persisteFinancImpostoReflection(Object  novoFinanc){
		manager.persist(novoFinanc);
		manager.close();
	}
	
	
}
