package br.com.sysloccOficial.criacao.controllerCriacao.itens;

import java.util.List;

import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.CriacaoItemPendencia;
import br.com.sysloccOficial.model.CriacaoItemPendenciaHistorico;
import br.com.sysloccOficial.model.Usuario;

@Controller
@Transactional
public class CriacaoPendenciaController extends CriacaoPendencia{

	@Autowired AuxModelAndView auxMAV;
	@Autowired UsuarioDAO usuarioDAO;
	@Autowired Utilitaria util;
	
	
	
	ModelAndView MV = new ModelAndView();
	
	@RequestMapping("/exibePendencias")
	public ModelAndView exibePendenciasP(){
		
		Usuario u = util.retornaUsuarioLogado();
		
		Integer idResponsavel = u.getIdUsuario(); 
		String abertos = "where responsavelItem ="+idResponsavel+" and idCriacaoItemStatus !=2";
		String finalizados = "where responsavelItem ="+idResponsavel+" and idCriacaoItemStatus=2 order by dataTermino desc";
		
		List<Object[]> itensAbertos = exibePendencias(abertos);
		List<Object[]> itensFinalizados = exibePendencias(finalizados);
		
		MV.setViewName("criacao/listaPendencias");
		MV.addObject("pendencias",itensAbertos);
		MV.addObject("finalizados",itensFinalizados);
		
		return MV;
	}

	@RequestMapping("/exibeTarefas")
	public ModelAndView exibeTarefas(){
		
		Usuario u = util.retornaUsuarioLogado();
		
		Integer idResponsavel = u.getIdUsuario(); 
		String abertos = "where itemDelegado=1 and responsavelItem = "+idResponsavel+" and idCriacaoItemStatus = 1 order by dataCriacao";
		
		String finalizados = "where itemDelegado=1 and responsavelItem ="+idResponsavel+" and idCriacaoItemStatus = 2 order by termino";
		
		List<Object[]> itensAbertos = exibeTarefas(abertos);
		List<Object[]> itensFinalizados = exibeTarefas(finalizados);
		
		MV.setViewName("criacao/listaTarefas");
		MV.addObject("tarefas",itensAbertos);
		MV.addObject("finalizados",itensFinalizados);
		
		return MV;
	}

	
	
	
	@RequestMapping("/exibeHistoricoItem")
	public ModelAndView exibeHistoricoItem(Integer idHistoricoItem){
		MV.setViewName("criacao/itemPendente");
		MV.addObject("detPendencia",exibeDetalheItem(idHistoricoItem));
		MV.addObject("historicoPendencia",exibeDetalhePendenciaHistorico(idHistoricoItem));
		MV.addObject("historico",historicoCompleto(idHistoricoItem));
		
		MV.addObject("contaTempo", contaTempo(idHistoricoItem));    
		
//		MV.addObject("historico",exibeDetalheItem(idHistoricoItem));
		
		// Carregar historico detalhes da pendencia do item
		// Carregar historico detalhes do item
		
		return MV;
	}
	
	@RequestMapping("/iniciaTempoPendencia")
	public ModelAndView iniciaTempoT(Integer idCriacaoItemHistoricoPendencia){
		iniciaTempo(idCriacaoItemHistoricoPendencia);
		return iniciaTempoAjax(idCriacaoItemHistoricoPendencia);
	}
	
	@RequestMapping("/terminaTempoPendencia")
	public ModelAndView terminaTempoPendenciaP(Integer idCriacaoItemHistoricoPendencia){
		terminaTempo(idCriacaoItemHistoricoPendencia);
		return terminaTempoAjax(idCriacaoItemHistoricoPendencia);
	}
	
	@RequestMapping("/fechaPendencia")
	public String fechaPendenciaP(CriacaoItemPendencia cItemhPendencia){
		
		if(cItemhPendencia.getCriacaoItemStatusTransiente() == 2){ // Se finalizado.
			salvaPendencia(cItemhPendencia);
		}else{
			interrompePendencia(cItemhPendencia); // Se interrompido.
		}
		return "redirect:exibeHistoricoItem?idHistoricoItem="+cItemhPendencia.getIdCriacaoItemHistoricoPendenciaTransiente();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


