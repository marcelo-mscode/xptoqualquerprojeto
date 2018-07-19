package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;
import br.com.sysloccOficial.financeiro.model.FinancDespesas;
import br.com.sysloccOficial.financeiro.model.FinancEscritorio;
import br.com.sysloccOficial.financeiro.model.FinancFolhaPgto;
import br.com.sysloccOficial.financeiro.model.FinancImpostos;
import br.com.sysloccOficial.financeiro.model.FinancOutrasDespesas;
import br.com.sysloccOficial.financeiro.model.FinancTelefone;
import br.com.sysloccOficial.model.RelatorioEventos;
import br.com.sysloccOficial.model.VideosYt;


@Repository
@Transactional
public class AnaliticoIndividualDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;

	public FinancAnalitico carregaAnaliticoIndividual(Integer idAnalitico) {
		FinancAnalitico analitico = manager.find(FinancAnalitico.class, idAnalitico);
		return analitico;
	}
	
	public FinancAnalitico carregaAnaliticoIndividual2(Integer idAnalitico) {
		try {
			TypedQuery<FinancAnalitico> f = manager.createQuery("select f from FinancAnalitico f join fetch f.telefone where idAnalitico="+idAnalitico,FinancAnalitico.class);
			return f.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<FinancEscritorio> carregaAnaliticoIndividualEscritorio(Integer idAnalitico) {
		try {
			TypedQuery<FinancEscritorio> f = manager.createQuery("select f from FinancEscritorio f join fetch f.analitico where idAnalitico="+idAnalitico,FinancEscritorio.class);
			return f.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<FinancTelefone> carregaAnaliticoIndividualTelefones(Integer idAnalitico) {
		try {
			TypedQuery<FinancTelefone> f = manager.createQuery("select f from FinancTelefone f join fetch f.analitico where idAnalitico="+idAnalitico,FinancTelefone.class);
			return f.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<FinancFolhaPgto> carregaAnaliticoIndividualFolha(Integer idAnalitico) {
		try {
			TypedQuery<FinancFolhaPgto> f = manager.createQuery("select f from FinancFolhaPgto f join fetch f.analitico where idAnalitico="+idAnalitico,FinancFolhaPgto.class);
			return f.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<FinancDespesas> carregaAnaliticoIndividualDespesas(Integer idAnalitico) {
		try {
			TypedQuery<FinancDespesas> f = manager.createQuery("select f from FinancDespesas f join fetch f.analitico where idAnalitico="+idAnalitico,FinancDespesas.class);
			return f.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<FinancOutrasDespesas> carregaAnaliticoIndividualOutrasDespesas(Integer idAnalitico) {
		try {
			TypedQuery<FinancOutrasDespesas> f = manager.createQuery("select f from FinancOutrasDespesas f join fetch f.analitico where idAnalitico="+idAnalitico,FinancOutrasDespesas.class);
			return f.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<FinancImpostos> carregaAnaliticoIndividualFinancImpostos(Integer idAnalitico) {
		try {
			TypedQuery<FinancImpostos> f = manager.createQuery("select f from FinancImpostos f join fetch f.analitico where idAnalitico="+idAnalitico,FinancImpostos.class);
			return f.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	
	public BigDecimal somaValorTelefonePorMesAno(String mes,String ano){
		
		BigDecimal zero = new BigDecimal("0.00");
		
		try {
			String consulta =  "select idAnalitico from FinancAnalitico where anoA ='"+ano+"' and mesA='"+mes+"'";
			TypedQuery<Integer> q = manager.createQuery(consulta,Integer.class);
			Integer idAnalitico = q.getSingleResult();
			
			String consultaTelefone = "select sum(valor) from FinancTelefone where analitico.idAnalitico="+idAnalitico;
			TypedQuery<BigDecimal> s = manager.createQuery(consultaTelefone, BigDecimal.class);
			BigDecimal valorTelefone = s.getSingleResult();
		
			return valorTelefone;
			
		} catch (Exception e) {
			System.out.println("Não encontrou valores de telefone cadastrados para o mes de "+mes+" de "+ano);
			return zero;
		}
		
	}
	
	public void excluiItemAnalitco(int idAnalitico, int idTabela, String tabela) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		try {
			Object obj = Class.forName("br.com.sysloccOficial.financeiro.model."+tabela).newInstance();
			Object obj2 = manager.find(obj.getClass(), idTabela);
			manager.remove(obj2);
		} catch (Exception e) {
			System.out.println("Houve um erro ao excluir um item da tabela: "+tabela+"\nErro: "+e);
		}
	}
	
	
	public Integer quantEventosMes(int anoEvento, int mesReferencia){
		try {
			String consulta = "select count(idRelatorioEvento) from RelatorioEventos where anoEvento ="+anoEvento+" and mesReferencia ="+mesReferencia;
			TypedQuery<Long> cons = manager.createQuery(consulta, Long.class);
			long quant = cons.getSingleResult();
			int valor = (int) quant ;
			return valor;
			
		} catch (Exception e) {
			System.out.println("Erro ao carregar quantidade de eventos em enalitico: "+e);
			return 0;
		}
	}
	
	public List<Tuple> idsRelatorioEventosMes(int anoEvento, int mesReferencia){
		try {
			
			CriteriaBuilder cb = manager.getCriteriaBuilder();
			CriteriaQuery<Tuple> c = cb.createQuery(Tuple.class);
			Root<RelatorioEventos> relatorio = c.from(RelatorioEventos.class);
			
			c.multiselect(relatorio.<Integer>get("idLista").alias("relatorio.idLista"), 
						  relatorio.<Integer>get("idRelatorioEvento").alias("relatorio.idRelatorioEvento") 
			);
			
			Predicate predicate =  cb.and(cb.equal(relatorio.get("anoEvento"), anoEvento),cb.equal(relatorio.get("mesReferencia"), mesReferencia));
			c.where(predicate);
			
			TypedQuery<Tuple> query = manager.createQuery(c);
			List<Tuple> resultado = query.getResultList();
					
			return resultado;
		} catch (Exception e) {
			System.out.println("Erro ao carregar idsRelatorios de eventos em enalitico: "+e);
			return null;
		}
	}
	
	
	public int retornaIdAnaliticoPorMesAnoEvento(int mes, int ano){
		
		try {
			TypedQuery<Integer> consulta = manager.createQuery("SELECT idAnalitico FROM FinancAnalitico where anoA = "+ano+" and mesReferencia = "+mes, Integer.class);
			int valor = consulta.getSingleResult();
			return valor;
		} catch (Exception e) {
			System.out.println("Não foi possível retornar o idAnalitico: "+e);
			return 0;
		}
	}
	
	
// --------------------------------------------------------- //
	public void salvaVideo(VideosYt videos){
		try {
			manager.persist(videos);
		} catch (Exception e) {
			System.out.println("Erro ao salvar um video: "+e);
		}
		
	}
	
	
}
