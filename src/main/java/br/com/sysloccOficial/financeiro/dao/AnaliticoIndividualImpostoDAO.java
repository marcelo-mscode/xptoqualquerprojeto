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
import br.com.sysloccOficial.financeiro.model.FinancImpostos;

@Repository
@Transactional
public class AnaliticoIndividualImpostoDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	@Autowired private AnaliticoIndividualDAO individualDAO;
	
	
	public void salvaNovoImposto(Integer idAnalitico, String valor,String descricao) {
		
		FinancAnalitico analitico = individualDAO.carregaAnaliticoIndividual(idAnalitico);
		try {
			FinancImpostos escritorio = new FinancImpostos();
			escritorio.setDescricao(descricao);
			
			if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
				escritorio.setValor(new BigDecimal("0.00"));
			}else{
				escritorio.setValor(new BigDecimal(util.formataValores(valor)));
			}
			escritorio.setAnalitico(analitico);
			escritorio.setData(Calendar.getInstance());
			manager.persist(escritorio);
		} catch (Exception e) {
			System.out.println("Erro ao inserir escritorio: "+e);
		}
		
	}
	
	public List<FinancImpostos> carregaAnaliticoImposto(Integer idAnalitico) {
		try {
			TypedQuery<FinancImpostos> f = manager.createQuery("select f from FinancImpostos f where analitico.idAnalitico="+idAnalitico,FinancImpostos.class);
			return f.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Integer editaImposto(Integer idTabela, String valor, String tipoCampo) {
			
			FinancImpostos escritorio = manager.find(FinancImpostos.class, idTabela);
			escritorio.setData(Calendar.getInstance());
			
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
