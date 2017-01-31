package br.com.sysloccOficial.ListaProducao.Excel;

import java.io.*;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.BaseExcelBayer;


@Component
public class TesteExcel {
	    
	XSSFSheet aba;
	XSSFWorkbook testeExcel;
	
	public String GeraListagemTeste(Integer idLista) throws IOException{
		BaseExcelBayer base = new BaseExcelBayer("C:/SYSLOC/upload/excel/"+"listagemEmpresas");
		FileOutputStream out = base.caminhoeNomeDoArquivo();
		
		testeExcel = new XSSFWorkbook();
		
		aba = testeExcel.createSheet("Masters"); /** Cria Aba Master da planilha */

		XSSFCellStyle style = bordaFundo(new int[]{242,242,242});

		XSSFRow linha = criaLinha(style);
		
		System.out.println(linha.getLastCellNum());
		
		
		setaLinha(linha,style , 4);
		
		
		
		
		
		
		
		
		base.fechaPlanilha(testeExcel,out);
		return "ok";
	}	
	
	
	public XSSFRow criaLinha(XSSFCellStyle estilo){
		XSSFRow linha = aba.createRow(1);
		linha.setHeightInPoints(35);
		XSSFCell cell = linha.createCell(0);
		
		cell = linha.createCell(3);
		cell.setCellValue("Teste");
	
		if(estilo != null){
			cell.setCellStyle(estilo);
		}
		
		return linha;
	}

	public XSSFRow setaLinha(XSSFRow linha,XSSFCellStyle estilo,int posicaoCelula){
		linha.setHeightInPoints(35);
		XSSFCell cell = linha.createCell(posicaoCelula);
		
		cell = linha.createCell(posicaoCelula);
		estilo.setDataFormat(testeExcel.createDataFormat().getFormat("R$    #,##0.00"));
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula("E5*E10");
		if(estilo != null){
			cell.setCellStyle(estilo);
		}
		
		geraMerge(cell, linha.getRowNum(), 4, 5);
		
		return linha;
	}
	
	public XSSFCellStyle bordaFundo(int[]fundo){
		
		XSSFCellStyle styleReal = testeExcel.createCellStyle();
		
		styleReal.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleReal.setFillForegroundColor(new XSSFColor(new java.awt.Color(fundo[0],fundo[1],fundo[2])));
		styleReal.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); 
		styleReal.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		styleReal.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styleReal.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		styleReal.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		styleReal.setBorderRight(XSSFCellStyle.BORDER_THIN);
		styleReal.setRightBorderColor(IndexedColors.BLACK.getIndex());
		styleReal.setBorderTop(XSSFCellStyle.BORDER_THIN);
		styleReal.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return styleReal;
	}
	
	public void geraMerge(XSSFCell cell,int linha, int primCelula, int ultCelula){
		CellRangeAddress cellRangeAddress = new CellRangeAddress(linha, linha, primCelula, ultCelula);
		aba.addMergedRegion(cellRangeAddress);
		RegionUtil.setBorderBottom(cell.getCellStyle().getBorderBottom(), cellRangeAddress, aba, testeExcel);
		RegionUtil.setBorderTop(cell.getCellStyle().getBorderTop(), cellRangeAddress, aba, testeExcel);
		RegionUtil.setBorderLeft(cell.getCellStyle().getBorderLeft(), cellRangeAddress, aba, testeExcel);
		RegionUtil.setBorderRight(cell.getCellStyle().getBorderRight(), cellRangeAddress, aba, testeExcel);
	}
	
	
	
	
	
}
