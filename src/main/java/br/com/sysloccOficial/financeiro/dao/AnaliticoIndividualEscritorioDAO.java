package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;
import br.com.sysloccOficial.financeiro.model.FinancEscritorio;



@Repository
@Transactional
public class AnaliticoIndividualEscritorioDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	@Autowired private AnaliticoIndividualDAO individualDAO;
	
	
	public void salvaNovoEscritorio(Integer idAnalitico, String valor,String descricao, int chkFixoOutrosImpostos) {
		
		boolean chk = true;
		
		if(chkFixoOutrosImpostos == 0) { chk = false; }
		
		
		FinancAnalitico analitico = individualDAO.carregaAnaliticoIndividual(idAnalitico);
		try {
			FinancEscritorio escritorio = new FinancEscritorio();
			escritorio.setDescricao(descricao);
			
			if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
				escritorio.setValor(new BigDecimal("0.00"));
			}else{
				escritorio.setValor(new BigDecimal(util.formataValores(valor)));
			}
			escritorio.setAnalitico(analitico);
			escritorio.setData(Calendar.getInstance());
			escritorio.setFixo(chk);
			manager.persist(escritorio);
		} catch (Exception e) {
			System.out.println("Erro ao inserir escritorio: "+e);
		}
		
	}
	
	public List<FinancEscritorio> carregaAnaliticoEscritorio(Integer idAnalitico) {
		try {
			TypedQuery<FinancEscritorio> f = manager.createQuery("select f from FinancEscritorio f where analitico.idAnalitico="+idAnalitico,FinancEscritorio.class);
			return f.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Integer editaEscritorio(Integer idTabela, String valor, String tipoCampo) {
			
			FinancEscritorio escritorio = manager.find(FinancEscritorio.class, idTabela);
			
			// escritorio.setData(Calendar.getInstance());
			
			if(tipoCampo.equals("descricao")){
				String valor2 = valor.replace("x1x2x3x", "%");
				escritorio.setDescricao(valor2);
			}
			if(tipoCampo.equals("valor")){
				if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
					escritorio.setValor(new BigDecimal("0.00"));
				}else{
					escritorio.setValor(new BigDecimal(util.formataValores(valor)));
				}
			}
			manager.merge(escritorio);
			Integer idAnalitico = escritorio.getAnalitico().getIdAnalitico();
			manager.close();
			return idAnalitico;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
