package br.com.sysloccOficial.controllerExcel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.LocalEvento;

@Transactional
public class TextosStaticosExcel {

	
	@Autowired AuxExcelEstilos excelEstilos;
	@PersistenceContext	private EntityManager manager;
	
	
	public List<String> inicio(Lista lista, List<LocalEvento> localEvento){
		
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();		
		
		DateFormat formataData = DateFormat.getDateInstance();
		System.out.println("Data atual com formatação: "+ formataData.format(data));
		
		
		List<String> inicio = new ArrayList<String>();
		inicio.add(lista.getIdJob().getEmpresa().getEmpresa());
		inicio.add("Evento: "+ lista.getIdJob().getTitulo());
		
		inicio.add(trabalhaData(localEvento));

		String requis = lista.getIdJob().getContato().getContato();
		
		String[] lp = lista.getListaCod().split("-");
		
		String planilha = "Planilha atualizada: "+formataData.format(data)+	" - "+lp[1]+"."+lista.getRevisao()+"/ Requisitante: "+requis;
		
		
		inicio.add(planilha); 
		return inicio;
	}
	
	public List<String> cabecalhoPlanilha(){
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
	
	/**
	 * Método usado para criar células em branco de uma linha
	 * 
	 * @param i
	 * @param rowSubCategoria
	 * @param workbook
	 */
	public void celulasEmBranco(int comeca,int termina, XSSFRow rowSubCategoria, XSSFWorkbook workbook){
		for(int i = comeca;i < termina; i++){
			Cell cellSub1 = rowSubCategoria.createCell(i);
			cellSub1.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, 255, 255, 255));
		}
	}
	

	/**
	 * Método usado para criar células com cor
	 * 
	 * @param i
	 * @param rowSubCategoria
	 * @param workbook
	 */
	public void celulasColoridas(int comeca,int termina, XSSFRow rowSubCategoria, XSSFWorkbook workbook, int[] cor){
		for(int i = comeca;i < termina; i++){
			Cell cellSub1 = rowSubCategoria.createCell(i);
			cellSub1.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook, cor[0], cor[1], cor[2]));
		}
	}
	
	
	/**
	 * Método que trabalha a data do Evento vindo de Job
	 * 
	 * 
	 */
	public String trabalhaData(List<LocalEvento> localEvento){
			
		String datas="Data: ";

		String dataz = "dd/MM/yyyy HH:mm";
        SimpleDateFormat formatas = new SimpleDateFormat(dataz );
		
        
        if(localEvento.isEmpty()){
        	datas = datas + "a definir";
        }else{
        	int tamLista = localEvento.size();
			if(tamLista == 1 ){
				for (int i =0; i< localEvento.size();i++) {
					Date valorDataInicio = localEvento.get(i).getLocalEventoDataInicio();
					Date valorDataTermino = localEvento.get(i).getLocalEventoDataTermino();
					datas = verificaDatas(datas, formatas, valorDataInicio, valorDataTermino);
				}
			}else{
				
				for (int i = 0; i < localEvento.size();i++) {
				
					if(i == 0){
						Date valorDataInicio = localEvento.get(i).getLocalEventoDataInicio();
						datas = datas + formatas.format(valorDataInicio)+" ";
					}else if( i == tamLista-1){
						Date valorDataTermino = localEvento.get(i).getLocalEventoDataTermino();
						datas = datas + " - "+ formatas.format(valorDataTermino);
					}
				}
			}
        }
	   return datas;
	}

	private String verificaDatas(String datas, SimpleDateFormat formatas, Date valorDataInicio, Date valorDataTermino) {
		if(valorDataInicio == null && valorDataTermino != null){
			datas = datas + "Data de Inicio do evento não informada no Job - "+ formatas.format(valorDataTermino);
		}
		
		if(valorDataTermino == null && valorDataInicio != null){
			datas = datas + formatas.format(valorDataInicio)+" - Data de termino do evento não informada no Job";
		}
		
		if(valorDataInicio != null && valorDataTermino != null){
			datas = datas + formatas.format(valorDataInicio)+" - "+ formatas.format(valorDataTermino)+" ";
		}

		if(valorDataInicio == null && valorDataTermino == null){
			datas = "Data de inicio e fim do evento não informadas no Job.";
		}
		return datas;
	}
	
	
	
	
	
	
	
	
	
	
	
}
