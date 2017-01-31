package br.com.sysloccOficial.controllerBugs;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.controllers.EmailController;
import br.com.sysloccOficial.model.Bugs;
import br.com.sysloccOficial.model.Usuario;

@Controller
@Transactional
public class BugsController {
	
	@Autowired private Utilitaria util;   
	@Autowired private EmailController email;
	@PersistenceContext
	private EntityManager manager;
	
	ModelAndView MV = new ModelAndView();
	
	
	@RequestMapping("/reportarBug")
	public String reportarBug(String urlAtual, String urlAnterior,String tipoErro){
		
		
		
		String uAtual = urlAtual.replace("--s--", "&");
		String uAnterior = urlAnterior.replace("--s--", "&");
		
		Usuario usuarioEnviou = util.retornaUsuarioLogado();

		Bugs bugs = new Bugs();
		bugs.setUrlAtual(uAtual);
		bugs.setUrlAnterior(uAnterior);
		bugs.setCriadoEm(Calendar.getInstance());
		bugs.setEnviadoPor(usuarioEnviou);
		bugs.setTipoErro(tipoErro);
		bugs.acrescenta();
		
		manager.persist(bugs);
		
		
		// Envia email bug
		String subject = "URGENTE !!!! BUG REPORTADO.";
		
		String text = "Tipo: " +bugs.getTipoErro()
					+ "\nEnviado por: "+usuarioEnviou.getNome()
				    + "\nData: "+bugs.getCriadoEm().getTime()
				    + "\nUrl Anterior: "+ bugs.getUrlAnterior()
				    + "\nUrl Atual: "+ bugs.getUrlAtual();
		
		String to = "sisloc@loccoagencia.com.br";
		
		email.singleEmail(to, subject, text);
		
		return "Obrigado.";
	}
	
	@RequestMapping("/listaBugs")
	public ModelAndView listaBugs(){
		MV.setViewName("bugs/listaBugs");
		
		List<Bugs> bugsLista = manager.createQuery("SELECT b FROM Bugs b order by criadoEm").getResultList();
		
		MV.addObject("bugs", bugsLista);
		
		return MV;
	}

}
