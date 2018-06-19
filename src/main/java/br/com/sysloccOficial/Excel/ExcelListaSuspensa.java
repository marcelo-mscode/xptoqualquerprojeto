package br.com.sysloccOficial.Excel;

import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;



@Component
public class ExcelListaSuspensa {
	
		
	public static void listaSuspensaFixa(XSSFSheet abaMaster,int linha,int primColum,int ultColum, String[] textos){
		
		XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(abaMaster);
		XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint)
		dvHelper.createExplicitListConstraint(textos);
		CellRangeAddressList addressList = new CellRangeAddressList(linha, linha, primColum, ultColum);
		XSSFDataValidation validation = (XSSFDataValidation)dvHelper.createValidation(
		dvConstraint, addressList);
		validation.setShowErrorBox(true);
		abaMaster.addValidationData(validation);
		
	}
	
	public static void listaSuspensa(XSSFSheet abaMaster,int linha){
		
		XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(abaMaster);
		XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint)
				dvHelper.createExplicitListConstraint(new String[]{"Terceiro", "Custo Interno", "Faturamento Direto","Contratado pela Bayer","Reembolso de despesas"});
		CellRangeAddressList addressList = new CellRangeAddressList(linha, linha, 1, 1);
		XSSFDataValidation validation = (XSSFDataValidation)dvHelper.createValidation(
				dvConstraint, addressList);
		validation.setShowErrorBox(true);
		abaMaster.addValidationData(validation);
		
	}
	
	
	
	
	
	
	
	
	
}
