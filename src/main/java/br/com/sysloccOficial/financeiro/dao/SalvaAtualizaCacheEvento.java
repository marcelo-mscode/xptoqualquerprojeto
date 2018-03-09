package br.com.sysloccOficial.financeiro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.relatorioeventos.CacheDoEventoApoio;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.RelatorioEventos;


@Repository
@Transactional
public class SalvaAtualizaCacheEvento {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired CacheDoEventoApoio cacheEvento;
	@Autowired UtilitariaDatas utildatas;
	
	
	public void salvaAtualizaCacheDoEvento(RelatorioEventos relatorioEvento){
		
		List<CachePadrao> cachePadrao =  cacheEvento.listaRelatorioCaches(relatorioEvento.getIdLista());
		
	}
	
	

}
