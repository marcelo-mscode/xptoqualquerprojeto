package br.com.sysloccOficial.financeiro.contasreceber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.ContasPagarDAO;
import br.com.sysloccOficial.financeiro.dao.ContasReceberDAO;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.RelatorioEventos;


@Controller
public class ContasReceberController {
	
	
	@Autowired private ContasReceberDAO contasreceberDAO;
	
	@RequestMapping("contasReceber")
	public ModelAndView contasReceber(){
		ModelAndView MV = new ModelAndView("financeiro/contasReceber/contasReceber");
		
		List<RelatorioEventos> relatorio = contasreceberDAO.relatorioEventos();
		List<InfoInterna> infoInterna = contasreceberDAO.inforInterna();
		
		
		
		MV.addObject("listaReceber",relatorio);
		MV.addObject("infoInterna",infoInterna);
		
		return MV;
	}

	@RequestMapping("receberConta")
	public String receberConta(Integer idLista){
		
		
		contasreceberDAO.recebeConta(idLista);
		
		return "redirect:contasReceber";
	}
	
	
	
}
