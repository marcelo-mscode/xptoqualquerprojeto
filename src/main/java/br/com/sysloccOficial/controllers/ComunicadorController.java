package br.com.sysloccOficial.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.model.Comunicador;
import br.com.sysloccOficial.model.Contato;



@Controller
@Transactional
public class ComunicadorController {
	
	
	
	@Autowired private ContatoDAO contatoDAO;
	@Autowired private EmpresaController empresaController;
	
	@PersistenceContext
	private EntityManager manager;
	
	
	ModelAndView MV = new ModelAndView();
	
	
	@RequestMapping("/ListaComunicadores")
	public ModelAndView ListaComunicadorEmJob(Integer idContato){
	//	JOptionPane.showMessageDialog(null, "ComunicadorController: ListaComunicadores");
		
		MV.setViewName("empresa/editaComunicador");
		MV.addObject("contato",contatoDAO.PreeencheListaEdicaoContatoJob(idContato));
		return MV;
		
	}
	
	
	
	
	
	@RequestMapping("/salvaNovoComunicador")
	public ModelAndView SalvaComunicadorJob(Integer idContato,String comunicador,String comunicadorDesc,String telefone,String email){
		
		
		String comunicadorOrigem = "contato";	
		Contato contato = manager.find(Contato.class, idContato);
				
		Comunicador c = new Comunicador();
		c.setContato(contato);
		
		
		c.setComunicadorDesc(comunicadorDesc);
		c.setComunicadorOrigem(comunicadorOrigem);
		
		if(telefone == null || telefone == ""){
			c.setComunicador(email);
		}else{
		    c.setComunicador(telefone);
		}
		
		manager.persist(c);	

		MV.setViewName("empresa/contatoCriadoAjaxJob");
		//MV.addObject("contatos", idContato);
		MV.addObject("contato",contatoDAO.PreeencheListaEdicaoContatoJob(idContato));
		
		
		
		
		return MV;
		/*comunicadorTelefone ComunicadorEmail */
	}
	
	
	@RequestMapping("/apagaComunicadorEditar")
	public ModelAndView pagaComunicador(Integer idComunicador, Integer idContato){
	//	JOptionPane.showMessageDialog(null, "Apagando Comunicador Modal Editar"+idComunicador+"\n"+idContato);
		
		Comunicador c = manager.find(Comunicador.class, idComunicador);
		manager.remove(c);
		
		
		MV.setViewName("empresa/contatoCriadoAjaxJob");
		
	//	mvc.addObject("contatos", idContato);
		MV.addObject("contato",contatoDAO.PreeencheListaEdicaoContatoJob(idContato));
		
		return MV;
		
	}
	
	@RequestMapping("/editaCelulaComunicadorEditar")
	public ModelAndView editaComunicador(Integer idComunicador,	String comunicador, Integer idContato) {

		JOptionPane.showMessageDialog(null, "Editando Celula");
		
		Comunicador c = manager.find(Comunicador.class, idComunicador);
		c.setComunicador(comunicador);

		manager.merge(c);

		
		MV.setViewName("empresa/contatoCriadoAjaxJob");

//		MV.addObject("contatos", idContato);
		
		MV.addObject("contato",contatoDAO.PreeencheListaEdicaoContatoJob(idContato));

		return MV;

	}	
	
	
	
}
