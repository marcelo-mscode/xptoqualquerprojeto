package br.com.sysloccOficial.financeiro.dao;

import java.lang.reflect.Field;

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
			
			Object obj = Class.forName("nomeTabela").newInstance();
			
			Class tabela = obj.getClass();
			
			Class outrosImpostos = manager.find(tabela.getClass(), idTabela);
			
			
			 for (Field field : outrosImpostos.getDeclaredFields())
		        {

		            String campo = field.getName();
		            if(campo == "fixo"){
		            	System.out.println("Esse é o Campo Fixo");
		            	Class tipo = field.getType();
		            	field.set(tipo, chk);
		            	manager.merge(outrosImpostos);
		            }
		        }
		} catch (Exception e) {
			System.out.println("Erro ao inserir escritorio: "+e);
		}
	}
	
}
