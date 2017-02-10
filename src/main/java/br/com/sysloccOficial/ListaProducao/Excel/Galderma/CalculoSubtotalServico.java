package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import br.com.sysloccOficial.Excel.ExcelCelulaEspecial;
import br.com.sysloccOficial.Excel.ExcelEstiloPadrao;
import br.com.sysloccOficial.Excel.ExcelFormatoCelulaComum;

@Component
public class CalculoSubtotalServico {

	public static int[] corFundoCelulaPadrao = {255,255,255};
	
	public static void totalDeServico(XSSFWorkbook excel, XSSFSheet cenario, int linhaComeco,int linhaSubTotal, int linhaISS){
		XSSFRow linha = cenario.createRow(linhaComeco);
		ExcelFormatoCelulaComum.textoBold(excel,linha, "Total deste serviço", 0, corFundoCelulaPadrao);
		
		for (int i = 1; i < 4; i++) {
			XSSFCell celulaBco = linha.createCell(i);
			celulaBco.setCellStyle(ExcelEstiloPadrao.estiloPadrao(excel));
		}
	
		ExcelCelulaEspecial.formatoFormula(excel, ExcelEstiloPadrao.estiloPadraoBold(excel), linha, 4, "SUM(E"+linhaSubTotal+":E"+linhaISS+")");
		XSSFCell celulaBco = linha.createCell(5);celulaBco.setCellStyle(ExcelEstiloPadrao.estiloPadrao(excel));
		ExcelCelulaEspecial.formatoFormula(excel, ExcelEstiloPadrao.estiloPadraoBold(excel), linha, 6, "SUM(G"+linhaSubTotal+":G"+linhaISS+")");
		
	}
	
	
}
