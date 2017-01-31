package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Anexos;
import br.com.sysloccOficial.model.Job;

@Repository
public class AnexoDAO {

	@PersistenceContext
	private EntityManager manager;


	
public void saveAnexo(Anexos a){
		
		manager.persist(a);
		
	}
	
	
public Number VerificaAnexoJob(Job job,String origem){
		
		String consulta =  "SELECT count(id) FROM Anexos where anexoidorigem=:idJob and AnexoOrigem =:origem";
		
		TypedQuery<Number> query1 = manager.createQuery(consulta, Number.class);
		
		query1.setParameter("idJob", job);
		query1.setParameter("origem", origem);
		
		Number result = query1.getSingleResult();
		
		return result;
	}

// Pega os anexos apenas pelo id do Job

public Number VerificaAnexo(Job job){
	
	String consulta =  "SELECT count(id) FROM Anexos where anexoidorigem=:idJob";
	
	TypedQuery<Number> query1 = manager.createQuery(consulta, Number.class);
	
	query1.setParameter("idJob", job);
	
	Number result = query1.getSingleResult();
	
	return result;
}

//Pega Anexos pelo tipo e pelo id

public List<Anexos> ListaAnexosJob(String tipo, Integer idJob){
	
	String consulta = "from Anexos where anexoidorigem =:idDoJob and AnexoOrigem=:origem order by criadoData desc	";
	
	Query query1 = manager.createQuery(consulta, Anexos.class);
	query1.setParameter("idDoJob", idJob);	
    query1.setParameter("origem", tipo);
	return query1.getResultList();
}


// Lista Anexos apenas para as estratégias
public List<Anexos> ListaAnexosEstrategias(String tipo, String anexoCod){
	
	System.out.println(anexoCod);
	
	String consulta = "FROM Anexos where anexoOrigem=:tipo and AnexoCod like '%"+anexoCod+"%' order by criadoData desc";
	
	Query query1 = manager.createQuery(consulta, Anexos.class);
	query1.setParameter("tipo", tipo);	
	
     
	return query1.getResultList();
}


	
	}	

