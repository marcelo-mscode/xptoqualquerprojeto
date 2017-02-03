package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelCelulaEspecial;
import br.com.sysloccOficial.Excel.ExcelEstiloPadrao;
import br.com.sysloccOficial.Excel.ExcelFonts;
import br.com.sysloccOficial.Excel.ExcelFormatoCelulaComum;


@Component
public class GeraConteudoCategoriasCenarios {
	
	public static int[] corFundoCelulaPadrao = {255,255,254};
		
	public static int geraConteudoCategorias(XSSFWorkbook excel, XSSFSheet cenario,int linhaComeco,String infoCategoria){
		
		int[] cor = {255,255,25};
		
		int linhaParaFormula = linhaComeco+1;
		
		XSSFRow linha2 = cenario.createRow(linhaComeco);
		XSSFCell celulaInfoProduto = linha2.createCell(0);
		//texto
		ExcelFormatoCelulaComum.textWrap(excel, celulaInfoProduto, linha2, infoCategoria , linhaComeco, corFundoCelulaPadrao);
		//Diárias
		ExcelFormatoCelulaComum.numero(excel,linha2, 4, 1, cor);
		//Qtd
		ExcelFormatoCelulaComum.numero(excel,linha2, 20, 2, cor);
		//Valor Unitário Inicial
		XSSFCellStyle estilo = ExcelEstiloPadrao.estiloPadrao(excel, ExcelFonts.font(excel, 12, "Tahoma"));
		ExcelCelulaEspecial.formatoEmReal(excel, estilo, linha2, 3, 390.00);
		//SubTotal Inicial Formula
		ExcelCelulaEspecial.formatoFormula(excel, estilo, linha2, 4, "C"+linhaParaFormula+"*B"+linhaParaFormula+"*D"+linhaParaFormula+"");
		//Valor Unitário Negociado
		ExcelCelulaEspecial.formatoEmReal(excel, estilo, linha2, 5, 390.00);
		//Valor Total já Negociado
		ExcelCelulaEspecial.formatoFormula(excel, estilo, linha2, 6, "F"+linhaParaFormula+"*C"+linhaParaFormula+"*B"+linhaParaFormula+"");
		
		return linhaParaFormula;
		/*subTotalCategorias(excel, cenario, linhaParaFormula+1);*/
		
	}
	
	
	
	
	
	
}
