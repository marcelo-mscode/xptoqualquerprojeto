package br.com.sysloccOficial.Excel;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;



@Component
public class ExcelEstiloPadrao {
	
	public static XSSFCellStyle estiloPadrao(XSSFWorkbook excel){
		XSSFCellStyle estilo = excel.createCellStyle();
		estilo.setWrapText(true);
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		estilo.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
		estilo = ExcelBordas.borda(estilo); 
		return estilo;
	}

	public static XSSFCellStyle estiloPadrao(XSSFWorkbook excel,XSSFFont font){
		XSSFCellStyle estilo = estiloPadrao(excel);
		estilo.setFont(font);
		return estilo;
	}

	public static XSSFCellStyle estiloCentralizado(XSSFWorkbook excel){
		XSSFCellStyle estilo = excel.createCellStyle();
		estilo.setWrapText(true);
		estilo.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		estilo.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		estilo = ExcelBordas.borda(estilo); 
		return estilo;
	}
	
	public static XSSFCellStyle estiloPadraoCentralizado(XSSFWorkbook excel,XSSFFont font){
		XSSFCellStyle estilo = estiloCentralizado(excel);
		estilo.setFont(font);
		return estilo;
	}

	public static XSSFCellStyle estiloPadraoBold(XSSFWorkbook excel){
		XSSFCellStyle estilo = estiloPadrao(excel);
		estilo.setFont(ExcelFonts.fontBold(excel, 12, "Tahoma"));
		return estilo;
	}
	public static XSSFCellStyle estiloPadraoBoldCentralizado(XSSFWorkbook excel,XSSFFont font){
		XSSFCellStyle estilo = estiloPadraoCentralizado(excel,font);
		estilo.setFont(ExcelFonts.fontBold(excel, 12, "Tahoma"));
		return estilo;
	}

	public static XSSFCellStyle estiloPadrao(XSSFWorkbook excel,XSSFFont font,boolean wrap){
		XSSFCellStyle estilo = estiloPadrao(excel);
		estilo.setFont(font);
		estilo.setWrapText(wrap);
		return estilo;
	}
	
	public static XSSFCellStyle estiloPadrao(XSSFWorkbook excel,boolean wrap){
		XSSFCellStyle estilo = estiloPadrao(excel);
		estilo.setWrapText(wrap);
		return estilo;
	}
	

}
