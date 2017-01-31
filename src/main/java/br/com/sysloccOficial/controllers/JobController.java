package br.com.sysloccOficial.controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaMsg;
import br.com.sysloccOficial.daos.AnexoDAO;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.EstatusDAO;
import br.com.sysloccOficial.daos.InteracaoDAO;
import br.com.sysloccOficial.daos.InternoDAO;
import br.com.sysloccOficial.daos.JobDAO;
import br.com.sysloccOficial.daos.JobListaJobDAO;
import br.com.sysloccOficial.daos.JobStatusDAO;
import br.com.sysloccOficial.daos.LocalEventoDAO;
import br.com.sysloccOficial.daos.MensagensEmailsDAO;
import br.com.sysloccOficial.daos.NotificaEquipeDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.Anexos;
import br.com.sysloccOficial.model.Estatus;
import br.com.sysloccOficial.model.Interno;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.JobConsulta;
import br.com.sysloccOficial.model.JobStatus;
import br.com.sysloccOficial.model.NotificaEquipe;
import br.com.sysloccOficial.model.Usuario;



@Controller
@Transactional
public class JobController {
	
	@Autowired private JobDAO jobDAO;
	@Autowired private EmpresaDAO empresaDAO;
	@Autowired private ContatoDAO contatoDAO;
	@Autowired private LocalEventoDAO localeventoDAO;
	@Autowired private Utilitaria util;
	@Autowired private UtilitariaMsg utilMsg;
	@Autowired private InternoDAO internoDAO;
	@Autowired private UsuarioDAO usuarioDAO;
	@Autowired private FileSaver fileSaver;
	@Autowired private AnexoDAO anexoDAO;
	@Autowired private InteracaoDAO interacaoDAO;
	@Autowired private NotificaEquipeDAO notificaDAO;
	@Autowired private EmailController email;
	@Autowired private MensagensEmailsDAO mensagensEmails;	
	@Autowired private EstatusDAO statusDAO;	
	@Autowired private JobStatusDAO jobEstatusDAO;	
	@Autowired private EstatusDAO estatusDAO;
	@Autowired private JobStatusDAO jobStatusDAO;
	@Autowired private JobListaJobDAO jobListaDAO;
	@Autowired private NotificacaoController notifica;
	
	
	
	@PersistenceContext
	private EntityManager manager;
	
	ModelAndView MV = new ModelAndView();
	
	@RequestMapping("/job")
	public ModelAndView jobs() {
		MV.setViewName("job/novojob");
		MV.addObject("empresas", empresaDAO.mostraEmpresaJob());
	return MV;
	}
	
	
    //Action que retorna por Ajax as referencias do contato por id
	@RequestMapping("/buscaContato")
	public ModelAndView buscaContato(Integer id) throws InterruptedException{
		MV.setViewName("job/jobContatos");
		MV.addObject("contato", contatoDAO.mostraContato(id));
		new Thread();
		Thread.sleep(500);  
		return MV;
	}
	

// --------------------------- Cadastro de Novo Job ---------------------------------- //	
	@RequestMapping(value="/cadjob", method=RequestMethod.POST)
//	@CacheEvict(value="listaJob",allEntries=true)
	public String CadastraJob(Job j, String verba1, RedirectAttributes rd) {
		
	
		
		String prazoProposta = j.getPd() + " " + j.getPh();

		String propostaApresentada = j.getDataProposta() + " " + j.getApresHoraProposta();

		if (j.getPd() == null || j.getPd().isEmpty() && j.getPh() == null || j.getPh().isEmpty()) {
		
		}else {
			j.setPropostaData(util.formataDatasStringParaCalendar(prazoProposta));
		}

		if (j.getDataProposta() == null || j.getDataProposta().isEmpty()) {
		
		} else {
			j.setApresPropostaData(util.formataDatasStringParaCalendar(propostaApresentada));
		}

		if (verba1 == null || verba1.isEmpty()) {
			verba1 = "0";
			BigDecimal v = new BigDecimal(util.formataValores(verba1));
			j.setVerba(v);
		} else {
			BigDecimal v = new BigDecimal(util.formataValores(verba1));
			j.setVerba(v);
		}

		if (j.getIdJobEditar() == null) {
			
			Integer codJobNovo = util.novoCodJob();
			j.setCodJob(codJobNovo);
			
			j.setCriadoEm(Calendar.getInstance());
			jobDAO.salva(j);
			
			Integer ultimoIdJob = j.getIdJob();
			
			cadastraStatusNovoJob(ultimoIdJob,j.getIdEmp());

		} else {

			j.setIdJob(j.getIdJobEditar());
			j.setCodJob(j.getCodJobTransient());
			
			Job jobDataCriada = jobDAO.dataCriadoEm(j.getIdJobEditar()).get(0);
			j.setCriadoEm(jobDataCriada.getCriadoEm());
			
			
			
			jobDAO.atualiza(j);
		}
		
		
		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());

		return "redirect:editaJob?titulo=" + j.getTitulo() + "&idEmpresa="
				+ j.getIdEmp() + "&idContato=" + j.getIdContato() + "&idJob="
				+ j.getIdJob();
	}
	
	
	
// --------------- Método usado para preencher a tela de job ------------ //	

	@RequestMapping(value="/editaJob",method=RequestMethod.GET)
	public ModelAndView EditaJob(String titulo,Integer idEmpresa,Integer idJob) throws ParseException {

	    Job j = new Job();
	    j.setIdJob(idJob);
	    Number n = null;
	    
		MV.setViewName("job/job");
		
		//--- Usado na página principal ---//
		if (titulo == null || titulo.isEmpty()) {
			MV.addObject("job", jobDAO.mostraPorId(idJob));
		}
		//--------------------------------//
		
		
		//--- Usado quando o Job é editado ---//
		if (titulo != null || idJob != null ) {
			MV.addObject("job", jobDAO.mostraPorId(idJob));
		}
		//--------------------------------//
		
		
		//---- Usado quando é criado um novo job ----//
		else{
			MV.addObject("job", jobDAO.mostraPorNome(titulo));
		}
		//--------------------------------//
		
		MV.addObject("contato",contatoDAO.mostraContato(idEmpresa));
		MV.addObject("localEvento",localeventoDAO.mostra(idJob));
		
		MV.addObject("estrategia",internoDAO.mostra(idJob,"internoEstr"));
	    MV.addObject("novaEstrategia",internoDAO.mostraJob(idJob));
	    MV.addObject("demanda",internoDAO.mostra(idJob,"internoCriacao"));
	    
	    if(internoDAO.mostraJob(idJob).isEmpty()){
	    	
	    }else
	        {
			    Interno element0 = internoDAO.mostraJob(idJob).get(0);
			    MV.addObject("codInterno", util.splitDI(element0.getCodInterno()));
	    }
	    
	    MV.addObject("usuariosHabilitados",usuarioDAO.mostraHabilitados());
	
	    MV.addObject("anexosJob",anexoDAO.ListaAnexosJob("job", j.getIdJob()));
	   
	    /*MV.addObject("AnexosInternoEstr",anexoDAO.ListaAnexosJob("internoEstr"));*/

	    n = anexoDAO.VerificaAnexoJob(j,"job");
    	Integer codigoDoAnexo = n.intValue();
 	    MV.addObject("qtdAnexos", codigoDoAnexo);
		
 	    
 	   n = internoDAO.ContaEstrategiaPorJob(j,"internoEstr");
   	   Integer TotalEstrategias = n.intValue();
 	   MV.addObject("qtdEstrategias", TotalEstrategias);
 	    
 	   
 	   n = internoDAO.ContaEstrategiaPorJob(j,"internoCriacao");
 	   Integer TotalDemandas = n.intValue();
	   MV.addObject("qtdDemandas", TotalDemandas);
	   
	   
	   n = internoDAO.QtdJobsInterno(j.getIdJob());
  	   Integer TotalDeJobsPorInternos = n.intValue();
	   MV.addObject("qtdDeJobs", TotalDeJobsPorInternos);
	   
       MV.addObject("interacao",interacaoDAO.listar(j.getIdJob(),"job"));
       MV.addObject("notifica",notificaDAO.mostraPorNome(j));
      
       MV.addObject("estatus",jobEstatusDAO.mostraPorId(idJob));
	   MV.addObject("status",statusDAO.ListaTodosEstatus());
	   
	   
	   Date dataProposta = jobStatusDAO.data(idJob); // Pega a data para converter em Joda
	   
	   List<Integer> dataPrazo = util.FormataDataJoda(dataProposta);
	   
	   
	   MV.addObject("prazoDia", dataPrazo.get(2));
	   MV.addObject("prazoHora", dataPrazo.get(1));
	   MV.addObject("prazoMinuto", dataPrazo.get(0));
	   
	   
	   
     //  JOptionPane.showMessageDialog(null, ""+j.getCodJob());
       
	   return MV;
	}

// --------------------- Método para listar Job na Página principal -------------------------- //	

	@RequestMapping("/listajob")
//	@Cacheable(value="listaJob")
	public ModelAndView lista() throws ParseException{
		//TODO testes 
		MV.setViewName("job/listajobs");
		
		List<Integer> idJobsNovos = jobListaDAO.listaTeste();
		
		List<JobConsulta> listaContatos = jobListaDAO.lista(idJobsNovos);
		
		MV.addObject("jobs", listaContatos);
		MV.addObject("empresas", empresaDAO.mostraEmpresaJob());
		MV.addObject("estatus", estatusDAO.ListaTodosEstatus()); 
		MV.addObject("teste", notifica.qtdPendencias());
		
		return MV;
	}
	
	
	@RequestMapping("/notificaEquipe")
	public String notificaEquipe(NotificaEquipe notifica,Integer idJob,String codJob,String jobTitulo,String descricao, RedirectAttributes rd){
		
	    Job job = manager.find(Job.class, notifica.getIdJobJob());
		
	    String cliente = job.getEmpresa().getEmpresa();
	    
	    
		boolean mensagem = false;
		
		
		Calendar c = Calendar.getInstance();
		notifica.setEnviadoEm(c);
		
		List<String> OutrosEmailsInternos = new ArrayList<String>();
		
		
		
		if(notifica.isMultiploNotifica() != false){
			
			String subject = "Notificação de novo Job: "+jobTitulo;
			String text = "Novo Job: "+jobTitulo+
					      "\nCódigo do Job: "+codJob+
					      "\nCliente: "+cliente+
						  "\nDescrição: "+descricao;
			
			OutrosEmailsInternos = usuarioDAO.AllEmailUsers();
		
			mensagem = email.severalEmail(OutrosEmailsInternos, subject, text);
        }
		
		
		
		ArrayList<String> e = new ArrayList<String>();
		
		if(mensagem == true){
			e.add("sucesso");
			e.add("Equipe notificada com sucesso!");
			notificaDAO.salva(notifica);
		}else{
			e.add("erro");
			e.add("Houve um erro ao tentar enviar o email.<br /> Verifique os endereços de emails dos destinatários e tente novamente.");
			manager.detach(notifica);
		}

		rd.addFlashAttribute("msg", e);
		
		
		
		return "redirect:editaJob?idJob=" + notifica.getIdJobJob()+ "&idEmpresa=" + notifica.getIdEmp();	
	}
	
	@RequestMapping("/caminhoPastas")
	public ModelAndView caminhoPasta(String nomeDepartamento, Integer idJob){
		
		String caminhoPastas = Integer.toString(jobDAO.pegaCodigoJob(idJob))+"/"+nomeDepartamento;
	    		MV.setViewName("job/anexo/pastas");
	    		MV.addObject("pastas",fileSaver.VerificaPastas(caminhoPastas));   
		return MV;
	}
	
	
// ----------------- Salvar Anexo ---------------------//	
	@RequestMapping("/recebeAnexo")
	public String save(MultipartFile NomeAnexo,Anexos a, BindingResult result){
		
		Calendar c = Calendar.getInstance();
		Number n = null;
		Integer codigoDoAnexo = null;
		String CodJob= "";
	    
		Usuario user = util.retornaUsuarioLogado();
		//user.setIdUsuario(a.getIdCriadoPor());
		a.setCriadopor(user);
		
		Job job = new Job();
		job.setIdJob(a.getIdJob());
		a.setAnexoIdOrigem(job);
	        	
// ---------------------- Salvar as informações do Anexo ----------------------------------//
	    
    	n = anexoDAO.VerificaAnexo(a.getAnexoIdOrigem());
    	codigoDoAnexo = n.intValue();

// ---------------- Montando o prefixo do anexo --------------------------------- //
    	
    	CodJob = a.getCodInterno() + (util.PrefixoAnexo(codigoDoAnexo));
    	
    	a.setAnexoCod(CodJob);
    	a.setAnexoArquivo(CodJob+"_"+NomeAnexo.getOriginalFilename());
    	
// ----------------------------------------------------------------------------- //        
        
        a.setCriadoData(c);
        a.setAlteradoData(c);
    
        
        String codJob = a.getCodInterno();
        codJob = codJob+"/"+a.getAnexoarea()+"/"+a.getPastaIntermediaria().replace(" ", "");
        
        String webPath = fileSaver.uploadFile(codJob,NomeAnexo,CodJob+"_");
        
        if(webPath == "0"){
        	System.out.println("Arquivo Muito grande.");
        }

        else{
        	a.setAnexoDiretorio(webPath);
            jobDAO.saveAnexo(a);
        }


        jobDAO.limpaVirgula(); 
        
// -------------------------------------------------------------------------------------//	
        
        if(a.getAnexoOrigem().equals("internoCriacao")){
        	return "redirect:internoAlteraDemanda?codInterno="+a.getCodInterno()+"&idEmpresa="+a.getIdEmp()+"&idJob="+a.getIdJob();
        }
        else if (a.getAnexoOrigem().equals("internoEstr")){
        	
        	return "redirect:internoAlteraEstrategia?codInterno="+a.getCodInterno()+"&idEmpresa="+a.getIdEmp()+"&idJob="+a.getIdJob();
        }
        else{
        
        	return "redirect:editaJob?idJob=" + a.getIdJob()+ "&idEmpresa=" + a.getIdEmp();
        }
        
    }
	
	@RequestMapping("/apagarAnexo")
	public ModelAndView apagaAnexo(Integer idAnexo, Integer idJob){
		Anexos a = manager.find(Anexos.class, idAnexo);
		
		manager.remove(a);
		
		
		MV.setViewName("job/anexo/anexosAjax");
		MV.addObject("anexosJob",anexoDAO.ListaAnexosJob("job", idJob));
		
		return MV;
		
	}
	
	
	
	
	@RequestMapping("/cadastraStatus")
	public String cadastraStatus(JobStatus jobStatus, RedirectAttributes rd){
		Calendar c = Calendar.getInstance();
		jobStatus.setCriadoData(c);
		
		Usuario usuario1 = util.retornaUsuarioLogado();
	
		jobStatus.setCriadoPor(usuario1);
		
		Job job = manager.find(Job.class, jobStatus.getIdJobTransiente());
		jobStatus.setIdJob(job);
		
		Estatus estatus = manager.getReference(Estatus.class, jobStatus.getIdStatusTransiente());
		jobStatus.setIdStatus(estatus);
		
		// Usuario produtor1
 		Usuario produtor1 = manager.getReference(Usuario.class,jobStatus.getIdUsuarioTransiente());
		jobStatus.setIdUsuario(produtor1);

		// Usuario produtor2
		Usuario produtor2 = manager.getReference(Usuario.class,jobStatus.getIdProdutor2Transiente());
		jobStatus.setProdutor2(produtor2);
		
		String prazoProposta = jobStatus.getPrazoEstatusData() + " " + jobStatus.getPrazoEstatusHora();
		
		if (jobStatus.getPrazoEstatusData() == null || jobStatus.getPrazoEstatusData().isEmpty() && jobStatus.getPrazoEstatusHora() == null
				|| jobStatus.getPrazoEstatusHora().isEmpty()) {
		} else {
			jobStatus.setPrazoConclusao(util.formataDatasStringParaCalendar(prazoProposta));
		}

		manager.persist(jobStatus);	
		
		
		job.setIdStatusAtual(jobStatus);
		manager.merge(job);
		
		
		
		
		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		
		return "redirect:editaJob?idJob=" + jobStatus.getIdJobTransiente()+ "&idEmpresa=" + jobStatus.getIdEmpTransiente();
	}

	
	@RequestMapping("/cadastraStatusNovoJob")
	public String cadastraStatusNovoJob(Integer idJob,Integer idEmp){
		Job j = manager.find(Job.class, idJob);
		Calendar c = Calendar.getInstance();
		Usuario usuario1 = util.retornaUsuarioLogado();

		  JobStatus jobStatus = new JobStatus();
		
   		    jobStatus.setCriadoData(c);
			
			jobStatus.setCriadoPor(usuario1);
	
			jobStatus.setIdJob(j);
			
			
			
			Estatus estatus = manager.getReference(Estatus.class, 7);
			jobStatus.setIdStatus(estatus);
			
			jobStatus.setIdUsuario(usuario1);
			
			jobStatus.setJobStatusObservacao(" ");
					
			
			if (jobStatus.getPrazoEstatusData() == null || jobStatus.getPrazoEstatusData().isEmpty() && jobStatus.getPrazoEstatusHora() == null
					|| jobStatus.getPrazoEstatusHora().isEmpty()) {
				Date date = new Date();
				jobStatus.setPrazoConclusao(date);
			} else {
				
				Date date = new Date();
				jobStatus.setPrazoConclusao(date);
			}
	
	 		manager.persist(jobStatus);	
		
	 		j.setIdStatusAtual(jobStatus);
			manager.merge(j);	
			
		return "redirect:editaJob?idJob=" + idJob+ "&idEmpresa=" + idEmp;
	}

	
	
	
	

	
}

//  String webPath = fileSaver.uploadFile("1005010/Atendimento/briefing",NomeAnexo,CodJob+"_");
//  String webPath = fileSaver.uploadFile("/resources/upload",NomeAnexo,CodJob+"_");	



