package br.com.sysloccOficial.ListaProducao.Excel;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.controllerExcel.AuxExcelSQL;
import br.com.sysloccOficial.model.Grupo;


@Controller
public class ExcelNovoBayerController {
	
	
	@Autowired private GeraExcelNovoBayer tipoExcel;
	@Autowired private TesteExcel testeExcel;
	@Autowired private AuxExcelSQL sql;

	@RequestMapping("ExcelNovoBayer")
	public ModelAndView geraExcelEmpresas(Integer idLista) throws IOException, ParseException{
		
		ModelAndView MV = new ModelAndView("producao/geraExcel/novoExcel");
		String downloadExcel = tipoExcel.GeraListagem(idLista);
		MV.addObject("nomeArquivo", downloadExcel);
		System.out.println(downloadExcel);
		return MV;  
	}

}
