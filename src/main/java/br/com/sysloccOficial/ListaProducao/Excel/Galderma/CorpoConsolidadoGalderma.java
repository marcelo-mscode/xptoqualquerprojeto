package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class CorpoConsolidadoGalderma {
	
	public static void corpoPlanilhaConsolidado(XSSFWorkbook excelGalderma,XSSFSheet cenario,List<LinhasConsolidado> linhasParaConsolidado){
		//Cabeçalho estático 
		CorpoConsolidadoGaldermaTopo.geraCabecalhoEstatico(excelGalderma, cenario, 17);
		int linhaComecaConteudo = 18;
		int numCenario = 1;
		
		
		for (int i = 0; i < linhasParaConsolidado.size(); i++) {
			GeraConteudoConsolidado.geraConteudo(excelGalderma, cenario,linhasParaConsolidado.get(i).getNomeAba(),linhaComecaConteudo,linhasParaConsolidado.get(i).getUltimaLinhaCalculos(),numCenario);
			linhaComecaConteudo++;
			numCenario++;
		}
		
	}
}
