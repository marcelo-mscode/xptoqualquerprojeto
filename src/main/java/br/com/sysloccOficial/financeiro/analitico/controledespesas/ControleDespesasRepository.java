package br.com.sysloccOficial.financeiro.analitico.controledespesas;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


@Repository
public class ControleDespesasRepository {

	
	@PersistenceContext	private EntityManager manager;
	
	
	
}
