
package br.com.sysloccOficial.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.daos.AtuacaoDAO;
import br.com.sysloccOficial.daos.ConfiguracaoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.EstatusDAO;
import br.com.sysloccOficial.daos.GeneroDAO;
import br.com.sysloccOficial.daos.GrupoDAO;
import br.com.sysloccOficial.daos.ImpostoDAO;
import br.com.sysloccOficial.daos.JobDAO;
import br.com.sysloccOficial.daos.ListaDAO;
import br.com.sysloccOficial.daos.LocalEventoDAO;
import br.com.sysloccOficial.daos.PagamentoDAO;
import br.com.sysloccOficial.daos.ProducaoDAO;
import br.com.sysloccOficial.daos.ProdutoDAO;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.daos.UnidadeDAO;
import br.com.sysloccOficial.model.DeterQuantpadrao;
import br.com.sysloccOficial.model.DeterminaQuantidades;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.ListaConsulta;
import br.com.sysloccOficial.model.Produto;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.Usuario;

@Controller
@Transactional
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ProducaoListagemController {
	
	@Autowired private EmpresaDAO empresaDAO;
	@Autowired private JobDAO jobDAO;
	@Autowired private ListaDAO listaDAO;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@Autowired private AtuacaoDAO atuacaoDAO;
	@Autowired private ProdutoDAO produtoDAO;
	@Autowired private ImpostoDAO impostoDAO;
	@Autowired private ProducaoDAO producaoDAO;
	@Autowired private Utilitaria util;
	@Autowired private ValoresEmGrupo valoresEmGrupo;
	@Autowired private ValoresProdutoGrupo valoresProdutoGrupo;
	@Autowired private ValoresNaLista valoresNaLista;
	@Autowired private LocalEventoDAO localEventoDAO;
	@Autowired private EstatusDAO estatusDAO;
	@Autowired private UnidadeDAO unidadeDAO;
	@Autowired private GeneroDAO generoDAO;
	@Autowired private AtuacaoDAO tagDAO;
	@Autowired private PagamentoDAO pagamentoDAO;
	@Autowired private GrupoDAO grupoDAO;
	@Autowired private ConfiguracaoDAO configuracaoDAO;
	@Autowired private UtilitariaDatas utilDatas;
	
	@PersistenceContext	private EntityManager manager;
	
	// ----------------------------------------------------- Listagens ---------------------------------------------------------------------- //	
	
	@RequestMapping("/producao")
	public ModelAndView prod() {
		ModelAndView MV = new ModelAndView();
		MV.setViewName("producao/novaListaProducao");
		MV.addObject("empresas", empresaDAO.listaEmpresaPorNome());
		return MV;
	}
	
	@RequestMapping("/populaListajobPorCliente")
	public ModelAndView populaListajobPorCliente(String nomeEmpresa){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("producao/listaJobSelect");
		MV.addObject("listaJob", jobDAO.listaJobIdENome(nomeEmpresa));
		return MV;
	}
	
	@RequestMapping("/exibeListasPorJob")
	public ModelAndView exibeListasPorJob(Integer idJob){

		ModelAndView MV = new ModelAndView();
		MV.setViewName("producao/copiaListaPorJob");
		MV.addObject("listaJob", listaDAO.listasPorJob(idJob));

		return MV;
	}
	
	@RequestMapping("/listaProducao")
    @Cacheable(value="listaProducao")
	public ModelAndView listaProducao() throws ParseException{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("producao/exibeListas");
		
		
		List<Object[]> codListas = listaDAO.idListasTeste();
		
		List<Integer> idList2 = new ArrayList<Integer>();
		
		
		for(int i =0; i< codListas.size();i++){
			String codList = (String) codListas.get(i)[0];
			List<Integer> codigos = listaDAO.retornaCodListas(codList);
			
			for(int x = 0; x < codigos.size();x++){
				idList2.add(codigos.get(x));
			}
		}
		

		List<ListaConsulta> listas = new ArrayList<ListaConsulta>();
		
		for(int i = 0; i < idList2.size(); i++){

			Integer idLista = idList2.get(i);
			
			ListaConsulta l = new ListaConsulta();

			l.setLista((String)listaDAO.condicao(idLista,"l.lista"));
			l.setIdLista((Integer)listaDAO.condicao(idLista,"l.idLista"));
			l.setRevisao((Integer)listaDAO.condicao(idLista,"l.revisao"));
			l.setEmpresa((String)listaDAO.condicao(idLista,"l.idJob.empresa.empresa"));
			l.setJob((String)listaDAO.condicao(idLista,"l.idJob.titulo"));
			l.setStatus((String)listaDAO.condicao(idLista,"l.idlistaEstatus.listaEstatus"));
			l.setListaCod((String)listaDAO.condicao(idLista,"l.listaCod"));
			listas.add(l);
		}
		
		MV.addObject("lista2", listas);
		
		MV.addObject("empresas", empresaDAO.listaEmpresasClientes());
		MV.addObject("status", estatusDAO.ListaEstatusProducao());
		
		
		Date dataMeses = utilDatas.subtraiMeses(18);
		ArrayList<String> dataBase = utilDatas.converteDateParaStringNacional(dataMeses);
		
		
		String anoBase = dataBase.get(1)+"/"+dataBase.get(2);
		
		MV.addObject("anoBase", anoBase);
		
		return MV;
	}
	
	@RequestMapping("/editaLista")
	public ModelAndView editaLista(Integer idLista, HttpSession session){
		
		Usuario user = util.retornaUsuarioLogado();
		
		session.setAttribute("usuarioSessao", user.getNome());
		
		TypedQuery<Integer> q = manager.createQuery("select l.idJob.idJob from Lista l where idLista="+idLista,Integer.class);
		Integer idJob = q.getSingleResult();
		
		ModelAndView MV = new ModelAndView();
		MV.setViewName("producao/producao");
		MV.addObject("lista", listaDAO.editaLista(idLista));
		MV.addObject("categoria", listaDAO.listadeCategorias(idLista));
		MV.addObject("categoriasGalderma", listaDAO.listadeCategoriasGalderma());
		MV.addObject("categoriasBayer", listaDAO.listadeCategoriasBayer());
		MV.addObject("grupoOrdem", listaDAO.listadeGruposPorIdLista(idLista));
		
		MV.addObject("imposto",impostoDAO.listaImposto());
		MV.addObject("localEvento", localEventoDAO.mostra(idJob));
		
		return MV;
	}
	
	@RequestMapping("/editaListaAncora")
	public ModelAndView editaListaAncora(Integer idLista, HttpSession session){
		
		Usuario user = util.retornaUsuarioLogado();
		session.setAttribute("usuarioSessao", user.getNome());
		
		Lista lista = manager.find(Lista.class, idLista);
		ModelAndView MV = new ModelAndView();
		MV.setViewName("producao/producao");
		MV.addObject("lista", listaDAO.editaLista(idLista));
		MV.addObject("categoria", listaDAO.listadeCategorias(idLista));
		MV.addObject("grupoOrdem", listaDAO.listadeGruposPorIdLista(idLista));
		MV.addObject("imposto",impostoDAO.listaImposto());
		MV.addObject("localEvento", localEventoDAO.mostra(lista.getIdJob().getIdJob()));
		
		return MV;
	}
	
	//Imprimir página de producao
		@RequestMapping("/printProducao")	
		public ModelAndView printProducao(Integer idLista, HttpSession session){
			
			ModelAndView MV = new ModelAndView();
			
			List<Lista> lista = listaDAO.editaLista(idLista);
			MV.addObject("categoria", listaDAO.listadeCategorias(idLista));
			
			MV.addObject("lista", lista);
			MV.setViewName("producao/printProducao");
			return MV;

		}
	
	
	@RequestMapping("/editaLinha")
	public ModelAndView EditaLinha(Integer idGrupo){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("producao/editaLinha");
		MV.addObject("produtoGrupo", produtoGrupoDAO.listaProdutoGrupoPorGrupo(idGrupo));
		MV.addObject("grupo", produtoGrupoDAO.listaGrupo(idGrupo));
		MV.addObject("categorias", produtoGrupoDAO.pegaCategoriaPorIdGrupo(idGrupo));
		MV.addObject("categoriasBayer", produtoGrupoDAO.categoriaBayer());
		MV.addObject("lista", produtoGrupoDAO.pegaListaParaProdutoGrupo(idGrupo));
		MV.addObject("empresa",produtoGrupoDAO.empresaLista(idGrupo));
		MV.addObject("fornecedores", empresaDAO.listaFornecedores());
		MV.addObject("orcamento", grupoDAO.orcamentoFornecedor(idGrupo));
		MV.addObject("idGrupo",idGrupo);
		
		DeterminaQuantidades det =  produtoGrupoDAO.quantDeterm(idGrupo);

		DeterQuantpadrao detPadrao =  produtoGrupoDAO.quantDetermPadrao(idGrupo);

		MV.addObject("detPadrao", detPadrao);
		
		if(det == null){
			
					if(detPadrao == null){
							
					}else{
							double quantDeter = detPadrao.getQuantDetermPadrao();
							double preco = produtoGrupoDAO.pegaPrecoGrupo(idGrupo);
							double diaria = detPadrao.getDiariasPadrao();
							
							BigDecimal diar = BigDecimal.valueOf(diaria);
							BigDecimal q = BigDecimal.valueOf(quantDeter).multiply(diar);
							
							BigDecimal p = BigDecimal.valueOf(preco);
							BigDecimal t = p.divide(q,12,RoundingMode.UP);
		
							MV.addObject("vUnitario", t);
							MV.addObject("precoT", p);
							MV.addObject("quantD", quantDeter*diaria);
							
					   }
			
		}else{
			
			double quantDeter = produtoGrupoDAO.pegaQuantidade(det.getProdutoGrupo().getIdProdutoGrupo());
			double preco = produtoGrupoDAO.pegaPrecoGrupo(idGrupo);
			double diaria = produtoGrupoDAO.pegaDiaria(det.getProdutoGrupo().getIdProdutoGrupo());
			
			BigDecimal diar = BigDecimal.valueOf(diaria);
			BigDecimal q = BigDecimal.valueOf(quantDeter).multiply(diar);
			
			BigDecimal p = BigDecimal.valueOf(preco);
			BigDecimal t = p.divide(q,12,RoundingMode.UP);

			MV.addObject("vUnitario", t);
			MV.addObject("precoT", p);
			MV.addObject("quantD", quantDeter*diaria);
		}
		
		return MV;
	}
	
	@RequestMapping("/descricaoItem")
	public ModelAndView descricaoItem(Integer idProdutoGrupo,Integer idGrupo){
		ProdutoGrupo produtoGrupo= manager.find(ProdutoGrupo.class, idProdutoGrupo);
		BigDecimal custoMaisBv = produtoGrupo.getCustoProduto().add(produtoGrupo.getBv());
//		BigDecimal zero = new BigDecimal("0");
		ModelAndView MV = new ModelAndView();
		
		Grupo imposto = producaoDAO.pegaIdcategoriaPorIdGrupo(idGrupo);
  		String imposto2 = imposto.getIdCategoria().getImposto().toString();
  		String resultadoFinalConverte ="0";
  		
  		
  		/**
  		 * Verifica se o custo do produto + o bv está como zero
  		 * 
  		 * Se estiver não será possível aplicar a fórmula.
  		 * O melhor é mandar o valor como zero para a JSP
  		 * 
  		 */
  		if(custoMaisBv.compareTo(BigDecimal.ZERO) == 0){
  		}else{
  			resultadoFinalConverte = util.formulaParaCalculoImposto(imposto2, custoMaisBv);
  		}
  		
		MV.setViewName("producao/descricaoItem");
		MV.addObject("lista", produtoGrupoDAO.pegaListaParaProdutoGrupo(idGrupo));
		MV.addObject("detalhesItem", produtoGrupoDAO.detalhesItem(idProdutoGrupo));
		
		double qt1 = produtoGrupoDAO.pegaQuantidades(idProdutoGrupo, "quantidade");
		double qt2 = produtoGrupoDAO.pegaQuantidades(idProdutoGrupo, "quantidade2");
		double diarias = produtoGrupoDAO.pegaQuantidades(idProdutoGrupo, "diarias");
		
		MV.addObject("quantidade1", util.teste(qt1));
		MV.addObject("quantidade2",util.teste(qt2));
		MV.addObject("diarias",util.teste(diarias));
		
		MV.addObject("fornecedores", empresaDAO.listaFornecedores());
		MV.addObject("tags",atuacaoDAO.mostra());
		MV.addObject("produtos",produtoDAO.listaidNomeProdutos());
		MV.addObject("empresa",produtoGrupoDAO.empresaLista(idGrupo));
		MV.addObject("produtoGrupo", produtoGrupoDAO.listaProdutoGrupoPorGrupo(idGrupo));
		MV.addObject("grupo", produtoGrupoDAO.apenasNomeGrupo(idGrupo));
		MV.addObject("Impostogrupo", produtoGrupoDAO.impostoCategoria(idGrupo));
		MV.addObject("formulaImposto", resultadoFinalConverte);
		MV.addObject("idProdutoGrupo", idProdutoGrupo);
		MV.addObject("idGrupo",idGrupo);
		MV.addObject("genero",generoDAO.mostra());
		MV.addObject("unidades",unidadeDAO.mostra());
		
		return MV;
	}
	
	@RequestMapping("/produtoAtuacao")
	public ModelAndView produtoAtuacao(Integer idAtuacao){
		ModelAndView MV = new ModelAndView();
		List<Produto> lista = produtoDAO.listaProdutosPorTag(idAtuacao);
		
		MV.setViewName("producao/produtoAtuacao");
		MV.addObject("fornecedores",lista);
		return MV;
	}
	
	@RequestMapping("/todosProdutoAtuacao")
	public ModelAndView todosProdutoAtuacao(){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("producao/todosProdutoAtuacao");
		MV.addObject("produtos",produtoDAO.listaidNomeProdutos());
		return MV;
	}
	
	@RequestMapping("/inserirDadosItem")
	public ModelAndView InserirDadosItem(Integer idGrupo){

		ModelAndView MV = new ModelAndView();
		MV.setViewName("producao/novoitem");
		MV.addObject("lista", produtoGrupoDAO.pegaListaParaProdutoGrupo(idGrupo));
		MV.addObject("produtoGrupo", produtoGrupoDAO.listaProdutoGrupoPorGrupo(idGrupo));
		MV.addObject("empresa",produtoGrupoDAO.empresaLista(idGrupo));
		MV.addObject("grupo", produtoGrupoDAO.apenasNomeGrupo(idGrupo));
		MV.addObject("produtos",produtoDAO.listaidNomeProdutos());
		MV.addObject("fornecedores", empresaDAO.listaFornecedores());
		MV.addObject("idGrupo",idGrupo);
		MV.addObject("impostoGrupo",produtoGrupoDAO.impostoCategoria(idGrupo) );
		MV.addObject("tags",atuacaoDAO.mostra());
		MV.addObject("genero",generoDAO.mostra());
		MV.addObject("unidades",unidadeDAO.mostra());
		MV.addObject("tags",tagDAO.mostra());
		MV.addObject("tipoPagamento",pagamentoDAO.listaTipoPagamento());
		
		return MV;
	}
	
}	
// ----------------------------------------------------------------------------------------------------------------------------------------------- //
