package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;










import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.financeiro.model.FinancImpostos;
import br.com.sysloccOficial.financeiro.relatorioeventos.Giro;
import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioCaches;
import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.GiroEvento;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Repository
@Transactional
public class RelatorioEventoDAO {

	@PersistenceContext	private EntityManager manager;
	
	public List<Integer> idsFornecedoresPorLista(int idLista){
		TypedQuery<Integer> q = manager.createQuery("SELECT distinct(idEmpFornecedor.idEmpresa) FROM ProducaoP p where idLista = "+idLista+" and p.produtoGrupo.imposto = 1 order by idEmpFornecedor.empresa",Integer.class);
		return q.getResultList();
	}
	
	public List<ProducaoP> listaProducaoPPorIdLista(int idLista){
		//TypedQuery<ProducaoP> q2 = manager.createQuery("SELECT p FROM ProducaoP p where idLista = "+idLista,ProducaoP.class);
		TypedQuery<ProducaoP> q2 = manager.createQuery("SELECT p FROM ProducaoP p where idLista = "+idLista,ProducaoP.class);
		return q2.getResultList();
	}
	

	public List<ProducaoP> listaProducaoP(){
		TypedQuery<ProducaoP> q2 = manager.createQuery("SELECT p FROM ProducaoP p where idLista = 2082",ProducaoP.class);
		return q2.getResultList();
	}
	
	public Lista listaPorIdLista(Integer idLista){
		return manager.find(Lista.class, idLista);
		
	}
	
	public RelatorioEventos relatorioEventoPorIdLista(Integer idLista){
		try {
			TypedQuery<RelatorioEventos> q = manager.createQuery("from RelatorioEventos where idLista="+idLista, RelatorioEventos.class);
			return q.getSingleResult();
			
		} catch (Exception e) {
			return null;
		}
	}

	public List<Lista> relatorioListasIdLista(List<RelatorioEventos> eventos){
		
		List<Integer> idListas = new ArrayList<Integer>();
		
		for (int i = 0; i < eventos.size(); i++) {
			idListas.add(eventos.get(i).getIdLista());
		}
		
		try {
			String consulta1 ="from Lista where idLista in("+idListas+")";
			String consulta2 = consulta1.replace("[", "").replace("]", "");
			
			TypedQuery<Lista> q = manager.createQuery(consulta2, Lista.class);
			
			return q.getResultList();
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro em RelatorioEventoDAO, linha 73\nErro: "+e);
			return null;
		}
	}

	public List<RelatorioEventos> relatorioEventoPorMesReferencia(Integer mes, Integer ano){
		try {
			TypedQuery<RelatorioEventos> q = manager.createQuery("from RelatorioEventos where mesReferencia="+mes + " and anoEvento ="+ ano+"order by idRelatorioEvento", RelatorioEventos.class);
			return q.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro Em RelatorioEventoDAO\nMétodo -- > relatorioEventoPorMesReferencia:" +e);
			return null;
		}
	}
	
	
	public List<CachePadrao> listaRelatorioCaches(){
		String consulta = "from CachePadrao order by idCachePadrao";
		TypedQuery<CachePadrao> q = manager.createQuery(consulta,CachePadrao.class);
		return q.getResultList();
	}
	
	public GiroEvento giroPorIdLista(Integer idRelatorioEvento){
	
		try {
			TypedQuery<GiroEvento> giro = manager.createQuery("from GiroEvento g where g.relatorioEvento.idRelatorioEvento="+idRelatorioEvento,GiroEvento.class);
			return giro.getSingleResult();
		} catch (Exception e) {
			/*JOptionPane.showMessageDialog(null, ""+e);*/
			return null;
		}

	
	}
	
	public List<CacheEvento> listaCacheEventoPorEvento(Integer idRelatorioEvento){
		Query q = manager.createQuery("FROM CacheEvento where relatorioEvento ="+idRelatorioEvento);
		return q.getResultList();
	}
	
	public BigDecimal calculaTotalCachesFuncionarios(BigDecimal totalDif, List<CachePadrao> relatorio) {
		
		
		BigDecimal totalCachesFunc = new BigDecimal("0");
	
		for (int i = 0; i < relatorio.size(); i++) {
			if(relatorio.get(i).getTipoCache() == TipoCache.FUNCIONARIO){
				totalCachesFunc = totalCachesFunc.add(totalDif
						.multiply(relatorio.get(i).getRazaoPorcentagem()));
			}
		}
		return totalCachesFunc;
	}
	
	public BigDecimal caculaValorSeDiretoria(List<CachePadrao> relatorio, BigDecimal totalDifCaches, BigDecimal totalCacheFuncionarios) {
		BigDecimal totalCalculoParaDiretoria = totalDifCaches.subtract(totalCacheFuncionarios);
		
		BigDecimal totalCachesDiretoria = new BigDecimal("0");
		for (int i = 0; i < relatorio.size(); i++) {
			if(relatorio.get(i).getTipoCache() == TipoCache.DIRETORIA1 || relatorio.get(i).getTipoCache() == TipoCache.DIRETORIA2){
				totalCachesDiretoria = totalCachesDiretoria.add(totalCalculoParaDiretoria
						.multiply( new BigDecimal(relatorio.get(i).getPorcentagem())
						.divide(   new BigDecimal("100"))));
			}
		}
		
		return totalCachesDiretoria;
	}
	
	public BigDecimal somaGirosPorAnoMes(String ano, String mes, Integer idRelatorioAtual){
		BigDecimal zero = new BigDecimal("0.00");
		try {
			String consulta = "select sum(giroSemTelefone) from GiroEvento where anoEvento ='"+ano+"' and mesEvento = '"+mes+"' and relatorioEvento <> "+idRelatorioAtual;
			TypedQuery<BigDecimal> soma = manager.createQuery(consulta, BigDecimal.class);
			
			if(soma.getSingleResult() == null){
				return zero;
			}else{
				return soma.getSingleResult();
			}
		} catch (Exception e) {
			System.out.println(e);
			return zero;
		}
	}
	
	public void salvaCacheDoEvento(RelatorioEventos relatorioEvento){
		
		manager.createQuery("DELETE FROM CacheEvento WHERE relatorioEvento="+relatorioEvento.getIdRelatorioEvento()).executeUpdate();
		
		List<CachePadrao> cachePadrao =  listaRelatorioCaches();
		BigDecimal valorParaDiretoria = relatorioEvento.getTotalDiferenca().subtract(relatorioEvento.getTotalCachesIntExt());
		
		
		
		for (int i = 0; i < cachePadrao.size(); i++) {
				CacheEvento novoCacheEvento = new CacheEvento();
				if(cachePadrao.get(i).getTipoCache().equals(TipoCache.FUNCIONARIO)){
					novoCacheEvento.setRazaoPorcentagem(cachePadrao.get(i).getRazaoPorcentagem());
					novoCacheEvento.setValor(relatorioEvento.getTotalDiferenca().multiply((cachePadrao.get(i).getRazaoPorcentagem())));
					novoCacheEvento.setRelatorioEvento(relatorioEvento);
					novoCacheEvento.setCachePadrao(cachePadrao.get(i));
					manager.merge(novoCacheEvento);
				}
				
				if(cachePadrao.get(i).getTipoCache().equals(TipoCache.DIRETORIA1) || cachePadrao.get(i).getTipoCache().equals(TipoCache.DIRETORIA2)){
					novoCacheEvento.setRazaoPorcentagem(cachePadrao.get(i).getRazaoPorcentagem());
					novoCacheEvento.setValor(valorParaDiretoria.multiply((cachePadrao.get(i).getRazaoPorcentagem())));
					novoCacheEvento.setRelatorioEvento(relatorioEvento);
					novoCacheEvento.setCachePadrao(cachePadrao.get(i));
				//	System.out.println(cachePadrao.get(i).getNomeFunc()+"- valor:"+novoCacheEvento.getValor());
					
					manager.merge(novoCacheEvento);
				}
		}
	
	}

	public List<String> listaAnoRelatorioEventos(){
		try {
			String ano ="SELECT distinct(anoEvento) FROM RelatorioEventos order by anoEvento desc";
			TypedQuery<String> q = manager.createQuery(ano, String.class);
			return q.getResultList();
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "erro AnoLista: "+e);
			return null;
		}
	}

	public List<RelatorioEventos> listaRelatorioEventos(){
		try {
			String mes ="select r FROM RelatorioEventos r order by anoEvento DESC, mesReferencia DESC";
			TypedQuery<RelatorioEventos> q = manager.createQuery(mes, RelatorioEventos.class);
			return q.getResultList();
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "erro Listas: "+e);
			return null;
		}
	}

	public List<Lista> ListaProducao(){
		try {
			
			String listaRelatorios = "SELECT distinct(idLista) FROM RelatorioEventos";
			TypedQuery<Integer> cons = manager.createQuery(listaRelatorios, Integer.class);
			List<Integer> idListas = cons.getResultList();

			String mes ="select l FROM Lista l where idLista in ("+idListas+")";
			String c2 = mes.replace("[", "").replace("]", "");
			TypedQuery<Lista> q = manager.createQuery(c2, Lista.class);
			return q.getResultList();
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "erro Listas: "+e);
			return null;
		}
	}
	
	
	
	public BigDecimal despesasFixas(String nomeTabela, String data) {
		
		try {
			TypedQuery<BigDecimal> f = manager.createQuery("select sum(valor) from "+nomeTabela+" where data like '%"+data+"%'",BigDecimal.class);
			return f.getSingleResult();
		} catch (Exception e) {
			System.out.println("erro despesasFixas: "+e);
			return null;
		}
	}
	

	public BigDecimal MOMargemContribuicao(String anoEvento, String mesEvento) {
		
		try {
			TypedQuery<BigDecimal> f = manager.createQuery("select sum(margemContribuicao) from RelatorioEventos where anoEvento = '"+anoEvento+"' and mesEvento = '"+mesEvento+"'",BigDecimal.class);
			return f.getSingleResult();
		} catch (Exception e) {
			System.out.println("erro MOMargemContribuicao: "+e);
			return null;
		}
	}

	public BigDecimal contasReceber(String anoEvento, String mesEvento) {
		try {
			TypedQuery<BigDecimal> f = manager.createQuery("select sum(valorLoccoAgenc) from RelatorioEventos where anoEvento = '"+anoEvento+"' and mesEvento = '"+mesEvento+"'",BigDecimal.class);
			return f.getSingleResult();
		} catch (Exception e) {
			System.out.println("erro contasReceber: "+e);
			return null;
		}
	}
	
	public BigDecimal eventosContasPagar(String anoEvento, String mesEvento) {
		try {
			TypedQuery<BigDecimal> f = manager.createQuery("select sum(valorLoccoAgenc) from RelatorioEventos where anoEvento = '"+anoEvento+"' and mesEvento = '"+mesEvento+"'",BigDecimal.class);
			return f.getSingleResult();
		} catch (Exception e) {
			System.out.println("erro contasReceber: "+e);
			return null;
		}
	}

	public BigDecimal salarios(String data) {
		try {
			TypedQuery<BigDecimal> f = manager.createQuery("select sum(valor) from FinancFolhaPgto where data like '%"+data+"%'",BigDecimal.class);
			return f.getSingleResult();
		} catch (Exception e) {
			System.out.println("erro salarios: "+e);
			return null;
		}
	}

	public BigDecimal outrosImpostos(String data) {
		
		try {
			TypedQuery<BigDecimal> f = manager.createQuery("select sum(valor) from FinancImpostos where data like '%"+data+"%'",BigDecimal.class);
			return f.getSingleResult();
		} catch (Exception e) {
			System.out.println("erro despesasFixas: "+e);
			return null;
		}
	}

	public InfoInterna infoInterna(Integer idLista) {
		
		try {
			TypedQuery<InfoInterna> info = manager.createQuery("from InfoInterna where idLista = "+idLista, InfoInterna.class);
			return info.getSingleResult();
			
		} catch (Exception e) {
			System.out.println("Erro ao buscar informações em InfoInterna: "+e);
			return null;
		}
		
	}

	public InfoInterna pegaInfoInterna(Integer idLista) {
		try {
			TypedQuery<InfoInterna> info = manager.createQuery("from InfoInterna where idLista= "+idLista, InfoInterna.class);
			return info.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	
}
