package br.com.sysloccOficial.ListaProducao.Excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GeraCelulaTexto {

	private XSSFSheet aba;
	private XSSFWorkbook novoExcel;



	public GeraCelulaTexto(XSSFSheet abaOpcionais,XSSFWorkbook novoExcel){
		this.aba = abaOpcionais;
		this.novoExcel = novoExcel;
	}
	
	public void celulaBasica(int linha,String texto,int posicaoCelula,int alturaLinha){
		celulaBasicaGera(linha,texto, posicaoCelula,alturaLinha);
	}
	
	public void celulaBorda(int linha,String texto,int posicaoCelula,int[] corFundo){
		XSSFCell cell = celulaBasicaGera(linha,texto, posicaoCelula,15);
		cell.setCellStyle(new GeraStyloCelula(novoExcel).styloBorda(corFundo));
	}

	
	public XSSFCell celulaBorda(int linha,String texto,int posicaoCelula,int[] corFundo,boolean italic,boolean bold){
		XSSFCell cell = celulaBasicaGera(linha,texto, posicaoCelula,15);
		GeraStyloCelula styloItalic = new GeraStyloCelula(novoExcel);
		XSSFCellStyle corCelula = styloItalic.styloBorda(corFundo);
		XSSFFont font = novoExcel.createFont();
		font.setItalic(true);
		font.setBold(bold);
		corCelula.setFont(font);
		cell.setCellStyle(corCelula);
		return cell;
	}

	public XSSFCell celulaBorda(int linha,String texto,int posicaoCelula,int[] corFundo,boolean italic,boolean bold,short alinhamento){
		XSSFCell cell = celulaBasicaGera(linha,texto, posicaoCelula,15);
		GeraStyloCelula styloItalic = new GeraStyloCelula(novoExcel);
		XSSFCellStyle corCelula = styloItalic.styloBorda(corFundo);
		corCelula.setAlignment(alinhamento);
		XSSFFont font = novoExcel.createFont();
		font.setItalic(true);
		font.setBold(bold);
		corCelula.setFont(font);
		cell.setCellStyle(corCelula);
		return cell;
	}

	public XSSFCell celulaBorda(XSSFRow linha,String texto,int posicaoCelula,int[] corFundo,boolean italic,boolean bold,short alinhamento){
		
		linha.setHeightInPoints(15);
		XSSFCell cell = linha.createCell(posicaoCelula);
		cell.setCellValue(texto);
		
		GeraStyloCelula styloItalic = new GeraStyloCelula(novoExcel);
		XSSFCellStyle corCelula = styloItalic.styloBorda(corFundo);
		corCelula.setAlignment(alinhamento);
		XSSFFont font = novoExcel.createFont();
		font.setItalic(italic);
		font.setBold(bold);
		corCelula.setFont(font);
		cell.setCellStyle(corCelula);
		return cell;
	}

	public void celulaBorda(int linha,String texto,int posicaoCelula,int[] corFundo,boolean italic,boolean bold,int[] rangeMerge){
		XSSFCell cell = celulaBorda(linha,texto,posicaoCelula,corFundo,italic,bold);
		merge(cell, linha, rangeMerge);
	}

	public XSSFRow celulaBordaComTextoSeguidoFormula(int linha,String texto,int posicaoCelula,int[] corFundo,boolean italic,boolean bold,int[] rangeMerge){
		XSSFCell cell = celulaBorda(linha,texto,posicaoCelula,corFundo,italic,bold);
		merge(cell, linha, rangeMerge);
	    XSSFRow infoLinha =	cell.getRow();
		return infoLinha;
	}

	public XSSFRow celulaBordaComTextoSeguidoFormula(int linha,String texto,int posicaoCelula,int[] corFundo,boolean italic,boolean bold,int[] rangeMerge,short alinhamento){
		XSSFCell cell = celulaBorda(linha,texto,posicaoCelula,corFundo,italic,bold,alinhamento);
		merge(cell, linha, rangeMerge);
		XSSFRow infoLinha =	cell.getRow();
		return infoLinha;
	}
	
	public XSSFRow celulaBordaComTextoSeguidoFormula(XSSFRow infoLinha,String texto,int posicaoCelula,int[] corFundo,boolean italic,boolean bold,int[] rangeMerge,short alinhamento,int alturaLinha){
		
		XSSFCell cell = infoLinha.createCell(posicaoCelula);
		infoLinha.setHeightInPoints(alturaLinha);
		cell.setCellValue(texto);
		
		GeraStyloCelula styloItalic = new GeraStyloCelula(novoExcel);
		XSSFCellStyle corCelula = styloItalic.styloBorda(corFundo);
		corCelula.setAlignment(alinhamento);
		corCelula.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		XSSFFont font = novoExcel.createFont();
		font.setItalic(italic);
		font.setBold(bold);
		corCelula.setFont(font);
		cell.setCellStyle(corCelula);
		
		
		merge(cell, infoLinha, rangeMerge);
		return infoLinha;
	}
	
	/**
	 * Método para celula com formula
	 * @param linha
	 * @param texto
	 * @param posicaoCelula
	 * @param corFundo
	 * @param italic
	 * @param bold
	 * @param rangeMerge
	 * @param formula
	 */
	public void celulaBorda(XSSFRow infoLinha,String texto,int posicaoCelula,int[] corFundo,boolean italic,boolean bold,int[] rangeMerge,String formula){
		
		XSSFCell cell = infoLinha.createCell(6);
		infoLinha.setHeightInPoints(15);
		cell = infoLinha.createCell(posicaoCelula);
		cell.setCellValue(texto);
		
		GeraStyloCelula styloItalic = new GeraStyloCelula(novoExcel);
		XSSFCellStyle corCelula = styloItalic.styloBorda(corFundo);
		XSSFFont font = novoExcel.createFont();
		font.setItalic(true);
		font.setBold(bold);
		corCelula.setFont(font);
		cell.setCellStyle(corCelula);
	
		celulaComFormula(cell, formula, posicaoCelula);
		merge(cell, infoLinha.getRowNum(), rangeMerge);
	
	
	}
	
	public void merge(XSSFCell cell,int linha,int[] rangeMerge){
		GeraMergeLinhaComStylodaLinha merge = new GeraMergeLinhaComStylodaLinha(aba, novoExcel);
		merge.geraMerge(cell, linha, rangeMerge[0], rangeMerge[1]);
	}

	public void merge(XSSFCell cell,XSSFRow infoLinha,int[] rangeMerge){
		GeraMergeLinhaComStylodaLinha merge = new GeraMergeLinhaComStylodaLinha(aba, novoExcel);
		merge.geraMerge(cell, infoLinha, rangeMerge[0], rangeMerge[1]);
	}
	
	public XSSFCell celulaBasicaGera(int numLinha, String texto,int posicaoCelula,int alturaLinha){
		XSSFRow linha = aba.createRow(numLinha);
		XSSFCell cell = linha.createCell(0);
		cell = linha.createCell(posicaoCelula);
		cell.setCellValue(texto);
		return cell;
	}
	
	public void celulaComFormula(XSSFCell cell,String valor,int posicaoCelula){
		
		XSSFCellStyle corCelula = cell.getCellStyle();
		corCelula.setDataFormat(novoExcel.createDataFormat().getFormat("R$    #,##0.00"));
		corCelula.setAlignment(XSSFCellStyle.ALIGN_LEFT);

		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula(valor);
}
	
	
}
