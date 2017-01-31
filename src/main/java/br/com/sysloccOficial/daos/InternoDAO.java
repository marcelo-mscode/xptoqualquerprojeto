package br.com.sysloccOficial.daos;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Departamento;
import br.com.sysloccOficial.model.Interno;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.Usuario;

@Repository
public class InternoDAO {

	@PersistenceContext
	private EntityManager manager;
	@Autowired private Utilitaria util;
	
	
	public void salva(Interno i){
		Job j = manager.find(Job.class, i.getJobId());
		i.setIdJob(j);
        manager.persist(i);
	}
	
	public List<Interno> mostra(Integer idjob, String tipo) {
		
		Query query = manager.createQuery("select i from Interno i where idjob=:idJob"
			                           	+ " and internoTipo =:tipo and notificadoIdUsuario >= 0 order by "
	    		                        + "codInterno desc",Interno.class);
		query.setParameter("idJob", idjob);
		query.setParameter("tipo", tipo);
		
		return query.getResultList();
    }
	
	public List<Interno> mostraJob(Integer idjob) {
		Query query = manager.createQuery("select i from Interno i where idjob=:idJob order by codInterno desc",Interno.class).setMaxResults(1); 
			                           	
		query.setParameter("idJob", idjob);
		
		return query.getResultList();
    }
    
	public List<Interno> mostraInternoAlterar(String codInterno){
		
		
		Query query = manager.createQuery("select i from Interno i where codInterno=:codInterno",Interno.class);
		
		query.setParameter("codInterno", codInterno);
		
		return  query.getResultList();
	}
	
	
	
	
	public void atualizaEstrategia(Interno interno) {

		Job j = manager.find(Job.class, interno.getJobId());
		interno.setIdJob(j);
        manager.merge(interno);
		
	}

// --------------- Conta estratégias -----------------------------------------------------//
	
public Number ContaEstrategiaPorJob(Job idJob,String tipo){
		
		String consulta =  "SELECT count(idInterno) FROM Interno where idJob=:pjob and internoTipo=:tipo";
		
		TypedQuery<Number> query1 = manager.createQuery(consulta, Number.class);
		
        query1.setParameter("pjob", idJob);
        query1.setParameter("tipo", tipo);
		
        Number result = query1.getSingleResult();
		
		return result;
}

public Number ContaDemandasPorJob(Job idJob,String tipo){
	
	String consulta =  "SELECT count(idInterno) FROM Interno where idJob=:pjob";
	
	TypedQuery<Number> query1 = manager.createQuery(consulta, Number.class);
	
    query1.setParameter("pjob", idJob);
	Number result = query1.getSingleResult();
	
	return result;
}

public Number QtdJobsInterno(Integer integer) {
    
	
	
	String consulta =  "SELECT count(idInterno) FROM Interno where idJob="+integer;
	
	TypedQuery<Number> query1 = manager.createQuery(consulta, Number.class);
	/*
    query1.setParameter("pjob", j.getIdJob());*/
	Number result = query1.getSingleResult();
	
	return result;
}
    
    
    
	
public void concluiEstrategia(Integer codInterno){
    	
		Interno i = manager.find(Interno.class, codInterno);
		Calendar c = Calendar.getInstance();
		i.setConcluidoData(c);
        
		Usuario userCriou = util.retornaUsuarioLogado();
		
		i.setConcluidoPor(userCriou);
		
		manager.merge(i);
        
    }

public void CancelaconcluiEstrategia(Integer codInterno){
	
	Interno i = manager.find(Interno.class, codInterno);
	i.setConcluidoData(null);
    
    i.setConcluidoPor(null);

	
	
	manager.merge(i);
    
}

		
}
