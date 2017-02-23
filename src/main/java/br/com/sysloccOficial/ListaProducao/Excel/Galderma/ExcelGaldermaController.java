package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.IOException;
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
	public String excelGalderma(Integer idLista) throws IOException{
		
		long tempoInicio = System.currentTimeMillis();
		
		String downloadExcel = excel.constroiExcel(idLista);

		System.out.println(downloadExcel);

		
//		String.format( "%03d:%02d", System.currentTimeMillis()-tempoInicio / 3600000, ( System.currentTimeMillis()-tempoInicio / 60000 ) % 60 );
		
		System.out.println("Tempo Total: "+( System.currentTimeMillis()-tempoInicio / 60000 ) % 60 );
		
		
		return "redirect:index";
	}
	

}
