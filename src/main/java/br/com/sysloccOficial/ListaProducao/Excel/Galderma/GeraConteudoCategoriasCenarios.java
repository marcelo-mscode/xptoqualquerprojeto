package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.math.BigDecimal;

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
		
	public static void geraConteudoCategorias(XSSFWorkbook excel, XSSFSheet cenario,int linhaComeco,
											 String infoCategoria,double diarias,double quantidade,BigDecimal vlorUnitInicial,BigDecimal vlorUnitNegociado){
		
		int[] cor = {255,255,25};
		
		int linhaParaFormula = linhaComeco+1;
		
		XSSFRow linha2 = cenario.createRow(linhaComeco);
		XSSFCell celulaInfoProduto = linha2.createCell(0);
		//texto
		ExcelFormatoCelulaComum.textWrap(excel, celulaInfoProduto, linha2, infoCategoria , linhaComeco, corFundoCelulaPadrao);
		//Diárias
		ExcelFormatoCelulaComum.numero(excel,linha2, diarias, 1, cor);
		//Qtd
		ExcelFormatoCelulaComum.numero(excel,linha2, quantidade, 2, cor);
		//Valor Unitário Inicial
		XSSFCellStyle estilo = ExcelEstiloPadrao.estiloPadrao(excel, ExcelFonts.font(excel, 12, "Tahoma"));
		ExcelCelulaEspecial.formatoEmReal(excel, estilo, linha2, 3, vlorUnitInicial.doubleValue());
		//SubTotal Inicial Formula
		ExcelCelulaEspecial.formatoFormula(excel, estilo, linha2, 4, "C"+linhaParaFormula+"*B"+linhaParaFormula+"*D"+linhaParaFormula+"");
		//Valor Unitário Negociado
		ExcelCelulaEspecial.formatoEmReal(excel, estilo, linha2, 5, vlorUnitNegociado.doubleValue());
		//Valor Total já Negociado
		ExcelCelulaEspecial.formatoFormula(excel, estilo, linha2, 6, "F"+linhaParaFormula+"*C"+linhaParaFormula+"*B"+linhaParaFormula+"");
	}
	
	
	
	
	
	
}
