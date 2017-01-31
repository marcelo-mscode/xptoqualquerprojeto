package br.com.sysloccOficial.ListaProducao.Excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GeraStyloCelula {
	
	private XSSFWorkbook novoExcel;

	public GeraStyloCelula(XSSFWorkbook novoExcel){
		this.novoExcel = novoExcel;
	}
	
	public XSSFCellStyle styloBorda(int[]corFundo){
		
		XSSFCellStyle corCelula = novoExcel.createCellStyle();
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(corFundo[0],corFundo[1],corFundo[2])));
		corCelula.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		corCelula.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP); 
		corCelula.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		corCelula.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		corCelula.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		corCelula.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		corCelula.setBorderRight(XSSFCellStyle.BORDER_THIN);
		corCelula.setRightBorderColor(IndexedColors.BLACK.getIndex());
		corCelula.setBorderTop(XSSFCellStyle.BORDER_THIN);
		corCelula.setTopBorderColor(IndexedColors.BLACK.getIndex());

		return corCelula;
	}
	

	
	
	

}
