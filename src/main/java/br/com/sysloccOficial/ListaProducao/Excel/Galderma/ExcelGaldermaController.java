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
		
		
		
//		List<Grupo> listaGrupos  = montaGrupos.listaGruposNAOOpcionais(2424);
		
		
/*		List<Grupo> listaGrupos = new ArrayList<Grupo>();
		
		try {
			
			String consulta = "from Grupo where idLista = 2424 and opcional = 0 and categoriaGalderma > 1 order by ordemGrupo";
			
			Query q  = manager.createQuery(consulta, Grupo.class);
			
			listaGrupos = q.getResultList();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, ""+e);
		}
*/		
		
		
		
		
		String downloadExcel = excel.constroiExcel(idLista);
		
		
		System.out.println(downloadExcel);
		
		
		
		return "redirect:index";
		
	}
	

}
