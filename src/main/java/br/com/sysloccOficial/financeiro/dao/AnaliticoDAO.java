package br.com.sysloccOficial.financeiro.dao;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;


@Repository
@Transactional
public class AnaliticoDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private UtilitariaDatas utilDatas;
	
	public FinancAnalitico salvaNovoAnalitico(FinancAnalitico novoAnalitico) {
		
		try {
			
			Integer numeroMes = utilDatas.referenciaMesAnalitico(novoAnalitico.getMesA());
			novoAnalitico.setMesReferencia(numeroMes);
			manager.persist(novoAnalitico);
			return novoAnalitico;
		} catch (Exception e) {
			System.out.println("Deu um  erro ao fazer novo analitico");
			
			return null;
		}
	}
	
	public List<String> indexAnaliticoAno(){
		try {
			TypedQuery<String> q = manager.createQuery("SELECT distinct(anoA) FROM FinancAnalitico where anoA > 2017 order by anoA DESC",String.class);
			return q.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<FinancAnalitico> indexAnalitico(){
		try {
			TypedQuery<FinancAnalitico> q = manager.createQuery("from FinancAnalitico order by anoA desc, mesReferencia desc",FinancAnalitico.class);
			return q.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<String> indexAnaliticoPorMesAno(int anoA){
		try {
			TypedQuery<String> q = manager.createQuery("select mesA from FinancAnalitico where anoA = "+anoA+" order by mesreferencia",String.class);
			return q.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
}
