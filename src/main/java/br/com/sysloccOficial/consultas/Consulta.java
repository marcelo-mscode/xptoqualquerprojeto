package br.com.sysloccOficial.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;



public class Consulta {

	@PersistenceContext private EntityManager manager;
	ModelAndView MV = new ModelAndView();
	
	/**
	 * 
	 * @param termo
	 * @return
	 */
	public List<Object[]> ListaDeObjetos(String termo){
		Query query = manager.createQuery(termo);
		List<Object[]> retorno = query.getResultList();
	return retorno;
	}
	
	public Object[] Objeto(String termo){
		Query query = manager.createQuery(termo);
		Object[] retorno = (Object[]) query.getSingleResult();
	return retorno;
	}
	
	public ModelAndView setaParametroHerdado(String viewName,String objetoParaJSP,String termo,String consultaGeral){
		MV.setViewName(viewName);
		MV.addObject(objetoParaJSP, ListaDeObjetos(consultaGeral+termo));
		return MV;
	}
	
	public void tempo() throws InterruptedException{
		new Thread();Thread.sleep(50);  
	}
	
}
