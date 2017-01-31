package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Marca;


@Repository
public class MarcaDAO {
	
	@PersistenceContext
	private EntityManager manager;

	
	public List<Marca> mostra(){
		return 	manager.createQuery("select m from Marca m order by marca", Marca.class)
				.getResultList();
	}
	
	public void salvaMarca(Marca m){
		manager.persist(m);
	}

	public void editaMarca(Marca m) {
		manager.merge(m);	
	}
	
	
}
