package br.com.sysloccOficial.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Comunicador;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Empresa;

@Repository
public class ContatoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
		public void salva(Contato c){
			
			Empresa e = manager.find(Empresa.class, c.getIdEmp());
			c.setEmpresa(e);
			
			manager.persist(c);
		}
	
		public void salvaAjax(Contato c){
				
				manager.persist(c);
		}

		public List<Comunicador> buscaUltimoComunicador(Integer idContato){
			String consulta ="select com from Comunicador com where idContato=:idcontato";
			
			TypedQuery<Comunicador>query = manager.createQuery(consulta, Comunicador.class);			
			query.setParameter("idcontato", idContato);
			
			return query.getResultList();
		}
		
		public Comunicador buscaComunicadorPorId(Integer idComunicador){
			
			String consulta ="select com from Comunicador com where idComunicador=:idComunicador";
			
			TypedQuery<Comunicador> query = manager.createQuery(consulta, Comunicador.class);			
			query.setParameter("idComunicador", idComunicador);
			
			return query.getSingleResult();
		}
		
		
		
		
		
		public List<Contato> mostraContato(Integer id) {
			
			return manager.createQuery("select distinct c from Contato c join fetch c.comunicador com where c.empresa="+id+
					                   " and c.habilitado <> 0 and c.idContato > 0 order by contato",Contato.class)
			.getResultList();
	    }
	
		public List<Comunicador> mostra(Integer id) {
		
		return manager.createQuery("select c from Comunicador c join c.contato com where com.empresa="+id,Comunicador.class)
			/*.setMaxResults(3)*/
		.getResultList();
		}
		
		public List<Contato> mostraContatosEmInfoEmpresa(Integer idEmp){
			//String consulta = "from Contato j join fetch j.comunicador where idEmpresa=:idEmp order by j.contato";
			String consulta = "from Contato j where idEmpresa=:idEmp order by j.contato";
			Query query = manager.createQuery(consulta, Contato.class);
			
	 		query.setParameter("idEmp", idEmp);
			
		return query.getResultList();
		}
		
		public Integer ultimoContatoAdicionado(){
			String consulta = "select c.idContato from Contato c order by idContato desc";
			TypedQuery<Integer> query = manager.createQuery(consulta, Integer.class)
			.setMaxResults(1);
			return query.getSingleResult();
		}
		
		
		
		public List<Contato>PreeencheListaEdicaoContatoJob(Integer idContato){
			return manager.createQuery("select distinct(c) from Contato c LEFT JOIN FETCH c.comunicador com where c.idContato="+idContato,Contato.class).getResultList();
		}


		public List<Comunicador>PreeencheListaEdicaoComunicadorJob(Integer idContato){
	
			String consulta = "from Comunicador where idContato=:idContato";
			
			
			Query query = manager.createQuery(consulta, Comunicador.class);
			query.setParameter("idContato", idContato);
			
			
			return query.getResultList();
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
