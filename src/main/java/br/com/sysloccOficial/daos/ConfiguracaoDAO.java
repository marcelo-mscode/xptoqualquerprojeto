package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Configuracao;


@Repository
public class ConfiguracaoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Configuracao> listaConfiguracao(){
		
		String consulta = "from Configuracao";
		Query query = manager.createQuery(consulta, Configuracao.class);
		
		return query.getResultList();
		
	}
	
	public void salvaConfiguracao(Configuracao c){
		manager.merge(c);
	}
	
	
	
}
