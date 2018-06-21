package br.com.sysloccOficial.ListaProducao.Excel;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GeraExcelRodapeCalculos {
	
	private XSSFWorkbook novoExcel;
	private int primeiraLinha;
	private int ultimaLinha;
	private XSSFSheet abaMaster;
	private int[] cor;
	private int[] merge;
	private int[] mergeForm;
	private int[] corBranco = {252,252,252};
	private int[] corCinzaEsc = {217,217,217};
	private int[] corVerdeAgua = {204,255,204};

	public GeraExcelRodapeCalculos(XSSFWorkbook novoExcel,int primeiraLinha, int ultimaLinha,XSSFSheet abaMaster){
		this.novoExcel = novoExcel;
		this.primeiraLinha = primeiraLinha;
		this.ultimaLinha = ultimaLinha;
		this.abaMaster = abaMaster;
	}
	
	public void setaCelulaComFormulaRodapeSubTotalItens(XSSFCell cell,XSSFRow linha,int num,List<Integer> subtotais,XSSFSheet abaMaster){
		int[] corFundo = {247,150,70};
		CelulaStylo celulaStilo = new CelulaStylo(novoExcel);
		XSSFCellStyle corCelula = celulaStilo.fundoBorda(corFundo,false,11,true);
		XSSFCellStyle corCelulaFormula = celulaStilo.fundoBordaComFormula(corFundo,false,11,true);
		
		cell = linha.createCell(0);
		corCelula.setWrapText(true);
		cell.setCellStyle(corCelula);
		cell.setCellValue("Sub total 1 (soma dos serviços do evento)");

	    abaMaster.addMergedRegion(new CellRangeAddress(
	    		 num, //first row (0-based)
	             num, //last row  (0-based)
	             0, //first column (0-based)
	             5  //last column  (0-based)
	    ));
		
		for (int i = 1; i < 6; i++) {
			criaLinhaECelulaTextoComStylo(cell, linha, "", i,corCelula);
		}
		
		String valorFormulaSubTotais = "";
		for (int i = 0; i < subtotais.size(); i++) {
			valorFormulaSubTotais = valorFormulaSubTotais+"G"+subtotais.get(i);
			
			if(i < subtotais.size()-1){
				valorFormulaSubTotais = valorFormulaSubTotais+"+";
			}
		}
		criaLinhaECelulaFormulaComStilo(cell, linha, valorFormulaSubTotais, 6,corCelulaFormula); /** Sub total 1 (soma dos serviços do evento) */
	}

	public void setaCelulaComFormulaRodapeSubTotalItensOpcional(XSSFCell cell,XSSFRow linha,int num,List<Integer> subtotais,XSSFSheet abaMaster){
		
		int[] corFundo = {247,150,70};
		CelulaStylo celulaStilo = new CelulaStylo(novoExcel);
		XSSFCellStyle corCelula = celulaStilo.fundoBorda(corFundo,false,11,true);
		XSSFCellStyle corCelulaFormula = celulaStilo.fundoBordaComFormula(corFundo,false,11,true);
		
		cell = linha.createCell(0);
		corCelula.setWrapText(true);
		cell.setCellStyle(corCelula);
		cell.setCellValue("Sub total 1 (soma dos serviços do evento)");
		
		for (int i = 1; i < 6; i++) {
			criaLinhaECelulaTextoComStylo(cell, linha, "", i,corCelula);
		}
		
		String valorFormulaSubTotais = "SUM(F5:F"+num+")";
		
		criaLinhaECelulaFormulaComStilo(cell, linha, valorFormulaSubTotais, 5,corCelulaFormula); /** Sub total 1 (soma dos serviços do evento) */
	}
	
	public void setaCelulaComFormula(XSSFCell cell,XSSFRow linha,int num){
		criaLinhaECelulaFormula(cell, linha, "C"+num+"*D"+num+"*F"+num, 6); /** SubTotal */
		criaLinhaECelulaFormula(cell, linha, "(E"+num+"-F"+num+")*C"+num, 7); /** Saving por item */
	}


	public void setaCelulaComFormulaRodapeFatDireto(XSSFCell cell,XSSFRow linha){
		primeiraLinha = primeiraLinha + 2;
		ultimaLinha = ultimaLinha - 2;

		cor = new int[]{242,242,242};
		merge = new int[]{0,5};
		mergeForm = new int[]{6,6};
		
		GeraCelulaTexto celulaMerge = new GeraCelulaTexto(abaMaster, novoExcel);
		XSSFRow infoLinha = celulaMerge.celulaBordaComTextoSeguidoFormula(linha.getRowNum(), "Fração do sub Total 1 - faturada diretamente para a Bayer", 0, cor, true, false,merge);
		celulaMerge.celulaBorda(infoLinha, "", 6, cor, true, false, mergeForm, "SUMIF($B$"+primeiraLinha+":$B$"+ultimaLinha+","+"\"Faturamento Direto\",$G$"+primeiraLinha+":$G$"+ultimaLinha+")");
	}

/////// ReembolsoDespesas
	public void setaCelulaComFormulaReembolsoDespesas(XSSFCell cell,XSSFRow linha){
		primeiraLinha = primeiraLinha + 2;
		ultimaLinha = ultimaLinha - 2;
		
		cor = new int[]{242,242,242};
		merge = new int[]{0,5};
		mergeForm = new int[]{6,6};
		
		GeraCelulaTexto celulaMerge = new GeraCelulaTexto(abaMaster, novoExcel);
		XSSFRow infoLinha = celulaMerge.celulaBordaComTextoSeguidoFormula(linha.getRowNum(), "Fração do sub Total 1 - Reembolso de despesas", 0, cor, true, false,merge);
		celulaMerge.celulaBorda(infoLinha, "", 6, cor, true, false, mergeForm, "SUMIF($B$"+primeiraLinha+":$B$"+ultimaLinha+","+"\"Reembolso de despesas\",$G$"+primeiraLinha+":$G$"+ultimaLinha+")");
	}

	public void setaCelulaComFormulaRodapeFatDiretoOpc(XSSFCell cell,XSSFRow linha, int ultLinha, String texto, String condicao){
		int[] cor = {242,242,242};
		ultimaLinha = ultLinha;
		cor = new int[]{242,242,242};
		merge = new int[]{0,4};
		mergeForm = new int[]{5,5};
		GeraCelulaTexto celulaMerge = new GeraCelulaTexto(abaMaster, novoExcel);
		XSSFRow infoLinha = celulaMerge.celulaBordaComTextoSeguidoFormula(linha.getRowNum(), texto, 0, cor, true, false,merge);
		celulaMerge.celulaBorda(infoLinha, "", 5 , cor, true, false, mergeForm, "SUMIF($B$"+5+":$B$"+ultimaLinha+","+"\""+condicao+"\",$F$"+5+":$F$"+ultimaLinha+")");
	}

	public void setaCelulaComFormulaRodapeCalculoFeeOpc(XSSFCell cell,XSSFRow linha,int fatDiretoBayer,int terceiros,int total, XSSFSheet aba,BigDecimal fee){
		
  		GeraCelulaTexto celulaMerge = new GeraCelulaTexto(aba, novoExcel);
		XSSFRow infoLinha = celulaMerge.celulaBordaComTextoSeguidoFormula(linha.getRowNum()-1, "%", 2, new int[]{247,150,70}, false, true,new int[]{2,2},XSSFCellStyle.ALIGN_CENTER);
		celulaMerge.celulaBordaComTextoSeguidoFormula(infoLinha, "Valor Total", 3, new int[]{247,150,70}, false, true,new int[]{3,3},XSSFCellStyle.ALIGN_CENTER,15);
		celulaMerge.celulaBordaComTextoSeguidoFormula(infoLinha, "Valor do Fee", 4, new int[]{247,150,70}, false, true,new int[]{4,5},XSSFCellStyle.ALIGN_CENTER,15);
		
		int linha2 = linha.getRowNum()+1;
		
		
		criaLinhaECelulaTextoComMerge(cell, linha, "Fee de administração sobre fornecedor contratado pela agência", 0, 0, 1, 30,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,false,11,aba);
		XSSFCell cellPorc = criaLinhaECelulaPorcentagem(cell, linha, fee.doubleValue(), 2,novoExcel); /** Porcentagem de 10% */
		estiloCorFundoBordaRecebeEstilo(cellPorc.getCellStyle(), linha, 30, XSSFCellStyle.ALIGN_CENTER, new int[]{242,242,242},true,false,11);
		criaLinhaECelulaFormulaComMerge(cell, linha, "F"+fatDiretoBayer+"+F"+terceiros+"", 3,3,3,30,11, new int[]{242,242,242},false);
		criaLinhaECelulaFormulaComMerge(cell, linha, "D"+linha2+"*C"+linha2+"", 4,4,5,30,11, new int[]{242,242,242},false,aba);
	}
	
	public void setaCelulaComFormulaRodapeCalculoFeeReduzidoOpc(XSSFCell cell,XSSFRow linha,int num,XSSFSheet aba,BigDecimal feeReduzido){
		int linPorc = num+5;
		num = num+1;
		criaLinhaECelulaTextoComMerge(cell, linha, "Fee de administração sobre fornecedor contratado pela Bayer (gestão da agência\nsomente in loco)", 0, 0, 1, 30,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,false,11,aba);
		XSSFCell cellPorc = criaLinhaECelulaPorcentagem(cell, linha, feeReduzido.doubleValue(), 2,novoExcel); /** Porcentagem de 10% */
		estiloCorFundoBordaRecebeEstilo(cellPorc.getCellStyle(), linha, 30, XSSFCellStyle.ALIGN_CENTER, new int[]{242,242,242},true,false,11);
		criaLinhaECelulaFormulaComMerge(cell, linha, "F"+num+"", 3,3,3,30,11, new int[]{242,242,242},false);
		criaLinhaECelulaFormulaComMerge(cell, linha, "D"+( linPorc + 1 )+"*C"+( linPorc + 1 )+"", 4,4,5,30,11, new int[]{242,242,242},false,aba);
	}

/////Sub total Fee Opc
	public void setaCelulaComFormulaRodapeSubtotalFeeOpc(XSSFCell cell,XSSFRow linha,int num,XSSFSheet aba,BigDecimal feeReduzido){
		int linPorc = num+5;
		num = num+1;
		criaLinhaECelulaTextoComMerge(cell, linha, "Sub total Fee", 0, 0, 1, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,true,10,aba);
		
		criaLinhaECelulaTextoComMerge(cell, linha, "", 2, 2, 2, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,true,10);
		
		criaLinhaECelulaFormulaComMerge(cell, linha, "SUM(D"+( linha.getRowNum() -1 )+":D"+linha.getRowNum()+")", 3,3,3,15,10, new int[]{242,242,242},false);
		criaLinhaECelulaFormulaComMerge(cell, linha, "SUM(E"+( linha.getRowNum() -1 )+":E"+linha.getRowNum()+")", 4,4,5,15,10, new int[]{242,242,242},false,aba);
	}
	
	public void setaCelulaComFormulaRodapeCalculoSubTotal2Opc(XSSFCell cell,XSSFRow linha,int num,XSSFSheet aba){
		num = num+1;
		int num1 = num+1;
		int num2 = num+5;
		int num3 = num+6;
		criaLinhaECelulaTextoComMerge(cell, linha, "Sub total 2 (Serviços faturados via agência + Fee de remuneração)", 0, 0, 4, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,true,11,aba);
		criaLinhaECelulaFormulaComMerge(cell, linha, "$F$"+num+"+$F$"+num1+"+$E$"+num2+"+$E$"+num3+"", 5,5,5,15,11, new int[]{242,242,242},false,aba);
	}
	
	public void setaCelulaComFormulaRodapeCalculoEncargosOpc(XSSFCell cell,XSSFRow linha,int num,XSSFSheet abaOpcionais,BigDecimal imposto){
		
		int totalFaturado = num-1;
		int porcentagem = num+1;
		
		criaLinhaECelulaTextoComMerge(cell, linha, "Encargos tributários", 0, 0, 1, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,false,11,abaOpcionais);
		criaLinhaECelulaPorcentagemMerge(cell, linha, imposto.doubleValue(), 2,novoExcel); /** Porcentagem de 15% */
		criaLinhaECelulaFormulaComMergeOpc(cell, linha, "$F$"+porcentagem+"*$C$"+totalFaturado+"", 3,3,5,15,11, new int[]{242,242,242},false,abaOpcionais);
	}
///Encargo tributários - Fee
	/*public void setaCelulaComFormulaRodapeCalculoEncargosOpc(XSSFCell cell,XSSFRow linha,int num,XSSFSheet abaOpcionais,BigDecimal imposto){
		
		int totalFaturado = num-1;
		int porcentagem = num+1;
		
		criaLinhaECelulaTextoComMerge(cell, linha, "Encargos tributários", 0, 0, 1, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,false,11,abaOpcionais);
		criaLinhaECelulaPorcentagemMerge(cell, linha, imposto.doubleValue(), 2,novoExcel); *//** Porcentagem de 15% *//*
		criaLinhaECelulaFormulaComMergeOpc(cell, linha, "$F$"+porcentagem+"*$C$"+totalFaturado+"", 3,3,5,15,11, new int[]{242,242,242},false,abaOpcionais);
	}*/
	
	
	
	
	public void setaCelulaComFormulaRodapeCalculoFaturadoAgenciaOpc(XSSFCell cell,XSSFRow linha,int num,XSSFSheet aba){
		int num1 = num+1;
		num = num+2;
		criaLinhaECelulaTextoComMerge(cell, linha, "Total Faturado via agência", 0, 0, 4, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,true,11,aba);
		criaLinhaECelulaFormulaComMerge(cell, linha, "$F$"+num1+"/(100%-$C$"+num+")", 5,5,5,15,11, new int[]{242,242,242},false,aba);
	}
	
	public void setaCelulaComFormulaRodapeCalculoFaturadoDiretoBayerOpc(XSSFCell cell,XSSFRow linha,int num,XSSFSheet aba){
		num = num+1;
		int num1= num+3;
		criaLinhaECelulaTextoComMerge(cell, linha, "Total Faturado diretamente para a Bayer", 0, 0, 4, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,true,11,aba);
		criaLinhaECelulaFormulaComMerge(cell, linha, "F"+num+"+F"+num1+"", 5,5,5,15,11, new int[]{242,242,242},false,aba);
	}
	
	public void setaCelulaComFormulaRodapeCalculoTotalOrcamentoOpc(XSSFCell cell,XSSFRow linha,int num,XSSFSheet aba){
		num = num-1;
		int num1 = num+2;
		criaLinhaECelulaTextoComMerge(cell, linha, "Total do orçamento de opcionais/extras", 0, 0, 4, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{247,150,70},true,true,11,aba);
		criaLinhaECelulaFormulaComMerge(cell, linha, "F"+num+"+F"+num1+"", 5,5,5,15,11, new int[]{247,150,70},true,aba);
	}
	
	public void setaCelulaComFormulaRodapeCustoInterno(XSSFCell cell,XSSFRow linha){
		cor = new int[]{242,242,242};
		merge = new int[]{0,5};
		mergeForm = new int[]{6,6};
		
		GeraCelulaTexto celulaMerge = new GeraCelulaTexto(abaMaster, novoExcel);
		XSSFRow infoLinha = celulaMerge.celulaBordaComTextoSeguidoFormula(linha.getRowNum(), "Fração do sub Total 1 de custos internos (faturado via agência)", 0, cor, true, false,merge);
		celulaMerge.celulaBorda(infoLinha, "", 6, cor, true, false, mergeForm, "SUMIF($B$"+primeiraLinha+":$B$"+ultimaLinha+","+"\"Custo Interno\",$G$"+primeiraLinha+":$G$"+ultimaLinha+")");
	}

	public void setaCelulaComFormulaRodapeTerceiro(XSSFCell cell,XSSFRow linha){
		cor = new int[]{242,242,242};
		merge = new int[]{0,5};
		mergeForm = new int[]{6,6};
		GeraCelulaTexto celulaMerge = new GeraCelulaTexto(abaMaster, novoExcel);
		XSSFRow infoLinha = celulaMerge.celulaBordaComTextoSeguidoFormula(linha.getRowNum(), "Fração do sub Total 1 de terceiros (faturado via agência)", 0, cor, true, false,merge);
		celulaMerge.celulaBorda(infoLinha, "", 6, cor, true, false, mergeForm, "SUMIF($B$"+primeiraLinha+":$B$"+ultimaLinha+","+"\"Terceiro\",$G$"+primeiraLinha+":$G$"+ultimaLinha+")");
	}
	
	public void setaCelulaComFormulaRodapeContratadoPelaBayer(XSSFCell cell,XSSFRow linha){
		
		cor = new int[]{242,242,242};
		merge = new int[]{0,5};
		mergeForm = new int[]{6,6};
		GeraCelulaTexto celulaMerge = new GeraCelulaTexto(abaMaster, novoExcel);
		XSSFRow infoLinha = celulaMerge.celulaBordaComTextoSeguidoFormula(linha.getRowNum(), "Fração do sub Total 1 de terceiros contratados diretamente pela Bayer (gestão da agência somente in loco)", 0, cor, true, false,merge);
		celulaMerge.celulaBorda(infoLinha, "", 6, cor, true, false, mergeForm, "SUMIF($B$"+primeiraLinha+":$B$"+ultimaLinha+","+"\"Contratado pela Bayer\",$G$"+primeiraLinha+":$G$"+ultimaLinha+")");
	}


////////SubTotalFee	
	public void setaCelulaComFormulaRodapeCalculoFee(XSSFCell cell,XSSFRow linha,int fatDiretoBayer,int terceiros,int total,BigDecimal feeLista){
		
		XSSFCell cellTeste = linha.createCell(5);
		
		GeraCelulaTexto celulaMerge = new GeraCelulaTexto(abaMaster, novoExcel);
		XSSFRow infoLinha = celulaMerge.celulaBordaComTextoSeguidoFormula(linha.getRowNum()-1, "%", 2, new int[]{247,150,70}, false, true,new int[]{2,2},XSSFCellStyle.ALIGN_CENTER);
		celulaMerge.celulaBordaComTextoSeguidoFormula(infoLinha, "Valor Total", 3, new int[]{247,150,70}, false, true,new int[]{3,4},XSSFCellStyle.ALIGN_CENTER,15);
		celulaMerge.celulaBordaComTextoSeguidoFormula(infoLinha, "Valor do Fee", 5, new int[]{247,150,70}, false, true,new int[]{5,6},XSSFCellStyle.ALIGN_CENTER,15);
		
		cor = new int[]{247,150,70};
		
		criaLinhaECelulaTextoComMerge(cell, linha, "Fee de administração sobre fornecedor contratado pela agência", 0, 0, 1, 30,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,false,11);
		
		XSSFCell cellPorc = criaLinhaECelulaPorcentagem(cell, linha, feeLista.doubleValue(), 2,novoExcel); /** Porcentagem de 10% */
		estiloCorFundoBordaRecebeEstilo(cellPorc.getCellStyle(), linha, 30, XSSFCellStyle.ALIGN_CENTER, new int[]{242,242,242},true,false,11);
		criaLinhaECelulaFormulaComMerge(cellTeste, linha, "G"+fatDiretoBayer+"+G"+terceiros+"", 3,3,4,30,11, new int[]{242,242,242},false);
		criaLinhaECelulaFormulaComMerge(cellTeste, linha, "D"+total+"*C"+total+"", 5,5,6,30,11, new int[]{242,242,242},false);
		
	}

	public void setaCelulaComFormulaRodapeCalculoFeeReduzido(XSSFCell cell,XSSFRow linha,int num,BigDecimal fee){
		int linPorc = num+4;
		
		criaLinhaECelulaTextoComMerge(cell, linha, "Fee de administração sobre fornecedor contratado pela Bayer (gestão da agência\nsomente in loco)", 0, 0, 1, 30,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,false,11);
		XSSFCell cellPorc = criaLinhaECelulaPorcentagem(cell, linha, fee.doubleValue(), 2,novoExcel); /** Porcentagem de 10% */
		estiloCorFundoBordaRecebeEstilo(cellPorc.getCellStyle(), linha, 30, XSSFCellStyle.ALIGN_CENTER, new int[]{242,242,242},true,false,11);
		
		criaLinhaECelulaFormulaComMerge(cell, linha, "G"+num+"", 3,3,4,30,11, new int[]{242,242,242},false);
		criaLinhaECelulaFormulaComMerge(cell, linha, "D"+linPorc+"*C"+linPorc+"", 5,5,6,30,11, new int[]{242,242,242},false);
	}
//////// Sub total fee
	public void setaCelulaComFormulaRodapeCalculoSubTotalFee(XSSFCell cell,XSSFRow linha,int num,BigDecimal fee){
		int linPorc = num+4;
		int linhaFeeadmForn = linha.getRowNum()-1;
		int linhaFeeadmFornBayer = linha.getRowNum();
		
		
		criaLinhaECelulaTextoComMerge(cell, linha, "Sub total Fee", 0, 0, 1, 30,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,false,11);
		criaLinhaECelulaTextoComMerge(cell, linha, "", 2, 2, 2, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,true,10);
		
		criaLinhaECelulaFormulaComMerge(cell, linha, "SUM(D"+linhaFeeadmForn+":D"+linhaFeeadmFornBayer+")", 3,3,4,15,10, new int[]{242,242,242},false);
		criaLinhaECelulaFormulaComMerge(cell, linha, "SUM(F"+linhaFeeadmForn+":F"+linhaFeeadmFornBayer+")", 5,5,6,15,10, new int[]{242,242,242},false);
	}

	public void setaCelulaComFormulaRodapeCalculoSubTotal2(XSSFCell cell,XSSFRow linha,int num){
		num = num+2;
		int num1 = num+1;
		int num2 = num+5;
		int num3 = num+6;
		criaLinhaECelulaTextoComMerge(cell, linha, "Sub total 2 (Serviços faturados via agência + Fee de remuneração)", 0, 0, 3, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,true,11);
		criaLinhaECelulaFormulaComMerge(cell, linha, "$G$"+num+"+$G$"+num1+"+$F$"+num2+"+$F$"+num3+"", 5,5,6,15,11, new int[]{242,242,242},false);
	}

	public void setaCelulaComFormulaRodapeCalculoEncargos(XSSFCell cell,XSSFRow linha,int num,BigDecimal imposto){
		
		int totalFaturado = num-1;
		int porcentagem = num+1;
		
		criaLinhaECelulaTextoComMerge(cell, linha, "Encargos tributários", 0, 0, 1, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,false,11);
		criaLinhaECelulaPorcentagemMerge(cell, linha, imposto.doubleValue(), 2,novoExcel); /** Porcentagem de 15% */
		criaLinhaECelulaFormulaComMerge(cell, linha, "$F$"+porcentagem+"*$C$"+totalFaturado+"", 5,5,6,15,11, new int[]{242,242,242},false);
	}

	public void setaCelulaComFormulaRodapeCalculoEncargosFee(XSSFCell cell,XSSFRow linha,int num,BigDecimal imposto){
		
		int totalFaturado = num-1;
		int porcentagem = num+1;
		
		criaLinhaECelulaTextoComMerge(cell, linha, "Encargos tributários - Fee", 0, 0, 1, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,false,11);
		criaLinhaECelulaPorcentagemMerge(cell, linha, imposto.doubleValue(), 2,novoExcel); /** Porcentagem de 15% */
		criaLinhaECelulaFormulaComMerge(cell, linha, "$F$"+porcentagem+"*$C$"+totalFaturado+"", 5,5,6,15,11, new int[]{242,242,242},false);
	}
	
	public void setaCelulaComFormulaRodapeCalculoFaturadoAgencia(XSSFCell cell,XSSFRow linha,int num){
		int num1 = num+1;
		
		criaLinhaECelulaTextoComMerge(cell, linha, "Total Faturado via agência", 0, 0, 3, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,true,11);
		criaLinhaECelulaFormulaComMerge(cell, linha, "$F$"+num+"/(100%-$C$"+num1+")", 5,5,6,15,11, new int[]{242,242,242},true);
	}

	public void setaCelulaComFormulaRodapeCalculoFaturadoDiretoBayer(XSSFCell cell,XSSFRow linha,int num){
		num = num+1;
		int num1= num+3;
		criaLinhaECelulaTextoComMerge(cell, linha, "Total Faturado diretamente para a Bayer", 0, 0, 3, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,true,11);
		criaLinhaECelulaFormulaComMerge(cell, linha, "G"+num+"+G"+num1+"", 5,5,6,15,11, new int[]{242,242,242},true);

	}

	public void setaCelulaComFormulaRodapeCalculoTotalOrcamento(XSSFCell cell,XSSFRow linha,int num){

		num = num+1;
		int num1 = num+2;
		
		criaLinhaECelulaTextoComMerge(cell, linha, "Total do orçamento (todos os custos de produção somandos)", 0, 0, 3, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{247,150,70},true,true,11);
		criaLinhaECelulaFormulaComMerge(cell, linha, "F"+num+"+F"+num1+"", 5,5,6,15,11, new int[]{247,150,70},true);

	}

	public void setaCelulaComFormulaRodapeCalculoTotalSaving(XSSFCell cell,XSSFRow linha,int num){
		criaLinhaECelulaTextoComMerge(cell, linha, "Soma total de Saving no evento", 0, 0,1, 15,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},true,true,10);
		criaLinhaECelulaFormulaComMerge(cell, linha, "SUM(H"+primeiraLinha+":H"+ultimaLinha+")", 3,3,3,15,10, new int[]{242,242,242},true);
	}

	public void quadroPedidCompra(XSSFCell cell,XSSFRow linha,int num){
		criaLinhaECelulaTextoComMerge(cell, linha, "PEDIDO DE COMPRA", 0, 0,0, 15,XSSFCellStyle.ALIGN_LEFT,corCinzaEsc,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, "FATURAMENTO", 1, 1,2, 15,XSSFCellStyle.ALIGN_LEFT,corCinzaEsc,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, "", 3, 3,3, 15,XSSFCellStyle.ALIGN_LEFT,corCinzaEsc,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, "", 4, 4,4, 15,XSSFCellStyle.ALIGN_LEFT,corCinzaEsc,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, "", 5, 5,5, 15,XSSFCellStyle.ALIGN_LEFT,corCinzaEsc,false,true,10);
	}
	public void quadroTotalEvento(XSSFCell cell,XSSFRow linha,int nf1,int nf2){
		criaLinhaECelulaTextoComMerge(cell, linha, "TOTAL EVENTO", 0, 0,4, 15,XSSFCellStyle.ALIGN_CENTER,corCinzaEsc,false,true,10);
		criaLinhaECelulaFormulaComMergeQuadro(cell, linha, "F"+nf1+"+F"+nf2+"", 5,5,5,15,10,corCinzaEsc,true);
	}

	public void quadroTotalServSubCont(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,String numPorcentagem,int linhaFormula){
		String formula = "G"+linhaFormula;
		quadroLinha1(cell, linha, num, textoFaturamento, numPorcentagem, formula);
	}
	public void quadroFatDirBayer(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,String numPorcentagem,int linhaFormula){
		String formula = "G"+linhaFormula;
		quadroLinha1(cell, linha, num, textoFaturamento, numPorcentagem, formula);
	}
	public void quadroContBayer(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,String numPorcentagem,int linhaFormula){
		String formula = "D"+linhaFormula;
		quadroLinha1(cell, linha, num, textoFaturamento, numPorcentagem, formula);
	}
	public void quadroFee14(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,BigDecimal numPorcentagem,int totalSub,int fatDireto,int linhaPorc){
		String formula = "((F"+totalSub+"+F"+fatDireto+")*C"+linhaPorc+")";
		quadroLinha1Porc(cell, linha, num, textoFaturamento, numPorcentagem.toString(), formula);
	}
	public void quadroFee7(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,BigDecimal numPorcentagem,int linha7Porc,int contBayer){
		String formula = "(F"+contBayer+"*C"+linha7Porc+")";
		quadroLinha1Porc(cell, linha, num, textoFaturamento, numPorcentagem.toString(), formula);
	}
	public void quadroCustInter(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,String numPorcentagem,int custoInter){
		String formula = "G"+custoInter+"";
		quadroLinha1(cell, linha, num, textoFaturamento, numPorcentagem, formula);
	}
	public void quadroSub(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,String numPorcentagem,int totalserv,int custosint){
		String formula = "SUM(F"+totalserv+":F"+custosint+")";
		quadroLinha1(cell, linha, num, textoFaturamento, numPorcentagem, formula);
	}
	public void quadroImpostos(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,BigDecimal numPorcentagem, int subTotal, int fatDirBayer){
		String formula = "((F"+subTotal+"-F"+fatDirBayer+"-F"+(fatDirBayer+1)+")/0.771)-(F"+subTotal+"-F"+fatDirBayer+"-F"+(fatDirBayer+1)+")";
		quadroLinha1Porc(cell, linha, num, textoFaturamento, numPorcentagem.toString(), formula);
	}
	
	public void quadroTotalNF(XSSFCell cell,XSSFRow linha,int num,String colunaLinha1,String colunaLinha2,String colunaLinha3,int subTotal,int imposto){
		criaLinhaECelulaTextoComMerge(cell, linha, colunaLinha1, 0, 0,0, 15,XSSFCellStyle.ALIGN_LEFT,corVerdeAgua,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, colunaLinha2, 1, 1,2, 15,XSSFCellStyle.ALIGN_LEFT,corVerdeAgua,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, colunaLinha3, 3, 3,3, 15,XSSFCellStyle.ALIGN_LEFT,corVerdeAgua,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, "", 4, 4,4, 15,XSSFCellStyle.ALIGN_LEFT,corVerdeAgua,false,true,10);
		criaLinhaECelulaFormulaComMergeQuadro(cell, linha, "SUM(F"+subTotal+"+F"+imposto+")", 5,5,5,15,10, corVerdeAgua,true);
	}
	public void quadroPrevExtras(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,String numPorcentagem){
		quadroLinha2(cell, linha, num, textoFaturamento, numPorcentagem);
	}
	public void quadroTotalServSubEx(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,BigDecimal numPorcentagem,int totalSubCont){
		String formula = "F"+totalSubCont+"*E"+num+"";
		quadroLinha3(cell, linha, textoFaturamento, numPorcentagem, formula);
	}
	
	public void quadroFatDirBayerEx(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,BigDecimal numPorcentagem,int fatDiretoBayer){
		String formula = "F"+fatDiretoBayer+"*E"+num+"";
		quadroLinha3(cell, linha,textoFaturamento, numPorcentagem, formula);
	}
	public void quadroContBayerEx(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,BigDecimal numPorcentagem,int contbayer){
		String formula = "F"+contbayer+"*E"+num+"";
		quadroLinha3(cell, linha,textoFaturamento, numPorcentagem, formula);
	}
	public void quadroFee14Ex(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,BigDecimal numPorcentagem,int totalServSub,int fatDiretoBayer){
		String formula = "(F"+totalServSub+"+F"+fatDiretoBayer+")*E"+num+"";
		quadroLinha3(cell, linha,textoFaturamento, numPorcentagem, formula);
	}
	public void quadroFee7Ex(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,BigDecimal numPorcentagem,int contBayer){
		String formula = "F"+contBayer+"*E"+num+"";
		quadroLinha3(cell, linha,textoFaturamento, numPorcentagem, formula);
	}
	public void quadroCustInterEx(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,BigDecimal numPorcentagem,int custoInt){
		String formula = "F"+custoInt+"*E"+num+"";
		quadroLinha3(cell, linha, textoFaturamento, numPorcentagem, formula);
	}
	public void quadroSubTotalEx(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,String numPorcentagem,int totalServ,int custosInt){
		String formula = "SUM(F"+totalServ+":F"+custosInt+")";
		quadroLinha1(cell, linha, num, textoFaturamento, numPorcentagem, formula);
	}
	public void quadroImpostosEx(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento,BigDecimal numPorcentagem,int fatBayer,int contBayer){
		String formula = "((F"+num+"-F"+fatBayer+"-F"+contBayer+")/0.771)-(F"+num+"-F"+fatBayer+"-F"+contBayer+")";
		quadroLinha3(cell, linha,textoFaturamento, numPorcentagem, formula);
	}
	
	public void quadroTotalNF2(XSSFCell cell,XSSFRow linha,int num,String colunaLinha1,String colunaLinha2,String colunaLinha3,int subtotal,int impostos){
		criaLinhaECelulaTextoComMerge(cell, linha, colunaLinha1, 0, 0,0, 15,XSSFCellStyle.ALIGN_LEFT,corVerdeAgua,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, colunaLinha2, 1, 1,2, 15,XSSFCellStyle.ALIGN_LEFT,corVerdeAgua,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, colunaLinha3, 3, 3,3, 15,XSSFCellStyle.ALIGN_LEFT,corVerdeAgua,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, "", 4, 4,4, 15,XSSFCellStyle.ALIGN_LEFT,corVerdeAgua,false,true,10);
		criaLinhaECelulaFormulaComMergeQuadro(cell, linha, "F"+subtotal+"+F"+impostos+"", 5,5,5,15,10, corVerdeAgua,true);
	}
	
	
	public void quadroLinha1(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento, String numPorcentagem,String formula){
		criaLinhaECelulaTextoComMerge(cell, linha, "", 0, 0,0, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, textoFaturamento, 1, 1,2, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,false,10);
		criaLinhaECelulaTextoComMerge(cell, linha, "", 3, 3,3, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, numPorcentagem.toString(), 4, 4,4, 15,XSSFCellStyle.ALIGN_CENTER,corBranco,false,false,10);
		criaLinhaECelulaFormulaComMergeQuadro(cell, linha, formula, 5,5,5,15,10, corBranco,false);
	}

	public void quadroLinha1Porc(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento, String numPorcentagem,String formula){
		criaLinhaECelulaTextoComMerge(cell, linha, "", 0, 0,0, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, textoFaturamento, 1, 1,2, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,false,10);
		criaLinhaECelulaTextoComMerge(cell, linha, "", 3, 3,3, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,true,10);
		double porc =  Double.parseDouble(numPorcentagem);
		criaLinhaECelulaPorcentagemBorda(cell, linha, porc, 4, novoExcel);
		criaLinhaECelulaFormulaComMergeQuadro(cell, linha, formula, 5,5,5,15,10, corBranco,false);
	}
	
	public void quadroLinha2(XSSFCell cell,XSSFRow linha,int num,String textoFaturamento, String numPorcentagem){
		criaLinhaECelulaTextoComMerge(cell, linha, "", 0, 0,0, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, textoFaturamento, 1, 1,2, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, "", 3, 3,3, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, numPorcentagem , 4, 4,4, 15,XSSFCellStyle.ALIGN_CENTER,corBranco,false,false,10);
		criaLinhaECelulaTextoComMerge(cell, linha, numPorcentagem , 5, 5,5, 15,XSSFCellStyle.ALIGN_CENTER,corBranco,false,false,10);
	}
	
	public void quadroLinha3(XSSFCell cell,XSSFRow linha,String textoFaturamento, BigDecimal numPorcentagem,String formula){
		criaLinhaECelulaTextoComMerge(cell, linha, "", 0, 0,0, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,true,10);
		criaLinhaECelulaTextoComMerge(cell, linha, textoFaturamento, 1, 1,2, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,false,10);
		criaLinhaECelulaTextoComMerge(cell, linha, "", 3, 3,3, 15,XSSFCellStyle.ALIGN_LEFT,corBranco,false,true,10);
		criaLinhaECelulaPorcentagemBorda(cell, linha, numPorcentagem.doubleValue(), 4, novoExcel);
		criaLinhaECelulaFormulaComMergeQuadro(cell, linha, formula, 5,5,5,15,10, corBranco,false);
	}
	
	public void setaCelulaRodapeContrato(XSSFCell cell,XSSFRow linha,int num){
		String contrato = "\"O Presente anexo Técnico-Comercial é um anexo do Contrato Nº 0065/15, assinado em 05 de Novembro de 2015 entre Bayer S.A. e LoCCo Agencia Ltda. e em razão disso, é Titulo reconhecido como Titulo Executivo Extrajudicial para todos os efeitos, devendo prevalecer o mencionado em caso de divergência entre os documentos”.  O Pedido de compras deverá ser encaminhado com antecedência mínima de 10 dias úteis sob pena da não realização do mesmo.\"";
		criaLinhaECelulaTextoComMerge(cell, linha, contrato, 0, 0,6, 48,XSSFCellStyle.ALIGN_LEFT,new int[]{242,242,242},false,false,11);
	}
	
	
	public void criaLinhaECelulaFormula(XSSFCell cell,XSSFRow linha,String valor,int posicaoCelula){
		XSSFCellStyle styleReal = novoExcel.createCellStyle();
		styleReal.setDataFormat(novoExcel.createDataFormat().getFormat("R$    #,##0.00"));
		styleReal.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		styleReal.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cell = linha.createCell(posicaoCelula);	
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		
		styleReal = setaBordaSimples(styleReal);
		
		cell.setCellStyle(styleReal);
		cell.setCellFormula(valor);
	}

	public XSSFCell criaLinhaECelulaFormulaComMerge(XSSFCell cell,XSSFRow linha,String valor,int posicaoCelula,int priCol, int ultCol,int alturaLinha,int fontTam,int[] cor,boolean bold){
		XSSFCellStyle styleReal = novoExcel.createCellStyle();
		styleReal.setDataFormat(novoExcel.createDataFormat().getFormat("R$    #,##0.00"));
		styleReal.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		linha.setHeightInPoints(alturaLinha);
		
		estiloCorFundoBordaRecebeEstilo(styleReal, linha, alturaLinha, XSSFCellStyle.ALIGN_RIGHT, cor,true,bold,fontTam);
 		
		cell = linha.createCell(posicaoCelula);	
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula(valor);
		cell.setCellStyle(styleReal);
	
		GeraMergeLinhaComStylodaLinha merge = new GeraMergeLinhaComStylodaLinha(abaMaster, novoExcel);
		merge.geraMerge(cell,linha.getRowNum(),priCol,ultCol);
		return cell;
	}

	public XSSFCell criaLinhaECelulaFormulaComMergeQuadro(XSSFCell cell,XSSFRow linha,String valor,int posicaoCelula,int priCol, int ultCol,int alturaLinha,int fontTam,int[] cor,boolean bold){
		XSSFCellStyle styleReal = novoExcel.createCellStyle();
		styleReal.setDataFormat(novoExcel.createDataFormat().getFormat("R$    #,##0.00"));
		styleReal.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		linha.setHeightInPoints(alturaLinha);
		
		estiloCorFundoBordaRecebeEstilo(styleReal, linha, alturaLinha, XSSFCellStyle.ALIGN_LEFT, cor,true,bold,fontTam);
		
		cell = linha.createCell(posicaoCelula);	
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula(valor);
		cell.setCellStyle(styleReal);
		
		GeraMergeLinhaComStylodaLinha merge = new GeraMergeLinhaComStylodaLinha(abaMaster, novoExcel);
		merge.geraMerge(cell,linha.getRowNum(),priCol,ultCol);
		return cell;
	}

	public XSSFCell criaLinhaECelulaFormulaComMerge(XSSFCell cell,XSSFRow linha,String valor,int posicaoCelula,int priCol, int ultCol,int alturaLinha,int fontTam,int[] cor,boolean bold,XSSFSheet aba){
		
		XSSFCellStyle styleReal = novoExcel.createCellStyle();
		styleReal.setDataFormat(novoExcel.createDataFormat().getFormat("R$    #,##0.00"));
		styleReal.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		linha.setHeightInPoints(alturaLinha);
		
		estiloCorFundoBordaRecebeEstilo(styleReal, linha, alturaLinha, XSSFCellStyle.ALIGN_RIGHT, cor,true,bold,fontTam);
		
		cell = linha.createCell(posicaoCelula);	
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula(valor);
		cell.setCellStyle(styleReal);
		
 		GeraMergeLinhaComStylodaLinha merge = new GeraMergeLinhaComStylodaLinha(aba, novoExcel);
		merge.geraMerge(cell,linha.getRowNum(),priCol,ultCol);
		
		return cell;
	}

	public XSSFCell criaLinhaECelulaFormulaComMergeOpc(XSSFCell cell,XSSFRow linha,String valor,int posicaoCelula,int priCol, int ultCol,int alturaLinha,int fontTam,int[] cor,boolean bold,XSSFSheet abaOpcionais){
		
		XSSFCellStyle styleReal = novoExcel.createCellStyle();
		styleReal.setDataFormat(novoExcel.createDataFormat().getFormat("R$    #,##0.00"));
		styleReal.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		linha.setHeightInPoints(alturaLinha);
		
		estiloCorFundoBordaRecebeEstilo(styleReal, linha, alturaLinha, XSSFCellStyle.ALIGN_RIGHT, cor,true,bold,fontTam);
		
		cell = linha.createCell(posicaoCelula);	
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula(valor);
		cell.setCellStyle(styleReal);
		
		GeraMergeLinhaComStylodaLinha mergeOpc = new GeraMergeLinhaComStylodaLinha(abaOpcionais, novoExcel);
		mergeOpc.geraMerge(cell,linha.getRowNum(),priCol,ultCol);
		
		return cell;
	}
	
	public void criaLinhaECelulaTextoComMerge(XSSFCell cell,XSSFRow linha,String valor,int posicaoCelula,int priCol,
			int ultCol,int alturaLinha,short alinhamento,int[]corFundo,boolean italic, boolean bold,int fontTam){
		
		XSSFCellStyle styleReal = estiloCorFundoBorda(linha, alturaLinha, alinhamento, corFundo);
		
		XSSFFont font = novoExcel.createFont();
		font.setItalic(italic);
		font.setBold(bold);
		font.setFontHeight(fontTam);
		font.setFontName("Arial");
		styleReal.setFont(font);
		styleReal.setWrapText(true);

		cell.setCellStyle(styleReal);
		
		cell = linha.createCell(posicaoCelula);	
		cell.setCellStyle(styleReal);
		cell.setCellValue(valor);
		GeraMergeLinhaComStylodaLinha merge = new GeraMergeLinhaComStylodaLinha(abaMaster, novoExcel);
		merge.geraMerge(cell,linha.getRowNum(),priCol,ultCol);
	}
	
	public void criaLinhaECelulaTextoComMerge(XSSFCell cell,XSSFRow linha,String valor,int posicaoCelula,int priCol,
			int ultCol,int alturaLinha,short alinhamento,int[]corFundo,boolean italic, boolean bold,int fontTam,XSSFSheet aba){
		
		XSSFCellStyle styleReal = estiloCorFundoBorda(linha, alturaLinha, alinhamento, corFundo);
		
		XSSFFont font = novoExcel.createFont();
		font.setItalic(italic);
		font.setBold(bold);
		font.setFontHeight(fontTam);
		font.setFontName("Arial");
		styleReal.setFont(font);
		styleReal.setWrapText(true);
		
		cell.setCellStyle(styleReal);
		
		cell = linha.createCell(posicaoCelula);	
		cell.setCellStyle(styleReal);
		cell.setCellValue(valor);
		GeraMergeLinhaComStylodaLinha merge = new GeraMergeLinhaComStylodaLinha(aba, novoExcel);
		merge.geraMerge(cell,linha.getRowNum(),priCol,ultCol);
	}

	public void criaLinhaECelulaTextoComStylo(XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula,XSSFCellStyle corCelula){
		cell = linha.createCell(posicaoCelula);
		cell.setCellValue(texto);
		
		corCelula.setWrapText(true);
		cell.setCellStyle(corCelula);
	}
	
	public void criaLinhaECelulaFormulaComStilo(XSSFCell cell,XSSFRow linha,String valor,int posicaoCelula,XSSFCellStyle corCelula){
		cell = linha.createCell(posicaoCelula);	
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell.setCellStyle(corCelula);
		
		cell.setCellFormula(valor);
	}
	
	public XSSFCell criaLinhaECelulaPorcentagem(XSSFCell cell,XSSFRow linha,double valor,int posicaoCelula,XSSFWorkbook novoExcel){
		double valorPorc = valor/100;
		XSSFCellStyle styleReal = novoExcel.createCellStyle();
		styleReal.setDataFormat(novoExcel.createDataFormat().getFormat("0%"));
		styleReal.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cell = linha.createCell(posicaoCelula);
		cell.setCellStyle(styleReal);
		cell.setCellValue(valorPorc);
		return cell;
	}

	public XSSFCell criaLinhaECelulaPorcentagemBorda(XSSFCell cell,XSSFRow linha,double valor,int posicaoCelula,XSSFWorkbook novoExcel){
		double valorPorc = valor/100;
		XSSFCellStyle styleReal = novoExcel.createCellStyle();
		styleReal.setDataFormat(novoExcel.createDataFormat().getFormat("0%"));
		styleReal.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		styleReal.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleReal.setFillForegroundColor(new XSSFColor(new java.awt.Color(corBranco[0],corBranco[1],corBranco[2])));
		styleReal.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); 
		
		styleReal = setaBordaSimples(styleReal);

		cell = linha.createCell(posicaoCelula);
		cell.setCellStyle(styleReal);
		cell.setCellValue(valorPorc);
		return cell;
	}

	public XSSFCell criaLinhaECelulaPorcentagemMerge(XSSFCell cell,XSSFRow linha,double valor,int posicaoCelula,XSSFWorkbook novoExcel){
		
		int[] corFundo  = {242,242,242};
		
		double valorPorc = valor/100;
		XSSFCellStyle styleReal = novoExcel.createCellStyle();
		styleReal.setDataFormat(novoExcel.createDataFormat().getFormat("0%"));
		styleReal.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		styleReal.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleReal.setFillForegroundColor(new XSSFColor(new java.awt.Color(corFundo[0],corFundo[1],corFundo[2])));
		styleReal.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); 
		
		styleReal = setaBordaSimples(styleReal);

		cell = linha.createCell(posicaoCelula);
		cell.setCellStyle(styleReal);
		cell.setCellValue(valorPorc);
		
		GeraMergeLinhaComStylodaLinha merge = new GeraMergeLinhaComStylodaLinha(abaMaster, novoExcel);
		merge.geraMerge(cell,linha.getRowNum(),2,3);
		return cell;
	}
	private XSSFCellStyle estiloCorFundoBorda(XSSFRow linha, int alturaLinha, short alinhamento, int[] corFundo) {
		XSSFCellStyle styleReal = novoExcel.createCellStyle();
		styleReal.setAlignment(alinhamento);
		linha.setHeightInPoints(alturaLinha);
		
		styleReal.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleReal.setFillForegroundColor(new XSSFColor(new java.awt.Color(corFundo[0],corFundo[1],corFundo[2])));
		styleReal.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); 
		
		styleReal = setaBordaSimples(styleReal);

		return styleReal;
	}

	private void estiloCorFundoBordaRecebeEstilo(XSSFCellStyle styleReal,XSSFRow linha, int alturaLinha, short alinhamento, int[] corFundo,boolean italic,boolean bold,int fontTam) {
		styleReal.setAlignment(alinhamento);
		linha.setHeightInPoints(alturaLinha);
		
		XSSFFont font = novoExcel.createFont();
		font.setItalic(italic);
		font.setBold(bold);
		font.setFontHeight(fontTam);
		font.setFontName("Arial");
		styleReal.setFont(font);
		
		styleReal.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleReal.setFillForegroundColor(new XSSFColor(new java.awt.Color(corFundo[0],corFundo[1],corFundo[2])));
		styleReal.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); 
		
		styleReal = setaBordaSimples(styleReal);
	
	}

	public XSSFCellStyle criaBordasSimples(){
		
		XSSFCellStyle corBorda = novoExcel.createCellStyle();
		corBorda.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		corBorda.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		corBorda.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		corBorda.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		corBorda.setBorderRight(XSSFCellStyle.BORDER_THIN);
		corBorda.setRightBorderColor(IndexedColors.BLACK.getIndex());
		corBorda.setBorderTop(XSSFCellStyle.BORDER_THIN);
		corBorda.setTopBorderColor(IndexedColors.BLACK.getIndex());
		
		return corBorda;
		
	}
	
	public XSSFCellStyle setaBordaSimples(XSSFCellStyle estiloCelula){
		
		estiloCelula.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		estiloCelula.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		estiloCelula.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderRight(XSSFCellStyle.BORDER_THIN);
		estiloCelula.setRightBorderColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setBorderTop(XSSFCellStyle.BORDER_THIN);
		estiloCelula.setTopBorderColor(IndexedColors.BLACK.getIndex());
		
		return estiloCelula;
	}
	
	
	
	
	

}
