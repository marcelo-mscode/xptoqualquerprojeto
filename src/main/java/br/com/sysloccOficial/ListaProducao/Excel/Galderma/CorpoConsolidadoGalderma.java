package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class CorpoConsolidadoGalderma {
	
	
	
	public static void corpoPlanilhaConsolidado(XSSFWorkbook excelGalderma,XSSFSheet cenario){
		//Cabeçalho estático 
		CorpoConsolidadoGaldermaTopo.geraCabecalhoEstatico(excelGalderma, cenario, 17);
		
		int ultimaLinhaConteudoCategoria =  GeraConteudoConsolidado.geraConteudo(excelGalderma, cenario, 18);

		
	}
	

}
