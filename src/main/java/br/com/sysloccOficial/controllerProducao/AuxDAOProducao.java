package br.com.sysloccOficial.controllerProducao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Producao;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.producao.DtPgtoFornecedor;
import br.com.sysloccOficial.model.producao.FornecedorFinanceiro;
import br.com.sysloccOficial.model.producao.ProducaoP;
import br.com.sysloccOficial.model.producao.StatusFinanceiro;
import br.com.sysloccOficial.model.producao.StatusProducao;
import br.com.sysloccOficial.model.producao.ValorPagtoFornecedor;
import br.com.sysloccOficial.model.producao.ValorPgtoAux;


@Repository
public class AuxDAOProducao extends AuxProducao{

	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	
	public void apagaFornecedor(ProducaoP producao, Integer idFornecedor,List<ValorPgtoAux> pagamentos){
		
		String pegaFornecedorFinanceiro = "SELECT idFornecedor FROM FornecedorFinanceiro where idProducao =" +producao.getIdProducao()+" and idEmpresa =" +idFornecedor;
		TypedQuery<Integer> q = manager.createQuery(pegaFornecedorFinanceiro, Integer.class);
		Integer idFornecedorFinanc = q.getSingleResult();
		
		apagaFornecedorFinanceiro(producao, idFornecedorFinanc);
		
		FornecedorFinanceiro outro = verifeicaSeExisteOutroFornecedorContratacao(producao.getIdProducao(), idFornecedor);

		if(outro.getIdFornecedor() == null){
			
		}else{
			apagaFornecedorFinanceiro(producao, outro.getIdFornecedor());
		}
		
   		salvaFornecedorFinanceiro(producao, pagamentos);
		
	}

	public void apagaFornecedorFinanceiro(ProducaoP producao,Integer idFornecedorFinanc){
		
		// Lista de ids Valorpgtofornecedor
		String idsvalorpagtofornecedor = "SELECT idValorFinancForn FROM ValorPagtoFornecedor  WHERE idFornecedorFinanceiro =" + idFornecedorFinanc;
		TypedQuery<Integer> apoiolistaIdsValorPgtoFornec = manager.createQuery(idsvalorpagtofornecedor,Integer.class);
		List<Integer> listaIdsValorPgtoFornec = apoiolistaIdsValorPgtoFornec.getResultList();
		
		for (int i =0; i<listaIdsValorPgtoFornec.size();i++ ){
			excluiDtPgto(listaIdsValorPgtoFornec.get(i));
		}
		
		//Remove valorPgtoFornecedor
		for(int i =0; i< listaIdsValorPgtoFornec.size();i++){
			ValorPagtoFornecedor v = manager.getReference(ValorPagtoFornecedor.class, listaIdsValorPgtoFornec.get(i));
			manager.remove(v);
		}
		
		FornecedorFinanceiro fin = manager.find(FornecedorFinanceiro.class, idFornecedorFinanc);
		manager.remove(fin);
		
	}

	public void salvaFornecedorFinanceiro(ProducaoP producao,List<ValorPgtoAux> pagamentos){
		
		FornecedorFinanceiro fornecedor =	persisteNovoFornecedorFinanceiro(producao, pagamentos);
		
		enviarParaCriarValorPgtoFornecedor(pagamentos, fornecedor);
		
		/**
		 * Verifica se tem valor de contratação com outro fornecedor
		 * 
		 */
		if(producao.getInfoPag().equals("1") && producao.getInfoForn().equals("1"))
			contratacaoOutroFornecedor(producao);
		
	}
	
	public FornecedorFinanceiro persisteNovoFornecedorFinanceiro(ProducaoP producao,List<ValorPgtoAux> pagamentos){
		
		long verificaMaisIntens = verificaSeFornTemMaisItens(producao);

		FornecedorFinanceiro fornecedor = new FornecedorFinanceiro();
			fornecedor.setIdProducao(producao);
			fornecedor.setIdEmpresa(producao.getIdEmpFornecedor());
			fornecedor.setCondicaoPagamento(producao.getNumParcelas());
			fornecedor.setTipoPagamento(producao.getTipoPagamento());
			/** Verificar se fornecedor tem outros itens */
			if(verificaMaisIntens <= 1){
				fornecedor.setContratacao(true);
			}else{
				fornecedor.setContratacao(false);
			}
				
		// Verifica se tem valor de contratação
		if(producao.getInfoPag().equals("0")){  // Não tem valor de contratação combinado
					
		}else{		
			
			// Tem valor de contratação combinado
			fornecedor.setImposto(producao.getImposto());
			fornecedor.setContratacao(true);
			/**
			 * Verificar se eh mesmo fornecedor
			 */
			
		}

		manager.persist(fornecedor);
		return fornecedor;
	}

	
	public List<ValorPgtoAux> populaCondicaoesPgto(Integer parcelas, Producao producao){
		List<ValorPgtoAux> pagamentos = new ArrayList<ValorPgtoAux>();
		for(int i = 0; i <  parcelas;i++){
			ValorPgtoAux valor = new ValorPgtoAux();
			valor.setParcela(producao.getParcela().get(i));
			valor.setPrazo(producao.getPrazo().get(i));
			valor.setData(util.formataDatasStringParaCalendar(producao.getData().get(i)+" 08:00"));
			BigDecimal valorItem = util.valoresEmReais(producao.getValor().get(i));
			valor.setValor(valorItem);
			pagamentos.add(valor);
		}
		return pagamentos;
	}
	
	public Integer pegaTotalProdutoGrupoPorFornecedor(Integer idLista, Integer forn) {
		String counte = "select count(idProdutoGrupo) from ProdutoGrupo p where fornecedor_item = "
						+ forn+" and p.idGrupo.idLista.idLista ="+idLista;
		TypedQuery<Long> q = manager.createQuery(counte,Long.class);
		Integer retorna = Integer.valueOf(q.getSingleResult().toString());
		return retorna;
	}
	public Integer pegaTotalFornecedorPorProducaoPorIdLista(Integer idLista, Integer forn) {
		String contProducao = "select count(idProducao) from ProducaoP where idLista = "+idLista+" and idEmpFornecedor= "+ forn;
		TypedQuery<Long> qs = manager.createQuery(contProducao,Long.class);
		Integer retorna = Integer.valueOf(qs.getSingleResult().toString());
		return retorna;
	}

	public void mudaStatusItemProducaoParaItemFechado(Integer idLista,Integer idDoProdutoGrupo) {
		try {
			String consulta = "select p FROM ProducaoP p where idLista ="+idLista+" and idProdutoGrupo ="+idDoProdutoGrupo;
			TypedQuery<ProducaoP> q = manager.createQuery(consulta, ProducaoP.class);
			
			ProducaoP ItemProducao = q.getSingleResult();
			ItemProducao.setStatusProducao(StatusProducao.ITEMFECHADO);
			manager.merge(ItemProducao);
			
		} catch (Exception e) {
			
		}
	}
	
	public void mudaStatusProdutoGrupo(Integer idDoProdutoGrupo) {
		try {
			ProdutoGrupo produtoGrupo = manager.find(ProdutoGrupo.class, idDoProdutoGrupo);
			produtoGrupo.setStatus(1);
			manager.merge(produtoGrupo);
		} catch (Exception e) {
		}
	}
	
}
