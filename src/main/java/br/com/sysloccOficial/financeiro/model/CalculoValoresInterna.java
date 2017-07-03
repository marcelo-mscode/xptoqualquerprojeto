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

import br.com.sysloccOficial.financeiro.calculadora.Calculadora;
import br.com.sysloccOficial.financeiro.dao.InternaIndividualDAO;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Component
public class CalculoValoresInterna extends Calculadora{
	
	@Autowired private InternaIndividualDAO internaIndividualDAO;
	@PersistenceContext	private EntityManager manager;
	
	private List<ProducaoP> listaDeProducaoP;
//	private Integer idLista;
	private BigDecimal impostoLista;
	
	public CalculoValoresInterna(List<ProducaoP> _listaDeProducaoP, Integer _idLista, BigDecimal _impostoLista) {
		this.listaDeProducaoP = _listaDeProducaoP;
//		this.idLista = _idLista;
		this.impostoLista = _impostoLista;
	}
	
	

	private BigDecimal subLoCCo = new BigDecimal("0.00");
	private BigDecimal subDireto = new BigDecimal("0.00");
	private BigDecimal subDiferenca;
	private BigDecimal feeGeral = new BigDecimal("0.00");
	private BigDecimal subTotalGeral;
	private BigDecimal impostoValorFornecedor;
	private BigDecimal impostoDiferenca;
	private BigDecimal total1Diferenca = new BigDecimal("0.00");
	private BigDecimal total2 = new BigDecimal("0.00");
	private BigDecimal total1LoCCO;
	private BigDecimal subValorFornecedor;	
	private BigDecimal subValorNf;
	
	private BigDecimal prevExtraSubContrat= new BigDecimal("0.00");
	private BigDecimal prevExtraCustosInternos = new BigDecimal("0.00");
	private BigDecimal feeFatNF2= new BigDecimal("0.00");
	private BigDecimal subTotalFatNF2= new BigDecimal("0.00");
	private BigDecimal impostoFatNF2= new BigDecimal("0.00");
	private BigDecimal totalFatNF2= new BigDecimal("0.00");
	private BigDecimal totalEvento= new BigDecimal("0.00");
	
	
	
	BigDecimal prevExtras = new BigDecimal("0.1");
	
	
//	private BigDecimal feeLoCCO;
//	private BigDecimal feeDireto; 
//	private BigDecimal feeValorFornecedor;	
//	private BigDecimal feeValorNf;	
//	private BigDecimal feeDiferenca;
//	private BigDecimal DespesasLoCCo;
//	private BigDecimal DespesasDireto;
//	private BigDecimal DespesasValorFornecedor;
//	private BigDecimal DespesasValorNf;
//	private BigDecimal DespesasDiferenca;
//	private BigDecimal impostoLoCCo;
//	private BigDecimal impostoValorNf;
//	private BigDecimal impostoParaFormula;
//	private BigDecimal Total1Direto;



	
	public BigDecimal getSubDireto() {
		for (int i = 0; i < listaDeProducaoP.size(); i++) {
			if(listaDeProducaoP.get(i).getProdutoGrupo().isImposto() == false)	
				subDireto = calculaSubTotais(listaDeProducaoP.get(i).getProdutoGrupo().isImposto());
		}
		return subDireto;
	}

	public BigDecimal getFeeGeral() {
			feeGeral = listaDeProducaoP.get(0).getProdutoGrupo().getIdGrupo().getIdLista().getAdministracaoValor();
	    return feeGeral;
	}

	public BigDecimal getsubLoCCo() {
		for (int i = 0; i < listaDeProducaoP.size(); i++) {
			if(listaDeProducaoP.get(i).getProdutoGrupo().isImposto() == true && listaDeProducaoP.get(i).getProdutoGrupo().getIdGrupo().isIncideAdministracao() ==  false)
				subLoCCo = calculaSubTotais(listaDeProducaoP.get(i).getProdutoGrupo().isImposto());
			// JOptionPane.showMessageDialog(null, "SubLocco: "+subLoCCo);
		}			
		return subLoCCo;
	}

	private BigDecimal calculaSubTotais(boolean imposto){
		
		BigDecimal calculoTotal = new BigDecimal("0");
		
		double quant;
		for (int i = 0; i < listaDeProducaoP.size(); i++) {
		  if(listaDeProducaoP.get(i).getProdutoGrupo().isImposto() == imposto){
			  BigDecimal precoPorQuantidades = new BigDecimal("0");
			  BigDecimal preco = new BigDecimal("0");
			  quant = 0;
			  preco = preco.add(listaDeProducaoP.get(i).getProdutoGrupo().getPrecoProduto());
			  quant = multiplicaQuantPorDiarias(listaDeProducaoP.get(i).getProdutoGrupo().getQuantidade(),
					  listaDeProducaoP.get(i).getProdutoGrupo().getQuantidade2(),
					  listaDeProducaoP.get(i).getProdutoGrupo().getDiarias());
			  precoPorQuantidades = precoPorQuantidades.add(preco).multiply(new BigDecimal(quant));
			  calculoTotal = calculoTotal.add(precoPorQuantidades);
		  }
		}
		return calculoTotal;
	}

	public BigDecimal getSubTotalGeral() {
		List<BigDecimal> totalFeeLoccoEdireto = new ArrayList<BigDecimal>();
		totalFeeLoccoEdireto.add(getFeeGeral());
		totalFeeLoccoEdireto.add(getSubDireto());
		totalFeeLoccoEdireto.add(getsubLoCCo());
		subTotalGeral = somaListaDeValores(totalFeeLoccoEdireto);
		return subTotalGeral;
	}

	
	public BigDecimal getTotal1LoCCO() {
		total1LoCCO = subTotalGeral.add(impostoLista);
		return total1LoCCO;
	}
	
	public BigDecimal getImpostoValorFornecedor() {
		return impostoValorFornecedor = getTotal1LoCCO().multiply(new BigDecimal("0.155")); 
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
		prevExtraSubContrat = getSubDireto().multiply(prevExtras);
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
		totalEvento = total1LoCCO.add(totalFatNF2);
		return totalEvento;
	}
	
}
