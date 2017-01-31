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
public class EstrategiaController {
	
	
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

	
	
	// ---------- Método para salvar Estratégia ou Demanda -----------//	
	@RequestMapping("/salvaEstrategia")
		public String CadEstrategia (Interno interno, RedirectAttributes rd){
		//TODO Alterei aqui	
		boolean mensagem = false;
		
			String msg ="";
			
			Calendar c = Calendar.getInstance();
			interno.setCriadoData(c);
			
			
			Usuario userCriou= util.retornaUsuarioLogado();
			Usuario userResponsavel= new Usuario();
			
			interno.setCriadoPor(userCriou);
			
			userResponsavel.setIdUsuario(interno.getIdResponsavel());
			interno.setResponsavel(userResponsavel);
			
			String prazoConclusao = interno.getpConclusao()+ " "+interno.gethConclusao();
			
			
			if(interno.isNotificaResponsavel() == true){
				
				Calendar cal = Calendar.getInstance();
				interno.setNotificadoEm(cal);
				String emailInterno = usuarioDAO.SelectSingleEmail(interno.getIdResponsavel());
				String subject = mensagensEmails.MontaSubjectEstrategia(interno.getJobId());
				
				String conteudo = "Título do Job: "+mensagensEmails.PegaTituloJob(interno.getJobId())
						         +"\n\nTítulo da Estratégia: "+interno.getInternoTitulo()
						         +"\n\nItem da Estratégia: "+interno.getInternoDescricao()
						         +"\n\nPrazo Conclusão: "+prazoConclusao;
				
				mensagem = email.singleEmail(emailInterno, subject, conteudo);
				
				ArrayList<String> e = new ArrayList<String>();
				
				if(mensagem == true){
					e.add("sucesso");
					e.add(msgEmail.getReponsavelNotificado());
					
					if(interno.getIdInterno() == null){
			        	internoDAO.salva(interno);
			       }else{
			        	internoDAO.atualizaEstrategia(interno);
			       }
				}
				else{
					
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
				e.add("Estratégia salva com sucesso !");
				rd.addFlashAttribute("msg", e);
			}
			
	    	
	       if(interno.getpConclusao() == null || interno.getpConclusao().isEmpty()
	    		 && interno.gethConclusao() == null || interno.gethConclusao().isEmpty()){
		   }
		   else{interno.setPrazoConclusao(util.formataDatasStringParaCalendar(prazoConclusao));}
		 	
	        return "redirect:editaJob?idJob=" + interno.getJobId()+ "&idEmpresa="
			+ interno.getIdEmp();
			
			
			
		}
		
	// -------------------- Método para Alterar Estratégia --------------------------------- //
		
		
		@RequestMapping("/internoAlteraEstrategia")
		public ModelAndView internoAlteraEstrategia(String codInterno,Integer idEmpresa,Integer idJob){
			

			
			MV.setViewName("job/estrategia/alteraEstrategia");

			MV.addObject("estrategia", internoDAO.mostraInternoAlterar(codInterno));
			
			MV.addObject("AnexosInternoEstr",anexoDAO.ListaAnexosEstrategias("internoEstr", codInterno));
			
			MV.addObject("contato",contatoDAO.mostraContato(idEmpresa));
			
			MV.addObject("usuariosHabilitados",usuarioDAO.mostraHabilitados());

			MV.addObject("interacao",interacaoDAO.listar(idJob,"internoEstr"));
			
			//MV.addObject("interacao",interacaoDAO.listar(1, "internoEstr"));
			
			return MV;
		}
		
	// -------------------- Método para Concluir Estratégia --------------------------------- //	

	    @RequestMapping("/concluirEstrategia")
		public String concluiEstrategia(Integer codInterno,Integer outroId,Integer outraEmp){
			
			internoDAO.concluiEstrategia(codInterno);
			
			Job j = jobDAO.recebeObjeto(outroId);
			
			return "redirect:editaJob?idJob=" + j.getIdJob()+ "&idEmpresa=" + outraEmp;
				
	}	

	// -------------------- Método para Cancelar o Concluir Estratégia --------------------------------- //	
		
	    @RequestMapping("/cancelaConcluirEstrategia")
		public String cancelaConcluirEstrategia(Integer codInterno,Integer outroId,Integer outraEmp){
			
			internoDAO.CancelaconcluiEstrategia(codInterno);
			Job j = jobDAO.recebeObjeto(outroId);
			
			return "redirect:editaJob?idJob=" + j.getIdJob()+ "&idEmpresa=" + outraEmp;
	}

// ------------------------------ Fim Métodos Estratégias ---------------------------------------------- //    
	    

	
	
	
	
	
}
