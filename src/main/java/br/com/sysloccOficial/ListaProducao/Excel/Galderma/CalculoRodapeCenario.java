package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.ExcelBordas;
import br.com.sysloccOficial.Excel.ExcelCelulaBackground;
import br.com.sysloccOficial.Excel.ExcelCelulaEspecial;
import br.com.sysloccOficial.Excel.ExcelFonts;
import br.com.sysloccOficial.Excel.ExcelMerge;
import br.com.sysloccOficial.conf.Utilitaria;

@Component
public class CalculoRodapeCenario {
	
	private static XSSFCellStyle estilo;
	
	public static void calculosRodapePlanilha(XSSFWorkbook excelGalderma,XSSFSheet cenario,int ultimaLinhaCorpo,List<Integer[]> linhasSubtotais){
		
		
		if(cenario.getSheetName().equals("Opcionais")){
			int i = linhasSubtotais.get(0)[0] - 6;
			String formulaInicialOpc = "SUM(E"+i+":E"+ultimaLinhaCorpo+")";
			String formulaNegociadoOpc = "SUM(G"+i+":G"+ultimaLinhaCorpo+")";
			CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+1, "Subtotal Geral",new int[]{0,176,240},formulaInicialOpc,formulaNegociadoOpc);

			String NTformulaInicialOpc = "E"+(ultimaLinhaCorpo+2);
			String NTformulaNegociadoOpc = "G"+(ultimaLinhaCorpo+2);
			CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+2, "Investimento - Serviços Terceiros - PGTO VIA NOTA DE DÉBITO",new int[]{219,219,219},NTformulaInicialOpc,NTformulaNegociadoOpc);
			
			/*String ServicosAgenciaformulaInicialOpc = "D29";
			String ServicosAgenciaformulaNegociadoOpc = "D29";
			CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+3, "Investimento - Serviços Agência",new int[]{219,219,219},ServicosAgenciaformulaInicialOpc,ServicosAgenciaformulaNegociadoOpc	);*/
			
		}else{
			String formulaInicial = montaFormulaParaCalculoSutotalGeral(linhasSubtotais, "E");
			String formulaNegociado = montaFormulaParaCalculoSutotalGeral(linhasSubtotais, "G");
			CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+1, "Subtotal Geral",new int[]{0,176,240},formulaInicial,formulaNegociado);

			String NTformulaInicial = montaFormulaParaPGTOVIANTDebito(linhasSubtotais, "E");
			String NTformulaNegociado = montaFormulaParaPGTOVIANTDebito(linhasSubtotais, "G");
			CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+2, "Investimento - Serviços Terceiros - PGTO VIA NOTA DE DÉBITO",new int[]{219,219,219},NTformulaInicial,NTformulaNegociado);
		}
		
		
		String ServicosAgenciaformulaInicial = montaFormulaServicosAgencia(linhasSubtotais, "E");
		String ServicosAgenciaformulaNegociado = montaFormulaServicosAgencia(linhasSubtotais, "G");
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+3, "Investimento - Serviços Agência",new int[]{219,219,219},ServicosAgenciaformulaInicial,ServicosAgenciaformulaNegociado);
		
		//(E82+E83)*D84
		
		String margem10 = "(E"+(ultimaLinhaCorpo+3)+"+E"+(ultimaLinhaCorpo+4)+")*D"+(ultimaLinhaCorpo+5);
		String margem10Negociado = "(G"+(ultimaLinhaCorpo+3)+"+G"+(ultimaLinhaCorpo+4)+")*F"+(ultimaLinhaCorpo+5);
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+4, "Margem + 10% Extras",new int[]{219,219,219},10,margem10,margem10Negociado);
	
		String feeAgenciaInicial = "E"+(ultimaLinhaCorpo+3)+"*D"+(ultimaLinhaCorpo+6);
		String feeAgenciaNegociado = "G"+(ultimaLinhaCorpo+3)+"*F"+(ultimaLinhaCorpo+6);
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+5, "FEE Agência",new int[]{219,219,219},5.2,feeAgenciaInicial,feeAgenciaNegociado);
		
		String ImpostoInicial = "E"+(ultimaLinhaCorpo+4)+"*D"+(ultimaLinhaCorpo+6);
		String ImpostoNegociado = "G"+(ultimaLinhaCorpo+4)+"*F"+(ultimaLinhaCorpo+6);
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+6, "Impostos Emissão NF - Serviços Agência",new int[]{219,219,219},22.9,ImpostoInicial,ImpostoNegociado);
		
		String TotalPrevistoInicial = "SUM(E"+(ultimaLinhaCorpo+3)+":E"+(ultimaLinhaCorpo+6)+")";
		String TotalPrevistoNegociado = "SUM(G"+(ultimaLinhaCorpo+3)+":G"+(ultimaLinhaCorpo+6)+")";
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, ultimaLinhaCorpo+7, "TOTAL PREVISTO",new int[]{0,176,240},TotalPrevistoInicial,TotalPrevistoNegociado);
		
	}

	private static String montaFormulaServicosAgencia(List<Integer[]> linhasSubtotais,String letra) {
		String formulaInicial = "";
		for (int j = 0; j < linhasSubtotais.size(); j++) {
			if(j <= linhasSubtotais.size()&& linhasSubtotais.get(j)[1] == 8){
				formulaInicial = formulaInicial+letra+linhasSubtotais.get(j)[0]+"+";
			}
		}
		try {
			formulaInicial = Utilitaria.retiraUltimoCaracter(formulaInicial);
			return formulaInicial;
		} catch (Exception e) {
			return "0";
		}
		
	}
	
	
	
	private static String montaFormulaParaPGTOVIANTDebito(List<Integer[]> linhasSubtotais,String letra) {
		String formulaInicial = "";
		for (int j = 0; j < linhasSubtotais.size(); j++) {
			if(j <= linhasSubtotais.size()&& linhasSubtotais.get(j)[1] != 8){
				formulaInicial = formulaInicial+letra+linhasSubtotais.get(j)[0]+"+";
			}
		}		
		try {
			formulaInicial = Utilitaria.retiraUltimoCaracter(formulaInicial);
			return formulaInicial;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	private static String montaFormulaParaCalculoSutotalGeral(List<Integer[]> linhasSubtotais,String letra) {
		String formulaInicial = "";
		for (int j = 0; j < linhasSubtotais.size(); j++) {
			if(j <= linhasSubtotais.size()){
				formulaInicial = formulaInicial+letra+linhasSubtotais.get(j)[0]+"+";
			}
		}		
		try {
			formulaInicial = Utilitaria.retiraUltimoCaracter(formulaInicial);
			return formulaInicial;
		} catch (Exception e) {
			return null;
		}
	}

	
	/**
	 * Método que faz a linha de 
	 * SubTotal Geral
	 * Investimentos de Terceiros
	 * Investimentos de Agência
	 * 
	 * @param excelGalderma
	 * @param cenario
	 */
	public static XSSFRow montaCelulas(XSSFWorkbook excelGalderma,XSSFSheet cenario,int numLinha,String texto,int[] corFundo,String formulaInicial, String formulaNegociado){
		
		XSSFRow linha = cenario.createRow(numLinha);
		XSSFCell cell = linha.createCell(0);
		
		estilo = excelGalderma.createCellStyle();
		estilo.setFont(ExcelFonts.fontBold(excelGalderma, 12, "Tahoma"));
		estilo = ExcelBordas.borda(estilo);
		cell.setCellStyle(ExcelCelulaBackground.background(estilo, corFundo));
		
		cell.setCellValue(texto);
		
		ExcelMerge.merge(excelGalderma, cenario, cell, linha, 0, 2);
		ExcelCelulaEspecial.formatoFormula(excelGalderma, estilo, linha, 4, formulaInicial);
		ExcelCelulaEspecial.formatoFormula(excelGalderma, estilo, linha, 6, formulaNegociado);
		
		return linha;
		
	}

	
	public static void calculoRodapeCenario(XSSFWorkbook excelGalderma,XSSFSheet cenario,int numLinha,String texto,int[] corFundo,String formulaInicial, String formulaNegociado){
			
		XSSFRow linha = montaCelulas(excelGalderma, cenario, numLinha, texto, corFundo,formulaInicial,formulaNegociado);
		XSSFCell cell2 = linha.createCell(3);cell2.setCellStyle(estilo);
		XSSFCell cell3 = linha.createCell(5);cell3.setCellStyle(estilo);
		
	}

	public static void calculoRodapeCenario(XSSFWorkbook excelGalderma,XSSFSheet cenario,int numLinha,String texto,int[] corFundo,double porcentagem,String formulaInicial, String formulaNegociado){
		
		XSSFRow linha = montaCelulas(excelGalderma, cenario, numLinha, texto, corFundo,formulaInicial,formulaNegociado);
		
		XSSFCellStyle estiloPorcentagem = excelGalderma.createCellStyle();
		estiloPorcentagem.setFont(ExcelFonts.fontBold(excelGalderma, 12, "Tahoma"));
		estiloPorcentagem = ExcelBordas.borda(estiloPorcentagem);
		estiloPorcentagem = ExcelCelulaBackground.background(estiloPorcentagem, corFundo);
		
        ExcelCelulaEspecial.formatoPorcentagem(excelGalderma, estiloPorcentagem, porcentagem, linha, 3);
        ExcelCelulaEspecial.formatoPorcentagem(excelGalderma, estiloPorcentagem, porcentagem, linha, 5);
		
	}
}
