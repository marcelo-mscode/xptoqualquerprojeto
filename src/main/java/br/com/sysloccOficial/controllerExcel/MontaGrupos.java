package br.com.sysloccOficial.controllerExcel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.DeterQuantpadrao;
import br.com.sysloccOficial.model.DeterminaQuantidades;
import br.com.sysloccOficial.model.Grupo;

public class MontaGrupos {
	
	@Autowired private AuxExcelSQL sql;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	
	
	/**
	 * Método para montar os Grupos de Cada Categoria 
	 * 
	 * @param idLista
	 * @return List<CorpoGrupos>
	 */
	public List<CorpoGrupos> montaGrupo(Integer idLista){
		
		// Monta a lista de Grupos de Cada Categoria
		List<Grupo> listaGrupos = sql.retornaGrupos(idLista);
		List<CorpoGrupos> corpoGrupos = new ArrayList<CorpoGrupos>();
	 	
		for(int j =0; j<listaGrupos.size();j++){

			BigDecimal zero = new BigDecimal("0");
			/*
			BigDecimal p = new BigDecimal("0");
			BigDecimal t = new BigDecimal("0");
			double quantDeter = 0;
			*/
			DeterminaQuantidades det =  produtoGrupoDAO.quantDeterm(listaGrupos.get(j).getIdgrupo());
			DeterQuantpadrao detPad  = produtoGrupoDAO.deterQuantPadrao(listaGrupos.get(j).getIdgrupo());
			
			// Pegar da tabela Aux padraoDeterminante pelo idGrupo
				
			// DetQuantPadrao detPad = produtoGrupoDAO.quantDetPadrao(listaGrupos.get(j).getIdgrupo());
			
			
	 		BigDecimal custoAgencia = new BigDecimal("0");
	 		BigDecimal subContratado = new BigDecimal("0");
	 		
	 		// Verifica se a linha tera custo de Agência ou Custo de SubContratado  
	 		if(listaGrupos.get(j).isIncideAdministracao() == false){
	 			custoAgencia = listaGrupos.get(j).getGrupoValorNaoIncideImposto().add(listaGrupos.get(j).getGrupoValorIncideImposto());
	 		}else{
	 			subContratado = listaGrupos.get(j).getGrupoValorIncideImposto();
	 		}
	 		
	 		
	 		BigDecimal fatDireto = listaGrupos.get(j).getGrupoValorNaoIncideImposto();
	 		BigDecimal fee = listaGrupos.get(j).getGrupoAdministracaoValor();
	 		BigDecimal imposto = listaGrupos.get(j).getGrupoImpostoValor();
	 		
	 		// Faz a soma do subTotal da Linha
	 		BigDecimal sutTotalLinha = custoAgencia.add(subContratado.add(fatDireto)); 
	 		// Faz a soma do Total 
	 		BigDecimal Total = sutTotalLinha.add(fee.add(imposto));
	 		
	 		CorpoGrupos corpoGrup = new CorpoGrupos();
	 		
	 		corpoGrup.setIdCategoria(listaGrupos.get(j).getIdCategoria().getIdcategoria());
	 		corpoGrup.setLinha(listaGrupos.get(j).getGrupo());
	 		
	 		if(det == null){

	 			// Verificar se na tabela AuxDetermina tem alguma referencia para quantidades
	 			
	 			 if(detPad == null){
	 				corpoGrup.setQuant(0);
	 				corpoGrup.setCustounit(zero);
	 				corpoGrup.setDiarias(0);
	 			
	 			}else{
	 			
	 				corpoGrup.setQuant(detPad.getQuantDetermPadrao());
					corpoGrup.setCustounit(detPad.getValorUnitPadrao());
					corpoGrup.setDiarias(detPad.getDiariasPadrao());
	 			
	 			}	
	 			
			}else{
				
			
				corpoGrup.setQuant(det.getQuantDeterm());
			
				
				corpoGrup.setCustounit(det.getValorUnit());
				
				
				corpoGrup.setDiarias(det.getDiarias());
			
			
			}
	 		
	 		corpoGrup.setCustosAgencia(custoAgencia);
	 		corpoGrup.setSubContratado(subContratado);
	 		corpoGrup.setFaturamentoDiretoND(fatDireto);
	 		corpoGrup.setSubTotal(sutTotalLinha);
	 		corpoGrup.setFee(fee);
	 		corpoGrup.setImpostos(imposto);
	 		corpoGrup.setTotal(Total);
	 		corpoGrup.setInformacoes(listaGrupos.get(j).getInformacoes());
	 		corpoGrup.setNaoInclusosCusto(listaGrupos.get(j).getNecessidades());

	 		corpoGrupos.add(corpoGrup);
	 	}
		
		return corpoGrupos;
		
	}
}
