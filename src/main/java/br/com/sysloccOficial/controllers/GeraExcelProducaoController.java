package br.com.sysloccOficial.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import jxl.Workbook;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.AtuacaoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.ImpostoDAO;
import br.com.sysloccOficial.daos.JobDAO;
import br.com.sysloccOficial.daos.ListaDAO;
import br.com.sysloccOficial.daos.LocalEventoDAO;
import br.com.sysloccOficial.daos.ProducaoDAO;
import br.com.sysloccOficial.daos.ProdutoDAO;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.ProdutoGrupo;

@Controller
@Transactional
public class GeraExcelProducaoController extends GeraAuxiliarExcel {
	
	
	@Autowired private EmpresaDAO empresaDAO;
	@Autowired private JobDAO jobDAO;
	@Autowired private ListaDAO listaDAO;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@Autowired private AtuacaoDAO atuacaoDAO;
	@Autowired private ProdutoDAO produtoDAO;
	@Autowired private ImpostoDAO impostoDAO;
	@Autowired private ProducaoDAO producaoDAO;
	@Autowired private Utilitaria util;
	@Autowired private ValoresEmGrupo valoresEmGrupo;
	@Autowired private ValoresProdutoGrupo valoresProdutoGrupo;
	@Autowired private ValoresNaLista valoresNaLista;
	@Autowired private LocalEventoDAO localEventoDAO;
	@Autowired private UtilExcel utilExcel;
	
	@PersistenceContext	private EntityManager manager;
	
	ModelAndView MV = new ModelAndView();
	private WritableCellFormat times;
	
	private HSSFCellStyle cs;
	private HSSFDataFormat df;
	final int INICIO_CABECALHO_LINHA = 7;
	BigDecimal feeDaLista;
	
//	WritableWorkbook workbook;
	WritableWorkbook workbook;
	XSSFWorkbook workbook2;
	
	
//Gera Arquivo Excel
	@RequestMapping("/exportaExcel")
	public ModelAndView exportaExcel(Integer idLista){
		
		
		
		MV.setViewName("producao/geraExcel");
		
		Calendar c = Calendar.getInstance(); 
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss"); 
		String a = s.format(c.getTime());
		
		
		
		try {
			
			String fileName = "K:/SYSLOC/upload/excel/"+a+".xls";
			String downloadExcel = "upload/upload/excel/"+a+".xls";
			
			MV.addObject("nomeArquivo", downloadExcel);
			workbook = Workbook.createWorkbook(new File(fileName));
			
			try {
		           FileOutputStream outputStream = new FileOutputStream(fileName);
		           workbook2.write(outputStream);
		       } catch (FileNotFoundException e) {
		           e.printStackTrace();
		       } catch (IOException e) {
		           e.printStackTrace();
		    }
			
			WritableSheet sheet = workbook.createSheet("Planilha 1", 0);
			XSSFSheet sheet2 = workbook2.createSheet("Planilha 1");
			
			
			/*WritableCellFeatures ff = workbook. 
			
			
			CellFormat cf = new CellDateFormatter("###");
			
			WritableCellFormat ft =  new WritableCellFormat(format)
			
			cs = workbook.  createCellStyle();
			df = workbook.createDataFormat();
			cs.setDataFormat(df.getFormat("R$ #.###,##"));*/
			
					
					
			/**
			 * Método para setar as larguras das colunas
			 */
			setaLarguraDasColunas(sheet); 
//			setaLarguraDasColunas(sheet2); 
			
			/**
			 * Método para montar o texto de 'Linha, FAtLocco, FatDireto, Opcional Informacoes e etc'
			 */
//			montaCabecalhoAntesDasCategorias(sheet);
			montaCabecalhoAntesDasCategorias(sheet2);

			
			int LINHA_DA_CATEGORIA = INICIO_CABECALHO_LINHA;
			List<Categoria>categorias = producaoDAO.categoriaPorIdLista(idLista);
			
			feeDaLista = categorias.get(0).getIdLista().getAdministracao().divide(new BigDecimal("100")) ;
			
			List<Integer> linhasDecadaSubTotalCategoria = new ArrayList<Integer>();
			
			for(int i = 0; i < categorias.size(); i ++){
				String GrupoFatLocco = "";
				String GrupoFatDireto = "";
				String GrupoOpcional = "";
				String subTotalz = "";
				
				// Soma do Fat Locco Da Categoria - //
				BigDecimal stGrupoFatLocco = producaoDAO.somaFatLocco(categorias.get(i).getIdcategoria());
				GrupoFatLocco = util.ConverteDolarParaReal(stGrupoFatLocco.toString());
				// Soma do Fat Direto Da Categoria - //	    
				BigDecimal stGrupoFatDireto = producaoDAO.somaFatDireto(categorias.get(i).getIdcategoria());
				GrupoFatDireto = util.ConverteDolarParaReal(stGrupoFatDireto.toString());
			    
				BigDecimal stGrupoOpcional = producaoDAO.somaOpcional(categorias.get(i).getIdcategoria());
				GrupoOpcional = util.ConverteDolarParaReal(stGrupoOpcional.toString());
			    
				BigDecimal novo = (stGrupoFatLocco.add(stGrupoFatDireto));
 			    subTotalz = util.ConverteDolarParaReal(novo.toString());
				
				Integer idCategoria = categorias.get(i).getIdcategoria();
				List<Grupo> grupo = producaoDAO.listaGrupoPorIdCategoria(idCategoria);
				String categoriaNome = categorias.get(i).getCategoria(); 
			
				
				
				
				if(grupo.isEmpty()){
					
				}else{
				
					LINHA_DA_CATEGORIA = LINHA_DA_CATEGORIA + 1;
					// Imprime o nome da categoria no Excel ----------------------------------------------------- //
					
					Label categoria = new Label(0,LINHA_DA_CATEGORIA, categoriaNome,alinhaCentroComTodasBordasFontBold());sheet.addCell(categoria);
					Label colum1 = new Label(1,LINHA_DA_CATEGORIA,"",BordaCimaBaixo());sheet.addCell(colum1);
					Label colum2 = new Label(2,LINHA_DA_CATEGORIA,"",BordaCimaBaixo());sheet.addCell(colum2);
					Label colum3 = new Label(3,LINHA_DA_CATEGORIA,"",BordaCimaBaixo());sheet.addCell(colum3);
					Label colum4 = new Label(4,LINHA_DA_CATEGORIA,"",BordaCimaBaixo());sheet.addCell(colum4);
					Label colum5 = new Label(5,LINHA_DA_CATEGORIA,"",BordaCimaBaixoDireita());sheet.addCell(colum5);
					// ------------------------------------------------------------------------------------------ //
				}
					
					//Método que monta as categorias no Excel
					LINHA_DA_CATEGORIA = montaLinhasDeCadaCategoria(sheet, LINHA_DA_CATEGORIA, grupo);
					
					if(grupo.isEmpty()){
						
					}else{
						LINHA_DA_CATEGORIA = montaSubTotaisDeCadaCategoria(sheet, LINHA_DA_CATEGORIA, GrupoFatLocco,
								GrupoFatDireto, subTotalz, categoriaNome, grupo.size());
					}
					
					linhasDecadaSubTotalCategoria.add(LINHA_DA_CATEGORIA+1);
					
			}
			
			BigDecimal subTotalGeralFatLocco = new BigDecimal("0");
			BigDecimal subTotalGeralFatDireto = new BigDecimal("0");
			
			subTotalGeralFatLocco = producaoDAO.subTotalFatLocco(idLista);
			subTotalGeralFatDireto = producaoDAO.subTotalFatDireto(idLista);
			
			
			
			
			
			String subTotalGeralFatLoccoConv = util.ConverteDolarParaReal(subTotalGeralFatLocco.toString());
			String subTotalGeralDiretoConv = util.ConverteDolarParaReal(subTotalGeralFatDireto.toString());
			
			
			BigDecimal novo = (subTotalGeralFatLocco.add(subTotalGeralFatDireto));
			String subTotaly = util.ConverteDolarParaReal(novo.toString());
			
			Label linhaVazia = new Label (5,LINHA_DA_CATEGORIA+1, "",BordaCimaBaixoDireita());sheet.addCell(linhaVazia);

			
			//Label subTotal = new Label(0,LINHA_DA_CATEGORIA+2, "Sub Total: "+ subTotaly,formataSubTotal());
			Label subTotal = new Label(0,LINHA_DA_CATEGORIA+2, "Sub Total");
			
			
			// Pegar as linhas dos suibtotais de cada categoria
			//Number subTotalFatLocco = new Number (1,LINHA_DA_CATEGORIA+2, converteStringParaDouble(util.formataValores(subTotalGeralFatLoccoConv)),formataSubTotal());
			
			String modificadaMontaSomaSubTotalFatoLocco = montaFormulaSubTotalfatLocco(linhasDecadaSubTotalCategoria);
			
			
			String modificadaMontaSomaSubTotalFatDireto = montaFormulaSubTotalFatDireto(linhasDecadaSubTotalCategoria);
			
			// Coluna x linha 
			Formula subTotalFatLoccoDireto = new Formula(0, LINHA_DA_CATEGORIA+3, "SUM(b"+(LINHA_DA_CATEGORIA+4)+":c"+(LINHA_DA_CATEGORIA+4));
			
			// Coluna x linha 
			Formula formulaSubTotalFatLocco = new Formula(1, LINHA_DA_CATEGORIA+3, "SUM("+modificadaMontaSomaSubTotalFatoLocco+")");
			
			// Coluna x linha 
			Formula formulaSubTotalFatDireto = new Formula(2, LINHA_DA_CATEGORIA+3, "SUM("+modificadaMontaSomaSubTotalFatDireto+")");
			
//			Number subTotalFatDireto = new Number (2,LINHA_DA_CATEGORIA+2, converteStringParaDouble(util.formataValores(subTotalGeralDiretoConv)),formataSubTotal());
			
			Label vazio3 = new Label (3,LINHA_DA_CATEGORIA+2, "",BordaCimaBaixo());
			Label vazio4 = new Label (4,LINHA_DA_CATEGORIA+2, "",BordaCimaBaixo());
			Label vazio5 = new Label (5,LINHA_DA_CATEGORIA+2, "",BordaCimaBaixoDireita());
			
			
			sheet.addCell(subTotalFatLoccoDireto);
			sheet.addCell(formulaSubTotalFatLocco);
			sheet.addCell(formulaSubTotalFatDireto);
			sheet.addCell(vazio3);
			sheet.addCell(vazio4);
			sheet.addCell(vazio5);
			sheet.addCell(subTotal);
			
			try {
				rodapeFee(sheet, LINHA_DA_CATEGORIA);
			} catch (Exception e) {
				System.out.println(e);
			} 
			
			
			
			rodapeSubtotalLocco(sheet, LINHA_DA_CATEGORIA);
			rodapeImposto(sheet, LINHA_DA_CATEGORIA);
			rodapeTotalGeral(sheet, LINHA_DA_CATEGORIA);
			rodapeFaturamentoLocco(sheet, LINHA_DA_CATEGORIA);
			rodapeFaturamentoDireto(sheet, LINHA_DA_CATEGORIA);
			
			workbook.write();
			workbook.close();
			
			} catch (Exception e) {
			System.out.println(e);	
			//	JOptionPane.showMessageDialog(null, "Deu um erro ao gerar a Lista. Alguma linha está com o valor vazio."+e);
		}
		return MV;	
	}
	
	private void rodapeFee(WritableSheet sheet, int LINHA_DA_CATEGORIA) throws RowsExceededException, WriteException{
		int linha = LINHA_DA_CATEGORIA + 4;
		// Coluna x linha 
		Formula formulaFee = new Formula(1,linha, "SUM(B"+(LINHA_DA_CATEGORIA+4)+"+C"+(LINHA_DA_CATEGORIA+4)+")*"+feeDaLista);
		sheet.addCell(formulaFee);
		Label fee = new Label(0,linha, "Fee");
		sheet.addCell(fee);
	}
	
	private void rodapeSubtotalLocco(WritableSheet sheet, int LINHA_DA_CATEGORIA)throws RowsExceededException, WriteException{
		int linha = LINHA_DA_CATEGORIA +5;
		Formula formulaFee = new Formula(1, linha, "SUM(B"+(LINHA_DA_CATEGORIA+4)+":B"+(LINHA_DA_CATEGORIA+5)+")");
		
		
		
		sheet.addCell(formulaFee);
		Label subTotalLocco = new Label(0,linha, "Subtotal Locco:");
		sheet.addCell(subTotalLocco);
		
	}
	private void rodapeImposto(WritableSheet sheet, int LINHA_DA_CATEGORIA)throws RowsExceededException, WriteException{
		try {
			int linha = LINHA_DA_CATEGORIA +6;
			int SubtotalLocco = LINHA_DA_CATEGORIA +5;
			int linhaSubTotalLocco = SubtotalLocco + 1 ;
			Formula formulaFee = new Formula(1, linha, "SUM((B"+linhaSubTotalLocco+"/0.771)- B"+linhaSubTotalLocco+")");
			sheet.addCell(formulaFee);
			Label imposto = new Label(0,linha, "Imposto:");
			sheet.addCell(imposto);
		} catch (WriteException e) {
			System.out.println(e.getMessage());
		}
	}
	private void rodapeTotalGeral(WritableSheet sheet, int LINHA_DA_CATEGORIA)throws RowsExceededException, WriteException{
		int linha = LINHA_DA_CATEGORIA +7;
		
		int linhaTotalFatLoccoDireto = linha - 3;
		int linhaFee = linha - 2;
		int linhaImposto = linha ;
		
		Formula formulaFee = new Formula(1, linha, "SUM(B"+linhaTotalFatLoccoDireto+"+C"+linhaTotalFatLoccoDireto+"+B"+linhaFee+"+B"+linhaImposto+")");
		sheet.addCell(formulaFee);
		Label totalGeral = new Label(0,linha, "Total Geral:");
		sheet.addCell(totalGeral);
	}

	private void rodapeFaturamentoLocco(WritableSheet sheet, int LINHA_DA_CATEGORIA)throws RowsExceededException, WriteException{
		int linha = LINHA_DA_CATEGORIA +9;
		int linhaTotalFatLoccoDireto = linha - 5;
		int linhaFee = linha - 4;
		int linhaImposto = linha -2 ;
		Formula formulaFee = new Formula(1, linha, "SUM(B"+linhaTotalFatLoccoDireto+"+B"+linhaFee+"+B"+linhaImposto+")");
		sheet.addCell(formulaFee);
		Label faturamentoLocco = new Label(0,linha, "Faturamento Locco (NF):");
		sheet.addCell(faturamentoLocco);
	}

	private void rodapeFaturamentoDireto(WritableSheet sheet, int LINHA_DA_CATEGORIA)throws RowsExceededException, WriteException{
		int linha = LINHA_DA_CATEGORIA +10;
		int linhaTotalFatLoccoDireto = linha - 6;
		Formula formulaFee = new Formula(1, linha, "SUM(C"+linhaTotalFatLoccoDireto+")");
//		sheet.addCell(formulaFee);
		
		
		Number n = new Number(1,0,3.1415926535);
		WritableCellFormat cf2 = new WritableCellFormat(NumberFormats.FLOAT);
	    n = new Number(1,2,3.1415926535,cf2);
   		sheet.addCell(n);
		
		Label faturamentoDireto = new Label(0,linha, "Faturamento Direto (ND):");
		sheet.addCell(faturamentoDireto);
	}
	
	private String montaFormulaSubTotalFatDireto(
			List<Integer> linhasDecadaSubTotalCategoria) {
		List<String> montaFormulaSubTotalFatDireto = new ArrayList<String>();
		String montaSomaSubTotalFatDireto = "";
		for (int i = 0; i < linhasDecadaSubTotalCategoria.size(); i++) {
			montaFormulaSubTotalFatDireto.add("c"+linhasDecadaSubTotalCategoria.get(i).toString());
		}
		
		for (String string : montaFormulaSubTotalFatDireto) {
			montaSomaSubTotalFatDireto = montaSomaSubTotalFatDireto + string + "+";
		}
		String modificadaMontaSomaSubTotalFatDireto = montaSomaSubTotalFatDireto.substring(0, montaSomaSubTotalFatDireto.length() - 1);
		return modificadaMontaSomaSubTotalFatDireto;
	}

	private String montaFormulaSubTotalfatLocco(
			List<Integer> linhasDecadaSubTotalCategoria) {
		List<String> montaFormulaSubTotalFatLocco = new ArrayList<String>();
		String montaSomaSubTotalFatoLocco = "";
		for (int i = 0; i < linhasDecadaSubTotalCategoria.size(); i++) {
			montaFormulaSubTotalFatLocco.add("b"+linhasDecadaSubTotalCategoria.get(i).toString());
		}
		
		for (String string : montaFormulaSubTotalFatLocco) {
			montaSomaSubTotalFatoLocco = montaSomaSubTotalFatoLocco + string + "+";
		}
		
		String modificadaMontaSomaSubTotalFatoLocco = montaSomaSubTotalFatoLocco.substring(0, montaSomaSubTotalFatoLocco.length() - 1);
		return modificadaMontaSomaSubTotalFatoLocco;
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

	private void montaCabecalhoAntesDasCategorias(XSSFSheet sheet2)
			throws WriteException, RowsExceededException {
		//Adding A Label
		Label linha = new Label(0,INICIO_CABECALHO_LINHA,"Linha",formataCabecalho());
		Label FatLocco = new Label(1,INICIO_CABECALHO_LINHA,"Fat loCCo",formataCabecalho());
		Label FatDireto = new Label(2,INICIO_CABECALHO_LINHA,"Fat Direto/Nota de Debito",formataCabecalho());
		Label Opcional = new Label(3,INICIO_CABECALHO_LINHA,"Opcional",formataCabecalho());
		Label Info = new Label(4,INICIO_CABECALHO_LINHA,"Informações",formataCabecalho());
		Label NaoIncluso = new Label(5,INICIO_CABECALHO_LINHA,"Não inclusos no custo",formataCabecalho());
		
		
		Row row = sheet2.createRow(INICIO_CABECALHO_LINHA);
        Cell cell = row.createCell(0);
	    cell.setCellValue("Essa linha Foi escrita apenas para teste do XSSF");
		
		
		
	/*	sheet2.addCell(linha);
		sheet2.addCell(FatLocco);
		sheet2.addCell(FatDireto);
		sheet2.addCell(Opcional);
		sheet2.addCell(Info);
		sheet2.addCell(NaoIncluso);*/
	}

	private void setaLarguraDasColunas(WritableSheet sheet) {
		//Largura de cada Coluna
		sheet.setColumnView(0, 30); 
		sheet.setColumnView(1, 12); 
		sheet.setColumnView(2, 12); 
		sheet.setColumnView(3, 12); 
		sheet.setColumnView(4, 65); 
		sheet.setColumnView(5, 40);
	}

	private void setaLarguraDasColunas(XSSFSheet sheet2) {
		//Largura de cada Coluna
		sheet2.setColumnWidth(0, 30); 
		sheet2.setColumnWidth(1, 12); 
		sheet2.setColumnWidth(2, 12); 
		sheet2.setColumnWidth(3, 12); 
		sheet2.setColumnWidth(4, 65); 
		sheet2.setColumnWidth(5, 40);
	}

	private int montaSubTotaisDeCadaCategoria(WritableSheet sheet, int LINHA_DA_CATEGORIA, String GrupoFatLocco,
											  String GrupoFatDireto, String subTotalz, String categoriaNome,
											  int tamanhoCategoria)throws WriteException, RowsExceededException {
		int ULTIMA_LINHA_CATEGORIA = LINHA_DA_CATEGORIA;
		
		System.out.println(ULTIMA_LINHA_CATEGORIA);
		
		LINHA_DA_CATEGORIA = LINHA_DA_CATEGORIA + 1;	
		/*Imprimi subTotal de Cada Categoria */
		Label categoriaNomeTotals = new Label(0,LINHA_DA_CATEGORIA, categoriaNome+": "+subTotalz,alinhaCentroComTodasBordasFontBold());
		/*Imprimi Total FatLocco da Categori */
//		Number TotalsFatLocco = new Number (1, LINHA_DA_CATEGORIA,converteStringParaDouble(util.formataValores(GrupoFatLocco)), formataNumeroParaRealComBold());
		/*Imprimi Total FatDireto da Categor */
//		Number TotalFatDireto = new Number (2, LINHA_DA_CATEGORIA,converteStringParaDouble(util.formataValores(GrupoFatDireto)), formataNumeroParaRealComBold());
		
		Label column3 = new Label(3,LINHA_DA_CATEGORIA,"",BordaCimaBaixo());
		sheet.addCell(column3);					
		  
		Label column4 = new Label(4,LINHA_DA_CATEGORIA,"",BordaCimaBaixo());
		sheet.addCell(column4);					
		Label column5 = new Label(5,LINHA_DA_CATEGORIA,"",BordaCimaBaixoDireita());sheet.addCell(column5);					
		sheet.addCell(categoriaNomeTotals);
		
		
		
		//Pegar primeira linha da categoria
		//Pegar última linha da categorira
		//Fazer formula de soma
		//sheet.addCell(TotalsFatLocco);
		
		int tamanhoCat = LINHA_DA_CATEGORIA - tamanhoCategoria;
		
		// Coluna x linha 
		Formula formulaCellFatLocco = new Formula(1, LINHA_DA_CATEGORIA, "SUM(b"+(tamanhoCat+1)+":b"+(LINHA_DA_CATEGORIA)+")");
	    sheet.addCell(formulaCellFatLocco);
	    
	    Formula formulaCellFatDireto = new Formula(2, LINHA_DA_CATEGORIA, "SUM(c"+(tamanhoCat+1)+":c"+(LINHA_DA_CATEGORIA)+")");
	    sheet.addCell(formulaCellFatDireto);
		
		
		//sheet.addCell(TotalFatDireto);
		/*sheet.addCell(OpcionalImprime);*/
		return LINHA_DA_CATEGORIA;
	}
	
	private int montaLinhasDeCadaCategoria(WritableSheet sheet, int LINHA_DA_CATEGORIA, List<Grupo> grupo) throws WriteException, RowsExceededException {
	    
		for( int j = 0; j < grupo.size(); j++){
			
		 	Integer idGrupo1 = grupo.get(j).getIdgrupo();
			
			List<ProdutoGrupo> teste = produtoGrupoDAO.listaProdutoGrupoPorGrupo(idGrupo1);
		    
			if(teste.isEmpty()){
				String GrupoNome = grupo.get(j).getGrupo(); // imprime nome categoria
				Label grupos = new Label(0,LINHA_DA_CATEGORIA, GrupoNome,alinhaCentroComTodasBordas());sheet.addCell(grupos);
			}else{
				LINHA_DA_CATEGORIA = LINHA_DA_CATEGORIA + 1;
				
			String GrupoNome = grupo.get(j).getGrupo(); // imprime nome categoria
			Label grupos = new Label(0,LINHA_DA_CATEGORIA, GrupoNome,alinhaCentroComTodasBordas());sheet.addCell(grupos);
			
			BigDecimal grupoIncideImpostoValor = grupo.get(j).getGrupoValorIncideImposto();
			String grupoIncideImpostoValorConvertido = grupoIncideImpostoValor.toString();
			
			BigDecimal grupofatDiretoValor = grupo.get(j).getGrupoValorNaoIncideImposto();
			String grupofatDiretoValorConvertido = grupofatDiretoValor.toString();
			
			
			if(grupo.get(j).isOpcional() == false){ // Imprimi FatLocco da Linha - opcional = 0 
			  Number grupofatLocco = new Number (1, LINHA_DA_CATEGORIA,converteStringParaDouble(grupoIncideImpostoValorConvertido), formataNumeroParaReal());
			  sheet.addCell(grupofatLocco);							
			}else {
				Label grupofatLocco = new Label(1,LINHA_DA_CATEGORIA, " ",alinhaCentroComTodasBordas());
				sheet.addCell(grupofatLocco);
			}
			
			if(grupo.get(j).isOpcional() == false ){// Imprime FatDireto - opcional = 0
				Number grupofatDireto = new Number(2,LINHA_DA_CATEGORIA,converteStringParaDouble(util.formataValores(grupofatDiretoValorConvertido)),formataNumeroParaReal());
				sheet.addCell(grupofatDireto);
			}else{
				 Label grupofatDireto = new Label(2,LINHA_DA_CATEGORIA," ",alinhaCentroComTodasBordas());
				 sheet.addCell(grupofatDireto);
			}
			
			if(grupo.get(j).isOpcional() == true ){// Imprime Opcional
				
				BigDecimal opcional = (grupofatDiretoValor.add(grupoIncideImpostoValor));
			    String opcionalz = util.ConverteDolarParaReal(opcional.toString());
				
				
				Number grupoOpcional = new Number(3,LINHA_DA_CATEGORIA,converteStringParaDouble(util.formataValores(opcionalz)),formataNumeroParaReal());
				sheet.addCell(grupoOpcional);
				
				
						
			}else{
				Label grupoOpcional = new Label(3,LINHA_DA_CATEGORIA,"",alinhaCentroComTodasBordas());
				sheet.addCell(grupoOpcional);
			}
			

			Label grupoInformacao = new Label(4,LINHA_DA_CATEGORIA,grupo.get(j).getInformacoes(),alinhaCentroComTodasBordas());
			Label grupoNaoIncluidos = new Label(5,LINHA_DA_CATEGORIA,grupo.get(j).getNecessidades(),alinhaCentroComTodasBordas());
			sheet.addCell(grupoInformacao);
			sheet.addCell(grupoNaoIncluidos);
			}
         }
		return LINHA_DA_CATEGORIA;
	}
	
	public Double converteStringParaDouble(String valor){
		BigDecimal z = new BigDecimal("0");	
		z = (new BigDecimal(valor));	
		Double teste = Double.valueOf(z.doubleValue());
		return teste;
	}

}
