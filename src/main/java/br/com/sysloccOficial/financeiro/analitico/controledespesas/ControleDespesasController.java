package br.com.sysloccOficial.financeiro.analitico.controledespesas;

import java.util.List;
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
	
	@RequestMapping("salvaControleDespesas")
	public String salvaControleDespesas(ControleDespesas controleNovo){
		
		int idAnalitico = 123;
		
		System.out.println(controleNovo.getDataTransiente());
		
		try {
			controleServices.salvaNovoControleDespesas(controleNovo);
		} catch (Exception e) {
			System.out.println("");
		}
		
		
		return "redirect:controleDespesas?idAnalitico="+idAnalitico;
		
	}
	
	
	
	
	

}
