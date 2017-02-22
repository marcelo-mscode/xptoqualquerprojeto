package br.com.sysloccOficial.ListaProducao.Excel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.apache.poi.hslf.exceptions.CorruptPowerPointFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.controllerExcel.AuxExcelSQL;
import br.com.sysloccOficial.controllerExcel.CorpoGrupos;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.financeiro.indexlistainternaindividual.VerificaTipoDespesa;
import br.com.sysloccOficial.model.DeterQuantpadrao;
import br.com.sysloccOficial.model.DeterminaQuantidades;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.OrcamentoFornecedor;


@Component
public class GeraExcelNovoBayerGrupos {

	@Autowired private AuxExcelSQL sql;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@PersistenceContext	private EntityManager manager;
	
	
	public List<Grupo> listaGruposNAOOpcionais(Integer idLista){
		List<Grupo> listaGrupos = sql.retornaGrupos(idLista);
		return listaGrupos;
	}

	public List<Grupo> listaGruposOpcionais(Integer idLista){
		List<Grupo> listaGrupos = sql.retornaGruposOpcionais(idLista);
		return listaGrupos;
	}
	
	public List<CorpoGrupoCategoriaBayer> montaGruposParaExcel(Integer idLista, boolean opcionais){
		
		List<Grupo> listaGrupos = null;
		
		if(opcionais == false){
			listaGrupos = listaGruposNAOOpcionais(idLista);
		}else{
			listaGrupos = listaGruposOpcionais(idLista);
		}
		
		List<CorpoGrupoCategoriaBayer> corpoGrupos = new ArrayList<CorpoGrupoCategoriaBayer>();
		
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
								
								//Multiplica qtd1 * qts2 * diarias
								qtdcomImposto = listaGrupos.get(i).getProdutoGrupo().get(j).getQuantidade()*listaGrupos.get(i).getProdutoGrupo().get(j).getQuantidade2()*listaGrupos.get(i).getProdutoGrupo().get(j).getDiarias();
								//Pega diarias
								diariacomImposto = listaGrupos.get(i).getProdutoGrupo().get(j).getDiarias();
								//Pega preço do produto
								preco = preco.add(listaGrupos.get(i).getProdutoGrupo().get(j).getPrecoProduto());
								
								comImpostoUnico = comImpostoUnico.add(preco.multiply(new BigDecimal(qtdcomImposto)));
								
								/*if(listaGrupos.get(i).getIdgrupo() == 77049){
									System.out.println(listaGrupos.get(j).getIdgrupo());
									JOptionPane.showMessageDialog(null, "Preço: " +preco+"\nQtd: "+qtdcomImposto+"\ndiariacomImposto: "+diariacomImposto+"\nPreço total: "+comImpostoUnico);
								}*/

							}
						}
	// ------------------------------------------------------------------------------------------------------------------------ //
						if(listaGrupos.get(i).getProdutoGrupo().get(j).isImposto() != true){
								if(semImposto.equals(zero)){
									
									BigDecimal precoSemImposto = new BigDecimal("0.00");
									
									qtdsemImposto = listaGrupos.get(i).getProdutoGrupo().get(j).getQuantidade()*listaGrupos.get(i).getProdutoGrupo().get(j).getQuantidade2()*listaGrupos.get(i).getProdutoGrupo().get(j).getDiarias();
									diariasemImposto = listaGrupos.get(i).getProdutoGrupo().get(j).getDiarias();
									precoSemImposto = precoSemImposto.add(listaGrupos.get(i).getProdutoGrupo().get(j).getPrecoProduto());
									semImpostoUnico = semImpostoUnico.add(precoSemImposto.multiply(new BigDecimal(qtdsemImposto)));
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
		try {
			String consultaComImposto = "select idProdutoGrupo FROM ProdutoGrupo where idGrupo = "+listaGrupos.get(i).getIdgrupo()+" and imposto = "+imposto;
			String cc = consultaComImposto.replace("[", "").replace("]", "");
			
			TypedQuery<Integer> idProd = manager.createQuery(cc,Integer.class);
			List<Integer> idsProdutoGrupos = idProd.getResultList();
			
			String consultaOrcamComImposto = "select sum(valorOrcamento) FROM OrcamentoFornecedor where produto in("+idsProdutoGrupos+")";
			String ccs = consultaOrcamComImposto.replace("[", "").replace("]", "");
			TypedQuery<BigDecimal> vOrcComImpot = manager.createQuery(ccs, BigDecimal.class);
			orcamento = vOrcComImpot.getSingleResult();
			
			orcamento = orcamento.divide(new BigDecimal(qtdFinal),12,RoundingMode.UP);
			
			if(orcamento.equals(null)){
				return new BigDecimal("0");
			}else{
				return orcamento;
				
			}
		} catch (Exception e) {
			return new BigDecimal("0.00");
		}
	}

	private void valoresEmCadaItem(List<Grupo> listaGrupos,
			List<CorpoGrupoCategoriaBayer> corpoGrupos, int i, BigDecimal zero,
			BigDecimal comImposto, BigDecimal semImposto, double qtdcomImposto,
			double diariacomImposto, double qtdsemImposto,
			double diariasemImposto, boolean incideAdministracao, boolean feeReduzido,
			BigDecimal orcamentoComImposto,BigDecimal orcamentoSemImposto, double qtdUnica, double diariaUnica, BigDecimal comImpostoUnico, BigDecimal semImpostoUnico) {
			
		BigDecimal quantFinal = new BigDecimal(qtdUnica*diariaUnica);
		
		BigDecimal precoImpostoPorquantidade = comImpostoUnico; 
//		BigDecimal precoImpostoPorquantidade = comImpostoUnico.multiply(new BigDecimal(qtdcomImposto)); 
		BigDecimal precoImpostoFinal = precoImpostoPorquantidade.divide(quantFinal ,12,RoundingMode.UP); 
		
	
		
		BigDecimal precoSemImpostoPorquantidade = semImpostoUnico; 
		//BigDecimal precoSemImpostoPorquantidade = semImpostoUnico.multiply(new BigDecimal(qtdsemImposto)); 
		BigDecimal precoSemImpostoFinal = precoSemImpostoPorquantidade.divide(quantFinal ,12,RoundingMode.UP); 
		
	    // ---------
			
		if(comImpostoUnico.equals(zero)){
		
		}else{
			CorpoGrupoCategoriaBayer corpoGrupoBayer = new CorpoGrupoCategoriaBayer();

			if(listaGrupos.get(i).getGrupoCategoriaBayer() == null){
				corpoGrupoBayer.setIdCategoriaBayer(1);
			}else{
				corpoGrupoBayer.setIdCategoriaBayer(listaGrupos.get(i).getGrupoCategoriaBayer().getIdGrupoCategoria());
			}
			
			corpoGrupoBayer.setIdGrupo(listaGrupos.get(i).getIdgrupo());
			corpoGrupoBayer.setInfoGrupo(listaGrupos.get(i).getInformacoes());
			corpoGrupoBayer.setTemImposto(true);
			
			corpoGrupoBayer.setPrecoItem(precoImpostoFinal);
			
			if(orcamentoComImposto.equals(zero)){
				corpoGrupoBayer.setOrcamento(comImpostoUnico.divide(quantFinal ,12,RoundingMode.UP));
			}else{
				corpoGrupoBayer.setOrcamento(orcamentoComImposto);
			}
			corpoGrupoBayer.setQuantidade(qtdUnica);
			corpoGrupoBayer.setDiaria(diariaUnica);
			
			corpoGrupoBayer.setTipoServico(categoriasimpostoBayer(corpoGrupoBayer.isTemImposto(),incideAdministracao,feeReduzido));
			
			corpoGrupos.add(corpoGrupoBayer);
		}
		if(semImpostoUnico.equals(zero)){
			
		}else{	
			CorpoGrupoCategoriaBayer corpoGrupoBayerSemImposto = new CorpoGrupoCategoriaBayer();
			
			if(listaGrupos.get(i).getGrupoCategoriaBayer() == null){
				corpoGrupoBayerSemImposto.setIdCategoriaBayer(1);
			}else{
				corpoGrupoBayerSemImposto.setIdCategoriaBayer(listaGrupos.get(i).getGrupoCategoriaBayer().getIdGrupoCategoria());
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
