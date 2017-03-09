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
		GeraConteudoCategoriasCenarios.geraConteudoCategorias(excelGalderma, cenario, linhaInfoGrupos,infoGrupo,diarias,quantidade,valorUnitInicial,vlorUnitNegociado);
	}
	
	public static void geraSubTotalCadaCategoria(XSSFWorkbook excelGalderma,XSSFSheet cenario,int primeiraLinhaGrupoCategoria,int ultimaLinhaConteudoCategoria, double txServico, double txservicoNegociado, double txIss,double txIssNegociado){
		
		GeraSubTotalCategorias.subTotalCategorias(excelGalderma, cenario,primeiraLinhaGrupoCategoria, ultimaLinhaConteudoCategoria);
		
		int subTotal = ultimaLinhaConteudoCategoria+1;
		int porcentagem = ultimaLinhaConteudoCategoria+2;
		
		CalculoSubTotalCategoriaTaxaServico.calculoSubTotalCategoriaTaxas(excelGalderma, cenario, ultimaLinhaConteudoCategoria+1,"Taxa de Serviço",txServico, txservicoNegociado,subTotal,porcentagem);
	    CalculoSubTotalCategoriaTaxaServico.calculoSubTotalCategoriaTaxas(excelGalderma, cenario, ultimaLinhaConteudoCategoria+2,"Taxa de ISS",txIss,txIssNegociado,subTotal,porcentagem+1);
		CalculoSubtotalServico.totalDeServico(excelGalderma,cenario, ultimaLinhaConteudoCategoria+3, subTotal,ultimaLinhaConteudoCategoria+3);
		
	}
	
}
