package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import br.com.sysloccOficial.Excel.ExcelBordas;
import br.com.sysloccOficial.Excel.ExcelCelulaBackground;
import br.com.sysloccOficial.Excel.ExcelFonts;

@Component
public class CorpoConsolidadoGaldermaTopo {
		
	public static int[] corFundoCelulaPadrao = {255,255,254};
	
	public static void geraCabecalhoEstatico(XSSFWorkbook excel,XSSFSheet cenario,int linhaComecoTopo){
		
		String[] textosCabecalho = {"Serviços","Valor Inicial","Valor Negociado","Qtd.Pax","Valor per capita","Saving","Deadline","Observação"};
		
		cenario.setColumnWidth(0, 7500);
		cenario.setColumnWidth(1, 7500);
		cenario.setColumnWidth(2, 7500);
		cenario.setColumnWidth(3, 7500);
		cenario.setColumnWidth(4, 7500);
		cenario.setColumnWidth(5, 7500);
		cenario.setColumnWidth(6, 7500);
		cenario.setColumnWidth(7, 7500);
		
		XSSFRow linha = cenario.createRow(linhaComecoTopo);
		linha.setHeightInPoints(34);
		
		XSSFCellStyle estilo = excel.createCellStyle();
		estilo = ExcelCelulaBackground.background(estilo, new int[]{47,117,181});
		estilo = ExcelBordas.borda(estilo);
		estilo.setFont(ExcelFonts.fontBold(excel, 12,"Tahoma", corFundoCelulaPadrao));
		estilo.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		for (int i = 0; i < textosCabecalho.length; i++) {
			XSSFCell celulaEstimat = linha.createCell(i);
			celulaEstimat.setCellValue(textosCabecalho[i]);
			estilo.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			celulaEstimat.setCellStyle(estilo);
		}
		
	}
	
}
