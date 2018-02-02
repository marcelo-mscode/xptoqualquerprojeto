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
		 Categoria umaCategoria = new Categoria();
		
		 try {
			 umaCategoria = _umaCategorias;
			 umaCategoria.setIdcategoria(null);
			 umaCategoria.setIdLista(_listaNova);
			 manager.detach(umaCategoria);
			 manager.persist(umaCategoria);
			 return umaCategoria;
			
		} catch (Exception e) {
			System.out.println("Deu Erro ao Clonar Cateogira:" + e);
			return null;
		}
		 
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> categoria(Integer _idLista){
		
		try {
			String consulta = "select c from Categoria c where c.idLista.idLista="+_idLista;
			Query query = manager.createQuery(consulta);
			
			List<Categoria> categorias = query.getResultList();
			
			/*for (int i = 0; i < categorias.size(); i++) {
				System.out.println("Categoria: "+categorias.get(i).getCategoria());
			}*/
			
			return categorias;
			
			
			
			
		} catch (Exception e) {
			System.out.println("Erro em categoria:" + e);
			
			return null;
		}
		
    }
	
	
	
	
}
