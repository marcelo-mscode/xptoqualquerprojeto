package br.com.sysloccOficial.financeiro.analitico.controledespesas;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.model.ControleDespesas;

@Controller
public class ControleDespesasController {
	
	@Autowired ControleDespesasService controleServices;
	
	
	@RequestMapping("controleDespesas")
	public ModelAndView ControleDespesas(Integer idAnalitico){
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/controleDespesas/controleDespesas");
		
		try {
			List<ControleDespesas> lista = controleServices.listaControleDespesas();
			MV.addObject("lista", lista);
		} catch (NullPointerException e) {
			System.out.println("Informação ao buscar Listas: " + e.getMessage());
		}
		return MV;
	}
	
	

}
