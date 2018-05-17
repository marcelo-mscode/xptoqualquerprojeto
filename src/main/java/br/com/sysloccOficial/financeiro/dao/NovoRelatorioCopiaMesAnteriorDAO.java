package br.com.sysloccOficial.financeiro.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.UtilitariaDatas;



@Repository
@Transactional
public class NovoRelatorioCopiaMesAnteriorDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private UtilitariaDatas utilDatas;
	
	public void copiaOutrosImpostos(int idAnalitico){
		
		System.out.println("Copiando OutrosImpostos no DAO");
		System.out.println();
	}
	
	
	
	
}
