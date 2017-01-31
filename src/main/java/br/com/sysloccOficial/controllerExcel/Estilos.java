package br.com.sysloccOficial.controllerExcel;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface Estilos {
	
	
	/**
	 * Determina que toda a célula terá:
	 * borda fina, uma cor de fundo, alinhamento horizontal central,
	 * alinhamento à esquerda, font bold e tamanho definado na chamada
	 * do método
	 * @param w
	 * @param cor
	 * @return
	 */
	public XSSFCellStyle bordaCorFundoverticalCentralEsquerda(XSSFWorkbook w,int[]cor,int tamanhoFonte);
	
	
	
	
	
	
}
