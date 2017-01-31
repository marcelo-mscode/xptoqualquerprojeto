package br.com.sysloccOficial.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Atuacao;

@Repository
public class AtuacaoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void salva(Atuacao a){
		manager.persist(a);
	}

	public List<Atuacao> mostra() {
		return manager.createQuery("select a from Atuacao a order by atuacao",Atuacao.class)
		/*.setMaxResults(30)*/
		.getResultList();
    }

	public List<Atuacao> listaPorIdAtuacao(Integer i){
		
	return manager.createQuery("select a from Atuacao a where idAtuacao="+i,Atuacao.class)
			.getResultList();
	}

	public void atualizaTags(Atuacao atuacao){
		Atuacao a = manager.find(Atuacao.class, atuacao.getIdAtuacao());
		a.setAtuacao(atuacao.getAtuacao());
		manager.merge(a);
	}
	
	
}
