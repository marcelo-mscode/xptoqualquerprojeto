package br.com.sysloccOficial.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.AtuacaoDAO;
import br.com.sysloccOficial.model.Atuacao;



@Controller
@Transactional
public class TagController {
	
	@Autowired
	private AtuacaoDAO atuacaoDAO;
	
	
	@RequestMapping("/tag")
//	@Cacheable(value="tag")
	public ModelAndView tag() {
		ModelAndView mv = new ModelAndView("tags/tag");
		mv.addObject("tags",atuacaoDAO.mostra());
	return mv;
	}
	
	
	@RequestMapping("/informacaoTag")
	public ModelAndView usuarios(Integer id) throws InterruptedException{
		ModelAndView mv = new ModelAndView("tags/modalTags");
		mv.addObject("tags", atuacaoDAO.listaPorIdAtuacao(id));
		
		new Thread();Thread.sleep(100);  
		
		return mv;
	}
	
	@RequestMapping("/CarregaNovaTag")
	public ModelAndView NovaTag() throws InterruptedException{
		ModelAndView mv = new ModelAndView("tags/modalnovaTag");
		
		new Thread();Thread.sleep(100);  
		
		return mv;
	}
	
	@RequestMapping("/SalvaNovaTag")
//	@CacheEvict(value="tag", allEntries=true)
	public String SalvaNovaTag(Atuacao a){
		
		atuacaoDAO.salva(a);
		return "redirect:tag";
	}
	
	
	
	
	@RequestMapping("/atualizaTags")
	public String atualizaUsuario(Atuacao a){
		atuacaoDAO.atualizaTags(a);
		return "redirect:tag";
	}
	
	/*@RequestMapping("/removeTag")
	public ModelAndView removeUsuario(Integer id){
		System.out.println("Dentro de remove tag");
		
		atuacaoDAO.remove(id);
		return tag();
	}*/

		
}
