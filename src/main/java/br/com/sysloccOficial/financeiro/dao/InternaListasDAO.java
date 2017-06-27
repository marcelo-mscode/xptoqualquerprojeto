package br.com.sysloccOficial.financeiro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Repository
public class InternaListasDAO {
	
	@PersistenceContext	private EntityManager manager;

	public List<Lista> listasInternasComItensFechados() {
		try {
		TypedQuery<Integer> q = manager.createQuery("select distinct(p.lista.idLista) from ProducaoP p",Integer.class);
		List<Integer> ids = q.getResultList();
		
		String consulta = "select l from Lista l where idLista in ("+ids+")";
		String c2 = consulta.replace("[", "").replace("]", "");
		
	
			TypedQuery<Lista> qs = manager.createQuery(c2,Lista.class);
			List<Lista> listasInternas = qs.getResultList();
			return listasInternas;
		} catch (Exception e) {
			System.out.println("Erro ao gerar Listas Internas: "+e);
			return null;
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
