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
import br.com.sysloccOficial.financeiro.model.FinancCategTelef;
import br.com.sysloccOficial.financeiro.model.FinancTelefone;



@Repository
@Transactional
public class AnaliticoIndividualTelefoneDAO {

	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	@Autowired private AnaliticoIndividualDAO individualDAO;
	
	
	public FinancCategTelef categoriaTelefone(Integer idCateg) {
		FinancCategTelef analitico = manager.find(FinancCategTelef.class, idCateg);
		return analitico;
	}
	
	public List<FinancTelefone> carregaAnaliticoTelefone(Integer idAnalitico) {
		try {
			TypedQuery<FinancTelefone> f = manager.createQuery("select f from FinancTelefone f where analitico.idAnalitico="+idAnalitico,FinancTelefone.class);
			return f.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void salvaNovoTelefone(Integer idAnalitico, String valor,String semCategoria) {
			
			FinancAnalitico analitico = individualDAO.carregaAnaliticoIndividual(idAnalitico);
			FinancCategTelef categoriaTelefone = categoriaTelefone(1);
			try {
				FinancTelefone telefone = new FinancTelefone();
				telefone.setSemCategoria(semCategoria);
				
				if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
					telefone.setValor(new BigDecimal("0.00"));
				}else{
					telefone.setValor(new BigDecimal(util.formataValores(valor)));
				}
				
				telefone.setAnalitico(analitico);
				telefone.setData(Calendar.getInstance());
		//		telefone.setCategoria(categoriaTelefone);
				manager.persist(telefone);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao inserir telefone: "+e);
			}
			
		}
	
		public Integer editaTelefone(Integer idTabela, String valor, String tipoCampo) {
				
				TypedQuery<FinancTelefone> q = manager.createQuery("from FinancTelefone where idFinancTelefone="+idTabela,FinancTelefone.class);
				FinancTelefone telefone = q.getSingleResult();
				
				telefone.setData(Calendar.getInstance());
				
				if(tipoCampo.equals("descricao")){
					String valor2 = valor.replace("x1x2x3x", "%");
					telefone.setSemCategoria(valor2);
				}
				if(tipoCampo.equals("valor")){
					if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
						telefone.setValor(new BigDecimal("0.00"));
					}else{
						telefone.setValor(new BigDecimal(util.formataValores(valor)));
					}
				} 
				manager.merge(telefone);
				Integer idAnalitico = telefone.getAnalitico().getIdAnalitico();
				manager.close();
				return idAnalitico;
			}
	
	
	
	
}
