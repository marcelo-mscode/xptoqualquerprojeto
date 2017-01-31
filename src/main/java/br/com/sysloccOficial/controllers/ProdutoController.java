//String valor = NumberFormat.getCurrencyInstance().format(12345678.90);

package br.com.sysloccOficial.controllers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaMsg;
import br.com.sysloccOficial.daos.AtuacaoDAO;
import br.com.sysloccOficial.daos.GeneroDAO;
import br.com.sysloccOficial.daos.MarcaDAO;
import br.com.sysloccOficial.daos.ProdutoDAO;
import br.com.sysloccOficial.daos.UnidadeDAO;
import br.com.sysloccOficial.model.Atuacao;
import br.com.sysloccOficial.model.Marca;
import br.com.sysloccOficial.model.MarcaProduto;
import br.com.sysloccOficial.model.Produto;
import br.com.sysloccOficial.model.ProdutoAtuacao;




@Controller
@Transactional
public class ProdutoController {
	
	@Autowired	private AtuacaoDAO tagDAO;
	@Autowired	private MarcaDAO marcaDAO;
	@Autowired	private ProdutoDAO produtoDAO;
	@Autowired	private UnidadeDAO unidadeDAO;
	@Autowired	private GeneroDAO generoDAO;
	@Autowired	private AtuacaoDAO atuacaoDAO;
	@Autowired  private UtilitariaMsg utilMsg;
	@Autowired  private Utilitaria util;
	
	@PersistenceContext
	private EntityManager manager;

	@RequestMapping("/produto")
	public ModelAndView produto() {
	
	ModelAndView MV = new ModelAndView("produtos/produto");
	
	MV.addObject("genero",generoDAO.mostra());
	MV.addObject("unidades",unidadeDAO.mostra());
	MV.addObject("tags",tagDAO.mostra());
	MV.addObject("marcas",marcaDAO.mostra());
		
	return MV;
	}
	
	@RequestMapping("/salvaProduto")
	public String salva(Produto produto,String custoPadrao1,String precoPadrao1, RedirectAttributes rd){
	
		if(custoPadrao1.equals(null) || custoPadrao1 == "" || custoPadrao1 == " "){
			custoPadrao1 ="0";
			precoPadrao1 ="0";	
		}
		
		BigDecimal custo = new BigDecimal(formataValores1(custoPadrao1));
		BigDecimal preco = new BigDecimal(formataValores1(precoPadrao1));
		
		produto.setCustoPadrao(custo);
		produto.setPrecoPadrao(preco);
		
		produto.setDataAtualizacao(Calendar.getInstance());
	
		produtoDAO.salva(produto);
		
		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		
		return "redirect:informacaoProduto?id="+produto.getIdproduto();
	}
	
	
	@RequestMapping("/atualizaProduto")
	public String atualizaUsuario(Produto produto,String custoPadrao1,String precoPadrao1, RedirectAttributes rd){
		
		if(custoPadrao1.equals(null) || custoPadrao1 == "" || custoPadrao1 == " "){
			custoPadrao1 ="0";
			precoPadrao1 ="0";	
		}
		
		BigDecimal custo = new BigDecimal(formataValores1(custoPadrao1));
		BigDecimal preco = new BigDecimal(formataValores1(precoPadrao1));
		
		produto.setCustoPadrao(custo);
		produto.setPrecoPadrao(preco);
		
	    //Pega data e hora atual do sistema	
		Calendar c = Calendar.getInstance();
		produto.setDataAtualizacao(c);
		
		List<ProdutoAtuacao>produtoAtuacao = produtoDAO.atualiza(produto);
		
		produtoDAO.atualizaProdutoAtucao(produtoAtuacao, produto.getIdproduto());
		
		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		
		return "redirect:informacaoProduto?id="+produto.getIdproduto();
	}
	
	
	
	
	
	@RequestMapping("/removeProduto")
	public String removeUsuario(Integer id){
		produtoDAO.remove(id);
		return "redirect:listaProdutos";
	}
	
	
	@RequestMapping("/listaProdutos")
	public ModelAndView lista(){
		ModelAndView mv = new ModelAndView("produtos/listaProdutos");
		mv.addObject("produtos", produtoDAO.mostra());
		return mv;
	}
	
	
	
	
	@RequestMapping("/informacaoProduto")
	public ModelAndView produtos(Integer id) throws InterruptedException{
		
		ModelAndView mv = new ModelAndView("produtos/editaProduto");
		
		mv.addObject("produtos", produtoDAO.listaPorId(id , "unidade"));
		mv.addObject("unidade", unidadeDAO.mostra());
		mv.addObject("generos",  generoDAO.mostra());
		mv.addObject("produtoAtuacao",  produtoDAO.listaProdutoAtuacao(id));
		mv.addObject("produtoMarca",  produtoDAO.listaProdutomarca(id));
		
		mv.addObject("tags",tagDAO.mostra());
		mv.addObject("marcas",marcaDAO.mostra());

	/*	new Thread();Thread.sleep(1000);  */
		
		return mv;
	}
	
	
	@RequestMapping("/SalvarTagProduto")
	public String SalvarTagProduto(Integer idprodutoAtuacao, Integer idAtuacao){
		Produto p = manager.find(Produto.class, idprodutoAtuacao);
		Atuacao a = manager.find(Atuacao.class, idAtuacao);
		
		ProdutoAtuacao prodAtua  = new ProdutoAtuacao();
		
		prodAtua.setIdProduto(p);
		prodAtua.setIdAtuacao(a);
		
		manager.persist(prodAtua);
		
		return "redirect:informacaoProduto?id="+idprodutoAtuacao+"#marcas";
	}
	
	
	@RequestMapping("/SalvarMarcaProduto")
	public String SalvarMarcaProduto(Integer idprodutoAtuacao, Integer idmarca){
		Produto p = manager.find(Produto.class, idprodutoAtuacao);
	
		Marca a = manager.find(Marca.class, idmarca);
		
		MarcaProduto marcaProd  = new MarcaProduto();
		
		marcaProd.setIdProduto(p);
		marcaProd.setIdMarca(a);
		
		manager.persist(marcaProd);
		
		return "redirect:informacaoProduto?id="+idprodutoAtuacao+"#marcas";
	}
	
	@RequestMapping("/salvaNovaTagProduto")
	public String salvaNovaTag(Atuacao a, Integer idprodutoAtuacao){
		
		atuacaoDAO.salva(a);
		
		
		return "redirect:informacaoProduto?id="+idprodutoAtuacao+"#marcas";
		
	}
	
	@RequestMapping("/salvaMarcaProduto")
	public String salvaMarca(Marca m, Integer idprodutoAtuacao){
	
		marcaDAO.salvaMarca(m);
	
		return "redirect:informacaoProduto?id="+idprodutoAtuacao+"#marcas";
	}
	
	
	@RequestMapping("/verificaProduto")
	@ResponseBody
	public int verificaProduto(String produto){
		String confere = "";

		if(produto == "" || produto == null || produto == " "){
			confere = "anular";
		}else{
			confere = "ok";
		}
		
		String espaco = "Update produto Set produto = Trim(produto) where idProduto > 0";
		manager.createNativeQuery(espaco).executeUpdate();

		String limpaProduto = produto.trim();
		String consulta ="from Produto p where produto like '"+limpaProduto+"'";
		
		Query query = manager.createQuery(consulta);
		if(query.getResultList().isEmpty() &&confere == "ok"){
			return 0;
		}else{
			return 1;
		}
		
	}
	
	/*@RequestMapping("/atualizaProdutoLista")
	public String atualizaProdutoLista(Produto produto,String custoPadrao1,String precoPadrao1, RedirectAttributes rd){
		salvaProduto(produto, custoPadrao1, precoPadrao1);
		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		return "redirect:descricaoItem?idProdutoGrupo="+produtoGrupo.getIdProdutoGrupo()+"&idGrupo="+produtoGrupo.getIdGrupoTransiente();
	}*/
	
	
	
	@RequestMapping("/salvaProdutoLista")
	public String salvaProdutoLista(Produto produto,String custoPadrao1,String precoPadrao1, RedirectAttributes rd){
		salvaProduto(produto, custoPadrao1, precoPadrao1);
		rd.addFlashAttribute("msg", utilMsg.msgConfirmacaoDadosSalvos());
		return "redirect:inserirDadosItem?idGrupo="+produto.getIdGrupo();
	}
	
	@RequestMapping("/excluitag")
	public String excluitag(Integer idprodutoatuacao,String idprodutoAtuacao, RedirectAttributes rd){
		
		Query q = manager.createNativeQuery("DELETE FROM Produtoatuacao WHERE idprodutoAtuacao='"+idprodutoatuacao+"';");
		q.executeUpdate();
		
		rd.addFlashAttribute("msg", utilMsg.msgComSucesso());

		return "redirect:informacaoProduto?id="+idprodutoAtuacao+"#marcas";
	}

	@RequestMapping("/excluimarca")
	public String excluimarca(Integer idmarcaProduto,String idprodutoAtuacao, RedirectAttributes rd){
		
		Query q = manager.createNativeQuery("DELETE FROM Marcaproduto WHERE idmarcaProduto='"+idmarcaProduto+"';");
		q.executeUpdate();
		
		rd.addFlashAttribute("msg", utilMsg.msgComSucesso());
		
		return "redirect:informacaoProduto?id="+idprodutoAtuacao+"#marcas";
	}
	
	
	
	public String formataValores1(String i){
			
			String j;
			j = i.replace('.', ' ').replace(',', '.').replaceAll(" ", "");	
			
			return j;
		}
	
	public void salvaProduto(Produto produto,String custoPadrao1,String precoPadrao1){
		
		if(custoPadrao1.equals(null) || custoPadrao1 == "" || custoPadrao1 == " "){
			custoPadrao1 ="0";
			precoPadrao1 ="0";	
		}
		
		BigDecimal custo = new BigDecimal(formataValores1(custoPadrao1));
		BigDecimal preco = new BigDecimal(formataValores1(precoPadrao1));
		
		produto.setCustoPadrao(custo);
		produto.setPrecoPadrao(preco);
		produto.setDataAtualizacao(Calendar.getInstance());
		produto.setProduto(util.primeiraLetraMaiuscula(produto.getProduto())); // Coloca primeira letra Maiuscula
		
		produtoDAO.salva(produto);
		
		String atuacao = produto.getIdAtuacaoEmLista();
		
		if(atuacao.equals("selecione")){
			
		}else{
			
			Integer atua = Integer.parseInt(atuacao);
			Atuacao a = manager.find(Atuacao.class, atua);
			ProdutoAtuacao prodAtua  = new ProdutoAtuacao();
			
			prodAtua.setIdProduto(produto);
			prodAtua.setIdAtuacao(a);
			
			manager.persist(prodAtua);
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
