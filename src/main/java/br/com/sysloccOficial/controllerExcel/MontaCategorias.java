package br.com.sysloccOficial.controllerExcel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.model.Categoria;

public class MontaCategorias {

	
	@Autowired private AuxExcelSQL sql;
	
	
	
	/**
	 * Método Responsável por montar as Categorias da Lista com seus subTotais
	 * 
	 * @param idLista
	 * @return BigDecimal
	 */
	
	public List<CorpoCategoria> montaCategoria(Integer idLista){
	// Monta a lista de Categorias da Lista	
		List<Categoria> listaCategoria = sql.retornaCategoria(idLista);
		List<CorpoCategoria> corpoCategoria = new ArrayList<CorpoCategoria>();
		
		for (int i  = 0; i < listaCategoria.size(); i++) {
			Integer idCategoria = listaCategoria.get(i).getIdcategoria();
			
			//  Cria um array que receba os itens na ordem
			// Adicionar esse array em CorpoPlanilha
			
			// Verifica se existe algum item opcional na planinlha
			// Caso Exista Não exibir
			// Se todos os itens forem opcionais, não exibe a categoria e nem os itens
			Integer opc = sql.opcional(listaCategoria.get(i).getIdcategoria());
			if(listaCategoria.get(i).getGrupo().isEmpty() || opc == 0){

			}else{
				CorpoCategoria corpoCateg = new CorpoCategoria();
				corpoCateg.setIdCategoria(listaCategoria.get(i).getIdcategoria());
				corpoCateg.setCategoria(listaCategoria.get(i).getCategoria());
				
				// Linhas que não incide Administracao
				// Lista Grupos pelo idCategoria e somar essas linhas
				corpoCateg.setSomaCustoAgencia(sql.totalCustoAgencia(idCategoria));
				
				// SubContratado
				corpoCateg.setSomaSubContratado(sql.totalSubContratado(idCategoria));
				
				// Faturamento Direto
				corpoCateg.setSomaFatDireto(sql.totalFatDireto(idCategoria));
				
				// SutTotal
				corpoCateg.setSubTotal(sql.subTotal(idCategoria));
				
				// Total
				corpoCateg.setTotal(sql.total(idCategoria));
				
				corpoCateg.setSomaImposto(sql.totalImposto(idCategoria));
				corpoCateg.setFee(sql.totalFeeCategoria(idCategoria));

				
				
				
				corpoCategoria.add(corpoCateg);
			}
		}
		
		return corpoCategoria;
		
		
	}
	
}
