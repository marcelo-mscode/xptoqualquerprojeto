package br.com.sysloccOficial.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.AnexoDAO;
import br.com.sysloccOficial.daos.AtuacaoDAO;
import br.com.sysloccOficial.daos.EstatusDAO;
import br.com.sysloccOficial.daos.JobDAO;
import br.com.sysloccOficial.daos.JobStatusDAO;
import br.com.sysloccOficial.daos.ListaDAO;
import br.com.sysloccOficial.model.DataTeste;
import br.com.sysloccOficial.model.Departamento;
import br.com.sysloccOficial.model.Usuario;


@Controller
@Transactional
public class TesteControllers {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired private FileSaver fileSaver;
	@Autowired private JobDAO jobDAO;
	@Autowired private AnexoDAO anexoDAO;
	@Autowired private Utilitaria util;
	@Autowired private JobStatusDAO statusDAO;	
	@Autowired private EstatusDAO listaEstatusDAO;	
	@Autowired private ListaDAO listaDAO;	
	@Autowired private AtuacaoDAO atuacaoDAO;
	
	
	ModelAndView MV = new ModelAndView();
	
	
	@RequestMapping("/anexo")
	public ModelAndView anexo(){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("testes/anexo");
		
		return MV;
	}
	

	
	@RequestMapping("/validar")
	public ModelAndView teste2(String nivel1,String nivel2){
		
	//	JOptionPane.showMessageDialog(null, "Nivel 1: "+nivel1);
	//	JOptionPane.showMessageDialog(null, "Nivel 2: "+nivel2);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("testes/salva");
		return mv;
	}
	
	@RequestMapping("/teste")
	public String salva(@Valid DataTeste data, BindingResult result){
		
		if (result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("data",data);
			return salva((DataTeste) model, result);
		}
		
		{
			return "testes/layout2";
		}
		
	
		
		
}	
	@RequestMapping("/jobStatus")	
	public ModelAndView testeTesta(){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("testes/salva");
		
		MV.addObject("status",statusDAO.mostraPorId(718));
		MV.addObject("ListadeEstatus",listaEstatusDAO.ListaTodosEstatus());
		
		return MV;
	}	
	
	@RequestMapping("/listajobStatus")
	public ModelAndView lista(){
		
		ModelAndView MV = new ModelAndView();
		MV.setViewName("testes/salva");
		MV.addObject("jobs", jobDAO.listaJobStatus());

		return MV;
	}
	
	@RequestMapping("/testesLayout")
	public ModelAndView testesLayout(){
		
	MV.setViewName("testes/novoLayout");
	MV.addObject("tags",atuacaoDAO.mostra());	
	
	
	return MV;
	}
	
	@RequestMapping("/tabelas")
	public ModelAndView tabelas(){
		MV.setViewName("testes/tabelas");
		
		
		return MV;
	}
	
	@RequestMapping("/novoMenu")
	public ModelAndView novoMenu(){
		MV.setViewName("testes/novoMenu");
		MV.addObject("categoria", listaDAO.listasPorIdLista(2));
		
		return MV;
	}
	
	
	
	
	@RequestMapping("/testeConsultas")
	public String teste(String teste){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("testes/data");
		
		
		Query query = manager.createQuery("select distinct(d) from Departamento d left join fetch  d.usuario");
		List<Departamento> lista = query.getResultList();
		
		System.out.println();
		for (Departamento departamento : lista) {
			System.out.println("Departamento: "+departamento.getDepartamento());
			Collection<Usuario> user = departamento.getUsuario();
			
			for (Usuario usuario : user) {
				System.out.println("Usuario: "+usuario.getNome());
			}
			
		}
		
		return "testes/data";
	}
	
		
	@RequestMapping("/testeId")
	public String testeId(String id){
		
		
		String[] retornoSplit = id.split(",");

		
	//	List<Integer> li = listaDAO.idListasPorIdLista(2);
		
		
		for( int i = 0; i < retornoSplit.length; i++){
		//	Integer ij = li.get(i);
		//	System.out.println("id categoria: " + ij + "posicão: " + ids);

			
			
			
			
			Integer ids = Integer.parseInt(retornoSplit[i]);
			listaDAO.mergeCategoria(ids, i, 2);
	   }
		
	return null;
	}

	
	
	
	
	
	
	
	
	
	
	
}
