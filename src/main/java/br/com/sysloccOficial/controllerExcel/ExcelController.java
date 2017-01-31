package br.com.sysloccOficial.controllerExcel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.LocalEvento;

@Controller
@Transactional
public class ExcelController {
	
	@PersistenceContext private EntityManager manager;
	@Autowired private TextosStaticosExcel textos;
	@Autowired private Utilitaria util;
	@Autowired private AuxExcelSQL sql;
	@Autowired private MontaCategorias montaCategorias;
	@Autowired private MontaGrupos montaGrupos;
 	@Autowired private MontaValoresTotais montaValoresTotais;
// 	@Autowired private MontaCalculosFinais montaCalculosFinais;
	
	ModelAndView MV = new ModelAndView();
	
	
@RequestMapping("/geradorDeExcel")
public ModelAndView geradorDeExcel(Integer idLista) throws IOException{
	
	MV.setViewName("producao/geraExcel/geraExcelBayer");
	
    
//	idLista = 1934;	
	Lista lista = manager.find(Lista.class, idLista);

		
	// Topo da Planilha 	
	// ----------------------------------------- //	
	 	Calendar c = Calendar.getInstance(); 
		SimpleDateFormat s = new SimpleDateFormat("HHmmss"); 
		String a = s.format(c.getTime());
	 	
		String tituloJob = lista.getIdJob().getTitulo().replace(" ", "");
		String tituloJob2 = tituloJob.replaceAll("[^A-Za-z0-9 ]", "");
		
	//	JOptionPane.showMessageDialog(null, ""+tituloJob2);
		
		
		// Cria as saidas do arquivo	
		XSSFWorkbook workbook = new XSSFWorkbook();
		FileOutputStream out = new FileOutputStream(new File ("C:/SYSLOC/upload/excel/"+tituloJob2+a+".xlsx"));
		String downloadExcel = "upload/upload/excel/"+tituloJob2+a+".xlsx";
		
		
		MV.addObject("nomeArquivo", downloadExcel);
		
		XSSFSheet planilha = workbook.createSheet("Planilha"); // Nome da aba
		
		AuxExcelEstilos.larguraColunas(planilha);
		
		AuxExcelCabecalho cabecalho = new AuxExcelCabecalho();
		
		// Cria texto de inicio e texto "Tipo Faturamento" e Linha cabecalho da tabela 
		
		List<LocalEvento> localEvento = sql.listaEventosJob(lista.getIdJob().getIdJob());
		
		cabecalho.criaCabecalho(workbook, planilha,lista,localEvento);

// ------------------------------------------ //
// Monta a lista de Categorias da Lista	
	List<CorpoCategoria> corpoCategoria = 	montaCategorias.montaCategoria(idLista);

// Monta a lista de Grupos de Cada Categoria
	List<CorpoGrupos> corpoGrupos = montaGrupos.montaGrupo(idLista);

// ---------------------------------------------------------------------------------- //
// Monta as categorias e as linhas da Planilha

	AuxExcelMontaCategoria monta = new AuxExcelMontaCategoria();
	Integer linha  = monta.montaCateg(workbook, planilha,corpoCategoria, corpoGrupos);
	
// ----------------------------------------------------------------------------------- //
// SubTotal Fim da Planilha
	// Pega a última linha da tabela
	linha = linha + 1;	
	
	// Textos Estáticos de SubTotal
	XSSFRow rowNotasRodape = planilha.createRow(linha+2);
	List<String> notasRod = AuxExcelEstilos.notasRodapeTabela();
	
	int ps = 4;
	for(int j=0;j< notasRod.size();j++){
		Cell cell = rowNotasRodape.createCell(ps);
		rowNotasRodape.setHeight((short)700);
		cell.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleCentral(workbook));
		cell.setCellValue(notasRod.get(j));
		ps = ps + 1;
	}
	
	//Monta os Valores de SubTotais na Tabela
	XSSFRow rowSubTotal = planilha.createRow(linha);
	montaValoresTotais.montaRodapePlanilha(idLista, rowSubTotal, workbook);
	
// ------------------------------------------------------------------------- //	
// Cálculos Finais do relatório
	XSSFRow rowCalculosFinais = planilha.createRow(linha+4);
	montaValoresTotais.calculosFinais(idLista, rowCalculosFinais, workbook, linha+4, planilha);
 //	montaCalculosFinais.calculosFinais(idLista, rowCalculosFinais, workbook,linha+4,planilha);
	
	
// ------------------------------------------------------------------------- //	
//  Salva Arquivo no disco	
	workbook.write(out);
	out.close();
	System.out.println("ok");
	return MV;
 }

}
