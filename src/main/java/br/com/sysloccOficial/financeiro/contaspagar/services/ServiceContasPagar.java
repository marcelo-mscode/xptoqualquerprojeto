package br.com.sysloccOficial.financeiro.contaspagar.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.financeiro.contaspagar.repository.ContasPagarRepository;
import br.com.sysloccOficial.model.producao.DtPgtoFornecedor;
import br.com.sysloccOficial.model.producao.ProducaoP;
import br.com.sysloccOficial.model.producao.ValorPagtoFornecedor;


@Service
public class ServiceContasPagar {
	
	@Autowired ContasPagarRepository contasPagarRepository;
	@Autowired private Utilitaria util;
	@Autowired private ServicePegaDiasPagamento servicePegaDiasPagamento;
	@Autowired private ServiceMontaDiasPagamentoDataFornecedorValor serviceMontaDiasPagamento;
	
	public List<Object[]> montaListaMesAtual(){
		List<Object[]> idListas = contasPagarRepository.pegaListasMesAtual(); 
		return idListas;
	}
	
	public List<Object[]> constroiObjeto(List<Object[]> idListas){
		
		List<Object[]> objetoConstruido = new ArrayList<Object[]>();

		for (int i = 0; i < idListas.size(); i++) {
			Integer num = (Integer) idListas.get(i)[0];
			
			List<Integer> idFornecedores = contasPagarRepository.pegaIdsFornecedoresPorIdLista(num);
			for (int j = 0; j < idFornecedores.size(); j++) {
				List<Object[]> constroiObjeto = montaObjeto(num,idFornecedores.get(j));
				objetoConstruido.addAll(constroiObjeto);
			}
		}
		return objetoConstruido;
	}
	
	public List<Object[]> montaObjeto(Integer idLista,Integer idFornecedor){
		
		List<Integer> listaUmFornecedor = contasPagarRepository.listaIdsProducaoPDeUmFornecedorPorLista(idLista, idFornecedor);   
		
		List<Integer> listaFornecedorFinanceiro = contasPagarRepository.pegaListaFornecedorFinanceiroPorIdsProducao(listaUmFornecedor);
		
		List<Object[]> listaValorPgtoFornecedor = serviceMontaDiasPagamento.montaDiasPagamentoDataFornecedorValor(listaFornecedorFinanceiro);
		
		List<Integer> pegaDiasPagamento = servicePegaDiasPagamento.pegaDiasPagamento(listaFornecedorFinanceiro);
		
		ProducaoP producaoP = contasPagarRepository.pegaProducaoP(idLista, idFornecedor);
		
		
		List<Object[]> listaObjetos = new ArrayList<Object[]>();
		
		for (int i = 0; i < pegaDiasPagamento.size(); i++) {
			
			BigDecimal valor = new BigDecimal("0.00");
			BigDecimal diferenca = new BigDecimal("0.00");
			
			Object[] novo = new Object[11];

			for (int j = 0; j < listaValorPgtoFornecedor.size(); j++) {
				if (listaValorPgtoFornecedor.get(j)[0].equals(pegaDiasPagamento.get(i))) {
					
				  //listaValorPgtoFornecedor.get(j)[3]); // ---> Aqui pega o valor total sem contratação
				  //listaValorPgtoFornecedor.get(j)[8]); // ---> Aqui pega o valor de contratação !
				  //listaValorPgtoFornecedor.get(j)[9]); // ---> Aqui pega a diferenca !
					
					valor = valor.add((BigDecimal) listaValorPgtoFornecedor.get(j)[3]); // 
					diferenca = diferenca.add((BigDecimal) listaValorPgtoFornecedor.get(j)[9]); // 
			
					novo[0] = listaValorPgtoFornecedor.get(j)[1]; // data pagar
					novo[1] = listaValorPgtoFornecedor.get(j)[2]; // nome fornecedor
					novo[5] = listaValorPgtoFornecedor.get(j)[4]; // idFornecedor
					novo[6] = listaValorPgtoFornecedor.get(j)[0]; // diasPrazoParaPagamenteo
					novo[7] = listaValorPgtoFornecedor.get(j)[5]; // temContratacao

					novo[8] = listaValorPgtoFornecedor.get(j)[6]; // temMesmoFornecedor
					novo[9] = listaValorPgtoFornecedor.get(j)[7]; // valorPagtoContratacao
					novo[10] = listaValorPgtoFornecedor.get(j)[8]; // valorContratacao
					
				}
			}
				
			novo[2] = producaoP.getTipoPagamento(); 
			novo[3] = producaoP.getLista().getIdLista(); 
			novo[4] = valor.subtract(diferenca); 
			
			listaObjetos.add(novo);
		}
		
		return listaObjetos;
	}

	public List<DtPgtoFornecedor> pegarContasPendente() {
		List<DtPgtoFornecedor> pegarContasPendente = contasPagarRepository.pegarContasPendente();
		return pegarContasPendente;
	}

	public List<ValorPagtoFornecedor> pegarValoresPendente( List<DtPgtoFornecedor> pegarContasPendente) {
		
		for (int i = 0; i < pegarContasPendente.size(); i++) {
		
			System.out.println(pegarContasPendente.get(i).getValorPgtoForn().getIdValorFinancForn());
			
		}
		
		
	/*	String limpa = "SELECT * FROM ValorPagtoFornecedor WHERE idValorFinancForn IN ("+numeros+")";
		String consulta = util.limpaSqlComList(limpa);
		System.out.println(consulta);*/
		System.out.println();
		
		
		
		return null;
	}

	public void testaIdsProducao() {
		
		
		contasPagarRepository.pegaIdsProducaoP();
		
		
		
		
		
	}
}
