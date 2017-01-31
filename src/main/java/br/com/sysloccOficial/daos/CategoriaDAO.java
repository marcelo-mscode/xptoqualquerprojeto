package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.Lista;

@Repository
public class CategoriaDAO {

	@PersistenceContext	private EntityManager manager;
	
	public Categoria clonaUMACategoria(Lista _listaNova, Categoria _umaCategorias, int _i) { 
		 Categoria umaCategoria;
		
		 umaCategoria = _umaCategorias;
		 umaCategoria.setIdcategoria(null);
		 umaCategoria.setIdLista(_listaNova);
		 manager.detach(umaCategoria);
		 manager.persist(umaCategoria);
		 return umaCategoria;
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> categoria(Integer _idLista){
		String consulta = "select c from Categoria c where idLista="+_idLista;
		Query query = manager.createQuery(consulta);
		return query.getResultList();
    }
	
	
	
	
}
