package br.com.sysloccOficial.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import br.com.sysloccOficial.model.Anexos;

@Repository
public class DemandaDAO {

	@PersistenceContext
	private EntityManager manager;
	

	// Lista Anexos apenas para as estratégias
public List<Anexos> ListaAnexosEstrategias(String tipo, String anexoCod){
		
		System.out.println(anexoCod);
		
		String consulta = "FROM Anexos where anexoOrigem=:tipo and AnexoCod like '%"+anexoCod+"%'";
		
		Query query1 = manager.createQuery(consulta, Anexos.class);
		query1.setParameter("tipo", tipo);	
		
	     
		return query1.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public void salva(Job j){
		Empresa e = manager.find(Empresa.class, j.getIdEmp());
		j.setEmpresa(e);
		
		Contato c = manager.find(Contato.class, j.getIdContato());
		j.setContato(c);
		
		
		manager.persist(j);
		
	}
	
//--- Preenche um novo job na tela de Edição ----//
	
	public List<Job> mostraPorNome(String titulo) {
		
		return manager.createQuery("select j from Job j join fetch j.empresa order by idJob desc",Job.class) 		
		.setMaxResults(1)
		.getResultList();
    }
//----------------------------------------------//
	
	
	
//------- Lista os Jobs na página principal ----//

	public List<Job> listaJob(){
		
		return manager.createQuery("from Job j join fetch j.contatos where idContatoResponsavel >= 0 order by criadoem desc",Job.class)
				.setMaxResults(1000)
				.getResultList();
	}
// --------------------------------------------//	
	
	
	public List<Job> mostraPorId(Integer idJob) {
		
		    Query query = (Query) manager.createQuery("select j from Job j join fetch j.empresa where j.idJob=:Job",Job.class);
		    query.setParameter("Job", idJob);
		    
		    return query.getResultList();
		    
	    }
	
	public void atualiza(Job j){
		Empresa e = manager.find(Empresa.class, j.getIdEmp());
		j.setEmpresa(e);
		Contato c = manager.find(Contato.class, j.getIdContato());
		j.setContato(c);
		
		manager.merge(j);
	}
	
	public List<Job> dataCriadoEm(Integer idJob){
		
		Query query = manager.createQuery("from Job where idJob=:idJob",Job.class);
		
		query.setParameter("idJob",idJob);
		
		return query.getResultList();
	}
	
	public Number codJob(){
		
		Calendar c= Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM");  
	    String a = s.format(c.getTime());
		
		
		String consulta =  "Select count(j) FROM Job j where criadoem like '%"+a+"%'";
		
		TypedQuery<Number> query1 = manager.createQuery(consulta, Number.class);
		
		Number result = query1.getSingleResult();
		
		return result;
	}
	public void saveAnexo(Anexos a){
		
		manager.persist(a);
		
	}
	public Job recebeObjeto(Integer i){
    	Job j = manager.find(Job.class, i);
    	return j;
    	
    }
	*/
	
	
	
	/*public List<Usuario> mostra() {
		return manager.createQuery("select f from Usuario f order by nome",Usuario.class)
			.setMaxResults(10)		
		.getResultList();
    }

	public List<Usuario> listaPorId(Integer i){
		
	return manager.createQuery("select f from Usuario f where idUsuario="+i,Usuario.class)
			.getResultList();
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
