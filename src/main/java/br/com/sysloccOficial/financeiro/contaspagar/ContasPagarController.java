package br.com.sysloccOficial.financeiro.contaspagar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.financeiro.dao.ContasPagarDAO;
import br.com.sysloccOficial.financeiro.dao.MontaContasPagarDAO;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Controller
public class ContasPagarController {
	
	@Autowired ContasPagarDAO contasPagar;
	@Autowired InternaAuxiliar internaAuxiliar;
	@Autowired Utilitaria utildatas;
	@Autowired MontaContasPagarDAO montaObjeto;
	
	@RequestMapping("contasPagar")
	public ModelAndView contasPagar(){
		ModelAndView MV = new ModelAndView("financeiro/contasPagar/contasPagar");
		
		List<Object[]> idListas = montaObjeto.pegaListasIndividuais(); 
		MV.addObject("idListas",idListas);
		
		List<Object[]> lista = montaObjeto.constroiObjeto();
		MV.addObject("novaLista", lista);
		
		return MV;
	}
	
	
	@RequestMapping("contasTeste")
	private ModelAndView contaTeste(){
		ModelAndView MV = new ModelAndView("menuProducao/testeContas");

		return MV;
	}
	
	@RequestMapping("pagaConta")
	public ModelAndView pagaConta(Integer idLista,Integer idFornecedor,Integer qtdDias){
		montaObjeto.pagaConta(idLista,idFornecedor,qtdDias);
		
		ModelAndView MV = new ModelAndView("financeiro/contasPagar/contasPagarAjax");
		List<Object[]> idListas = montaObjeto.pegaListasIndividuais(); 
		MV.addObject("idListas",idListas);
		
		List<Object[]> lista = montaObjeto.constroiObjeto();
		MV.addObject("novaLista", lista);
		
		return MV;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
