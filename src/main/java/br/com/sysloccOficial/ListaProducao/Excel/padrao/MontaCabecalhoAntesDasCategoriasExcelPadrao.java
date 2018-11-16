package br.com.sysloccOficial.ListaProducao.Excel.padrao;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class MontaCabecalhoAntesDasCategoriasExcelPadrao {
			
	
	private String INICIO_CABECALHO_LINHA;


	public MontaCabecalhoAntesDasCategoriasExcelPadrao(String INICIO_CABECALHO_LINHA){
		INICIO_CABECALHO_LINHA = this.INICIO_CABECALHO_LINHA;
		
	}
	
	
	private void montaCabecalhoAntesDasCategorias(WritableSheet sheet)
			throws WriteException, RowsExceededException {
		//Adding A Label
		Label linha = new Label(0,INICIO_CABECALHO_LINHA,"Linha",formataCabecalho());
		Label FatLocco = new Label(1,INICIO_CABECALHO_LINHA,"Fat loCCo",formataCabecalho());
		Label FatDireto = new Label(2,INICIO_CABECALHO_LINHA,"Fat Direto/Nota de Debito",formataCabecalho());
		Label Opcional = new Label(3,INICIO_CABECALHO_LINHA,"Opcional",formataCabecalho());
		Label Info = new Label(4,INICIO_CABECALHO_LINHA,"Informações",formataCabecalho());
		Label NaoIncluso = new Label(5,INICIO_CABECALHO_LINHA,"Não inclusos no custo",formataCabecalho());
		sheet.addCell(linha);
		sheet.addCell(FatLocco);
		sheet.addCell(FatDireto);
		sheet.addCell(Opcional);
		sheet.addCell(Info);
		sheet.addCell(NaoIncluso);
	}
	
	
}
