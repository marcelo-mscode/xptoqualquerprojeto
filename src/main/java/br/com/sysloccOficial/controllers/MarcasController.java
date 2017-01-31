package br.com.sysloccOficial.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.MarcaDAO;
import br.com.sysloccOficial.model.Marca;
import br.com.sysloccOficial.model.Usuario;



@Controller
@Transactional
public class MarcasController {
	
	@Autowired
	private MarcaDAO marcaDAO;
	
	
	@RequestMapping("/marcas")
	public ModelAndView salva(Usuario usuario){
	
		ModelAndView mv = new ModelAndView("marcas/marcas");
		
		mv.addObject("marcas",marcaDAO.mostra());
		
		return mv;
	}
	
	@RequestMapping("/salvaMarca")
	public String salvaMarca(Marca m){
	
		marcaDAO.salvaMarca(m);
	
	return "redirect:marcas";
	}
	
	@RequestMapping("/editaMarca")
	public String editaMarca(Marca m){
		marcaDAO.editaMarca(m);
	return "redirect:marcas";	
	}
	
}
