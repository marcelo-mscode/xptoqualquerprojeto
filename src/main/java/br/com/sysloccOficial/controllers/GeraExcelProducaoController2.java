package br.com.sysloccOficial.controllers;

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

import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.Excel.BaseGeraExcel;
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
public class GeraExcelProducaoController2 extends GeraAuxiliarExcel {
	
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
	
	final int INICIO_CABECALHO_LINHA = 7;
	BigDecimal feeDaLista;
	
	//Gera Arquivo Excel
	@RequestMapping("/exportaExcel2")
	public String exportaExcel(Integer idLista) throws IOException, RowsExceededException, WriteException{
		MV.setViewName("producao/geraExcel");
		
		Calendar c = Calendar.getInstance(); 
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss"); 
		String a = s.format(c.getTime());

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(" Planilha 1 ");
//		setaLarguraDasColunas(sheet);
		montaCabecalhoAntesDasCategorias(sheet);

		BaseGeraExcel base = new BaseGeraExcel("K:/SYSLOC/upload/excel/"+a);
		FileOutputStream out = base.caminhoeNomeDoArquivo();
		String downloadExcel = base.caminhoDownloadExcel("Galderma","upload/upload/excel/"+"Galderma");
		
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
            
                LINHA_DA_CATEGORIA = imprimeLinhaCategoria(sheet, LINHA_DA_CATEGORIA, categoriaNome);
            }
                
                //Método que monta as categorias no Excel
                LINHA_DA_CATEGORIA = montaLinhasDeCadaCategoria(sheet, LINHA_DA_CATEGORIA, grupo);
                /*
                if(grupo.isEmpty()){
                    
                }else{
                    LINHA_DA_CATEGORIA = montaSubTotaisDeCadaCategoria(sheet, LINHA_DA_CATEGORIA, GrupoFatLocco,
                            GrupoFatDireto, subTotalz, categoriaNome, grupo.size());
                }
                
                linhasDecadaSubTotalCategoria.add(LINHA_DA_CATEGORIA+1);*/
                
        }
		
		
		
		
		
		
/// ------------------------------------------------------------------		
		
        base.fechaPlanilha(workbook, out);
		return "redirect:apontamentos";
		
	}

    private int imprimeLinhaCategoria(XSSFSheet sheet, int LINHA_DA_CATEGORIA, String categoriaNome) {
        LINHA_DA_CATEGORIA = LINHA_DA_CATEGORIA + 1;
        // Imprime o nome da categoria no Excel ----------------------------------------------------- //
      /*  Label categoria = new Label(0,LINHA_DA_CATEGORIA, categoriaNome,alinhaCentroComTodasBordasFontBold());sheet.addCell(categoria);
        Label colum1 = new Label(1,LINHA_DA_CATEGORIA,"",BordaCimaBaixo());sheet.addCell(colum1);
        Label colum2 = new Label(2,LINHA_DA_CATEGORIA,"",BordaCimaBaixo());sheet.addCell(colum2);
        Label colum3 = new Label(3,LINHA_DA_CATEGORIA,"",BordaCimaBaixo());sheet.addCell(colum3);
        Label colum4 = new Label(4,LINHA_DA_CATEGORIA,"",BordaCimaBaixo());sheet.addCell(colum4);
        Label colum5 = new Label(5,LINHA_DA_CATEGORIA,"",BordaCimaBaixoDireita());sheet.addCell(colum5);*/
        
        Row row = sheet.createRow(LINHA_DA_CATEGORIA);
        Cell cell = row.createCell(0); cell.setCellValue(categoriaNome);
        Cell cell1 = row.createCell(1); cell1.setCellValue("");
        Cell cell2 = row.createCell(2); cell2.setCellValue("");
        Cell cell3 = row.createCell(3); cell3.setCellValue("");
        Cell cell4 = row.createCell(4); cell4.setCellValue("");
        Cell cell5 = row.createCell(5); cell5.setCellValue("");
        
        
        
        
        // ------------------------------------------------------------------------------------------ //
        return LINHA_DA_CATEGORIA;
    }
	
	private void setaLarguraDasColunas(XSSFSheet sheet2) {
        //Largura de cada Coluna
        sheet2.setColumnWidth(0, 30); 
        sheet2.setColumnWidth(1, 120); 
        sheet2.setColumnWidth(2, 120); 
        sheet2.setColumnWidth(3, 120); 
        sheet2.setColumnWidth(4, 650); 
        sheet2.setColumnWidth(5, 40);
    }
	
	private void montaCabecalhoAntesDasCategorias(XSSFSheet sheet) throws WriteException, RowsExceededException {
        //Adding A Label
       /* Label linha = new Label(0,INICIO_CABECALHO_LINHA,"Linha",formataCabecalho());
        Label FatLocco = new Label(1,INICIO_CABECALHO_LINHA,"Fat loCCo",formataCabecalho());
        Label FatDireto = new Label(2,INICIO_CABECALHO_LINHA,"Fat Direto/Nota de Debito",formataCabecalho());
        Label Opcional = new Label(3,INICIO_CABECALHO_LINHA,"Opcional",formataCabecalho());
        Label Info = new Label(4,INICIO_CABECALHO_LINHA,"Informações",formataCabecalho());
        Label NaoIncluso = new Label(5,INICIO_CABECALHO_LINHA,"Não inclusos no custo",formataCabecalho());
        */
        
        Row row = sheet.createRow(INICIO_CABECALHO_LINHA);
        Cell cell = row.createCell(0); cell.setCellValue("Linha");
        Cell cell1 = row.createCell(1); cell1.setCellValue("Fat loCCo");
        Cell cell2 = row.createCell(2); cell2.setCellValue("Fat Direto/Nota de Debito");
        Cell cell3 = row.createCell(3); cell3.setCellValue("Opcional");
        Cell cell4 = row.createCell(4); cell4.setCellValue("Informações");
        Cell cell5 = row.createCell(5); cell5.setCellValue("Não inclusos no custo");
        
        
        
    /*  sheet2.addCell(linha);
        sheet2.addCell(FatLocco);
        sheet2.addCell(FatDireto);
        sheet2.addCell(Opcional);
        sheet2.addCell(Info);
        sheet2.addCell(NaoIncluso);*/
    }
	
    private int montaLinhasDeCadaCategoria(XSSFSheet sheet, int LINHA_DA_CATEGORIA, List<Grupo> grupo)
            throws WriteException, RowsExceededException {
        
        for (int j = 0; j < grupo.size(); j++) {
            
            Integer idGrupo1 = grupo.get(j).getIdgrupo();
            
            List<ProdutoGrupo> teste = produtoGrupoDAO.listaProdutoGrupoPorGrupo(idGrupo1);
            
            if (teste.isEmpty()) {
                String GrupoNome = grupo.get(j).getGrupo(); // imprime nome categoria
                // Label grupos = new Label(0,LINHA_DA_CATEGORIA,
                // GrupoNome,alinhaCentroComTodasBordas());sheet.addCell(grupos);
                
                Row row = sheet.createRow(LINHA_DA_CATEGORIA);
                Cell cell = row.createCell(0);
                cell.setCellValue(GrupoNome);
                
            } else {
                LINHA_DA_CATEGORIA = LINHA_DA_CATEGORIA + 1;
                
                String GrupoNome = grupo.get(j).getGrupo(); // imprime nome categoria
                // Label grupos = new Label(0,LINHA_DA_CATEGORIA,
                // GrupoNome,alinhaCentroComTodasBordas());sheet.addCell(grupos);
                Row row = sheet.createRow(LINHA_DA_CATEGORIA);
                Cell cell = row.createCell(0);
                cell.setCellValue(GrupoNome);
                
                BigDecimal grupoIncideImpostoValor = grupo.get(j).getGrupoValorIncideImposto();
                String grupoIncideImpostoValorConvertido = grupoIncideImpostoValor.toString();
                
                BigDecimal grupofatDiretoValor = grupo.get(j).getGrupoValorNaoIncideImposto();
                String grupofatDiretoValorConvertido = grupofatDiretoValor.toString();
                
                if (grupo.get(j).isOpcional() == false) { // Imprimi FatLocco da Linha - opcional = 0
                    Number grupofatLocco = new Number(1, LINHA_DA_CATEGORIA,
                    converteStringParaDouble(grupoIncideImpostoValorConvertido), formataNumeroParaReal());
                    // sheet.addCell(grupofatLocco);
 //                   Row row0 = sheet.createRow(grupofatLocco);
 //                   Cell cell0 = row0.createCell(1);
                    
                    Row row1 = sheet.createRow(LINHA_DA_CATEGORIA);
                    Cell cell1 = row1.createCell(1);
                    cell1.setCellValue(converteStringParaDouble(grupoIncideImpostoValorConvertido));
                    
                    /*
                     * }else {
                     * Label grupofatLocco = new Label(1,LINHA_DA_CATEGORIA, " ",alinhaCentroComTodasBordas());
                     * sheet.addCell(grupofatLocco);
                     * }
                     */
                    /*
                     * if(grupo.get(j).isOpcional() == false ){// Imprime FatDireto - opcional = 0
                     * Number grupofatDireto = new
                     * Number(2,LINHA_DA_CATEGORIA,converteStringParaDouble(util.formataValores
                     * (grupofatDiretoValorConvertido)),formataNumeroParaReal());
                     * sheet.addCell(grupofatDireto);
                     * }else{
                     * Label grupofatDireto = new Label(2,LINHA_DA_CATEGORIA," ",alinhaCentroComTodasBordas());
                     * sheet.addCell(grupofatDireto);
                     * }
                     * if(grupo.get(j).isOpcional() == true ){// Imprime Opcional
                     * BigDecimal opcional = (grupofatDiretoValor.add(grupoIncideImpostoValor));
                     * String opcionalz = util.ConverteDolarParaReal(opcional.toString());
                     * Number grupoOpcional = new
                     * Number(3,LINHA_DA_CATEGORIA,converteStringParaDouble(util.formataValores
                     * (opcionalz)),formataNumeroParaReal());
                     * sheet.addCell(grupoOpcional);
                     * }else{
                     * Label grupoOpcional = new Label(3,LINHA_DA_CATEGORIA,"",alinhaCentroComTodasBordas());
                     * sheet.addCell(grupoOpcional);
                     * }
                     * Label grupoInformacao = new
                     * Label(4,LINHA_DA_CATEGORIA,grupo.get(j).getInformacoes(),alinhaCentroComTodasBordas());
                     * Label grupoNaoIncluidos = new
                     * Label(5,LINHA_DA_CATEGORIA,grupo.get(j).getNecessidades(),alinhaCentroComTodasBordas());
                     * sheet.addCell(grupoInformacao);
                     * sheet.addCell(grupoNaoIncluidos);
                     * }
                     */
                }
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