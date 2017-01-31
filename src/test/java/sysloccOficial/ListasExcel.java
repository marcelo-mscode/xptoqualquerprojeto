package sysloccOficial;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ListasExcel {
	
	
	public static void main(String[] args) throws IOException{
        TreeSet<String> temp_rxGroups = new TreeSet<String>();
        for (int i = 0; i < 302; i++) {
            temp_rxGroups.add("" + i);
        }
        String[] countryName = temp_rxGroups.toArray(new String[temp_rxGroups.size()]);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet realSheet = workbook.createSheet("realSheet");
     
        XSSFSheet hidden = workbook.createSheet("hidden");
      
        for (int i = 0, length= countryName.length; i < length; i++) {
            String name = countryName[i];
            XSSFRow row = hidden.createRow(i);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(name);
        }

        DataValidation dataValidation = null;
        DataValidationConstraint constraint = null;
        DataValidationHelper validationHelper = null;
        validationHelper=new XSSFDataValidationHelper(realSheet);
        CellRangeAddressList addressList = new  CellRangeAddressList(0,0,0,0);
        constraint =validationHelper.createFormulaListConstraint("hidden!$A$1:$A$" + countryName.length);
        dataValidation = validationHelper.createValidation(constraint, addressList);
        dataValidation.setSuppressDropDownArrow(true);
        workbook.setSheetHidden(1, true);
        realSheet.addValidationData(dataValidation);
        
        
        
        
        FileOutputStream stream = new FileOutputStream("c:\\sysCon\\test.xlsx");
        workbook.write(stream);
        stream.close();

    }
	
	

}
