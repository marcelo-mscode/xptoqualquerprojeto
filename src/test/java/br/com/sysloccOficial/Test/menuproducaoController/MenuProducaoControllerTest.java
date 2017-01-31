package br.com.sysloccOficial.Test.menuproducaoController;



import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaConversoes;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.conf.UtilitariaValores;
import br.com.sysloccOficial.controllerProducao.AuxDAOProducao;
import br.com.sysloccOficial.controllerProducao.carta.MenuProducaoControllerCarta;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.GrupoDAO;
import br.com.sysloccOficial.daos.JobDAO;
import br.com.sysloccOficial.daos.ListaDAO;
import br.com.sysloccOficial.daos.LocalEventoDAO;
import br.com.sysloccOficial.daos.MenuProducaoDAO;
import br.com.sysloccOficial.daos.PagamentoDAO;
import br.com.sysloccOficial.daos.ProducaoDAO;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.CadastroLocco;
import br.com.sysloccOficial.model.CartaContratacao;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.LocalEvento;
import br.com.sysloccOficial.model.ProducaoValoresGJson;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.Usuario;
import br.com.sysloccOficial.model.producao.FornecedorFinanceiro;
import com.google.gson.Gson;


@Controller
@Transactional
public class MenuProducaoControllerTest {
	
	@Autowired private EmpresaDAO empresaDAO;
	@Autowired private JobDAO jobDAO;
	@Autowired private ListaDAO listaDAO;
	@Autowired private PagamentoDAO pagamentoDAO;
	@Autowired private UsuarioDAO usuarioDAO;
	@Autowired private ProducaoDAO producaoDAO;
	@Autowired private MenuProducaoDAO menuProducaoDAO;
	@Autowired private LocalEventoDAO localEventoDAO;
	@Autowired private ContatoDAO contatoDAO;
	@Autowired private GrupoDAO grupoDAO;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@Autowired private AuxDAOProducao auxDAOProducao;
	
	
	
	@Autowired private Utilitaria util;
	@Autowired private UtilitariaDatas utilDatas;
	@Autowired private MenuProducaoControllerCarta menuCarta;
	@Autowired private UtilitariaConversoes utilConversoes;
	@Autowired private UtilitariaValores utilValores;
	
	@PersistenceContext	private EntityManager manager;
	
	ModelAndView MV = new ModelAndView();

	@RequestMapping("/menuProducao")
	public ModelAndView menuProducao() {
		MV.setViewName("menuProducao/menuProducao");
		MV.addObject("lista", listaDAO.listaMenuProducao());
		return MV;
	}
	
	@RequestMapping("/itensProducao")
	public ModelAndView itensProducao(Integer idLIsta) {
		
		MV.setViewName("menuProducao/itensLista");
		MV.addObject("lista", listaDAO.editaLista(idLIsta));
		MV.addObject("categoria", listaDAO.listadeCategorias(idLIsta));
		MV.addObject("grupos", listaDAO.listaDeGrupos(idLIsta));

		
		List<Grupo> lista2 = grupoDAO.listaDeGrupos(idLIsta); // Pega lista de Grupos por idLista
		
		List<Integer>idEmpresas = new ArrayList<Integer>();
		Integer num = 0;
		
		for(int i = 0; i< lista2.size();i++){ // Percorre a lista de Grupos
			
		  for(int j = 0;j < lista2.get(i).getProdutoGrupo().size(); j++){ // Percorre a lista de um grupo em procura de produtos desse grupo

				try {
					num = lista2.get(i).getProdutoGrupo().get(j).getEmpresa().getIdEmpresa(); // Pega o id do Fornecedor desse produto
				} catch (Exception e) {
					num = 1234567890;
				}

				if(idEmpresas.contains(num)){ }
				else{
					idEmpresas.add(num);
				}
		   }
				
		}

	    List listaTotal = new ArrayList();
		
		for(int r = 0;r < idEmpresas.size();r++){
			
			if(idEmpresas.get(r).equals(1234567890)){
				listaTotal.add(0);
			}
			
			listaTotal.add(produtoGrupoDAO.produto(idEmpresas.get(r),idLIsta));
		}
		
		
		MV.addObject("Total", listaTotal);
		
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
		LocalEvento localEvento = localEventoDAO.ultimoLocalEvento(lista.getIdJob().getIdJob());
		ProdutoGrupo produtoGrupo = manager.find(ProdutoGrupo.class, idProdutoGrupo);
		
/* ------------------------------------------------------------------------------------------- */		
// Relacionado a carta de contratação		
		/**
		 * É necessário verificar:
		 * Se o fornecedor tem um ou mais itens
		 * 		Se tiver só um item ou mais e concluido(s), exibir a carta 
		 * 		Não concluidos, não exibir
		 * 
		 */
		
		/*if(menuProducaoDAO.ConfereItemProducao(idProdutoGrupo).isEmpty()){

		}else{
			Producao producao = manager.find(Producao.class, produtoGrupo.getProducao().getIdProducao());
			CartaContratacao carta = manager.find(CartaContratacao.class, producao.getCartaContracao().getIdCarta());
			MV.addObject("carta", carta);
		}*/
	
		/**
		 * Verificar:
		 * Se tem outro fornecedor com valor de contratação
		 * 		Se tiver só um item ou mais desse outro fornecedor e concluido(s), exibir a carta desse outro fornecedor
		 * 		Não concluidos, não exibir
		 * 
		 */
			
		/*MV.addObject("outraCarta", outraCarta); exemplo*/
		
		/**
		 * Enviar carta Modelo para pegar dados estáticos
		 * 
		 */
		CartaContratacao cartaModelo = manager.find(CartaContratacao.class, 1);
		MV.addObject("outraCarta", cartaModelo);
		
		ArrayList<String> datas = utilDatas.dataHojeFormatada();
		MV.addObject("dataHoje", datas);
		
/* ------------------------------------------------------------------------------------------- */		

		
		MV.setViewName("menuProducao/detalhesItensProducao");
		
		MV.addObject("fornecedor", empresaDAO.infoEmpresaProducao(produtoGrupo.getEmpresa().getIdEmpresa()));
		MV.addObject("contatoFornecedor", empresaDAO.listaContatoEmpresa(idFornecedor));
		MV.addObject("tipoPagamento", pagamentoDAO.listaTipoPagamento());
		MV.addObject("usuarios", usuarioDAO.usuariosProducao());
		MV.addObject("idLista", idLista);
		MV.addObject("idProdutoGrupo", idProdutoGrupo);
		MV.addObject("produtoGrupo", produtoGrupo);
	    MV.addObject("producao", menuProducaoDAO.listaProducao(idProdutoGrupo));
	    
	    MV.addObject("InfoJobs",localEvento);
	    MV.addObject("fornecedoresLista", empresaDAO.listaFornecedores());
	    MV.addObject("lista", lista);
	    
		return MV;
	}
	
	/*@RequestMapping("/salvaItem")
	public String salvaItem(Producao producao, Integer idProdutoGrupo) throws IOException, ParseException{
		ProdutoGrupo produtog = manager.find(ProdutoGrupo.class, idProdutoGrupo);
		Lista lista = manager.getReference(Lista.class, producao.getIdLista());
	
		Integer parcelas = producao.getNumParcelas();
		
		List<ValorPgtoAux> pagamentos = new ArrayList<ValorPgtoAux>();
		
		
		for(int i = 0; i <  parcelas;i++){
			
			ValorPgtoAux valor = new ValorPgtoAux();
		
			valor.setParcela(producao.getParcela().get(i));
			valor.setPrazo(producao.getPrazo().get(i));
			valor.setData(util.formataDatasStringParaCalendar(producao.getData().get(i)+" 08:00"));
			BigDecimal valorItem = util.valoresEmReais(producao.getValor().get(i));
			valor.setValor(valorItem);
			
			pagamentos.add(valor);
			
		}
		
		Empresa fornecedor = manager.getReference(Empresa.class, producao.getIdEmpresaTransiente()); 
		Usuario usuario = manager.getReference(Usuario.class, producao.getIdUsuario());
		LocalEvento localEvento = localEventoDAO.ultimoLocalEvento(lista.getIdJob().getIdJob());
		ProdutoGrupo produtoGrupo = manager.getReference(ProdutoGrupo.class, idProdutoGrupo);
		
		
		producao.setLista(lista);
		producao.setUsuario(usuario);
		producao.setIdEmpFornecedor(fornecedor);
		producao.setDataConsolidado(Calendar.getInstance());
		producao.setResponsavelContratacao(usuario.getNome());
		producao.setStatusProducao(StatusProducao.CONSOLIDADO);
		
		producao.setPrazoEntrega(utilDatas.formataDatasStringParaCalendar(producao.getpEntrega()+" "+"08:00"));

		
		if(localEvento == null){
			producao.setPrazoPagamento(Calendar.getInstance().getTime());
		}else{
			Date cs = localEvento.getLocalEventoDataTermino();
			producao.setPrazoPagamento(utilDatas.SomaDias(cs, producao.getPrazo().get(0))); // Soma Termino do Evento + Dias para pagamento.
		}
		
		producao.setProdutoGrupo(produtoGrupo);
		producao.setValorItem(util.valoresEmReais(producao.getItemValor1()));
		producao.setValorContratacao(util.valoresEmReais(producao.getContratacaoValor1()));
		
		producao.setDiferenca(util.valoresEmReais(producao.getDiferencaValor1()));
		producao.setStatus(true);
		
		if(producao.getLocalEntrega().equals("localevento")){
			if(localEvento == null){
				producao.setReferenciaEntrega(producao.getLocalEntrega());
				producao.setLocalEntrega("Nenhum endereço cadastrado no Job");
			}else{
				producao.setReferenciaEntrega(producao.getLocalEntrega());
				producao.setLocalEntrega(localEvento.getLocalEventoEndereco());
			}
		}else{
			producao.setReferenciaEntrega(producao.getLocalEntrega());
			producao.setLocalEntrega("agencia");
		}
		
		
		*//**
		 * Verificar se existe mais algum item do Fornecedor
		 * 
		 * if(mais itens fornecedor === true){
		 * 		fornecedorFinanceiro.setContratacao(true);
		 * }else{
		 * 		fornecedorFinanceiro.setContratacao(false);
		 * }
		 * 
		 * 
		 *//*
		
		// Verifica se item vai ser salvo ou atualizado

		
		if(producao.getIdProducao() == null ){ // Será salvo
			manager.persist(producao);
			
		}else{ 									// Será atualizado
			manager.detach(producao);
			manager.merge(producao);
		}
		
		
		auxDAOProducao.salvaFornecedorFinanceiro(producao,pagamentos);
		
	
		
		Contato contato = manager.getReference(Contato.class, producao.getContatoFornecedor());
		List<Comunicador> comunicador = contatoDAO.buscaUltimoComunicador(producao.getContatoFornecedor());
		
		if(FornecedorFinanceiro.getContratacao()  == true){
			menuCarta.geraNovaCarta(producao, produtog,comunicador,contato);
		}else{
			
		}
		
		//	menuCarta.geraNovaCarta(producao, produtog,comunicador,contato);
		
		
		*//**
		 * Gerar carta de contratação
		 * 
		 * 
		 * producao.setCartaContratacao(cartaContForn);
		 * 
		 *//*
		
		
		Contato contato = manager.getReference(Contato.class, producao.getContatoFornecedor());
		List<Comunicador> comunicador = contatoDAO.buscaUltimoComunicador(producao.getContatoFornecedor());
		
		if(producao.getIdProducao() == null || producao.getIdProducao().equals(null) || producao.getIdProducao().equals(" ")){
			menuCarta.geraNovaCarta(producao, produtog,comunicador,contato);
		}else{
			menuCarta.geraNovaCarta(producao, produtog,comunicador,contato);
		}
		
		
		return "redirect:itemListaAjax?idFornecedor="+produtog.getEmpresa().getIdEmpresa()
		+"&idLista="+lista.getIdLista()+"&idProdutoGrupo="+producao.getProdutoGrupo().getIdProdutoGrupo();
	}
*/
	
	
	
	@RequestMapping("/fechaItem")
	public String fechaItem(Integer idDoProdutoGrupo, Integer idLista){
		ProdutoGrupo produtoGrupo = manager.find(ProdutoGrupo.class, idDoProdutoGrupo);
	
		produtoGrupo.setStatus(1);
		manager.merge(produtoGrupo);
		
		return "redirect:itensProducao?idLIsta="+idLista;
	}
	
	@RequestMapping("/trocarFornecedor")
	public ModelAndView trocarFornecedor(Integer idEmpresa,Integer idProduto){
		MV.setViewName("menuProducao/fornecedorAjax");
		MV.addObject("fornecedor", empresaDAO.infoEmpresaProducao(idEmpresa));
		
		ProdutoGrupo produtoGrupo = manager.find(ProdutoGrupo.class, idProduto);
		Empresa empresa = manager.getReference(Empresa.class, idEmpresa);
		produtoGrupo.setEmpresa(empresa);
		manager.merge(produtoGrupo);
		
	   return MV;	
	}
	
	@RequestMapping("/calculaPrazo")
	public ModelAndView calculaPrazo(Integer diasPrazo,Integer idLista,String nameInput){
		MV.setViewName("menuProducao/vencimentoAjax");
		
		Lista lista = manager.getReference(Lista.class, idLista);
		LocalEvento localEvento = localEventoDAO.ultimoLocalEvento(lista.getIdJob().getIdJob());
		
		Date cs = localEvento.getLocalEventoDataTermino();
		MV.addObject("dataPrazo", utilDatas.SomaDias(cs, diasPrazo));
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
	public ModelAndView parcelamento(Integer idProdutoGrupo,Integer parcelas){

		BigDecimal qtdParcelas = BigDecimal.valueOf(parcelas);
		BigDecimal valorParcela = new BigDecimal("0");
		List<Integer> numParcelas = new ArrayList<Integer>();
		
		/*valorParcela = TestaParcelas(idProdutoGrupo, parcelas, qtdParcelas,
				valorParcela, numParcelas);*/

		MV.setViewName("menuProducao/fornFinanc/fornComum");
		MV.addObject("valorParcela", valorParcela);
		MV.addObject("qtdParcelas", numParcelas);
		
		return MV;
	}

	public void TestaParcelas() {
		Integer qtdParcelas = 6;
		Integer posicaoItem = 2;
		BigDecimal item = new BigDecimal("600.00");
		
		BigDecimal somaAnt = new BigDecimal("0");
		BigDecimal valorParcelasRestantes = new BigDecimal("0");
		
		/*BigDecimal[] teste = "";
		*/
		List<BigDecimal> teste = new ArrayList<BigDecimal>();
		
		teste.add(new BigDecimal("200.00"));
		teste.add(new BigDecimal("180.00"));
		teste.add(new BigDecimal("55.00"));
		teste.add(new BigDecimal("55.00"));
		teste.add(new BigDecimal("155.00"));
		
		
		//Pega quantas parcelas existem anteriores a celula selecionada
		BigDecimal parcelasRest = pegaParcelasRestantesParaDividirValor(qtdParcelas, posicaoItem);
		
		// Soma os valores das parcelas anteriores com o valor da parcela atual
		somaAnt = somaValoresParcelasAnterioresComParcelaAtual(posicaoItem, somaAnt, teste);
		//Calcula o valor das Parcelas restantes em partes iguais
		valorParcelasRestantes = calculaValorDasParcelasRestantes(item, somaAnt, parcelasRest);
		
		System.out.println(valorParcelasRestantes);

		
		
		
	}

	private BigDecimal calculaValorDasParcelasRestantes(BigDecimal item, BigDecimal somaAnt,
			BigDecimal parcelasRest) {
		BigDecimal diferencaItemMenosSomaParcelas;
		BigDecimal valorParcelasRestantes;
		diferencaItemMenosSomaParcelas = item.subtract(somaAnt);
		valorParcelasRestantes = diferencaItemMenosSomaParcelas.divide(parcelasRest,BigDecimal.ROUND_UP);
		return valorParcelasRestantes;
	}

	private BigDecimal somaValoresParcelasAnterioresComParcelaAtual(Integer posicaoItem, BigDecimal somaAnt,
			List<BigDecimal> teste) {
		for(int i = 0; i < posicaoItem - 1;i++){
			somaAnt = somaAnt.add(teste.get(i));
		}
		somaAnt = somaAnt.add(teste.get(posicaoItem-1));
		return somaAnt;
	}

	private BigDecimal pegaParcelasRestantesParaDividirValor(Integer qtdParcelas, Integer posicaoItem) {
		Integer parcelasRestantes = 0;
		
		for(int i = posicaoItem+1; i <= qtdParcelas;i++){
			parcelasRestantes = parcelasRestantes+1;
		}
		String g = parcelasRestantes.toString();
		BigDecimal parcelasRest = new BigDecimal(g.toString());
		return parcelasRest;
	}

	private BigDecimal ValorPRoduto() {
//		Query valor = manager.createQuery("SELECT custoProduto FROM ProdutoGrupo where idProdutoGrupo ="+65888);
		BigDecimal custo = new BigDecimal("4.12");
		return custo;
	}

	private double pegaSoma() {
//		Query qtd = manager.createQuery("SELECT sum(quantidade*quantidade2*diarias) FROM ProdutoGrupo where idProdutoGrupo ="+655888);
		double qtditem = 60;
		return qtditem;
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
	public String atualizaCarta(CartaContratacao carta,Integer idFornecedor,Integer idLista, Integer idProdutoGrupo){
	
		
		
	    ArrayList<String>data = new ArrayList<String>();
	    data = utilDatas.dataHojeFormatada();
	    
		String dataHoje  = "São Paulo, "+data.get(0)+" de "+data.get(1)+" de "+data.get(2);
		
		carta.setDataCabecalho(dataHoje);
		carta.setGerarCarta(true);
		carta.setAtualizacao(Calendar.getInstance());

		Usuario u = util.retornaUsuarioLogado();
		String usuario = u.getNome();
		carta.setUsuario(usuario);
		
		if(carta.getIdCarta() == null){
			manager.persist(carta);
		}else{
			manager.detach(carta);
			manager.merge(carta);
		}
		
		return "redirect:itemListaAjax?idFornecedor="+idFornecedor+"&idLista="+idLista+"&idProdutoGrupo="+idProdutoGrupo;
		
	}
	
	@RequestMapping("/gerarWordCarta")
	public ModelAndView gerarWordCarta(Integer idCarta) throws IOException{
		CartaContratacao carta = manager.find(CartaContratacao.class, idCarta);
		MV.setViewName("menuProducao/wordAjax");
		
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss"); 
		String a = s.format(Calendar.getInstance().getTime());
		
		String nomeCaminhhoCarta = "C:/SYSLOC/upload/word/"+a+".docx";
		String downloadCarta = "upload/upload/word/"+a+".docx";

		/*menuCarta.formatoWord(carta,nomeCaminhhoCarta);
		MV.addObject("nomeCarta", downloadCarta);*/
		
		return MV;
	}
	
	@RequestMapping("/calculaDiferencaFornecedor")
	@ResponseBody
	public String calculaDiferencaFornecedor(String itemValor,String contratacaoValor,String impostoMesmoFornecedor){
		
		BigDecimal divide = new BigDecimal("100");
		BigDecimal divide2 = new BigDecimal(impostoMesmoFornecedor);
		BigDecimal B = divide2.divide(divide);
		
		
		BigDecimal valorItem = new BigDecimal(util.formataValores(itemValor));
		BigDecimal valorContratacao = new BigDecimal(util.formataValores(contratacaoValor));
		
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
	
	@RequestMapping("/consolidarProduto")
	public ModelAndView consProduto(){
		
		
		return MV;
	}

	
	@Test
	public void testa(){
		String teste = "from FornecedorFinanceiro where idProducao = 59 and idEmpresa <> 7024";
		
		Query q = manager.createQuery(teste);
		
		FornecedorFinanceiro f = (FornecedorFinanceiro) q.getSingleResult();
		
		System.out.println(f.getImposto());
		
	}
	
	
}




