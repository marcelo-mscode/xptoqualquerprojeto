package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.com.sysloccOficial.model.Genero;



@Repository
public class GeneroDAO {
	
	@PersistenceContext
	private EntityManager manager;

	
	public List<Genero> mostra(){
		return 	manager.createQuery("select g from Genero g order by genero", Genero.class)
				.getResultList();
	}
	
	public List<Genero> generoPorId(){
		return 	manager.createQuery("select g from Genero g order by genero", Genero.class)
				.getResultList();
	}
	
	
	
}
