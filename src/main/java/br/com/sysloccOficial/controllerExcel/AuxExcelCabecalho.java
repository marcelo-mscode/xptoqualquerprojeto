package br.com.sysloccOficial.controllerExcel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.LocalEvento;


@Repository
@Transactional
public class AuxExcelCabecalho {

	@PersistenceContext	private EntityManager manager;
	TextosStaticosExcel textos = new TextosStaticosExcel();
	
	public void criaCabecalho(XSSFWorkbook workbook, XSSFSheet planilha,Lista lista, List<LocalEvento> localEvento){
		
		// Cria texto de inicio 
				List<String>inicio = textos.inicio(lista, localEvento);
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
		List<String> cab = textos.cabecalhoPlanilha();
		for(int i =0;i < cab.size() ; i++){
			Cell cell = row.createCell(i);
			cell.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleCentral(workbook));
			cell.setCellValue(cab.get(i));
			
			if(i == 1 || i == 2 || i == 3) {
				cell.setCellStyle(AuxExcelEstilos.estiloBordaCordeFundo(workbook, 255, 230, 230));
			}
		}
		
	}
	
}
