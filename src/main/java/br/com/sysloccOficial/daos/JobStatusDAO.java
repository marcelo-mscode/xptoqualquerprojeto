package br.com.sysloccOficial.daos;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import br.com.sysloccOficial.model.JobStatus;

@Repository
public class JobStatusDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public List<JobStatus> mostraPorId(Integer idJob) {
	    return manager.createQuery("from JobStatus j where idJob="+idJob,JobStatus.class).getResultList();
    }
	
	public List<JobStatus> mostra() {
	    return manager.createQuery("from JobStatus",JobStatus.class).getResultList();
	}
    
	
	public Date data(Integer idJob){
		String consulta = "select prazoConclusao from JobStatus where idJob="+idJob+" order by idJobStatus desc";
		TypedQuery<Date> query = manager.createQuery(consulta, Date.class).setMaxResults(1);
		return query.getSingleResult();
	}
	
	public Date listaDatas(Integer idJob){
		String consulta = "select propostaData from Job where idJob="+idJob+" order by propostaData desc";
		TypedQuery<Date> query = manager.createQuery(consulta, Date.class).setMaxResults(1);
		return query.getSingleResult();
	}
	
}	
	
	
