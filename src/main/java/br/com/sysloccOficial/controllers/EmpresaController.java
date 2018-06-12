package br.com.sysloccOficial.controllers;

import java.io.IOException;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaMsg;
import br.com.sysloccOficial.consultas.consultasAvancadas.ConsultasPassaSql;
import br.com.sysloccOficial.daos.AtuacaoDAO;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.PagamentoDAO;
import br.com.sysloccOficial.model.Atuacao;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.EmpresaAtuacao;
import br.com.sysloccOficial.model.Pagamento;
import br.com.sysloccOficial.model.TipoPagamento;



@Controller
@Transactional
public class EmpresaController {
	
	@Autowired 	private EmpresaDAO empresaDAO;
	@Autowired	private ContatoDAO contatoDAO;
	@Autowired	private AtuacaoDAO tagDAO;
	@Autowired	private PagamentoDAO pagamentoDAO;
	@Autowired	private AtuacaoDAO atuacaoDAO;
	@Autowired	private ConsultasPassaSql sql;
	@Autowired private UtilitariaMsg utilMsg;
	@Autowired private Utilitaria util;
	
	@Autowired private FileSaver fileSaver;
	@PersistenceContext private EntityManager manager;
	
	ModelAndView MV = new ModelAndView();
	
	
	@RequestMapping("/empresa")
	public ModelAndView emp() {
		MV.setViewName("empresa/empresa");
		MV.addObject("tipoPagamento",pagamentoDAO.listaTipoPagamento());
	return MV;
	}
	
	
	@RequestMapping("/listaempresa")
	/*@Cacheable(value="listaEmpresa")*/
	public ModelAndView lista(Integer first, Integer max){
		ModelAndView mv = new ModelAndView("empresa/listaEmpresa");
		
		long tempoInicio = System.currentTimeMillis();
	
		
		mv.addObject("empresas", empresaDAO.mostra(first, max));
		
		long tempoTotal = (System.currentTimeMillis()-tempoInicio);
		
		long  segundos = ( tempoTotal / 1000 ) % 60;    
		
		
		System.out.println("Tempo Total em segundos: "+segundos);
		
		
		mv.addObject("min", first);
		mv.addObject("max", max);
		
		
		//atualizaBancoEmpresa();
		
		
		return mv;
	}
	
	
	
	
	@RequestMapping("/salvaEmpresa")
	/*@CacheEvict(value="listaEmpresa", allEntries=true)*/
	public String salva(Pagamento pagamento,Empresa e,Integer idEmpresaAtualiza,RedirectAttributes rd){
	
		Calendar c = Calendar.getInstance();
		e.setDataCadastro(c);
		e.setIdEmpresa(idEmpresaAtualiza);

		e.setDescricao("-");
		e.setLogotipo("-");
		
		
		if(e.isFornecedor() == true){
			TipoPagamento tipoPagamento = manager.find(TipoPagamento.class, pagamento.getIdTipoPagamentoTransiente());
			pagamento.setIdEmpresa(e);
			pagamento.setIdtipoPagamento(tipoPagamento);
			
			empresaDAO.salva(e);
			manager.persist(pagamento);
		}else{
			empresaDAO.salva(e);
		}
		
		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		
		return "redirect:infoempresa?id="+e.getIdEmpresa();
	}

	
	
	
	@RequestMapping("/AtualizaEmpresa")
	public String atualizaEmpresa(MultipartFile NomeAnexo,Pagamento pagamento, Empresa e, Integer idEmpresaAtualiza, RedirectAttributes rd){
	
		e.setDataCadastro(Calendar.getInstance());
		e.setIdEmpresa(idEmpresaAtualiza);
		
		e.setDescricao(" - ");
		
		if(NomeAnexo.isEmpty()){
			e.setLogotipo(empresaDAO.pegaLogotipo(e.getIdEmpresaAtualiza()));
		}else{
			String webPath = fileSaver.LogoEmpresa(NomeAnexo);
			e.setLogotipo(webPath);
		}

		if(e.getIdEmpresaAtualiza()!= null && e.isFornecedor() == true && pagamento.getIdpagamento() == null){
			
			TipoPagamento tipoPagamento = manager.find(TipoPagamento.class, pagamento.getIdTipoPagamentoTransiente());
			pagamento.setIdEmpresa(e);
			pagamento.setIdtipoPagamento(tipoPagamento);
			
			manager.merge(e);
			manager.detach(e);
			
			manager.persist(pagamento);
			
		
	    }else if(e.getIdEmpresaAtualiza()!= null && e.isFornecedor() == true && pagamento.getIdpagamento() != null){
	    	
	    	TipoPagamento tipoPagamento = manager.find(TipoPagamento.class, pagamento.getIdTipoPagamentoTransiente());
	    	pagamento.setIdEmpresa(e);
			pagamento.setIdtipoPagamento(tipoPagamento);
			
			manager.merge(e);
			manager.merge(pagamento);
		}
	    else {
			manager.merge(e);
		}
		
		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		
		return "redirect:infoempresa?id="+idEmpresaAtualiza;
	}
	
	@RequestMapping("/deletaEmpresa")
	public String deletaEmpresa(Integer idEmpresa){
		empresaDAO.deletaEmpresa(idEmpresa);
		return "redirect:listaempresa";
	}
	
	//Ativar - Desativar
	@RequestMapping("/statusempresa")
	public ModelAndView status(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("empresa/status");
		return mv;
		
	}
		
	@RequestMapping("/infoempresa")
	public ModelAndView info(Integer id){
		
		ModelAndView mv = new ModelAndView("empresa/infoempresa");
		
		mv.addObject("infoempresa", empresaDAO.infoempresa(id));
		mv.addObject("contato",contatoDAO.mostraContatosEmInfoEmpresa(id));
		mv.addObject("empresaAtuacao",empresaDAO.listaEmpresaAtuacao(id));
		mv.addObject("tags",tagDAO.mostra());
        mv.addObject("pagamento",pagamentoDAO.listaPagamento(id));
        mv.addObject("tipoPagamento",pagamentoDAO.listaTipoPagamento());
        
		
		return mv;
	}
	
	@RequestMapping("/testeContato")
	public ModelAndView teste(){
		ModelAndView mv = new ModelAndView("empresa/infocontato");
		
		mv.addObject("comunicador",contatoDAO.mostra(3));
		
		Contato c = new Contato();
		System.out.println("Teste de Lista: "+c.getContato());
		
		return mv;
	}
	
	@RequestMapping("/SalvarTagEmpresa")
	public String SalvarTagEmpresa(Integer idEmpresaAtuacao, Integer idAtuacao){
		
		
		Empresa e = manager.find(Empresa.class, idEmpresaAtuacao);
		Atuacao a = manager.find(Atuacao.class, idAtuacao);
		
		EmpresaAtuacao empresaAtuacao  = new EmpresaAtuacao();
		
		empresaAtuacao.setIdEmpresa(e);
		empresaAtuacao.setIdAtuacao(a);
		
		manager.persist(empresaAtuacao);
		
		return "redirect:infoempresa?id="+idEmpresaAtuacao;
	}
		
	@RequestMapping("/salvaNovaTag")
	public String salvaNovaTag(Atuacao a){
		atuacaoDAO.salva(a);
		return "redirect:infoempresa?id="+a.getIdEmpresaTag();
		
	}
	
	@RequestMapping("/busca")
	public ModelAndView buscaAjax(String nome) throws IOException {
		MV.setViewName("empresa/listaAjax");
		
		if(nome == null || nome == "" || nome == " "){
			MV.addObject("empresas", empresaDAO.mostra(0,350));
		}else{
			long tempoInicio = System.currentTimeMillis();
			
			System.out.println("Comecei a busca em: "+tempoInicio);
			
			MV.addObject("empresas", empresaDAO.listaBuscaAjaxEmpresas(nome));
			long tempoTotal = (System.currentTimeMillis()-tempoInicio);
			long  segundos = ( tempoTotal / 1000 ) % 60;    
			System.out.println("Tempo Total em segundos da busca: "+segundos);
		}
		
	    return MV;
	}
	
	@RequestMapping("/buscaClientes")
	public ModelAndView buscaClientes(Integer tipo){
		MV.setViewName("empresa/listaAjax");
		
		String fornecedores = "";
		
		if(tipo == 1){ fornecedores = " cliente != 0 ";	}
		
		if(tipo == 2){ fornecedores = " prospect != 0 "; }

		if(tipo == 3){ fornecedores = " cliente != 0 and prospect != 0 "; }


		if(tipo == 5){ fornecedores = " fornecedor != 0 and cliente != 0 "; }

		if(tipo == 6){ fornecedores = " fornecedor != 0 and prospect != 0 "; }

		if(tipo == 7){ fornecedores = " cliente != 0 and prospect != 0 and fornecedor != 0 "; }
		
		if(tipo == 4){ fornecedores = " fornecedor != 0 ";
/*			String f = "from Empresa where fornecedor != 0 order by habilitado,empresa";
			MV.addObject("empresas", sql.retornaLista(f));*/   
		}

		String consulta = "from Empresa where "+fornecedores+" order by habilitado,empresa";

		MV.addObject("empresas", sql.retornaLista(consulta));

		return MV;
	}

	@RequestMapping("/fornecedor")
	public ModelAndView fornecedor(){
		ModelAndView mm = new ModelAndView();
		
		mm.setViewName("empresa/listaAjax");
		
		String fornecedores = "from Empresa where fornecedor != 0 order by habilitado,empresa";
		mm.addObject("empresas", sql.retornaLista(fornecedores));
		
		return mm;
	}
	
	@RequestMapping("/excluitagEmpresa")
	public String excluitag(Integer idEmpresaAtuacao,Integer idEmpresa, RedirectAttributes rd){
		
		Query q = manager.createNativeQuery("DELETE FROM Empresaatuacao WHERE idEmpresaAtuacao='"+idEmpresaAtuacao+"';");
		
		q.executeUpdate();
		
		rd.addFlashAttribute("msg", utilMsg.msgComSucesso());

		return "redirect:infoempresa?id="+idEmpresa;
	}
	
	@RequestMapping("/salvaEmpresaLista")
	public ModelAndView salvaEmpresaLista(Pagamento pagamento,Empresa e,Integer idEmpresaAtualiza,RedirectAttributes rd){
	
		Calendar c = Calendar.getInstance();
		e.setDataCadastro(c);
		e.setIdEmpresa(idEmpresaAtualiza);

		e.setDescricao("-");
		e.setLogotipo("-");

		e.setFornecedor(true);
		e.setHabilitado(true);
		
		
		if(e.isFornecedor() == true){
			TipoPagamento tipoPagamento = manager.find(TipoPagamento.class, pagamento.getIdTipoPagamentoTransiente());
			pagamento.setIdEmpresa(e);
			pagamento.setIdtipoPagamento(tipoPagamento);
			
			empresaDAO.salva(e);
			manager.persist(pagamento);
		}else{
			empresaDAO.salva(e);
		}
		
		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		
		Query q = manager.createNativeQuery("UPDATE Empresa SET habilitado='-1' WHERE idEmpresa > '0' and habilitado = 1;");
		q.executeUpdate();

		MV.setViewName("empresa/fornecedorLista");
		MV.addObject("fornecedores", empresaDAO.listaFornecedores());
		MV.addObject("fornecedorNovo", empresaDAO.listaFornecedoresPorId(e.getIdEmpresa()));
		
		return MV;
	}

	@RequestMapping("/verificaEmpresa")
	@ResponseBody
	public int verificaEmpresa(String empresa){
		String consulta = "from Empresa p where empresa like '";		
		String tiraEspaco = "Update empresa Set empresa = Trim(empresa) where idEmpresa > 0";
		int valor = util.confereSeExisteNoBanco(empresa,tiraEspaco,consulta);
		return valor;	
	}	
	
	
	/**
	 * Método para corrigir uma inconsistência que existia no banco de dados antigo
	 * que salvava cliente e habilitado como 1.
	 * Para o filtro lista todas as empresas na ordem definida é necessário que esses
	 * campos estejam como -1. 
	 * Esse método garante que esses campos fiquem como -1 convertendo-os. 
	 * 
	 * 
	 */
	public void atualizaBancoEmpresa(){
		Query q = manager.createNativeQuery("UPDATE Empresa SET habilitado='-1' WHERE idEmpresa > '0' and habilitado = 1;");
		q.executeUpdate();
		
		Query q2 = manager.createNativeQuery("UPDATE Empresa SET cliente='-1' WHERE idEmpresa > '0' and cliente = 1;");
		q2.executeUpdate();
	}
	
	
	
	
	
	
	
	
}
