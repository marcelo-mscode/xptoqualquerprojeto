package br.com.sysloccOficial.Excel;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;



public class ExcelBordas {
	
	
	public static XSSFCellStyle borda(XSSFCellStyle estiloCelula){
		
		estiloCelula.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		estiloCelula.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		estiloCelula.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderRight(XSSFCellStyle.BORDER_THIN);
		estiloCelula.setRightBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderTop(XSSFCellStyle.BORDER_THIN);
		estiloCelula.setTopBorderColor(IndexedColors.BLACK.getIndex());
		
		return estiloCelula;
	}

	public static XSSFCellStyle bordaBold(XSSFCellStyle estiloCelula){
		
		estiloCelula.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setRightBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setTopBorderColor(IndexedColors.BLACK.getIndex());
		
		return estiloCelula;
	}
	
	public static XSSFCellStyle bordaEsquerdaBold(XSSFCellStyle estiloCelula){
		estiloCelula.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		return estiloCelula;
	}
	public static XSSFCellStyle bordaEsquerdaTopoBold(XSSFCellStyle estiloCelula){
		estiloCelula.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setTopBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setRightBorderColor(IndexedColors.BLACK.getIndex());
		
		
		
		return estiloCelula;
	}
	public static XSSFCellStyle bordaTopoBold(XSSFCellStyle estiloCelula){
		estiloCelula.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return estiloCelula;
	}
	public static XSSFCellStyle bordaDireitaBold(XSSFCellStyle estiloCelula){
		estiloCelula.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setRightBorderColor(IndexedColors.BLACK.getIndex());
		return estiloCelula;
	}
	public static XSSFCellStyle bordaBaixoBold(XSSFCellStyle estiloCelula){
		estiloCelula.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
		estiloCelula.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		return estiloCelula;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static XSSFCellStyle bordaDuplas(XSSFCellStyle estiloCelula){
		
		estiloCelula.setBorderBottom(XSSFCellStyle.BORDER_DOUBLE);
		estiloCelula.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderLeft(XSSFCellStyle.BORDER_DOUBLE);
		estiloCelula.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderRight(XSSFCellStyle.BORDER_DOUBLE);
		estiloCelula.setRightBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderTop(XSSFCellStyle.BORDER_DOUBLE);
		estiloCelula.setTopBorderColor(IndexedColors.BLACK.getIndex());
		
		return estiloCelula;
	}
	
}
