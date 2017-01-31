package br.com.sysloccOficial.criacao.controllerCriacao.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.model.CriacaoItemHistorico;
import br.com.sysloccOficial.model.CriacaoItemLista;
import br.com.sysloccOficial.model.CriacaoItemPendencia;
import br.com.sysloccOficial.model.CriacaoLista;
import br.com.sysloccOficial.model.Empresa;

@Repository
@Transactional
public class RelatorioDAO {

	@PersistenceContext	private EntityManager manager;
	@Autowired private UtilitariaDatas utilData;
	
	
	/*public List<Object[]> idNomeEmpresas(){
		
		String c1 = "SELECT distinct(idListaProducao) FROM CriacaoLista";
		Query q = manager.createQuery(c1);
		List<Integer> cc1 = q.getResultList();
		
		String c2 = "SELECT idJob from Lista where idlista in ("+cc1+")";
		
		
		
		String consulta = "SELECT idEmpresa,empresa from Empresa where idEmpresa in ("+
						  "SELECT idEmpresa from Job where idJob in ( "+
						  "SELECT idJob from Lista where idlista in ("+
						  "SELECT distinct(idListaProducao) FROM CriacaoLista"+
						  "))) order by empresa;";
		Query q = 	manager.createNativeQuery(consulta);
		return q.getResultList();
	}*/
	
	public List<Object[]> idEmpresas(){
		String consulta = "select idEmpresa, empresa from Empresa e where e.habilitado != 0 and cliente!= 0 order by empresa";
		Query query = manager.createQuery(consulta);	
		List<Object[]> lista = query.getResultList();	
	return lista;
	}
	
	
	public List<String> dataCriacaoLista(){
		String consulta = "select distinct(SUBSTRING(dataCriacao,1,4)) from CriacaoLista order by dataCriacao desc";
		Query q = manager.createQuery(consulta);
/*		Query q = manager.createNativeQuery(consulta);
*/		return q.getResultList();
	}
	
	public List<String> mesCriacao(String termo){
		
		// termo = dataCriacao ou dataFechamento
		
		String consulta = "select distinct(SUBSTRING("+termo+",6,2)) from CriacaoLista where dataFechamento is not null";
		Query q = manager.createQuery(consulta);
		return q.getResultList();
		
	}
	
	public List<CriacaoLista> exibeCriacaoListasFechadasPorEmpresa(Integer idEmpresa, String data){
		String Consulta = "select c from CriacaoLista c  left join c.listaProducao where"
						+ " c.listaProducao.idJob.empresa.idEmpresa=:idEmpresa"
						+ " and c.dataCriacao like '%"+data+"%'"
						+ " and criacaoStatus = 2 order by c.dataFechamento desc";
		
		Query query = manager.createQuery(Consulta,CriacaoLista.class)
				.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}
	
	public List<CriacaoLista> ultimasListasFechadas() throws ParseException{
		try {
			String consulta = "from CriacaoLista where criacaoStatus = 2 order by dataFechamento desc";
			Query q = manager.createQuery(consulta).setMaxResults(20);
			List<CriacaoLista> qs = q.getResultList();
			return qs;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Integer> idsListasFechadas() throws ParseException{
		try {
			String consulta = "Select idCriacaoLista from CriacaoLista where criacaoStatus = 2 order by dataFechamento desc";
			TypedQuery<Integer> q = manager.createQuery(consulta,Integer.class);
			List<Integer> qs = q.getResultList();
			return qs;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<CriacaoItemLista> itensListaProIdCriacao(Integer idCriacao){
		String consulta ="FROM CriacaoItemLista c where idCriacaoLista = "+idCriacao+" order by responsavelItem desc";		
		Query q = manager.createQuery(consulta);
		return q.getResultList();
	}
	
	public List<CriacaoItemHistorico> verificaHistoricoItemCompleto(Integer idCriacaoItem){
		String consulta =  "select h from CriacaoItemHistorico h where idCriacaoItem="+idCriacaoItem+" order by idItemHistorico desc";
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}

	public List<CriacaoItemHistorico> verificaHistoricoItemCompletoPorusuario(Integer idCriacaoItem, Integer idUsuario){
		String consulta =  "select h from CriacaoItemHistorico h where idCriacaoItem="+idCriacaoItem+""
						 + " and iniciadoPor = "+idUsuario+" order by idItemHistorico desc";
		
		Query query = manager.createQuery(consulta);

		List<CriacaoItemHistorico> lista1 =  query.getResultList();
		
		if(lista1.isEmpty()){
			String consulta2 =  "select h from CriacaoItemHistorico h where idCriacaoItem="+idCriacaoItem+""
					          + " and responsavelItem = "+idUsuario+" order by idItemHistorico desc";
			Query query2 = manager.createQuery(consulta2);
			List<CriacaoItemHistorico> lista2 = query2.getResultList();
			return lista2;
	
		}else{
			return lista1;
		}
	}
    
    public List<CriacaoItemPendencia> verifHistItemPendencPorUsuario(Integer idCriacaoItem, Integer idUsuario){
		String consulta =  "select h from CriacaoItemPendencia h where idCriacaoItem="+idCriacaoItem+" and responsavelItem = "+idUsuario;
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}

    
    
    
    public List<CriacaoItemPendencia> verifHistItemPendenc(Integer idCriacaoItem){
    	String consulta =  "select h from CriacaoItemPendencia h where idCriacaoItem="+idCriacaoItem;
    	Query query = manager.createQuery(consulta);
    	return query.getResultList();
    }
	
    public List<Integer> pegaIdCriacaoItemListaPorLista(Integer idCriacaoLista){
    	String consulta = "SELECT idCriacaoItemLista FROM CriacaoItemLista where idCriacaoLista ="+idCriacaoLista;
    	TypedQuery<Integer> q = manager.createQuery(consulta, Integer.class);
    	return q.getResultList();
    }
	
    public List<Integer> idsItensPorUsuario (String idusuario,String mes, String ano){
    	try {
    		String c = "SELECT idCriacaoItemLista FROM CriacaoItemLista where idCriacaoLista in ("+
    				   "SELECT idCriacaoLista from criacaolista where CriacaoStatus in(2,3) and idCriacaoLista in ("+
    				   "SELECT distinct(idCriacaoLista) FROM CriacaoItemLista where responsavelItem ="+idusuario+
    				   ") and dataFechamento like '%"+ano+"-"+mes+"%') and responsavelItem ="+idusuario;
    		
    		Query q = manager.createNativeQuery(c);
    		List<Integer> ids =  q.getResultList();
    		return ids;
		} catch (Exception e) {
			return null;
		}
    }
    
    public List<Integer> idsListaPorUsuario (String idusuario,String mes, String ano){
    	
    	
    	try {
    		String c = "SELECT idCriacaoLista from criacaolista where CriacaoStatus = 2 and idCriacaoLista in ("+
    				   "SELECT distinct(idCriacaoLista) FROM CriacaoItemLista where responsavelItem ="+idusuario+
    				   ") and dataFechamento like '%"+ano+"-"+mes+"%'";
    		
    		Query q = manager.createNativeQuery(c);
    		List<Integer> ids =  q.getResultList();
    		
    		if(ids.isEmpty()){
    			String consulta2 = "SELECT idCriacaoLista from criacaolista where CriacaoStatus = 2 and idCriacaoLista in ("+
     				   "SELECT distinct(idCriacaoLista) FROM CriacaoItemLista where liderCriacao ="+idusuario+
     				   ") and dataFechamento like '%"+ano+"-"+mes+"%'";
    			
    			Query q2 = manager.createNativeQuery(consulta2);
        		List<Integer> ids2 =  q2.getResultList();
        		
        		if(ids2.isEmpty()){
        			String consulta3 = "SELECT idCriacaoLista from criacaolista where CriacaoStatus = 2 and parResponsavel ="+idusuario+
          				   ") and dataFechamento like '%"+ano+"-"+mes+"%'";
         			
         			Query q3 = manager.createNativeQuery(consulta3);
             		List<Integer> ids3 =  q3.getResultList();
             		
             		if(ids3.isEmpty()){
             			String consulta4 = "SELECT idCriacaoLista from criacaolista where CriacaoStatus = 2 and responsavel ="+idusuario+
               				   ") and dataFechamento like '%"+ano+"-"+mes+"%'";
              			
              			Query q4 = manager.createNativeQuery(consulta4);
                  		List<Integer> ids4 =  q4.getResultList();
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
			return null;
		}
    }
    
    
    
    
    public List<CriacaoLista> CriacaoListasPorIds(List<Integer> idsListasCriacao){
    	String consulta = "FROM CriacaoLista where idCriacaoLista in (:id) order by dataFechamento desc";
    	TypedQuery<CriacaoLista> lista = manager.createQuery(consulta, CriacaoLista.class);
    	lista.setParameter("id", idsListasCriacao);
    	List<CriacaoLista> listas = lista.getResultList();
    	return listas;
    }
    
    
    public CriacaoLista lista(Integer idLista){
    	CriacaoLista lista = manager.find(CriacaoLista.class, idLista);
    	return lista;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
