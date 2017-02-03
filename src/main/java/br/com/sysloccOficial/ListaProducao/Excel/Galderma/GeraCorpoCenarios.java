package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.FileNotFoundException;
import java.io.IOException;
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

		passaInformacoesCorpoExcel(cenario, excelGalderma, gruposParaExcel, categoriasGalderma);
		
		//Não mexer mais
		GeraTextoRodapeCenarios.geraTextoRodape(excelGalderma, cenario);
	}

	/**
	 * Método que passa as informações para gerar o corpo do
	 * Excel com as informações da planilha do cliente
	 */
	private static void passaInformacoesCorpoExcel(XSSFSheet cenario, XSSFWorkbook excelGalderma, List<CorpoGrupoCategoriaGalderma> gruposParaExcel,List<GrupoCategoriaGalderma> categoriasGalderma) {
		for (int i = 0; i < categoriasGalderma.size(); i++) {
			System.out.println(categoriasGalderma.get(i).getIdCategoriaGalderma());
				for (int j = 0; j < gruposParaExcel.size(); j++) {
					System.out.println(gruposParaExcel.get(j).getIdCategoriaGalderma());
					if(categoriasGalderma.get(i).getIdCategoriaGalderma() == gruposParaExcel.get(j).getIdCategoriaGalderma()){
						CorpoCenarioGalderma.corpoCenario(excelGalderma, cenario,categoriasGalderma.get(i).getCategoria(),
								gruposParaExcel.get(j));
					}
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
		CorpoCenarioGalderma.corpoCenarioOpcionais(excelGalderma, cenario,infoGrupo);
		GeraTextoRodapeCenarios.geraTextoRodapeOpcionais(excelGalderma, cenario);
	}
}
