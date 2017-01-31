package br.com.sysloccOficial.controllers;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
	
//Gera Arquivo Excel
	@RequestMapping("/exportaExcel")
	public ModelAndView exportaExcel(Integer idLista){
		
		MV.setViewName("producao/geraExcel");
		
		Calendar c = Calendar.getInstance(); 
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss"); 
		String a = s.format(c.getTime());
		
		try {
			
			
			
			
			String fileName = "C:/SYSLOC/upload/excel/"+a+".xls";
			String downloadExcel = "upload/upload/excel/"+a+".xls";
			
			MV.addObject("nomeArquivo", downloadExcel);
			WritableWorkbook workbook = Workbook.createWorkbook(new File(fileName));
			WritableSheet sheet = workbook.createSheet("Planilha 1", 0);
			
			
			sheet.setColumnView(0, 30); 
			sheet.setColumnView(1, 12); 
			sheet.setColumnView(2, 12); 
			sheet.setColumnView(3, 12); 
			sheet.setColumnView(4, 65); 
			sheet.setColumnView(5, 40); 
			
			//Adding A Label
			Label linha = new Label(0,1,"Linha",formataCabecalho());
			Label FatLocco = new Label(1,1,"Fat loCCo",formataCabecalho());
			Label FatDireto = new Label(2,1,"Fat Direto",formataCabecalho());
			Label Opcional = new Label(3,1,"Opcional",formataCabecalho());
			Label Info = new Label(4,1,"Informações",formataCabecalho());
			Label NaoIncluso = new Label(5,1,"Não inclusos no custo",formataCabecalho());
			sheet.addCell(linha);
			sheet.addCell(FatLocco);
			sheet.addCell(FatDireto);
			sheet.addCell(Opcional);
			sheet.addCell(Info);
			sheet.addCell(NaoIncluso);

			
			int categoria1 = 1;
			List<Categoria>categorias = producaoDAO.categoriaPorIdLista(idLista);
			
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
				
					categoria1 = categoria1 + 1;
					// Imprime o nome da categoria no Excel ----------------------------------------------------- //
					
					Label categoria = new Label(0,categoria1, categoriaNome,alinhaCentroComTodasBordasFontBold());sheet.addCell(categoria);
					Label colum1 = new Label(1,categoria1,"",BordaCimaBaixo());sheet.addCell(colum1);
					Label colum2 = new Label(2,categoria1,"",BordaCimaBaixo());sheet.addCell(colum2);
					Label colum3 = new Label(3,categoria1,"",BordaCimaBaixo());sheet.addCell(colum3);
					Label colum4 = new Label(4,categoria1,"",BordaCimaBaixo());sheet.addCell(colum4);
					Label colum5 = new Label(5,categoria1,"",BordaCimaBaixoDireita());sheet.addCell(colum5);
					// ------------------------------------------------------------------------------------------ //
				}
							
			for( int j = 0; j < grupo.size(); j++){
				
				
			 	Integer idGrupo1 = grupo.get(j).getIdgrupo();
				
				List<ProdutoGrupo> teste = produtoGrupoDAO.listaProdutoGrupoPorGrupo(idGrupo1);
			    
				if(teste.isEmpty()){
					String GrupoNome = grupo.get(j).getGrupo(); // imprime nome categoria
					Label grupos = new Label(0,categoria1, GrupoNome,alinhaCentroComTodasBordas());sheet.addCell(grupos);
				}else{
					categoria1 = categoria1 + 1;
					
				String GrupoNome = grupo.get(j).getGrupo(); // imprime nome categoria
				Label grupos = new Label(0,categoria1, GrupoNome,alinhaCentroComTodasBordas());sheet.addCell(grupos);
				
				BigDecimal grupoIncideImpostoValor = grupo.get(j).getGrupoValorIncideImposto();
				String grupoIncideImpostoValorConvertido = grupoIncideImpostoValor.toString();
				
				BigDecimal grupofatDiretoValor = grupo.get(j).getGrupoValorNaoIncideImposto();
				String grupofatDiretoValorConvertido = grupofatDiretoValor.toString();
				
				
				if(grupo.get(j).isOpcional() == false){ // Imprimi FatLocco da Linha - opcional = 0 
				  Number grupofatLocco = new Number (1, categoria1,converteStringParaDouble(grupoIncideImpostoValorConvertido), formataNumeroParaReal());
				  sheet.addCell(grupofatLocco);							
				}else {
					Label grupofatLocco = new Label(1,categoria1, " ",alinhaCentroComTodasBordas());
					sheet.addCell(grupofatLocco);
				}
				
				if(grupo.get(j).isOpcional() == false ){// Imprime FatDireto - opcional = 0
					Number grupofatDireto = new Number(2,categoria1,converteStringParaDouble(util.formataValores(grupofatDiretoValorConvertido)),formataNumeroParaReal());
					sheet.addCell(grupofatDireto);
				}else{
					 Label grupofatDireto = new Label(2,categoria1," ",alinhaCentroComTodasBordas());
					 sheet.addCell(grupofatDireto);
				}
				
				if(grupo.get(j).isOpcional() == true ){// Imprime Opcional
					
					BigDecimal opcional = (grupofatDiretoValor.add(grupoIncideImpostoValor));
				    String opcionalz = util.ConverteDolarParaReal(opcional.toString());
					
					
					Number grupoOpcional = new Number(3,categoria1,converteStringParaDouble(util.formataValores(opcionalz)),formataNumeroParaReal());
					sheet.addCell(grupoOpcional);
					
					
							
				}else{
					Label grupoOpcional = new Label(3,categoria1,"",alinhaCentroComTodasBordas());
					sheet.addCell(grupoOpcional);
				}
				
		
				Label grupoInformacao = new Label(4,categoria1,grupo.get(j).getInformacoes(),alinhaCentroComTodasBordas());
				Label grupoNaoIncluidos = new Label(5,categoria1,grupo.get(j).getNecessidades(),alinhaCentroComTodasBordas());
				sheet.addCell(grupoInformacao);
				sheet.addCell(grupoNaoIncluidos);
				}
		}

					
					
					if(grupo.isEmpty()){
						
					}else{
						categoria1 = categoria1+1;	
					/*Imprimi subTotal de Cada Categoria */
					Label categoriaNomeTotals = new Label(0,categoria1, categoriaNome+": "+subTotalz,alinhaCentroComTodasBordasFontBold());
					/*Imprimi Total FatLocco da Categori */
					Number TotalsFatLocco = new Number (1, categoria1,converteStringParaDouble(util.formataValores(GrupoFatLocco)), formataNumeroParaRealComBold());
					/*Imprimi Total FatDireto da Categor */
					Number TotalFatDireto = new Number (2, categoria1,converteStringParaDouble(util.formataValores(GrupoFatDireto)), formataNumeroParaRealComBold());
						   Label column3 = new Label(3,categoria1,"",BordaCimaBaixo());sheet.addCell(column3);					
						   Label column4 = new Label(4,categoria1,"",BordaCimaBaixo());sheet.addCell(column4);					
						   Label column5 = new Label(5,categoria1,"",BordaCimaBaixoDireita());sheet.addCell(column5);					
					sheet.addCell(categoriaNomeTotals);
					sheet.addCell(TotalsFatLocco);
					sheet.addCell(TotalFatDireto);
					/*sheet.addCell(OpcionalImprime);*/
					}
			}
			
			BigDecimal subTotalGeralFatLocco = new BigDecimal("0");
			BigDecimal subTotalGeralFatDireto = new BigDecimal("0");
			
			subTotalGeralFatLocco = producaoDAO.subTotalFatLocco(idLista);
			subTotalGeralFatDireto = producaoDAO.subTotalFatDireto(idLista);
			
			
			
			
			
			String subTotalGeralFatLoccoConv = util.ConverteDolarParaReal(subTotalGeralFatLocco.toString());
			String subTotalGeralDiretoConv = util.ConverteDolarParaReal(subTotalGeralFatDireto.toString());
			
			
			BigDecimal novo = (subTotalGeralFatLocco.add(subTotalGeralFatDireto));
			String subTotaly = util.ConverteDolarParaReal(novo.toString());
			
			Label linhaVazia = new Label (5,categoria1+1, "",BordaCimaBaixoDireita());sheet.addCell(linhaVazia);

			
			Label subTotal = new Label(0,categoria1+2, "Sub Total: "+ subTotaly,formataSubTotal());
			
			Number subTotalFatLocco = new Number (1,categoria1+2, converteStringParaDouble(util.formataValores(subTotalGeralFatLoccoConv)),formataSubTotal());
			Number subTotalFatDireto = new Number (2,categoria1+2, converteStringParaDouble(util.formataValores(subTotalGeralDiretoConv)),formataSubTotal());
			Label vazio3 = new Label (3,categoria1+2, "",BordaCimaBaixo());
			Label vazio4 = new Label (4,categoria1+2, "",BordaCimaBaixo());
			Label vazio5 = new Label (5,categoria1+2, "",BordaCimaBaixoDireita());
			
			
			sheet.addCell(subTotal);
			sheet.addCell(subTotalFatLocco);
			sheet.addCell(subTotalFatDireto);
			sheet.addCell(vazio3);
			sheet.addCell(vazio4);
			sheet.addCell(vazio5);
			
			workbook.write();
			workbook.close();
			
			
			
			} catch (Exception e) {
			//	JOptionPane.showMessageDialog(null, "Deu um erro ao gerar a Lista. Alguma linha está com o valor vazio."+e);
		}
		return MV;	
	}
	
	public Double converteStringParaDouble(String valor){
		BigDecimal z = new BigDecimal("0");	
		z = (new BigDecimal(valor));	
		Double teste = Double.valueOf(z.doubleValue());
		return teste;
	}

}
