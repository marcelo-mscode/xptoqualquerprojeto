package br.com.sysloccOficial.ListaProducao.Excel;

import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.BaseExcelBayer;

@Component
public class GeraExcelNovoBayerCategorias {

	public void categorias(XSSFSheet abaMaster, BaseExcelBayer base, XSSFWorkbook novoExcel,String categoria,int posicaoCategoria,int[]celulasFormula){
			
			int[] cor = {220,230,241};
		
			XSSFRow linha1 = abaMaster.createRow(posicaoCategoria); // cria linha 1
			XSSFCell cell1 = linha1.createCell(0);    // Cria celula
			
			base.criaLinhaECelulaTexto(cell1, linha1, categoria, 0,novoExcel,cor);
			base.criaLinhaECelulaTexto(cell1, linha1, "", 1,novoExcel,cor);
			
			base.criaLinhaECelulaTexto(cell1, linha1, "", 2,novoExcel,cor);
			base.criaLinhaECelulaTexto(cell1, linha1, "", 3,novoExcel,cor);
			base.criaLinhaECelulaTexto(cell1, linha1, "", 4,novoExcel,cor);
			base.criaLinhaECelulaTexto(cell1, linha1, "", 5,novoExcel,cor);
			
			
			
			criaLinhaECelulaFormula(novoExcel,cell1, linha1, "SUM(G"+celulasFormula[0]+":G"+celulasFormula[1]+")", 6);
			
			
			
			base.criaLinhaECelulaTexto(cell1, linha1, "", 7,novoExcel,cor);
			base.criaLinhaECelulaTexto(cell1, linha1, "Data:", 8,novoExcel,cor);
			base.criaLinhaECelulaTexto(cell1, linha1, "Data:", 9,novoExcel,cor);
			base.criaLinhaECelulaTexto(cell1, linha1, "Data:", 10,novoExcel,cor);
			base.criaLinhaECelulaTexto(cell1, linha1, "Data:", 11,novoExcel,cor);
			
			if(categoria.equals("ESTRUTURA DE PRODUÇÃO (Seguros, taxas obrigatórias, equipamentos de produção, ambulância, banheiro químico e etc)")){
				linha1.setHeightInPoints(32);
			}else{
				linha1.setHeightInPoints(16);
			}
		}
	
		public void criaLinhaECelulaFormula(XSSFWorkbook novoExcel,XSSFCell cell,XSSFRow linha,String formula,int posicaoCelula){
			DecimalFormat df = new DecimalFormat();  
			df.applyPattern("#,##0.00");
	
			cell = linha.createCell(posicaoCelula);	
			cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
			cell.setCellFormula(formula);
			
			
			XSSFCellStyle corCelula = novoExcel.createCellStyle();
			corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
			corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(220,230,241)));
			corCelula.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			corCelula.setVerticalAlignment(XSSFCellStyle.ALIGN_LEFT); 
			corCelula.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			corCelula.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			corCelula.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			corCelula.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			corCelula.setBorderRight(XSSFCellStyle.BORDER_THIN);
			corCelula.setRightBorderColor(IndexedColors.BLACK.getIndex());
			corCelula.setBorderTop(XSSFCellStyle.BORDER_THIN);
			corCelula.setTopBorderColor(IndexedColors.BLACK.getIndex());
			corCelula.setDataFormat(novoExcel.createDataFormat().getFormat("R$    #,##0.00"));
			
			cell.setCellStyle(corCelula);
		}
	
	
	
	
	
	
}
