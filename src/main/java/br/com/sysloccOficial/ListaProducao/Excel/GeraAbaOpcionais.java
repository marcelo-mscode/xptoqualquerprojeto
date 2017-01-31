package br.com.sysloccOficial.ListaProducao.Excel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.BaseExcelBayer;


public class GeraAbaOpcionais extends GeraExcelNovoBayer{

	@Autowired private GeraExcelNovoBayerCabecalho cabecalho;
	
	public void geraAba(XSSFWorkbook novoExcel,XSSFSheet abaOpcionais){
		
		BaseExcelBayer baseOpcionais = new BaseExcelBayer();
		int[] cor = {220,230,241};
		
		XSSFRow linha1 = abaOpcionais.createRow(3); // cria linha 1
		XSSFCell cell1 = linha1.createCell(0);    // Cria celula
		
		for (int i = 0; i < 10; i++) {
			if(i == 0){
				baseOpcionais.criaLinhaECelulaTexto(cell1, linha1, "Opcionais", i, novoExcel, cor);
			}
			if(i > 0 && i <= 6){
				baseOpcionais.criaLinhaECelulaTexto(cell1, linha1, "", i, novoExcel, cor);
			}
			if(i>6){
				baseOpcionais.criaLinhaECelulaTexto(cell1, linha1, "Data", i, novoExcel, cor);
			}
		}
	}
}
