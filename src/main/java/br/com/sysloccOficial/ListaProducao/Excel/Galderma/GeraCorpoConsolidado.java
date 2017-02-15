package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelImagem;



@Component
public class GeraCorpoConsolidado {

	public static XSSFSheet geraCorpoAbaConsolidado(XSSFSheet consolidado,XSSFWorkbook excelGalderma,String nomeAba) throws FileNotFoundException, IOException{
			consolidado = excelGalderma.createSheet(nomeAba); /** Cria Aba Consolidado da planilha */
			consolidado.setZoom(80);
			ExcelImagem.InsereImagem(excelGalderma, consolidado, "C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia2.png",0.67);
			GeraCabecalhoExcelGalderma.geraCabecalho(consolidado, excelGalderma, nomeAba);
			
			return consolidado;
			/*CorpoConsolidadoGalderma.corpoPlanilhaConsolidado(excelGalderma, consolidado,linhasConsolidado);*/
		}
}
