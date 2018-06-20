package br.com.sysloccOficial.ListaProducao.Excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.BaseExcelBayer;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.GrupoCategoriaBayer;
import br.com.sysloccOficial.model.Lista;

@Component
public class GeraExcelNovoBayer{
	
	@Autowired private GeraExcelNovoBayerCabecalho cabecalho;
	@Autowired protected GeraExcelNovoBayerCategorias categoria;
	@Autowired private GeraExcelNovoBayerGrupos grupos;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@Autowired private UtilitariaDatas utilData;
	
	XSSFWorkbook novoExcel;
	XSSFSheet abaMaster;
	XSSFSheet abaOpcionais;
	XSSFSheet abaLogisticaStaff;
	XSSFSheet abaUnidadeNegocio;
	
	public String GeraListagem(Integer idLista) throws IOException, ParseException {
		
		Lista infoLista = produtoGrupoDAO.pegaLista(idLista);
		Categoria infoCategoria = produtoGrupoDAO.pegaCategoria(idLista);
		
		
		if(infoLista.getFeeReduzido() == null){
			infoLista.setFeeReduzido(new BigDecimal("7"));
		}

		BaseExcelBayer base = new BaseExcelBayer("K:/Programas instalados no SSD/SYSLOC/upload/excel/"+infoLista.getIdJob().getTitulo());
//		BaseExcelBayer base = new BaseExcelBayer("C:/SYSLOC/upload/excel/"+infoLista.getIdJob().getTitulo());
		FileOutputStream out = base.caminhoeNomeDoArquivo();
		String downloadExcel = base.caminhoDownloadExcel(infoLista.getIdJob().getTitulo(),"upload/upload/excel/"+infoLista.getIdJob().getTitulo());
		
		
		novoExcel = new XSSFWorkbook();
		
		abaMaster = novoExcel.createSheet("Master"); /** Cria Aba Master da planilha */
		cabecalho.geraCabecalho(abaMaster, base, novoExcel,8); /** Cria Cabeçalho */
		
		abaMaster.createFreezePane(0, 9);

		InsereImagem();
		
		XSSFRow linha;
		
		List<CorpoGrupoCategoriaBayer> montaGruposParaExcel = grupos.montaGruposParaExcel(idLista,false);
		
		List<GrupoCategoriaBayer> grupoCategoriaBayer = produtoGrupoDAO.grupoCategoriaBayer();
		
		int posi = 9;
		int primeiraLinha = posi;	 
		int num = 1+posi;

		List<Integer> subtotais = new ArrayList<Integer>();
		
		for(int i=1;i<grupoCategoriaBayer.size();i++){
			
			int pLinha = num; subtotais.add(pLinha); // pegas linhas correspondentes para cada subTotal de cada categoria
			
			linha = abaMaster.createRow(num);

			int[] nums = montaGruposDinamicos(abaMaster, base, montaGruposParaExcel, linha, num, posi, grupoCategoriaBayer.get(i).getCategoria(), grupoCategoriaBayer.get(i).getIdGrupoCategoria());
			
			posi = nums[0];
			num = nums[1];
			
		}
		
		int ultimaLinha = num;

		GeraExcelRodapeCalculos calculoRopapExcelRodapeCalculos =  new GeraExcelRodapeCalculos(novoExcel,primeiraLinha,ultimaLinha,abaMaster);
		
		//Sub total 1 (soma dos serviços do evento)
		linha = abaMaster.createRow(num-1);
		XSSFCell cellTotal = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeSubTotalItens(cellTotal, linha, num-1,subtotais,abaMaster);
	   
		num = num+2;

		//Fração do sub Total 1 faturada diretamente para a Bayer
		linha = abaMaster.createRow(num);
		XSSFCell cell = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeFatDireto(cell, linha);

		//Fração do sub Total 1 de custos internos (faturado via agência)
		linha = abaMaster.createRow(num+1);
		XSSFCell cell2 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCustoInterno(cell2, linha);

		//Fração do sub Total 1 de terceiros (faturado via agência)
		linha = abaMaster.createRow(num+2);
		XSSFCell cell3 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeTerceiro(cell3, linha);

		//Fração do sub Total 1 de terceiros contratados diretamente pela Bayer (gestão da agência somente in loco)
		linha = abaMaster.createRow(num+3);
		XSSFCell cell4 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeContratadoPelaBayer(cell4, linha);
		
		//Fee de adminstração sobre fornecedor contratado pela agência
		linha = abaMaster.createRow(num+6);
		XSSFCell cell5 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoFee(cell5, linha, num+1,num+3,num+7,infoLista.getAdministracao());

		//Fee de administração sobre fornecedor contratado pela Bayer (gestão da agência somente in loco)
		linha = abaMaster.createRow(num+7);
		XSSFCell cell6 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoFeeReduzido(cell6, linha,num+4,infoLista.getFeeReduzido());

		//Sub total 2 (Serviços faturados via agência + Fee de remuneração)			
		linha = abaMaster.createRow(num+9);
		XSSFCell cell7 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoSubTotal2(cell7, linha, num);

		//Encargos tributários	
		linha = abaMaster.createRow(num+10);
		XSSFCell cell8 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoEncargos(cell8, linha, num+12,infoCategoria.getImposto());

		//Total faturado via agência			
		linha = abaMaster.createRow(num+12);
		XSSFCell cell9 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoFaturadoAgencia(cell9, linha, num+10);

		//Total faturado diretamente para a Bayer			
		linha = abaMaster.createRow(num+14);
		XSSFCell cell10 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoFaturadoDiretoBayer(cell10, linha, num);
		
		//Total do orçamento (todos os custos de produção somados)			
		linha = abaMaster.createRow(num+16);
		XSSFCell cell11 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoTotalOrcamento(cell11, linha, num+12);
		
		//Soma total de Saving no evento
		linha = abaMaster.createRow(num+18);
		XSSFCell cell12 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoTotalSaving(cell12, linha, num);

// -------------------------------------
//Quadro 10% Loccoagencia		
		// Num Contrato SAP
		linha = abaMaster.createRow(num+20);
		XSSFCell numContrato = linha.createCell(0);
		criaLinhaECelulaMesclado(numContrato, linha, "Nr. Contrato SAP: 4400182963", 0, abaMaster, 0, 0, XSSFCellStyle.ALIGN_LEFT);
		//Pedido Compra
		linha = abaMaster.createRow(num+21);
		XSSFCell pedidoCompra = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroPedidCompra(pedidoCompra, linha, num);
		//Total Serviços Subcontratados
		linha = abaMaster.createRow(num+22);
		XSSFCell totalSubCont = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroTotalServSubCont(totalSubCont, linha, num,"Total Serviços Subcontratados","-",num+3);
		//Faturado diretamente para Bayer
		linha = abaMaster.createRow(num+23);
		XSSFCell fatDireto = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroFatDirBayer(fatDireto, linha, num,"Faturado diretamente para Bayer","",num+1);
		//Contratado pela Bayer gestão Agência
		linha = abaMaster.createRow(num+24);
		XSSFCell contBayer = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroContBayer(contBayer, linha, num,"Contratado pela Bayer gestão Agência","",num+8);
		//Fee(14.0%)
		linha = abaMaster.createRow(num+25);
		XSSFCell fee14 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroFee14(fee14, linha, num,"Fee("+infoLista.getAdministracao()+"%)",infoLista.getAdministracao(),num+23,num+24,num+7);
		//Fee(7.0%)
		linha = abaMaster.createRow(num+26);
		XSSFCell fee7 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroFee7(fee7, linha, num,"Fee("+infoLista.getFeeReduzido()+"%)",infoLista.getFeeReduzido(),num+8,num+25);
		//Custos internos agência
		linha = abaMaster.createRow(num+27);
		XSSFCell custosInternos = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroCustInter(custosInternos, linha, num,"Custos internos Agência","-",num+2);
		//Sub-total
		linha = abaMaster.createRow(num+28);
		XSSFCell subTotal = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroSub(subTotal, linha, num,"Sub-total","-",num+23,num+28);
		//impostos
		linha = abaMaster.createRow(num+29);
		XSSFCell impostos = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroImpostos(impostos, linha, num,"Impostos", infoCategoria.getImposto(),num+29,num+24);
		//TotalNF1
		linha = abaMaster.createRow(num+30);
		XSSFCell totalNF1 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroTotalNF(totalNF1, linha, num,"linha 10","NF1","TOTAL NF1",num+29,num+30);
		//PrevEx
		linha = abaMaster.createRow(num+31);
		XSSFCell PrevEx = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroPrevExtras(PrevEx, linha, num,"Previsão de Extras 10% sobre:","");
		
		//Total Serviços SubContratados
		linha = abaMaster.createRow(num+32);
		XSSFCell totalSubEx = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroTotalServSubEx(totalSubEx, linha, num+33,"Total Serviços SubContratados",new BigDecimal("10"),num+23);
		
		//Faturado diretamemente para Bayer
		linha = abaMaster.createRow(num+33);
		XSSFCell fatDirBayerEx = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroFatDirBayerEx(fatDirBayerEx, linha, num+34,"Faturado diretamente para Bayer",new BigDecimal("10"),num+24);
		//Contratado pela Bayer gestão Agência
		linha = abaMaster.createRow(num+34);
		XSSFCell contBayerEx = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroContBayerEx(contBayerEx, linha, num+35,"Contratado pela Bayer gestão Agência",new BigDecimal("10"),num+25);
		//Fee(14.0%)
		linha = abaMaster.createRow(num+35);
		XSSFCell fee14Ex = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroFee14Ex(fee14Ex, linha, num+36,"Fee("+infoLista.getAdministracao().toString()+"%)",infoLista.getAdministracao(),num+33,num+34);
		//Fee(7%)
		linha = abaMaster.createRow(num+36);
		XSSFCell fee7Ex = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroFee7Ex(fee7Ex, linha, num+37,"Fee("+infoLista.getFeeReduzido().toString()+"%)",infoLista.getFeeReduzido(),num+35);
		//Custos Internos Agência
		linha = abaMaster.createRow(num+37);
		XSSFCell custosInterEx = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroCustInterEx(custosInterEx, linha, num+38,"Custos Internos Agência",new BigDecimal("10"),num+28);
		//Sub-Total
		linha = abaMaster.createRow(num+38);
		XSSFCell subTotalEx = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroSubTotalEx(subTotalEx, linha, num,"Sub-Total","",num+33,num+38);
		//Impostos
		linha = abaMaster.createRow(num+39);
		XSSFCell impostosEx = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroImpostosEx(impostosEx, linha, num+39,"Impostos",infoCategoria.getImposto(),num+34,num+35);
		//TotalNF2
		linha = abaMaster.createRow(num+40);
		XSSFCell totalNF2 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroTotalNF2(totalNF2, linha, num,"linha 20","NF2","TOTAL NF2",num+39,num+40);
		//Total Evento
		linha = abaMaster.createRow(num+41);
		XSSFCell totalEvento = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.quadroTotalEvento(totalEvento, linha,num+41,num+31);
		
// -------------------------------------
		// Num Contrato SAP
		linha = abaMaster.createRow(num+44);
		XSSFCell numContrato2 = linha.createCell(0);
		criaLinhaECelulaMesclado(numContrato2, linha, "Nr. Contrato SAP: 4400182963", 0, abaMaster, 0, 0, XSSFCellStyle.ALIGN_LEFT);
		linha = abaMaster.createRow(num+45);
		XSSFCell cellContrato = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaRodapeContrato(cellContrato, linha, num);
		
// ------------------------------------------------------------------ // 		
//Topo do Excel
		linha = abaMaster.createRow(0);
		XSSFCell cell1MescEvento = linha.createCell(0);
 		criaLinhaECelulaMesclado(cell1MescEvento, linha, "Evento: ", 3, abaMaster,0,2,XSSFCellStyle.ALIGN_RIGHT);

 		XSSFCell cell1Mesc = linha.createCell(3);
 		criaLinhaECelulaMesclado(cell1Mesc, linha, infoLista.getIdJob().getTitulo(), 3, abaMaster,3,6,XSSFCellStyle.ALIGN_LEFT);

 		linha = abaMaster.createRow(1);
 		XSSFCell cell1MescNomeAgenc = linha.createCell(0);
 		criaLinhaECelulaMesclado(cell1MescNomeAgenc, linha, "Nome da Agência: ", 3, abaMaster,0,2,XSSFCellStyle.ALIGN_RIGHT);
 		
 		XSSFCell cell1Mesc1 = linha.createCell(3);
 		criaLinhaECelulaMesclado(cell1Mesc1, linha, "LoccoAgencia", 3, abaMaster,3,4,XSSFCellStyle.ALIGN_LEFT);
 		XSSFCell cell1LP = linha.createCell(5);
 		
 		criaLinhaECelulaMesclado(cell1LP, linha,infoLista.getListaCod()+"."+infoLista.getRevisao(), 5, abaMaster,5,5,XSSFCellStyle.ALIGN_LEFT);
 		XSSFCell cell1DataHoje = linha.createCell(6);
 		criaLinhaECelulaMesclado(cell1DataHoje, linha, utilData.converteDateParaString(Calendar.getInstance().getTime()), 6, abaMaster,6,6,XSSFCellStyle.ALIGN_LEFT);

 		linha = abaMaster.createRow(2);
 		XSSFCell cell1MescCategoriaEvento = linha.createCell(0);
 		criaLinhaECelulaMesclado(cell1MescCategoriaEvento, linha, "Categoria do evento: ", 2, abaMaster,0,2,XSSFCellStyle.ALIGN_RIGHT);
 		
 		XSSFCell cell1Mesc2 = linha.createCell(3);
 		criaLinhaECelulaMesclado(cell1Mesc2, linha, "", 3, abaMaster,3,6,XSSFCellStyle.ALIGN_LEFT);
 		
 		TextosListaSuspensas textosListas =  new TextosListaSuspensas();
 		GeraListaSuspensa listaSuspensa = new GeraListaSuspensa();
		listaSuspensa.listaSuspensaFixa(abaMaster, 2, 3, 3, textosListas.CategoriaEvento());

		linha = abaMaster.createRow(4);
		XSSFCell cell1Local = linha.createCell(0);
		criaLinhaECelulaMesclado(cell1Local, linha, "Local: ", 3, abaMaster,0,2,XSSFCellStyle.ALIGN_RIGHT);
		
		String localEvento = "";
		String data = "";
		String pax = "";

		for (int i = 0; i < infoLista.getIdJob().getLocalEvento().size(); i++) {
		    if(infoLista.getIdJob().getLocalEvento().get(i).getLocalEventoNome()!= null)
			localEvento = localEvento + infoLista.getIdJob().getLocalEvento().get(i).getLocalEventoNome()+" - ";
			
			if(infoLista.getIdJob().getLocalEvento().get(i).getLocalEventoDataInicio()!= null)
			data = data +	utilData.converteDateParaString(infoLista.getIdJob().getLocalEvento().get(i).getLocalEventoDataInicio());
			
			if(infoLista.getIdJob().getLocalEvento().get(i).getLocalEventoQtdPessoas()!= null)
			pax = pax + infoLista.getIdJob().getLocalEvento().get(i).getLocalEventoQtdPessoas().toString()+" - ";
		}
		
		XSSFCell cell1Mesc4 = linha.createCell(3);
		criaLinhaECelulaMesclado(cell1Mesc4, linha, localEvento, 3, abaMaster,3,4,XSSFCellStyle.ALIGN_LEFT);
		
		XSSFCell cell1Data = linha.createCell(5);
		criaLinhaECelulaMesclado(cell1Data, linha, "Data: ", 5, abaMaster,5,5,XSSFCellStyle.ALIGN_RIGHT);
		XSSFCell cell1Mesc5 = linha.createCell(6);
		criaLinhaECelulaMesclado(cell1Mesc5, linha, data, 6, abaMaster,6,6,XSSFCellStyle.ALIGN_RIGHT);

		linha = abaMaster.createRow(5);
		XSSFCell cell1Solicitante = linha.createCell(0);
		criaLinhaECelulaMesclado(cell1Solicitante, linha, "Solicitante: ", 3, abaMaster,0,2,XSSFCellStyle.ALIGN_RIGHT);
		
		XSSFCell cell1Mesc6 = linha.createCell(3);
		criaLinhaECelulaMesclado(cell1Mesc6, linha, infoLista.getIdJob().getContato().getContato(), 3, abaMaster,3,4,XSSFCellStyle.ALIGN_LEFT);
		
		XSSFCell cell1NumPax = linha.createCell(5);
		criaLinhaECelulaMesclado(cell1NumPax, linha, "Número de Pax: ", 5, abaMaster,5,5,XSSFCellStyle.ALIGN_RIGHT);
		XSSFCell cell1Mesc7 = linha.createCell(6);
		criaLinhaECelulaMesclado(cell1Mesc7, linha, pax, 6, abaMaster,6,6,XSSFCellStyle.ALIGN_RIGHT);

		linha = abaMaster.createRow(6);
		XSSFCell cell1Pedidos = linha.createCell(0);
		criaLinhaECelulaMesclado(cell1Pedidos, linha, "Pedidos(s) de compras: ", 3, abaMaster,0,2,XSSFCellStyle.ALIGN_RIGHT);
		
		XSSFCell cell1Mesc8 = linha.createCell(3);
		criaLinhaECelulaMesclado(cell1Mesc8, linha, "", 3, abaMaster,3,4,XSSFCellStyle.ALIGN_LEFT);
		
		XSSFCell cell1Budget = linha.createCell(5);
		criaLinhaECelulaMesclado(cell1Budget, linha, "Budget: ", 5, abaMaster,5,5,XSSFCellStyle.ALIGN_RIGHT);
		XSSFCell cell1Mesc9 = linha.createCell(6);
		criaLinhaECelulaMesclado(cell1Mesc9, linha, "", 6, abaMaster,6,6,XSSFCellStyle.ALIGN_RIGHT);
		
// ------------------------------------------------------------------ // 		
 		
		//Aba Opcionais
		abaOpcionais = novoExcel.createSheet("Opcionais"); /** Cria Aba Opcionais */
		cabecalho.geraCabecalhoOpcionais(abaOpcionais, base, novoExcel,2,infoLista); /** Cria Cabeçalho */
		
		GeraAbaOpcionais geraAbaOpcionais = new GeraAbaOpcionais();
		geraAbaOpcionais.geraAba(novoExcel, abaOpcionais);
		
		
		List<CorpoGrupoCategoriaBayer> montaGruposOpcionais = grupos.montaGruposParaExcel(idLista,true);
		
		
		Integer subtotaisOpcionais = montaGruposOpcionais.size()+5;
		
		
		int[] posicoes = montaGruposDinamicosOpcionais(abaOpcionais, base, montaGruposOpcionais, linha, num, posi);
	
		
		
		GeraExcelRodapeCalculos RodapeCalculosOpc =  new GeraExcelRodapeCalculos(novoExcel,5,subtotaisOpcionais,abaOpcionais);
		
		
		
		
		//Sub total 1 (soma dos serviços do evento)
		linha = abaOpcionais.createRow(posicoes[1]);
		XSSFCell cellTotalOpcionais = linha.createCell(0);
		RodapeCalculosOpc.setaCelulaComFormulaRodapeSubTotalItensOpcional(cellTotalOpcionais, linha, posicoes[1],subtotais,abaMaster);
		
		
		//Fração do sub Total 1 faturada diretamente para a Bayer
		linha = abaOpcionais.createRow(posicoes[1]+2);
		XSSFCell cellOpc1 = linha.createCell(0);
		RodapeCalculosOpc.setaCelulaComFormulaRodapeFatDiretoOpc(cellOpc1, linha,subtotaisOpcionais, "Fração do sub Total 1 faturada diretamente para a Bayer","Faturamento Direto");
		
		//Fração do sub Total 1 de custos internos (faturado via agencia)
		linha = abaOpcionais.createRow(posicoes[1]+3);
		XSSFCell cellOpc2 = linha.createCell(0);
		RodapeCalculosOpc.setaCelulaComFormulaRodapeFatDiretoOpc(cellOpc2, linha,subtotaisOpcionais, "Fração do sub Total 1 de custos internos (faturado via agencia)","Custo Interno");
		
		//Fração do sub Total 1 de terceiros (faturado via agencia)
		linha = abaOpcionais.createRow(posicoes[1]+4);
		XSSFCell cellOpc3 = linha.createCell(0);
		RodapeCalculosOpc.setaCelulaComFormulaRodapeFatDiretoOpc(cellOpc3, linha,subtotaisOpcionais, "Fração do sub Total 1 de terceiros (faturado via agencia)","Terceiro");
		
		//Fração do sub Total 1 de terceiros contratados diretamente pela Bayer (gestão da agência somente in loco)
		linha = abaOpcionais.createRow(posicoes[1]+5);
		XSSFCell cellOpc5 = linha.createCell(0);
		RodapeCalculosOpc.setaCelulaComFormulaRodapeFatDiretoOpc(cellOpc5, linha,subtotaisOpcionais, "Fração do sub Total 1 de terceiros contratados diretamente pela Bayer (gestão da agência somente in loco)","Contratado pela Bayer");
		
// ----------------------------------------------------------------------------------------------------------------- //		
		//Fee de adminstração sobre fornecedor contratado pela agência
		
		linha = abaOpcionais.createRow(posicoes[1]+8);
		XSSFCell cellOpc6 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoFeeOpc(cellOpc6, linha, posicoes[1]+3,posicoes[1]+5,num+7,abaOpcionais,infoLista.getAdministracao());

		//Fee de administração sobre fornecedor contratado pela Bayer (gestão da agência somente in loco)
		linha = abaOpcionais.createRow(posicoes[1]+9);
		XSSFCell cellOpc7 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoFeeReduzidoOpc(cellOpc7, linha,posicoes[1]+5,abaOpcionais,infoLista.getFeeReduzido());

		//Sub total 2 (Serviços faturados via agência + Fee de remuneração)			
		linha = abaOpcionais.createRow(posicoes[1]+11);
		XSSFCell cellOpc8 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoSubTotal2Opc(cellOpc8, linha, posicoes[1]+3,abaOpcionais);

		//Encargos tributários	
		linha = abaOpcionais.createRow(posicoes[1]+12);
		XSSFCell cellOpc9 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoEncargosOpc(cellOpc9, linha, posicoes[1]+14,abaOpcionais,infoCategoria.getImposto());

		//Total faturado via agência			
		linha = abaOpcionais.createRow(posicoes[1]+14);
		XSSFCell cellOpc10 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoFaturadoAgenciaOpc(cellOpc10, linha, posicoes[1]+11,abaOpcionais);

		//Total faturado diretamente para a Bayer			
		linha = abaOpcionais.createRow(posicoes[1]+16);
		XSSFCell cellOpc11 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoFaturadoDiretoBayerOpc(cellOpc11, linha, posicoes[1]+2,abaOpcionais);
		
		//Total do orçamento (todos os custos de produção somados)			
		linha = abaOpcionais.createRow(posicoes[1]+18);
		XSSFCell cellOpc12 = linha.createCell(0);
		calculoRopapExcelRodapeCalculos.setaCelulaComFormulaRodapeCalculoTotalOrcamentoOpc(cellOpc12, linha, posicoes[1]+16,abaOpcionais);
		
		
// ----------------------------------------------------------------------------------------------------------------- //		
		
		//Aba Logística
		abaLogisticaStaff = novoExcel.createSheet("Logística Staff"); /** Cria Aba Master da planilha */
		cabecalho.geraCabecalhoLogistica(abaLogisticaStaff, base, novoExcel,2,infoLista,abaLogisticaStaff); /** Cria Cabeçalho */
		
		abaUnidadeNegocio = novoExcel.createSheet("Unidade");
		
		linha = abaMaster.createRow(3);
 		XSSFCell cell1MescunidNegocio = linha.createCell(0);
 		criaLinhaECelulaMesclado(cell1MescunidNegocio, linha, "Unidade de negócio: ", 3, abaMaster,0,2,XSSFCellStyle.ALIGN_RIGHT);
 		XSSFCell cell1Mesc3 = linha.createCell(3);
 		criaLinhaECelulaMesclado(cell1Mesc3, linha, "", 3, abaMaster,3,6,XSSFCellStyle.ALIGN_LEFT);
		geraListaSuspensaUnidadeNegocio();
		
		
		
		base.fechaPlanilha(novoExcel,out);
		return downloadExcel;
	}
	
	public void geraListaSuspensaUnidadeNegocio(){
        TextosListaSuspensas listas = new TextosListaSuspensas();
        
        String [] texto = listas.UnidadeNegocio();
		int n = 150;
    	for (int i = 0;i < texto.length;i++) {
    		XSSFRow row =  abaUnidadeNegocio.createRow(n);
    		XSSFCell cell = row.createCell(13);
    		
    		String name = texto[i];
    		cell.setCellValue(name);
    		n++;
    	}
    	
    	//Criar outra aba
    	//Gerar Listagem nessa Aba
    	
    	try {
    		DataValidationHelper validationHelper = new XSSFDataValidationHelper(abaMaster);
    		CellRangeAddressList addressList = new  CellRangeAddressList(3,3,3,3);
    		DataValidationConstraint constraint = validationHelper.createFormulaListConstraint("Unidade!N151:N" + n);
    		DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);
    		dataValidation.setSuppressDropDownArrow(true);
    		dataValidation.setShowErrorBox(true);
    		abaMaster.addValidationData(dataValidation);
			
		} catch (Exception e) {
			//System.out.println("Erro ao gerar lista suspensa: "+ e);	
		}
	}
	
	private void InsereImagem() throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream("K:/Programas instalados no SSD/SYSLOC/upload/logoEmpresas/bayerExcel.png");

		//InputStream is = new FileInputStream("C:/SYSLOC/upload/logoEmpresas/bayerExcel.png");
	    byte[] bytes = IOUtils.toByteArray(is);
	    int pictureIdx = novoExcel.addPicture(bytes, novoExcel.PICTURE_TYPE_JPEG);
	    is.close();

	    CreationHelper helper = novoExcel.getCreationHelper();

	    // Create the drawing patriarch.  This is the top level container for all shapes. 
	    Drawing drawing = abaMaster.createDrawingPatriarch();

		//add a picture shape
	    ClientAnchor anchor = helper.createClientAnchor();
	    //set top-left corner of the picture,
	    //subsequent call of Picture#resize() will operate relative to it
	    anchor.setCol1(0);
	    anchor.setRow1(1);
	    
	    Picture pict = drawing.createPicture(anchor, pictureIdx);

	    //auto-size picture relative to its top-left corner
	    pict.resize();
	}
	
	
	
	
	public int[] montaGruposDinamicos(XSSFSheet abaMaster,BaseExcelBayer base,List<CorpoGrupoCategoriaBayer> montaGrupo,XSSFRow linha,int num, int posi,String nomeCategoria, int idCategoria){
		
		
		GeraExcelRodapeCalculos calculoRopapExcelRodapeCalculos =  new GeraExcelRodapeCalculos(novoExcel,0,0,abaMaster);
		
		
		int primeiraLinha = num+1;
		int ultimaLinha = primeiraLinha;
		
		
		for(int i = 0; i < montaGrupo.size();i++ ){
			if(montaGrupo.get(i).getIdCategoriaBayer() == idCategoria){
				ultimaLinha++;
			}
		}
		
		int[] posicaoParaFormulaSoma = new int[2];
		posicaoParaFormulaSoma[0] = primeiraLinha;
		posicaoParaFormulaSoma[1] = ultimaLinha+2;

		
		categoria.categorias(abaMaster, base, novoExcel, nomeCategoria ,posi,posicaoParaFormulaSoma); /** Cria Categoria */

		
		for(int i = 0; i < montaGrupo.size();i++ ){
			
			if(montaGrupo.get(i).getIdCategoriaBayer() == idCategoria){
				linha = abaMaster.createRow(num); // cria linha 1
				gruposNaLista(montaGrupo, linha, i, num+1,abaMaster);
				num++;
			}else{
				
			}
		}
		
		posi = num;
		num = 1+posi;

		for(int i= 0;i < 2;i++){
			linha = abaMaster.createRow(posi++); // cria linha 1
			
			XSSFCell cell = linha.createCell(0);    // Cria celula
			criaLinhaECelulaTexto(cell, linha, "" , 0);
			criaLinhaECelulaTextoListaSuspensa(cell,linha,num-1);
			
			criaLinhaECelulaNumeroBranco(cell, linha, 0 , 2,novoExcel);
			criaLinhaECelulaNumeroBranco(cell, linha, 0 , 3,novoExcel);
			criaLinhaECelulaNumeroBranco(cell, linha, 0 , 4,novoExcel);
			criaLinhaECelulaNumeroBranco(cell, linha, 0 , 5,novoExcel);
			criaLinhaECelulaNumeroBranco(cell, linha, 0 , 6,novoExcel);
			criaLinhaECelulaNumeroBranco(cell, linha, 0 , 7,novoExcel);
			criaLinhaECelulaNumeroBranco(cell, linha, 0 , 8,novoExcel);
			criaLinhaECelulaNumeroBranco(cell, linha, 0 , 9,novoExcel);
			criaLinhaECelulaNumeroBranco(cell, linha, 0 , 10,novoExcel);
			criaLinhaECelulaNumeroBranco(cell, linha, 0 , 11,novoExcel);
			
			calculoRopapExcelRodapeCalculos.setaCelulaComFormula(cell, linha, posi);
			
			num++;
		}
		
		posi = num;
		num = 1+posi;
		
		int[] posicoes = new int[2];
		posicoes[0] = posi;
		posicoes[1] = num;
		
		return posicoes;

	}
	
	
	public void gruposNaLista(List<CorpoGrupoCategoriaBayer> montaGrupo,XSSFRow linha,int i, int num,XSSFSheet abaMaster){
		
		GeraExcelRodapeCalculos calculoRopapExcelRodapeCalculos =  new GeraExcelRodapeCalculos(novoExcel,0,0,abaMaster);
		
		
		XSSFCell cell = linha.createCell(0);    // Cria celula
	
		wrapTeste(cell, linha, montaGrupo.get(i).getInfoGrupo(), 0, new int[]{255,255,255});
		
		criaLinhaECelulaTextoListaSuspensa(cell, linha, montaGrupo.get(i).getTipoServico(),num,abaMaster); /** COMBO PROBLEMÁTICO -  Resolvido yeaahhhhhh */
		criaLinhaECelulaQuantDiaria(cell, linha, montaGrupo.get(i).getQuantidade(), 2,novoExcel);
		criaLinhaECelulaQuantDiaria(cell, linha, montaGrupo.get(i).getDiaria(), 3,novoExcel);
		criaLinhaECelulaNumero(cell, linha, montaGrupo.get(i).getOrcamento().doubleValue(), 4,novoExcel); //Valor Unitário Negociado 
		criaLinhaECelulaNumero(cell, linha,  montaGrupo.get(i).getPrecoItem().doubleValue(), 5,novoExcel); //Valor Unitário inicial 
		
		wrapTeste(cell, linha,"", 8, new int[]{255,255,255});
		wrapTeste(cell, linha,"", 9, new int[]{255,255,255});
		wrapTeste(cell, linha,"", 10, new int[]{255,255,255});
		wrapTeste(cell, linha,"", 11, new int[]{255,255,255});
		
		calculoRopapExcelRodapeCalculos.setaCelulaComFormula(cell, linha, num);
	}

	public void criaLinhaECelulaFormula(XSSFCell cell,XSSFRow linha,String valor,int posicaoCelula){
			
			XSSFCellStyle styleReal = novoExcel.createCellStyle();
			styleReal.setDataFormat(novoExcel.createDataFormat().getFormat("R$    #,##0.00"));
			styleReal.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			styleReal.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			
			styleReal = setaBordaSimples(styleReal);
			
			cell = linha.createCell(posicaoCelula);	
			cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
			cell.setCellStyle(styleReal);
			cell.setCellFormula(valor);
		}
	
	
	public void criaLinhaECelulaMesclado(XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula,XSSFSheet abaMaster,int priColum,
			int secColum, short alinhamento){
		
		CellStyle cs = novoExcel.createCellStyle();
		cs.setWrapText(true);
		cs.setAlignment(alinhamento);

		cell.setCellStyle(cs);
	    cell.setCellValue(texto);
	    
		GeraMergeLinhaComStylodaLinha merge = new GeraMergeLinhaComStylodaLinha(abaMaster, novoExcel);
		merge.geraMerge(cell, linha.getRowNum(), priColum, secColum);

	}

	
	public XSSFCellStyle styloBorda(int[]corFundo, short alinhamento){
		XSSFCellStyle corCelula = novoExcel.createCellStyle();
		corCelula.setWrapText(true);
		corCelula.setAlignment(alinhamento);
		corCelula.setFillPattern(CellStyle.SOLID_FOREGROUND);
		corCelula.setFillForegroundColor(new XSSFColor(new java.awt.Color(corFundo[0],corFundo[1],corFundo[2])));
		corCelula.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		corCelula.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP); 
		corCelula.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		corCelula.setBottomBorderColor(IndexedColors.WHITE.getIndex());
		corCelula.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		corCelula.setLeftBorderColor(IndexedColors.WHITE.getIndex());
		corCelula.setBorderRight(XSSFCellStyle.BORDER_THIN);
		corCelula.setRightBorderColor(IndexedColors.WHITE.getIndex());
		corCelula.setBorderTop(XSSFCellStyle.BORDER_THIN);
		corCelula.setTopBorderColor(IndexedColors.WHITE.getIndex());
		return corCelula;
	}
	
	
	
	public void criaLinhaECelulaTexto(XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula){
		
		String info = concatenaWrap(texto);
		
		String info2 = info;
		
		XSSFCellStyle estilo = novoExcel.createCellStyle();
		estilo.setWrapText(true);
		estilo.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
		estilo = setaBordaSimples(estilo);
		cell.setCellStyle(estilo);
		cell.setCellValue(info2.toString());
	}
	
	public void wrapTeste(XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula,int[]cor){
		
		cell = linha.createCell(posicaoCelula);
		cell.setCellValue(texto);
		
		XSSFFont font = novoExcel.createFont();
		font.setFontName("Arial");
		font.setItalic(false);
		font.setFontHeightInPoints((short)9);
		font.setBold(false);
		
		XSSFCellStyle corCelula = novoExcel.createCellStyle();
		corCelula.setFont(font);
		corCelula.setWrapText(true);
		corCelula.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		corCelula.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		corCelula = setaBordaSimples(corCelula);
		
		cell.setCellStyle(corCelula);
	}
	
	public String concatenaWrap(String texto){
		int c = texto.length();
		StringBuilder concat = new StringBuilder(texto);
		int num = 40;
		for (int i = 0; i < texto.length(); i++) {
			while (num <= c ) {
				concat.insert(texto.length() - c + num, "\\n");
				num = num + 50;
			}
		}
		String t2 = concat.toString();
		return t2;
	}

	public void criaLinhaECelulaTextoListaSuspensa(XSSFCell cell,XSSFRow linha,int posicaoCelula){
	
		cell = linha.createCell(1);
		cell.setCellStyle(criaBordasSimples());

		GeraListaSuspensa listaSuspensas = new GeraListaSuspensa();
		listaSuspensas.listaSuspensa(abaMaster,posicaoCelula);
	}

	public void criaLinhaECelulaTextoListaSuspensaTextos(int linha,int primColum,int ultColum, String[] textos){
		GeraListaSuspensa listaSuspensa = new GeraListaSuspensa();
		listaSuspensa.listaSuspensaFixa(abaMaster,linha,primColum,ultColum,textos);
	}
	
	
	
	public void criaLinhaECelulaTextoListaSuspensa(XSSFCell cell,XSSFRow linha,String texto,int posicaoCelula,XSSFSheet abaMaster){
		cell = linha.createCell(1);
		cell.setCellStyle(criaBordasSimples());
		cell.setCellValue(texto);
		GeraListaSuspensa listaSuspensa = new GeraListaSuspensa();
		listaSuspensa.listaSuspensa(abaMaster,posicaoCelula-1);
	}
	
	public void criaLinhaECelulaNumero(XSSFCell cell,XSSFRow linha,double valor,int posicaoCelula,XSSFWorkbook novoExcel){
		
		XSSFCellStyle styleReal = novoExcel.createCellStyle();
		styleReal.setDataFormat(novoExcel.createDataFormat().getFormat("R$    #,##0.00"));
		styleReal.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		styleReal.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		styleReal = setaBordaSimples(styleReal);
		
		cell = linha.createCell(posicaoCelula);
		cell.setCellStyle(styleReal);
		cell.setCellValue(valor);
	}

	public void criaLinhaECelulaNumeroBranco(XSSFCell cell,XSSFRow linha,double valor,int posicaoCelula,XSSFWorkbook novoExcel){
		cell = linha.createCell(posicaoCelula);
		cell.setCellStyle(criaBordasSimples());
	}
	
	public void criaLinhaECelulaQuantDiaria(XSSFCell cell,XSSFRow linha,double valor,int posicaoCelula,XSSFWorkbook novoExcel){
		cell = linha.createCell(posicaoCelula);
		
		cell.setCellStyle(criaBordasSimples());
		cell.setCellValue(valor);
	}
	
	public XSSFCellStyle criaBordasSimples(){
		
		XSSFCellStyle corBorda = novoExcel.createCellStyle();
		corBorda.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
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
	
	
	
	
	
	
	
	
	
	
public int[] montaGruposDinamicosOpcionais(XSSFSheet abaMaster,BaseExcelBayer base,List<CorpoGrupoCategoriaBayer> montaGrupo,XSSFRow linha,int num, int posi){
		
		int primeiraLinha = 3+1;
		int ultimaLinha = primeiraLinha;
		
		num = 4;
		
		for(int i = 0; i < montaGrupo.size();i++ ){
				ultimaLinha++;
		}
		
		int[] posicaoParaFormulaSoma = new int[2];
		posicaoParaFormulaSoma[0] = primeiraLinha;
		posicaoParaFormulaSoma[1] = ultimaLinha+2;

		for(int i = 0; i < montaGrupo.size();i++ ){
				linha = abaMaster.createRow(num); // cria linha 1
				gruposNaListaOpcionais(montaGrupo, linha, i, num+1,abaMaster);
				num++;
		}
		
		posi = num;
		num = 1+posi;
		
		int[] posicoes = new int[2];
		posicoes[0] = posi;
		posicoes[1] = num;
		
		return posicoes;

	}
	
	public void gruposNaListaOpcionais(List<CorpoGrupoCategoriaBayer> montaGrupo,XSSFRow linha,int i, int num,XSSFSheet abaMaster){
		
		XSSFCell cell = linha.createCell(0);    // Cria celula
		wrapTeste(cell, linha, montaGrupo.get(i).getInfoGrupo(), 0, new int[]{255,255,255});
		
		criaLinhaECelulaTextoListaSuspensa(cell, linha, montaGrupo.get(i).getTipoServico(),num,abaMaster); /** COMBO PROBLEMÁTICO */
		criaLinhaECelulaQuantDiaria(cell, linha, montaGrupo.get(i).getQuantidade(), 2,novoExcel);
		criaLinhaECelulaQuantDiaria(cell, linha, montaGrupo.get(i).getDiaria(), 3,novoExcel);
		criaLinhaECelulaNumero(cell, linha, montaGrupo.get(i).getPrecoItem().doubleValue(), 4,novoExcel); // 	Valor Unitário inicial
		setaCelulaComFormulaOpcional(cell, linha, num);
		
		wrapTeste(cell, linha, "", 6, new int[]{255,255,255});
		wrapTeste(cell, linha, "", 7, new int[]{255,255,255});
		wrapTeste(cell, linha, "", 8, new int[]{255,255,255});
		wrapTeste(cell, linha, "", 9, new int[]{255,255,255});
		
		
	}
	
	
	public void setaCelulaComFormulaOpcional(XSSFCell cell,XSSFRow linha,int num){
		criaLinhaECelulaFormula(cell, linha, "C"+num+"*D"+num+"*E"+num, 5); /** SubTotal */
	}
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
