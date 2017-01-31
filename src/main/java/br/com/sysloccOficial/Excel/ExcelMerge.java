package br.com.sysloccOficial.Excel;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelMerge {

	public static void merge(XSSFWorkbook excel, XSSFSheet aba,XSSFCell cell,XSSFRow infoLinha,int primCelula,int ultCelula){
			CellRangeAddress cellRangeAddress = new CellRangeAddress(infoLinha.getRowNum(), infoLinha.getRowNum(), primCelula, ultCelula);
			aba.addMergedRegion(cellRangeAddress);
			RegionUtil.setBorderBottom(cell.getCellStyle().getBorderBottom(), cellRangeAddress, aba, excel);
			RegionUtil.setBorderTop(cell.getCellStyle().getBorderTop(), cellRangeAddress, aba, excel);
			RegionUtil.setBorderLeft(cell.getCellStyle().getBorderLeft(), cellRangeAddress, aba, excel);
			RegionUtil.setBorderRight(cell.getCellStyle().getBorderRight(), cellRangeAddress, aba, excel);
	}
	
	public static void mergeVertical(XSSFWorkbook excel, XSSFSheet aba,XSSFCell cell,int primeiraLinha,int ultimaLinha,int primCelula,int ultCelula){
		CellRangeAddress cellRangeAddress = new CellRangeAddress(primeiraLinha,ultimaLinha, primCelula, ultCelula);
		aba.addMergedRegion(cellRangeAddress);
		RegionUtil.setBorderBottom(cell.getCellStyle().getBorderBottom(), cellRangeAddress, aba, excel);
		RegionUtil.setBorderTop(cell.getCellStyle().getBorderTop(), cellRangeAddress, aba, excel);
		RegionUtil.setBorderLeft(cell.getCellStyle().getBorderLeft(), cellRangeAddress, aba, excel);
		RegionUtil.setBorderRight(cell.getCellStyle().getBorderRight(), cellRangeAddress, aba, excel);
	}
}
