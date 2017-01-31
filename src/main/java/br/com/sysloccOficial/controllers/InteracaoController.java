package br.com.sysloccOficial.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.InteracaoDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Interacao;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.Usuario;



@Controller
@Transactional
public class InteracaoController {
	
	@Autowired private InteracaoDAO interacaoDAO;
	@Autowired private Utilitaria util;
	@Autowired private JobController jobController;
	@Autowired private EmailController email;
	@Autowired private UsuarioDAO usuarioDAO;
	@Autowired private EstrategiaController estrategiaController;
	
	
	@RequestMapping("/CadInteracao")
	public String CadInteracao(Interacao interacao){
		
		List<String> OutrosEmailsInternos = new ArrayList<String>();
		
		
		Calendar c = Calendar.getInstance();
		interacao.setDataInteracao(c);

		Contato contato = new Contato();
		contato.setIdContato(interacao.getIdContatoContato());
		interacao.setIdContato(contato);

		Usuario usuario = new Usuario();
		Usuario usuario1 = new Usuario();

		usuario.setIdUsuario(interacao.getIdUsuarioUsuario());
		interacao.setIdUsuario(usuario);

		if (interacao.isInterno() == true) {
			usuario1.setIdUsuario(interacao.getIdContatoContato());
			interacao.setIdUsuarioInterno(usuario1);
		}

		Job j = new Job();
		j.setIdJob(interacao.getIdJobJob());
		interacao.setIdOrigem(j);

		String proxInteracao = interacao.getPd() + " " + interacao.getPh();

		if (interacao.getPd() == null || interacao.getPd().isEmpty()
				&& interacao.getPh() == null || interacao.getPh().isEmpty()) {

		} else {

			interacao.setDataProximaInteracao(util
					.formataDatasStringParaCalendar(proxInteracao));
		}

		
		
		comunicarOutrosPorEmail(interacao);

		
		interacaoDAO.save(interacao);
		
		if(interacao.getInteracaoOrigem().equals("internoEstr")){
			
			
			return "redirect:internoAlteraEstrategia?codInterno=" + interacao.getCodInterno()+ "&idEmpresa=" + interacao.getIdEmp()
					+"&idJob="+interacao.getIdJobJob();	
		
		}else if(interacao.getInteracaoOrigem().equals("job")){
 		   
			return "redirect:editaJob?idJob=" + interacao.getIdJobJob()+ "&idEmpresa=" + interacao.getIdEmp();	
		
		}else if(interacao.getInteracaoOrigem().equals("internoCriacao")){
			
			return "redirect:internoAlteraDemanda?codInterno=" + interacao.getCodInterno()+ "&idEmpresa=" + interacao.getIdEmp()
					+"&idJob="+interacao.getIdJobJob();	
			
		}else {
			
			return "redirect:editaJob?idJob=" + interacao.getIdJobJob()+ "&idEmpresa=" + interacao.getIdEmp();
		
		}
		
		
		
				
	}


	private void comunicarOutrosPorEmail(Interacao interacao) {
		List<String> OutrosEmailsInternos;
		if(interacao.getMultiplo() != null){
			String subject = "Interação do Job: "+interacao.getTituloJob();
			String text = interacao.getInteracao();
			
			OutrosEmailsInternos = usuarioDAO.someEmailUser(interacao.getMultiplo());
			email.severalEmail(OutrosEmailsInternos, subject, text);
		}
	}
	
}






























