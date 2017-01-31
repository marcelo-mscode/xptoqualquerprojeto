package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Departamento;
import br.com.sysloccOficial.model.Usuario;

@Repository
public class DepartamentoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void salva(Departamento d){

		
		
	
	}

	public void salvaUsuario(Usuario usuario){
		
		
	}
	
	public List<Departamento> mostra() {
	     return manager.createQuery("select d from Departamento d",Departamento.class)
		.getResultList();
    }
	
	
	
}
