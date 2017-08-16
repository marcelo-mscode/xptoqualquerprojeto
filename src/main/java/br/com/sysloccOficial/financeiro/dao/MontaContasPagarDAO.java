package br.com.sysloccOficial.financeiro.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancosSaidas;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.producao.DtPgtoFornecedor;
import br.com.sysloccOficial.model.producao.ProducaoP;
import br.com.sysloccOficial.model.producao.StatusFinanceiro;
import br.com.sysloccOficial.model.producao.ValorPagtoFornecedor;

@Repository
@Transactional
public class MontaContasPagarDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	
	
	public List<Object[]> constroiObjeto(){
		List<Object[]> objetoConstruido = new ArrayList<Object[]>();
	
		List<Integer> idListas =  pegaIdsListasIndividuais(); 
		
		for (int i = 0; i < idListas.size(); i++) {
			List<Integer> idFornecedores = pegaIdsFornecedoresPorIdLista(idListas.get(i));
			for (int j = 0; j < idFornecedores.size(); j++) {
				List<Object[]> constroiObjeto = montaObjeto(idListas.get(i),idFornecedores.get(j));
				objetoConstruido.addAll(constroiObjeto);
			}
		}
		return objetoConstruido;
	}

	public List<Object[]> constroiObjetoTeste(List<Object[]> idListas){
		List<Object[]> objetoConstruido = new ArrayList<Object[]>();
		//List<Integer> idListas =  pegaIdsListasIndividuais(); 
		for (int i = 0; i < idListas.size(); i++) {
			Integer num = (Integer) idListas.get(i)[0];
			
			List<Integer> idFornecedores = pegaIdsFornecedoresPorIdLista(num);
			for (int j = 0; j < idFornecedores.size(); j++) {
				List<Object[]> constroiObjeto = montaObjeto(num,idFornecedores.get(j));
				objetoConstruido.addAll(constroiObjeto);
			}
		}
		return objetoConstruido;
	}

	public BigDecimal somaTotalMeses(List<Object[]> listaAtual, List<Object[]> listaAnteriores){
		
		BigDecimal somaAnteriores = new BigDecimal("0");
		BigDecimal somaAtual = new BigDecimal("0");
		BigDecimal somaTotal = new BigDecimal("0");
		
		
		
		if(listaAtual.isEmpty()){
			//System.out.println("Sou uma lista atual vazia");	
		}else{
			for (int i = 0; i < listaAtual.size(); i++) {
				somaAtual = somaAtual.add((BigDecimal) listaAtual.get(i)[4]);
			}
		}
		
		if(listaAnteriores.isEmpty()){
			//System.out.println("Sou uma lista anterior vazia");	
		}else{
			for (int i = 0; i < listaAnteriores.size(); i++) {
				somaAnteriores = somaAnteriores.add((BigDecimal) listaAnteriores.get(i)[4]);
			}
		}
		
		
		somaTotal = somaAtual.add(somaAnteriores);
		
		return somaTotal;
	}
	
	
	private List<Integer> pegaIdsFornecedoresPorIdLista(Integer idListas) {
		String consulta = "select distinct(idEmpFornecedor.idEmpresa) from ProducaoP where idLista ="+idListas;
		TypedQuery<Integer> conLista = manager.createQuery(consulta, Integer.class);
		List<Integer> idsFornecedores = conLista.getResultList();
		return idsFornecedores;
	}


	public List<Object[]> pegaListasMesAtual() {
		String dataHoje =  UtilitariaDatas.pegaDataAtualEmStringPassandoFormato("yyyy-MM");
		String idsListasIndiv = "select distinct(lista.idLista), lista.lista from ProducaoP where lista.dataDoEvento like '%"+dataHoje+"%' order by lista.dataDoEvento desc";
		TypedQuery<Object[]> listaIds = manager.createQuery(idsListasIndiv,Object[].class);
		List<Object[]> idListas = listaIds.getResultList();
		return idListas;
	}

	public List<Object[]> pegaListasMesAnterior() {
		String dataHoje =  UtilitariaDatas.pegaDataAtualEmStringPassandoFormato("yyyy-MM");
		String idsListasIndiv = "select distinct(lista.idLista), lista.lista from ProducaoP where lista.dataDoEvento < '"+dataHoje+"-01' or dataDoEvento is null order by lista.dataDoEvento desc";
		TypedQuery<Object[]> listaIds = manager.createQuery(idsListasIndiv,Object[].class);
		List<Object[]> idListas = listaIds.getResultList();
		return idListas;
	}

	public List<Integer> pegaIdsListasIndividuais() {
		String idsListasIndiv = "select distinct(lista.idLista) from ProducaoP order by lista.dataDoEvento desc";
		TypedQuery<Integer> listaIds = manager.createQuery(idsListasIndiv,Integer.class);
		List<Integer> idListas = listaIds.getResultList();
		return idListas;
	}

	public List<Object[]> pegaListasIndividuais() {
		String idsListasIndiv = "select distinct(lista.idLista), lista.lista from ProducaoP order by lista.dataDoEvento desc";
		TypedQuery<Object[]> listaIds = manager.createQuery(idsListasIndiv,Object[].class);
		List<Object[]> idListas = listaIds.getResultList();
		return idListas;
	}
	
	
	public List<Object[]> montaObjeto(Integer idLista,Integer idFornecedor){
		
		/**
		 * Pega todos os registros da Lord em producaoP de uma lista
		 * 
		 */
		String consultaUmFornecedor = "SELECT idProducao FROM ProducaoP where lista.idLista ="+idLista+" and idEmpFornecedor ="+idFornecedor;
		TypedQuery<Integer> listaProdPUmForn = manager.createQuery(consultaUmFornecedor, Integer.class);
		List<Integer> listaUmFornecedor = listaProdPUmForn.getResultList();
		
		/**
		 * Pega todos os registros Financeiros da Lord 
		 * 
		 */
		String consultaLimpaFfinanceiro = util.limpaSqlComList("SELECT idFornecedor FROM"
				+ " FornecedorFinanceiro where idProducao in ("+listaUmFornecedor+")");
		TypedQuery<Integer> lista2 = manager.createQuery(consultaLimpaFfinanceiro,Integer.class);
		List<Integer> listaUmFinanceiro = lista2.getResultList();
		
		
		List<Object[]> lista = montaDiasPagamentoDataFornecedorValor(listaUmFinanceiro);
		
		List<Integer> pegaDiasPagamento = pegaDiasPagamento(listaUmFinanceiro);
		
		
		
		List<Object[]> listaObjetos = new ArrayList<Object[]>();
		
		
		String consultaModoPagamento = "FROM ProducaoP where lista.idLista ="+idLista+" and idEmpFornecedor ="+idFornecedor;
		TypedQuery<ProducaoP> consultaProducaP = manager.createQuery(consultaModoPagamento, ProducaoP.class).setMaxResults(1);
		ProducaoP producaoP = consultaProducaP.getSingleResult();
		
		
		for (int i = 0; i < pegaDiasPagamento.size(); i++) {
			
			BigDecimal valor = new BigDecimal("0.00");

			Object[] novo = new Object[11];
			for (int j = 0; j < lista.size(); j++) {
				if (lista.get(j)[0].equals(pegaDiasPagamento.get(i))) {
					
				  //valor = valor.add((BigDecimal) lista.get(j)[3]); // ---> Aqui pega o valor total sem contratação
					valor = valor.add((BigDecimal) lista.get(j)[8]); // ---> Aqui pega o valor de contratação !
			
					novo[0] = lista.get(j)[1]; // data pagar
					novo[1] = lista.get(j)[2]; // nome fornecedor
					novo[5] = lista.get(j)[4]; // idFornecedor
					novo[6] = lista.get(j)[0]; // diasPrazoParaPagamenteo
					novo[7] = lista.get(j)[5]; // temContratacao

					novo[8] = lista.get(j)[6]; // temMesmoFornecedor
					novo[9] = lista.get(j)[7]; // valorPagtoContratacao
					novo[10] = lista.get(j)[8]; // valorContratacao
					
				}
			}
				
			novo[2] = producaoP.getTipoPagamento(); 
			novo[3] = producaoP.getLista().getIdLista(); 
			novo[4] = valor; 
			
			listaObjetos.add(novo);
		}
		
		return listaObjetos;
	}

	/**
	 * Pega todas as datas para pagar a Lord
	 * 45 - 2016-07-25
	 * 60 - 2016-08-09
	 */
	private List<Object[]> montaDiasPagamentoDataFornecedorValor(List<Integer> listaUmFinanceiro) {
		String consultaDiasPrazoPagamento = util.limpaSqlComList("SELECT"
				+ " distinct(diasPrazoParaPagamento),"
				+ " dtPgotFornecedor.dataPagar, "
				+ " idEmpresa.empresa,"
				+ " valor,"
				+ " idEmpresa.idEmpresa,"
				+ " idFornecedorFinanceiro.contratacao,"
				+ " idFornecedorFinanceiro.idProducao.temMesmoFornecedor,"
				+ " idFornecedorFinanceiro.idProducao.valorDePagamentoContratacao,"
				+ " idFornecedorFinanceiro.idProducao.valorContratacao"
				+ " FROM ValorPagtoFornecedor where idFornecedorFinanceiro in("
				+ listaUmFinanceiro+") and dtPgotFornecedor.Status = 'PENDENTE' order by diasPrazoParaPagamento");
		TypedQuery<Object[]> listaT = manager.createQuery(consultaDiasPrazoPagamento,Object[].class);
		List<Object[]> lista = listaT.getResultList();
		
		//System.out.println();
		
		return lista;
	}

	/**
	 * Pega os dias que foram parcelados 
	 * 
	 * 
	 * @param listaUmFinanceiro
	 * @return
	 */
	private List<Integer> pegaDiasPagamento(List<Integer> listaUmFinanceiro) {
		String consultaDiasPrazoPagamento = util.limpaSqlComList("SELECT distinct(diasPrazoParaPagamento)"
				+ " FROM ValorPagtoFornecedor where idFornecedorFinanceiro in("
				+ listaUmFinanceiro+") and dtPgotFornecedor.Status = 'PENDENTE' order by diasPrazoParaPagamento");
		TypedQuery<Integer> listaT = manager.createQuery(consultaDiasPrazoPagamento,Integer.class);
		List<Integer> lista = listaT.getResultList();
		return lista;
	}





	public void pagaConta(Integer idLista, Integer idFornecedor, Integer qtdDias) {
		
		List<Integer> listaIdProducaoPorIdListaEIdFornecedor = pegaIdProducaoP(idLista, idFornecedor);
		
		List<Integer> listaIdFornecedorEmFornecedorFinanceiroPorIdProducao = pegaIdFornecedorFornecedorFinanceiro(listaIdProducaoPorIdListaEIdFornecedor);
		
		List<ValorPagtoFornecedor> listaValorPagamentoFornecedor = pegaListaValoresPGTODoFornecedor(qtdDias,listaIdFornecedorEmFornecedorFinanceiroPorIdProducao);
		

		MovimentacaoBancosSaidas registraSaida = new MovimentacaoBancosSaidas();

		
		System.out.println();
		
		Lista lista = manager.find(Lista.class, idLista);
		
		registraSaida.setData(Calendar.getInstance().getTime());
		registraSaida.setDescricao(lista.getLista());
		
		
		efetivaPagamento(listaValorPagamentoFornecedor,registraSaida);
	}

	
	private void efetivaPagamento( List<ValorPagtoFornecedor> listaValorPagamentoFornecedor,MovimentacaoBancosSaidas registraSaida) {
		
		BigDecimal valorFornecedor = new BigDecimal("0");
		
		for (int i = 0; i < listaValorPagamentoFornecedor.size(); i++) {
			int idDataPgto = 0;
			
			idDataPgto = listaValorPagamentoFornecedor.get(i).getDtPgotFornecedor().getDtPagtoForncedor();
			DtPgtoFornecedor dataPagamentoFornecedor = manager.find(DtPgtoFornecedor.class, idDataPgto);
			
			dataPagamentoFornecedor.setStatus(StatusFinanceiro.PAGO);
			manager.merge(dataPagamentoFornecedor);
			manager.close();
			valorFornecedor = valorFornecedor.add(listaValorPagamentoFornecedor.get(i).getValor());
		}
		
		
		FinancAnalitico analitico = manager.getReference(FinancAnalitico.class, 3);
		BancosAnalitico banco = manager.getReference(BancosAnalitico.class, 1);		
		registraSaida.setValor(valorFornecedor);
		registraSaida.setAnalitico(analitico);
		registraSaida.setBanco(banco);
		
		manager.persist(registraSaida);
		
	}

	private List<ValorPagtoFornecedor> pegaListaValoresPGTODoFornecedor(Integer qtdDias,
			List<Integer> listaIdFornecedorEmFornecedorFinanceiroPorIdProducao) {
		String consultaValorPgto =util.limpaSqlComList("FROM ValorPagtoFornecedor v join fetch v.dtPgotFornecedor where idFornecedorFinanceiro in("+listaIdFornecedorEmFornecedorFinanceiroPorIdProducao
		+") and diasPrazoParaPagamento="+qtdDias);
		
		TypedQuery<ValorPagtoFornecedor> listaValorPgto = manager.createQuery(consultaValorPgto,ValorPagtoFornecedor.class);
		List<ValorPagtoFornecedor> listaValorP = listaValorPgto.getResultList();
		return listaValorP;
	}

	private List<Integer> pegaIdFornecedorFornecedorFinanceiro(List<Integer> listaP) {
		String consultaFornFinanceiro =util.limpaSqlComList("SELECT idFornecedor FROM FornecedorFinanceiro where idProducao in ("+listaP+")");
		TypedQuery<Integer> listaFornFinanceiro = manager.createQuery(consultaFornFinanceiro,Integer.class);
		List<Integer> listaF = listaFornFinanceiro.getResultList();
		return listaF;
	}

	private List<Integer> pegaIdProducaoP(Integer idLista, Integer idFornecedor) {
		String consultaProducaoP = "select idProducao from ProducaoP where idLista ="+idLista+" and idEmpFornecedor ="+idFornecedor;
		TypedQuery<Integer> listaProducaoP = manager.createQuery(consultaProducaoP,Integer.class);
		List<Integer> listaP = listaProducaoP.getResultList();
		return listaP;
	}
	
	

}
