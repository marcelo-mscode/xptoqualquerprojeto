package br.com.sysloccOficial.daos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;














import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import br.com.sysloccOficial.model.Anexos;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Departamento;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.NotificaEquipe;
import br.com.sysloccOficial.model.Usuario;

@Repository
public class NotificaEquipeDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void salva(NotificaEquipe n){
		Job j = manager.find(Job.class, n.getIdJobJob());
		n.setIdJob(j);
		
			 n.setIdUsuarioNotificaEquipe(1);
 			 manager.merge(n);
	}
	
//--- Preenche um novo job na tela de Edição ----//
	
	public List<NotificaEquipe> mostraPorNome(Job integer) {
		
		String consulta = "select i from NotificaEquipe i where idJob=:id order by enviadoEm desc";
		
		Query query = manager.createQuery(consulta, NotificaEquipe.class);
		query.setParameter("id", integer);
		
		
		return query.getResultList();
    }
	
}
