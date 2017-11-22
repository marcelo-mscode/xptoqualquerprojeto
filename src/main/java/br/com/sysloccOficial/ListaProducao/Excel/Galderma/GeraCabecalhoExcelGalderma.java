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
		
		String nomeContato = job.getContato().getContato();
		String email = "";
		String telefone = "";
		
		for (int i = 0; i < job.getContato().getComunicador().size(); i++) {
			if(job.getContato().getComunicador().get(i).getComunicador().contains("@")){
				email = job.getContato().getComunicador().get(i).getComunicador();
			}else{
				telefone = job.getContato().getComunicador().get(i).getComunicador();
			}
		}
		
		textoContato(aba, excelGalderma, "Contato: "+nomeContato, 1);
		textoContato(aba, excelGalderma, "Telefone: "+telefone, 2);
		textoContato(aba, excelGalderma, "Email: "+email, 3);
		
		/*textoContato(aba, excelGalderma, "Compras: Paula Samora", 4);
		textoContato(aba, excelGalderma, "Telefone: 11 3524-6498", 5);
		textoContato(aba, excelGalderma, "Email: paula.samora@galderma.com", 6);*/
		
		textoContato(aba, excelGalderma, "Fornecedor: LOCCO Agencia", 4, new int[]{247, 255, 0});
		textoContato(aba, excelGalderma, "Telefone: 11 - 3938-3250", 5, new int[]{247, 255, 0});
		textoContato(aba, excelGalderma, "Email: celia@loccoagencia.com.br; pedro@loccoagencia.com.br", 6, new int[]{247, 255, 0});
		
		geraCabecalhoEvento(aba, excelGalderma, nomeAba, job);
	}
	
	public static void geraCabecalhoFormula(XSSFSheet aba,XSSFWorkbook excelGalderma,String nomeAba, Job job) throws ParseException{
		
		
		int[] corCinza = new int[]{242,242,242};
		int[] corAmarelo = new int[]{247, 255, 0};
		
		textoContatoFormula(aba, excelGalderma, "'Consolidado'!C2", 1, corCinza);
		textoContatoFormula(aba, excelGalderma, "'Consolidado'!C3", 2, corCinza);
		textoContatoFormula(aba, excelGalderma, "'Consolidado'!C4", 3, corCinza);
		
		textoContatoFormula(aba, excelGalderma, "'Consolidado'!C5", 4, corAmarelo);
		textoContatoFormula(aba, excelGalderma, "'Consolidado'!C6", 5, corAmarelo);
		textoContatoFormula(aba, excelGalderma, "'Consolidado'!C7", 6, corAmarelo);

		/*textoContatoFormula(aba, excelGalderma, "'Consolidado'!C8", 7, corAmarelo);
		textoContatoFormula(aba, excelGalderma, "'Consolidado'!C9", 8, corAmarelo);
		textoContatoFormula(aba, excelGalderma, "'Consolidado'!C10", 9, corAmarelo);*/
		
/*		
		
		textoContato(aba, excelGalderma, "Fornecedor: LOCCO Agencia", 7, new int[]{247, 255, 0});
		textoContato(aba, excelGalderma, "Telefone: 11 - 3938-3250", 8, new int[]{247, 255, 0});
		textoContato(aba, excelGalderma, "Email: celia@loccoagencia.com.br; pedro@loccoagencia.com.br", 9, new int[]{247, 255, 0});
*/		
		geraCabecalhoEvento(aba, excelGalderma, nomeAba, job);
	}
	
	private static void textoContatoFormula(XSSFSheet aba,XSSFWorkbook excelGalderma,String texto,int posicaoLinha,int[] cor){
		
		XSSFRow linha = aba.createRow(posicaoLinha);
		linha.setHeightInPoints(20);
		
		XSSFCell celula1 = linha.createCell(2);
		celula1.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		
		try {
			celula1.setCellFormula(texto);
			celula1.setCellValue(texto);
		} catch (Exception e) {
			System.out.println("Erro: "+e);
		}
		
		XSSFCell celula2 = linha.createCell(3);
		XSSFCell celula3 = linha.createCell(4);
		XSSFCellStyle estilo = EstilosGaldema.estiloCabecalhoContato(excelGalderma,cor);
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		celula1.setCellStyle(estilo);
		celula2.setCellStyle(estilo);
		celula3.setCellStyle(estilo);
	}
	
	
	private static void textoContato(XSSFSheet aba,XSSFWorkbook excelGalderma,String texto,int posicaoLinha){
		
		XSSFRow linha = aba.createRow(posicaoLinha);
		linha.setHeightInPoints(20);
		
		XSSFCell celula1 = linha.createCell(2);
		XSSFCell celula2 = linha.createCell(3);
		XSSFCell celula3 = linha.createCell(4);
		//celula1.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		celula1.setCellValue(texto);
		
		XSSFCellStyle estilo = EstilosGaldema.estiloCabecalhoContato(excelGalderma,new int[]{242,242,242});
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		celula1.setCellStyle(estilo);
		celula2.setCellStyle(estilo);
		celula3.setCellStyle(estilo);
	}

	private static void textoContato(XSSFSheet aba,XSSFWorkbook excelGalderma,String texto,int posicaoLinha,int[] cor){
		
		XSSFRow linha = aba.createRow(posicaoLinha);
		linha.setHeightInPoints(20);
		
		XSSFCell celula1 = linha.createCell(2);
		XSSFCell celula2 = linha.createCell(3);
		XSSFCell celula3 = linha.createCell(4);
		celula1.setCellValue(texto);
		
		XSSFCellStyle estilo = EstilosGaldema.estiloCabecalhoContato(excelGalderma,cor);
		estilo.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		celula1.setCellStyle(estilo);
		celula2.setCellStyle(estilo);
		celula3.setCellStyle(estilo);
	}

	
	public static void geraCabecalhoEvento(XSSFSheet aba,XSSFWorkbook excelGalderma,String nomeAba, Job job) throws ParseException{
		textoEvento(aba,excelGalderma, "EVENTO: "+ job.getTitulo() ,12); 
		
		String dataInicio = "Data não cadastrada";
		String dataFim = "Data não cadastrada";
		for (int i = 0; i < job.getLocalEvento().size(); i++) {
			
			if(job.getLocalEvento().get(i).getLocalEventoDataInicio() != null){
				dataInicio =  UtilitariaDatas.converteDateParaStringStatic(job.getLocalEvento().get(i).getLocalEventoDataInicio());
			}
			if(job.getLocalEvento().get(i).getLocalEventoDataTermino() != null){
				dataFim =  UtilitariaDatas.converteDateParaStringStatic(job.getLocalEvento().get(i).getLocalEventoDataTermino());
			}
			
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
