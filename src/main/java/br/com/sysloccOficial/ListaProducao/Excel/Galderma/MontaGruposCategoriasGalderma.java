package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.ListaProducao.Excel.ChainVerificaCategoriaImposto;
import br.com.sysloccOficial.controllerExcel.AuxExcelSQL;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.CenariosGalderma;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.Lista;


@Component
@Transactional
public class MontaGruposCategoriasGalderma{
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private static AuxExcelSQL sql;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@Autowired private AuxCarregaGrupos montaGrupos;
	
	
	
	public List<Integer> pegaIdsCenarios(Integer idLista){
		
		List<Integer> listaIds = new ArrayList<Integer>();
		
		//Verifica se tem referência em filha 
        try {
        	String consulta = "select planilhaMae from CenariosGalderma where planilhaFilha = " +idLista;
        	TypedQuery<Integer> idMae = manager.createQuery(consulta, Integer.class);
        	Integer idListaMae = idMae.getSingleResult();
        	preencheListaComIds(listaIds, idListaMae);
        	return listaIds;
  
		} catch (Exception e) {
			try {
				String consultaMae = "select planilhaMae from CenariosGalderma where planilhaMae = "+ idLista;
				TypedQuery<Integer> idMae = manager.createQuery(consultaMae, Integer.class);
	        	Integer idListaMae = idMae.getSingleResult();
	        	preencheListaComIds(listaIds, idListaMae);
	        	return listaIds;
	        	
			} catch (Exception e2) {
				return null;
			}
		}
	}




	private void preencheListaComIds(List<Integer> listaIds, Integer idListaMae) {
		String cunsultaFilhas = "select planilhaFilha from CenariosGalderma where planilhaMae=" + idListaMae + "order by cenarioFilha";
		TypedQuery<Integer> idsFilhas = manager.createQuery(cunsultaFilhas, Integer.class);
		List<Integer> listaIdsFilhas = idsFilhas.getResultList();
		
		listaIds.add(idListaMae);
		
		for (int i = 0; i < listaIdsFilhas.size(); i++) {
			listaIds.add(listaIdsFilhas.get(i));
		}
	}
	
	
	
	
	public List<Grupo> listaGruposNAOOpcionais(Integer idLista){
		List<Grupo> listaGrupos = montaGrupos.listaGruposNAOOpcionais(2424);;
		return listaGrupos;
	}

	public List<Grupo> listaGruposOpcionais(Integer idLista){
		List<Grupo> listaGrupos = sql.retornaGruposOpcionaisGalderma(idLista);
		return listaGrupos;
	}
	
	public List<CorpoGrupoCategoriaGalderma> montaGruposParaExcel(List<Grupo> listaGrupos){
		
		/*List<Grupo> listaGrupos = null;
		
		if(opcionais == false){
			listaGrupos = listaGruposNAOOpcionais(idLista);
		}else{
			listaGrupos = listaGruposOpcionais(idLista);
		}*/
		
		List<CorpoGrupoCategoriaGalderma> corpoGrupos = new ArrayList<CorpoGrupoCategoriaGalderma>();
		
		for (int i = 0; i < listaGrupos.size(); i++) {
			
			if(listaGrupos.get(i).getProdutoGrupo().size() == 0){
				
			}else{
				
				
				BigDecimal zero = new BigDecimal("0.00");
				BigDecimal comImposto = new BigDecimal("0.00");
				BigDecimal comImpostoUnico = new BigDecimal("0.00");
				BigDecimal semImposto = new BigDecimal("0.00");
				BigDecimal semImpostoUnico = new BigDecimal("0.00");
				double qtdcomImposto = 0;
				double diariacomImposto = 0;

				double qtdsemImposto = 0;
				double diariasemImposto = 0;
				
				double qtdUnica = 0;
				double diariaUnica = 0;
				
				BigDecimal orcamComImposto = new BigDecimal("0");
				BigDecimal orcamSemImposto = new BigDecimal("0");
				

				if(listaGrupos.get(i).getDetermPadrao() != null){
					qtdUnica = listaGrupos.get(i).getDetermPadrao().getQuantDetermPadrao();
					diariaUnica = listaGrupos.get(i).getDetermPadrao().getDiariasPadrao();
				}else{
					qtdUnica = listaGrupos.get(i).getDeterm().getQuantDeterm();
					diariaUnica = listaGrupos.get(i).getDeterm().getDiarias();
				}

				// Pegar id de ProdutoGrupo com Imposto
				
				double qtdFinal = qtdUnica * diariaUnica;
				
				orcamComImposto = pegaOrcamentos(listaGrupos, i,1,qtdFinal);
				orcamSemImposto = pegaOrcamentos(listaGrupos, i,0,qtdFinal);
				
				for (int j = 0; j < listaGrupos.get(i).getProdutoGrupo().size(); j++) {
					
					
	// ------------------------------------------------------------------------------------------------------------------------ //
	 				  if(listaGrupos.get(i).getProdutoGrupo().get(j).isImposto() == true){
							if(comImposto.equals(zero)){
								
								BigDecimal preco = new BigDecimal("0.00");
								
								qtdcomImposto = listaGrupos.get(i).getProdutoGrupo().get(j).getQuantidade()*listaGrupos.get(i).getProdutoGrupo().get(j).getQuantidade2()*listaGrupos.get(i).getProdutoGrupo().get(j).getDiarias();
								diariacomImposto = listaGrupos.get(i).getProdutoGrupo().get(j).getDiarias();
								preco = preco.add(listaGrupos.get(i).getProdutoGrupo().get(j).getPrecoProduto());
								comImpostoUnico = comImpostoUnico.add(preco.multiply(new BigDecimal(diariacomImposto)).multiply(new BigDecimal(qtdcomImposto)));
								
							}
						}
	// ------------------------------------------------------------------------------------------------------------------------ //
						if(listaGrupos.get(i).getProdutoGrupo().get(j).isImposto() != true){
								if(semImposto.equals(zero)){
									
									BigDecimal precoSemImposto = new BigDecimal("0.00");
									
									qtdsemImposto = listaGrupos.get(i).getProdutoGrupo().get(j).getQuantidade()*listaGrupos.get(i).getProdutoGrupo().get(j).getQuantidade2()*listaGrupos.get(i).getProdutoGrupo().get(j).getDiarias();
									diariasemImposto = listaGrupos.get(i).getProdutoGrupo().get(j).getDiarias();
									precoSemImposto = precoSemImposto.add(listaGrupos.get(i).getProdutoGrupo().get(j).getPrecoProduto());
									semImpostoUnico = semImpostoUnico.add(precoSemImposto.multiply(new BigDecimal(diariasemImposto)).multiply(new BigDecimal(qtdsemImposto)));
								}
					    }
			     }
	// ------------------------------------------------------------------------------------------------------------------------ //
					
				   valoresEmCadaItem(listaGrupos, corpoGrupos, i, zero, comImposto,semImposto, qtdcomImposto,
						          diariacomImposto, qtdsemImposto,diariasemImposto,
						          listaGrupos.get(i).isIncideAdministracao(), listaGrupos.get(i).isFeeReduzido(),orcamComImposto,orcamSemImposto,
						          qtdUnica,diariaUnica,comImpostoUnico, semImpostoUnico);
			}
		}
		return corpoGrupos;
	}
	

	private BigDecimal pegaOrcamentos(List<Grupo> listaGrupos, int i,int imposto,double qtdFinal) {
		BigDecimal orcamento;
		BigDecimal nulo = null; 

		try {
			
			List<Integer> idsProdutoGrupos = pegaidsProdutoGrupos(listaGrupos, i, imposto);
			
			if(idsProdutoGrupos == null || idsProdutoGrupos.isEmpty()){
				return null;
			}else{
				BigDecimal vOrcComImpot = pegaSomaOrcamentosProdutoGrupos(idsProdutoGrupos);
				if(vOrcComImpot == nulo){
					return new BigDecimal("0");
				}else{
					orcamento = vOrcComImpot;
					orcamento = orcamento.divide(new BigDecimal(qtdFinal),12,RoundingMode.UP);
					return orcamento;
				}
			}
		
		} catch (Exception e) {
			return new BigDecimal("0.00");
		}
	}
	
	private List<Integer> pegaidsProdutoGrupos(List<Grupo> listaGrupos,int i,int imposto){
		
		try {
			//Pega id do produto Grupo baseado se tem imposto ou não
			String consultaComImposto = "select idProdutoGrupo FROM ProdutoGrupo where idGrupo = "+listaGrupos.get(i).getIdgrupo()+" and imposto = "+imposto;
			String cc = consultaComImposto.replace("[", "").replace("]", "");
			TypedQuery<Integer> idProd = manager.createQuery(cc,Integer.class);
			return idProd.getResultList();
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	private BigDecimal pegaSomaOrcamentosProdutoGrupos(List<Integer> idsProdutoGrupos){
		
		try {
			//Pega a soma dos orçamentos dos produtos grupos desses orçamento.
			String consultaOrcamComImposto = "select sum(valorOrcamento) FROM OrcamentoFornecedor where produto in("+idsProdutoGrupos+")";
			String ccs = consultaOrcamComImposto.replace("[", "").replace("]", "");
			TypedQuery<BigDecimal> vOrcComImpot = manager.createQuery(ccs, BigDecimal.class);
			
			return vOrcComImpot.getSingleResult();
			
		} catch (Exception e) {
			return null;
		}
		
	
	}

	private void valoresEmCadaItem(List<Grupo> listaGrupos,
			List<CorpoGrupoCategoriaGalderma> corpoGrupos, int i, BigDecimal zero,
			BigDecimal comImposto, BigDecimal semImposto, double qtdcomImposto,
			double diariacomImposto, double qtdsemImposto,
			double diariasemImposto, boolean incideAdministracao, boolean feeReduzido,
			BigDecimal orcamentoComImposto,BigDecimal orcamentoSemImposto, double qtdUnica, double diariaUnica, BigDecimal comImpostoUnico, BigDecimal semImpostoUnico) {
			
		BigDecimal quantFinal = new BigDecimal(qtdUnica*diariaUnica);
		
		BigDecimal precoImpostoPorquantidade = comImpostoUnico; 
		BigDecimal precoImpostoFinal = precoImpostoPorquantidade.divide(quantFinal ,12,RoundingMode.UP); 
		
		BigDecimal precoSemImpostoPorquantidade = semImpostoUnico; 
		BigDecimal precoSemImpostoFinal = precoSemImpostoPorquantidade.divide(quantFinal ,12,RoundingMode.UP); 
		
	    // ---------
			
		if(comImpostoUnico.equals(zero)){
		}else{
			CorpoGrupoCategoriaGalderma corpoGrupoGalderma = new CorpoGrupoCategoriaGalderma();
			
			/**
			 * Setas as categorias
			 */
			if(listaGrupos.get(i).getGrupoCategoriaGalderma().getIdCategoriaGalderma() < 1){
				corpoGrupoGalderma.setIdCategoriaGalderma(1);
			}else{
				corpoGrupoGalderma.setIdCategoriaGalderma(listaGrupos.get(i).getGrupoCategoriaGalderma().getIdCategoriaGalderma());
			}
			
			corpoGrupoGalderma.setIdGrupo(listaGrupos.get(i).getIdgrupo());
			corpoGrupoGalderma.setInfoGrupo(listaGrupos.get(i).getInformacoes());
			corpoGrupoGalderma.setTemImposto(true);
			
			corpoGrupoGalderma.setPrecoItem(precoImpostoFinal);
			
			if(orcamentoComImposto.equals(zero)){
				corpoGrupoGalderma.setOrcamento(comImpostoUnico.divide(quantFinal ,12,RoundingMode.UP));
			}else{
				corpoGrupoGalderma.setOrcamento(orcamentoComImposto);
			}
			corpoGrupoGalderma.setQuantidade(qtdUnica);
			corpoGrupoGalderma.setDiaria(diariaUnica);
			
			corpoGrupoGalderma.setTipoServico(categoriasimpostoBayer(corpoGrupoGalderma.isTemImposto(),incideAdministracao,feeReduzido));
			
			corpoGrupos.add(corpoGrupoGalderma);
		}
		if(semImpostoUnico.equals(zero)){
		}else{	
			CorpoGrupoCategoriaGalderma corpoGrupoBayerSemImposto = new CorpoGrupoCategoriaGalderma();
			
			if(listaGrupos.get(i).getGrupoCategoriaGalderma() == null){
				corpoGrupoBayerSemImposto.setIdCategoriaGalderma(1);
			}else{
				corpoGrupoBayerSemImposto.setIdCategoriaGalderma(listaGrupos.get(i).getGrupoCategoriaGalderma().getIdCategoriaGalderma());
			}
			
			corpoGrupoBayerSemImposto.setIdGrupo(listaGrupos.get(i).getIdgrupo());
			corpoGrupoBayerSemImposto.setInfoGrupo(listaGrupos.get(i).getInformacoes());
			corpoGrupoBayerSemImposto.setTemImposto(false);
			
			corpoGrupoBayerSemImposto.setPrecoItem(precoSemImpostoFinal);
			
			if(orcamentoSemImposto.equals(zero)){
				corpoGrupoBayerSemImposto.setOrcamento(precoSemImpostoFinal);
			}else{
				corpoGrupoBayerSemImposto.setOrcamento(orcamentoSemImposto);
			}
			corpoGrupoBayerSemImposto.setQuantidade(qtdUnica);
			corpoGrupoBayerSemImposto.setDiaria(diariaUnica);
			
			corpoGrupoBayerSemImposto.setTipoServico(categoriasimpostoBayer(corpoGrupoBayerSemImposto.isTemImposto(),incideAdministracao,feeReduzido));
			corpoGrupos.add(corpoGrupoBayerSemImposto);
		}
		
	}
	
	public String categoriasimpostoBayer(boolean imposto,boolean incideAdministracao,boolean feeReduzido){
		ChainVerificaCategoriaImposto verificaTipoDespesa  = new ChainVerificaCategoriaImposto();
		String tipoCategoriaImposto =  verificaTipoDespesa.verifica(imposto,incideAdministracao,feeReduzido);
		return tipoCategoriaImposto;
	}

	
	
	
	
	
	
}
