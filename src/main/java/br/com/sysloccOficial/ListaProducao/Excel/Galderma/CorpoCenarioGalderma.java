package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;


@Component
public class CorpoCenarioGalderma {
	
	public static void cabecalhoCenario(){
		
	}
		
	public static void corpoCenario(XSSFWorkbook excelGalderma,XSSFSheet cenario,String nomeCategoria,CorpoGrupoCategoriaGalderma gruposParaExcel){
		GeraTextoCategorias.geratextoCategorias(excelGalderma, cenario, 20,nomeCategoria); // ok

		corpoPlanilhaCenario(excelGalderma, cenario,gruposParaExcel.getInfoGrupo());
	}	
	
	public static void corpoCenarioOpcionais(XSSFWorkbook excelGalderma,XSSFSheet cenario,String infoGrupo){
		corpoPlanilhaCenario(excelGalderma, cenario,infoGrupo);
	}	
	
	public static void corpoPlanilhaCenario(XSSFWorkbook excelGalderma,XSSFSheet cenario,String infoGrupo){
		
		//Não precisa mexer mais
		CorpoCenarioGaldermaTopo.geraTopoEstatico(excelGalderma, cenario, 17);
		
		int ultimaLinhaConteudoCategoria =  GeraConteudoCategoriasCenarios.geraConteudoCategorias(excelGalderma, cenario, 21,infoGrupo);
		GeraSubTotalCategorias.subTotalCategorias(excelGalderma, cenario, ultimaLinhaConteudoCategoria+1);
		CalculoSubTotalCategoriaTaxaServico.calculoSubTotalCategoriaTaxas(excelGalderma, cenario, ultimaLinhaConteudoCategoria+2,"Taxa de Serviço",0,0,ultimaLinhaConteudoCategoria+2,ultimaLinhaConteudoCategoria+3);
		CalculoSubTotalCategoriaTaxaServico.calculoSubTotalCategoriaTaxas(excelGalderma, cenario, ultimaLinhaConteudoCategoria+3,"Taxa de ISS",5,5,ultimaLinhaConteudoCategoria+2,ultimaLinhaConteudoCategoria+4);
		CalculoSubtotalServico.totalDeServico(excelGalderma,cenario, ultimaLinhaConteudoCategoria+4, 0);	

		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 24, "Subtotal Geral",new int[]{0,176,240});
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 25, "Investimento - Serviços Terceiros - PGTO VIA NOTA DE DÉBITO",new int[]{219,219,219});
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 26, "Investimento - Serviços Agência",new int[]{219,219,219});
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 27, "FEE Agência",new int[]{219,219,219},5.2);
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 28, "Impostos Emissão NF - Serviços Agência",new int[]{219,219,219},22.9);
		CalculoRodapeCenario.calculoRodapeCenario(excelGalderma, cenario, 29, "TOTAL PREVISTO",new int[]{0,176,240});
//		CalculoRodapeCenario.textoRodape(excelGalderma, cenario, 31, "Observações: *Check-in a partir das 12H00 - Checkout até 12H00\nOs valores apresentados são válidos exclusivamente para o grupo e período em referência\nForma de pagamento: Faturamento para 60 dias, exceto Hotel e Bar do Alemão 30 dias.");	
	}
	
	
	
}
