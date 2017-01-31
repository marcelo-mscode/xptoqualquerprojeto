package br.com.sysloccOficial.controllerProducao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.conf.UtilitariaValores;
import br.com.sysloccOficial.controllerProducao.carta.ApoioCartaDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.LocalEventoDAO;
import br.com.sysloccOficial.daos.MenuProducaoDAO;
import br.com.sysloccOficial.daos.PagamentoDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.CartaContratacao;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.LocalEvento;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.producao.CartaContFornecedor;
import br.com.sysloccOficial.model.producao.FornecedorFinanceiro;
import br.com.sysloccOficial.model.producao.ObjetoApoioValorPgto;
import br.com.sysloccOficial.model.producao.ProducaoP;

public class ItemFornecedorIndividualController {

	@PersistenceContext	private EntityManager manager;
	@Autowired private EmpresaDAO empresaDAO;
	@Autowired private PagamentoDAO pagamentoDAO;
	@Autowired private UsuarioDAO usuarioDAO;
	@Autowired private LocalEventoDAO localEventoDAO;
	@Autowired private UtilitariaDatas utilDatas;
	@Autowired private MenuProducaoDAO menuProducaoDAO;
	@Autowired private ApoioCartaDAO apoioCarta;	
	@Autowired private UtilitariaValores utilvalores;
	
	
	
	/*	@RequestMapping("/itemLis22222222taAjax")
	public ModelAndView itemListaAjax(Integer idFornecedor,Integer idLista, Integer idProdutoGrupo) throws InterruptedException{
		
		ModelAndView MV = new ModelAndView();
		
		Lista lista = manager.find(Lista.class, idLista);
		
		ProdutoGrupo produtoGrupo = manager.find(ProdutoGrupo.class, idProdutoGrupo);
		
		MV.addObject("fornecedor", empresaDAO.infoEmpresaProducao(produtoGrupo.getEmpresa().getIdEmpresa()));
		MV.addObject("contatoFornecedor", empresaDAO.listaContatoEmpresa(idFornecedor));
		MV.addObject("tipoPagamento", pagamentoDAO.listaTipoPagamento());
		MV.addObject("usuarios", usuarioDAO.usuariosProducao());
		MV.addObject("idLista", idLista);
		MV.addObject("idProdutoGrupo", idProdutoGrupo);
		MV.addObject("produtoGrupo", produtoGrupo);
		ArrayList<Integer> parcelasPadrao = qtsParcelasPadrao();
	    MV.addObject("parcelasPadrao", parcelasPadrao);
        MV.addObject("fornecedoresLista", empresaDAO.listaFornecedores());
	    
	    LocalEvento localEvento = localEventoDAO.ultimoLocalEvento(lista.getIdJob().getIdJob());
	    MV.addObject("InfoJobs",localEvento);
	    MV.addObject("lista", lista);
		
		*//**
		 *  Verificar se o item vai ser salvo ou atualizado
		 *//*
		TypedQuery<ProducaoP> PS = verificaSeItemSeraSalvoOuAtualizado(idFornecedor, idLista,idProdutoGrupo);
		
		try {
			atualizaProducao(idFornecedor, idLista, idProdutoGrupo);
			MV.setViewName("menuProducao/detalhesItensProducao");
			
		} catch (Exception e) {
			System.out.println("Não tem registro, vai salvar");
			MV.setViewName("menuProducao/salvaItem/salvaItensProducao");
		}
		
		
	    // Carta de contratação	

		*//**
		 * Enviar carta Modelo para pegar dados estáticos
		 * 
		 *//*
		
	    try {
	    	
	    	CartaContFornecedor cc = verificaSeexistiCarta(idFornecedor, idProdutoGrupo);
	    	MV.addObject("CartaNovaTeste", cc);

	    	MV.addObject("outraCarta", manager.find(CartaContratacao.class, 1));
			
			MV.addObject("dataHoje", utilDatas.dataHojeFormatada());
		    
		    List<ProducaoP> ff = pegaProducaoPPorListaEFornecedor(idFornecedor, idLista);
		    
		    MV.addObject("fProducao", ff);
		    
		    BigDecimal totalImposto = somaApenasValoresImpostoComContratacao(ff);
		    String compara = totalImposto.toString();
		    
		    if(compara.equals("0") || compara == "0"){
		    	
		    }else{
		    	MV.addObject("somaImposto", totalImposto);
		    }
		    

		    
		    ArrayList<Integer>idsProducao = new ArrayList<Integer>();
		    
		    for(int i = 0; i < ff.size(); i++){
		    	idsProducao.add(ff.get(i).getIdProducao());
		    }
		    
		    
		    String dd = apoioCarta.pegaIdsProducaoPComFornecedorRelacionado(idFornecedor, idLista);
		    
		    String ffd = apoioCarta.PegaIDFornecedorFinanceiroComProducaoPRelacionado(dd, idFornecedor);
		    
		    List<Integer> diasPrazo = pegaQuantidadeDiasPrazoPagamento(ffd);
		   
		    
		    *//**
		     * Monta a lista de pagamento para condiçoes Pagamento
		     * 
		     *//*
		    List<ObjetoApoioValorPgto> pgtoLista = new ArrayList<ObjetoApoioValorPgto>();
		    Integer parcelado = 0;
		    parcelado = montaListaPagamento(ffd, diasPrazo, pgtoLista, parcelado);
		    MV.addObject("condicaoPagamentos", pgtoLista);

		    
		//    List<FornecedorFinanceiro> fst = apoioCarta.PegaFornFinanceiroCompletoComProducaoPRelacionado(dd);

		    MV.addObject("parceladoEm", parcelado);

	    
	    } catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    
	    try {
	    	ProducaoP p = (ProducaoP) PS.getSingleResult();	
	    	FornecedorFinanceiro outroFornecedorFinanceiro = apoioCarta.verificaTemOutroFornecedor(p);
	    	MV.addObject("outroFornx", outroFornecedorFinanceiro);
	    	
	    	CartaContFornecedor outrof = verificaSeexistiCarta(outroFornecedorFinanceiro.getIdEmpresa().getIdEmpresa(), idProdutoGrupo);
	    	MV.addObject("CartaOutroFornecedor", outrof);
	    	
		} catch (Exception e) {
			System.out.println("Não tem ");
			// TODO: handle exception
		}
	    
	    manager.flush();
 		manager.clear();
		return MV;
	}*/



	private List<Integer> pegaQuantidadeDiasPrazoPagamento(String ffd) {
		String tes = "SELECT distinct(diasPrazoParaPagamento) FROM ValorPagtoFornecedor"
				   + " where idFornecedorFinanceiro in ("+ffd+") order by diasPrazoParaPagamento";
		Query q = manager.createQuery(tes);
		List<Integer> diasPrazo = q.getResultList();
		return diasPrazo;
	}

	private List<ProducaoP> pegaProducaoPPorListaEFornecedor(Integer idFornecedor, Integer idLista) {
		String tst = "from ProducaoP p where idlista = "+idLista+" and idEmpFornecedor= "+idFornecedor;
		Query qw = manager.createQuery(tst);
		List<ProducaoP> ff = qw.getResultList();
		return ff;
	}



	private TypedQuery<ProducaoP> verificaSeItemSeraSalvoOuAtualizado(Integer idFornecedor,Integer idLista, Integer idProdutoGrupo) {
		String trasProducao = "from ProducaoP p where idlista = "+idLista+" and idEmpFornecedor= "+idFornecedor+" and idprodutogrupo = "+idProdutoGrupo;
		TypedQuery<ProducaoP> PS  = manager.createQuery(trasProducao, ProducaoP.class);
		return PS;
	}
	
	
	
	private ArrayList<Integer> qtsParcelasPadrao() {
		ArrayList<Integer> parcelasPadrao = new ArrayList<Integer>();
	    
	    for(int i =0;i <= 12;i++){
	    	parcelasPadrao.add(i, i);
	    }
		return parcelasPadrao;
	}
	
	public ModelAndView atualizaProducao(Integer idFornecedor,Integer idLista, Integer idProdutoGrupo){
		
		ModelAndView MV = new ModelAndView();
	
		ProdutoGrupo produtoGrupo = manager.find(ProdutoGrupo.class, idProdutoGrupo);
		
		Integer teste = produtoGrupo.getProducaop().getIdProducao();
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
	    	CartaContFornecedor cc = verificaSeexistiCarta(idFornecedor, idProdutoGrupo);
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
	
	private CartaContFornecedor verificaSeexistiCarta(Integer idFornecedor, Integer idProdutoGrupo) {
		Query q = manager.createQuery("from ProducaoP where idProdutoGrupo=" +idProdutoGrupo);
		ProducaoP p = (ProducaoP) q.getSingleResult(); 
		String consultaCarta = "from CartaContFornecedor where fornecedor = "+idFornecedor
				+ " and idLista = "+ p.getLista().getIdLista()+" and produtoGrupo ="+idProdutoGrupo;
		TypedQuery<CartaContFornecedor> c = manager.createQuery(consultaCarta, CartaContFornecedor.class);
		CartaContFornecedor cc = c.getSingleResult();
		return cc;
	}
	
	public BigDecimal somaApenasValoresImpostoComContratacao(List<ProducaoP> ff) {
		BigDecimal valorItem = new BigDecimal("0");
		BigDecimal valorCont = new BigDecimal("0");
		BigDecimal result = new BigDecimal("0");
		
		BigDecimal calculoImposto = new BigDecimal("0");
		BigDecimal cem = new BigDecimal("100");
		BigDecimal totalImposto = new BigDecimal("0");
		
		for(int i=0; i < ff.size();i++){
			//Se tiver contratacao
			if(ff.get(i).isTemContratacao() == true){
				double imposto = ff.get(i).getImposto();
				BigDecimal ii = new BigDecimal(imposto);
				valorItem = ff.get(i).getValorItem();
				valorCont = ff.get(i).getValorContratacao();
				result = valorItem.subtract(valorCont);
				calculoImposto = result.multiply((ii).divide(cem));
				
				totalImposto = totalImposto.add(calculoImposto);
				
			}
		}
		return totalImposto;
	}
	
	
// -----------------------------------------------------------------------------
	private Integer montaListaPagamento(String ffd, List<Integer> diasPrazo, List<ObjetoApoioValorPgto> pgtoLista, Integer parcelado) {
		for(int i =0;i < diasPrazo.size();i++){
			ObjetoApoioValorPgto pgto = new ObjetoApoioValorPgto();
			parcelado = parcelado+1;
			pgto.setParcela(i+1);
			pgto.setDias(diasPrazo.get(i)+" Dias do Evento");
			pgto.setDataPagar(PegaDatapagamento(ffd, diasPrazo.get(i)));
			
			BigDecimal val = somavalorFornecedor(ffd, diasPrazo.get(i));
			
			String vvv = utilvalores.ConverteDolarParaReal(val.toString());
			
			BigDecimal v = new BigDecimal(utilvalores.formataValores(vvv));
			String  valorExtenso= utilvalores.converteValoresPorExtenso(v);
			
			pgto.setValorPagar(vvv+"&nbsp&nbsp( "+valorExtenso+" )");
			
			pgtoLista.add(pgto);
		}
		return parcelado;
	}
	
	private Date PegaDatapagamento(String ffd, Integer diasPrazo) {
		String sss = "select idValorFinancForn FROM ValorPagtoFornecedor"
				+ " where idFornecedorFinanceiro in ("+ffd+") and diasPrazoParaPagamento ="+ diasPrazo;
		TypedQuery<Integer> qsss = manager.createQuery(sss, Integer.class).setMaxResults(1);
		
		String s = "select dataPagar FROM DtPgtoFornecedor where idValorPgForn ="+qsss.getSingleResult();
		
		TypedQuery<Date> data = manager.createQuery(s,Date.class);
		
		Date d = data.getSingleResult();
		
		return d;
	}

	private BigDecimal somavalorFornecedor(String ffd, Integer diasPrazo) {
		String sss = "select sum(valor) FROM ValorPagtoFornecedor"
				+ " where idFornecedorFinanceiro in ("+ffd+") and diasPrazoParaPagamento ="+ diasPrazo;
		Query qsss = manager.createQuery(sss);
		BigDecimal sumoa = (BigDecimal) qsss.getSingleResult();
		return sumoa;
		
	}
	
	
}
