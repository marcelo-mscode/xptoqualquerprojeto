package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelCelulaEspecial;
import br.com.sysloccOficial.Excel.ExcelEstiloPadrao;
import br.com.sysloccOficial.Excel.ExcelFonts;
import br.com.sysloccOficial.Excel.ExcelFormatoCelulaComum;

@Component
public class CalculoSubTotalCategoriaTaxaServico {
public static int[] corFundoCelulaPadrao = {255,255,255};
	
	public static void calculoSubTotalCategoriaTaxas(XSSFWorkbook excel, XSSFSheet cenario,
													 int linhaComeco,String tipoServico, double porc1,double porc2,int linhaSubTotal,int linhaPorc){
		
		//linhaSubTotal = linhaSubTotal +1;
		
		XSSFRow linha2 = cenario.createRow(linhaComeco);
		XSSFCell celulaInfoProduto = linha2.createCell(0);
		ExcelFormatoCelulaComum.textoSimples(excel, celulaInfoProduto, linha2, tipoServico, linhaComeco, corFundoCelulaPadrao);
		
		for (int i = 1; i < 3; i++) {
			XSSFCell celulaBco = linha2.createCell(i);
			celulaBco.setCellStyle(ExcelEstiloPadrao.estiloPadrao(excel));
		}
		
		ExcelCelulaEspecial.formatoPorcentagem(excel, ExcelEstiloPadrao.estiloPadrao(excel, ExcelFonts.font(excel, 12, "Tahoma")), porc1 ,linha2,3);
		ExcelCelulaEspecial.formatoFormula(excel, ExcelEstiloPadrao.estiloPadrao(excel), linha2, 4, "E"+linhaSubTotal+"*D"+linhaPorc+"");
		ExcelCelulaEspecial.formatoPorcentagem(excel, ExcelEstiloPadrao.estiloPadrao(excel, ExcelFonts.font(excel, 12, "Tahoma")), porc2 ,linha2,5);
		ExcelCelulaEspecial.formatoFormula(excel, ExcelEstiloPadrao.estiloPadrao(excel), linha2, 6, "G"+linhaSubTotal+"*F"+linhaPorc+"");
		
	}
	
}
