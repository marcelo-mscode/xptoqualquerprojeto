package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ExcelGaldermaController {
	@Autowired private GeraBaseExcelGalderma excel;
	
	
	@RequestMapping("excelGalderma")
	public String excelGalderma() throws IOException{
		
		
		String downloadExcel = excel.constroiExcel();
		
		
		System.out.println(downloadExcel);
		
		
		
		return "redirect:index";
		
	}
	

}
