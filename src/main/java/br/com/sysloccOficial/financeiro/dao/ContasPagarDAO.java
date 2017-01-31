package br.com.sysloccOficial.financeiro.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.producao.DtPgtoFornecedor;
import br.com.sysloccOficial.model.producao.ProducaoP;

@Repository
@Transactional
public class ContasPagarDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	
	
	public List<Integer> idsListasEmProducaoP(){
		TypedQuery<Integer> qListas = manager.createQuery("SELECT distinct(lista.idLista) FROM ProducaoP",Integer.class);
		List<Integer> idsListas = qListas.getResultList();
		return idsListas;
	}

	public List<Object[]> nomesListasEmProducaoP(List<Integer> idListas){
		String consulta = "SELECT idLista,lista FROM Lista where idLista in ("+idListas+")";
		String c2 = consulta.replace("[", "").replace("]", "");
		Query qListas = manager.createQuery(c2);
		List<Object[]> idsListas = qListas.getResultList();
		return idsListas;
	}

	public List<ProducaoP> carregaListasProducaoPPorIdLista(/*List<Integer> idssListas*/){
		
		List<Integer> idsListas = idsListasEmProducaoP();
		
		try {
			String consulta = "select p FROM ProducaoP p where idLista in ("+idsListas+") order by idEmpFornecedor.empresa";
			String c2 = consulta.replace("[", "").replace("]", "");
			TypedQuery<ProducaoP> q  = manager.createQuery(c2,ProducaoP.class);
			
			List<ProducaoP> listas = q.getResultList();
			
			return listas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public List<Object[]> idsDistintosFornecedoresPorListas(List<Integer> idsListas){
		try {
			String consulta = "SELECT distinct(idEmpFornecedor.idEmpresa), p.lista.idLista FROM ProducaoP p where idlista in ("+idsListas+")order by idEmpFornecedor";
			String c2 = consulta.replace("[", "").replace("]", "");
			Query ids = manager.createQuery(c2);
			List<Object[]> lista = ids.getResultList();
			return lista;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	//Pegar datas Pagamento = PENDENTE
	public List<ProducaoP> pegaInfoFornecedores(){
		List<DtPgtoFornecedor> listaValorNaoPago = datasNaoPagas();

		List<Integer> listaIds = idsProducaoPorValorNaoPago(listaValorNaoPago);
		
		List<Integer> listasProducaoP = idsEmpresasDistintas(listaIds);
		
		TypedQuery<ProducaoP> listas = listaProducaoPPorIdFornecedor(listasProducaoP);
		return listas.getResultList();
	}

	private TypedQuery<ProducaoP> listaProducaoPPorIdFornecedor(List<Integer> listasProducaoP) {
		String consulta2 = "FROM ProducaoP where idEmpFornecedor.idEmpresa in ("+listasProducaoP+") order by lista.idLista,idEmpFornecedor.idEmpresa";
		String c3 = consulta2.replace("[", "").replace("]", "");
		TypedQuery<ProducaoP> listas = manager.createQuery(c3,ProducaoP.class);
		return listas;
	}

	private List<Integer> idsEmpresasDistintas(List<Integer> listaIds) {
		String consultaFornecedoresDistintos = "select distinct(idEmpFornecedor.idEmpresa) from ProducaoP where idProducao in ("+listaIds+")";
		String c2 =consultaFornecedoresDistintos .replace("[", "").replace("]", "");
		TypedQuery<Integer> produ = manager.createQuery(c2,Integer.class);
		List<Integer> listasProducaoP = produ.getResultList();
		return listasProducaoP;
	}

	
	
	private List<DtPgtoFornecedor> datasNaoPagas() {
		String consultaPgtoPendentes = "FROM DtPgtoFornecedor where status = 'PENDENTE' ORDER BY dataPagar";
		TypedQuery<DtPgtoFornecedor> q = manager.createQuery(consultaPgtoPendentes,DtPgtoFornecedor.class);
		List<DtPgtoFornecedor> listaValorNaoPago = q.getResultList();
		return listaValorNaoPago;
	}

	
	
	
	private List<Integer> idsProducaoPorValorNaoPago(List<DtPgtoFornecedor> listaValorNaoPago) {
		List<Integer> listaIds = new ArrayList<Integer>();
		for (int i = 0; i < listaValorNaoPago.size(); i++) {
			listaIds.add(listaValorNaoPago.get(i).getValorPgtoForn().getIdFornecedorFinanceiro().getIdProducao().getIdProducao());
		}
		return listaIds;
	}
	
	
	public List<Integer> idListasDistintas(){
		
		try {
			List<DtPgtoFornecedor> listaValorNaoPago = datasNaoPagas();
			List<Integer> listaIdsProducaoPDistintos = idsProducaoPorValorNaoPago(listaValorNaoPago);
			
			String consulta = "select distinct(lista.idLista) from ProducaoP where idProducao in ("+listaIdsProducaoPDistintos+")";
			String c2 =consulta.replace("[", "").replace("]", "");
			TypedQuery<Integer> ids = manager.createQuery(c2,Integer.class);
			return ids.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<Object[]> idFornecedoresDistintos(){
		
		
		List<Integer> idListasDistintas = idListasDistintas();
		
		try {
//			String consulta = "select distinct(idEmpFornecedor.idEmpresa),lista.idLista,idEmpFornecedor.empresa  from ProducaoP where lista.idLista in ("+idListasDistintas+") order by idEmpFornecedor.idEmpresa";
			String consulta = "select distinct(idEmpFornecedor.idEmpresa),lista.idLista,idEmpFornecedor.empresa  from ProducaoP where lista.idLista in ("+1932+") order by idEmpFornecedor.idEmpresa";
			String c2 =consulta.replace("[", "").replace("]", "");
			TypedQuery<Object[]> ids = manager.createQuery(c2,Object[].class);
			
			List<Object[]> listas = ids.getResultList();
			
			return listas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
