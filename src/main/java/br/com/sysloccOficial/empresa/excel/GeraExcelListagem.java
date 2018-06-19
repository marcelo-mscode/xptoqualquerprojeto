package br.com.sysloccOficial.empresa.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.ListaProducao.calcula.grupos.dao.ListaProducaoGrupoDAO;
import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.empresas.services.MontaQueryCliente;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.ContatoInfoBasica;
import br.com.sysloccOficial.model.EmpresaInfoBasica;

@Component
public class GeraExcelListagem {
	
	
	@Autowired 	private EmpresaDAO empresaDAO;
	
	
	public String GeraListagem() throws IOException{
		
		List<Contato> listaEmpresas = empresaDAO.listaEmpresasParaExcel();
		
		// Topo da Planilha 	
		// ----------------------------------------- //	
		 	Calendar c = Calendar.getInstance(); 
			SimpleDateFormat s = new SimpleDateFormat("HHmmss"); 
			String a = s.format(c.getTime());
		 	
			// Cria excel em branco
			XSSFWorkbook workbook = new XSSFWorkbook();
			
			FileOutputStream out = new FileOutputStream(new File ("C:/SYSLOC/upload/excel/"+"listagemEmpresas"+a+".xlsx"));
			String downloadExcel = "upload/upload/excel/"+"listagemEmpresas"+a+".xlsx";
		
			XSSFSheet spreadSheet = workbook.createSheet("Listagem de Empresas");
			
			
			XSSFRow linha = spreadSheet.createRow((short)0);
			
			spreadSheet.setColumnWidth(0, 6500);
			spreadSheet.setColumnWidth(1, 8500);
			spreadSheet.setColumnWidth(2, 9000);
			spreadSheet.setColumnWidth(3, 9500);
			spreadSheet.setColumnWidth(4, 9500);
			
			Cell empresa = linha.createCell(0);
			Cell NomeContato = linha.createCell(1);
			Cell Contato = linha.createCell(2);
			
			empresa.setCellValue("Empresa");
			NomeContato.setCellValue("Nome Contato");
			Contato.setCellValue("Contato");
			
			int numLinha = 2;
			
			for (int i = 0; i < listaEmpresas.size(); i++) {
				
				if(listaEmpresas.get(i).getEmpresa().isHabilitado() != false && listaEmpresas.get(i).getEmpresa().isCliente() != false ){
					
					XSSFRow linhaNova;
					linhaNova = spreadSheet.createRow(numLinha);
					Cell empresaNova = linhaNova.createCell(0);
					Cell NomeContatoNova = linhaNova.createCell(1);
					
					empresaNova.setCellValue(listaEmpresas.get(i).getEmpresa().getEmpresa());
					NomeContatoNova.setCellValue(listaEmpresas.get(i).getContato());
					
					String[] tipo = new String[listaEmpresas.get(i).getComunicador().size()];
					
					for (int j = 0; j < listaEmpresas.get(i).getComunicador().size(); j++) {
						tipo[j] = listaEmpresas.get(i).getComunicador().get(j).getComunicador();
					}
					
					int celula = 2;	
					for (int j = 0; j < tipo.length; j++) {
						Cell comunicador = linhaNova.createCell(celula);
						comunicador.setCellValue(tipo[j]);
						celula++;
					}
					numLinha++;
				}
				
			}
			workbook.write(out);
			out.close();
			return downloadExcel;
	}
	
	
public String GeraListagemNova(Integer tipoDeListagem) throws IOException{
	
		String tipoConsulta = MontaQueryCliente.montaQueryCliente(tipoDeListagem);
		
		List<Object[]> listagemEmpresas = empresaDAO.listaEmpresaTeste(tipoConsulta);
		List<Object[]> listaContatos =  empresaDAO.listaContatos(listagemEmpresas);
		List<Object[]> listaComunicador =  empresaDAO.listaComunicador(listaContatos);
		
		// Topo da Planilha 	
		// ----------------------------------------- //	
		 	Calendar c = Calendar.getInstance(); 
			SimpleDateFormat s = new SimpleDateFormat("HHmmss"); 
			String a = s.format(c.getTime());
		 	
			// Cria excel em branco
			XSSFWorkbook workbook = new XSSFWorkbook();
			
			FileOutputStream out = new FileOutputStream(new File ("C:/SYSLOC/upload/excel/"+"listagemEmpresas"+a+".xlsx"));
			String downloadExcel = "upload/upload/excel/"+"listagemEmpresas"+a+".xlsx";
		
			XSSFSheet spreadSheet = workbook.createSheet("Listagem de Empresas");
			
			
			XSSFRow linha = spreadSheet.createRow((short)0);
			
			spreadSheet.setColumnWidth(0, 6500);
			spreadSheet.setColumnWidth(1, 8500);
			spreadSheet.setColumnWidth(2, 9000);
			spreadSheet.setColumnWidth(3, 9500);
			spreadSheet.setColumnWidth(4, 9500);
			
			Cell empresa = linha.createCell(0);
			Cell NomeContato = linha.createCell(1);
			Cell Contato = linha.createCell(2);
			
			empresa.setCellValue("Empresa");
			NomeContato.setCellValue("Nome Contato");
			Contato.setCellValue("Contato");
			
			int numLinha = 2;
			
			for (int i = 0; i < listagemEmpresas.size(); i++) {
				    
					int idEmpresa = (int)listagemEmpresas.get(i)[0];
				
					XSSFRow linhaNova; linhaNova = spreadSheet.createRow(numLinha);	Cell empresaNova = linhaNova.createCell(0);Cell NomeContatoNova = linhaNova.createCell(1);
					
					//Nome da Empresa
					String nomeEmpresa = (String) listagemEmpresas.get(i)[1];empresaNova.setCellValue(nomeEmpresa);
					
					//Nome Contato
					//int tamanhoComunicador = 0;
					for (int j = 0; j < listaContatos.size(); j++) {
						
						int idEmpresaEmContato = (int)listaContatos.get(j)[2];
						int idContato = (int)listaContatos.get(j)[0];
					
						if(idEmpresa == idEmpresaEmContato){
							String nomeContato = (String) listaContatos.get(j)[1];
							NomeContatoNova.setCellValue(nomeContato);
						}
						
						int celula = 2;
						
						for (int l = 0; l < listaComunicador.size(); l++) {
						  
						  if(listaComunicador.get(l)[0] != null ){
							  int idContatoEmComunicador = (int)listaComunicador.get(l)[1];
							  if(idContato == idContatoEmComunicador && idEmpresaEmContato == idEmpresa){
								Cell comunicador = linhaNova.createCell(celula);
								String comunic = (String)listaComunicador.get(l)[0];
								comunicador.setCellValue(comunic);
								celula++;
			  					
								listaComunicador.get(l)[0] = null ;
								listaComunicador.get(l)[1] = null ;
							}
						  }	
						}
					}

					numLinha++;
			}
			workbook.write(out);
			out.close();
			return downloadExcel;
	}

public String GeraListagemNovaRefatorada(Integer tipoDeListagem) throws IOException{
	
	String tipoConsulta = MontaQueryCliente.montaQueryCliente(tipoDeListagem);
	
	List<EmpresaInfoBasica> listagemEmpresas = empresaDAO.listaEmpresaTesteRefatorada(tipoConsulta);
	List<ContatoInfoBasica> listaContatos =  empresaDAO.listaContatosRefatorada(listagemEmpresas);
	List<Object[]> listaComunicador =  empresaDAO.listaComunicadorRefatorada(listaContatos);
	
	// Topo da Planilha 	
	// ----------------------------------------- //	
	Calendar c = Calendar.getInstance(); 
	SimpleDateFormat s = new SimpleDateFormat("HHmmss"); 
	String a = s.format(c.getTime());
	
	// Cria excel em branco
	XSSFWorkbook workbook = new XSSFWorkbook();
	
	FileOutputStream out = new FileOutputStream(new File ("C:/SYSLOC/upload/excel/"+"listagemEmpresas"+a+".xlsx"));
	String downloadExcel = "upload/upload/excel/"+"listagemEmpresas"+a+".xlsx";
	
	XSSFSheet spreadSheet = workbook.createSheet("Listagem de Empresas");
	
	
	XSSFRow linha = spreadSheet.createRow((short)0);
	
	spreadSheet.setColumnWidth(0, 6500);
	spreadSheet.setColumnWidth(1, 8500);
	spreadSheet.setColumnWidth(2, 9000);
	spreadSheet.setColumnWidth(3, 9500);
	spreadSheet.setColumnWidth(4, 9500);
	
	Cell empresa = linha.createCell(0);
	Cell NomeContato = linha.createCell(1);
	Cell Contato = linha.createCell(2);
	
	empresa.setCellValue("Empresa");
	NomeContato.setCellValue("Nome Contato");
	Contato.setCellValue("Contato");
	
	int numLinha = 2;

	for (int i = 0; i < listagemEmpresas.size(); i++) {
		
		int idEmpresa = listagemEmpresas.get(i).getIdEmpresa();

		for (int j = 0; j < listaContatos.size(); j++) {
			
			
			int idEmpresaEmContato = (int)listaContatos.get(j).getIdEmpresa();
			int idContato = (int)listaContatos.get(j).getIdContato();
			
			XSSFRow linhaNova; 
			linhaNova = spreadSheet.createRow(numLinha);	

			if(idEmpresa == idEmpresaEmContato){
				
				Cell empresaNova = linhaNova.createCell(0);
				Cell NomeContatoNova = linhaNova.createCell(1);
				
				//Nome da Empresa
				String nomeEmpresa = (String) listagemEmpresas.get(i).getEmpresa();
				empresaNova.setCellValue(nomeEmpresa);
				//Nome Contato
				String nomeContato = listaContatos.get(j).getContato();
				NomeContatoNova.setCellValue(nomeContato);
				numLinha++;
				
			}
			
			int celula = 2;
	
			List<Integer> listaComunicadorApagar = new ArrayList<Integer>();

			for (int l = 0; l < listaComunicador.size(); l++) {
					
					if(listaComunicador.get(l)[0] != null ){

					int idContatoEmComunicador = (int)listaComunicador.get(l)[1];
				
				    if(idContato == idContatoEmComunicador && idEmpresaEmContato == idEmpresa){
				    	Cell comunicador = linhaNova.createCell(celula);
						String comunic = (String)listaComunicador.get(l)[0];
						comunicador.setCellValue(comunic);
						celula++;
						listaComunicadorApagar.add(l);
			    }
			  }
			}
			for (int k = 0; k < listaComunicadorApagar.size(); k++) {
				int t = listaComunicadorApagar.get(k);
				listaComunicador.get(t)[0] = null; 
				listaComunicador.get(t)[1] = null; 
			}
		}
	}
	
	
	workbook.write(out);
	out.close();
	return downloadExcel;
}
	
	
	
	
	
	
	
	
	
	
	

}
