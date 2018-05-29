package br.com.sysloccOficial.financeiro.dao;

import java.lang.reflect.Field;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class AnaliticoEditaFixoDAO {
	
	@PersistenceContext	private EntityManager manager;

	public void editaFixo(int idAnalitico, int idTabela, int chkFixo, String nomeTabela) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
	
		
		boolean chk = true;
		if(chkFixo == 0) { chk = false; }
	
		try {
			
			Object obj3 = Class.forName("br.com.sysloccOficial.financeiro.model."+nomeTabela).newInstance();
			Object obj4 = manager.find(obj3.getClass(), idTabela);
			
			Field f = obj4.getClass().getDeclaredField("fixo");
			f.setAccessible(true);
			f.setBoolean(obj4, chk);
			
			manager.merge(obj4);
			manager.close();
		
		
		} catch (ClassNotFoundException  e) {
			System.out.println("Erro ao editarFixo: "+e);
		}
	}
}
