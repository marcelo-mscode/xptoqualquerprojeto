package br.com.sysloccOficial.Excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class BaseGeraExcel {

	private String caminhoNomeArquivo;
	private String complementoNomeArquivo;

	public BaseGeraExcel(){}
	public BaseGeraExcel(String caminhoNomeArquivo){
		this.caminhoNomeArquivo = caminhoNomeArquivo;
	}
	public String metodoComplementaNomeArquivo(){
		Calendar c = Calendar.getInstance(); 
		SimpleDateFormat s = new SimpleDateFormat("HHmmss"); 
		String complemento = s.format(c.getTime());
		complementoNomeArquivo = complemento;
		return complemento;
	}
	public FileOutputStream caminhoeNomeDoArquivo() throws FileNotFoundException{
		caminhoNomeArquivo = caminhoNomeArquivo+metodoComplementaNomeArquivo()+".xlsx";
		FileOutputStream out = new FileOutputStream(new File (caminhoNomeArquivo));
		return out;
	}
	public String caminhoDownloadExcel(String caminhoNomeArquivo,String caminho) throws FileNotFoundException{
		String downloadExcel = "upload/upload/excel/"+caminhoNomeArquivo+complementoNomeArquivo+".xlsx";
		return downloadExcel;
	}
	
	public void fechaPlanilha(XSSFWorkbook workbook, FileOutputStream out) throws IOException {
		workbook.write(out);
		out.close();
	}
	
}
