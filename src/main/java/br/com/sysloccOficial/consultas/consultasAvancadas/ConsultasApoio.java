package br.com.sysloccOficial.consultas.consultasAvancadas;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.UtilitariaDatas;

@Repository
public class ConsultasApoio implements Consultas{
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private UtilitariaDatas utilDatas;
	
	/**
	 * Método pirocudo 1
	 * @return SingleResult
	 */
	@Override
	public Object ObjetoCompleto(String consulta) {  // Método pirocudo 
		try {
			Query query = manager.createQuery(consulta);
			Object retorno = query.getSingleResult();
			return retorno;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Método pirocudo 2
	 * @return ResultList()
	 */
	
	@Override
	public Object ObjetoCompletoRetornaLista(String termo) {
		try {
			Query query = manager.createQuery(termo);
			Object retorno = query.getResultList();
			return retorno;
		} catch (Exception e) {
			return "";
		}
	}
	
	
	/**
	 * Método que retorna apenas o último elemento de uma lista no estilo Limit 1 de SQL 
	 */
	public Object ObjetoCompletoLimit(String consulta) {  // Método com apenas um resultado 
		try {
			Query query = manager.createQuery(consulta).setMaxResults(1);
			Object retorno = query.getSingleResult();
			return retorno;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	

	@Override
	public String chamaQuery(String consulta) {
		try {
			Query query = manager.createQuery(consulta);
			String retorno = (String) query.getSingleResult();
			return retorno;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Integer chamaQueryInteger(String consulta) {
		try {
			Query query = manager.createQuery(consulta);
			Integer retorno = (Integer) query.getSingleResult();
			return retorno;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Date chamaQueryDate(String consulta) {
		try {
			Query query = manager.createQuery(consulta);
			Date retorno = (Date) query.getSingleResult();
			return retorno;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Calendar chamaQueryCalendar(String consulta) {
		try {			
			Query query = manager.createQuery(consulta);
			return (Calendar) query.getSingleResult();
			
		} catch (Exception e) {
			return null;
		}
	}

	
	@Override
	public List<Object[]> ListaDeObjetos(String termo) {
		return null;
	}

	@Override
	public Object[] Objeto(String termo) {
		return null;
	}
	
	

}
