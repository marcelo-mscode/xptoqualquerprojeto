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
import br.com.sysloccOficial.financeiro.model.FinancFolhaPgto;

@Repository
@Transactional
public class AnaliticoIndividualFolhaDAO {

	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	@Autowired private AnaliticoIndividualDAO individualDAO;
	
	
	public void salvaNovoFolha(Integer idAnalitico, String valor,String descricao, int chkFixo) {
		
		boolean chk = true;
		if(chkFixo == 0) { chk = false; }
		
		FinancAnalitico analitico = individualDAO.carregaAnaliticoIndividual(idAnalitico);
		try {
			FinancFolhaPgto folhaPgto = new FinancFolhaPgto();
			folhaPgto.setDescricao(descricao);
			
			if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
				folhaPgto.setValor(new BigDecimal("0.00"));
			}else{
				folhaPgto.setValor(new BigDecimal(util.formataValores(valor)));
			}
			
			folhaPgto.setAnalitico(analitico);
			folhaPgto.setData(Calendar.getInstance());
			folhaPgto.setFixo(chk);
			manager.persist(folhaPgto);
			
		} catch (Exception e) {
			System.out.println("Erro ao inserir Folha PGTO: "+e);
		}
		
	}
	
	public List<FinancFolhaPgto> carregaAnaliticoFolha(Integer idAnalitico) {
		try {
			TypedQuery<FinancFolhaPgto> f = manager.createQuery("select f from FinancFolhaPgto f where analitico.idAnalitico="+idAnalitico,FinancFolhaPgto.class);
			return f.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Integer editaFolha(Integer idTabela, String valor, String tipoCampo) {
			
		FinancFolhaPgto escritorio = manager.find(FinancFolhaPgto.class, idTabela);
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
