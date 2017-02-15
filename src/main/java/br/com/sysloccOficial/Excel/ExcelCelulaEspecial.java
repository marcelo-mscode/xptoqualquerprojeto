package br.com.sysloccOficial.Excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.loader.custom.Return;
import org.springframework.stereotype.Component;



@Component
public class ExcelCelulaEspecial {
		
	public static XSSFCellStyle formatoEmReal(XSSFWorkbook excel, XSSFCellStyle estilo,XSSFRow linha,int posicaoCelula,double valor){
		estilo.setDataFormat(excel.createDataFormat().getFormat("R$		    #,##0.00"));
		XSSFCell celulaNova = linha.createCell(posicaoCelula);
		celulaNova.setCellValue(valor);
		celulaNova.setCellStyle(estilo);
		return estilo;
	}

	public static void formatoFormula(XSSFWorkbook excel,XSSFCellStyle estilo,XSSFRow linha,int posicaoCelula,String valor){
		estilo.setDataFormat(excel.createDataFormat().getFormat("R$		    #,##0.00"));
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		XSSFCell celulaNova = linha.createCell(posicaoCelula);
		celulaNova.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		celulaNova.setCellStyle(estilo);
		celulaNova.setCellFormula(valor);
	}
	
	public static XSSFCell formatoPorcentagem(XSSFWorkbook excel, XSSFCellStyle estilo,double valor,XSSFRow linha,int posicaoCelula){
		double valorPorc = valor/100;
		XSSFCell cell = linha.createCell(posicaoCelula);
		estilo.setDataFormat(excel.createDataFormat().getFormat("0.0%"));
		estilo.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cell.setCellStyle(estilo);
		cell.setCellValue(valorPorc);
		
		return cell;
		
	}
	
}	
