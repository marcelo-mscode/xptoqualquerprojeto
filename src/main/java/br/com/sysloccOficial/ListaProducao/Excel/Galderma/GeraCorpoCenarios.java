package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import br.com.sysloccOficial.Excel.ExcelImagem;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;

@Component
public class GeraCorpoCenarios {
	
	/**
	 * Método para gerar o cabeçalho da aba
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	/*public static void geraCabecalhoAbaCenarios(XSSFSheet cenario,XSSFWorkbook excelGalderma,String nomeAba){

	}*/
	
	public static void geraCorpoAbaCenarios(XSSFSheet cenario,XSSFWorkbook excelGalderma,String nomeAba,
											List<CorpoGrupoCategoriaGalderma> gruposParaExcel,List<GrupoCategoriaGalderma> categoriasGalderma)
											throws FileNotFoundException, IOException{
		
		cenario = excelGalderma.createSheet(nomeAba); /** Cria Aba Cenarios da planilha */
		cenario.setZoom(80);
		ExcelImagem.InsereImagem(excelGalderma, cenario, "C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia2.png",0.35);
		GeraCabecalhoExcelGalderma.geraCabecalho(cenario, excelGalderma, nomeAba);

		
		//Não precisa mexer mais
		CorpoCenarioGaldermaTopo.geraTopoEstatico(excelGalderma, cenario, 17);
		
		
		passaInformacoesCorpoExcel(cenario, excelGalderma, gruposParaExcel, categoriasGalderma);
		
		//Não mexer mais
		GeraTextoRodapeCenarios.geraTextoRodape(excelGalderma, cenario);
	}

	/**
	 * Separa por Categoria 
	 * 
	 * Método que passa as informações para gerar o corpo do
	 * Excel com as informações da planilha do cliente
	 * Info de quantidade, diaria, valor unitario inicial, negociado
	 */
	private static void passaInformacoesCorpoExcel(XSSFSheet cenario, XSSFWorkbook excelGalderma, List<CorpoGrupoCategoriaGalderma> gruposParaExcel,List<GrupoCategoriaGalderma> categoriasGalderma) {
		
		int linhaComecoCategorias = 19;
		int linhaComecoInfoCategorias = 21;


		int qtdInfoGrupo = 0;
		
		for (int i = 0; i < categoriasGalderma.size(); i++) {
			
			
			linhaComecoCategorias = linhaComecoCategorias+1;

			GeraTextoCategorias.geratextoCategorias(excelGalderma, cenario, linhaComecoCategorias + qtdInfoGrupo,categoriasGalderma.get(i).getCategoria()); // ok
			
			System.out.println(linhaComecoCategorias + qtdInfoGrupo);
		
			for (int j = 0; j < gruposParaExcel.size(); j++) {
						if(categoriasGalderma.get(i).getIdCategoriaGalderma() == gruposParaExcel.get(j).getIdCategoriaGalderma()){
					    //Chama método para gerar o corpo
						linhaComecoInfoCategorias=linhaComecoInfoCategorias+qtdInfoGrupo;
						CorpoCenarioGalderma.corpoCenario(excelGalderma, cenario,linhaComecoInfoCategorias,gruposParaExcel.get(j));
						
						qtdInfoGrupo = qtdInfoGrupo + 1;
					}
						//System.out.println(""+qtdInfoGrupo);
			}
		}
	}
	
	/**
	 * Método para gerar rodapé da aba
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public static void geraCorpoAbaCenariosOpcionais(XSSFSheet cenario,XSSFWorkbook excelGalderma,String nomeAba,String infoGrupo) throws FileNotFoundException, IOException{
	  cenario = excelGalderma.createSheet(nomeAba); /** Cria Aba Cenarios da planilha */
	  cenario.setZoom(80);
	  	ExcelImagem.InsereImagem(excelGalderma, cenario, "C:/SYSLOC/upload/logoEmpresas/logoExcelAgencia2.png",0.35);
		GeraCabecalhoExcelGalderma.geraCabecalho(cenario, excelGalderma, nomeAba);
		CorpoCenarioGalderma.corpoCenarioOpcionais(excelGalderma, cenario,infoGrupo,250,450, new BigDecimal("3596.00"), new BigDecimal("3596.00"));
		GeraTextoRodapeCenarios.geraTextoRodapeOpcionais(excelGalderma, cenario);
	}
}
