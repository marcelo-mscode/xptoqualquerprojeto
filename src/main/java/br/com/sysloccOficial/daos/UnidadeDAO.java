package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Unidade;


@Repository
public class UnidadeDAO {
	
	@PersistenceContext
	private EntityManager manager;

	
	public List<Unidade> mostra(){
		return 	manager.createQuery("select u from Unidade u order by unidade", Unidade.class)
				.getResultList();
	}
	 
	public List<Unidade> EncontraUnidade(Integer id){
		return (List<Unidade>) manager.find(Unidade.class,id);
		
	}
	
	
	
	
	
}
