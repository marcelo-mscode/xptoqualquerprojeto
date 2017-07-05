package br.com.sysloccOficial.financeiro.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.financeiro.calculadora.Calculadora;
import br.com.sysloccOficial.financeiro.dao.InternaIndividualDAO;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Component
public class CalculoValoresInterna extends Calculadora{
	
	@Autowired private InternaIndividualDAO internaIndividualDAO;
	@Autowired private ProdutoGrupoDAO produtoDAO;
	@PersistenceContext	private EntityManager manager;
	
	private List<ProducaoP> listaDeProducaoP;
	private BigDecimal impostoLista;
	private double impostoDaInterna;
	
	public CalculoValoresInterna(List<ProducaoP> _listaDeProducaoP, Integer _idLista, BigDecimal _impostoLista, double impostoDaInterna) {
		this.listaDeProducaoP = _listaDeProducaoP;
		this.impostoLista = _impostoLista;
		this.impostoDaInterna = impostoDaInterna;
	}
	
	private BigDecimal subLoCCo = new BigDecimal("0.00");
	private BigDecimal subDireto = new BigDecimal("0.00");
	private BigDecimal subContratados = new BigDecimal("0.00");
	private BigDecimal subDiferenca;
	private BigDecimal feeGeral = new BigDecimal("0.00");
	private BigDecimal feeReduzido = new BigDecimal("0.00");
	private BigDecimal subTotalGeral;
	private BigDecimal impostoValorFornecedor;
	private BigDecimal impostoDiferenca;
	private BigDecimal total1Diferenca = new BigDecimal("0.00");
	private BigDecimal total2 = new BigDecimal("0.00");
	private BigDecimal total1LoCCO;
	private BigDecimal subValorFornecedor;	
	private BigDecimal subValorNf;

	private BigDecimal totalNF1;
	
	
	private BigDecimal prevExtraSubContrat= new BigDecimal("0.00");
	private BigDecimal prevExtraCustosInternos = new BigDecimal("0.00");
	private BigDecimal feeFatNF2= new BigDecimal("0.00");
	private BigDecimal subTotalFatNF2= new BigDecimal("0.00");
	private BigDecimal impostoFatNF2= new BigDecimal("0.00");
	private BigDecimal totalFatNF2= new BigDecimal("0.00");
	private BigDecimal totalEvento = new BigDecimal("0.00");
	private BigDecimal prevExtras = new BigDecimal("0.1");
	private BigDecimal subTotalGeralTabela = new BigDecimal("0.00");
	private BigDecimal impostoListatabela = new BigDecimal("0");
	private BigDecimal impostoListaPlanilha = new BigDecimal("0");
	
	public BigDecimal getSubDireto() {
		return subDireto = calculaSubDireto();
	}

	private BigDecimal calculaSubDireto() {
		BigDecimal soma = new BigDecimal("0");
		for (int i = 0; i < listaDeProducaoP.size(); i++) {
			if(listaDeProducaoP.get(i).getProdutoGrupo().isImposto() == false)	
				soma = soma.add(calculaSubLocco(i));
		}
		return soma;
	}

	
	public BigDecimal getFeeGeral() {
		try {
			feeGeral = listaDeProducaoP.get(0).getProdutoGrupo().getIdGrupo().getIdLista().getAdministracaoValor();
			return feeGeral.subtract(getFeeReduzido()) ;
		} catch (Exception e) {
			return new BigDecimal("0");
		}
		
	}

	public BigDecimal getFeeReduzido() {
		
		try {
			feeReduzido = getFeeReduzidoNovoCalculo();
			getFeeReduzidoNovoCalculo();
			return feeReduzido;
			
		} catch (Exception e) {
			return new BigDecimal("0");
		}
		
	}
	public BigDecimal getFeeReduzidoNovoCalculo() {
		BigDecimal totalItensSemImpostoFeeReduzido = new BigDecimal("0");
		BigDecimal apoioSoma = new BigDecimal("0");
		
		try {
			BigDecimal divideFeeReduzido = listaDeProducaoP.get(0).getProdutoGrupo().getIdGrupo().getIdLista().getFeeReduzido().divide(
					new BigDecimal(100),12,RoundingMode.UP);
			
			
			for (int i = 0; i < listaDeProducaoP.size(); i++) {
				if (listaDeProducaoP.get(i).getProdutoGrupo().isImposto() == false
					 && listaDeProducaoP.get(i).getProdutoGrupo().getIdGrupo().isFeeReduzido() == true) {
					
					double quant = listaDeProducaoP.get(i).getProdutoGrupo().getQuantidade();
					double quant2 = listaDeProducaoP.get(i).getProdutoGrupo().getQuantidade2();
					double diaria = listaDeProducaoP.get(i).getProdutoGrupo().getDiarias();
					double quantTotal = quant*quant2*diaria;
					apoioSoma = apoioSoma.add(listaDeProducaoP.get(i).getProdutoGrupo().getPrecoProduto().multiply
											  (new BigDecimal(quantTotal))) ;
				}
			}
			totalItensSemImpostoFeeReduzido = apoioSoma.multiply(divideFeeReduzido);
			
			return totalItensSemImpostoFeeReduzido;
		} catch (Exception e) {
			return new BigDecimal("0");
		}
	}
	
	
	
	public BigDecimal getsubLoCCo() {
		return subLoCCo = calculaSubLoCCo();
	}

	public BigDecimal calculaSubLoCCo() {
		BigDecimal somaSubLocco = new BigDecimal("0");
		for (int i = 0; i < listaDeProducaoP.size(); i++) {
			if(listaDeProducaoP.get(i).getProdutoGrupo().isImposto() == true && listaDeProducaoP.get(i).getProdutoGrupo().getIdGrupo().isIncideAdministracao() ==  false)
				somaSubLocco = somaSubLocco.add(calculaSubLocco(i));
		}			
		return somaSubLocco;
	}

	
	public BigDecimal getsubContratados(){
		return subContratados = calculaSubContratados();
	}	
		
	public BigDecimal calculaSubContratados(){
		BigDecimal somaSubContratados = new BigDecimal("0");
		for (int i = 0; i < listaDeProducaoP.size(); i++) {
			if(listaDeProducaoP.get(i).getProdutoGrupo().isImposto() == true && listaDeProducaoP.get(i).getProdutoGrupo().getIdGrupo().isIncideAdministracao() ==  true)
				somaSubContratados  = somaSubContratados.add(calculaSubLocco(i));
		}			
		return somaSubContratados;
	}
	
	
	
	
	
	private BigDecimal calculaSubLocco(int _i){
		BigDecimal calculoTotal = new BigDecimal("0");
		double quant;
			  BigDecimal precoPorQuantidades = new BigDecimal("0");
			  BigDecimal preco = new BigDecimal("0");
			  quant = 0;
			  preco = preco.add(listaDeProducaoP.get(_i).getProdutoGrupo().getPrecoProduto());
			  quant = multiplicaQuantPorDiarias(listaDeProducaoP.get(_i).getProdutoGrupo().getQuantidade(),
					  listaDeProducaoP.get(_i).getProdutoGrupo().getQuantidade2(),
					  listaDeProducaoP.get(_i).getProdutoGrupo().getDiarias());
			  precoPorQuantidades = precoPorQuantidades.add(preco).multiply(new BigDecimal(quant));
			  calculoTotal = calculoTotal.add(precoPorQuantidades);
		return calculoTotal;
	}

	public BigDecimal getSubTotalGeral() {
		List<BigDecimal> totalFeeLoccoEdireto = new ArrayList<BigDecimal>();
		
		totalFeeLoccoEdireto.add(getFeeGeral());
		totalFeeLoccoEdireto.add(getFeeReduzido());
		totalFeeLoccoEdireto.add(calculaSubContratados());
		totalFeeLoccoEdireto.add(calculaSubLoCCo());
		

		subTotalGeral = somaListaDeValores(totalFeeLoccoEdireto);

		return subTotalGeral;
	}

	public BigDecimal getTotal1LoCCO() {
		total1LoCCO = getSubTotalGeral();
		total1LoCCO = total1LoCCO.add(getImpostoListaPlanilha());
		return total1LoCCO;
	}
	
	public BigDecimal getImpostoListaPlanilha() {
		BigDecimal calculaImposto = subTotalGeral.divide(new BigDecimal("0.771"),12,RoundingMode.UP);
		BigDecimal calculaImposto2 = calculaImposto.subtract(subTotalGeral);
		return impostoListaPlanilha = calculaImposto2;
	}
	

	
	
	
// ------------------------------------------------------------------------------------------------ //
	/*
	 * Calculos da tabela de Faturamento
	 * 
	 */
	
	
	public BigDecimal getSubTotalGeralTabela() { //ok
		
		List<BigDecimal> totalFeeLoccoEdireto = new ArrayList<BigDecimal>();
		
		totalFeeLoccoEdireto.add(getFeeGeral());
		totalFeeLoccoEdireto.add(calculaSubContratados());
		totalFeeLoccoEdireto.add(calculaSubLoCCo());
		
		subTotalGeralTabela = somaListaDeValores(totalFeeLoccoEdireto);
		
		return subTotalGeralTabela;
	}
	
	
	public BigDecimal getTotalNF1() {
		totalNF1 = getSubTotalGeralTabela();
		totalNF1 = totalNF1.add(getImpostoListatabela());
		return totalNF1;
	}
	
	
	
	public BigDecimal getImpostoListatabela() {
		BigDecimal calculaImposto = subTotalGeralTabela.divide(new BigDecimal("0.771"),12,RoundingMode.UP);
		BigDecimal calculaImposto2 = calculaImposto.subtract(subTotalGeralTabela);
		return impostoListatabela = calculaImposto2;
	}
	

	
	public BigDecimal getImpostoValorFornecedor() {
		return impostoValorFornecedor = getTotal1LoCCO().multiply(new BigDecimal((impostoDaInterna/100))); 
	}

	public BigDecimal getImpostoDiferenca() {
		return impostoDiferenca = subtraiDoisValores(impostoLista,impostoValorFornecedor);
	}

	public BigDecimal getTotal1Diferenca() {
		try {
			total1Diferenca = total1Diferenca.add(subDiferenca);
			/*total1Diferenca = total1Diferenca.add(despesasDiferenca);*/
			total1Diferenca = total1Diferenca.add(feeGeral);
			total1Diferenca = total1Diferenca.add(impostoDiferenca);
		} catch (Exception e) {
			System.out.println(e);
		}
		return total1Diferenca;
	}

	public BigDecimal getSubDiferenca() {
		List<BigDecimal> valoresDeDiferencaELoCCoAgencia = new ArrayList<BigDecimal>();
		BigDecimal somaValores;
		for (int i = 0; i < listaDeProducaoP.size(); i++) {
			somaValores = listaDeProducaoP.get(i).getDiferenca();
			if(listaDeProducaoP.get(i).getIdEmpFornecedor().getIdEmpresa() == 6961)	
				somaValores = somaValores.add(listaDeProducaoP.get(i).getValorItem());
			valoresDeDiferencaELoCCoAgencia.add(somaValores);
		}
		subDiferenca = somaListaDeValores(valoresDeDiferencaELoCCoAgencia);
		return subDiferenca;
	}
	
	
	
	public BigDecimal getSubValorNf() {
		BigDecimal valor = new BigDecimal("0");
		for (int i = 0; i < listaDeProducaoP.size(); i++) {
			valor = valor.add(listaDeProducaoP.get(i).getValorItem());
		}	
		subValorNf = valor;
		return subValorNf;
	}

	public BigDecimal getSubValorFornecedor() {
		BigDecimal valorNF = new BigDecimal("0");
		for (int i = 0; i < listaDeProducaoP.size(); i++) {
			if(listaDeProducaoP.get(i).getIdEmpFornecedor().getIdEmpresa() != 6961)
			valorNF = valorNF.add(listaDeProducaoP.get(i).getValorItem()).subtract(listaDeProducaoP.get(i).getDiferenca());
		}
		subValorFornecedor = valorNF;
		return subValorFornecedor;
	}
	
	public BigDecimal getTotal2() {
		try {
			total2 = total2.add(total1LoCCO);
		} catch (Exception e) {
		}
		return total2;
	}
	
	public BigDecimal getPrevExtraSubContrat() {
		prevExtraSubContrat = subContratados.multiply(prevExtras);
		return prevExtraSubContrat;
	}


	public BigDecimal getFeeFatNF2() {
		BigDecimal prevExtras = new BigDecimal("0.14");
		feeFatNF2 = prevExtraSubContrat.multiply(prevExtras);
		return feeFatNF2;
	}


	public BigDecimal getPrevExtraCustosInternos() {
		prevExtraCustosInternos = getsubLoCCo().multiply(prevExtras);
		return prevExtraCustosInternos;
	}


	public BigDecimal getSubTotalFatNF2() {
		subTotalFatNF2 = prevExtraSubContrat.add(feeFatNF2).add(prevExtraCustosInternos);
		return subTotalFatNF2;
	}


	public BigDecimal getImpostoFatNF2() {
		BigDecimal imposto  = new BigDecimal("22.9");
		impostoFatNF2 =  calculoImpostoPorFora(imposto,subTotalFatNF2);
		return impostoFatNF2;
	}


	public BigDecimal getTotalFatNF2() {
		totalFatNF2 = somaDoisValores(subTotalFatNF2, impostoFatNF2);
		return totalFatNF2;
	}


	public BigDecimal getTotalEvento() {
		totalEvento = totalNF1.add(totalFatNF2);
		return totalEvento;
	}
	
}
