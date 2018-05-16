package br.com.sysloccOficial.financeiro.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.financeiro.model.FinancImpostos;


@Repository
@Transactional
public class AnaliticoEditaFixoDAO {
	
	@PersistenceContext	private EntityManager manager;

	public void editaFixo(int idAnalitico, int idTabela, int chkFixo, String nomeTabela){
		boolean chk = true;
		if(chkFixo == 0) { chk = false; }
		
		try {
			
			Object tabela = Class.forName("nomeTabela").newInstance();
			
			Object outrosImpostos = manager.find(tabela.getClass(), idTabela);
			
			
			
			outrosImpostos.setFixo(chk);
			manager.merge(outrosImpostos);
		} catch (Exception e) {
			System.out.println("Erro ao inserir escritorio: "+e);
		}
	}
	
}
