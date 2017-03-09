package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sysloccOficial.controllerExcel.AuxExcelSQL;
import br.com.sysloccOficial.model.Grupo;



@Controller
public class ExcelGaldermaController {
	@Autowired private GeraBaseExcelGalderma excel;
	@PersistenceContext	private EntityManager manager;
	@Autowired private AuxCarregaGrupos montaGrupos;
	
	@RequestMapping("excelGalderma")
	public String excelGalderma(Integer idLista) throws IOException, ParseException{
		
		long tempoInicio = System.currentTimeMillis();
		
		String downloadExcel = excel.constroiExcel(idLista);
		System.out.println(downloadExcel);
		
		

		System.out.println("Tempo Total: "+( System.currentTimeMillis()-tempoInicio));

		return "redirect:editaLista?idLista="+idLista;
	}
	

}
