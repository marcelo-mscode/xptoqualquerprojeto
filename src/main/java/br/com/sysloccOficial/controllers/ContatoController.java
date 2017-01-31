package br.com.sysloccOficial.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.model.Comunicador;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Empresa;



@Controller
@Transactional
public class ContatoController {
	
	@Autowired private ContatoDAO contatoDAO;
	@Autowired private EmpresaController empresaController;
	
	@PersistenceContext
	private EntityManager manager;
	
	
	ModelAndView MV = new ModelAndView();
	
	
	@RequestMapping("/salvaContato")
	public String salva(Contato c){
	
		contatoDAO.salva(c);
		
		return "redirect:infoempresa?id="+c.getIdEmp();
	}

	@RequestMapping("/salvaContatoAjax")
	public ModelAndView salvaContatoAjax(Integer idContatoTeste,Integer idEmp,String nome,String cargo,String telefone,String descricao,String email,boolean habilitado){

		
		Integer idContato;
		Contato contato = new Contato();
		contato.setContato(nome);
		contato.setCargo(cargo);
		
		contato.setHabilitado(habilitado);

		Empresa empresa = new Empresa();
		empresa.setIdEmpresa(idEmp);
		contato.setEmpresa(empresa);
		contato.setObs("testes");
		
		if(idContatoTeste == null){
            contatoDAO.salvaAjax(contato); // Persiste Contato
            idContato = contatoDAO.ultimoContatoAdicionado();
    		contato.setIdContato(idContato); // Pega último contato adicionado
		}else{
			idContato = idContatoTeste;
			contato.setIdContato(idContato); 
		}
        
		Comunicador comunicador = new Comunicador();
		
		if(telefone == "" || telefone == null){
		
			comunicador.setComunicador(email);
			
		}else{
		    comunicador.setComunicador(telefone);
		}
		
		
		
		
		comunicador.setComunicadorDesc(descricao);
		comunicador.setContato(contato);
		comunicador.setComunicadorOrigem("Contato");
		comunicador.isComunicadorEmail();
		comunicador.isComunicadorTelefone();
		
		manager.persist(comunicador);
		
		
		MV.setViewName("empresa/contatoCriadoAjax");
		MV.addObject("contatos", idContato);
		MV.addObject("comunicador", contatoDAO.buscaUltimoComunicador(idContato));
		
		/*return "redirect:infoempresa?id="+empresa.getIdEmpresa();*/
		return MV;
		
	}
	
	@RequestMapping("/apagaComunicador")
	public ModelAndView pagaComunicador(Integer idComunicador, Integer idContato){
		Comunicador c = manager.find(Comunicador.class, idComunicador);
		manager.remove(c);
		
		ModelAndView mvc = new  ModelAndView();
		
		mvc.setViewName("empresa/contatoCriadoAjax");
		
		mvc.addObject("contatos", idContato);
		mvc.addObject("comunicador", contatoDAO.buscaUltimoComunicador(idContato));
		
		return mvc;
		
	}
	
	@RequestMapping("/editaComunicador")
	public ModelAndView editaComunicador(Integer idComunicador,
			String comunicador, Integer idContato) {

		Comunicador c = manager.find(Comunicador.class, idComunicador);
		c.setComunicador(comunicador);

		manager.merge(c);

		ModelAndView mvc = new ModelAndView();

		mvc.setViewName("empresa/contatoCriadoAjax");

		mvc.addObject("contatos", idContato);
		mvc.addObject("comunicador",
				contatoDAO.buscaUltimoComunicador(idContato));

		return mvc;

	}	

	@RequestMapping("/editaContato")
	public ModelAndView editaContato(Integer OutroidEmpNovo,
			boolean novoHabilitado, Integer idContato, String OutroContatoNovo,
			String OutrocargoNovo, String novaObs) throws InterruptedException {

		Empresa empresa = manager.find(Empresa.class, OutroidEmpNovo);
		Contato contato = new Contato();
		contato.setEmpresa(empresa);
		
		
		contato.setHabilitado(novoHabilitado);
		contato.setIdContato(idContato);
		contato.setContato(OutroContatoNovo);
		contato.setCargo(OutrocargoNovo);
		contato.setObs(novaObs);

		manager.merge(contato);

		MV.setViewName("job/jobContatos");
		MV.addObject("contato", contatoDAO.mostraContato(idContato));

		new Thread();
		Thread.sleep(500);

		return MV;
	}
	
	
}
