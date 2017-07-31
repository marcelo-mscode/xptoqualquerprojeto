package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;
import br.com.sysloccOficial.financeiro.model.FinancItauEntrada;
import br.com.sysloccOficial.financeiro.model.FinancOutrasDespesas;

@Repository
@Transactional
public class AnaliticoIndividualItauDAO {
	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	@Autowired private AnaliticoIndividualDAO individualDAO;
	
	
	public void salvaNovaEntrada(Integer idAnalitico,String DataPgto,String valor,String descricao,String ndnf) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new java.sql.Date(format.parse(DataPgto).getTime());
		
		FinancAnalitico analitico = individualDAO.carregaAnaliticoIndividual(idAnalitico);
	
		try {
			FinancItauEntrada entradas = new FinancItauEntrada ();
			entradas.setDescricao(descricao);
			
			if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
				entradas.setValor(new BigDecimal("0.00"));
			}else{
				entradas.setValor(new BigDecimal(util.formataValores(valor)));
			}
			entradas.setNfnd(ndnf);
			entradas.setAnalitico(analitico);
			entradas.setData(data);
			manager.persist(entradas);
			
		} catch (Exception e) {
			System.out.println("Erro ao salva dados de entrada Itau: "+e);
		}
		
	}
	
	public List<FinancOutrasDespesas > carregaAnaliticoOutrasDespesas(Integer idAnalitico) {
		try {
			TypedQuery<FinancOutrasDespesas > f = manager.createQuery("select f from FinancOutrasDespesas  f where analitico.idAnalitico="+idAnalitico,FinancOutrasDespesas .class);
			return f.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Integer editaOutrasDespesas(Integer idTabela,String DataPgto,String valor, String tipoCampo) throws ParseException {
		
		FinancOutrasDespesas  despesas = manager.find(FinancOutrasDespesas .class, idTabela);

		if(tipoCampo.equals("data")){
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date data = new java.sql.Date(format.parse(valor).getTime());
				despesas.setData(data);
			} catch (ParseException e) {
				System.out.println(e);
			}
		}
			
		if(tipoCampo.equals("descricao")){
			String valor2 = valor.replace("x1x2x3x", "%");
			despesas.setDescricao(valor2);
		}
		if(tipoCampo.equals("valor")){
			if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
				despesas.setValor(new BigDecimal("0.00"));
			}else{
				despesas.setValor(new BigDecimal(util.formataValores(valor)));
			}
		}
		
		manager.merge(despesas);
		Integer idAnalitico = despesas.getAnalitico().getIdAnalitico();
		manager.close();
		return idAnalitico;
	}

}
