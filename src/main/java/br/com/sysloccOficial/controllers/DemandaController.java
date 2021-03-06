package br.com.sysloccOficial.controllers;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaMsg;
import br.com.sysloccOficial.daos.AnexoDAO;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.daos.InteracaoDAO;
import br.com.sysloccOficial.daos.InternoDAO;
import br.com.sysloccOficial.daos.JobDAO;
import br.com.sysloccOficial.daos.MensagensEmailsDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.Interno;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.Usuario;



@Controller
@Transactional
public class DemandaController {
	
	
	@Autowired private InternoDAO internoDAO;
	@Autowired private Utilitaria util;
	@Autowired private UtilitariaMsg msgEmail;
	@Autowired private AnexoDAO anexoDAO;
	@Autowired private JobDAO jobDAO;
	@Autowired private UsuarioDAO usuarioDAO;
	@Autowired private ContatoDAO contatoDAO;
	@Autowired private InteracaoDAO interacaoDAO;
	@Autowired private EmailController email;
	@Autowired private MensagensEmailsDAO mensagensEmails;
	
	

	
	ModelAndView MV = new ModelAndView();
	
	// ---------------------------------------------------- Métodos de Estratégia ---------------------------------------------------------------//	

	
	
	// ---------- Método para salvar Demanda -----------//	
		@RequestMapping("/CadDemanda")
		
		//TODO Alterei aqui
		
		public String CadEstrategia (Interno interno, RedirectAttributes rd){
			boolean mensagem = false;
			
			Calendar c = Calendar.getInstance();
			interno.setCriadoData(c);
			
			
			Usuario userCriou= util.retornaUsuarioLogado();
			Usuario userResponsavel= new Usuario();
			
			
		//	userCriou.setIdUsuario(40);
			interno.setCriadoPor(userCriou);
			
			userResponsavel.setIdUsuario(interno.getIdResponsavel());
			interno.setResponsavel(userResponsavel);
			
			String prazoConclusao = interno.getpConclusao()+ " "+interno.gethConclusao();
			
			
			/*if(interno.isNotificaResponsavel() == true){
				JOptionPane.showMessageDialog(null, "Aqui !");
				Calendar cal = Calendar.getInstance();
				interno.setNotificadoEm(cal);
				String emailInterno = usuarioDAO.SelectSingleEmail(interno.getIdResponsavel());
				email.singleEmail(emailInterno, "Nova Demanda", interno.getInternoDescricao());
			}*/
			
			
			
			
	    	
	       if(interno.getpConclusao() == null || interno.getpConclusao().isEmpty()
	    		 && interno.gethConclusao() == null || interno.gethConclusao().isEmpty()){
		    }
		   else{interno.setPrazoConclusao(util.formataDatasStringParaCalendar(prazoConclusao));}
		 	
	        /*if(interno.getIdInterno() == null){
	        	internoDAO.salva(interno);
	        }else{
	        	internoDAO.atualizaEstrategia(interno);
	        }*/
	        
	  	
	        if(interno.isNotificaResponsavel() == true){
				Calendar cal = Calendar.getInstance();
				interno.setNotificadoEm(cal);
				String emailInterno = usuarioDAO.SelectSingleEmail(interno.getIdResponsavel());
				String subject = mensagensEmails.MontaSubjectDemanda(interno.getJobId());
				String conteudo = "Título do Job: "+mensagensEmails.PegaTituloJob(interno.getJobId())
						         +"\n\nItem da Demanda: "+interno.getInternoDescricao()
					  	         +"\n\nPrazo Conclusão: "+prazoConclusao;
				
				mensagem = email.singleEmail(emailInterno, subject, conteudo);
			
	        
	        
		        ArrayList<String> e = new ArrayList<String>();
				
				if(mensagem == true){
					e.add("sucesso");
					e.add("Demanda Salva.<br/>"+msgEmail.getReponsavelNotificado());
					
					if(interno.getIdInterno() == null){
			        	internoDAO.salva(interno);
			       }else{
			        	internoDAO.atualizaEstrategia(interno);
			       }
				}else{
					
					if(interno.getIdInterno() == null){
			        	internoDAO.salva(interno);
			       }else{
			        	internoDAO.atualizaEstrategia(interno);
			       }
					e.add("erro");
					e.add(msgEmail.getErroEnviarEmail());
				}
	
				rd.addFlashAttribute("msg", e);
	        
	        }else{
	        	
	        	if(interno.getIdInterno() == null){
		        	internoDAO.salva(interno);
		       }else{
		        	internoDAO.atualizaEstrategia(interno);
		       }
	        	
	        	
	        	ArrayList<String> e = new ArrayList<String>();
	        	e.add("sucesso");
	        	e.add("Demanda Salva com sucesso.<br/>");
	        	rd.addFlashAttribute("msg", e);
	        }
	        
	        
			
	        return "redirect:editaJob?idJob=" + interno.getJobId()+ "&idEmpresa="
			+ interno.getIdEmp();
	        
		}
		
// -------------------- Método para Alterar Demanda --------------------------------- //
		
		@RequestMapping("/internoAlteraDemanda")
		public ModelAndView internoAlteraDemanda(String codInterno,Integer idEmpresa,Integer idJob){
			
			MV.setViewName("job/demanda/alteraDemanda");
			MV.addObject("demanda", internoDAO.mostraInternoAlterar(codInterno));
	        MV.addObject("AnexosinternoCriacao",anexoDAO.ListaAnexosEstrategias("internoCriacao", codInterno));
	        MV.addObject("contato",contatoDAO.mostraContato(idEmpresa));
	        MV.addObject("usuariosHabilitados",usuarioDAO.mostraHabilitados());
	        MV.addObject("interacao",interacaoDAO.listar(idJob,"internoCriacao"));
	        
			return MV;
		}
		
// -------------------- Método para Concluir Estratégia --------------------------------- //	

	    @RequestMapping("/concluirDemanda")
		public String concluiEstrategia(Integer codInterno,Integer outroId,Integer outraEmp){
			
			internoDAO.concluiEstrategia(codInterno);
			
			Job j = jobDAO.recebeObjeto(outroId);
			
			return "redirect:editaJob?idJob=" + j.getIdJob()+ "&idEmpresa=" + outraEmp;
				
	    }	

// -------------------- Método para Cancelar o Concluir Estratégia --------------------------------- //	

	    @RequestMapping("/cancelaConcluirDemanda")
		public String cancelaConcluirEstrategia(Integer codInterno,Integer outroId,Integer outraEmp){
			
			internoDAO.CancelaconcluiEstrategia(codInterno);
			Job j = jobDAO.recebeObjeto(outroId);
			
			return "redirect:editaJob?idJob=" + j.getIdJob()+ "&idEmpresa=" + outraEmp;
	    }

// -----------------------------------------------------------Fim Métodos Estratégias ------------------------------------------------------------- //    
	    

	
	
	
	
	
}
