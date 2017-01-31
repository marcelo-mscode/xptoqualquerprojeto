package br.com.sysloccOficial.criacao.controllerCriacao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.UtilitariaDatas;


@Repository
@Transactional
public class RelatorioUsuarioDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private UtilitariaDatas utilData;

	public List<Integer> idsItensPorUsuario (List<Integer> idsListasPeriodos,String idUsuario){
		try {
			String c = "SELECT idCriacaoItemLista FROM CriacaoItemLista where idCriacaoLista in (:id)";
			
			Query q = manager.createQuery(c);
			q.setParameter("id", idsListasPeriodos);
			
			List<Integer> ids =  q.getResultList();
			return ids;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<Integer> idsItensPorLista (Integer idsListasPeriodos){
		try {
			String c = "SELECT idCriacaoItemLista FROM CriacaoItemLista where idCriacaoLista in (:id)";
			
			Query q = manager.createQuery(c);
			q.setParameter("id", idsListasPeriodos);
			
			List<Integer> ids =  q.getResultList();
			return ids;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
// --------------------------------------------------------------------------------------------------------------------------- //

	public List<Integer> idsListaPorUsuario (String idusuario,String mes, String ano){

		try {
	    		String c = "SELECT idCriacaoLista from criacaolista where CriacaoStatus = 2 and idCriacaoLista in ("+
	    				   "SELECT distinct(idCriacaoLista) FROM CriacaoItemLista where responsavelItem ="+idusuario+
	    				   ") and dataFechamento like '%"+ano+"-"+mes+"%'";
	    		
	    		Query q = manager.createNativeQuery(c);
	    		List<Integer> ids =  q.getResultList();
	    		
	    		if(ids.isEmpty()){
	    			List<Integer> ids2 = porLiderNoItem(idusuario, mes, ano);
	        		
	        		if(ids2.isEmpty()){
	        			List<Integer> ids3 = porParResponsavelNaLista(idusuario, mes, ano);
	             		
	             		if(ids3.isEmpty() || ids3 == null){
	             			List<Integer> ids4 = porResponsavelNaLista(idusuario, mes, ano);
	                  		if(ids4.isEmpty()){
	                  			//return null;
	                  		}
	             			return ids4;
	             		}
	             		return ids3;
	        		}else{
	        			return ids2;
	        		}
	    		}
	    		else{
	    			return ids;
	    		}
	    		
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
	    }
	
	private List<Integer> porResponsavelNaLista(String idusuario, String mes, String ano) {
		String consulta4 = "SELECT idCriacaoLista from CriacaoLista where CriacaoStatus = 2 and responsavel ="+idusuario+
			   " and dataFechamento like '%"+ano+"-"+mes+"%'";
		try {
			Query q4 = manager.createNativeQuery(consulta4);
			List<Integer> ids4 =  q4.getResultList();
			return ids4;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	private List<Integer> porParResponsavelNaLista(String idusuario, String mes, String ano) {
		String consulta3 = "SELECT idCriacaoLista from CriacaoLista where CriacaoStatus = 2 and parResponsavel ="+idusuario+
				           " and dataFechamento like '%"+ano+"-"+mes+"%'";
		try {
			Query q3 = manager.createNativeQuery(consulta3);
			List<Integer> ids3 =  q3.getResultList();
			return ids3;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	private List<Integer> porLiderNoItem(String idusuario, String mes, String ano) {
		String consulta2 = "SELECT idCriacaoLista from criacaolista where CriacaoStatus = 2 and idCriacaoLista in ("+
			   "SELECT distinct(idCriacaoLista) FROM CriacaoItemLista where liderCriacao ="+idusuario+
			   ") and dataFechamento like '%"+ano+"-"+mes+"%'";
		try {
			Query q2 = manager.createNativeQuery(consulta2);
			List<Integer> ids2 =  q2.getResultList();
			return ids2;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	
	
	
	
	

}
