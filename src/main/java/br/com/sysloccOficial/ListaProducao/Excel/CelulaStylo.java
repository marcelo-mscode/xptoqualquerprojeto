package br.com.sysloccOficial.ListaProducao.Excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CelulaStylo {
	
	
	private XSSFWorkbook novoExcel;

	public CelulaStylo(XSSFWorkbook novoExcel){
		this.novoExcel = novoExcel;
	}
	
	
	
	public XSSFCellStyle fundoBorda(int[] corFundo,boolean italico,int tamanhoFonte,boolean bold){
		XSSFFont font = novoExcel.createFont();
		font.setFontName("Arial");
		font.setItalic(italico);
		font.setFontHeightInPoints((short)tamanhoFonte);
		font.setBold(bold);
		
		XSSFCellStyle corCelula = novoExcel.createCellStyle();
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(corFundo[0],corFundo[1],corFundo[2])));
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
		corCelula.setFont(font);
		return corCelula;
	}
	
	public XSSFCellStyle fundoBordaComFormula(int[] corFundo,boolean italico,int tamanhoFonte,boolean bold){
		
		XSSFCellStyle corCelula = fundoBorda(corFundo, italico, tamanhoFonte, bold);
		
		corCelula.setDataFormat(novoExcel.createDataFormat().getFormat("R$    #,##0.00"));
		corCelula.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		
		return corCelula;
	}
	
	
	
	
	
	
	
	

}
