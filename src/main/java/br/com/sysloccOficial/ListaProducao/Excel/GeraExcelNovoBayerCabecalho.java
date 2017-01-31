package br.com.sysloccOficial.ListaProducao.Excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.BaseExcelBayer;
import br.com.sysloccOficial.model.Lista;

@Component
public class GeraExcelNovoBayerCabecalho {
	
		int[]corBranco = {255,255,255};
		int[]corOcreFeio = {238,236,225};
		int[]corAzulLeve = {220,230,241};
	
	public void geraCabecalho(XSSFSheet abaMaster, BaseExcelBayer base, XSSFWorkbook novoExcel,int posicaoCabecalho){
		
		abaMaster.setColumnWidth(0, 14000);
		abaMaster.setColumnWidth(1, 6500);
		abaMaster.setColumnWidth(2, 2200);
		abaMaster.setColumnWidth(3, 4000);
		abaMaster.setColumnWidth(4, 4000);
		abaMaster.setColumnWidth(5, 5000);
		abaMaster.setColumnWidth(6, 4000);
		abaMaster.setColumnWidth(7, 4000);
		abaMaster.setColumnWidth(8, 6000);
		abaMaster.setColumnWidth(9, 6000);
		abaMaster.setColumnWidth(10, 6000);
		abaMaster.setColumnWidth(11, 6000);
		
		
		XSSFRow linha = abaMaster.createRow(posicaoCabecalho); // cria linha 1
		XSSFCell cell = linha.createCell(0);    // Cria celula
		base.criaLinhaECelulaTexto(cell, linha, "Descritivo por Sessão", 0,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Serviço Subcontratado, Custo\nInterno, faturamento direto ou\ncontratado pela Bayer.", 1,novoExcel,corOcreFeio);
		
		base.criaLinhaECelulaTexto(cell, linha, "Qtde.", 2,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Diárias", 3,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Valor Unitário\ninicial", 4,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Valor Unitário\nNegociado", 5,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Sub-Total", 6,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Saving por item", 7,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Comentários Agência", 8,novoExcel,corAzulLeve);
		base.criaLinhaECelulaTexto(cell, linha, "Comentários Bayer /\nAprovação", 9,novoExcel,corAzulLeve);
		base.criaLinhaECelulaTexto(cell, linha, "Réplica Agência", 10,novoExcel,corAzulLeve);
		base.criaLinhaECelulaTexto(cell, linha, "Tréplica Bayer", 11,novoExcel,corAzulLeve);
		linha.setHeightInPoints(40);
		
	}

	public void geraCabecalhoOpcionais(XSSFSheet abaMaster, BaseExcelBayer base, XSSFWorkbook novoExcel,int posicaoCabecalho,Lista infoLista){
		
		abaMaster.setColumnWidth(0, 14000);
		abaMaster.setColumnWidth(1, 6500);
		abaMaster.setColumnWidth(2, 4000);
		abaMaster.setColumnWidth(3, 4000);
		abaMaster.setColumnWidth(4, 4000);
		abaMaster.setColumnWidth(5, 4000);
		abaMaster.setColumnWidth(6, 6000);
		abaMaster.setColumnWidth(7, 6000);
		abaMaster.setColumnWidth(8, 6000);
		abaMaster.setColumnWidth(9, 6000);
		
		
		
		
		XSSFRow linha0 = abaMaster.createRow(0); // cria linha 1
		XSSFCell cell0 = linha0.createCell(0); 
		base.celulaTextoSemFormatacao(cell0, linha0, "Evento: "+infoLista.getLista(), 0,novoExcel,corBranco);
		XSSFCell cell1 = linha0.createCell(1); 
		base.celulaTextoSemFormatacao(cell1, linha0, infoLista.getListaCod()+"."+infoLista.getRevisao(), 1,novoExcel,corBranco);
		
		XSSFRow linha = abaMaster.createRow(posicaoCabecalho); // cria linha 1
		XSSFCell cell = linha.createCell(0);    // Cria celula
		base.criaLinhaECelulaTexto(cell, linha, "Descritivo", 0,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Serviço Subcontratado, Custo\nInterno ou faturamento direto.", 1,novoExcel,corOcreFeio);
		
		base.criaLinhaECelulaTexto(cell, linha, "Qtde.", 2,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Diárias", 3,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Valor Unitário", 4,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Sub-Total", 5,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Comentários Agência", 6,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Comentários Bayer /\nAprovação", 7,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Réplica Agência", 8,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Tréplica Bayer", 9,novoExcel,corOcreFeio);
		linha.setHeightInPoints(28);
		
	}

	
	public void geraCabecalhoLogistica(XSSFSheet abaLogisticaStaff, BaseExcelBayer base, XSSFWorkbook novoExcel,int posicaoCabecalho,Lista infoLista,XSSFSheet abaMaster){
		
		abaLogisticaStaff.setColumnWidth(0, 10000);
		abaLogisticaStaff.setColumnWidth(1, 6000);
		abaLogisticaStaff.setColumnWidth(2, 6000);
		abaLogisticaStaff.setColumnWidth(3, 6000);
		abaLogisticaStaff.setColumnWidth(4, 6000);
		abaLogisticaStaff.setColumnWidth(5, 6000);
		abaLogisticaStaff.setColumnWidth(6, 6000);
		abaLogisticaStaff.setColumnWidth(7, 6000);
		abaLogisticaStaff.setColumnWidth(8, 6000);
		abaLogisticaStaff.setColumnWidth(9, 6000);
		
		
		
		
		XSSFRow linha0 = abaMaster.createRow(0); // cria linha 1
		XSSFCell cell0 = linha0.createCell(0); 
		base.celulaTextoSemFormatacao(cell0, linha0, infoLista.getLista(), 0,novoExcel,corBranco);
		XSSFCell cell1 = linha0.createCell(1); 
		base.celulaTextoSemFormatacao(cell1, linha0, infoLista.getListaCod()+"."+infoLista.getRevisao(), 1,novoExcel,corBranco);
		
		
		XSSFRow linha = abaLogisticaStaff.createRow(posicaoCabecalho); // cria linha 1
		XSSFCell cell = linha.createCell(0);    // Cria celula
		base.criaLinhaECelulaTexto(cell, linha, "Cargo/Função", 0,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Empresa", 1,novoExcel,corOcreFeio);
		
		base.criaLinhaECelulaTexto(cell, linha, "Nome", 2,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "CPF", 3,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "RG", 4,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Quantidade de\nrefeições", 5,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Entrada no hotel", 6,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Hospedagem", 7,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Saída do hotel", 8,novoExcel,corOcreFeio);
		base.criaLinhaECelulaTexto(cell, linha, "Diárias", 9,novoExcel,corOcreFeio);
		linha.setHeightInPoints(25);
	}
	
	
	
	
	
	
	
	
	
	
	

}
