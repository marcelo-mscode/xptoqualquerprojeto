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
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.model.BancosAnalitico;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;
import br.com.sysloccOficial.financeiro.model.FinancOutrasDespesas;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancos;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancosSaidas;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancosTarifas;
import br.com.sysloccOficial.financeiro.movbancos.FinanceiroMovBancos;

@Repository
@Transactional
public class AnaliticoIndividualMovimentoFinanceiro{
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	@Autowired private UtilitariaDatas utilDatas;
	@Autowired private AnaliticoIndividualDAO individualDAO;
	
	
	public void novaEntrada(Integer idAnalitico,String DataPgto,String valor,String descricao,String ndnf,Integer idBanco) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new java.sql.Date(format.parse(DataPgto).getTime());
		
		FinancAnalitico analitico = individualDAO.carregaAnaliticoIndividual(idAnalitico);
		BancosAnalitico banco = manager.getReference(BancosAnalitico.class, idBanco);
		try {
			MovimentacaoBancos entradas = new MovimentacaoBancos();
			entradas.setData(data);
			entradas.setDescricao(descricao);
			entradas.setNdnf(ndnf);
			if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
				entradas.setValor(new BigDecimal("0.00"));
			}else{
				entradas.setValor(new BigDecimal(util.formataValores(valor)));
			}
			entradas.setAnalitico(analitico);
			entradas.setBanco(banco);

			manager.persist(entradas);
			
		} catch (Exception e) {
			System.out.println("Erro ao salvar dados de entrada Itau: "+e);
		}
		
	}
	
	public List<MovimentacaoBancos> carregaMovimentaBancos(Integer idAnalitico, Integer idBanco) {
		try {
			TypedQuery<MovimentacaoBancos > f = manager.createQuery("select m from MovimentacaoBancos  m where analitico.idAnalitico="+idAnalitico+" and m.banco.idBanco = "+idBanco,MovimentacaoBancos .class);
			return f.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao carregar a lista de Movimento Financeiro: "+e);
			return null;
		}
	}

	public List<MovimentacaoBancosSaidas> carregaMovimentaBancosSaidas(Integer idAnalitico, Integer idBanco) {
		try {
			TypedQuery<MovimentacaoBancosSaidas> f = manager.createQuery("select m from MovimentacaoBancosSaidas  m where analitico.idAnalitico="+idAnalitico+" and m.banco.idBanco = "+idBanco,MovimentacaoBancosSaidas .class);
			return f.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao carregar a lista de Movimento Financeiro Saidas: "+e);
			return null;
		}
	}
	
	public Integer editaNovaEntrada(Integer idTabela,String valor, String tipoCampo) throws ParseException {
		
		MovimentacaoBancos  despesas = manager.find(MovimentacaoBancos.class, idTabela);

		if(tipoCampo.equals("ndnf")){
			try {
				despesas.setNdnf(valor);
			} catch (Exception e) {
				System.out.println("Erro ao editar uma ndnf: "+e);
			}
		}

		if(tipoCampo.equals("descricao")){
			String valor2 = valor.replace("x1x2x3x", "%");
			despesas.setDescricao(valor2);
		}

		if(tipoCampo.equals("data")){
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date data = new java.sql.Date(format.parse(valor).getTime());
				despesas.setData(data);
			} catch (ParseException e) {
				System.out.println(e);
			}
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

	public void novaSaida(Integer idAnalitico, String dataPgto,String valor, String descricao, Integer idBanco) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new java.sql.Date(format.parse(dataPgto).getTime());
		
		FinancAnalitico analitico = individualDAO.carregaAnaliticoIndividual(idAnalitico);
		BancosAnalitico banco = manager.getReference(BancosAnalitico.class, idBanco);
		try {
			MovimentacaoBancosSaidas entradas = new MovimentacaoBancosSaidas();
			entradas.setData(data);
			entradas.setDescricao(descricao);
			if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
				entradas.setValor(new BigDecimal("0.00"));
			}else{
				entradas.setValor(new BigDecimal(util.formataValores(valor)));
			}
			entradas.setAnalitico(analitico);
			entradas.setBanco(banco);

			manager.persist(entradas);
			
		} catch (Exception e) {
			System.out.println("Erro ao salvar dados de Saida: "+e);
		}
	}
	
	public Integer editaNovaSaida(Integer idTabela,String valor, String tipoCampo) throws ParseException {
		
		MovimentacaoBancosSaidas  despesas = manager.find(MovimentacaoBancosSaidas.class, idTabela);

		if(tipoCampo.equals("descricao")){
			String valor2 = valor.replace("x1x2x3x", "%");
			despesas.setDescricao(valor2);
		}

		if(tipoCampo.equals("data")){
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date data = new java.sql.Date(format.parse(valor).getTime());
				despesas.setData(data);
			} catch (ParseException e) {
				System.out.println(e);
			}
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
	
	public List<MovimentacaoBancos> carregaAnaliticoEntradas(Integer idAnalitico,Integer idBanco) {
		try {
			TypedQuery<MovimentacaoBancos> f = manager.createQuery("select f from MovimentacaoBancos f join fetch f.analitico where idAnalitico="+idAnalitico+" and f.banco.idBanco = "+idBanco,MovimentacaoBancos.class);
			return f.getResultList();
		} catch (Exception e) {
			System.out.println("Não foi possível carregar as listagens de entradas do Itau: "+e);
			return null;
		}
	}
	
	public List<MovimentacaoBancosSaidas> carregaAnaliticoSaidas(Integer idAnalitico,Integer idBanco) {
		try {
			TypedQuery<MovimentacaoBancosSaidas> f = manager.createQuery("select f from MovimentacaoBancosSaidas f join fetch f.analitico where idAnalitico="+idAnalitico+" and f.banco.idBanco = "+idBanco,MovimentacaoBancosSaidas.class);
			return f.getResultList();
		} catch (Exception e) {
			System.out.println("Não foi possível carregar as listagens de saidas do Itau: "+e);
			return null;
		}
	}

	public void novaTarifa(Integer idAnalitico, String dataPgto, String valor,String descricao, Integer idBanco) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date data = new java.sql.Date(format.parse(dataPgto).getTime());
		
		FinancAnalitico analitico = individualDAO.carregaAnaliticoIndividual(idAnalitico);
		BancosAnalitico banco = manager.getReference(BancosAnalitico.class, idBanco);
		try {
			MovimentacaoBancosTarifas entradas = new MovimentacaoBancosTarifas();
			entradas.setData(data);
			entradas.setDescricao(descricao);
			if(valor.equals(null) || valor.equals("")|| valor.equals(" ")){
				entradas.setValor(new BigDecimal("0.00"));
			}else{
				entradas.setValor(new BigDecimal(util.formataValores(valor)));
			}
			entradas.setAnalitico(analitico);
			entradas.setBanco(banco);

			manager.persist(entradas);
			
		} catch (Exception e) {
			System.out.println("Erro ao salvar dados de Saida: "+e);
		}
		
		
		
		
	}
	/*
	 * 
	@Override
	public Integer editaNovaTarifa(Integer idTabela, String valor,
			String tipoCampo) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	
	
}
