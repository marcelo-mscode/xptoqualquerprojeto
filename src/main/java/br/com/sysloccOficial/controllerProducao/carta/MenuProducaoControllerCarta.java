package br.com.sysloccOficial.controllerProducao.carta;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.conf.UtilitariaValores;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.daos.LocalEventoDAO;
import br.com.sysloccOficial.model.Comunicador;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.LocalEvento;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.Usuario;
import br.com.sysloccOficial.model.producao.CartaContFornecedor;
import br.com.sysloccOficial.model.producao.FornecedorFinanceiro;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Transactional
public class MenuProducaoControllerCarta extends ApoioCartaDAO{
	@Autowired private LocalEventoDAO localEventoDAO;
	@Autowired private ContatoDAO contatoDAO;

	@Autowired private UtilitariaDatas utilDatas;
	@Autowired private UtilitariaValores utilvalores;
	@Autowired private ApoioCartaUtil utilCarta;

	
	
	@PersistenceContext	private EntityManager manager;
	
	ModelAndView MV = new ModelAndView();
	
	
	public void geraNovaCarta(ProducaoP producao, ProdutoGrupo produtog, List<Comunicador> comunicador,Contato contato) throws ParseException{
		
		boolean verificaCarta = false;
		
		CartaContFornecedor cartaContratacao = new CartaContFornecedor();
		Empresa fornecedor = manager.getReference(Empresa.class, produtog.getEmpresa().getIdEmpresa());
		
		verificaCarta = verificaSeExistecartaSalva(producao, produtog, verificaCarta, fornecedor);
		
		System.out.println(verificaCarta);
		
		cartaContratacao.setProdGrupo(produtog);
		cartaContratacao.setFornecedor(fornecedor);
		cartaContratacao.setIdproducao(producao);
		cartaContratacao.setIdLista(producao.getLista().getIdLista());
		
		String contatoTel = "Contato(s): ";
		String comunicadores = "";
		Lista lista = manager.getReference(Lista.class, producao.getIdLista());

		Usuario usuario = manager.getReference(Usuario.class, producao.getIdUsuario());
		LocalEvento localEvento = localEventoDAO.ultimoLocalEvento(lista.getIdJob().getIdJob());
		
		cartaContratacao.setEmpresa(produtog.getEmpresa().getEmpresa());
		
		if(contato == null){
			cartaContratacao.setFornecedorContratado("Sem contato Cadastrado");
			cartaContratacao.setContatoResponsavel("Sem contato Cadastrado");
		}else{
			cartaContratacao.setFornecedorContratado(contato.getContato());
			cartaContratacao.setContatoResponsavel(contato.getContato()+",");
		}
		
		comunicadores = utilCarta.preencheListaComunicadores(comunicador, cartaContratacao, comunicadores);
	
		/**
		 * ( Antigo ) Fazer SUM de select para pegar valor total da carta
		 * 
		 */
		// fornecedorFinanceiro somar 
		// Pega ids de producao que tem o fornecedor relacionado
		String g = pegaIdsProducaoPComFornecedorRelacionado(producao);

		/**
		 * ( Novo ) Fazer SUM de select para pegar valor total da carta
		 * 
		 */
		// fornecedorFinanceiro somar 
		// Pega ids de producao que tem o fornecedor relacionado
		String gNovoMetodoPegavalor = pegaValorTotalFornecedorNovoMetodo(producao);
		
		//Pega FornecedorFinanceiro que tem ProducaoP Relacionado
		String gt = PegaIDFornecedorFinanceiroComProducaoPRelacionado(g,produtog.getEmpresa().getIdEmpresa());
		
		// Soma Total de Valores do Fornecedor Financeiro
		String vv = somaTotalValoresFornecedorFinanceiro(gt);
		
		
		//( Antigo )
		//String vvv = utilvalores.ConverteDolarParaReal(vv);

		String vvv = utilvalores.ConverteDolarParaReal(gNovoMetodoPegavalor);
		
		BigDecimal v = new BigDecimal(utilvalores.formataValores(vvv));
		String valorExtenso = utilvalores.converteValoresPorExtenso(v);
		cartaContratacao.setValorTotal(" R$ "+vvv+" ( "+valorExtenso+" )");	
		
		contatoTel = contatoTel + comunicadores;
		cartaContratacao.setFornecedorContato(contatoTel);
		
		double quant = produtog.getQuantidade()*produtog.getQuantidade2()*produtog.getDiarias();
//		cartaContratacao.setDescItem(quant +" "+ produtog.getProduto());

		
		cartaContratacao.setEmpCab5(lista.getLista());
		cartaContratacao.setEmpCab7(localEvento.getLocalEventoEndereco());
		
		String localEntrega="";

		if(producao.getLocalEntrega().equals("agencia")){
			localEntrega = "";
			cartaContratacao.setLocalEntregaAgencia(true);
		}else{
			localEntrega = localEvento.getLocalEventoEndereco();
			cartaContratacao.setLocalEntregaAgencia(false);
		}
		
		cartaContratacao.setLocalEntregaTexto(localEntrega);
		cartaContratacao.setResponsavelContratacao(usuario.getNome());
		
		utilDatas.converteDateParaString(producao.getPrazoEntrega());
		
		cartaContratacao.setDataEntrega(utilDatas.converteDateParaString(producao.getPrazoEntrega()));

		manager.detach(cartaContratacao);
		manager.persist(cartaContratacao);
		
	}

	
	public void geraCartaOutroFornecedor(FornecedorFinanceiro outro, ProducaoP producao, ProdutoGrupo produtog, List<Comunicador> comunicador,Contato contato) throws ParseException{
		
		Integer outroFornecedorId = outro.getIdEmpresa().getIdEmpresa();
		
		boolean verificaCarta = false;
		
		CartaContFornecedor cartaContratacao = new CartaContFornecedor();
		Empresa fornecedor = manager.getReference(Empresa.class, outroFornecedorId);
		
		verificaCarta = verificaSeExistecartaSalva(producao, produtog, verificaCarta, fornecedor);
		
		cartaContratacao.setProdGrupo(produtog);
		cartaContratacao.setFornecedor(fornecedor);
		cartaContratacao.setIdproducao(producao);
		cartaContratacao.setIdLista(producao.getLista().getIdLista());
		
		String contatoTel = "Contato(s): ";
		String comunicadores = "";
		Lista lista = manager.getReference(Lista.class, producao.getIdLista());
		
		Usuario usuario = manager.getReference(Usuario.class, producao.getIdUsuario());
		LocalEvento localEvento = localEventoDAO.ultimoLocalEvento(lista.getIdJob().getIdJob());
		
		cartaContratacao.setEmpresa(fornecedor.getEmpresa());
		cartaContratacao.setFornecedorContratado(contato.getContato());
		
		comunicadores = utilCarta.preencheListaComunicadores(comunicador, cartaContratacao, comunicadores);
		
		
		
	//	String g = pegaIdsProducaoPComFornecedorRelacionado(fornecedor.getIdEmpresa(), producao.getIdLista());
		
		
		String gg = producao.getIdProducao().toString();
		
		//Pega FornecedorFinanceiro que tem ProducaoP Relacionado
		String gt = PegaIDFornecedorFinanceiroComProducaoPRelacionado(gg,fornecedor.getIdEmpresa());
		
		String vv = somaTotalValoresFornecedorFinanceiro(gt);
		String vvv = utilvalores.ConverteDolarParaReal(vv);
		
		BigDecimal v = new BigDecimal(utilvalores.formataValores(vvv));
		String valorExtenso = utilvalores.converteValoresPorExtenso(v);
		cartaContratacao.setValorTotal(" R$ "+vvv+" ( "+valorExtenso+" )");
		
		
		
		/**
		 * Fazer SUM de select para pegar valor total da carta
		 * 
		 */
		// fornecedorFinanceiro somar 
		// Pega ids de producao que tem o fornecedor relacionado
		/*String g = pegaIdsProducaoPComFornecedorRelacionado(producao);
		
		//Pega FornecedorFinanceiro que tem ProducaoP Relacionado
		String gt = PegaFornecedorFinanceiroComProducaoPRelacionado(g,produtog.getEmpresa().getIdEmpresa());
		
		// Soma Total de Valores do Fornecedor Financeiro
		String vv = somaTotalValoresFornecedorFinanceiro(gt);
		String vvv = utilvalores.ConverteDolarParaReal(vv);
		
		BigDecimal v = new BigDecimal(utilvalores.formataValores(vvv));
		String valorExtenso = utilvalores.converteValoresPorExtenso(v);
		cartaContratacao.setValorTotal(" R$ "+vvv+" ( "+valorExtenso+" )");	*/
		
		contatoTel = contatoTel + comunicadores;
		cartaContratacao.setFornecedorContato(contatoTel);
		
	//	double quant = produtog.getQuantidade()*produtog.getQuantidade2()*produtog.getDiarias();
//		cartaContratacao.setDescItem(quant +" "+ produtog.getProduto());
		
		cartaContratacao.setContatoResponsavel(contato.getContato()+",");
		cartaContratacao.setEmpCab5(lista.getLista());
		cartaContratacao.setEmpCab7(localEvento.getLocalEventoEndereco());
		
	//	String localEntrega="";
		
		/*if(producao.getLocalEntrega().equals("agencia")){
			localEntrega = "";
			cartaContratacao.setLocalEntregaAgencia(true);
		}else{
			localEntrega = localEvento.getLocalEventoEndereco();
			cartaContratacao.setLocalEntregaAgencia(false);
		}*/
		
	//	cartaContratacao.setLocalEntregaTexto(localEntrega);
		cartaContratacao.setResponsavelContratacao(usuario.getNome());
		
	//	utilDatas.converteDateParaString(producao.getPrazoEntrega());
	//	cartaContratacao.setDataEntrega(utilDatas.converteDateParaString(producao.getPrazoEntrega()));
		
		manager.detach(cartaContratacao);
		manager.persist(cartaContratacao);
		
	}

	
}
