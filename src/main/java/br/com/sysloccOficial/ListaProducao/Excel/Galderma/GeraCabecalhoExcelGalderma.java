package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class GeraCabecalhoExcelGalderma {
	
	
	
	public static void geraCabecalho(XSSFSheet aba,XSSFWorkbook excelGalderma,String nomeAba){
	
		textoContato(aba, excelGalderma, "Gestor: A definir Paula Samora", 1);
		textoContato(aba, excelGalderma, "Telefone: 11 3524-6498", 2);
		textoContato(aba, excelGalderma, "Email: paula.samora@galderma.com", 3);
		
		textoContato(aba, excelGalderma, "Compras: Paula Samora", 4);
		textoContato(aba, excelGalderma, "Telefone: 11 3524-6498", 5);
		textoContato(aba, excelGalderma, "Email: paula.samora@galderma.com", 6);
		
		textoContato(aba, excelGalderma, "Fornecedor: A definir Paula Samora", 7);
		textoContato(aba, excelGalderma, "Telefone: 11 3524-6498", 8);
		textoContato(aba, excelGalderma, "Email: paula.samora@galderma.com", 9);
		
		geraCabecalhoEvento(aba, excelGalderma, nomeAba);
	}
	
	private static void textoContato(XSSFSheet aba,XSSFWorkbook excelGalderma,String texto,int posicaoLinha){
		
		XSSFRow linha = aba.createRow(posicaoLinha);
		linha.setHeightInPoints(20);
		
		XSSFCell celula1 = linha.createCell(2);
		XSSFCell celula2 = linha.createCell(3);
		XSSFCell celula3 = linha.createCell(4);
		celula1.setCellValue(texto);
		
		XSSFCellStyle estilo = EstilosGaldema.estiloCabecalhoContato(excelGalderma);
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		celula1.setCellStyle(estilo);
		celula2.setCellStyle(estilo);
		celula3.setCellStyle(estilo);
	}

	
	public static void geraCabecalhoEvento(XSSFSheet aba,XSSFWorkbook excelGalderma,String nomeAba){
		textoEvento(aba,excelGalderma, "EVENTO: Festa de Confraternização Farmacologia Bayer",12); 
		textoEvento(aba,excelGalderma, "DATA: 13 A 17 DE MARÇO DE 2017",13); 
		textoEvento(aba,excelGalderma, "LOCAL: CAMPINAS / HORTOLÂNDIA",14); 
		textoEvento(aba,excelGalderma, "NÚMEROS DE PESSOAS",15); 
	}
	
	private static void textoEvento(XSSFSheet aba,XSSFWorkbook excelGalderma,String texto,int posicaoLinha){
		
		XSSFRow linha = aba.createRow(posicaoLinha);
		linha.setHeightInPoints(25);
		
		XSSFCell celula1 = linha.createCell(0);
		XSSFCell celula2 = linha.createCell(1);
		XSSFCell celula3 = linha.createCell(2);
		XSSFCell celula4 = linha.createCell(3);
		XSSFCell celula5 = linha.createCell(4);
		celula1.setCellValue(texto);
		
		XSSFCellStyle estilo = EstilosGaldema.estiloCabecalhoEvento(excelGalderma);
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		celula1.setCellStyle(estilo);
		celula2.setCellStyle(estilo);
		celula3.setCellStyle(estilo);
		celula4.setCellStyle(estilo);
		celula5.setCellStyle(estilo);
		
	}
	
}
