package sysloccOficial;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.model.Categoria;

public class AuxExcelMontaCategoria {
	
	@Autowired public static AuxExcelEstilos auxExcel;
	@PersistenceContext	private EntityManager manager;
	
	
	public XSSFRow teste(int i,XSSFSheet planilha, int categ, int fimLinha){
		
		XSSFRow rowCategoria;
		if(i == 0){
			rowCategoria = planilha.createRow(categ);
			// Mescla celulas para Categoria
			AuxExcelEstilos.mescla(planilha, categ, categ, 0, 12);
			return rowCategoria;
		}else{
			rowCategoria = planilha.createRow(fimLinha);
			// Mescla celulas para Categoria
			AuxExcelEstilos.mescla(planilha, fimLinha, fimLinha, 0, 12);
			return rowCategoria;
		}
		
		
	}
	
	
	public void montaCateg(XSSFWorkbook workbook, XSSFSheet planilha, Integer idLista){
		
		// Pega Lista de categorias pelo Id da Lista
		String consulta = "from Categoria where idLista = 1906 order by categoriaOrdem";
		Query q = manager.createQuery(consulta);
		
		List<Categoria> listaCategoria = q.getResultList();
		
		
		
		
		//Categoria
		// Criar categorias a partir da linha 9
		int categ = 9;
		int linha = 9;
	
		for(int i = 0;i < listaCategoria.size() ; i++){
			
			XSSFRow rowCategoria = teste(i, planilha, categ, categ);
			
			rowCategoria.setHeight((short)400);
			Cell cellCategoria = rowCategoria.createCell(0);
			cellCategoria.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 51, 204, 51));
			cellCategoria.setCellValue(listaCategoria.get(i).getCategoria());

			//Monta as linhas dessa categoria após a linha da Categoria Principal
				for(int j = 0;j <= 6; j++){
					linha = categ + 1;
					System.out.println("linha: "+ linha);
					XSSFRow rowLinha = planilha.createRow(linha);	
					Cell cellLinha = rowLinha.createCell(0);
					cellLinha.setCellStyle(AuxExcelEstilos.bordaAlinhamentoVerticaleCentral(workbook));
					cellLinha.setCellValue("Carreta");
					categ = categ + 1;
				}
		//		System.out.println("FimLinha do loop de linha: " + fimLinha);
			
			// Montar próxima categoria depois da última linha 	
			
			categ = categ + 1;	
			linha = linha + 1;
			
			
		}
		
		
		
		/*XSSFRow rowLinha = planilha.createRow(10);	
		Cell cellLinha = rowLinha.createCell(0);
		cellLinha.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellLinha.setCellValue("Carreta");

		Cell cellQuant = rowLinha.createCell(1);
		cellQuant.setCellValue(XSSFCell.CELL_TYPE_NUMERIC);
		cellQuant.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellQuant.setCellValue(1);

		Cell cellCustoUnit = rowLinha.createCell(2);
		cellCustoUnit.setCellValue(XSSFCell.CELL_TYPE_STRING);
		cellCustoUnit.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellCustoUnit.setCellValue("7.583,33");

		Cell cellDiarias = rowLinha.createCell(3);
		cellDiarias.setCellValue(XSSFCell.CELL_TYPE_NUMERIC);
		cellDiarias.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellDiarias.setCellValue(24);

		Cell cellCustosagencia = rowLinha.createCell(4);
		cellCustosagencia.setCellValue(XSSFCell.CELL_TYPE_STRING);
		cellCustosagencia.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellCustosagencia.setCellValue("");
		
		Cell cellSubContratado = rowLinha.createCell(5);
		cellSubContratado.setCellValue(XSSFCell.CELL_TYPE_STRING);
		cellSubContratado.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellSubContratado.setCellValue("");
		
		Cell cellFatDireto = rowLinha.createCell(6);
		cellFatDireto.setCellValue(XSSFCell.CELL_TYPE_STRING);
		cellFatDireto.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellFatDireto.setCellValue("182.000,00");
		
		Cell cellSubTotal = rowLinha.createCell(7);
		cellSubTotal.setCellValue(XSSFCell.CELL_TYPE_STRING);
		cellSubTotal.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellSubTotal.setCellValue("182.000,00");

		Cell cellFee = rowLinha.createCell(8);
		cellFee.setCellValue(XSSFCell.CELL_TYPE_STRING);
		cellFee.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellFee.setCellValue("20.020,00");
		
		Cell cellImposto = rowLinha.createCell(9);
		cellImposto.setCellValue(XSSFCell.CELL_TYPE_STRING);
		cellImposto.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellImposto.setCellValue("5.946,28");
		
		
		Cell cellTotal = rowLinha.createCell(10);
		cellTotal.setCellValue(XSSFCell.CELL_TYPE_STRING);
		cellTotal.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellTotal.setCellValue("207.966,28");
		
		Cell cellInformacoes = rowLinha.createCell(11);
		cellInformacoes.setCellValue(XSSFCell.CELL_TYPE_STRING);
		cellInformacoes.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleEsquerda(workbook));
		cellInformacoes.setCellValue("Locação de uma carreta com dois avanços por 60 dias, contendo: locação de unidade, adesivagem externa, 100 cadeiras de plastico branca, tela de projeção, projetor, gerador, logistica de viagem contemplando 6000 km. ");
		
		Cell cellNaoInclusos = rowLinha.createCell(12);
		cellNaoInclusos.setCellValue(XSSFCell.CELL_TYPE_STRING);
		cellNaoInclusos.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleCentral(workbook));
		cellNaoInclusos.setCellValue("Hospedagem e alimentação para 8 pax");*/

		
		
	// Fim das linhas	
	// ---------------------------------------------------------------------------------------- //	
	// Repete a Categoria
		/*XSSFRow rowRepeteCategoria = planilha.createRow(12);
		rowCategoria.setHeight((short)600);
		
		Cell cellRepeteCategoria = rowRepeteCategoria.createCell(0);
		cellRepeteCategoria.setCellValue(XSSFCell.CELL_TYPE_STRING);
		
		XSSFCellStyle categoriaFim = AuxExcel.bordaAlinhamentoVerticaleEsquerda(workbook);
		categoriaFim.setFont(AuxExcel.fontBold(workbook));
		
		cellRepeteCategoria.setCellStyle(AuxExcel.bordaAlinhamentoVerticaleEsquerda(workbook));
		cellRepeteCategoria.setCellValue("Dia de Campo - 23 eventos + Paulinia: ");
		cellRepeteCategoria.setCellStyle(categoriaFim);*/
		
	}
	
	
	
	
	

}
