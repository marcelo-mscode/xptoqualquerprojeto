package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelCelulaEspecial;
import br.com.sysloccOficial.Excel.ExcelEstiloPadrao;
import br.com.sysloccOficial.Excel.ExcelFormatoCelulaComum;


@Component
public class GeraSubTotalCategorias {
	
	public static int[] corFundoCelulaPadrao = {255,255,254};
	
	
	
	public static void subTotalCategorias(XSSFWorkbook excel, XSSFSheet cenario,int primeiraLinhaGrupoCategoria,int ultimaLinhaConteudoCategoria){
		
		XSSFRow linha2 = cenario.createRow(ultimaLinhaConteudoCategoria);
		ExcelFormatoCelulaComum.textoBold(excel,linha2, "Subtotal", 0, corFundoCelulaPadrao);
		
		for (int i = 1; i < 4; i++) {
			XSSFCell celulaBco = linha2.createCell(i);
			celulaBco.setCellStyle(ExcelEstiloPadrao.estiloPadrao(excel));
		}
		
		if(primeiraLinhaGrupoCategoria == null){
			
		}
		
		//Soma SubTotalInicial
		ExcelCelulaEspecial.formatoFormula(excel, ExcelEstiloPadrao.estiloPadraoBold(excel), linha2, 4, "SUM(E"+primeiraLinhaGrupoCategoria+":E"+ultimaLinhaConteudoCategoria+")");

		XSSFCell celulaBco = linha2.createCell(5);
		celulaBco.setCellStyle(ExcelEstiloPadrao.estiloPadrao(excel));
		
		//Soma Valor Total já negociado
		ExcelCelulaEspecial.formatoFormula(excel, ExcelEstiloPadrao.estiloPadraoBold(excel), linha2, 6, "SUM(G"+primeiraLinhaGrupoCategoria+":G"+ultimaLinhaConteudoCategoria+")");
		
		
	}
	
	
	
}
