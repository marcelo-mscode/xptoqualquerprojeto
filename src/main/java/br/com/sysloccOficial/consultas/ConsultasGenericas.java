package br.com.sysloccOficial.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


@Repository
public class ConsultasGenericas {
	
	@PersistenceContext	private EntityManager manager;
	
	
	public List<Object[]> ListaDeObjetos(String termo){
		Query query = manager.createQuery(termo);
		List<Object[]> retorno = query.getResultList();
	return retorno;
	}
	public Object[] Objeto(String termo){
		Query query = manager.createQuery(termo);
		Object[] retorno = (Object[]) query.getSingleResult();
	return retorno;
	}
	public Object ObjetoCompleto(String termo){
		Query query = manager.createQuery(termo);
		Object retorno = query.getSingleResult();
	return retorno;
	}
	
	
	public Integer consultaPorId(String termo){
		 Query query = manager.createQuery(termo);
		 Integer idCriacaoLista = (Integer) query.getSingleResult();
		 return idCriacaoLista;
	 }
}
