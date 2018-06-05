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
	
	
	public void salvaNovoImposto(Integer idAnalitico, String valor,String descricao, int chkFixoOutrosImpostos) {
		
		boolean chk = true;
		
		if(chkFixoOutrosImpostos == 0) { chk = false; }
		
		FinancAnalitico analitico = individualDAO.carregaAnaliticoIndividual(idAnalitico);
		try {
			FinancImpostos outrosImpostos = new FinancImpostos();
			outrosImpostos.setDescricao(descricao);
			
			if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
				outrosImpostos.setValor(new BigDecimal("0.00"));
			}else{
				outrosImpostos.setValor(new BigDecimal(util.formataValores(valor)));
			}
			outrosImpostos.setAnalitico(analitico);
			outrosImpostos.setData(Calendar.getInstance());
			outrosImpostos.setFixo(chk);
			
			manager.persist(outrosImpostos);
		} catch (Exception e) {
			System.out.println("Erro ao inserir escritorio: "+e);
		}
		
	}
	
	public void editaFixo(int idAnalitico, int idTabela, int chkFixo){
		boolean chk = true;
		if(chkFixo == 0) { chk = false; }
		
		try {
			FinancImpostos outrosImpostos = manager.find(FinancImpostos.class, idTabela);
			outrosImpostos.setFixo(chk);
			manager.merge(outrosImpostos);
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
