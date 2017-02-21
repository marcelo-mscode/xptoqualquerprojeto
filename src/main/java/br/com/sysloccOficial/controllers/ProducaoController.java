
package br.com.sysloccOficial.controllers;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sysloccOficial.calculosProducao.CalculaValoresProdutoGrupo;
import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaConversoes;
import br.com.sysloccOficial.criacao.controllerCriacao.dao.CriacaoDAO;
import br.com.sysloccOficial.daos.ListaDAO;
import br.com.sysloccOficial.daos.ProducaoDAO;
import br.com.sysloccOficial.daos.ProdutoDAO;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.CenariosGalderma;
import br.com.sysloccOficial.model.CriacaoItemLista;
import br.com.sysloccOficial.model.CriacaoItemStatus;
import br.com.sysloccOficial.model.CriacaoLista;
import br.com.sysloccOficial.model.DetalhesItemProducao;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.GrupoCategoriaBayer;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;
import br.com.sysloccOficial.model.Imposto;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.ListaEstatus;
import br.com.sysloccOficial.model.OrcamentoFornecedor;
import br.com.sysloccOficial.model.Produto;
import br.com.sysloccOficial.model.User;
import br.com.sysloccOficial.model.Usuario;

import com.google.gson.Gson;

@Controller
@Transactional
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ProducaoController {
	
	@Autowired private ValoresNaLista valoresNaLista;
	@Autowired private ProducaoDAO producaoDAO;
	@Autowired private Utilitaria util;
	@Autowired private UtilitariaConversoes utilConv;
	@Autowired private ProdutoDAO produtoDAO;
	@Autowired private ListaDAO listaDAO;
	@Autowired private ValoresEmGrupo valoresEmGrupo;
	@Autowired private CriacaoDAO criacaoDAO;
	@Autowired private CalculaValoresProdutoGrupo calculaValores;
	
	@PersistenceContext	private EntityManager manager;
	
// ----------------------------------------------------- Inserções --------------------------------------------------------------------------------- //
	
	@RequestMapping("/cadastraLista")
    @CacheEvict(value="listaProducao", allEntries=true)
	public String CadastraLista(Lista l){
		
		BigDecimal zero = new BigDecimal(0);
		Integer zero1 = 0;
		l.setDataCriacao(Calendar.getInstance());
		
		Job idJob = manager.find(Job.class, l.getIdJobLista());
		l.setIdJob(idJob);
		
		Usuario usuario = manager.find(Usuario.class, 40);
		l.setUsuario(usuario);

		ListaEstatus listaEstatus = manager.find(ListaEstatus.class, 1);
		l.setIdlistaEstatus(listaEstatus);
		
		l.setMargemPadrao(zero);
		l.setAdministracao(new BigDecimal(14));
		l.setFeeReduzido(new BigDecimal(7));
		l.setSubTotalCusto(zero);
		l.setSubTotalVendaNaoIncideImposto(zero);  
		l.setSubTotalVendaIncideImposto(zero);     
		l.setAdministracaoValor(zero);             
		l.setImpostoValor(zero);                   
		l.setValorTotal(zero);                     
		l.setListaBvFornecedorValor(zero);                     
		l.setConcluido(zero1);
		l.setConcluido(zero1);
		l.setFormaPagamento(" ");
		l.setListaCod(util.montaCodigoLista(idJob.getIdJob()));
		l.setRevisao(0);
		
		listaDAO.SalvaLista(l);
		
		return "redirect:editaLista?idLista="+l.getIdLista();
	}
	
	@RequestMapping("/atualizaNomeLista")
	public ModelAndView atualizaNomeLista(Integer idLista, String nomeNovaLista){
		ModelAndView MV = new ModelAndView();
		Lista lista = manager.find(Lista.class, idLista);
		lista.setLista(nomeNovaLista);
		manager.merge(lista);
		MV.setViewName("producao/listaProducao/nomeListaNovo");
		MV.addObject("titulo", lista.getLista());
		return MV;
	}
	
	@RequestMapping("/salvaCategoria")
	public String salvaCategoria(Categoria categoria){
		
		Lista lista = manager.find(Lista.class, categoria.getIdListaTransiente());
		Imposto imposto = manager.find(Imposto.class, categoria.getIdImpostoTrasiente());
		
		
		Categoria ordem = producaoDAO.ordemCategoria(categoria.getIdListaTransiente());
		if(ordem == null){
			categoria.setCategoriaOrdem(1);
		}else{
			categoria.setCategoriaOrdem(ordem.getCategoriaOrdem()+1);
		}
		
		categoria.setIdLista(lista);
		categoria.setImposto(imposto.getImposto());
		categoria.setImpostoTitulo(imposto.getImpostoTitulo());
		categoria.setIdImposto(imposto);
		
		manager.persist(categoria);
		
		return "redirect:editaListaAncora?idLista="+categoria.getIdListaTransiente()+"#ac"+categoria.getIdcategoria();
	}
	
	@RequestMapping("/SalvaGrupo")
	public String SalvaGrupo(Grupo grupo){
		
		BigDecimal zero = new BigDecimal("0");
		
		Lista lista = manager.find(Lista.class, grupo.getIdListaTransiente());	
		Categoria categoria = manager.find(Categoria.class, grupo.getIdCategoriaTransiente());
		
		
		if(grupo.getIdgrupoCategoriaBayerTransiente() == null){

		}else{
			GrupoCategoriaBayer categoriaBayer = manager.getReference(GrupoCategoriaBayer.class, grupo.getIdgrupoCategoriaBayerTransiente());
			grupo.setGrupoCategoriaBayer(categoriaBayer);
		}

		if(grupo.getIdCategoriaGaldermaTransiente() == null){
			
		}else{
			GrupoCategoriaGalderma categoriaGalderma = manager.getReference(GrupoCategoriaGalderma.class, grupo.getIdCategoriaGaldermaTransiente());
			grupo.setGrupoCategoriaGalderma(categoriaGalderma);
		}
		
		Integer idCategoria = categoria.getIdcategoria();
		
		Grupo ordem = listaDAO.ordemGrupo(idCategoria);
		if(ordem == null){
			grupo.setOrdemGrupo(1);
		}else{
			grupo.setOrdemGrupo(ordem.getOrdemGrupo()+1);
		}
		
		if(grupo.isIncideAdministracao() == true || grupo.isFeeReduzido() == true){
			grupo.setIncideAdministracao(true);
		}
		
		grupo.setIdLista(lista);
		grupo.setIdCategoria(categoria);
		grupo.setCategoria(categoria.getCategoria());

		grupo.setGrupoCod("13341256");
		grupo.setGrupoAdministracaoValor(zero);
		grupo.setGrupoCusto(zero);
		grupo.setGrupoValorIncideImposto(zero);
		grupo.setGrupoValorNaoIncideImposto(zero);
		grupo.setGrupoImpostoValor(zero);
		
		manager.persist(grupo);
		return "redirect:editaLinha?idGrupo="+grupo.getIdgrupo();
	}
	
	@RequestMapping("/atualizaDescricaoCategoria")
	public String atualizaDescricaoCategoria(Grupo grupo){
		Grupo grupo2 = manager.find(Grupo.class, grupo.getIdgrupo());
		
		Categoria categoria = manager.find(Categoria.class, grupo.getIdCategoriaTransiente());
		
		if(grupo.getIdgrupoCategoriaBayerTransiente() == null){

		}else{
			GrupoCategoriaBayer categoriaBayer = manager.find(GrupoCategoriaBayer.class, grupo.getIdgrupoCategoriaBayerTransiente());
			grupo2.setGrupoCategoriaBayer(categoriaBayer);
		}
		
		Lista lista = manager.find(Lista.class, grupo.getIdListaTransiente());
		
		grupo2.setIdLista(lista);
		grupo2.setGrupo(grupo.getGrupo());
		grupo2.setInformacoes(grupo.getInformacoes());
		grupo2.setNecessidades(grupo.getNecessidades());
		
		
		grupo2.setIncideAdministracao(grupo.isIncideAdministracao());
		
		if(grupo.isFeeReduzido() == true){
			grupo2.setIncideAdministracao(true);
		}
		
		grupo2.setFeeReduzido(grupo.isFeeReduzido());
		grupo2.setOpcional(grupo.isOpcional());
		
		// Setando valores no Grupo dos itens
		valoresEmGrupo.atualizaImpostoGrupo(grupo2);
		// Setando os valores na Lista 
//		valoresNaLista.InsereValoresLista(grupo, lista);
		valoresNaLista.calculaValorLista(lista.getIdLista());

		grupo2.setIdCategoria(categoria);
		
		manager.merge(grupo2);
		
		return "redirect:editaLinha?idGrupo="+grupo.getIdgrupo();
	}
	
	@RequestMapping("/atualizaConfiguracaoLista")
	public String atualizaConfiguracaoLista(Lista lista){
		
		
		if(lista.getFeeReduzidoTransiente() == null || lista.getFeeReduzidoTransiente() == ""){
			lista.setFeeReduzidoTransiente("7");
		}
		
		String feer = lista.getFeeReduzidoTransiente().replace(",", ".");
		String adm = lista.getAdministracaoTransiente().replace(",", ".");
		
		Lista lista2 = manager.find(Lista.class, lista.getIdLista());
		
		lista2.setAdministracao(new BigDecimal(adm));
		lista2.setFeeReduzido(new BigDecimal(feer));
		lista2.setMargemPadrao(lista.getMargemPadrao());
		lista2.setFormaPagamento(lista.getFormaPagamento());
		lista2.setMargemPadrao(new BigDecimal("0"));
		
		manager.merge(lista2);
		
		valoresEmGrupo.loopGrupoAtualizaImpostos(lista2.getIdLista());
		valoresNaLista.calculaValorLista(lista2.getIdLista());
		
		return "redirect:editaLista?idLista="+lista.getIdLista();	
	}
	
	@RequestMapping("/editaCategoriaConfiguracao")
	public ModelAndView editaCategoriaConfiguracao(/*Integer categoriaOrdem,*/String categoria,Integer idImpostoTrasiente,Integer idcategoria,
		Integer idListaTransiente,String impostoTitulo) throws InterruptedException, UnsupportedEncodingException{
		
		ModelAndView MV = new ModelAndView();
		  
		MV.setViewName("producao/configuracaoListaValores");
		Categoria categoriAtualizar = manager.find(Categoria.class, idcategoria);
		
		Imposto imposto = manager.find(Imposto.class, idImpostoTrasiente);
		Lista lista = manager.find(Lista.class, idListaTransiente);
		
		categoriAtualizar.setIdcategoria(idcategoria);
//		categoriAtualizar.setCategoriaOrdem(categoriaOrdem);
		categoriAtualizar.setCategoria(categoria);
		categoriAtualizar.setImposto(imposto.getImposto());
		categoriAtualizar.setImpostoTitulo(imposto.getImpostoTitulo());
		categoriAtualizar.setIdImposto(imposto);
		categoriAtualizar.setIdLista(lista);

		Categoria categoria1 = manager.find(Categoria.class, idcategoria);
		categoria1.setCategoriaOrdem(categoriAtualizar.getCategoriaOrdem());
		categoria1.setCategoria(categoria);
        categoria1.setImposto(categoriAtualizar.getImposto());
        categoria1.setImpostoTitulo(categoriAtualizar.getImpostoTitulo());

        categoria1.setIdImposto(imposto);
        categoria1.setIdLista(lista);
        
		manager.merge(categoria1);

		MV.addObject("categoria", listaDAO.listadeCategoriaPorId(categoriAtualizar.getIdcategoria()));
		
		valoresEmGrupo.loopGrupoAtualizaImpostos(idListaTransiente);
		
		valoresNaLista.calculaValorLista(idListaTransiente);
		
		new Thread();Thread.sleep(1000);  
	
		return MV;
	}
	
	@RequestMapping("/concluiPlanilha")
	@CacheEvict(value="listaProducao", allEntries=true)
	public ModelAndView concluiPlanilha(Integer idLista) throws InterruptedException{
		
		ModelAndView MV = new ModelAndView();
		MV.setViewName("producao/listaConcluida");

		Lista listaAtualizada = manager.find(Lista.class, idLista);
		ListaEstatus estatus = manager.find(ListaEstatus.class, 2);
		
		listaAtualizada.setIdlistaEstatus(estatus);
		manager.merge(listaAtualizada);
		
		MV.addObject("categoria", "Planilha Concluida com sucesso !");
		
		return MV;
	}
	
	@RequestMapping("/aprovaPlanilha")
	public ModelAndView aprovaPlanilha(Integer idLista) throws InterruptedException{
		
		ModelAndView MV = new ModelAndView();
		
		Lista listaAprovada = manager.find(Lista.class, idLista);
		ListaEstatus estatus = manager.find(ListaEstatus.class, 5);
		Usuario usuario = util.retornaUsuarioLogado();
		
		
		listaAprovada.setIdlistaEstatus(estatus);
		listaAprovada.setDataAprovacao(Calendar.getInstance());
		listaAprovada.setUsuarioAprova(usuario);
		
		manager.merge(listaAprovada);
		
		MV.setViewName("producao/listaConcluida");
		MV.addObject("categoria", "Planilha Aprovada com sucesso !");
		
		return MV;
	}
	
	
	
// ----------------------------------------------------- Exclusões --------------------------------------------------------------------------------- //
	
	@RequestMapping("/excluiCategoria")
	public String excluiCategoria(Integer idGrupo,Integer idLista){
		Grupo grupo = manager.find(Grupo.class, idGrupo);
		Integer idCateg = grupo.getIdCategoria().getIdcategoria();
		
		/**
		 * Verifica se o item tem alguma referência em Criação. 
		 * Se tiver, vai setar o idGrupo do Item da criação como NULL,
		 * tirando a referencia, mas mantendo o item com seu histórico.
		 * Mudará tambem o status do item para "Excluido do Job".
		 * 
		 */
		if(grupo.isCriacao() == true){
			CriacaoItemStatus excluidoJob = manager.getReference(CriacaoItemStatus.class, 4);
			
			System.out.println("Tenho referencia em Criação");
			Query q = manager.createQuery("from CriacaoItemLista where idGrupo="+idGrupo);
			List<CriacaoItemLista> itemLista = q.getResultList();
			
			for(int i =0; i < itemLista.size();i++){
				CriacaoItemLista lista = manager.find(CriacaoItemLista.class, itemLista.get(i).getIdCriacaoItemLista());
				lista.setGrupo(null);
				if(lista.getApres() != null){
					lista.setCriacaoItemStatus(excluidoJob);
				}
				
				manager.merge(lista);
			}
		
		}else{
			System.out.println("Não tenho");
		}
		

		// Excluir todos os produtos do Grupo
		producaoDAO.excluiVariosProdutosGrupo(idGrupo); 
		
		
		//Exclui o Grupo
		manager.remove(grupo);
		
		// Excluir orcamento do Grupo
		try {
			TypedQuery<OrcamentoFornecedor> orc = manager.createQuery("from OrcamentoFornecedor where grupo.idgrupo="+idGrupo,OrcamentoFornecedor.class);
			OrcamentoFornecedor orcamento = orc.getSingleResult();
			manager.remove(orcamento);
		} catch (Exception e) {
		    
		}

		//Recalcula os Valores e insere na Lista
 	    valoresNaLista.calculaValorLista(idLista);
 	    
 	    //Verifica se é o último item dessa categoria, se for excluir Categoria
 	    String consulta = "FROM Grupo where idCategoria = "+ idCateg;
 	    Query q = manager.createQuery(consulta);
 	    
 	    if(q.getResultList().isEmpty()){
 	    	Categoria c = manager.getReference(Categoria.class, idCateg);
 	    	manager.remove(c);
 	    }else{
 	    	//JOptionPane.showMessageDialog(null, "Ainda tem mais grupos aqui !!!!");
 	    }
 	    
		return "redirect:editaLista?idLista="+idLista+"#ac"+idCateg;
	}
	
// ----------------------------------------------------- Duplicar Planilha  ----------------------------------------------------------------------------------- //
	
	@RequestMapping("/duplicaPlanilha")
	@CacheEvict(value="listaProducao", allEntries=true)
	public ModelAndView duplicaPlanilha(Integer idLista){
		Lista lista = manager.find(Lista.class, idLista);
		ListaEstatus listaEstatus = manager.find(ListaEstatus.class, 1);
		
		ModelAndView MV = new ModelAndView();
		
		// Código Modificado para o modelo de
		// Quando for duplicar a lista ele assumirá o codigo do job
		// mais regra para LP normalmente.
		String codLista = util.montaCodigoDuplicaLista(lista.getIdJob().getIdJob(), lista.getIdJob().getCodJob());
		
		Lista listaDuplicada = new Lista();
		
		listaDuplicada = lista;
		listaDuplicada.setRevisao(0);
		listaDuplicada.setIdLista(null);
		listaDuplicada.setIdlistaEstatus(listaEstatus);
	    listaDuplicada.setListaCod(codLista);

		manager.detach(listaDuplicada);
		manager.persist(listaDuplicada);
		Integer  idNovaLista = listaDuplicada.getIdLista();
		producaoDAO.clonaCategoria(idLista,listaDuplicada);

		manager.clear();
		Lista novalista = manager.find(Lista.class, idNovaLista);
		
        MV.setViewName("producao/listaDuplicada");
		MV.addObject("idNovaLista", novalista.getIdLista());
        return MV;
	}

// ----------------------------------------------------- Revisar Planilha  ----------------------------------------------------------------------------------- //
	
		@RequestMapping("/revisaPlanilha")
		@CacheEvict(value="listaProducao", allEntries=true)
		public ModelAndView revisaPlanilha(Integer idLista){
			
			
			ModelAndView MV = new ModelAndView();
			MV.setViewName("producao/listaRevisada");
			
			Lista lista = manager.find(Lista.class, idLista);
			
			
			Integer idListaAntiga = lista.getIdLista();
			ListaEstatus listaEstatusEmAberto = manager.find(ListaEstatus.class, 1);
			
			String codLista = util.montaCodigoLista(lista.getIdJob().getIdJob());
			
			Lista listaDuplicada = new Lista();
			
			listaDuplicada = lista;
			listaDuplicada.setRevisao(lista.getRevisao()+1);
			listaDuplicada.setIdLista(null);
			listaDuplicada.setIdlistaEstatus(listaEstatusEmAberto);
			listaDuplicada.setListaCod(lista.getListaCod());

			manager.detach(listaDuplicada);
			manager.persist(listaDuplicada);
			
			producaoDAO.clonaCategoria(idLista,listaDuplicada);
			producaoDAO.mudaEstatusLista(idLista);

			verificaSeListaExiste(listaDuplicada.getIdLista(),idListaAntiga,codLista);
			
	        MV.addObject("idNovaLista", listaDuplicada.getIdLista());
	        
	        
// ------------------------------------------------------------------------- //	        

	      //Verifica se tem referência em filha 
	        try {
	        	String consultaFilha = "from CenariosGalderma where planilhaFilha = "+ idLista;
	        	TypedQuery<CenariosGalderma> cnf = manager.createQuery(consultaFilha, CenariosGalderma.class);
	        	CenariosGalderma cenarios = cnf.getSingleResult();
	        	System.out.println("É filha, atualizando");

	        	cenarios.setPlanilhaFilha(listaDuplicada.getIdLista());
	        	manager.merge(cenarios);
	        	
	        	Lista listaAtual = manager.find(Lista.class, idLista);
	        	listaAtual.setNumCenarioGalderma(null);
	        	manager.merge(listaAtual);
	        	
	        	System.out.println("Atualizado com sucesso.");
	        	// Se true
	        	//atualizar para id da lista duplicada
			} catch (Exception e) {
				System.out.println("Não é filha.");
				try {
					String consultaMae = "from CenariosGalderma where planilhaMae = "+ idLista;
					TypedQuery<CenariosGalderma> cnf = manager.createQuery(consultaMae, CenariosGalderma.class);
					List<CenariosGalderma> cenariosMae = cnf.getResultList();
					
					if(cenariosMae.isEmpty()){
						//se false, não fazer nada
						System.out.println("Não é mãe, nada a fazer");
					}else{
						//verificar em referência em mae
						//se true, pegar lista de todos os idCenarios a atualizar idsCenariosMae
						System.out.println("É mãe, atualizando registros.");
						for (int i = 0; i < cenariosMae.size(); i++) {
							cenariosMae.get(i).setPlanilhaMae(listaDuplicada.getIdLista());
							manager.merge(cenariosMae.get(i));
							System.out.println("Mãe atualizado com sucesso.");
						}
						
						Lista listaAtual = manager.find(Lista.class, idLista);
			        	listaAtual.setNumCenarioGalderma(null);
			        	manager.merge(listaAtual);
						
						
					}
				} catch (Exception e2) {
					//se false, não fazer nada
				}
			}
	        
// ------------------------------------------------------------------------- //	        
	        return MV;
		}	
	
	
// ----------------------------------------------------- Regras  ----------------------------------------------------------------------------------- //
	
	
	@RequestMapping("/infoProdutos")
	@ResponseBody
	public String infoProduto(Integer idproduto, boolean incideimpostoCheckbox,String custoItemunitario,Integer idGrupo){
		

		Gson gson = new Gson();
		DetalhesItemProducao detalhesItem = new DetalhesItemProducao();
		Produto produto = manager.find(Produto.class, idproduto);
		String custo = produtoDAO.custoproduto(idproduto).toString();
		BigDecimal custoItemUnitario = new BigDecimal(custo); 
		
		
		if(incideimpostoCheckbox == true){
			//Calcula custo + formula
			
			//Pega Imposto da categoria pelo id do Grupo e passa para o método calcular
			Grupo imposto = producaoDAO.pegaIdcategoriaPorIdGrupo(idGrupo);
			String imposto2 = imposto.getIdCategoria().getImposto().toString();
			
			//Passa os valores de imposto e custo unitario para o metodo calcular o valor com imposto
			String resultadoFinalConverte = util.formulaParaCalculoImposto(imposto2, custoItemUnitario);
	        resultadoFinalConverte = util.ConverteDolarParaReal(resultadoFinalConverte);
	        detalhesItem.setResultadoFinalComImpostoCalculado(resultadoFinalConverte);
			detalhesItem.setUnidade(produto.getUnidade().getUnidade());
		}else{
			//Apenas custo
			String custoItemFinal = custoItemUnitario.toString();
			detalhesItem.setResultadoFinalComImpostoCalculado(util.ConverteDolarParaReal(custoItemFinal));
			detalhesItem.setUnidade(produto.getUnidade().getUnidade());
		}

		
        String convertido = util.ConverteDolarParaReal(custo);
		detalhesItem.setCusto(convertido);

		return gson.toJson(detalhesItem);
	}
	
	@RequestMapping("/calculaValorTotal")
	@ResponseBody
	public String calculaValorTotal(String qtd1,String qtd2,String diarias,String custoItemunitario,
		String precoUnitFinal,String bvFornecedorValorEdita){
		
		
		if(custoItemunitario.equals("0")){
			custoItemunitario = "0,00";
		}
		
		DetalhesItemProducao detalhes = new DetalhesItemProducao();
		
		BigDecimal custoTotal = new BigDecimal(util.formataValores(precoUnitFinal)); 
		BigDecimal q1 = new BigDecimal(utilConv.converterVirgulaParaPonto(qtd1));
		BigDecimal q2 = new BigDecimal(utilConv.converterVirgulaParaPonto(qtd2));
		BigDecimal ds = new BigDecimal(utilConv.converterVirgulaParaPonto(diarias));
		
		// Valor Final
        String convertido = calculaValores.calculaValorFinal(custoTotal, q1, q2, ds,precoUnitFinal);
        detalhes.setCustoTotalQuantidadePordiaria(convertido); 

		// ???????
		detalhes.setCustoToTalNSF(calculaValores.naoSei(custoItemunitario, custoTotal, q1, q2, ds, precoUnitFinal));
 	
		// Custo Total
		detalhes.setCustounitarioTotalconvertido(calculaValores.custoTotal(custoItemunitario, q1, q2, ds));
        
        //Valor s/ NF
        BigDecimal custounitar = new BigDecimal(util.formataValores(custoItemunitario));
        BigDecimal bvFornecedorValoTotal = new BigDecimal(util.formataValores(bvFornecedorValorEdita));
        bvFornecedorValoTotal = bvFornecedorValoTotal.add(custounitar);
        bvFornecedorValoTotal = bvFornecedorValoTotal.multiply(q1.multiply(q2).multiply(ds));
        String bvFornecedorValorFinal = bvFornecedorValoTotal.toString();
        bvFornecedorValorFinal = util.ConverteDolarParaReal(bvFornecedorValorFinal);
        detalhes.setResultadoFinalSNF(bvFornecedorValorFinal);
        
        // [ BV valor ]
        BigDecimal valorBv = new BigDecimal(util.formataValores(bvFornecedorValorEdita));
        valorBv = valorBv.multiply(q1.multiply(q2).multiply(ds));
        String bvValorFinal = valorBv.toString();
        bvValorFinal = util.ConverteDolarParaReal(bvValorFinal);
        detalhes.setBvValor(bvValorFinal);
        
        // Converte quantidade decimal String ( 10,2 ex ) para double 
        double testeQtd1= Double.parseDouble(utilConv.converterVirgulaParaPonto(qtd1));
        double testeQtd2= Double.parseDouble(utilConv.converterVirgulaParaPonto(qtd2));
        double QuantidadeTotal = testeQtd1 * testeQtd2;
        
        // formata para apresentar duas casas depois da vírgula
        DecimalFormat df = new DecimalFormat("#.00");
        
        detalhes.setQtdxqtd(df.format(QuantidadeTotal));
        
        Gson gson = new Gson();

 		return gson.toJson(detalhes);
	}
	

	@RequestMapping("/calculaValorSNF")
	@ResponseBody
	public String calculaValorSNF(String bvFornecedorValorEdita,String custoItemunitario,boolean incideimpostoCheckbox,Integer idGrupoHidden){
		
		if(custoItemunitario.equals("0")){
			custoItemunitario = "0,00";
		}
		
		
		DetalhesItemProducao detalhes = new DetalhesItemProducao();
		
		BigDecimal custoSNFItemUnitario = new BigDecimal(util.formataValores(custoItemunitario)); 
		BigDecimal custoSNFbvFornecedor = new BigDecimal(util.formataValores(bvFornecedorValorEdita)); 
		BigDecimal custoSNF = custoSNFItemUnitario.add(custoSNFbvFornecedor); 
		
		String convertido = custoSNF.toString();
        convertido = util.ConverteDolarParaReal(convertido);
        detalhes.setCustoTotalSFNCalculado(convertido);
        
        String somaPelaDiferencaConvertido = custoSNFbvFornecedor.toString();
        somaPelaDiferencaConvertido = util.ConverteDolarParaReal(somaPelaDiferencaConvertido);
        detalhes.setCustouniTiraBv(somaPelaDiferencaConvertido);
        
        
           //Pega Imposto da categoria pelo id do Grupo e passa para o método calcular
      		Grupo imposto = producaoDAO.pegaIdcategoriaPorIdGrupo(idGrupoHidden);
      		String imposto2 = imposto.getIdCategoria().getImposto().toString();

      		if(incideimpostoCheckbox == true){

	        String resultadoFinalConverte = util.formulaParaCalculoImposto(imposto2, custoSNF);
	        resultadoFinalConverte = util.ConverteDolarParaReal(resultadoFinalConverte);
	        detalhes.setResultadoFinalComImpostoCalculado(resultadoFinalConverte);

      		}else{
	        detalhes.setResultadoFinalComImpostoCalculado(convertido);	
        }
        
        Gson gson = new Gson();

 		return gson.toJson(detalhes);

	}
	
	@RequestMapping("/CalculaValoresLista")
	@ResponseBody
	public String CalculaValoresLista(Integer idLista){
   	   Query query = manager.createQuery("from Lista where idLista="+ idLista);
	   Lista lista = (Lista) query.getSingleResult(); 	
		DetalhesItemProducao detalhesValorLista = new DetalhesItemProducao();	
		Gson gson = new Gson();
		
		detalhesValorLista.setValorTotalImpostoLista(lista.getImpostoValor());
		detalhesValorLista.setValorTotalLista(lista.getValorTotal());
		return gson.toJson(detalhesValorLista);
	}

	@RequestMapping("/CalculaValoresListaAjax")
	@ResponseBody
	public String CalculaValoresListaAjax(Integer idLista){
		
	   Query query = manager.createQuery("from Lista where idLista="+ idLista);
	   Lista lista = (Lista) query.getSingleResult(); 	
		DetalhesItemProducao detalhesValorLista = new DetalhesItemProducao();	
		Gson gson = new Gson();
		
		BigDecimal admValor = lista.getAdministracaoValor();
		BigDecimal imposto = lista.getImpostoValor();
		BigDecimal valort = lista.getValorTotal();
		
		
		String admValorConvertido = util.ConverteDolarParaReal(admValor.toString());
		String impostoConvertido = util.ConverteDolarParaReal(imposto.toString());
		String valorTotalConvertido = util.ConverteDolarParaReal(valort.toString());

		
		
		detalhesValorLista.setAdministracaoValor(admValorConvertido);
		detalhesValorLista.setImpostoValor(impostoConvertido);
		detalhesValorLista.setValorTotal(valorTotalConvertido);
		
		return gson.toJson(detalhesValorLista);
	}

    public void verificaSeListaExiste(Integer idListaNova,Integer idListaAntiga, String CodLista){ 
    		Lista lista = manager.getReference(Lista.class, idListaNova);
    			
    		List<CriacaoLista>listaCriacao = new ArrayList<CriacaoLista>();
    		
    		try {
    			String consulta = "select c from CriacaoLista c join fetch c.listaProducao join fetch c.criacaoItemLista where idListaProducao="+idListaAntiga;
    			listaCriacao = criacaoDAO.ListaDeObjetos(consulta);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro: "+e);
			}
			
			String consultaGrupo = "SELECT g FROM Grupo g where idLista ="+idListaNova+" and criacao= 1";
			List<Grupo> grupos= criacaoDAO.ListaDeGrupos(consultaGrupo);
			
			if(listaCriacao.isEmpty()){ // Se lista vazia
			//	JOptionPane.showMessageDialog(null, "Está Vazia\nidListaAntiga: "+idListaAntiga+"\nidListaNova: "+idListaNova);
			}else{

					Integer idCriacaoLista = listaCriacao.get(0).getIdCriacaoLista();
					String consultaItens = "SELECT c from CriacaoItemLista c where idCriacaoLista="+idCriacaoLista;
					List<CriacaoItemLista> itens= criacaoDAO.ListaDeItens(consultaItens);

					String consultaListaAntiga="select c from CriacaoLista c where idListaProducao="+idListaAntiga;
					TypedQuery<CriacaoLista> query = manager.createQuery(consultaListaAntiga, CriacaoLista.class);
					CriacaoLista listaCriacaoAtualiza = query.getSingleResult();
					
					listaCriacaoAtualiza.setListaProducao(lista);
					manager.detach(listaCriacaoAtualiza);
					manager.merge(listaCriacaoAtualiza);
					
					for (CriacaoItemLista criacaoItemLista : itens) {
						for (Grupo gp : grupos) {
							if(gp.getGrupo().equals(criacaoItemLista.getTituloItem())){
								criacaoItemLista.setInformaoesItem(
								lista.getListaCod()+" ."+lista.getRevisao()
												   +"<br>"+gp.getInformacoes()
												   +"<br>-----------------<br>"
												   +criacaoItemLista.getInformaoesItem());
								manager.merge(criacaoItemLista);
							}
						}
					}
 	             }
	}
    
    @RequestMapping("/copiaLista")
    @CacheEvict(value="listaProducao", allEntries=true)
    public ModelAndView copiaLista(Integer idListaParaCopiar,String tituloLista, Integer selectJobsEmpresa){
    	
    	ModelAndView MV = new ModelAndView();
    	
    	Lista listaParaCopiar = manager.find(Lista.class, idListaParaCopiar);
    	Job job = manager.getReference(Job.class, selectJobsEmpresa);
    	ListaEstatus listaEstatus = manager.getReference(ListaEstatus.class, 1);

    	User user = manager.find(User.class, util.usuarioLogado());
    	
    	Lista novaLista = new Lista();
    	novaLista = listaParaCopiar;
    	String codLista = util.montaCodigoLista(job.getIdJob());
    	
    	novaLista.setIdLista(null);
    	novaLista.setLista(tituloLista);
    	novaLista.setListaCod(codLista);
    	novaLista.setIdJob(job);
    	novaLista.setIdlistaEstatus(listaEstatus);
    	novaLista.setRevisao(0);
    	novaLista.setUser(user);
    	
    	manager.detach(listaParaCopiar);
    	manager.persist(novaLista);
    	
    	producaoDAO.clonaCategoria(idListaParaCopiar,novaLista);
    
    	MV.setViewName("producao/listaCopiada");
		MV.addObject("idNovaLista", novaLista.getIdLista());
    	
    	return MV;
    }
    
    @RequestMapping("/carregaListaOrdenar")
    public ModelAndView carregaListaOrdenar(Integer idLista){
    	
    	ModelAndView MV = new ModelAndView();
    	
    	MV.setViewName("producao/ordenaLista");
    	
		MV.addObject("categoria", listaDAO.listasPorIdLista(idLista));
		MV.addObject("idLista", idLista);
    	return MV;
    }
    
    
    @RequestMapping("/ordenar")
	public String ordenar(String id, Integer idLista, RedirectAttributes rd){
		
		String[] retornoSplit = id.split(",");
		
		for( int i = 0; i < retornoSplit.length; i++){
			
			Integer ids = Integer.parseInt(retornoSplit[i]);
			listaDAO.mergeCategoria(ids, i, idLista);
	   }
		
	   rd.addFlashAttribute("sucesso", "Item Salvo com sucesso");	
		
	return "redirect:carregaListaOrdenar?idLista ="+idLista;
	}
    
    
    @RequestMapping("/carregaListaGruposOrdenar")
    public ModelAndView carregaListaGruposOrdenar(Integer idLista){
    	
    	ModelAndView MV = new ModelAndView();
    	
    	MV.setViewName("producao/ordenaGrupos");
    	
    	MV.addObject("categoria", listaDAO.listadeCategorias(idLista));
		MV.addObject("grupoOrdem", listaDAO.listadeGruposPorIdLista(idLista));
		MV.addObject("idLista", idLista);
    	return MV;
    }
    
    @RequestMapping("/ordenarGrupos")
	public ModelAndView ordenarGrupos(String id, Integer idLista){
    	
    	ModelAndView MV = new ModelAndView();
    	
    	MV.setViewName("msg/ok");
    	String[] retornoSplit = id.split(",");
		
		for( int i = 0; i < retornoSplit.length; i++){
			Integer ids = Integer.parseInt(retornoSplit[i]);
            listaDAO.mergeGrupo(ids, i);
		}
	return MV;
	}
    
    
    @RequestMapping("/geraCenarioGalderma")
    @CacheEvict(value="listaProducao", allEntries=true)
    public String geraCenarioGalderma(Integer idLista){
    	
	    Integer retorno =	producaoDAO.verificaSePlanilhaMae(idLista);

	    return "redirect:editaLista?idLista="+retorno;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
