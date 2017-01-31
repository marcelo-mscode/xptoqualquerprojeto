package br.com.sysloccOficial.controllerExcel;

import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ChamaEstilo {

	
	/**
	 * Estilo para Célula com: todas as bordas finas, cor de fundo Branca, 
	 * fonte alinhada a direita, mesclagem e valor na celula. 
	 * 
	 * @param row
	 * @param w
	 * @param planilha
	 * @param posicaoLinha
	 * @param comecaColuna
	 * @param terminaColuna
	 * @param valor
	 */
	public void bAlinhVertDMesc(XSSFRow row,XSSFWorkbook w,XSSFSheet planilha, int posicaoLinha,
									   int comecaColuna, int terminaColuna,String valor ){
		Cell cell = row.createCell((short)4);
		cell.setCellValue(valor);
		cell.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleDireita(w));
		AuxExcelEstilos.mescla(planilha, posicaoLinha, posicaoLinha,comecaColuna,terminaColuna);
		
		// for usado para criar as bordas em volta da mesclagem da célula.
		Cell cell5 = row.createCell((short)5);
		cell5.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleDireita(w));	
		
	}
	
	/**
	 * Estilo de Célula com: todas as bordas, alinhamento do texto ao centro,
	 * sem cor de fundo
	 * 
	 * 
	 * @param row
	 * @param w
	 * @param posicaoCelula
	 * @param valor
	 */
	public void bAlinhVertC(XSSFRow row,XSSFWorkbook w,int posicaoCelula, String valor){
		Cell cellAux = row.createCell(posicaoCelula);
		cellAux.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleCentral(w));
		cellAux.setCellValue(valor);
	}
	
	
	/**
	 * Estilo de Célula com: todas as bordas, cor de fundo e valor em double
	 * 
	 * @param row
	 * @param w
	 * @param posicaoCelula
	 * @param corFundo
	 * @param valor
	 */
	public void bDoubleCorFund(XSSFRow row,XSSFWorkbook w, int posicaoCelula, int []corFundo, double valor ){
		Cell cellValor = row.createCell(posicaoCelula);
		cellValor.setCellStyle(AuxExcelEstilos.estiloBordaCordeFundoSemBold(w,corFundo));
		cellValor.setCellValue(valor);
	}

	/**
	 * Estilo de Célula com: todas as bordas, cor de fundo e valor em Porcentagem
	 * 
	 * @param row
	 * @param w
	 * @param posicaoCelula
	 * @param corFundo
	 * @param valor
	 */
	public void bDoubleCorFundPorcentagem(XSSFRow row,XSSFWorkbook w, int posicaoCelula, int []corFundo, double valor ){
		Cell cellValor = row.createCell(posicaoCelula);
		cellValor.setCellStyle(AuxExcelEstilos.estiloBordaCordeFundoSemBold(w,corFundo));
		cellValor.setCellValue(valor);
	}

	
	
	
	
	
	/**
	 * Estilo de Célula com: todas as bordas, cor de fundo e valor em double sem formatação para moeda
	 * 
	 * @param row
	 * @param w
	 * @param posicaoCelula
	 * @param corFundo
	 * @param valor
	 */
	public void bDoubleCorFundSemMoeda(XSSFRow row,XSSFWorkbook w, int posicaoCelula, int []corFundo, double valor ){
		Cell cellValor = row.createCell(posicaoCelula);
		cellValor.setCellStyle(AuxExcelEstilos.estiloBordaCordeFundoSemBoldAlinCentro(w,corFundo));
		cellValor.setCellValue(valor);
	}
	
	
	/**
	 * Estilo de Célula com: todas as bordas, cor de fundo e valor em String
	 * 
	 * @param row
	 * @param w
	 * @param posicaoCelula
	 * @param corFundo
	 * @param valor
	 */
	public void bStringCorFund(XSSFRow row,XSSFWorkbook w, int posicaoCelula, int []corFundo, String valor ){
		Cell cellValor = row.createCell(posicaoCelula);
		cellValor.setCellStyle(AuxExcelEstilos.estiloBordaCordeFundoSemBoldAlinCentro(w,corFundo));
		cellValor.setCellValue(valor);
	}

	/**
	 * Estilo de Célula com: todas as bordas, cor de fundo, valor em String,
	 * Bold, alinhamento horizontal ao centro
	 * 
	 * @param row
	 * @param w
	 * @param posicaoCelula
	 * @param corFundo
	 * @param valor
	 */
	public void bStringCorFundBoldCentro(XSSFRow row,XSSFWorkbook w, int posicaoCelula, int []corFundo, String valor ){
		Cell cellValor = row.createCell(posicaoCelula);
		cellValor.setCellStyle(AuxExcelEstilos.estiloBordaCorFundoBoldAlinCentro(w,corFundo));
		cellValor.setCellValue(valor);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
