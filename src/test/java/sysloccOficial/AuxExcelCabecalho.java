package sysloccOficial;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AuxExcelCabecalho {

	
	public void criaCabecalho(XSSFWorkbook workbook, XSSFSheet planilha){
		
		// Cria texto de inicio 
				List<String>inicio = inicio();
				int p = 1;
				for(int i=0;i< inicio.size();i++){
					XSSFRow row = planilha.createRow(p);
					Cell cellInicio1 = row.createCell(0);
					cellInicio1.setCellValue(inicio.get(i));
					p = p + 1;
				}
				
				// Cria texto "Tipo Faturamento"
				XSSFRow tipoFaturamento = planilha.createRow(7);
				Cell tipoFaturamentoCell = tipoFaturamento.createCell((short)4);
				tipoFaturamentoCell.setCellValue("Tipo Faturamento");
				tipoFaturamentoCell.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleCentral(workbook));
				// Mescla celulas para "Tipo Faturamento"
				AuxExcelEstilos.mescla(planilha, 7, 7, 4, 6);
				
				// Coloca Borda em volta de Tipo Faturamento
				Cell tipoFaturamentoCell5 = tipoFaturamento.createCell((short)7);
				XSSFCellStyle borda = workbook.createCellStyle();
				tipoFaturamentoCell5.setCellStyle(AuxExcelEstilos.esquerdaBorda(workbook, borda));
				AuxExcelEstilos.setaBordaTipoFaturamento(workbook, planilha);
		
				cabecalhoDaTabela(workbook,planilha);
		
	}
	
	public void cabecalhoDaTabela(XSSFWorkbook workbook, XSSFSheet planilha){
		
		XSSFRow row = planilha.createRow(8);
		row.setHeight((short)600);
		List<String> cab = cabecalhoPlanilha();
		for(int i =0;i < cab.size() ; i++){
			Cell cell = row.createCell(i);
			cell.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleCentral(workbook));
			cell.setCellValue(cab.get(i));
			
			if(i == 1 || i == 2 || i == 3) {
				cell.setCellStyle(AuxExcelEstilos.estiloBordaCordeFundo(workbook, 255, 230, 230));
			}
		}
		
	}
	
	
	
	
	public static List<String> inicio(){
		List<String> inicio = new ArrayList<String>();
		inicio.add("Bayer Cropscience");
		inicio.add("Evento: Lançamento ALION");
		inicio.add("Data a Definir");
		
		String planilha = "Planilha atualizada: 23.02.16 - LP002.1/ Requisitante: ";
		String requis = "Elizabeth Baptista/Vanessa Guida";
		
		inicio.add(planilha+requis); 
		return inicio;
	}
	
	public static List<String> cabecalhoPlanilha(){
		List<String> cab = new ArrayList<String>();
		cab.add("Linha");
		cab.add("Quant.");
		cab.add("Custo Unit.");
		cab.add("Diárias");
		cab.add("Custos Agência");
		cab.add("Sub Contratado");
		cab.add("Faturamento \r\n Direto (ND)");
		cab.add("Sub-Total");
		cab.add("Fee");
		cab.add("Impostos");
		cab.add("TOTAL");
		cab.add("Informações");
		cab.add("Não inclusos no custo");
		
		return cab;
	}
	
	
	
}
