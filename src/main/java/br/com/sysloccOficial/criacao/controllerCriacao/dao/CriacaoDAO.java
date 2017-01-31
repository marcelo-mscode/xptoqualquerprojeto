package br.com.sysloccOficial.criacao.controllerCriacao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.CriacaoItemLista;
import br.com.sysloccOficial.model.CriacaoLista;
import br.com.sysloccOficial.model.Grupo;

@Repository
public class CriacaoDAO {

	@PersistenceContext	private EntityManager manager;
	
	public List<CriacaoLista> ListaDeObjetos(String termo){
		Query query = manager.createQuery(termo,CriacaoLista.class);
	return query.getResultList();
	}
	
	public List<CriacaoItemLista> ListaDeItens(String termo){
		Query query = manager.createQuery(termo,CriacaoItemLista.class);
	return query.getResultList();
	}

	public List<Grupo> ListaDeGrupos(String termo){
		Query query = manager.createQuery(termo,Grupo.class);
	return query.getResultList();
	}

	
	
	
	
}
