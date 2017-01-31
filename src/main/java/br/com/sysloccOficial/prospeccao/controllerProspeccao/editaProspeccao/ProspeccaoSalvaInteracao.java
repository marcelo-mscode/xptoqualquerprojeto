package br.com.sysloccOficial.prospeccao.controllerProspeccao.editaProspeccao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.UtilitariaMsg;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.model.prospeccao.InteracaoProspeccao;
import br.com.sysloccOficial.prospeccao.dao.InteracoesDAO;
import br.com.sysloccOficial.prospeccao.dao.ProspeccaoDAO;


@Controller
public class ProspeccaoSalvaInteracao {
	@Autowired UtilitariaMsg utilMsg;
	@Autowired ProspeccaoDAO prospDAO;
	@Autowired EmpresaDAO empresaDAO;
	@Autowired InteracoesDAO interacoesDAO;
	@Autowired private ContatoDAO contatoDAO;
	
	
	@RequestMapping("salvaInteracao")
	public String salvaInteracao(InteracaoProspeccao interacao, RedirectAttributes rd){
	    interacoesDAO.salvaInteracaoProspeccao(interacao);
	    rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		return "redirect:edicaoProspeccao?idProspeccao="+interacao.getIdProspeccaoTrans();
	}

}
