package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelBordas;
import br.com.sysloccOficial.Excel.ExcelCelulaBackground;
import br.com.sysloccOficial.Excel.ExcelFonts;

@Component
public class EstilosGaldema {

	
	
	public static XSSFCellStyle estiloCabecalhoEvento(XSSFWorkbook excelGalderma){
		XSSFCellStyle estilo = excelGalderma.createCellStyle();
		estilo = ExcelCelulaBackground.background(estilo, new int[]{255,255,0});
		estilo.setFont(ExcelFonts.fontBold(excelGalderma, 12,"Tahoma", new int[]{0,0,1}));
		estilo.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return estilo;
	}
	
	
	public static XSSFCellStyle estiloCabecalhoContato(XSSFWorkbook excelGalderma, int[]cor){
		XSSFCellStyle estilo = excelGalderma.createCellStyle();
		estilo = ExcelCelulaBackground.background(estilo, cor);
//		estilo = ExcelCelulaBackground.background(estilo, new int[]{242,242,242});
		estilo = ExcelBordas.bordaEsquerdaTopoBold(estilo);
		estilo.setFont(ExcelFonts.font(excelGalderma, 12,"Tahoma", new int[]{0,0,1}));
		estilo.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return estilo;
	}
	
	
	
	
	
}
