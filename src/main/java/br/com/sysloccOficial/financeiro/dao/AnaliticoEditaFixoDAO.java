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

	public void editaFixo(int idAnalitico, int idTabela, int chkFixo, String nomeTabela) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
		boolean chk = true;
		if(chkFixo == 0) { chk = false; }
		
		try {
			
			Object obj3 = Class.forName("br.com.sysloccOficial.financeiro.model.FinancImpostos").newInstance();
			Object obj4 = manager.find(obj3.getClass(), idTabela);
			
			Field fieldFixo = obj4.getClass().getDeclaredField("fixo");
			fieldFixo.setBoolean(obj4, chk);
			
			System.out.println(fieldFixo);
			
			/*fieldFixo.set(boolean.class, chk);
			
			manager.merge(obj4);*/
			
			
		} catch (ClassNotFoundException  e) {
			System.out.println("Erro ao inserir escritorio: "+e);
		}
	}
	
}
