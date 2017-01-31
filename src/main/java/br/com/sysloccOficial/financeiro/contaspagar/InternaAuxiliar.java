package br.com.sysloccOficial.financeiro.contaspagar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.financeiro.dao.ContasPagarDAO;
import br.com.sysloccOficial.model.producao.ProducaoP;

@Component
public class InternaAuxiliar implements Interna{

	@Autowired ContasPagarDAO contasPagar;
	
	
	private List<Integer> idsListas;
	public InternaAuxiliar(){}
	
	public InternaAuxiliar(List<Integer> idsListas) {
		this.idsListas = idsListas;
	}
	
	@Override
	public List<ProducaoP> carregaListasFornecedores(/*List<Integer> idsListas*/) {
		List<ProducaoP>  listas =  contasPagar.carregaListasProducaoPPorIdLista(/*idsListas*/);
		return listas;
	}
	
	@Override
	public List<Object[]> carregaIdsFornecedoresDistintos(List<Integer> idsListas){
		List<Object[]> carregaIdsFornecedoresDistintos = contasPagar.idsDistintosFornecedoresPorListas(idsListas);
		return carregaIdsFornecedoresDistintos;
	}
	
	
	
	@Override
    public List<ContasPagar> preencheContasAPagar(List<ProducaoP> listaProducaoP, List<Integer> idsListas) {
	
			List<ContasPagar> contasPagar = new ArrayList<ContasPagar>();
		
			
			for (int i = 0; i < listaProducaoP.size(); i++) {
				
				VerificaTipoPagto tipo = new VerificaTipoPagto(listaProducaoP, i);
				ContasPagar novaConta = new ContasPagar();
				
				novaConta.setIdLista(listaProducaoP.get(i).getLista().getIdLista());
				novaConta.setNomeLista(listaProducaoP.get(i).getLista().getLista());
				novaConta.setNomeFornecedor(listaProducaoP.get(i).getIdEmpFornecedor().getEmpresa());
				novaConta.setTipoPagto(tipo.verifica(listaProducaoP.get(i).getFornecedorFinanceiro().getTipoPagamento()));
				
				preencheInfoPagto(listaProducaoP, i, novaConta);
				contasPagar.add(novaConta);
			}
		
		return contasPagar;
	}

	private void preencheInfoPagto(List<ProducaoP> listaProducaoP, int i, ContasPagar novaConta) {
		for (int j = 0; j < listaProducaoP.get(i).getFornecedorFinanceiro().getIdValorPgtoFornecedor().size(); j++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(listaProducaoP.get(i).getFornecedorFinanceiro().getIdValorPgtoFornecedor().get(j).getDtPgotFornecedor().getDataPagar());
			
			novaConta.setValorPagamento(listaProducaoP.get(i).getFornecedorFinanceiro().getIdValorPgtoFornecedor().get(j).getValor());
			novaConta.setDataPagto(cal);
		}
	}
	
	
	
	
	
	
}
