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
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.controllerExcel.AuxExcelSQL;
import br.com.sysloccOficial.model.Grupo;



@Controller
public class ExcelGaldermaController {
	@Autowired private GeraBaseExcelGalderma excel;
	@PersistenceContext	private EntityManager manager;
	@Autowired private AuxCarregaGrupos montaGrupos;
	
	@RequestMapping("excelGalderma")
	public ModelAndView excelGalderma(Integer idLista) throws IOException, ParseException{
		
		try {
			long tempoInicio = System.currentTimeMillis();
			
			String downloadExcel = excel.constroiExcel(idLista);
			System.out.println(downloadExcel);
			
			System.out.println("Tempo Total: "+( System.currentTimeMillis()-tempoInicio));
			
			ModelAndView MV = new ModelAndView("producao/geraExcel/ExcelGalderma");
			MV.addObject("nomeArquivo", downloadExcel);
			
			return MV;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"ExcelGaldermaController: "+e);
			ModelAndView MV = new ModelAndView("producao/geraExcel/ExcelErro");
			MV.addObject("idLista", idLista);
			return MV;
		}

	}
	

}
