package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import br.com.sysloccOficial.Excel.ExcelBordas;
import br.com.sysloccOficial.Excel.ExcelCelulaBackground;
import br.com.sysloccOficial.Excel.ExcelFonts;
import br.com.sysloccOficial.Excel.ExcelMerge;

@Component
public class CorpoCenarioGaldermaTopo {
	
	public static int[] corTopo = {47,117,181};
	public static int[] corFundoCelulaPadrao = {255,255,254};
	
	public static void geraTopoEstatico(XSSFWorkbook excel, XSSFSheet cenario,int linhaComecoTopo){
		
		XSSFRow linha2 = cenario.createRow(linhaComecoTopo);
		linha2.setHeightInPoints(27);
		XSSFCell celulaEstimativa = linha2.createCell(0);
		celulaEstimativa.setCellValue("ESTIMATIVA DE CUSTOS");
		
		XSSFCellStyle estiloTopo = excel.createCellStyle();
		estiloTopo = ExcelCelulaBackground.background(estiloTopo, corTopo);
		estiloTopo.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		estiloTopo.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		estiloTopo = ExcelBordas.bordaBold(estiloTopo);
		
		estiloTopo.setFont(ExcelFonts.fontBold(excel, 14,"Tahoma", corFundoCelulaPadrao));
		
		celulaEstimativa.setCellStyle(estiloTopo);
		ExcelMerge.merge(excel, cenario, celulaEstimativa, linha2, 0, 6);
		
		geraCabecalho(excel,cenario, linhaComecoTopo+2);
	}
	
	private static void geraCabecalho(XSSFWorkbook excel,XSSFSheet cenario,int linhaComecoTopo){
		
		String[] textosCabecalho = {"Diárias","Qtd","Valor Unitário Inicial","Sub Total Inicial","Valor Unitário Negociado","Valor Total já Negociado"};
		
		cenario.setColumnWidth(0, 21500);
		cenario.setColumnWidth(3, 9500);
		cenario.setColumnWidth(4, 9500);
		cenario.setColumnWidth(5, 8500);
		cenario.setColumnWidth(6, 8500);
		
		XSSFRow linha = cenario.createRow(linhaComecoTopo);
		linha.setHeightInPoints(27);
		
		XSSFCellStyle estilo = excel.createCellStyle();
		estilo = ExcelCelulaBackground.background(estilo, new int[]{0,176,240});
		estilo = ExcelBordas.borda(estilo);
		estilo.setFont(ExcelFonts.fontBold(excel, 12,"Tahoma", corFundoCelulaPadrao));
		estilo.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		
		XSSFCell celulaEstimativa = linha.createCell(0);
		celulaEstimativa.setCellValue("Descrição dos serviços");
		celulaEstimativa.setCellStyle(estilo);
		
		for (int i = 0; i < textosCabecalho.length; i++) {
			XSSFCell celulaEstimat = linha.createCell(i+1);
			celulaEstimat.setCellValue(textosCabecalho[i]);
			estilo.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			celulaEstimat.setCellStyle(estilo);
		}
		
	}
	
	
}
