package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.LocalEvento;

@Repository
public class LocalEventoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void salva(LocalEvento l){
		Job j = manager.find(Job.class, l.getJobId());
		l.setIdJob(j);
        manager.persist(l);
	}

	public List<LocalEvento> mostra(Integer idJob) {
		return manager.createQuery("from LocalEvento where idJob ="+idJob,LocalEvento.class)
		.getResultList();
    }
	
	public List<LocalEvento> mostraLocalModal(Integer idLocalEvento) {
		
		return manager.createQuery("from LocalEvento l join fetch l.idJob where idLocalEvento ="+idLocalEvento,LocalEvento.class)
		.getResultList();
    }

	public void atualiza(LocalEvento l) {
		Job j = manager.find(Job.class, l.getJobId());
		l.setIdJob(j);
		manager.merge(l);
	}
	
	public LocalEvento ultimoLocalEvento(Integer idJob) {
		String consulta = "from LocalEvento where idJob ="+idJob+" order by idLocalEvento desc";
		Query query = manager.createQuery(consulta,LocalEvento.class).setMaxResults(1);
		if(query.getResultList().isEmpty()){
			return null;
		}else{
			return (LocalEvento) query.getSingleResult();
		}
    }
	
	
	
	/*public List<Usuario> listaPorId(Integer i){
		
	return manager.createQuery("select f from Usuario f where idUsuario="+i,Usuario.class)
			.getResultList();
	}
	public List<Departamento> listaDepartamento(){
		
		return manager.createQuery("from Departamento",Departamento.class)
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
