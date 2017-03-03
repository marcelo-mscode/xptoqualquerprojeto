package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.text.ParseException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.model.Job;

@Component
public class GeraCabecalhoExcelGalderma {
	
	@Autowired
	private static UtilitariaDatas utilData;
	
	public static void geraCabecalho(XSSFSheet aba,XSSFWorkbook excelGalderma,String nomeAba, Job job) throws ParseException{
	
		textoContato(aba, excelGalderma, "Gestor: A definir Paula Samora", 1);
		textoContato(aba, excelGalderma, "Telefone: 11 3524-6498", 2);
		textoContato(aba, excelGalderma, "Email: paula.samora@galderma.com", 3);
		
		textoContato(aba, excelGalderma, "Compras: Paula Samora", 4);
		textoContato(aba, excelGalderma, "Telefone: 11 3524-6498", 5);
		textoContato(aba, excelGalderma, "Email: paula.samora@galderma.com", 6);
		
		textoContato(aba, excelGalderma, "Fornecedor: A definir Paula Samora", 7);
		textoContato(aba, excelGalderma, "Telefone: 11 3524-6498", 8);
		textoContato(aba, excelGalderma, "Email: paula.samora@galderma.com", 9);
		
		geraCabecalhoEvento(aba, excelGalderma, nomeAba, job);
	}
	
	private static void textoContato(XSSFSheet aba,XSSFWorkbook excelGalderma,String texto,int posicaoLinha){
		
		XSSFRow linha = aba.createRow(posicaoLinha);
		linha.setHeightInPoints(20);
		
		XSSFCell celula1 = linha.createCell(2);
		XSSFCell celula2 = linha.createCell(3);
		XSSFCell celula3 = linha.createCell(4);
		celula1.setCellValue(texto);
		
		XSSFCellStyle estilo = EstilosGaldema.estiloCabecalhoContato(excelGalderma);
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		celula1.setCellStyle(estilo);
		celula2.setCellStyle(estilo);
		celula3.setCellStyle(estilo);
	}

	
	public static void geraCabecalhoEvento(XSSFSheet aba,XSSFWorkbook excelGalderma,String nomeAba, Job job) throws ParseException{
		textoEvento(aba,excelGalderma, "EVENTO: "+ job.getTitulo() ,12); 

		for (int i = 0; i < job.getLocalEvento().size(); i++) {
			String dataInicio =  UtilitariaDatas.converteDateParaStringStatic(job.getLocalEvento().get(i).getLocalEventoDataInicio());
			String dataFim =  UtilitariaDatas.converteDateParaStringStatic(job.getLocalEvento().get(i).getLocalEventoDataTermino());
			textoEvento(aba,excelGalderma, "DATA: "+dataInicio+" até " +dataFim,13); 
			textoEvento(aba,excelGalderma, "LOCAL: "+job.getLocalEvento().get(i).getLocal(),14); 
			textoEvento(aba,excelGalderma, "NÚMEROS DE PESSOAS/DIA: " +job.getLocalEvento().get(i).getLocalEventoQtdPessoas() ,15); 
		}
	}
	
	private static void textoEvento(XSSFSheet aba,XSSFWorkbook excelGalderma,String texto,int posicaoLinha){
		
		XSSFRow linha = aba.createRow(posicaoLinha);
		linha.setHeightInPoints(25);
		
		XSSFCell celula1 = linha.createCell(0);
		XSSFCell celula2 = linha.createCell(1);
		XSSFCell celula3 = linha.createCell(2);
		XSSFCell celula4 = linha.createCell(3);
		XSSFCell celula5 = linha.createCell(4);
		celula1.setCellValue(texto);
		
		XSSFCellStyle estilo = EstilosGaldema.estiloCabecalhoEvento(excelGalderma);
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		celula1.setCellStyle(estilo);
		celula2.setCellStyle(estilo);
		celula3.setCellStyle(estilo);
		celula4.setCellStyle(estilo);
		celula5.setCellStyle(estilo);
		
	}
	
}
