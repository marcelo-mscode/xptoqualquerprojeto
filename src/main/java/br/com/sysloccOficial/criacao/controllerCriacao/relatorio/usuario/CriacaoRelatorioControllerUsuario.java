package br.com.sysloccOficial.criacao.controllerCriacao.relatorio.usuario;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.criacao.controllerCriacao.dao.RelatorioDAO;
import br.com.sysloccOficial.criacao.controllerCriacao.dao.RelatorioUsuarioDAO;
import br.com.sysloccOficial.criacao.controllerCriacao.relatorio.apoio.ApoioRelatorio;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.CriacaoLista;
import br.com.sysloccOficial.model.Usuario;



@Controller
public class CriacaoRelatorioControllerUsuario extends ApoioRelatorio{

	@Autowired private RelatorioDAO relatorioDAO;
	@Autowired private RelatorioUsuarioDAO relatorioUsuarioDAO;
	@Autowired private UtilitariaDatas utilData;
	@Autowired private UsuarioDAO usuarioDAO;
	@Autowired private ApoioRelatorio apoioRelatorio;
	@Autowired private ApoioRelatorioItensUsuario apoioRelatorioUsuario;
	
	@PersistenceContext	private EntityManager manager;
		
	@RequestMapping("porUsuario")
	public ModelAndView porUsuario() throws ParseException{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("criacao/relatorio/porUsuario/porUsuario");
		MV.addObject("listaUsuarios", usuarioDAO.usuariosProducao());
		MV.addObject("dataCricao",relatorioDAO.dataCriacaoLista());
		List<String[]> mes = selecionaMesPorCriacaoLista("dataFechamento");
		MV.addObject("mesCriacao",mes);
		//Comentario
		return MV;
	}
	
	@RequestMapping("filtraListaUsuario")
	public ModelAndView filtraListaUsuario(String t,String s,String r) throws ParseException{
		Integer idUser = Integer.parseInt(t);
		//t = idUsuario , s = mes ( 02 ), r = ano
		
		ModelAndView MV = new ModelAndView();
		Usuario user = manager.find(Usuario.class, idUser);
		MV.setViewName("criacao/relatorio/porUsuario/listaUsuarioAjax");
		
		List<Integer> idsListasPeriodos = apoioRelatorioUsuario.pegaIdsListasPorUsuario(t,s,r);
	
		if(idsListasPeriodos.isEmpty() || idsListasPeriodos == null){
			System.out.println("null");
		}else{
	
			List<CriacaoLista> listas = relatorioDAO.CriacaoListasPorIds(idsListasPeriodos);
			MV.addObject("criacaoListas", listas);
		
			List<Integer> idsItensPeriodos = apoioRelatorioUsuario.idsItensPorUsuario(idsListasPeriodos,t);
			MV.addObject("tempoListasPeriodos", apoioRelatorioUsuario.somaTempoItensTodasListaPeriodos(idsItensPeriodos,t));
			
  		   /**
		    * Pega em um array a soma de cada item individual e o id da lista. 
		    */
			MV.addObject("itensTempo", apoioRelatorioUsuario.somaTempoListaVariosItens(idsItensPeriodos,idUser));
			
			
			/**
			 * Pega em um array a soma de todos os itens do usuario por Lista e o id da Lista 
			 */
			
//			apoioRelatorioUsuario.somaTempoItensPorLista(idsListasPeriodos, t);
			
			MV.addObject("tempoItensUsuario", apoioRelatorioUsuario.somaTempoItensPorLista(idsListasPeriodos, t));
			
		}
		
		MV.addObject("usuario", user);
		
		return MV;
		
	}
	
}
