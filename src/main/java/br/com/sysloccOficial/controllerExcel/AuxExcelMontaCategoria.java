package br.com.sysloccOficial.controllerExcel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.conf.UtilitariaConversoes;





public class AuxExcelMontaCategoria {
	
	@Autowired public static AuxExcelEstilos auxExcel;
	@Autowired public AuxExcelSQL excelSQL;
	@Autowired public UtilitariaConversoes conv;
	TextosStaticosExcel textosEstaticos = new TextosStaticosExcel();
	
	@PersistenceContext	private EntityManager manager;
	
	
	public Integer montaCateg(XSSFWorkbook workbook, XSSFSheet planilha, List<CorpoCategoria> corpoCategoria, List<CorpoGrupos> corpoGrupos){
		
		//Categoria
		int categ = 9; // Criar categorias a partir da linha 9
		int linha = 9; // Linha auxiliar na contagem 
		
		for(int i = 0;i < corpoCategoria.size() ; i++){
			
			XSSFRow rowCategoria = teste(i, planilha, categ, linha);
			
			//Regula a altura da célula na categorias
			rowCategoria.setHeight((short)400);
			
			Cell cellCategoria = rowCategoria.createCell(0);
			cellCategoria.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 146, 208, 80));
			cellCategoria.setCellValue(corpoCategoria.get(i).getCategoria());

			
			// Grupos das Categorias
			for(int j = 0; j < corpoGrupos.size();j++){
				if(corpoCategoria.get(i).getIdCategoria() == corpoGrupos.get(j).getIdCategoria()){
					linha = categ + 1;
					XSSFRow rowLinha = planilha.createRow(linha);	
				
					criaCelulaString    (workbook, rowLinha,corpoGrupos.get(j).getLinha(),0 );       	       // Nome Linha
					
					
					// Verificar se existe 
					criaCelulaInt       (workbook, rowLinha, corpoGrupos.get(j).getQuant() ,1 );			   // Quantidade
					criaCelulaBigDecimal(workbook, rowLinha,corpoGrupos.get(j).getCustounit(),2);              // Custo Unitario
					criaCelulaInt       (workbook, rowLinha,corpoGrupos.get(j).getDiarias(),3);      		   // Diarias
					
					
					
					criaCelulaBigDecimal(workbook, rowLinha,corpoGrupos.get(j).getCustosAgencia(),4);    	   // Custo Agência
					criaCelulaBigDecimal(workbook, rowLinha,corpoGrupos.get(j).getSubContratado(),5);    	   // Sub Contratado
					criaCelulaBigDecimal(workbook, rowLinha,corpoGrupos.get(j).getFaturamentoDiretoND(),6);    // Fat-Direto
					criaCelulaBigDecimal(workbook, rowLinha,corpoGrupos.get(j).getSubTotal(),7);               // Sub Total
					criaCelulaBigDecimal(workbook, rowLinha,corpoGrupos.get(j).getFee(),8);                    // Fee
					criaCelulaBigDecimal(workbook, rowLinha,corpoGrupos.get(j).getImpostos(),9);               // Imposto
					criaCelulaBigDecimal(workbook, rowLinha,corpoGrupos.get(j).getTotal(),10);                 // Total
					criaCelulaString    (workbook, rowLinha,corpoGrupos.get(j).getInformacoes(),11);           // Informações
					criaCelulaString    (workbook, rowLinha,corpoGrupos.get(j).getNaoInclusosCusto(),12);      // Não Inclusos no Custo
					
					categ = categ + 1;
				}
				
			}
			
			categ = categ + 1;
			linha = linha + 1;
			
			XSSFRow rowSubCategoria = planilha.createRow(linha);
			rowSubCategoria.setHeight((short)500);
			Cell cellSubCategoria = rowSubCategoria.createCell(0);
			cellSubCategoria.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 255, 255, 255));
			cellSubCategoria.setCellValue(corpoCategoria.get(i).getCategoria()+" :");

			//Imprimi 3 linhas em branco depois do subNome Categoria	
			textosEstaticos.celulasEmBranco(1,4, rowSubCategoria, workbook);
			
			
			
			// Custos Agência
			Cell cellSub4 = rowSubCategoria.createCell(4);
			cellSub4.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 255, 255, 255));
			criaCelulaSubTotalCategoria(workbook,rowSubCategoria,corpoCategoria.get(i).getSomaCustoAgencia(),4);
			
			// Sub Contratado
			Cell cellSub5 = rowSubCategoria.createCell(5);
			cellSub5.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 255, 255, 255));
			criaCelulaSubTotalCategoria(workbook,rowSubCategoria,corpoCategoria.get(i).getSomaSubContratado(),5);
			
			// Faturamento Direto
			Cell cellSub6 = rowSubCategoria.createCell(6);
			cellSub6.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 255, 255, 255));
			criaCelulaSubTotalCategoria(workbook,rowSubCategoria,corpoCategoria.get(i).getSomaFatDireto(),6);
			
			
			// Faturamento Sub Total
			Cell cellSub7 = rowSubCategoria.createCell(7);
			cellSub7.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 255, 255, 255));
			criaCelulaSubTotalCategoria(workbook,rowSubCategoria,corpoCategoria.get(i).getSubTotal(),7);
			
			// Faturamento Fee
			Cell cellSub8 = rowSubCategoria.createCell(8);
			cellSub8.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 255, 255, 255));
			criaCelulaSubTotalCategoria(workbook,rowSubCategoria,corpoCategoria.get(i).getFee(),8);
	
			
			// Faturamento Imposto
			Cell cellSub9 = rowSubCategoria.createCell(9);
			cellSub9.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 255, 255, 255));
			criaCelulaSubTotalCategoria(workbook,rowSubCategoria,corpoCategoria.get(i).getSomaImposto(),9);
			
			// Faturamento Total
			Cell cellSub10 = rowSubCategoria.createCell(10);
			cellSub10.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 255, 255, 255));
			criaCelulaSubTotalCategoria(workbook,rowSubCategoria,corpoCategoria.get(i).getTotal(),10);
			
			//Duas últimas células em branco
			textosEstaticos.celulasEmBranco(11,13, rowSubCategoria, workbook);
			
			categ = categ + 1;
			linha = linha + 1;
			
		}
		
		return linha;
	}
	
	
	public void criaCelulaString(XSSFWorkbook workbook, XSSFRow rowLinha, String valorCelula, int posicao){
		
		XSSFCellStyle borda = workbook.createCellStyle();
		AuxExcelEstilos.bordaStilo(workbook, borda);
		borda.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		borda.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		Cell cellLinha = rowLinha.createCell(posicao);
		cellLinha.setCellStyle(borda);
		cellLinha.setCellValue(valorCelula);
	}

	public void criaCelulaInt(XSSFWorkbook workbook, XSSFRow rowLinha, double valorCelula, int posicao){
		Cell cellLinha = rowLinha.createCell(posicao);
		cellLinha.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleDireitaFormataQuantidade(workbook));
		
		if(valorCelula == 0){
			cellLinha.setCellValue("");	
		}else{
			
			DataFormatter formatter = new DataFormatter();
			formatter.formatCellValue(cellLinha);
			cellLinha.setCellValue(valorCelula);
		}
		
	}

	public void criaCelulaBigDecimal(XSSFWorkbook workbook, XSSFRow rowLinha, BigDecimal valorCelula, int posicao){
		double num = valorCelula.doubleValue();

        Cell cellLinha = rowLinha.createCell(posicao);
        cellLinha.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleDireit(workbook));

        if(num  == 0){
			cellLinha.setCellValue("");	
		}else{
			cellLinha.setCellValue(num);
		}
	
	}

	
	
	// SubTotalCategoria
	public void criaCelulaSubTotalCategoria(XSSFWorkbook workbook, XSSFRow rowLinha, BigDecimal valorCelula, int posicao){
		double num = valorCelula.doubleValue();
		rowLinha.setHeight((short)300);
		
		Cell cellLinha = rowLinha.createCell(posicao);
		cellLinha.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 255, 255, 255));

		if(num  == 0){
			cellLinha.setCellValue("-");	
		}else{
			cellLinha.setCellValue(num);
		}
		
		
		
		
	}
	
	public XSSFRow teste(int i,XSSFSheet planilha, int categ, int linha){
			
			XSSFRow rowCategoria;
			if(i == 0){
				rowCategoria = planilha.createRow(categ);
				// Mescla celulas para Categoria
				AuxExcelEstilos.mescla(planilha, categ, categ, 0, 12);
				return rowCategoria;
			}else{
				rowCategoria = planilha.createRow(linha);
				// Mescla celulas para Categoria
				AuxExcelEstilos.mescla(planilha, linha, linha, 0, 12);
				return rowCategoria;
			}
			
			
		}
	
	
	

}
