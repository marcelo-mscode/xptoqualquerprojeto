package sysloccOficial;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AuxExcelBordas {

	public static XSSFCellStyle todasBordas(XSSFWorkbook w,XSSFCellStyle borda){
		borda.setBorderTop(XSSFCellStyle.BORDER_THIN);
		borda.setTopBorderColor(IndexedColors.BLACK.getIndex());
		borda.setBorderRight(XSSFCellStyle.BORDER_THIN);
		borda.setRightBorderColor(IndexedColors.BLACK.getIndex());
		borda.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		borda.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		borda.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		borda.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		return borda;
	
	}	
	
	public static XSSFCellStyle topoBorda(XSSFWorkbook w,XSSFCellStyle borda){
		borda.setBorderTop(XSSFCellStyle.BORDER_THIN);
		borda.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return borda;
	}

	public static XSSFCellStyle direitaBorda(XSSFWorkbook w,XSSFCellStyle borda){
		borda.setBorderRight(XSSFCellStyle.BORDER_THIN);
		borda.setRightBorderColor(IndexedColors.BLACK.getIndex());
		return borda;
	}

	public static XSSFCellStyle baixoBorda(XSSFWorkbook w,XSSFCellStyle borda){
		borda.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		borda.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		return borda;
	}
	
	public static XSSFCellStyle esquerdaBorda(XSSFWorkbook w,XSSFCellStyle borda){
		borda.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		borda.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		return borda;
	}
	
	public static void setaBordaTipoFaturamento(XSSFWorkbook workbook, XSSFSheet planilha){
		
		/*XSSFRow tipoFaturamento = planilha.createRow(7);*/
		
		XSSFRow tipoFaturamento4 = planilha.createRow(6);
		Cell tipoFaturamentoCell4 = tipoFaturamento4.createCell((short)4);
		XSSFCellStyle borda = workbook.createCellStyle();
		tipoFaturamentoCell4.setCellStyle(AuxExcelEstilos.baixoBorda(workbook, borda));

		
		Cell tipoFaturamentoCell5 = tipoFaturamento4.createCell((short)5);
		XSSFCellStyle borda5 = workbook.createCellStyle();
		tipoFaturamentoCell5.setCellStyle(AuxExcelEstilos.baixoBorda(workbook, borda5));
		
		
		Cell tipoFaturamentoCell6 = tipoFaturamento4.createCell((short)6);
		XSSFCellStyle borda6 = workbook.createCellStyle();
		tipoFaturamentoCell6.setCellStyle(AuxExcelEstilos.baixoBorda(workbook, borda6));
		
		/*Cell tipoFaturamentoCell7 = tipoFaturamento.createCell((short)7);
		XSSFCellStyle borda7 = workbook.createCellStyle();
		tipoFaturamentoCell7.setCellStyle(AuxExcel.esquerdaBorda(workbook, borda7));*/
		
		
	}
	
	
	
	
	
	
	
	
	
}
