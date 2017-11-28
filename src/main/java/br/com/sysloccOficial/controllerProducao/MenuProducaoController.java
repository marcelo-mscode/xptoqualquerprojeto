package br.com.sysloccOficial.controllerProducao;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaConversoes;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.conf.UtilitariaValores;
import br.com.sysloccOficial.controllerProducao.carta.ApoioCartaDAO;
import br.com.sysloccOficial.controllerProducao.carta.GeraCartaWord;
import br.com.sysloccOficial.controllerProducao.carta.GeraOutroWordCarta;
import br.com.sysloccOficial.controllerProducao.carta.MenuProducaoControllerCarta;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.JobDAO;
import br.com.sysloccOficial.daos.ListaDAO;
import br.com.sysloccOficial.daos.LocalEventoDAO;
import br.com.sysloccOficial.daos.MenuProducaoDAO;
import br.com.sysloccOficial.daos.PagamentoDAO;
import br.com.sysloccOficial.daos.ProducaoDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.CadastroLocco;
import br.com.sysloccOficial.model.CartaContratacao;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.LocalEvento;
import br.com.sysloccOficial.model.ProducaoValoresGJson;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.Usuario;
import br.com.sysloccOficial.model.producao.CartaContFornecedor;
import br.com.sysloccOficial.model.producao.FornecedorFinanceiro;
import br.com.sysloccOficial.model.producao.ObjetoApoioValorPgto;
import br.com.sysloccOficial.model.producao.ProducaoP;

import com.google.gson.Gson;


@Controller
@Transactional
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class MenuProducaoController extends AuxProducao{
	
	@Autowired private EmpresaDAO empresaDAO;
	@Autowired private JobDAO jobDAO;
	@Autowired private ListaDAO listaDAO;
	@Autowired private PagamentoDAO pagamentoDAO;
	@Autowired private UsuarioDAO usuarioDAO;
	@Autowired private ProducaoDAO producaoDAO;
	@Autowired private MenuProducaoDAO menuProducaoDAO;
	@Autowired private LocalEventoDAO localEventoDAO;
	@Autowired private ContatoDAO contatoDAO;
	@Autowired private AuxDAOProducao auxDAOProducao;
	@Autowired private Utilitaria util;
	@Autowired private UtilitariaValores utilValores;
	@Autowired private UtilitariaDatas utilDatas;
	@Autowired private MenuProducaoControllerCarta menuCarta;
	@Autowired private UtilitariaConversoes utilConversoes;
	@Autowired private GeraCartaWord geraCartaWord;
	@Autowired private GeraOutroWordCarta geraOutroCartaWord;
	@Autowired private ApoioCartaDAO apoioCarta;
	
	ModelAndView MV = new ModelAndView();
	
	@PersistenceContext	private EntityManager manager;
	
	@RequestMapping("/menuProducao")
	public ModelAndView menuProducao() {
		ModelAndView MV = new ModelAndView("menuProducao/menuProducao");
		
		List<Date> datas = new ArrayList<Date>();
		
		LinkedHashSet<Lista> listas = new LinkedHashSet<Lista>(listaDAO.listaMenuProducao());
		
		
		
		Iterator<Lista> iterador = listas.iterator();
		
		while(iterador.hasNext()){
			
			if(iterador.next().getDataDoEvento() != null)
				datas.add(iterador.next().getDataDoEvento().getTime());
		}
		
		
	
		
		
		MV.addObject("lista", listas);
		MV.addObject("datas", datas);
		
		
		
		
		return MV;
	}
	
	@RequestMapping("/itensProducaoFornecedor")
	public ModelAndView itensProducaoFornecedor() {
		
		MV.setViewName("menuProducao/itensListaFornecedor");
		return MV;
	}
	
	@RequestMapping("/itemListaAjax")
	public ModelAndView itemListaAjax(Integer idFornecedor,Integer idLista, Integer idProdutoGrupo) throws InterruptedException{
		
		Lista lista = manager.find(Lista.class, idLista);
		
		ProdutoGrupo produtoGrupo = manager.find(ProdutoGrupo.class, idProdutoGrupo);
		
		MV.addObject("fornecedor", empresaDAO.infoEmpresaProducao(produtoGrupo.getEmpresa().getIdEmpresa()));
		MV.addObject("contatoFornecedor", empresaDAO.listaContatoEmpresa(idFornecedor));
		MV.addObject("tipoPagamento", pagamentoDAO.listaTipoPagamento());
		MV.addObject("usuarios", usuarioDAO.usuariosProducao());
		MV.addObject("idLista", idLista);
		MV.addObject("idProdutoGrupo", idProdutoGrupo);
		MV.addObject("produtoGrupo", produtoGrupo);
	    MV.addObject("parcelasPadrao", qtsParcelasPadrao());
	    MV.addObject("fornecedoresLista", empresaDAO.listaFornecedores());
	    MV.addObject("InfoJobs",localEventoDAO.ultimoLocalEvento(lista.getIdJob().getIdJob()));
	    MV.addObject("lista", lista);
		
		verificaSeItemSeraSalvoOuAtualizado(idFornecedor, idLista, idProdutoGrupo);
	    
		return MV;
	}

	private void verificaSeTemCartaContratacao(Integer idFornecedor, Integer idLista, Integer idProdutoGrupo) {
		try {
	    	
	    	CartaContFornecedor cc = apoioCarta.verificaSeexistiCarta(idFornecedor, idProdutoGrupo);
	    	MV.addObject("CartaNovaTeste", cc);

	    	MV.addObject("outraCarta", manager.find(CartaContratacao.class, 1));
			
			MV.addObject("dataHoje", utilDatas.dataHojeFormatada());
		    
		    List<ProducaoP> listaItensMesmoFornecedor = producaoDAO.pegaListaItensDoMesmoFornecedor(idFornecedor,idLista);
		    
		    MV.addObject("fProducao", listaItensMesmoFornecedor);
		    
		    BigDecimal totalImposto = somaApenasValoresImpostoComContratacao(listaItensMesmoFornecedor);
		    String compara = totalImposto.toString();
		    
		    if(compara.equals("0") || compara == "0"){
		    	
		    }else{
		    	MV.addObject("somaImposto", totalImposto);
		    }
		    
		    String idsProducaoDoFornecedor = apoioCarta.pegaIdsProducaoPComFornecedorRelacionado(idFornecedor, idLista);
		    
		    String idsFornecedorFinanceiro = apoioCarta.PegaIDFornecedorFinanceiroComProducaoPRelacionado(idsProducaoDoFornecedor, idFornecedor);
		    
		    List<Integer> diasPrazo = producaoDAO.pegaDiasPagamentoDeUmFornecedor(idsFornecedorFinanceiro);
		    
		    /**
		     * Monta a lista de pagamento para condiçoes Pagamento
		     */
		    List<ObjetoApoioValorPgto> pgtoLista = new ArrayList<ObjetoApoioValorPgto>();
		    Integer parcelado = 0;
		    parcelado = montaListaPagamento(idsFornecedorFinanceiro, diasPrazo, pgtoLista, parcelado);
		    
		    MV.addObject("condicaoPagamentos", pgtoLista);

		    MV.addObject("parceladoEm", parcelado);
	    
	    } catch (Exception e) {
	    	/*System.out.println(e);*/
		}
	}

	private void verificaSeItemSeraSalvoOuAtualizado(Integer idFornecedor, Integer idLista, Integer idProdutoGrupo) {
		
		try {
			atualizaProducao(idFornecedor, idLista, idProdutoGrupo);
			MV.setViewName("menuProducao/detalhesItensProducao");
			verificaSeTemCartaContratacao(idFornecedor, idLista, idProdutoGrupo);
			apoioCarta.verificaSetemCartaContrataçãoOutroFornecedor(idFornecedor, idLista, idProdutoGrupo,MV);
			
		} catch (Exception e) {
			System.out.println("Não tem registro, vai salvar"+e);
			MV.setViewName("menuProducao/salvaItem/salvaItensProducao");
		}
	}

	
	
	private Integer montaListaPagamento(String idsFornecedorFinanceiro, List<Integer> diasPrazo,List<ObjetoApoioValorPgto> pgtoLista, Integer parcelado) {

		for(int i =0;i < diasPrazo.size();i++){
			ObjetoApoioValorPgto pgto = new ObjetoApoioValorPgto();
			parcelado = parcelado+1;
			pgto.setParcela(i+1);
			pgto.setDias(diasPrazo.get(i)+" Dias do Evento");
			pgto.setDataPagar(PegaDatapagamento(idsFornecedorFinanceiro, diasPrazo.get(i)));
			
			BigDecimal val = somavalorFornecedor(idsFornecedorFinanceiro, diasPrazo.get(i));
			
			String vvv = utilValores.ConverteDolarParaReal(val.toString());
			
			BigDecimal v = new BigDecimal(utilValores.formataValores(vvv));
			String  valorExtenso= utilValores.converteValoresPorExtenso(v);
			
			pgto.setValorPagar(vvv+"&nbsp&nbsp( "+valorExtenso+" )");
			
			pgtoLista.add(pgto);
		}
		return parcelado;
	}

/* -------------------------------------------------------------------------------------------------------------- */	
// Método antigo	
/*	private BigDecimal somavalorFornecedor(String ffd, Integer diasPrazo) {
		String sss = "select sum(valor) FROM ValorPagtoFornecedor"
				   + " where idFornecedorFinanceiro in ("+ffd+") and diasPrazoParaPagamento ="+ diasPrazo;
		Query qsss = manager.createQuery(sss);
		BigDecimal sumoa = (BigDecimal) qsss.getSingleResult();
		return sumoa;
		
	}
*/
/* -------------------------------------------------------------------------------------------------------------- */	

	
	private BigDecimal somavalorFornecedor(String idsFornecedorFinanceiro, Integer diasPrazo) {
		String sss = "select sum(valor) FROM ValorPagtoFornecedor"
				+ " where idFornecedorFinanceiro in ("+idsFornecedorFinanceiro+") and diasPrazoParaPagamento ="+ diasPrazo;
		Query qsss = manager.createQuery(sss);
		BigDecimal sumoa = (BigDecimal) qsss.getSingleResult();
		return sumoa;
		
	}

	private Date PegaDatapagamento(String idsFornecedorFinanceiro, Integer diasPrazo) {
		String consulta = "select idValorFinancForn FROM ValorPagtoFornecedor"
				+ " where idFornecedorFinanceiro in ("+idsFornecedorFinanceiro+") and diasPrazoParaPagamento ="+ diasPrazo;
		TypedQuery<Integer> qsss = manager.createQuery(consulta, Integer.class).setMaxResults(1);
		
		String s = "select dataPagar FROM DtPgtoFornecedor where idValorPgForn ="+qsss.getSingleResult();
		
		TypedQuery<Date> data = manager.createQuery(s,Date.class);
		
		Date d = data.getSingleResult();
		
		return d;
	}

	public ModelAndView atualizaProducao(Integer idFornecedor,Integer idLista, Integer idProdutoGrupo){
		
		ProdutoGrupo produtoGrupo = manager.find(ProdutoGrupo.class, idProdutoGrupo);
		
		/**
		 * 
		 * Aqui ele vai verificar se o produtoGrupo tem alguma referencia em ProducaoP
		 * Se tiver indica que já tem um registro salvo para esse produto em ProducaoP
		 * Se não tiver indica que esse item deverá ser salvo, e a lógica abaixo não deverá 
		 * ser executada 
		 * 
		 */
		Integer teste = produtoGrupo.getProducaop().getIdProducao();
		/**
		 * 
		 * Caso a atribuição acima falhe, a lógica abaixo não será executada
		 * 
		 */
		
			
		ProducaoP pp = menuProducaoDAO.pegaProducao(teste);
		MV.addObject("producaoP", pp);

		Set<FornecedorFinanceiro> f = menuProducaoDAO.pegaFornecedorFinanceiro(idProdutoGrupo,produtoGrupo.getEmpresa().getIdEmpresa() );
		
		MV.addObject("fornecedorFinanceiro", f);

		if(pp.isTemContratacao() ==  true && pp.isTemOutroFornecedor() == true){
			//idProducao  idteste
			// idEmpresa produtoGrupo.getEmpresa().getIdEmpresa()
			String consultaOutroFornecedor = "from FornecedorFinanceiro where idProducao = "+ teste+ " and idEmpresa <> "+ produtoGrupo.getEmpresa().getIdEmpresa();
			Query q = manager.createQuery(consultaOutroFornecedor);
			FornecedorFinanceiro outro = (FornecedorFinanceiro) q.getSingleResult();
			
			MV.addObject("IdOutroFornecedor", outro.getIdEmpresa().getIdEmpresa());
			MV.addObject("OutroFornecedor", outro.getIdEmpresa().getEmpresa());
			MV.addObject("OutroImposto", outro.getImposto());
			MV.addObject("OutroValor", outro.getIdValorPgtoFornecedor().get(0).getValor());
			MV.addObject("OutroDataPgto", outro.getIdValorPgtoFornecedor().get(0).getDtPgotFornecedor().getDataPagar());
		}
		

		try {
	    	CartaContFornecedor cc = apoioCarta.verificaSeexistiCarta(idFornecedor, idProdutoGrupo);
	    	MV.addObject("CartaNovaTeste", cc);
		
	    } catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    
		MV.addObject("outraCarta", manager.find(CartaContratacao.class, 1));
		MV.addObject("dataHoje", utilDatas.dataHojeFormatada());
	    String tst = "from ProducaoP p where idlista = "+idLista+" and idEmpFornecedor= "+idFornecedor;
	    Query qw = manager.createQuery(tst);
	    List<FornecedorFinanceiro> ff = qw.getResultList();
	    MV.addObject("fProducao", ff);

	    return MV;
	}
	
	private ArrayList<Integer> qtsParcelasPadrao() {
		ArrayList<Integer> parcelasPadrao = new ArrayList<Integer>();
	    
	    for(int i =0;i <= 12;i++){
	    	parcelasPadrao.add(i, i);
	    }
		return parcelasPadrao;
	}
	
	@RequestMapping("/fechaItem")
	public String fechaItem(Integer idDoProdutoGrupo, Integer idLista){
		auxDAOProducao.mudaStatusProdutoGrupo(idDoProdutoGrupo);
		auxDAOProducao.mudaStatusItemProducaoParaItemFechado(idLista,idDoProdutoGrupo);
		return "redirect:itensProducao?idLIsta="+idLista;
	}

	@RequestMapping("/modalTrocarFornecedor")
	public ModelAndView modalTrocarFornecedor(Integer idProdutoGrupo){
		
		ModelAndView MVv = new ModelAndView("menuProducao/salvaItem/mudaFornecedor");
		
		MVv.addObject("fornecedoresLista", empresaDAO.listaFornecedores());
		MVv.addObject("idProdutoGrupo", idProdutoGrupo);
		
	
	   return MVv;	
	}
	@RequestMapping("/trocarFornecedor")
	public ModelAndView trocarFornecedor(Integer idFornecedor,Integer idProdutoGrupo){
		
		MV.setViewName("menuProducao/fornecedorAjax");
		MV.addObject("fornecedor", empresaDAO.infoEmpresaProducao(idFornecedor));
		
		ProdutoGrupo produtoGrupo = manager.find(ProdutoGrupo.class, idProdutoGrupo);
		Empresa empresa = manager.getReference(Empresa.class, idFornecedor);
		produtoGrupo.setEmpresa(empresa);
		manager.merge(produtoGrupo);
		
	   return MV;	
	}
	
	
	
	
	@RequestMapping("/calculaPrazo")
	public ModelAndView calculaPrazo(Integer diasPrazo,Integer idLista,String nameInput){
		
		MV.setViewName("menuProducao/vencimentoAjax");
		
		Lista lista = manager.getReference(Lista.class, idLista);
		LocalEvento localEvento = localEventoDAO.ultimoLocalEvento(lista.getIdJob().getIdJob());
		
//		Date cs = localEvento.getLocalEventoDataTermino();
		
		try {
			Date cs = utilDatas.SomaDias(localEvento.getLocalEventoDataTermino(), diasPrazo);
			MV.addObject("dataPrazo", cs);
		} catch (Exception e) {
			Date cs = null;
		}
		
		
		
		MV.addObject("nameInput", nameInput);
		
		return MV;
	}
	
	@RequestMapping("/localEntrega")
	public ModelAndView localentrega(String localEntrega,Integer idLista){
		
		CadastroLocco locco = manager.find(CadastroLocco.class, 1);
		Lista lista = manager.getReference(Lista.class, idLista);
		LocalEvento localEvento = localEventoDAO.ultimoLocalEvento(lista.getIdJob().getIdJob());
		MV.setViewName("menuProducao/enderecoAjax");
		
		if(localEntrega.equals("localevento")){
			if(localEvento == null){
				MV.addObject("endereco", "Nenhum endereço cadastrado no Job");
			}else{
				MV.addObject("endereco", localEvento.getLocalEventoEndereco());
			}
		}else{
			MV.addObject("endereco", locco.getEndereco()+"-"+locco.getCidade()+"/"+locco.getUf()+" - CEP "+locco.getCep());
		}

		return MV;
	}
	
	@RequestMapping("/parcelamento")
	public ModelAndView parcelamento(Integer idProdutoGrupo,Integer parcelas,
									 String valorContratacaoCalculado, boolean infoPag, boolean infoForn,String valorContratacao){

		
		BigDecimal qtdParcelas = BigDecimal.valueOf(parcelas);
		BigDecimal valorParcela = new BigDecimal("0");
		BigDecimal custoTotal = new BigDecimal("0");
		
		custoTotal = pegaValorTotalItem(idProdutoGrupo);
		
		List<Integer> numParcelas = new ArrayList<Integer>();
		
		//Regra para calculo de Parcelamento
		valorParcela = regraParaCalculoParcelamento(parcelas, valorContratacaoCalculado, infoPag,
				infoForn, valorContratacao, qtdParcelas, valorParcela,
				custoTotal, numParcelas);

		MV.setViewName("menuProducao/fornFinanc/fornComum");
		MV.addObject("valorParcela", valorParcela);
	    MV.addObject("numParcelas", numParcelas);
	    MV.addObject("valItem", custoTotal);
	    MV.addObject("idProdutoGrupo", idProdutoGrupo);
		
		return MV;
	}

	@RequestMapping("/calculaPagamento")
	@ResponseBody
	public List<String> calculaPagamento(Integer qtdParcelas, Integer idProdutoGrupo,
										 String posicaoCelula,Integer posicaoItem, String valores,
										 String valorContratacao, boolean infoPag){
		BigDecimal custoTotalItem = new BigDecimal("0");
		BigDecimal somaAnt = new BigDecimal("0");
		BigDecimal valorParcelasRestantes = new BigDecimal("0");
		List<String> listaDeRetorno = new ArrayList<String>();
		
		ArrayList<String> novo = pegaValoresTabela(valores);
		
		
		if(infoPag == true){
			BigDecimal valorContratacaoConv = new BigDecimal(util.formataValores(valorContratacao).toString());
			custoTotalItem = valorContratacaoConv;
			
		}else{
			custoTotalItem = pegaValorTotalItem(idProdutoGrupo);
		}
		
		List<BigDecimal> teste = new ArrayList<BigDecimal>();
		
		/**Pega quantas parcelas existem após a celula selecionada
		 */
		BigDecimal parcelasRest = pegaParcelasRestantesParaDividirValor(qtdParcelas, posicaoItem);
		
		String c = parcelasRest.toString();
		Integer parc = Integer.parseInt(c);
		
		/** Converte tudo para BigDecimal
		 */
		for(int i =0;i < novo.size();i++){
			teste.add(new BigDecimal(novo.get(i)));
		}
		
		/**Preenche a Lista de retorno com valores anterior a lista selecionada 
		 */
		for(int i = 0; i < posicaoItem;i++){
			listaDeRetorno.add(novo.get(i));
		}
		
		/** Soma os valores das parcelas anteriores com o valor da parcela atual
		 */
		somaAnt = somaValoresParcelasAnterioresComParcelaAtual(posicaoItem, somaAnt, teste);
		
		/** Calcula o valor das Parcelas restantes em partes iguais
		 */
		valorParcelasRestantes = calculaValorDasParcelasRestantes(custoTotalItem, somaAnt, parcelasRest);
		
		/** Termina de preencher a lista com os valores divididos em partes iguais
		*/
		if(parc == 0){
			
		}else{
			for(int i = posicaoItem; i < posicaoItem+parc;i++){
				listaDeRetorno.add(valorParcelasRestantes.toString());
			}
		}
				
		List<String> listaDeRetornoConvertidoParaReal = new ArrayList<String>();	
		for (int i =0; i < listaDeRetorno.size();i++) {
			
			listaDeRetornoConvertidoParaReal.add(util.ConverteDolarParaReal(listaDeRetorno.get(i)));
			System.out.println(listaDeRetornoConvertidoParaReal.get(i));
		}
		
		return listaDeRetornoConvertidoParaReal;
	}


	@RequestMapping("/calculaDiferenca")
	public ModelAndView calculaDiferenca(String itemValor1, String contratacaoValor1){
		
		MV.setViewName("menuProducao/diferencaAjax");
		
		BigDecimal valor = new BigDecimal(utilValores.formataValores(itemValor1));
		BigDecimal contratacao;
		
		if(contratacaoValor1.equals(null) || contratacaoValor1.equals("")){
			contratacao = new BigDecimal(utilValores.formataValores("0"));
		}else{
			contratacao = new BigDecimal(utilValores.formataValores(contratacaoValor1));
		}

		BigDecimal df = valor.subtract(contratacao);
		String diferenca = df.toString();
		diferenca = utilValores.ConverteDolarParaReal(diferenca);
		MV.addObject("diferenca", df);
		

		
		return MV;
	}
	
	public void salvaCartaNova(CartaContratacao carta){
		
		if(carta.getIdCarta() == null){
			manager.persist(carta);
		}else{
			manager.detach(carta);
			manager.merge(carta);
		}
	}

	@RequestMapping("/atualizaCarta")
	public String atualizaCarta(CartaContFornecedor carta,Integer idFornecedor,Integer idLista, Integer idProdutoGrupo){
		Empresa fornecedor = manager.getReference(Empresa.class, carta.getIdFornecedorTrans());
	//	ProducaoP producao = manager.getReference(ProducaoP.class, primaryKey)
		ProdutoGrupo prodGrupo = manager.getReference(ProdutoGrupo.class, idProdutoGrupo);
//		ProducaoP producao = manager.find(ProducaoP.class, carta.getIdproducao().getIdProducao());
		
	    ArrayList<String>data = new ArrayList<String>();
	    data = utilDatas.dataHojeFormatada();
	    
		String dataHoje  = "São Paulo, "+data.get(0)+" de "+data.get(1)+" de "+data.get(2);
		
		carta.setDataCabecalho(dataHoje);
		carta.setGerarCarta(true);
		carta.setAtualizacao(Calendar.getInstance());
		carta.setFornecedor(fornecedor);
		carta.setProdGrupo(prodGrupo);
		carta.setIdLista(idLista);
//		carta.setFornecedorContato(fornecedorContato);

		Usuario u = util.retornaUsuarioLogado();
		String usuario = u.getNome();
		carta.setAtualizadoPor(usuario);
		
		manager.merge(carta);
		return "redirect:itemListaAjax?idFornecedor="+idFornecedor+"&idLista="+idLista+"&idProdutoGrupo="+idProdutoGrupo;
	}
	
	@RequestMapping("/gerarWordCarta")
	public ModelAndView gerarWordCarta(Integer idCarta,Integer idFornecedor, Integer idLista) throws IOException{
		
		CartaContFornecedor carta = manager.find(CartaContFornecedor.class, idCarta);
		MV.setViewName("menuProducao/wordAjax");
		
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss"); 
		String a = s.format(Calendar.getInstance().getTime());
		
		String nomeCaminhhoCarta = "C:/SYSLOC/upload/word/"+a+".docx";
		String downloadCarta = "upload/upload/word/"+a+".docx";

// ----------------------------------------------------------------------------- //		
		String dd = apoioCarta.pegaIdsProducaoPComFornecedorRelacionado(idFornecedor, idLista);
	    
	    String ffd = apoioCarta.PegaIDFornecedorFinanceiroComProducaoPRelacionado(dd, idFornecedor);
	    
	    List<Integer> diasPrazo = producaoDAO.pegaDiasPagamentoDeUmFornecedor(ffd);
	    
	    List<ObjetoApoioValorPgto> pgtoLista = new ArrayList<ObjetoApoioValorPgto>();
	    
	    Integer parcelado = 0;
	    
	    for(int i =0;i < diasPrazo.size();i++){
	    	ObjetoApoioValorPgto pgto = new ObjetoApoioValorPgto();
	    	parcelado = parcelado+1;
	    	pgto.setParcela(i+1);
	    	pgto.setDias(diasPrazo.get(i)+" Dias do Evento");
	    	pgto.setDataPagar(PegaDatapagamento(ffd, diasPrazo.get(i)));
	    	
	    	BigDecimal val = somavalorFornecedor(ffd, diasPrazo.get(i));
	    	
			String vvv = utilValores.ConverteDolarParaReal(val.toString());
			
			BigDecimal v = new BigDecimal(utilValores.formataValores(vvv));
			String  valorExtenso= utilValores.converteValoresPorExtenso(v);
			
	    	pgto.setValorPagar(vvv+" ( "+valorExtenso+" )");
	    	
	    	pgtoLista.add(pgto);
	    }

// ----------------------------------------------------------------------------- //		
		
		geraCartaWord.formatoWord(carta,nomeCaminhhoCarta,pgtoLista);
		MV.addObject("nomeCarta", downloadCarta);
		
		return MV;
	}

	@RequestMapping("/gerarOutroWordCarta")
	public ModelAndView gerarOutroWordCarta(Integer idCarta,Integer idFornecedor, Integer idLista,
											Integer idFornecedorOriginal,Integer idProdutoGrupo) throws IOException, ParseException{
		
		CartaContFornecedor carta = manager.getReference(CartaContFornecedor.class, idCarta);
		MV.setViewName("menuProducao/wordAjax");
		
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss"); 
		String a = s.format(Calendar.getInstance().getTime());
		
		String nomeCaminhhoCarta = "C:/SYSLOC/upload/word/"+a+".docx";
		String downloadCarta = "upload/upload/word/"+a+".docx";
		
		
		
		String trasProducao = "from ProducaoP p where idlista = "+idLista+" and idEmpFornecedor= "+idFornecedorOriginal+" and idprodutogrupo = "+idProdutoGrupo;
		TypedQuery<ProducaoP> PS  = manager.createQuery(trasProducao, ProducaoP.class);
		
		ProducaoP p = (ProducaoP) PS.getSingleResult();	
    	FornecedorFinanceiro outroFornecedorFinanceiro = apoioCarta.verificaTemOutroFornecedor(p);
    	
    	ObjetoApoioValorPgto pgtoLista = new ObjetoApoioValorPgto();
    	
    	for(int i =0; i < outroFornecedorFinanceiro.getIdValorPgtoFornecedor().size();i++){
    		pgtoLista.setDataPagar(outroFornecedorFinanceiro.getIdValorPgtoFornecedor().get(i).getDtPgotFornecedor().getDataPagar());
    	}
		
// ----------------------------------------------------------------------------- //		
		/*String dd = apoioCarta.pegaIdsProducaoPComFornecedorRelacionado(idFornecedor, idLista);
		
		String ffd = apoioCarta.PegaFornecedorFinanceiroComProducaoPRelacionado(dd, idFornecedor);
		
		String tes = "SELECT distinct(diasPrazoParaPagamento) FROM ValorPagtoFornecedor"
				+ " where idFornecedorFinanceiro in ("+ffd+") order by diasPrazoParaPagamento";
		Query q = manager.createQuery(tes);
		List<Integer> diasPrazo = q.getResultList();*/
		
		

		
//		pgto.setDataPagar(dataPagar);
		
		Integer parcelado = 0;
		
		/*for(int i =0;i < diasPrazo.size();i++){
		    
		    ObjetoApoioValorPgto pgto = new ObjetoApoioValorPgto();
			parcelado = parcelado+1;
			pgto.setParcela(i+1);
			pgto.setDias(diasPrazo.get(i)+" Dias do Evento");
			pgto.setDataPagar(PegaDatapagamento(ffd, diasPrazo.get(i)));
			
			BigDecimal val = somavalorFornecedor(ffd, diasPrazo.get(i));
			
			String vvv = utilvalores.ConverteDolarParaReal(val.toString());
			
			BigDecimal v = new BigDecimal(utilvalores.formataValores(vvv));
			String  valorExtenso= utilvalores.converteValoresPorExtenso(v);
			
			pgto.setValorPagar(vvv+" ( "+valorExtenso+" )");
			
			pgtoLista.add(pgto);
		}*/
		
// ----------------------------------------------------------------------------- //		
		
		geraOutroCartaWord.formatoOutroWord(carta,nomeCaminhhoCarta,pgtoLista);
		MV.addObject("nomeCarta", downloadCarta);
		
		return MV;
	}
	
	@RequestMapping("/calculaDiferencaFornecedor")
	@ResponseBody
	public String calculaDiferencaFornecedor(String itemValor,String contratacaoValor,String impostoMesmoFornecedor){
		
		BigDecimal divide2 = new BigDecimal("0");
		BigDecimal valorContratacao = new BigDecimal("0");
		
		BigDecimal divide = new BigDecimal("100");
		
		if(impostoMesmoFornecedor.equals("") || impostoMesmoFornecedor.equals(null)){
		}else{
			divide2 = new BigDecimal(impostoMesmoFornecedor);
		}

		if(contratacaoValor.equals("") || contratacaoValor.equals(null) || contratacaoValor.equals(" ")){
		}else{
			valorContratacao = new BigDecimal(util.formataValores(contratacaoValor));
		}
		
		
		BigDecimal B = divide2.divide(divide);
		
		
		BigDecimal valorItem = new BigDecimal(util.formataValores(itemValor));
	
		
		
		
		BigDecimal A = valorItem.subtract(valorContratacao); 
		BigDecimal Y = A.multiply(B);
		
		BigDecimal X = valorContratacao.add(Y);
		
		
		ProducaoValoresGJson valoresGJon = new ProducaoValoresGJson();
		valoresGJon.setValorPagamentoMesmoFornecedor(util.ConverteDolarParaReal(X.toString()));
		Gson gson = new Gson();

 		return gson.toJson(valoresGJon);
	}
	
	@RequestMapping("/calculaDiferencaOutroFornecedor")
	@ResponseBody
	public String calculaDiferencaOutroFornecedor(String diferenca,String impostoOutroFornecedor){
		
		BigDecimal divide = new BigDecimal("100");
		BigDecimal divide2 = new BigDecimal(impostoOutroFornecedor);
		BigDecimal B = divide2.divide(divide);
		
		BigDecimal valorDiferenca = new BigDecimal(util.formataValores(diferenca));
		BigDecimal Y = valorDiferenca.multiply(B);
		
		ProducaoValoresGJson valoresGJon = new ProducaoValoresGJson();
		valoresGJon.setValorPagamentoMesmoFornecedor(util.ConverteDolarParaReal(Y.toString()));
		Gson gson = new Gson();

 		return gson.toJson(valoresGJon);
	}
	
	/*@RequestMapping("/consolidarProduto")
	public ModelAndView consProduto(){
		
		
		return MV;
	}*/
	
	
	
	
	
}
