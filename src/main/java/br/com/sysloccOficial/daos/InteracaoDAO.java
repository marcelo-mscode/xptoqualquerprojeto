package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Interacao;


@Repository
public class InteracaoDAO {


	@PersistenceContext
	private EntityManager manager;

public void save(Interacao i){
	
	manager.persist(i);
	
}
	
	
	
public List<Interacao> listar(Integer idJob,String origem){
	
	String consulta = "select i from Interacao i  inner join fetch i.idContato where idOrigem=:pidJob and interacaoOrigem=:origem and i.idContato >= 0"
					+ "order by dataInteracao desc";
	Query query = manager.createQuery(consulta, Interacao.class);
	query.setParameter("pidJob", idJob);
	query.setParameter("origem", origem);
	
	
	return query.getResultList();
	
}
	
	
	
	
}
