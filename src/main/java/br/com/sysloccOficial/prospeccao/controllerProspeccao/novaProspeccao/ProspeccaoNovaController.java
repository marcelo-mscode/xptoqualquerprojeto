package br.com.sysloccOficial.prospeccao.controllerProspeccao.novaProspeccao;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.UtilitariaMsg;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.PagamentoDAO;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Pagamento;
import br.com.sysloccOficial.model.TipoPagamento;
import br.com.sysloccOficial.model.prospeccao.Prospeccao;
import br.com.sysloccOficial.prospeccao.dao.ProspeccaoDAO;


@Controller
@Transactional
public class ProspeccaoNovaController {

	@Autowired EmpresaDAO empresaDAO;
	@Autowired ProspeccaoDAO prospDAO;
	@Autowired ContatoDAO contatoDAO;
	@Autowired PagamentoDAO pagamentoDAO;
	
	@Autowired private UtilitariaMsg utilMsg;

	
	
	@RequestMapping("novaProspeccao")
	public ModelAndView novaProspeccao(){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("prospeccao/novaProspeccao/prospeccaoNova");
		MV.addObject("empresas", empresaDAO.mostraEmpresaInteracao());
		return MV;
	}
	
	@RequestMapping("salvaProspeccao")
	public String salvaProspeccao(Prospeccao prospeccao){
		prospDAO.salvaProspeccaoNova(prospeccao);
		return "redirect:edicaoProspeccao?idProspeccao="+prospeccao.getIdProspeccao();
	}
	
	@RequestMapping("/salvaEmpresaNovaProspeccao")
	public ModelAndView salvaEmpresaNovaProspeccao(Pagamento pagamento,Empresa e){
		ModelAndView MV = new ModelAndView();
		prospDAO.salvaEmpresaNova(pagamento, e);

		MV.setViewName("prospeccao/novoClienteProspect/fornecedorLista");
		MV.addObject("fornecedores", empresaDAO.mostraEmpresaJob());
		MV.addObject("fornecedorNovo", empresaDAO.listaFornecedoresPorId(e.getIdEmpresa()));
		
		return MV;
	}
	
	@RequestMapping("carregaCliente")
	public ModelAndView carregaCliente(Integer idEmpresa){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("prospeccao/editaClienteProspect/editaClienteProspect");
		
		MV.addObject("infoempresa", empresaDAO.infoempresa(idEmpresa));
		MV.addObject("pagamento",pagamentoDAO.listaPagamento(idEmpresa));
		MV.addObject("tipoPagamento",pagamentoDAO.listaTipoPagamento());
		
		return MV;
	}
	
	@RequestMapping("atualizaClienteProspeccao")
	public ModelAndView atualizaClienteProspeccao(Pagamento pagamento, Empresa e, Integer idEmpresaAtualiza){
		ModelAndView MV = new ModelAndView();
		prospDAO.atualizaEmpresaProspeccao(pagamento, e, idEmpresaAtualiza);
		MV.setViewName("prospeccao/editaProspeccao/modalEditaContato/okRetorno");
		return MV;
	}
	
	@RequestMapping("/buscaContatoProspeccao")
	public ModelAndView buscaContato(Integer idEmpresa) throws InterruptedException{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("prospeccao/novaProspeccao/prospeccaoContatos");
		MV.addObject("contato", contatoDAO.mostraContato(idEmpresa));
		MV.addObject("idEmpresa", idEmpresa);
		new Thread();
		Thread.sleep(500);  
		return MV;
	}
	
	
	
	
	
}
