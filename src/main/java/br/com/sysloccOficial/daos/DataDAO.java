package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.DataTeste;

@Repository
public class DataDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void salva(DataTeste d){
		
		manager.persist(d);
		
	}

	public List<DataTeste> mostra() {
		
		return manager.createQuery("select dt from DataTeste dt order by date desc",DataTeste.class)
		/*.setMaxResults(10)*/		
		.getResultList();
    }
 
	/*
	public List<Usuario> listaPorId(Integer i){
		
	return manager.createQuery("select f from Usuario f where idUsuario="+i,Usuario.class)
			.getResultList();
	}
	
	public void atualiza(Usuario u){
		Departamento d = manager.find(Departamento.class, u.getIdDep());
		u.setDepartamento(d);
		manager.merge(u);
	}

	public void remove(Integer u) {
		Usuario user = manager.find(Usuario.class, u);
		manager.remove(user);
	}
	
	*/
	
	
	
/*	public List<Departamento> mostraDepartamentos() {
		return manager.createQuery("select d from Departamento d",Departamento.class)
		.getResultList();
    }
*/	

/*	public List<Usuario> UsuarioDepartamento(){
		Departamento d = manager.find(Departamento.class, 1);
		System.out.println("Quantos usuarios nesse dep ? "+ d.getDepartamento());
		List<Usuario> dep = d.getUsuario();
		for (Usuario usuario : dep) {
			System.out.println("\nNome: "+ usuario.getNome());
		}
		return d.getUsuario();
	}
*/	
	
	
		
}
