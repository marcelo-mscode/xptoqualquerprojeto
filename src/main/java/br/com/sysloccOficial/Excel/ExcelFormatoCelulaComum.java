package br.com.sysloccOficial.Excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFormatoCelulaComum {

	public static XSSFFont textWrap(XSSFWorkbook novoExcel,XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula,int[]cor){
			cell.setCellValue(texto);
			XSSFFont font = novoExcel.createFont();
			font.setFontName("Tahoma");
			font.setFontHeightInPoints((short)12);
			cell.setCellStyle(ExcelEstiloPadrao.estiloPadrao(novoExcel,font,true));
			
			return font;
	}
	
	public static void textoSimples(XSSFWorkbook novoExcel,XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula,int[]cor){
		XSSFFont font = textWrap(novoExcel, cell, linha, texto, posicaoCelula, cor);
		cell.setCellValue(texto);
		cell.setCellStyle(ExcelEstiloPadrao.estiloPadrao(novoExcel,font,true));
	}

	

	public static void textoBold(XSSFWorkbook novoExcel,XSSFRow linha,String texto,int posicaoCelula,int[]cor){
		XSSFCell cell = linha.createCell(posicaoCelula);
		XSSFFont font = textWrap(novoExcel, cell, linha, texto, posicaoCelula, cor);
		cell.setCellValue(texto);
		font.setBold(true);
		cell.setCellStyle(ExcelEstiloPadrao.estiloPadrao(novoExcel,font,true));
	}
	public static void textoItalic(XSSFWorkbook novoExcel,XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula,int[]cor){
		cell.setCellValue(texto);
		XSSFFont font = novoExcel.createFont();
		font.setFontName("Tahoma");
		font.setItalic(false);
		font.setFontHeightInPoints((short)12);
		font.setBold(false);
		cell.setCellStyle(ExcelEstiloPadrao.estiloPadrao(novoExcel,font,true));
	}
	
	public static void numero(XSSFWorkbook novoExcel,XSSFRow linha,double numero,int posicaoCelula,int[]cor){
		XSSFCell celulaNova = linha.createCell(posicaoCelula);
		celulaNova.setCellValue(numero);
		XSSFFont font = novoExcel.createFont();
		font.setFontName("Tahoma");
		font.setFontHeightInPoints((short)12);
		celulaNova.setCellStyle(ExcelEstiloPadrao.estiloPadrao(novoExcel,font,true));
	}

	public static void numeroCentralizadoBold(XSSFWorkbook novoExcel,XSSFRow linha,double numero,int posicaoCelula,int[]cor){
		XSSFCell celulaNova = linha.createCell(posicaoCelula);
		celulaNova.setCellValue(numero);
		XSSFFont font = novoExcel.createFont();
		font.setFontName("Tahoma");
		font.setFontHeightInPoints((short)12);
		celulaNova.setCellStyle(ExcelEstiloPadrao.estiloPadraoBoldCentralizado(novoExcel,font));
	}
	
	public static void textoSimplesCentralizadoBold(XSSFWorkbook novoExcel,XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula,int[]cor){
		XSSFCell celulaNova = linha.createCell(posicaoCelula);
		celulaNova.setCellValue(texto);
		XSSFFont font = novoExcel.createFont();
		font.setFontName("Tahoma");
		font.setFontHeightInPoints((short)12);
		celulaNova.setCellStyle(ExcelEstiloPadrao.estiloPadraoBoldCentralizado(novoExcel,font));
	}
	
	
	
}
