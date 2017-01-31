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


@Component
public class GeraTextoCategorias {
	
	public static void geratextoCategorias(XSSFWorkbook excel,XSSFSheet cenario,int linha,String texto){
		
		XSSFRow linhaComeco = cenario.createRow(linha);
		XSSFCellStyle estilo = excel.createCellStyle();
		
		estilo = ExcelBordas.borda(estilo);
		estilo = ExcelCelulaBackground.background(estilo, new int[]{172,185,202});

		XSSFCell celulaCategoria = linhaComeco.createCell(0);
		celulaCategoria.setCellValue(texto);
		estilo.setFont(ExcelFonts.fontBold(excel, 12, "Tahoma"));
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		celulaCategoria.setCellStyle(estilo);
		
		for (int i = 1; i < 7; i++) {
			XSSFCell celulaEstimat = linhaComeco.createCell(i);
			celulaEstimat.setCellStyle(estilo);
		}
		
	}

}
