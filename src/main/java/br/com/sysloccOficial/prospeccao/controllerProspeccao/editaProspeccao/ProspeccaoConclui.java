package br.com.sysloccOficial.prospeccao.controllerProspeccao.editaProspeccao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.UtilitariaMsg;
import br.com.sysloccOficial.prospeccao.dao.ProspeccaoDAO;

@Controller
public class ProspeccaoConclui {

	@Autowired ProspeccaoDAO prospeccaoDAO;
	@Autowired UtilitariaMsg utilMsg;
	
	@RequestMapping("concluiProspeccao")
	public String concluiProspeccao(Integer idProspeccao, RedirectAttributes rd){
		prospeccaoDAO.concluiProspeccao(idProspeccao);
		rd.addFlashAttribute("msg", utilMsg.msgProspeccaoConfirmacaoDadosSalvos());
		return "redirect:edicaoProspeccao?idProspeccao="+idProspeccao;
	}

	@RequestMapping("cancelaConcluiProspeccao")
	public String cancelaConcluiProspeccao(Integer idProspeccao, RedirectAttributes rd){
		prospeccaoDAO.cancelaConcluiProspeccao(idProspeccao);
		rd.addFlashAttribute("msg", utilMsg.msgProspeccaoCancelaDadosSalvos());
		return "redirect:edicaoProspeccao?idProspeccao="+idProspeccao;
	}
	
	
	
	
	
}
