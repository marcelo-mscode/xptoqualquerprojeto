package br.com.sysloccOficial.criacao.controllerCriacao.itens;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.consultas.ConsultasGenericas;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.CriacaoItemHistorico;
import br.com.sysloccOficial.model.CriacaoItemPendencia;
import br.com.sysloccOficial.model.CriacaoItemLista;
import br.com.sysloccOficial.model.CriacaoItemStatus;
import br.com.sysloccOficial.model.Usuario;


@Controller
@Transactional
public class CriacaoItemController extends CriacaoItem{
//	@Autowired MVGenerica auxMAV;
	@PersistenceContext	private EntityManager manager;
	@Autowired private UsuarioDAO usuarioDAO;
	@Autowired private ConsultasGenericas consultaGenerica;
	@Autowired private Utilitaria util;
	@Autowired private CriacaoPendencia criacPendencia;
	
	ModelAndView MV = new ModelAndView();
	

	@RequestMapping("/exibeItens")
	public ModelAndView exibeItens(Integer idCriacaoLista){
		
		MV.setViewName("criacao/itens");
		List<CriacaoItemLista> itens = filtroItemCriacao(idCriacaoLista);
		MV.addObject("itens", itens);
		String consulta = "select c from CriacaoItemLista c where idCriacaoLista="+idCriacaoLista+" and idCriacaoItemStatus != 2";
		MV.addObject("listaStatus",consultaGenerica.ListaDeObjetos(consulta));
		MV.addObject("itemPendente", CriacaoItemPendencia(idCriacaoLista));
		MV.addObject("fechaLista", fechaLista(idCriacaoLista));
		
		return MV;
	}
	
	@RequestMapping("/exibeDetalhesDoItem")
	public ModelAndView exibeDetalhesDoItem(Integer idCriacaoItem){
		MV.setViewName("criacao/detalhesItem");
		CriacaoItemLista detalheItem = exibeDetalheItem(idCriacaoItem);
		List<Usuario> usuarios = usuarioDAO.usuarioPorDepartamento(4); // Exbir todos os usuários do sistema
		List<Usuario> usuariosDependencia = usuarioDAO.mostraHabilitados();
		
		MV.addObject("usuarios", usuarios);
		MV.addObject("usuariosDependencia", usuariosDependencia);
		MV.addObject("detalheItem", detalheItem);
		MV.addObject("itemHistorico", verificaHistoricoItem(idCriacaoItem));
        MV.addObject("historico", verificaHistoricoItemCompleto(idCriacaoItem));
		
        
        MV.addObject("contaTempo", contaTempo(idCriacaoItem));        
        MV.addObject("somaDoTempoTotal", somaTempoTotal(idCriacaoItem));    

		return MV;
	}
	
	@RequestMapping("/iniciaTempo")
	public ModelAndView iniciaTempoT(Integer idCriacaoItem){
		MV.setViewName("criacao/detalhesTempo/tempoItem");
		MV.addObject("tempo", iniciaTempo(idCriacaoItem));
		
		return MV;
	}

	@RequestMapping("/finalizaTempo")
	public ModelAndView finalizaTempo(Integer idCriacaoItem,Integer idHistoricoItem){
		MV.setViewName("criacao/detalhesTempo/tempoteste"); 
		MV.addObject("tempo2", terminaTempo(idCriacaoItem,idHistoricoItem));
		return MV;
	}
	
	@RequestMapping("/mudaStatusItem")
	public String mudaStatusItemI(CriacaoItemLista criacaoItemlista){
		
		//Responsavel do item
		
		MudaStatus(criacaoItemlista);
		
		
		
		return "redirect:exibeDetalhesDoItem?idCriacaoItem="+criacaoItemlista.getIdCriacaoItemLista();
	}
	
	@RequestMapping("/salvarDi")
	public String savaItemI(CriacaoItemLista itemLista){
		Integer criacaoItemlista = SalvaDI(itemLista);
		return "redirect:exibeItens?idCriacaoLista="+ criacaoItemlista;
	}

	@RequestMapping("/cria3D")
	@ResponseBody
	public String cria3D(Integer idCriacaoItemLista){
		Integer criacaoItemlista = Cria3D(idCriacaoItemLista);
		return "ok";
//		return "redirect:exibeItens?idCriacaoLista="+ criacaoItemlista;
	}
	
	@RequestMapping("/delegaItem")
	public String delegaItemI(CriacaoItemLista criacaoItemlista){ // Muda o responsável pelo item
		delegaItem(criacaoItemlista);
		
		return "redirect:exibeDetalhesDoItem?idCriacaoItem="+criacaoItemlista.getIdCriacaoItemLista();
	}
	
	@RequestMapping("/dependenciaItem")
	public String dependenciaItemI(CriacaoItemLista criacaoItemlista, CriacaoItemPendencia historicoPendenc){
		dependenciaItem(criacaoItemlista,historicoPendenc);
		return "redirect:exibeDetalhesDoItem?idCriacaoItem="+criacaoItemlista.getIdCriacaoItemLista();
	}
	
	@RequestMapping("/novaVersao")
	public String novaVersaoV(Integer idItemLista){
		Integer itemNovo = novaVersao(idItemLista);
		return "redirect:exibeDetalhesDoItem?idCriacaoItem="+itemNovo;
	}
	
	@RequestMapping("/alteracaoInfoItem")
	public String alteracaoInfoItem(CriacaoItemLista idCriacaoItem){
		CriacaoItemLista item = manager.find(CriacaoItemLista.class, idCriacaoItem.getIdCriacaoItemLista());	
		item.setInformaoesItem(idCriacaoItem.getInformaoesItem());
		manager.merge(item);
	return "redirect:exibeDetalhesDoItem?idCriacaoItem="+item.getIdCriacaoItemLista();
	}

	@RequestMapping("/infoUteis")
	public String infoUteis(CriacaoItemLista idCriacaoItem){
		CriacaoItemLista item = manager.find(CriacaoItemLista.class, idCriacaoItem.getIdCriacaoItemLista());	
		item.setInfoUteis(idCriacaoItem.getInfoUteis());
		manager.merge(item);
	return "redirect:exibeDetalhesDoItem?idCriacaoItem="+item.getIdCriacaoItemLista();
	}

}
