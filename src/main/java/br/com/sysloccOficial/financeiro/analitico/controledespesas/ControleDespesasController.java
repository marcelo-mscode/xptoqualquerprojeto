package br.com.sysloccOficial.financeiro.analitico.controledespesas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.model.ControleDespesas;

@Controller
@Transactional
public class ControleDespesasController {
	
	@Autowired ControleDespesasService controleServices;
	@PersistenceContext	private EntityManager manager;
	
	
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
	public String salvaControleDespesas(ControleDespesas controleNovo) throws ParseException{
		
		int idAnalitico = 123;
		
		System.out.println(controleNovo.getDataTransiente());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date data = new java.sql.Date(format.parse(controleNovo.getDataTransiente() + " 00:00:00").getTime());
		controleNovo.setData(data);
		
		manager.merge(controleNovo);
		
		
		
/*		try {
			controleServices.salvaNovoControleDespesas(controleNovo);
		} catch (Exception e) {
			System.out.println("");
		}
*/		
		
		return "redirect:controleDespesas?idAnalitico="+idAnalitico;
		
	}
	
	
	
	
	

}
