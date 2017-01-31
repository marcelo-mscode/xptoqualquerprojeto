package br.com.sysloccOficial.empresa.excel;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.model.Contato;


@Controller
public class EmpresaExcelController {

	@Autowired 	private EmpresaDAO empresaDAO;
	@Autowired 	private GeraExcelListagem geraExcel;
	
	
	
	@RequestMapping("excelEmpresas")
	public ModelAndView excelEmpresas(){
		ModelAndView MV = new ModelAndView("empresa/excelListagem/excelListagem");
	
		List<Contato> listaEmpresas = empresaDAO.listaEmpresasParaExcel();
		
		MV.addObject("listaContatos",listaEmpresas);
		
	return MV;
	
	}
	

	@RequestMapping("geraExcelEmpresas")
	public ModelAndView geraExcelEmpresas() throws IOException{
		ModelAndView MV = new ModelAndView("empresa/excelListagem/geraExcelEmpresas");
		
		String downloadExcel = geraExcel.GeraListagem();
		
		MV.addObject("nomeArquivo", downloadExcel);
		
		return MV;
	}
	
	
	
}
