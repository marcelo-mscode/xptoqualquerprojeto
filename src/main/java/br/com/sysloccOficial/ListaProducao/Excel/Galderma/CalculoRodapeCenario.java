package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import br.com.sysloccOficial.Excel.ExcelBordas;
import br.com.sysloccOficial.Excel.ExcelCelulaBackground;
import br.com.sysloccOficial.Excel.ExcelCelulaEspecial;
import br.com.sysloccOficial.Excel.ExcelFonts;
import br.com.sysloccOficial.Excel.ExcelMerge;

@Component
public class CalculoRodapeCenario {
	
	private static XSSFCellStyle estilo;
	
	
	/**
	 * Método que faz a linha de 
	 * SubTotal Geral
	 * Investimentos de Terceiros
	 * Investimentos de Agência
	 * 
	 * @param excelGalderma
	 * @param cenario
	 */
	public static XSSFRow montaCelulas(XSSFWorkbook excelGalderma,XSSFSheet cenario,int numLinha,String texto,int[] corFundo){
		
		XSSFRow linha = cenario.createRow(numLinha);
		XSSFCell cell = linha.createCell(0);
		
		estilo = excelGalderma.createCellStyle();
		estilo.setFont(ExcelFonts.fontBold(excelGalderma, 12, "Tahoma"));
		estilo = ExcelBordas.borda(estilo);
		cell.setCellStyle(ExcelCelulaBackground.background(estilo, corFundo));
		cell.setCellValue(texto);
		
		ExcelMerge.merge(excelGalderma, cenario, cell, linha, 0, 2);
		ExcelCelulaEspecial.formatoFormula(excelGalderma, estilo, linha, 4, "E17*D17");
		ExcelCelulaEspecial.formatoFormula(excelGalderma, estilo, linha, 6, "E17*D17");
		
		return linha;
		
	}

	
	public static void calculoRodapeCenario(XSSFWorkbook excelGalderma,XSSFSheet cenario,int numLinha,String texto,int[] corFundo){
			
		XSSFRow linha = montaCelulas(excelGalderma, cenario, numLinha, texto, corFundo);
		XSSFCell cell2 = linha.createCell(3);cell2.setCellStyle(estilo);
		XSSFCell cell3 = linha.createCell(5);cell3.setCellStyle(estilo);
		
	}

	public static void calculoRodapeCenario(XSSFWorkbook excelGalderma,XSSFSheet cenario,int numLinha,String texto,int[] corFundo,double porcentagem){
		
		XSSFRow linha = montaCelulas(excelGalderma, cenario, numLinha, texto, corFundo);
		
		XSSFCellStyle estiloPorcentagem = excelGalderma.createCellStyle();
		estiloPorcentagem.setFont(ExcelFonts.fontBold(excelGalderma, 12, "Tahoma"));
		estiloPorcentagem = ExcelBordas.borda(estiloPorcentagem);
		estiloPorcentagem = ExcelCelulaBackground.background(estiloPorcentagem, corFundo);
		
        ExcelCelulaEspecial.formatoPorcentagem(excelGalderma, estiloPorcentagem, porcentagem, linha, 3);
        ExcelCelulaEspecial.formatoPorcentagem(excelGalderma, estiloPorcentagem, porcentagem, linha, 5);
		
	}
}
