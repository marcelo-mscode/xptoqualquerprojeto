package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;
import br.com.sysloccOficial.financeiro.relatorioeventos.TipoCache;
import br.com.sysloccOficial.model.CacheEvento;
import br.com.sysloccOficial.model.CachePadrao;
import br.com.sysloccOficial.model.DesIntFinanc;
import br.com.sysloccOficial.model.GiroEvento;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Repository
@Transactional
public class RelatorioEventoDAO {

	@PersistenceContext	private EntityManager manager;
	@Autowired UtilitariaDatas utildatas;
	
	
	public FinancAnalitico pegaFinancAnalitico(Integer idAnalitico){
		return manager.find(FinancAnalitico.class, idAnalitico);
	}
	
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
	
	
	
	public ArrayList<String> dataRelatoriosEventosCadastrados(Integer idLista) throws ParseException{
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Calendar> c = cb.createQuery(Calendar.class);
		Root<Lista> l = c.from(Lista.class);
		c.select(l.<Calendar>get("dataDoEvento"));
		
		Predicate predicate = cb.equal(l.get("idLista"), idLista);
		c.where(predicate);
		
		TypedQuery<Calendar> dtEvento = manager.createQuery(c);
		Calendar data =  dtEvento.getSingleResult();
		ArrayList<String> datas = utildatas.converteDateParaStringNacional(data.getTime());

		return datas;
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
			TypedQuery<RelatorioEventos> q = manager.createQuery("from RelatorioEventos where mesReferencia= "+ mes + " and anoEvento ="+ ano +"order by idRelatorioEvento", RelatorioEventos.class);
			return q.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro Em RelatorioEventoDAO\nMétodo -- > relatorioEventoPorMesReferencia:" +e);
			return null;
		}
	}
	
	
	/*public List<CachePadrao> listaRelatorioCaches(Integer idLista){
		
		try {
			
			// Verificar se tem relatorio evento
			Integer id = verificaSeTemRelatorioEventoPorIdLista(idLista);
			
			// Se tiver o relatorio 
			if(id != null){

			List<CacheEvento> listaCaches = pegaCacheExistenteDoRelatorio(id);
			// Verificar se tem cacheEvento desse relatorio
				
				// Se não tiver
				// Pega Cache Padrão
				if(listaCaches.isEmpty()){
					
					return pegaCachePadrao();
					
				// Se tiver
				// Se tiver pega caches do evento	
				}else{
					
					return preencheListaCacheComCacheRelatorioEventoExistente(listaCaches);
				
				}
				
			}else{
				
				return pegaCachePadrao();
			}
		
			
			
			
		} catch (Exception e) {
			System.out.println("Erro ao pegar cache Evento:" + e);
			return null;
		}
	}*/

	/*private List<CacheEvento> pegaCacheExistenteDoRelatorio(Integer id) {
		TypedQuery<CacheEvento> cacheEvento = manager.createQuery("from CacheEvento where relatorioEvento = "+ id, CacheEvento.class);
		List<CacheEvento> listaCaches = cacheEvento.getResultList();
		return listaCaches;
	}*/

	/*private List<CachePadrao> preencheListaCacheComCacheRelatorioEventoExistente(List<CacheEvento> listaCaches) {
		List<CachePadrao> cachePadrao = new ArrayList<CachePadrao>();
		
		for (int i = 0; i < listaCaches.size(); i++) {
			
			CachePadrao transfCache = new CachePadrao();
			
			
			transfCache.setHabilitado(true);
			transfCache.setNomeFunc(listaCaches.get(i).getCachePadrao().getNomeFunc());
			transfCache.setRazaoPorcentagem(listaCaches.get(i).getRazaoPorcentagem());
			transfCache.setTipoCache(listaCaches.get(i).getCachePadrao().getTipoCache());
			
			String porcentagem = listaCaches.get(i).getRazaoPorcentagem().multiply(new BigDecimal("100")).toString();
			
			transfCache.setPorcentagem(porcentagem);
			
			cachePadrao.add(transfCache);
			
		}
		
		return cachePadrao;
	}*/

	/*private Integer verificaSeTemRelatorioEventoPorIdLista(Integer idLista) {
		
		try {
			String idRelatorioEvento = "select idRelatorioEvento from RelatorioEventos where idLista = " +idLista;
			TypedQuery<Integer> query = manager.createQuery(idRelatorioEvento,Integer.class);
			Integer id = query.getSingleResult();
			return id;
		} catch (Exception e) {
			return null;
		}
		
	}*/

	/*private List<CachePadrao> pegaCachePadrao() {
		String consulta = "from CachePadrao order by idCachePadrao";
		TypedQuery<CachePadrao> q = manager.createQuery(consulta,CachePadrao.class);

		return q.getResultList();
	}*/
	
	public GiroEvento giroPorIdLista(Integer idRelatorioEvento){
	
		try {
			TypedQuery<GiroEvento> giro = manager.createQuery("from GiroEvento g where g.relatorioEvento.idRelatorioEvento="+idRelatorioEvento,GiroEvento.class);
			return giro.getSingleResult();
		} catch (Exception e) {
			/*JOptionPane.showMessageDialog(null, ""+e);*/
			return null;
		}

	
	}
	
	public LinkedHashSet<CacheEvento> listaCacheEventoPorEvento(Integer idRelatorioEvento){
		TypedQuery<CacheEvento> q = manager.createQuery("FROM CacheEvento where relatorioEvento ="+idRelatorioEvento, CacheEvento.class);
		LinkedHashSet<CacheEvento> caches = new LinkedHashSet<CacheEvento>(q.getResultList());
		return caches;
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
//			String consulta = "select sum(giroSemTelefone) from GiroEvento where anoEvento ='"+ano+"' and mesEvento = '"+mes+"' and relatorioEvento <> "+idRelatorioAtual;
			
			// ------- > é a divisão do giro do evento pela soma de todos os giros
			String consulta = "select sum(giroSemTelefone) from GiroEvento where anoEvento ='"+ano+"' and mesEvento = '"+mes+"'";
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
		
		List<CachePadrao> cachePadrao =  listaRelatorioCaches(relatorioEvento.getIdLista());
		
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
			System.out.println("erro Listas: "+e);
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

	public List<DesIntFinanc> despesasProjeto(Integer idLista) {
		try {
			TypedQuery<DesIntFinanc> despesas = manager.createQuery("FROM DesIntFinanc WHERE idLista = "+idLista, DesIntFinanc.class);
			return despesas.getResultList();
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Integer> idsRelatoriosEventosPorMesAno(Integer mes, Integer anoEvento){
		try {
			String consultaRel = "select idRelatorioEvento from RelatorioEventos where anoEvento = '"+anoEvento+"' and mesReferencia = '"+mes+"'";
			TypedQuery<Integer> idsRelatorios = manager.createQuery(consultaRel, Integer.class);
			return idsRelatorios.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public LinkedHashSet<Integer> idsListaRelatoriosEventosPorMesAno(String mes,String anoEvento){
	
		try {
			String consultaRel = "select idLista from RelatorioEventos where mesEvento = '"+mes+"' and anoEvento = '"+anoEvento+"'";
			TypedQuery<Integer> idsRelatorios = manager.createQuery(consultaRel, Integer.class);
			
			LinkedHashSet<Integer> ids = new LinkedHashSet<Integer>(idsRelatorios.getResultList());
			
			return ids;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public void atualizaCacheEvento(Integer idRelatorio, Integer idCachePadrao, String novoValorCache){
		
		String consulta = "from CacheEvento where relatorioEvento = "+idRelatorio+" and cachePadrao = "+idCachePadrao;
		TypedQuery<CacheEvento> q = manager.createQuery(consulta, CacheEvento.class);
		
		CacheEvento cache = q.getSingleResult();
		
		BigDecimal rz = new BigDecimal(novoValorCache);
		
		cache.setRazaoPorcentagem(rz.divide(new BigDecimal("100"),12,RoundingMode.UP));

		
		manager.merge(cache);
		manager.flush();
		
		System.out.println(rz);
		
	}
	
	
	
}
