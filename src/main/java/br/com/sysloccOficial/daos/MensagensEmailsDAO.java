package br.com.sysloccOficial.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;


@Repository
public class MensagensEmailsDAO {
	@PersistenceContext
	private EntityManager manager;
	
	
	public String pegaSubject(String origem){
		String consulta = "select c.subject from MensagensEmails c where origem=:origem";
		TypedQuery<String> query = manager.createQuery(consulta, String.class);
		
		query.setParameter("origem", origem);
		
		
		return query.getSingleResult();
	}
	public String PegaTituloJob(Integer idJob){
		
		String consulta = "select j.titulo from Job j where idJob="+idJob;
		
		TypedQuery<String> query = manager.createQuery(consulta, String.class);

		return query.getSingleResult();
	}
	
	
	
	
	public String MontaSubjectDemanda(Integer idJob){
		String subject = "";									
		
		subject = pegaSubject("demanda")+
        PegaTituloJob(idJob);				
		
		return subject;
	}
	
	public String MontaSubjectEstrategia(Integer idJob){
		String subject = "";									
		
		subject = pegaSubject("estrategia")+
        PegaTituloJob(idJob);				
		
		return subject;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}

