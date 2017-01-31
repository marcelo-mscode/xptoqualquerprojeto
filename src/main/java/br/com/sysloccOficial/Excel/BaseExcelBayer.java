package br.com.sysloccOficial.Excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;


@Component
public class BaseExcelBayer{

	private String caminhoNomeArquivo;
	private String complementoNomeArquivo;

	public BaseExcelBayer(){}
	public BaseExcelBayer(String caminhoNomeArquivo){
		this.caminhoNomeArquivo = caminhoNomeArquivo;
	}
	public String metodoComplementaNomeArquivo(){
		Calendar c = Calendar.getInstance(); 
		SimpleDateFormat s = new SimpleDateFormat("HHmmss"); 
		String complemento = s.format(c.getTime());
		complementoNomeArquivo = complemento;
		return complemento;
	}
	public FileOutputStream caminhoeNomeDoArquivo() throws FileNotFoundException{
		caminhoNomeArquivo = caminhoNomeArquivo+metodoComplementaNomeArquivo()+".xlsx";
		FileOutputStream out = new FileOutputStream(new File (caminhoNomeArquivo));
		return out;
	}
	public String caminhoDownloadExcel(String caminhoNomeArquivo,String caminho) throws FileNotFoundException{
		String downloadExcel = "upload/upload/excel/"+caminhoNomeArquivo+complementoNomeArquivo+".xlsx";
		return downloadExcel;
	}
	
	public void criaLinhaECelulaTexto(XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula, XSSFWorkbook novoExcel,int[]cor){
		cell = linha.createCell(posicaoCelula);
		cell.setCellValue(texto);
		
		XSSFFont font = novoExcel.createFont();
		font.setFontName("Arial");
		font.setItalic(true);
		font.setFontHeightInPoints((short)9);
		font.setBold(true);
		
		XSSFCellStyle corCelula = novoExcel.createCellStyle();
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(cor[0], cor[1], cor[2])));
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		corCelula.setAlignment(XSSFCellStyle.ALIGN_CENTER);
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
		corCelula.setWrapText(true);
		cell.setCellStyle(corCelula);
			
	} 

	public void celulaTextoSemFormatacao(XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula, XSSFWorkbook novoExcel,int[]cor){
		cell = linha.createCell(posicaoCelula);
		cell.setCellValue(texto);
		XSSFFont font = novoExcel.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short)10);
		XSSFCellStyle corCelula = novoExcel.createCellStyle();
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(cor[0], cor[1], cor[2])));
		corCelula.setFont(font);
		corCelula.setWrapText(true);
		cell.setCellStyle(corCelula);
	} 
	
	
	
	public void fechaPlanilha(XSSFWorkbook workbook, FileOutputStream out) throws IOException {
		workbook.write(out);
		out.close();
	}
	
	
	
	
}
