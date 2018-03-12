package br.com.sysloccOficial.controllerExcel;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class MontaValoresTotais {

	
	@PersistenceContext	private EntityManager manager;
	@Autowired private TextosStaticosExcel textosEstaticos;
	
	private XSSFWorkbook workbook;
	private XSSFSheet planilha;
	
	AuxExcelSQL sql = new AuxExcelSQL(); 
	int[] corFundoBranca = {255,255,255};
	int[] corFundoAmarela = {255,255,0};
	int[] corFundoVerde = {0,255,0};
	int[] corFundoVerdeClaro = {204,255,204};
	int[] corFundoCinza = {217,217,217};
	
	
	String[][] textoFinais = {
			{" Total Serviços Subcontratados","-"},
			{"Fee ","11"},
			{"Custos Internos Agência","-"},
			{"Sub-Total","-"},
			{"Impostos","22,90"},			
			{"TOTAL NF1",""},
			{"Previsão de Extras sobre Subcontratados (10%)","10"},
			{"Fee","11"},
			{"Previsão de Extras sobre Custos Internos (10%)","10"},
			{"Sub-Total",""},
			{"Impostos","22,90"},
			{"TOTAL NF2",""},
			{"TOTAL EVENTO",""}
			};
	
	/**
	 * Método para montar a lista de valores do subTotal da tabela
	 * 
	 *  
	 * @return
	 */
	public void montaRodapePlanilha(Integer idLista,XSSFRow rowSubTotal, XSSFWorkbook workbook){
		
		RodapePanilha rodape = new RodapePanilha();
		rodape.setTextoSubTotal("SubTotal:");
		rodape.setCustoAgencia(SomaCustosAgencia(idLista));
		rodape.setSubContratado(SomaSubContratados(idLista));
		rodape.setFatDireto(SomaFatDireto(idLista));
		rodape.setSubTotal(SomaTotais(idLista));
		rodape.setFee(SomaFee(idLista));
		rodape.setImposto(SomaImpostos(idLista));
		rodape.setTotal(totalGeral(idLista));
		
		
		escreveRodapePlanilha(idLista, workbook, rowSubTotal,rodape);

	}
	
	public void escreveRodapePlanilha(Integer idLista,XSSFWorkbook workbook,XSSFRow rowSubTotal,RodapePanilha rodape){

		Cell cell = rowSubTotal.createCell(0);
		cell.setCellStyle(AuxExcelEstilos.SubTotalGeral(workbook));
		cell.setCellValue(rodape.getTextoSubTotal());
		textosEstaticos.celulasEmBranco(1,4, rowSubTotal, workbook);
		
		montaCelulasValores(workbook, rowSubTotal,4, rodape.getCustoAgencia());
	    montaCelulasValores(workbook, rowSubTotal,5, rodape.getSubContratado());
	    montaCelulasValores(workbook, rowSubTotal,6, rodape.getFatDireto());
	    montaCelulasValores(workbook, rowSubTotal,7, rodape.getSubTotal());
	    montaCelulasValores(workbook, rowSubTotal,8, rodape.getFee());
	    montaCelulasValores(workbook, rowSubTotal,9, rodape.getImposto());
	    montaCelulasValores(workbook, rowSubTotal,10, rodape.getTotal());
	    textosEstaticos.celulasEmBranco(11,13, rowSubTotal, workbook);
	    

	
	}
	
	/**
	 * Método para Criar as células e já imprimir na tabela
	 * 
	 */
	public void montaCelulasValores(XSSFWorkbook workbook,XSSFRow rowSubTotal,int posicao, BigDecimal valor){
	
		Cell cell = rowSubTotal.createCell(posicao);
		cell.setCellStyle(AuxExcelEstilos.SubTotalGeral(workbook));
		
		try {
			double num = valor.doubleValue();
			cell.setCellValue(num);
		} catch (Exception e) {
			cell.setCellValue(0);
		}
			
			
			
	}	

	/**
	 * Método para Criar as células em Texto
	 * 
	 */
	public void montaCelulasValoresString(int posicao,String valor,XSSFWorkbook workbook,XSSFRow rowSubTotal){
		Cell cell = rowSubTotal.createCell(posicao);
		cell.setCellStyle(AuxExcelEstilos.SubTotalGeral(workbook));
		cell.setCellValue(valor);
	}	
	
	
	/**
	 * Método para Inserir os dados de cálculos finais na Planilha
	 * 
	 */
	public void calculosFinais(Integer idLista,XSSFRow rowSubTotal, XSSFWorkbook w, int posicaoLinha,XSSFSheet p){
		planilha = p;
		workbook = w;
		
		CalculoValoresFinais calcFinal = new CalculoValoresFinais();
		calcFinal.setTotalServicosSubcontratados(totalGeralFinal(idLista));
		calcFinal.setFeeNF1(SomaFee(idLista));
		calcFinal.setCustosInternosAgencia(SomaCustosAgencia(idLista));
		calcFinal.setSubTotal(subTotalNF1(idLista));
		calcFinal.setImpostos(SomaImpostos(idLista));
		calcFinal.setTotalNF1(totalNF1Calc(idLista));
		// Segunda parte dos cálculos
		calcFinal.setPrevisaoExtrasSobreSubcontratadosNF2(previsaoExtraNF2(idLista));
		calcFinal.setFeeNF2(calcFeeNF2(idLista));
		calcFinal.setPrevisaoExtrasSobreCustosInternos(prevExCustosInter(idLista));
		calcFinal.setSubTotalNF2(subTotalNF2(idLista));
		calcFinal.setImpostosNF2(impostoNF2(idLista));
		calcFinal.setTotalNF2(totalNf2(idLista));
		calcFinal.setTotalGeral(totalGeralPlanilha(idLista));

		calcFinal.setFeeLista(feeLista(idLista));
		calcFinal.setImpostoLista(impostoCategoria(idLista));
		
		//TODO Verificar esses numeros se são constantes ou variaveis
		calcFinal.setPorcentagemSubContratados(10);
		calcFinal.setPorcentagemCustosInternos(10);
		
		
		
		escreveRodapePlanilhaCalculosFinais(idLista, rowSubTotal, posicaoLinha, planilha,calcFinal );
	}
	
	public void escreveRodapePlanilhaCalculosFinais(Integer idLista,XSSFRow rowSubTotal, int linha,XSSFSheet planilha ,CalculoValoresFinais calcFinal){
		
		DecimalFormat df = new DecimalFormat();  
        df.applyPattern("#,##0.00");
        
        
        SAPTexto(linha+2);
        cabecalhoFinal(linha+3);
        
		
        subContrat(7, converte(calcFinal.getTotalServicosSubcontratados()), df, linha+4 ,corFundoBranca, calcFinal.getFeeLista());

        feeNF1(7, converte(calcFinal.getFeeNF1()), df, linha+5, calcFinal.getFeeLista());
        
        CustAgencNF1(7, converte(calcFinal.getCustosInternosAgencia()), df, linha+6);
        
        SubTotalNF1(7, converte(calcFinal.getSubTotal()), df, linha+7);

        impostosNF1(7, converte(calcFinal.getImpostos()), df, linha+8,calcFinal.getImpostoLista());
        
        TOTALNF1(7, converte(calcFinal.getTotalNF1()), df, linha+9);

        previExtraNF2(7, converte(calcFinal.getPrevisaoExtrasSobreSubcontratadosNF2()), df, linha+10, calcFinal.getPorcentagemSubContratados());
		
        feeNF2(7, converte(calcFinal.getFeeNF2()), df, linha+11, calcFinal.getFeeLista());
		
        previExtraCustosIntNF2(7, converte(calcFinal.getPrevisaoExtrasSobreCustosInternos()), df, linha+12, calcFinal.getPorcentagemCustosInternos());
        
        SubTotalNF2(7, converte(calcFinal.getSubTotalNF2()), df, linha+13);

        impostosNF2(7, converte(calcFinal.getImpostosNF2()), df, linha+14,calcFinal.getImpostoLista());
		
        TOTALNF2(7, converte(calcFinal.getTotalNF2()), df, linha+15);
		
        totalEvento(7, converte(calcFinal.getTotalGeral()), df, linha+16);
        
        textoContrato(linha+17);
        
	}

	public void SAPTexto(Integer posicaoLinha){
		XSSFRow rowCabecalho = planilha.createRow(posicaoLinha);
		Cell cell = rowCabecalho.createCell(0);
		cell.setCellStyle(AuxExcelEstilos.alinVerticalCentroBorda(workbook));
		cell.setCellValue("Nr. Contrato SAP: 4400182963");
	}
	
	
	public void cabecalhoFinal(Integer posicaoLinha){
		
		XSSFRow rowCabecalho = planilha.createRow(posicaoLinha);
		rowCabecalho.setHeight((short)700);
	
		Cell cell = rowCabecalho.createCell(0);
		cell.setCellStyle(AuxExcelEstilos.estiloBordaCorFundoBoldAlinCentro(workbook,corFundoCinza));
		cell.setCellValue("PEDIDO DE COMPRA");
		
		for(int i =1;i<3;i++){
			Cell cells = rowCabecalho.createCell(i);
			cells.setCellStyle(AuxExcelEstilos.bordaCorFundoVerticalCentralEsquerda(workbook,corFundoCinza,10));
		}
		
		Cell cell3 = rowCabecalho.createCell(3);
		cell3.setCellStyle(AuxExcelEstilos.estiloBordaCorFundoBoldAlinCentro(workbook,corFundoCinza));
		cell3.setCellValue("FATURAMENTO");
		
		
		for(int i =4;i<8;i++){
			Cell cells = rowCabecalho.createCell(i);
			cells.setCellStyle(AuxExcelEstilos.bordaCorFundoVerticalCentralEsquerda(workbook,corFundoBranca,10));
		}
	}
	
	public void subContrat(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha, int []corFundo, double inf){
		ChamaEstilo estilo = new ChamaEstilo();
		XSSFRow rowSubContrat = planilha.createRow(posicaoLinha);
		textosEstaticos.celulasEmBranco(0,4, rowSubContrat, workbook);
		estilo.bAlinhVertDMesc(rowSubContrat, workbook, planilha,posicaoLinha, 4, 5, textoFinais[0][0]);
		estilo.bStringCorFund(rowSubContrat, workbook, 6,corFundo, textoFinais[0][1]);
		estilo.bDoubleCorFund(rowSubContrat, workbook, posicaoCelula, corFundo, valor);
		
	}

	public void feeNF1(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha, double inf){
		ChamaEstilo estilo = new ChamaEstilo();
		XSSFRow rowSubContrat = planilha.createRow(posicaoLinha);
		textosEstaticos.celulasEmBranco(0,4, rowSubContrat, workbook);
		estilo.bAlinhVertDMesc(rowSubContrat, workbook, planilha,posicaoLinha, 4, 5, textoFinais[1][0]+"("+inf+"%)");
		estilo.bDoubleCorFundSemMoeda(rowSubContrat, workbook, 6,corFundoAmarela, inf);
		estilo.bDoubleCorFund(rowSubContrat, workbook, posicaoCelula, corFundoBranca, valor);
	}

	public void CustAgencNF1(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha){
		ChamaEstilo estilo = new ChamaEstilo();
		XSSFRow rowSubContrat = planilha.createRow(posicaoLinha);
		textosEstaticos.celulasEmBranco(0,4, rowSubContrat, workbook);
		estilo.bAlinhVertDMesc(rowSubContrat, workbook, planilha,posicaoLinha, 4, 5, textoFinais[2][0]);
		estilo.bStringCorFund(rowSubContrat, workbook, 6,corFundoBranca, textoFinais[2][1]);
		estilo.bDoubleCorFund(rowSubContrat, workbook, posicaoCelula, corFundoVerde, valor);
	}

	public void SubTotalNF1(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha){
		ChamaEstilo estilo = new ChamaEstilo();
		XSSFRow rowSubContrat = planilha.createRow(posicaoLinha);
		textosEstaticos.celulasEmBranco(0,4, rowSubContrat, workbook);
		estilo.bAlinhVertDMesc(rowSubContrat, workbook, planilha,posicaoLinha, 4, 5, textoFinais[3][0]);
		estilo.bStringCorFund(rowSubContrat, workbook, 6,corFundoBranca, textoFinais[3][1]);
		estilo.bDoubleCorFund(rowSubContrat, workbook, posicaoCelula, corFundoBranca, valor);
	}

	public void impostosNF1(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha, double inf){
		ChamaEstilo estilo = new ChamaEstilo();
		XSSFRow rowSubContrat = planilha.createRow(posicaoLinha);
		textosEstaticos.celulasEmBranco(0,4, rowSubContrat, workbook);
		estilo.bAlinhVertDMesc(rowSubContrat, workbook, planilha,posicaoLinha, 4, 5, textoFinais[4][0]);
		estilo.bStringCorFund(rowSubContrat, workbook, 6,corFundoVerde, textoFinais[4][1]);
		estilo.bDoubleCorFundSemMoeda(rowSubContrat, workbook, 6,corFundoVerde, inf);
		
		estilo.bDoubleCorFund(rowSubContrat, workbook, posicaoCelula, corFundoAmarela, valor);
	}

	public void TOTALNF1(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha){
		
		XSSFRow rowTotalNF1 = planilha.createRow(posicaoLinha);
		rowTotalNF1.setHeight((short)500);
	
		Cell cell = rowTotalNF1.createCell(0);
		cell.setCellStyle(AuxExcelEstilos.estiloBordaCorFundoBoldAlinCentro(workbook,corFundoVerdeClaro));
		cell.setCellValue("linha 10");
		
		for(int i =1;i<3;i++){
			Cell cells = rowTotalNF1.createCell(i);
			cells.setCellStyle(AuxExcelEstilos.bordaCorFundoVerticalCentralEsquerda(workbook,corFundoVerdeClaro,12));
		}
				
		Cell cell3 = rowTotalNF1.createCell(3);
		cell3.setCellStyle(AuxExcelEstilos.estiloBordaCorFundoBoldAlinCentro(workbook,corFundoVerdeClaro));
		cell3.setCellValue("NF1");
				
		Cell cells = rowTotalNF1.createCell((short)4);
		cells.setCellValue("TOTAL NF1");
		
		AuxExcelEstilos.mescla(planilha, posicaoLinha, posicaoLinha,4,5);
		cells.setCellStyle(AuxExcelEstilos.estiloBordaCorFundoBoldAlinDireita(workbook,corFundoVerdeClaro));
		
		Cell cell6 = rowTotalNF1.createCell((short)6);
		cell6.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleDireita(workbook));	
		cell6.setCellStyle(AuxExcelEstilos.bordaCorFundoVerticalCentralEsquerda(workbook,corFundoVerdeClaro,12));
		
		Cell cellsValor = rowTotalNF1.createCell(7);
		cellsValor.setCellStyle(AuxExcelEstilos.bordaCorFundoVerticalCentralEsquerda(workbook,corFundoVerdeClaro,12));
		cellsValor.setCellValue(valor);
	}

	public void previExtraNF2(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha, double inf){
		ChamaEstilo estilo = new ChamaEstilo();
		
		XSSFRow rowTotalNF1 = planilha.createRow(posicaoLinha);
		rowTotalNF1.setHeight((short)700);
		textosEstaticos.celulasColoridas(0,6, rowTotalNF1, workbook, corFundoBranca);
		
		estilo.bAlinhVertDMesc(rowTotalNF1, workbook, planilha,posicaoLinha, 4, 5, textoFinais[6][0]);
		
		estilo.bDoubleCorFundSemMoeda(rowTotalNF1, workbook, 6,corFundoVerde,inf);
		
		estilo.bDoubleCorFund(rowTotalNF1, workbook, posicaoCelula, corFundoBranca, valor);
		
	}
	
	public void feeNF2(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha, double inf){
		ChamaEstilo estilo = new ChamaEstilo();
		XSSFRow rowSubContrat = planilha.createRow(posicaoLinha);
		textosEstaticos.celulasEmBranco(0,4, rowSubContrat, workbook);
		estilo.bAlinhVertDMesc(rowSubContrat, workbook, planilha,posicaoLinha, 4, 5, textoFinais[7][0]+"("+inf+"%)");
		estilo.bDoubleCorFundSemMoeda(rowSubContrat, workbook, 6,corFundoAmarela, inf);
		estilo.bDoubleCorFund(rowSubContrat, workbook, posicaoCelula, corFundoBranca, valor);
	}
	
	public void previExtraCustosIntNF2(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha, double inf){
		ChamaEstilo estilo = new ChamaEstilo();
		
		XSSFRow rowTotalNF1 = planilha.createRow(posicaoLinha);
		rowTotalNF1.setHeight((short)700);
		textosEstaticos.celulasColoridas(0,6, rowTotalNF1, workbook, corFundoBranca);
		
		estilo.bAlinhVertDMesc(rowTotalNF1, workbook, planilha,posicaoLinha, 4, 5, textoFinais[8][0]);
		estilo.bDoubleCorFundSemMoeda(rowTotalNF1, workbook, 6,corFundoVerde, inf);
		estilo.bDoubleCorFund(rowTotalNF1, workbook, posicaoCelula, corFundoBranca, valor);
		
	}
	
	
	public void SubTotalNF2(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha){
		ChamaEstilo estilo = new ChamaEstilo();
		XSSFRow rowSubContrat = planilha.createRow(posicaoLinha);
		textosEstaticos.celulasEmBranco(0,4, rowSubContrat, workbook);
		estilo.bAlinhVertDMesc(rowSubContrat, workbook, planilha,posicaoLinha, 4, 5, textoFinais[9][0]);
		estilo.bStringCorFund(rowSubContrat, workbook, 6,corFundoBranca, textoFinais[9][1]);
		estilo.bDoubleCorFund(rowSubContrat, workbook, posicaoCelula, corFundoBranca, valor);
	}
	
	public void impostosNF2(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha, double inf){
		ChamaEstilo estilo = new ChamaEstilo();
		XSSFRow rowSubContrat = planilha.createRow(posicaoLinha);
		textosEstaticos.celulasEmBranco(0,4, rowSubContrat, workbook);
		estilo.bAlinhVertDMesc(rowSubContrat, workbook, planilha,posicaoLinha, 4, 5, textoFinais[10][0]);
		estilo.bDoubleCorFundSemMoeda(rowSubContrat, workbook, 6,corFundoVerde, inf);
	
		estilo.bDoubleCorFund(rowSubContrat, workbook, posicaoCelula, corFundoAmarela, valor);
	}
	
	public void TOTALNF2(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha){
		
		XSSFRow rowTotalNF2 = planilha.createRow(posicaoLinha);
		rowTotalNF2.setHeight((short)500);
	
		Cell cell = rowTotalNF2.createCell(0);
		cell.setCellStyle(AuxExcelEstilos.estiloBordaCorFundoBoldAlinCentro(workbook,corFundoVerdeClaro));
		cell.setCellValue("linha 20");
		
		for(int i =1;i<3;i++){
			Cell cells = rowTotalNF2.createCell(i);
			cells.setCellStyle(AuxExcelEstilos.bordaCorFundoVerticalCentralEsquerda(workbook,corFundoVerdeClaro,12));
		}
		
		Cell cell3 = rowTotalNF2.createCell(3);
		cell3.setCellStyle(AuxExcelEstilos.estiloBordaCorFundoBoldAlinCentro(workbook,corFundoVerdeClaro));
		cell3.setCellValue("NF2");
		
		Cell cells = rowTotalNF2.createCell((short)4);
		cells.setCellValue("TOTAL NF2");
			
		AuxExcelEstilos.mescla(planilha, posicaoLinha, posicaoLinha,4,5);
		cells.setCellStyle(AuxExcelEstilos.estiloBordaCorFundoBoldAlinDireita(workbook,corFundoVerdeClaro));
		
		Cell cell7 = rowTotalNF2.createCell(7);
		cell7.setCellStyle(AuxExcelEstilos.bordaCorFundoVerticalCentralEsquerda(workbook,corFundoVerdeClaro,12));
		cell7.setCellValue(valor);
		
		Cell cell6 = rowTotalNF2.createCell((short)6);
		cell6.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleDireita(workbook));	
		cell6.setCellStyle(AuxExcelEstilos.bordaCorFundoVerticalCentralEsquerda(workbook,corFundoVerdeClaro,12));
		
	}

	
	
	public void totalEvento(int posicaoCelula, double valor,DecimalFormat df, int posicaoLinha){
		
		XSSFRow totalEvento = planilha.createRow(posicaoLinha);
		totalEvento.setHeight((short)700);
		
		Cell cells = totalEvento.createCell((short)0);
		cells.setCellValue(textoFinais[12][0]);
	
		AuxExcelEstilos.mescla(planilha, posicaoLinha, posicaoLinha,0,6);
		cells.setCellStyle(AuxExcelEstilos.estiloBordaCorFundoBoldAlinCentro(workbook,corFundoCinza));
		
		Cell cell = totalEvento.createCell(7);
		cell.setCellStyle(AuxExcelEstilos.bordaCorFundoVerticalCentralEsquerda(workbook,corFundoCinza,12));
		cell.setCellValue(valor);
		
		for(int i = 1;i < 7; i++){
			Cell cell5 = totalEvento.createCell((short)i);
			cell5.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleDireita(workbook));	
		}
		
		
	}
	
	public void textoContrato(Integer posicaoLinha){
		
		XSSFRow textoContrato = planilha.createRow(posicaoLinha);
		textoContrato.setHeight((short)900);
		
		
		Cell cellTexto = textoContrato.createCell((short)0);
		cellTexto.setCellValue("*O Presente anexo Técnico-Comercial é um anexo do Contrato Nº 0268/12, assinado"
				+ " em 16 de Outubro de 2012 entre Bayer S.A., Schering do Brasil e Química e Farmacêutica Ltda."
				+ " e LoCCo Agencia Ltda. e em razão disso, é Titulo reconhecido como Titulo Executivo Extrajudicial"
				+ " para todos os efeitos, devendo prevalecer o mencionado em caso de divergência entre os documentos.");
	
		AuxExcelEstilos.mescla(planilha, posicaoLinha, posicaoLinha,0,7);
		cellTexto.setCellStyle(AuxExcelEstilos.alinVerticalCentroCentralizado(workbook));	
		
		for(int i = 1;i < 8; i++){
			Cell cell5 = textoContrato.createCell((short)i);
			cell5.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleDireita(workbook));	
		}
		
		
	}
	
	
	
	/**
	 * Método para somar todos os Custos da Agência da Lista
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal SomaCustosAgencia(Integer idLista){
		BigDecimal zero = new BigDecimal("0");
		
		TypedQuery<BigDecimal> bg = manager.createQuery(
				"select sum(grupoValorIncideImposto) from Grupo where idLista="+idLista+" and opcional = 0"
			  + " and incideAdministracao = false", BigDecimal.class);
		BigDecimal custosAgencia = bg.getSingleResult();
		
		 if (custosAgencia == null){
			 return zero;
		 }else{
			 return custosAgencia;
		 }
	}
	
	
	/**
	 * Método para somar todos os Custos da Agência da Lista
	 * 
	 * @return BigDecimal
	 */	
	public BigDecimal SomaSubContratados(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		try {
			TypedQuery<BigDecimal> bg = manager.createQuery(
					"select sum(grupoValorIncideImposto) from Grupo where idLista="+idLista
					+" and opcional = 0 and incideAdministracao = true", BigDecimal.class);
			result = bg.getSingleResult();
			return result;
		} catch (Exception e) {
			return result;
		}
	}
	
	
	/**
	 * Método para somar todos os valores de Faturamento direto dos Grupos
	 * 
	 * Faturamento direto são os valores de grupoValorNaoIncideImposto que
	 * incidem administração( incideAdministracao = true )
	 * 
	 * @param idLista
	 * @return
	 */
	public BigDecimal SomaFatDireto(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		TypedQuery<BigDecimal> bg = manager.createQuery(
				"select sum(grupoValorNaoIncideImposto) from Grupo  where idlista = "+idLista+""
	  		  + " and opcional = 0 and incideAdministracao = true", BigDecimal.class);
		
		if(bg.getSingleResult() == null){
			return result;
		}else{
			result = bg.getSingleResult();
			return result;
		}
	}
	
	/**
	 * Método para somar os subTotais de todas as Categorias
	 * 
	 * 
	 * 
	 * @param idLista
	 * @return
	 */
	public BigDecimal SomaTotais(Integer idLista){
		BigDecimal total = new BigDecimal("0");
		BigDecimal result = new BigDecimal("0");
		
		try {
			BigDecimal custoAgencia = SomaCustosAgencia(idLista);
			BigDecimal subContratado = SomaSubContratados(idLista);
			BigDecimal FatDireto = SomaFatDireto(idLista);
			total = custoAgencia.add(subContratado).add(FatDireto);
			return total;
			
		} catch (Exception e) {
			return result;
		}
		
   }

	/**
	 * Método para somar todos os valores de Faturamento direto dos Grupos
	 * 
	 * Faturamento direto são os valores de grupoValorNaoIncideImposto que
	 * incidem administração( incideAdministracao = true )
	 * 
	 * @param idLista
	 * @return
	 */
	public BigDecimal SomaFee(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		TypedQuery<BigDecimal> bg = manager.createQuery(
		"select sum(grupoAdministracaoValor) from Grupo where idLista = "+idLista+"and opcional = 0", BigDecimal.class);
		if(bg.getSingleResult() == null){
			return result;
		}else{
			result = bg.getSingleResult();
			return result;
		}
	}

	/**
	 * Método para somar todos os Impostos dos Grupos
	 * 
	 * Imposto é o valor de grupoImpostoValor
	 * 
	 * @param idLista
	 * @return
	 */
	public BigDecimal SomaImpostos(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		TypedQuery<BigDecimal> bg = manager.createQuery(
		"select sum(grupoImpostoValor) from Grupo where idLista = "+idLista+" and opcional = 0", BigDecimal.class);
		if(bg.getSingleResult() == null){
			return result;
		}else{
			result = bg.getSingleResult();
			return result;
		}
	}
	
	/**
	 * Método para pegar o fee da Lista
	 * 
	 */
	public double feeLista(Integer idLista){
		Query fee  = manager.createQuery("select c.idLista.administracao from Categoria c where c.idLista="+idLista);
		double f = converte((BigDecimal) fee.getSingleResult());
		return f;
	}

	/**
	 * Método para pegar o imposto de cada Categoria da Lista
	 * 
	 */
	public double impostoCategoria(Integer idLista){
		Query fee  = manager.createQuery("select imposto from Categoria c where c.idLista="+idLista);
		double f = converte((BigDecimal) fee.getSingleResult());
		return f;
	}
	
	
	
	

	/**
	 * Método para somar todos os valores de CustoAgencia,
	 * SubContratado, Faturamento Direto, Subtotal, Fee e Impostos 
	 * de todas Categorias
	 * 
	 * 
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal totalGeral(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		BigDecimal total;
	
		try {
			BigDecimal custoAgencia = SomaCustosAgencia(idLista);
			BigDecimal subContratado = SomaSubContratados(idLista);
			BigDecimal FatDireto = SomaFatDireto(idLista);
			
			BigDecimal subTotal = custoAgencia.add(subContratado).add(FatDireto);
			BigDecimal totalFee = SomaFee(idLista);
			BigDecimal totalImposto = SomaImpostos(idLista);
			total = subTotal.add(totalFee).add(totalImposto);
			return total;
		} catch (Exception e) {
			return result;
		}
	}
	
	/**
	 * Método para Total de Serviços SubContratados,
	 * Total SubContratados + Faturamento Direto de todas Categorias
	 * Entra também no cálculo detalhado de NF1 como  Total Serviços Subcontratados	
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal totalGeralFinal(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		BigDecimal total;

		try {
			BigDecimal subContratado = SomaSubContratados(idLista);
			BigDecimal FatDireto = SomaFatDireto(idLista);
			total = subContratado.add(FatDireto);
			return total;

		} catch (Exception e) {
			return result;
		}
		
	}
	
	/**
	 * Método para calcular subTotal NF1
	 * é a soma de subContratado + fee + custosInternos
	 * 
	 * @param idLista
	 * @return
	 */
	public BigDecimal subTotalNF1(Integer idLista){
		BigDecimal total = new BigDecimal("0");
		BigDecimal result = new BigDecimal("0");
		
		BigDecimal subContratado = totalGeralFinal(idLista);
		BigDecimal fee = SomaFee(idLista);
		BigDecimal custosInternos = SomaCustosAgencia(idLista);
		total = subContratado.add(fee).add(custosInternos);
		
		if(total == null){
			return result;
		}else{
			return result = total;
		}
		
	}
	
	/**
	 * Método para calcular o Total de NF1
	 * é a soma de subTotal NF1 + Impostos
	 * @param idLista
	 * @return
	 */
	
	public BigDecimal totalNF1Calc(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		try {
			BigDecimal total = new BigDecimal("0");
			BigDecimal sbNF1 = subTotalNF1(idLista);
			BigDecimal somImpost = SomaImpostos(idLista);
			total = sbNF1.add(somImpost);
			return total;
		} catch (Exception e) {
			return result;// TODO: handle exception
		}
		
	}
	
	/**
	 * Método para calcular a Previsão Extra sobre SubContratados ( Base 10% )
	 * é a multiplicação de (TotalSubContratados * 10%)
	 * Foi usado um método que faz parte do cálculo de SubContratados ( totalGeralFinal )
	 * 
	 */
	public BigDecimal previsaoExtraNF2(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		
		try {
			BigDecimal total =  new BigDecimal("0");
			BigDecimal porcentagem = new BigDecimal("0.10");
			BigDecimal totalSubContratado = totalGeralFinal(idLista);
			total = totalSubContratado.multiply(porcentagem);
			return total;
		} catch (Exception e) {
			return result;// TODO: handle exception
		}
		
		
	}
	
	/**
	 * Método para calcular o Fee de NF2
	 * É usado o método previsaoExtraNF2 como parte do cálculo
	 * 
	 * 
	 */
	public BigDecimal calcFeeNF2(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		
		try {
			BigDecimal total =  new BigDecimal("0");
			BigDecimal porcentagem = new BigDecimal("0.11");
			BigDecimal previsaoExtraNF2 = previsaoExtraNF2(idLista);
			total = previsaoExtraNF2.multiply(porcentagem);
			return total;
			
		} catch (Exception e) {
			return result;// TODO: handle exception
		}
		
		
	}
	
	/**
	 * Método para calcular a Previsão Extra sob Custos internos
	 * Foi usado o método SomaCustosAgencia como parte do cálculo
	 * 
	 * 
	 */
	
	public BigDecimal prevExCustosInter(Integer idLista){
		BigDecimal result = new BigDecimal("0");
	 	 try{
			BigDecimal total =  new BigDecimal("0");
			BigDecimal porcentagem = new BigDecimal("0.10");
			
			BigDecimal somaCustosAgencia = SomaCustosAgencia(idLista);
			total = somaCustosAgencia.multiply(porcentagem);
		 return total;
		} catch (Exception e) {
				return result;// TODO: handle exception
		}
	}
	
	
	/**
	 * Método para calcular o subTotal de NF2
	 * É a soma de previsão Extra SubContratado + Previsão Extra Custos Internos + fee NF2
	 * 
	 * 
	 */
	public BigDecimal subTotalNF2(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		BigDecimal total =  new BigDecimal("0");

		try {
			BigDecimal pCustosInternos = prevExCustosInter(idLista);
			BigDecimal pExtraNF2 = previsaoExtraNF2(idLista);
			BigDecimal feeNF2 = calcFeeNF2(idLista);
			total = pCustosInternos.add(pExtraNF2).add(feeNF2);
			return total;
		} catch (Exception e) {
			return result;
		}
	}

	
	/**
	 * Cálculo de imposto do o SubTotal NF2
	 * 
	 * A formula para o cálculo é ((subtotalNF2/0,771)- subtotalNF2)
	 * 
	 * 
	 * O valore de 0,771 da formula é calculado da seguinte maneira 
	 * 
	 */
	public BigDecimal impostoNF2(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		
		try {
			BigDecimal total = new BigDecimal("0");
			BigDecimal formula = new BigDecimal("0.771");
			BigDecimal subtotalNF2 = subTotalNF2(idLista);
			BigDecimal parte1 = subtotalNF2.divide(formula,BigDecimal.ROUND_UP); 
			total = parte1.subtract(subtotalNF2);
			return total;
		} catch (Exception e) {
			return result;
		}
	}
	
	/**
	 * Soma de SubTotal + Imposto
	 * 
	 * 
	 * @param idLista
	 * @return
	 */
	public BigDecimal totalNf2(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		
		try {
			BigDecimal total = new BigDecimal("0");
			BigDecimal subtotalNF2 = subTotalNF2(idLista);
			BigDecimal impostoNF2 = impostoNF2(idLista);
			total = subtotalNF2.add(impostoNF2);
			return total;
		} catch (Exception e) {
		    return result;	
		}
	}
	
	/**
	 * Método para calcular o Total Geral das NFs
	 * 
	 * É a Soma de Total NF1 + total NF2
	 * 
	 */
	public BigDecimal totalGeralPlanilha(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		BigDecimal total = new BigDecimal("0");

		try {
			BigDecimal NF1 = totalNF1Calc(idLista);
			BigDecimal NF2 = totalNf2(idLista);
			total = NF1.add(NF2);
			return total;
		} catch (Exception e) {
			return result;
		}
	}
	
	public double converte( BigDecimal b){
		double result = 0;
		
		try {
			double num = b.doubleValue();
			return num;
		} catch (Exception e) {
			return result;
		}
	}
	
	
}
