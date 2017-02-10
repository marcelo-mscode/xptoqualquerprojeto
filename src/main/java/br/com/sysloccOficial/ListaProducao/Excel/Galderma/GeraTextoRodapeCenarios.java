package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelBordas;
import br.com.sysloccOficial.Excel.ExcelFonts;
import br.com.sysloccOficial.Excel.ExcelMerge;

@Component
public class GeraTextoRodapeCenarios {

	public static void geraTextoRodape(XSSFWorkbook excelGalderma,XSSFSheet cenario){
		textoRodape(excelGalderma, cenario, 60, "Observações: *Check-in a partir das 12H00 - Checkout até 12H00\nOs valores apresentados são válidos exclusivamente para o grupo e período em referência\nForma de pagamento: Faturamento para 60 dias, exceto Hotel e Bar do Alemão 30 dias.");
	}

	public static void geraTextoRodapeOpcionais(XSSFWorkbook excelGalderma,XSSFSheet cenario){
		textoRodape(excelGalderma, cenario, 38, "Forma de pagamento: Faturamento para 60 dias.");
	}
	
	
	private static void textoRodape(XSSFWorkbook excelGalderma,XSSFSheet cenario,int numLinha,String texto){
		XSSFRow linha = cenario.createRow(numLinha);
		XSSFCell cell = linha.createCell(0);
		cell.setCellValue(texto);
		XSSFCellStyle estilo = excelGalderma.createCellStyle();
		estilo.setFont(ExcelFonts.fontBold(excelGalderma, 8, "Tahoma"));
		estilo = ExcelBordas.bordaDuplas(estilo);
		estilo.setWrapText(true);
		estilo.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cell.setCellStyle(estilo);
		ExcelMerge.mergeVertical(excelGalderma, cenario, cell, 60,63, 0, 6);
	}

	
	
	
	
	
	
	
}
