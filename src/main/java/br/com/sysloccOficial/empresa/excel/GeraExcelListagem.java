package br.com.sysloccOficial.empresa.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.empresas.services.MontaQueryCliente;
import br.com.sysloccOficial.model.Contato;

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
		
		//List<Contato> listaEmpresas = empresaDAO.listaEmpresasParaExcel();
		
		
	
		String tipoConsulta = MontaQueryCliente.montaQueryCliente(tipoDeListagem);
		
		List<Object[]> listagem = empresaDAO.listaEmpresaTeste(tipoConsulta);
		List<Object[]> listaContatos =  empresaDAO.listaContatos(listagem);
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
			
			for (int i = 0; i < listagem.size(); i++) {
				
					XSSFRow linhaNova;
					linhaNova = spreadSheet.createRow(numLinha);
					Cell empresaNova = linhaNova.createCell(0);
					Cell NomeContatoNova = linhaNova.createCell(1);
					
					//Nome da Empresa
					String nomeEmpresa = (String) listagem.get(i)[1];
					empresaNova.setCellValue(nomeEmpresa);
					//Nome Contato
					
					for (int j = 0; j < listaContatos.size(); j++) {
						
						int idEmpresa = (int)listagem.get(i)[0];
						int idContato = (int)listaContatos.get(i)[2];
						
						if(idEmpresa == idContato){
							System.out.println(idEmpresa);
							System.out.println(idContato);
//							NomeContatoNova.setCellValue((String)listaContatos.get(i)[1]);
						}
					}
					 
					
					/*String[] tipo = new String[listaEmpresas.get(i).getComunicador().size()];
					
					
					
					
					for (int j = 0; j < listaEmpresas.get(i).getComunicador().size(); j++) {
						tipo[j] = listaEmpresas.get(i).getComunicador().get(j).getComunicador();
					}
*/					
/*					int celula = 2;	
					
					for (int j = 0; j < tipo.length; j++) {
						Cell comunicador = linhaNova.createCell(celula);
						comunicador.setCellValue(tipo[j]);
						celula++;
					}
*/					numLinha++;
				
				
			}
			workbook.write(out);
			out.close();
			return downloadExcel;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
