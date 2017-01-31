package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Imposto;


@Repository
public class ImpostoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public List<Imposto> listaImposto(){
		String consulta = "from Imposto i";
		Query query = manager.createQuery(consulta, Imposto.class);
		return query.getResultList();
	}
	
	public void salvaImposto(Imposto imposto){
		 manager.persist(imposto);
	}
	
	public void editaImposto(Imposto imposto){
		 manager.merge(imposto);
	}

	public Object exibeImpostoEditar(Integer idImposto) {

		String consulta = "select i from Imposto i where idImposto=:idImposto";
		Query query = manager.createQuery(consulta, Imposto.class);
		query.setParameter("idImposto", idImposto);
		
		return query.getResultList();
	}
	
	public Object pegaImpostoParaCategoria(Integer idImposto){
		String consulta = "select i.impostoTitulo,i.imposto from Imposto i where idImposto="+idImposto;
		Query query = manager.createQuery(consulta);
		
		return query.getSingleResult();
	}
	
	
	
	
}
