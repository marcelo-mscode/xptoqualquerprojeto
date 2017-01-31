package sysloccOficial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.sysloccOficial.ListaProducao.Excel.GeraExcelNovoBayer;

public class NovoExcelBayer {

	
	
	public static void main(String[] args) throws IOException {
		
		
		// Cria as saidas do arquivo	
		/*XSSFWorkbook workbook = new XSSFWorkbook();
		FileOutputStream out = new FileOutputStream(new File ("E:/Teste/novoExcel.xlsx"));
		XSSFSheet planilha = workbook.createSheet("Aba 1"); // Nome da aba
*/		
		
		String nome = "C:/sysCon/teste";
		
		LinkedDropDownLists lista = new LinkedDropDownLists(nome);
		
		
		
		
		
	}
	
	
	
	
	
}
