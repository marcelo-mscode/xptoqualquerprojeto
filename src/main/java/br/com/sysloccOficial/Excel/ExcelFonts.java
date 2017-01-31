package br.com.sysloccOficial.Excel;

import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelFonts {
	
	
	public static XSSFFont font(XSSFWorkbook excelGalderma, int tamanhoFonte,String nomeFonte){
		XSSFFont font = excelGalderma.createFont();
		font.setFontName(nomeFonte);
		font.setItalic(false);
		font.setFontHeightInPoints((short)tamanhoFonte);
		return font;
	}

	public static XSSFFont fontItalic(XSSFWorkbook excelGalderma, int tamanhoFonte,String nomeFonte){
		XSSFFont font = font(excelGalderma, tamanhoFonte,nomeFonte);
		font.setItalic(true);
		return font;
	}

	public static XSSFFont fontBold(XSSFWorkbook excelGalderma, int tamanhoFonte,String nomeFonte){
		XSSFFont font = font(excelGalderma, tamanhoFonte,nomeFonte);
		font.setBold(true);
		return font;
	}

	public static XSSFFont fontBoldItalic(XSSFWorkbook excelGalderma, int tamanhoFonte,String nomeFonte){
		XSSFFont font = font(excelGalderma, tamanhoFonte,nomeFonte);
		font.setBold(true);
		font.setItalic(true);
		return font;
	}
	

	
	/**
	 * Fonts coloridas
	 * 
	 * 
	 */
	public static XSSFFont font(XSSFWorkbook excelGalderma, int tamanhoFonte,String nomeFonte,int[] corFundo){
		XSSFFont font = excelGalderma.createFont();
		font.setFontName(nomeFonte);
		font.setColor(new XSSFColor(new java.awt.Color(corFundo[0],corFundo[1],corFundo[2])));
		font.setItalic(false);
		font.setFontHeightInPoints((short)tamanhoFonte);
		return font;
	}
	
	public static XSSFFont fontItalic(XSSFWorkbook excelGalderma, int tamanhoFonte,String nomeFonte,int[] corFundo){
		XSSFFont font = font(excelGalderma, tamanhoFonte,nomeFonte,corFundo);
		font.setItalic(true);
		return font;
	}

	public static XSSFFont fontBold(XSSFWorkbook excelGalderma, int tamanhoFonte,String nomeFonte,int[] corFundo){
		XSSFFont font = font(excelGalderma, tamanhoFonte,nomeFonte,corFundo);
		font.setBold(true);
		return font;
	}

	public static XSSFFont fontBoldItalic(XSSFWorkbook excelGalderma, int tamanhoFonte,String nomeFonte,int[] corFundo){
		XSSFFont font = font(excelGalderma, tamanhoFonte,nomeFonte,corFundo);
		font.setBold(true);
		font.setItalic(true);
		return font;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
