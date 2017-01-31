package br.com.sysloccOficial.controllerExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AuxExcelEstilos extends AuxExcelBordas{

	
	public void criaNovoExcel() throws FileNotFoundException{
		XSSFWorkbook workbook = new XSSFWorkbook();
		FileOutputStream out = new FileOutputStream(new File ("E:/Teste/novoExcel.xlsx"));
		XSSFSheet planilha = workbook.createSheet("Aba 1"); // Nome da aba
	}
	
	
	public static XSSFCellStyle bordaCorFundoVerticalCentralEsquerda(XSSFWorkbook w,int[] cor,int tamanhoFonte) {
		XSSFCellStyle corCelula = bordaAlinhamentoVerticaleDireit(w);
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(cor[0],cor[1], cor[2])));
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		corCelula.setFont(fontBold(w,tamanhoFonte));
		
		 XSSFDataFormat df = w.createDataFormat();
		corCelula.setDataFormat(df.getFormat("#,##0.00"));
		
		return corCelula;
	}
	
	
	
	
	
	/**
	 * Defini as larguras das colunas no Excel
	 * 
	 * 
	 * 
	 * 
	 * @param x
	 */
	public static void larguraColunas(XSSFSheet x){
		x.setColumnWidth(0, 9500);
		x.setColumnWidth(1, 2800);
		x.setColumnWidth(2, 3400);
		x.setColumnWidth(3, 3200);
		x.setColumnWidth(4, 3800);
		x.setColumnWidth(5, 3800);
		x.setColumnWidth(6, 3800);
		x.setColumnWidth(7, 4500);
		x.setColumnWidth(8, 4800);
		x.setColumnWidth(9, 4800);
		x.setColumnWidth(10, 5000);
		x.setColumnWidth(11, 20000);
		x.setColumnWidth(12, 10000);
	}
	
	public static XSSFFont fontBold(XSSFWorkbook w,int sizeFont){
		XSSFFont font =  w.createFont();
		font.setFontHeightInPoints((short)sizeFont);
		font.setBold(true);
		return font;
	}
	
	
	public static XSSFCellStyle estiloCategoria(XSSFWorkbook w,int r,int g, int b ){
		XSSFCellStyle borda = w.createCellStyle();
		bordaStilo(w, borda);
		borda.setFillForegroundColor(new XSSFColor(new java.awt.Color(r, g, b)));
		borda.setFillPattern(CellStyle.SOLID_FOREGROUND);
		borda.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		borda.setFont(fontBold(w,11));
		
		XSSFDataFormat df = w.createDataFormat();
		borda.setDataFormat(df.getFormat("#,##0.00"));
		
		return borda;
	}
	
	
	
	public static XSSFCellStyle alinVerticalCentroCentralizado(XSSFWorkbook w){
		XSSFCellStyle aliCentral = w.createCellStyle();
		bordaStilo(w, aliCentral);
		XSSFFont font =  w.createFont();
		font.setFontHeightInPoints((short)9);
		aliCentral.setFont(font);
		aliCentral.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		aliCentral.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return aliCentral;
	}
	
	public static XSSFCellStyle alinVerticalCentro(XSSFWorkbook w){
		XSSFCellStyle aliCentral = w.createCellStyle();
		aliCentral.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return aliCentral;
	}

	public static XSSFCellStyle alinVerticalCentroBorda(XSSFWorkbook w){
		XSSFCellStyle aliCentral = w.createCellStyle();
		aliCentral.setFont(fontBold(w,11));
		aliCentral.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return aliCentral;
	}

	
	
	
	/**
	 * Método para mesclar celulas
	 * 
	 * Esse método recebe qual linha e quais colunas serão mescladas.
	 * 
	 * Para inserir borda na mesclagem inteira deverá ser feito assim:
	 * 
	 * Criar a célula que vai o texto e chamar esse método para mesclar as céluas
	 * em seguida deverá ser criado as células seguintes com os estilos de borda para
	 * cada uma. Pode ser usar um laço for para criar essas células.
	 *  
	 * 
	 * 
	 * 
	 * @param planilha
	 * @param comecaLinha
	 * @param terminaLinha
	 * @param comecaColuna
	 * @param terminaColuna
	 */
	
	public static void mescla(XSSFSheet planilha,int comecaLinha,int terminaLinha,int comecaColuna,int terminaColuna){
		planilha.addMergedRegion(new CellRangeAddress(
				comecaLinha,   // Começa na linha
				terminaLinha,  // Termina na linha
				comecaColuna,  // Começa na coluna
				terminaColuna  // Termina na coluna
		));
	}
	
	public static XSSFCellStyle corCelula(XSSFWorkbook w,int r,int g, int b){
		XSSFCellStyle corCelula = w.createCellStyle();
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(r, g, b)));
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return corCelula;
	}
	
	public static XSSFCellStyle bordaAlinhamentoVerticaleCentral(XSSFWorkbook w){
		XSSFCellStyle borda = w.createCellStyle();
		bordaStilo(w, borda);
		borda.setFont(fontBold(w,11));
		borda.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		borda.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		return borda;
	}

	
	/**
	 * Estilo com:
	 * todas as bordas finas
	 * ,alinhamento a direita
	 * ,verticalmente ao centro
	 * 
	 * @param XSSFWorkbook w
	 * @return XSSFCellStyle borda
	 */
	
	public static XSSFCellStyle bordaAlinhamentoVerticaleDireita(XSSFWorkbook w){
		XSSFCellStyle borda = w.createCellStyle();
		borda.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		borda.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		bordaStilo(w, borda);
		return borda;
	}

	/**
	 * Estilo com:
	 * todas as bordas finas
	 * ,alinhamento a direita
	 * ,verticalmente ao centro
	 * 
	 * @param XSSFWorkbook w
	 * @return XSSFCellStyle borda
	 */
	
	public static XSSFCellStyle bordaAlinhamentoVerticaleDireitaFormataQuantidade(XSSFWorkbook w){
		XSSFCellStyle borda = w.createCellStyle();
		bordaStilo(w, borda);
		borda.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		borda.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return borda;
	}

	public static XSSFCellStyle bordaAlinhamentoVerticalsaseEsquerda(XSSFWorkbook w){
		XSSFCellStyle borda = w.createCellStyle();
		bordaStilo(w, borda);
		borda.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		borda.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return borda;
	}

	/**
	 * Método para montar célula com valor formatado 
	 * 
	 * 
	 * @param w
	 * @return
	 */
	
	public static XSSFCellStyle bordaAlinhamentoVerticaleDireit(XSSFWorkbook w){
		XSSFCellStyle borda = w.createCellStyle();
		bordaStilo(w, borda);
		borda.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		borda.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		XSSFDataFormat df = w.createDataFormat();
		borda.setDataFormat(df.getFormat("#,##0.00"));
		
		return borda;
	}

	/**
	 * 
	 * 
	 * 
	 * @param w
	 * @return
	 */
	public static XSSFCellStyle SubTotalGeral(XSSFWorkbook w){
		XSSFCellStyle borda = w.createCellStyle();
		bordaStilo(w, borda);
		borda.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		borda.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		borda.setFont(fontBold(w,14));
		
		XSSFDataFormat df = w.createDataFormat();
		borda.setDataFormat(df.getFormat("#,##0.00"));
		
		return borda;
	}
	
	
	
	public static XSSFCellStyle bordaStilo(XSSFWorkbook w,XSSFCellStyle borda){
		borda.setBorderTop(XSSFCellStyle.BORDER_THIN);
		borda.setTopBorderColor(IndexedColors.BLACK.getIndex());
		borda.setBorderRight(XSSFCellStyle.BORDER_THIN);
		borda.setRightBorderColor(IndexedColors.BLACK.getIndex());
		borda.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		borda.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		borda.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		borda.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		return borda;
	
	}	
		
	public static XSSFCellStyle estiloBordaCordeFundo(XSSFWorkbook w,int r,int g, int b ){
		XSSFCellStyle corCelula = bordaAlinhamentoVerticaleCentral(w);
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(r, g, b)));
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return corCelula;
	}

	

	public static XSSFCellStyle estiloBordaCordeFundoSemBold(XSSFWorkbook w,int []cor){
		XSSFCellStyle corCelula = w.createCellStyle();
		bordaStilo(w, corCelula);
		corCelula.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(cor[0], cor[1], cor[2])));
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		  XSSFDataFormat df = w.createDataFormat();
		  corCelula.setDataFormat(df.getFormat("#,##0.00"));
		
		return corCelula;
	}

	public static XSSFCellStyle estiloBordaCordeFundoSemBoldPorcentagem(XSSFWorkbook w,int []cor){
		XSSFCellStyle corCelula = w.createCellStyle();
		bordaStilo(w, corCelula);
		corCelula.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(cor[0], cor[1], cor[2])));
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		corCelula.setDataFormat(w.createDataFormat().getFormat("0.000%"));
		XSSFDataFormat df = w.createDataFormat();
		corCelula.setDataFormat(df.getFormat("#,##0.00"));
		return corCelula;
	}

	
	
	
	public static XSSFCellStyle estiloBordaCordeFundoSemBoldAlinCentro(XSSFWorkbook w,int []cor){
		XSSFCellStyle corCelula = w.createCellStyle();
		bordaStilo(w, corCelula);
		corCelula.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		corCelula.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(cor[0], cor[1], cor[2])));
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return corCelula;
	}

	public static XSSFCellStyle estiloBordaCorFundoBoldAlinCentro(XSSFWorkbook w,int []cor){
		XSSFCellStyle corCelula = w.createCellStyle();
		bordaStilo(w, corCelula);
		corCelula.setFont(fontBold(w,10));
		corCelula.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		corCelula.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(cor[0], cor[1], cor[2])));
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return corCelula;
	}

	public static XSSFCellStyle estiloBordaCorFundoBoldAlinDireita(XSSFWorkbook w,int []cor){
		XSSFCellStyle corCelula = w.createCellStyle();
		bordaStilo(w, corCelula);
		corCelula.setFont(fontBold(w,10));
		corCelula.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		corCelula.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(cor[0], cor[1], cor[2])));
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return corCelula;
	}
	
	
	
	
	
	public static List<String>notasRodapeTabela(){
		List<String> notas = new ArrayList<String>();
		 notas.add("Custos Agência");
		 notas.add("Sub Contratado");
		 notas.add("Faturamento \r\n Direto (ND)");
		 notas.add("Sub-Total");
		 notas.add("Fee");
		 notas.add("Impostos");
		 notas.add("TOTAL");
		 return notas;		
	}
	
	public List<String> cabecalhoPlanilha(){
		List<String> cab = new ArrayList<String>();
		cab.add("Linha");
		cab.add("Quant.");
		cab.add("Custo Unit.");
		cab.add("Diárias");
		cab.add("Custos Agência");
		cab.add("Sub Contratado");
		cab.add("Faturamento Direto (ND)");
		cab.add("Sub-Total");
		cab.add("Fee");
		cab.add("Impostos");
		cab.add("TOTAL");
		cab.add("Informações");
		cab.add("Não inclusos no custo");
		
		return cab;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
