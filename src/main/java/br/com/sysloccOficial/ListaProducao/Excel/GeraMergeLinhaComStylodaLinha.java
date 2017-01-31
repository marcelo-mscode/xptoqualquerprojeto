package br.com.sysloccOficial.ListaProducao.Excel;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GeraMergeLinhaComStylodaLinha {
	
	
	private XSSFSheet abaMaster;
	private XSSFWorkbook novoExcel;


	public GeraMergeLinhaComStylodaLinha(XSSFSheet abaMaster,XSSFWorkbook novoExcel){
		this.abaMaster = abaMaster;
		this.novoExcel = novoExcel;
	}
	
	
	public void geraMerge(XSSFCell cell,int linha, int primCelula, int ultCelula){
		
		CellRangeAddress cellRangeAddress = new CellRangeAddress(linha, linha, primCelula, ultCelula);
		abaMaster.addMergedRegion(cellRangeAddress);
		RegionUtil.setBorderBottom(cell.getCellStyle().getBorderBottom(), cellRangeAddress, abaMaster, novoExcel);
		RegionUtil.setBorderTop(cell.getCellStyle().getBorderTop(), cellRangeAddress, abaMaster, novoExcel);
		RegionUtil.setBorderLeft(cell.getCellStyle().getBorderLeft(), cellRangeAddress, abaMaster, novoExcel);
		RegionUtil.setBorderRight(cell.getCellStyle().getBorderRight(), cellRangeAddress, abaMaster, novoExcel);
	}

	public void geraMerge(XSSFCell cell,XSSFRow infoLinha, int primCelula, int ultCelula){
		CellRangeAddress cellRangeAddress = new CellRangeAddress(infoLinha.getRowNum(), infoLinha.getRowNum(), primCelula, ultCelula);
		abaMaster.addMergedRegion(cellRangeAddress);
		RegionUtil.setBorderBottom(cell.getCellStyle().getBorderBottom(), cellRangeAddress, abaMaster, novoExcel);
		RegionUtil.setBorderTop(cell.getCellStyle().getBorderTop(), cellRangeAddress, abaMaster, novoExcel);
		RegionUtil.setBorderLeft(cell.getCellStyle().getBorderLeft(), cellRangeAddress, abaMaster, novoExcel);
		RegionUtil.setBorderRight(cell.getCellStyle().getBorderRight(), cellRangeAddress, abaMaster, novoExcel);
	}
	

}
