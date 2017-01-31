package br.com.sysloccOficial.prospeccao.controllerProspeccao.editaProspeccao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.UtilitariaMsg;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.prospeccao.Prospeccao;
import br.com.sysloccOficial.prospeccao.dao.InteracoesDAO;
import br.com.sysloccOficial.prospeccao.dao.ProspeccaoDAO;


@Controller
public class ProspeccaoEditaController {
	@Autowired UtilitariaMsg utilMsg;
	@Autowired ProspeccaoDAO prospDAO;
	@Autowired EmpresaDAO empresaDAO;
	@Autowired UsuarioDAO usuarioDAO;
	@Autowired InteracoesDAO interacoesDAO;
	@Autowired private ContatoDAO contatoDAO;
	
	
	@RequestMapping("edicaoProspeccao")
	public ModelAndView editaProspeccao(Integer idProspeccao){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("prospeccao/editaProspeccao/edicaoProspeccao");
		Prospeccao p = prospDAO.pegaProspeccao(idProspeccao);
		MV.addObject("prospeccao", p);
		MV.addObject("empresas", empresaDAO.mostraEmpresaJob());
		MV.addObject("contato", contatoDAO.mostraContato(p.getIdEmpresa().getIdEmpresa()));
		MV.addObject("interacao",interacoesDAO.listar(idProspeccao,"prospeccao"));
		MV.addObject("usuariosHabilitados",usuarioDAO.mostraHabilitados());
		return MV;
	}
	
	
	@RequestMapping("editaProspeccao")
	public String editaProspeccao(Prospeccao p, RedirectAttributes rd){
		Empresa e = empresaDAO.retornaEmpresa(p.getIdEmpresaTrans());
		p.setIdEmpresa(e);
		prospDAO.editaProspeccao(p);
		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		return "redirect:edicaoProspeccao?idProspeccao="+p.getIdProspeccao();
	}
	
	

}
