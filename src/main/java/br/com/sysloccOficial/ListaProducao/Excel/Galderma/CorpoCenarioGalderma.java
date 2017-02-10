package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.math.BigDecimal;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;


@Component
public class CorpoCenarioGalderma {
	
	public static void cabecalhoCenario(){
		
	}
		
	public static void corpoCenario(XSSFWorkbook excelGalderma,XSSFSheet cenario,int linhaInfoGrupos,CorpoGrupoCategoriaGalderma gruposParaExcel){
		
		corpoPlanilhaCenario(excelGalderma, cenario, linhaInfoGrupos ,gruposParaExcel.getInfoGrupo(),gruposParaExcel.getDiaria(),gruposParaExcel.getQuantidade(),gruposParaExcel.getPrecoItem(),gruposParaExcel.getOrcamento());
	}	
	
	public static void corpoCenarioOpcionais(XSSFWorkbook excelGalderma,XSSFSheet cenario,String infoGrupo,int diarias, int quantidade,BigDecimal valorUnitInicial,BigDecimal vlorUnitNegociado){
		corpoPlanilhaCenario(excelGalderma, cenario, 21,infoGrupo,diarias,quantidade,valorUnitInicial,vlorUnitNegociado);
	}	
	
	public static void corpoPlanilhaCenario(XSSFWorkbook excelGalderma,XSSFSheet cenario,int linhaInfoGrupos ,String infoGrupo,double diarias, double quantidade,BigDecimal valorUnitInicial,BigDecimal vlorUnitNegociado){
		
		
		
		/**
		 * Aqui vai as quantidades e valores
		 */
		int ultimaLinhaConteudoCategoria =  GeraConteudoCategoriasCenarios.geraConteudoCategorias(excelGalderma, cenario, linhaInfoGrupos,infoGrupo,diarias,quantidade,valorUnitInicial,vlorUnitNegociado);
		
		
		/*GeraSubTotalCategorias.subTotalCategorias(excelGalderma, cenario, ultimaLinhaConteudoCategoria);
		CalculoSubTotalCategoriaTaxaServico.calculoSubTotalCategoriaTaxas(excelGalderma, cenario, ultimaLinhaConteudoCategoria+1,"Taxa de Serviço",0,0,ultimaLinhaConteudoCategoria+2,ultimaLinhaConteudoCategoria+3);
		CalculoSubTotalCategoriaTaxaServico.calculoSubTotalCategoriaTaxas(excelGalderma, cenario, ultimaLinhaConteudoCategoria+2,"Taxa de ISS",5,5,ultimaLinhaConteudoCategoria+2,ultimaLinhaConteudoCategoria+4);
		CalculoSubtotalServico.totalDeServico(excelGalderma,cenario, ultimaLinhaConteudoCategoria+3, 0);	*/

		/*CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 28, "Subtotal Geral",new int[]{0,176,240});
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 29, "Investimento - Serviços Terceiros - PGTO VIA NOTA DE DÉBITO",new int[]{219,219,219});
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 30, "Investimento - Serviços Agência",new int[]{219,219,219});
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 31, "FEE Agência",new int[]{219,219,219},5.2);
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 32, "Impostos Emissão NF - Serviços Agência",new int[]{219,219,219},22.9);
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 33, "TOTAL PREVISTO",new int[]{0,176,240});*/
		
		
	}
	
	public static void geraSubTotalCadaCategoria(XSSFWorkbook excelGalderma,XSSFSheet cenario,int primeiraLinhaGrupoCategoria,int ultimaLinhaConteudoCategoria){
		
		GeraSubTotalCategorias.subTotalCategorias(excelGalderma, cenario,primeiraLinhaGrupoCategoria, ultimaLinhaConteudoCategoria);
		CalculoSubTotalCategoriaTaxaServico.calculoSubTotalCategoriaTaxas(excelGalderma, cenario, ultimaLinhaConteudoCategoria+1,"Taxa de Serviço",0,0,ultimaLinhaConteudoCategoria+2,ultimaLinhaConteudoCategoria+3);
		
		CalculoSubTotalCategoriaTaxaServico.calculoSubTotalCategoriaTaxas(excelGalderma, cenario, ultimaLinhaConteudoCategoria+2,"Taxa de ISS",5,5,ultimaLinhaConteudoCategoria,ultimaLinhaConteudoCategoria+4);
		/*CalculoSubtotalServico.totalDeServico(excelGalderma,cenario, ultimaLinhaConteudoCategoria+3, 0);*/
		
	}
	
	
	
}
