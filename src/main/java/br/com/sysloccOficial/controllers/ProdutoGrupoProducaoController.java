package br.com.sysloccOficial.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaConversoes;
import br.com.sysloccOficial.daos.AtuacaoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.ImpostoDAO;
import br.com.sysloccOficial.daos.JobDAO;
import br.com.sysloccOficial.daos.ListaDAO;
import br.com.sysloccOficial.daos.LocalEventoDAO;
import br.com.sysloccOficial.daos.ProducaoDAO;
import br.com.sysloccOficial.daos.ProdutoDAO;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.DetalhesItemProducao;
import br.com.sysloccOficial.model.DeterQuantpadrao;
import br.com.sysloccOficial.model.DeterminaQuantidades;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.OrcamentoFornecedor;
import br.com.sysloccOficial.model.Produto;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.Usuario;

import com.google.gson.Gson;

@Controller
@Transactional



public class ProdutoGrupoProducaoController {
	
	
	@Autowired private EmpresaDAO empresaDAO;
	@Autowired private JobDAO jobDAO;
	@Autowired private ListaDAO listaDAO;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@Autowired private AtuacaoDAO atuacaoDAO;
	@Autowired private ProdutoDAO produtoDAO;
	@Autowired private ImpostoDAO impostoDAO;
	@Autowired private ProducaoDAO producaoDAO;
	@Autowired private Utilitaria util;
	@Autowired private UtilitariaConversoes utilConv;
	@Autowired private ValoresEmGrupo valoresEmGrupo;
	@Autowired private ValoresProdutoGrupo valoresProdutoGrupo;
	@Autowired private ValoresNaLista valoresNaLista;
	@Autowired private LocalEventoDAO localEventoDAO;
	
	@PersistenceContext	private EntityManager manager;
	
	ModelAndView MV = new ModelAndView();

	
	
	@RequestMapping("/salvaDetalhesItem")
	public String salvaDetalhesItem(ProdutoGrupo produtoGrupo){
		
		String teste = produtoGrupo.getCustoProdutoTransiente();
		
		/**
		 * Faz uma verificação se o valor unitário do item está zero ou nulo
		 * Se estiver preenche com  o valor 0,00
		 * 
		 */
		
		if(teste.equals("")){
			produtoGrupo.setCustoProdutoTransiente("0,00");
		}
		
		// Teste
		
		 String quant = produtoGrupo.getQuantidadeTransiente().replace(",", ".");
		 produtoGrupo.setQuantidade(utilConv.converterStringParaDouble(quant));
		
		 String quant2 = produtoGrupo.getQuantidade2Transiente().replace(",", ".");
		 produtoGrupo.setQuantidade2(utilConv.converterStringParaDouble(quant2));

		 
		 String diaria = produtoGrupo.getDiariasTransiente().replace(",", ".");
		 produtoGrupo.setDiarias(utilConv.converterStringParaDouble(diaria));
		 
		// Fim Teste
		
		
		BigDecimal zero = new BigDecimal(0);

		Empresa empresa= manager.find(Empresa.class, produtoGrupo.getIdEmpresaTransiente());
		Produto produto = manager.find(Produto.class, produtoGrupo.getIdprodutoTransiente());
		Grupo grupo = manager.find(Grupo.class, produtoGrupo.getIdGrupoTransiente());
		Lista lista = manager.find(Lista.class, grupo.getIdLista().getIdLista());
		
		Usuario usuario = util.retornaUsuarioLogado();
		produtoGrupo.setUsuario(usuario);
		
		BigDecimal custoProduto = new BigDecimal(util.formataValores(produtoGrupo.getCustoProdutoTransiente()));
		
		if(produtoGrupo.isCustoComBvFornecedor() == false){		// 1 ----- Verifica se terá Custo com BV forn. incluso * Caiu em desuso ---- //
			produtoGrupo.setBvFornecedor(zero);
			produtoGrupo.setCustoComBvFornecedor(false);
			produtoGrupo.setBvFornecedorEmPorcentagem(false);
			produtoGrupo.setBvFornecedorValor(zero);
		}else{
			valoresProdutoGrupo.custoComBvFornecedorIncluso(produtoGrupo);
		}
		
		valoresProdutoGrupo.BvVenda(produtoGrupo,custoProduto,produtoGrupo.getIdGrupoTransiente());

        // 2 ----- Custo do produto sem impostos ou bv -------------- //	
		produtoGrupo.setCustoProduto(custoProduto); 
		produtoGrupo.setIdGrupo(grupo);
		produtoGrupo.setIdProduto(produto);
		produtoGrupo.setProduto(produto.getProduto());
		produtoGrupo.setAlteradoEm(Calendar.getInstance());
		produtoGrupo.setEmpresa(empresa);
		
		String al = Utilitaria.nomeAleatorio(12);
		
		produtoGrupo.setTidProdutoGrupo(al);
		
		manager.persist(produtoGrupo);

		// Verifica se é necessário uma quantidade Determinante nesse produto
		
		determinaQuantidadesPadrao(produtoGrupo, grupo);
		
		valoresEmGrupo.atualizaImpostoGrupo(grupo); /// ------ Setando valores no Grupo dos itens e na Lista do Grupo ------ // 	
		valoresNaLista.InsereValoresLista(grupo,lista); /// ------- Setar valores na lista --------- ///
		valoresNaLista.calculaValorLista(lista.getIdLista()); /// ------- Setar valores na lista --------- ///
		
		if(produtoGrupo.isSalvarEfechar() == true){
			return "redirect:editaLinha?idGrupo="+produtoGrupo.getIdGrupoTransiente();
		}else{
			return "redirect:descricaoItem?idProdutoGrupo="+produtoGrupo.getIdProdutoGrupo()+"&idGrupo="+produtoGrupo.getIdGrupoTransiente();
		}
		
	}


	private void determinaQuantidadesPadrao(ProdutoGrupo produtoGrupo, Grupo grupo) {
		DeterQuantpadrao padrao = produtoGrupoDAO.deterQuantPadrao(produtoGrupo.getIdGrupo().getIdgrupo());
		if(padrao != null){
			 double preco = produtoGrupoDAO.pegaPrecoGrupo(grupo.getIdgrupo());
			 BigDecimal quants = new BigDecimal(padrao.getDiariasPadrao()).multiply(new BigDecimal(padrao.getQuantDetermPadrao()));
			 BigDecimal valorUnit = new BigDecimal(preco).divide(quants,5,RoundingMode.UP);
			 padrao.setPrecoTotalPadrao(new BigDecimal(preco));
			 padrao.setValorUnitPadrao(valorUnit);
			 manager.merge(padrao);
		}else{
			DeterminaQuantidades deterQuant  = produtoGrupoDAO.determinaQuant(produtoGrupo.getIdGrupoTransiente());
			if(deterQuant  == null ){
				novoDeterminaQuant(produtoGrupo, grupo);
			}else{
				   BigDecimal[] pegaValores = pegaValores(produtoGrupo.getIdProdutoGrupo(), grupo.getIdgrupo());
				   BigDecimal quants = new BigDecimal(deterQuant.getDiarias()).multiply(new BigDecimal(deterQuant.getQuantDeterm()));
				   BigDecimal valorUnit = pegaValores[1].divide(quants,5,RoundingMode.UP);
				   
				   deterQuant.setPrecoTotal(pegaValores[1]);
				   deterQuant.setValorUnit(valorUnit);
				   manager.merge(deterQuant);
			}
		}
	}
	
	
// ----------------------------------------------------- Atualizações -------------------------------------------------------------------- //

		@RequestMapping("/atualizaDetalhesItem")
		public String atualizaDetalhesItem(ProdutoGrupo produtoGrupo){
			

			/**
			 * Faz uma verificação se o valor unitário do item está zero ou nulo
			 * Se estiver preenche com  o valor 0,00
			 * 
			 */
			String teste = produtoGrupo.getCustoProdutoTransiente();
			if(teste.equals("")){
				produtoGrupo.setCustoProdutoTransiente("0,00");
			}
			
			String quant = produtoGrupo.getQuantidadeTransiente().replace(",", ".");
			 produtoGrupo.setQuantidade(utilConv.converterStringParaDouble(quant));
			
			 String quant2 = produtoGrupo.getQuantidade2Transiente().replace(",", ".");
			 produtoGrupo.setQuantidade2(utilConv.converterStringParaDouble(quant2));

			 
			 String diaria = produtoGrupo.getDiariasTransiente().replace(",", ".");
			 produtoGrupo.setDiarias(utilConv.converterStringParaDouble(diaria));
			 
			// Fim Teste
			
			
			Produto produto = manager.find(Produto.class, produtoGrupo.getIdprodutoTransiente());
			Grupo grupo = manager.find(Grupo.class, produtoGrupo.getIdGrupoTransiente());
			Lista lista = manager.find(Lista.class, grupo.getIdLista().getIdLista());
			Empresa empresa= manager.find(Empresa.class, produtoGrupo.getIdEmpresaTransiente());
			
			Usuario usuario = util.retornaUsuarioLogado();
			produtoGrupo.setUsuario(usuario);
			
			
			BigDecimal custoProduto = new BigDecimal(util.formataValores(produtoGrupo.getCustoProdutoTransiente()));
			BigDecimal zero = new BigDecimal(0);
			
			// 1 Verifica se terá Custo com BV forn. incluso // Caiu em desuso Segundo o Pedro
			if(produtoGrupo.isCustoComBvFornecedor() == false){
				produtoGrupo.setBvFornecedor(zero);
				produtoGrupo.setCustoComBvFornecedor(false);
				produtoGrupo.setBvFornecedorEmPorcentagem(false);
				produtoGrupo.setBvFornecedorValor(zero);
			}else{
				valoresProdutoGrupo.custoComBvFornecedorIncluso(produtoGrupo);
			}
			
	         // 2 Custo do produto sem impostos ou bv
			produtoGrupo.setCustoProduto(custoProduto);		
					
			// 3 Verifica se terá BV venda 
			valoresProdutoGrupo.BvVenda(produtoGrupo,custoProduto,produtoGrupo.getIdGrupoTransiente());

			// 4 ------------------------------------		
					//Buscar mais detalhes sobre essa regra
			
			// 5 ------------------------------------		
			produtoGrupo.setIdGrupo(grupo);
			produtoGrupo.setIdProduto(produto);
			produtoGrupo.setProduto(produto.getProduto());
			produtoGrupo.setEmpresa(empresa);
			produtoGrupo.setAlteradoEm(Calendar.getInstance());
			
			String al = Utilitaria.nomeAleatorio(12);
			
			produtoGrupo.setTidProdutoGrupo(al);
			
			
			manager.merge(produtoGrupo);

			
			// Setando valores no Grupo dos itens
			valoresEmGrupo.atualizaImpostoGrupo(grupo);
			// Setando os valores na Lista 
//			valoresNaLista.InsereValoresLista(grupo, lista);
			valoresNaLista.calculaValorLista(lista.getIdLista()); /// ------- Setar valores na lista --------- ///		
			
			
			
			
			// Verifica se é necessário uma quantidade Determinante nesse produto
			DeterminaQuantidades d  = produtoGrupoDAO.determinaQuant(produtoGrupo.getIdGrupoTransiente());
			
			/*Integer pg1 = d.getProdutoGrupo().getIdProdutoGrupo();
			Integer pg2 = produtoGrupo.getIdProdutoGrupo();*/
			
			
			if(d  == null ){
				novoDeterminaQuant(produtoGrupo, grupo);
			}else{
				
				if(d.getProdutoGrupo().getIdProdutoGrupo().equals(produtoGrupo.getIdProdutoGrupo())){
					atualizaDeterminaQuant(d,produtoGrupo, grupo);
				}else{
					System.out.println("Não faz nada");
				}
				
			}
			
			
			
			
			
			if(produtoGrupo.isSalvarEfechar() == true){
				return "redirect:editaLinha?idGrupo="+produtoGrupo.getIdGrupoTransiente();
			}else{
				return "redirect:descricaoItem?idProdutoGrupo="+produtoGrupo.getIdProdutoGrupo()+"&idGrupo="+produtoGrupo.getIdGrupoTransiente();
			}
	}
	
// ----------------------------------------------------- Exclusões --------------------------------------------------------------------------------- //
		
	@RequestMapping("/excluiItem")
	public String excluiItem(Integer idProdutoGrupo,Integer idGrupo){
		
		BigDecimal zero = new BigDecimal("0");
		
		ProdutoGrupo produtoGrupo= manager.find(ProdutoGrupo.class, idProdutoGrupo);
		
		produtoGrupo.setCustoProduto(zero);
		produtoGrupo.setPrecoProduto(zero);
		produtoGrupo.setBv(zero);
		
		Grupo grupo = manager.find(Grupo.class, idGrupo);
		Lista lista = manager.find(Lista.class, grupo.getIdLista().getIdLista());
	
		valoresEmGrupo.atualizaImpostoGrupo(grupo); 
		valoresNaLista.InsereValoresLista(grupo, lista);
		
		determinaQuantidadesPadrao(produtoGrupo, grupo);
		
		try {
			TypedQuery<DeterminaQuantidades> q = manager.createQuery("from DeterminaQuantidades where produtogrupo ="+ produtoGrupo.getIdProdutoGrupo(),DeterminaQuantidades.class);
 			List<DeterminaQuantidades> det = q.getResultList();
			for (int i = 0; i < det.size(); i++) {
				manager.remove(det.get(i));
			}
 			
		} catch (Exception e) {
		}
		
		try {
			TypedQuery<OrcamentoFornecedor> q = manager.createQuery("from OrcamentoFornecedor where produto ="+ produtoGrupo.getIdProdutoGrupo(),OrcamentoFornecedor.class);
			List<OrcamentoFornecedor> orc = q.getResultList();
			for (int i = 0; i < orc.size(); i++) {
				manager.remove(orc.get(i));
			}
		} catch (Exception e) {
		}
		
		manager.remove(produtoGrupo);
		return "redirect:editaLinha?idGrupo="+idGrupo;
	}
			
	
	
	@RequestMapping("/determinaQuantidade")
	@ResponseBody
	public String determinaQuantidade(Integer idProdutoGrupo,Integer idGrupo){

		ProdutoGrupo produto = manager.getReference(ProdutoGrupo.class, idProdutoGrupo);
		Grupo grupo = manager.getReference(Grupo.class, idGrupo);
		DeterminaQuantidades d  = produtoGrupoDAO.determinaQuant(idGrupo);
		DeterQuantpadrao detPad  = produtoGrupoDAO.deterQuantPadrao(idGrupo);
		
		
		DetalhesItemProducao detalhes = new DetalhesItemProducao();
		
		BigDecimal[] valores = pegaValores(produto.getIdProdutoGrupo(), grupo.getIdgrupo());
		// valores [0] = t - valorUnit; 
		// valores [1] = p - preco;
		// valores [2] = q - quantDeter Bigdecimal;
		// valores [3] = d - Diaria Bigdecimal;
		
		double quantDeter =  valores[2].doubleValue();
		double diarias = valores[3].doubleValue();
		
		double quantDiaria = quantDeter*diarias;
		
		if(d  == null ){
			
			if(detPad == null){
				novoDeterminaQuant(produto,grupo);
			}else{
				manager.remove(detPad);
				novoDeterminaQuant(produto,grupo);
			}

		 }else{
			d.setProdutoGrupo(produto);
			d.setValorUnit(valores[0]);
			d.setPrecoTotal(valores[1]);
			d.setQuantDeterm(quantDeter);
			d.setDiarias(diarias);
			
			manager.merge(d);
		}
		
		String quantDia = String.valueOf(quantDiaria);
		String precs = valores [1].toString();
		precs = util.ConverteDolarParaReal(precs);
		String tots = valores [0].toString();
		tots = util.ConverteDolarParaReal(tots);
		detalhes.setDeterminaQuant(quantDia);
		detalhes.setPrecoTotal(precs);
		detalhes.setValorUnit(tots);
		
		
		Gson gson = new Gson();
		return gson.toJson(detalhes);
	}
	
	
	
	@RequestMapping("/determinaQuantidadePadrao")
	@ResponseBody
	public String determinaQuantidadePadrao(Integer idGrupo, double quantidade, double diaria){
		
	//	ProdutoGrupo produto = manager.getReference(ProdutoGrupo.class, idProdutoGrupo);
		Grupo grupo = manager.getReference(Grupo.class, idGrupo);
		
		DeterminaQuantidades determ  = produtoGrupoDAO.determinaQuant(idGrupo);
		
		DeterQuantpadrao detPad  = produtoGrupoDAO.deterQuantPadrao(idGrupo);
		
		
		
		DetalhesItemProducao detalhes = new DetalhesItemProducao();
		
		BigDecimal[] valores = pegaValoresPadrao(quantidade, grupo.getIdgrupo(),diaria );
		// valores [0] = t - valorUnit; 
		// valores [1] = p - preco;
		// valores [2] = q - quantDeter Bigdecimal;
		// valores [3] = d - Diaria Bigdecimal;
		
		double quantDeter =  valores[2].doubleValue();
		double diarias = valores[3].doubleValue();
		double quantDiaria = quantDeter*diarias;

		
		if(determ  == null ){
		 		if(detPad == null){
		 			novoDeterminaQuantPadrao(grupo, quantidade, quantDiaria);
		 		}else{
					detPad.setValorUnitPadrao(valores[0]);
					detPad.setPrecoTotalPadrao(valores[1]);
					detPad.setQuantDetermPadrao(quantDeter);
					detPad.setDiariasPadrao(diarias);
					manager.merge(detPad);
		 			
		 		}
		 	}else{
		 	
		 		manager.remove(determ);
		 		
		 		if(detPad == null){
		 			novoDeterminaQuantPadrao(grupo, quantidade, quantDiaria);
		 		}else{
					detPad.setValorUnitPadrao(valores[0]);
					detPad.setPrecoTotalPadrao(valores[1]);
					detPad.setQuantDetermPadrao(quantDeter);
					detPad.setDiariasPadrao(diarias);
					manager.merge(detPad);
		 			
		 		}
		 		
		 	}
		 	
		
		String quantDia = String.valueOf(quantDiaria);
		
		String precs = valores [1].toString();
		precs = util.ConverteDolarParaReal(precs);
		
		String tots = valores [0].toString();
		tots = util.ConverteDolarParaReal(tots);
		
		detalhes.setDeterminaQuant(quantDia);
		detalhes.setPrecoTotal(precs);
		detalhes.setValorUnit(tots);
		
		
		Gson gson = new Gson();
		return gson.toJson(detalhes);
	}
	
	public BigDecimal[] pegaValores(Integer idProdutoGrupo,Integer idGrupo){
		
		double quantDeter = produtoGrupoDAO.pegaQuantidade(idProdutoGrupo);
		double preco = produtoGrupoDAO.pegaPrecoGrupo(idGrupo);
		double diarias = produtoGrupoDAO.pegaDiaria(idProdutoGrupo);
		
		BigDecimal d = BigDecimal.valueOf(diarias);
		BigDecimal q = BigDecimal.valueOf(quantDeter);
		BigDecimal p = BigDecimal.valueOf(preco);
		BigDecimal t = p.divide(q,5,RoundingMode.UP).divide(d,5,RoundingMode.UP);
		BigDecimal[] valores = {t,p,q,d};

		return valores;
	}
	
	public void novoDeterminaQuant(ProdutoGrupo produto, Grupo grupo) {
		BigDecimal[] valores = pegaValores(produto.getIdProdutoGrupo(), grupo.getIdgrupo());
		
		double quantDeter =  valores[2].doubleValue();
		double diarias = valores[3].doubleValue();
		
		DeterminaQuantidades novoDeterm = new DeterminaQuantidades();
		
		novoDeterm.setProdutoGrupo(produto);
		novoDeterm.setGrupo(grupo);
		novoDeterm.setValorUnit(valores[0]);
		novoDeterm.setPrecoTotal(valores[1]);
		novoDeterm.setQuantDeterm(quantDeter);
		novoDeterm.setDiarias(diarias);
		
		manager.persist(novoDeterm);
		
	}
	
	public void novoDeterminaQuantPadrao(Grupo grupo, double quantidadePadrao, double diaria) {
		BigDecimal[] valores = pegaValoresPadrao(quantidadePadrao, grupo.getIdgrupo(), diaria);
		
		double quantDeter =  valores[2].doubleValue();
		double diarias = valores[3].doubleValue();
		
		DeterQuantpadrao determPadrao = new DeterQuantpadrao();
	 	
	 	determPadrao.setGrupo(grupo);
		determPadrao.setValorUnitPadrao(valores[0]);
		determPadrao.setPrecoTotalPadrao(valores[1]);
		determPadrao.setQuantDetermPadrao(quantDeter);
		determPadrao.setDiariasPadrao(diarias);
		manager.persist(determPadrao);
		
	}

	public void atualizaDeterminaQuant(DeterminaQuantidades novoDeterm, ProdutoGrupo produto, Grupo grupo) {
		BigDecimal[] valores = pegaValores(produto.getIdProdutoGrupo(), grupo.getIdgrupo());
		
		double quantDeter =  valores[2].doubleValue();
		double diarias = valores[3].doubleValue();
		
		novoDeterm.setProdutoGrupo(produto);
		novoDeterm.setGrupo(grupo);
		novoDeterm.setValorUnit(valores[0]);
		novoDeterm.setPrecoTotal(valores[1]);
		novoDeterm.setQuantDeterm(quantDeter);
		novoDeterm.setDiarias(diarias);
		
		manager.merge(novoDeterm);
		
	}
	
	public BigDecimal[] pegaValoresPadrao(double quantidadePadrao,Integer idGrupo, double diaria){
		
		double quantDeter = quantidadePadrao;
		double preco = produtoGrupoDAO.pegaPrecoGrupo(idGrupo);
		double diarias = diaria;
		
		BigDecimal d = BigDecimal.valueOf(diarias);
		
		BigDecimal q = BigDecimal.valueOf(quantDeter);
		BigDecimal p = BigDecimal.valueOf(preco);
		BigDecimal t = p.divide(q,5,RoundingMode.UP).divide(d,5,RoundingMode.UP);
		BigDecimal[] valores = {t,p,q,d};

		return valores;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
