package br.com.sysloccOficial.controllerProducao.salvaItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
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
import br.com.sysloccOficial.model.Comunicador;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.LocalEvento;
import br.com.sysloccOficial.model.Producao;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.Usuario;
import br.com.sysloccOficial.model.producao.ProducaoP;
import br.com.sysloccOficial.model.producao.StatusProducao;
import br.com.sysloccOficial.model.producao.ValorPgtoAux;


@Controller
@Transactional
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ControllerSalvaItem {
	
	@Autowired private LocalEventoDAO localEventoDAO;
	@Autowired private Utilitaria util;
	@Autowired private UtilitariaDatas utilDatas;
	@Autowired private ApoioCartaSalvaItem apoioCarta;
	@Autowired private AuxDAOProducao auxDAOProducao;
	@Autowired private PreencheValorPagamentosServices preencheValoresPagamentos;
	
	@PersistenceContext	private EntityManager manager;
	
	ModelAndView MV = new ModelAndView();
	
	
	@RequestMapping("/salvaItem")
	public String salvaItem(ProducaoP producao, Integer idProdutoGrupo) throws IOException, ParseException{
		ProdutoGrupo produtog = manager.getReference(ProdutoGrupo.class, idProdutoGrupo);
		Lista lista = manager.getReference(Lista.class, producao.getIdLista());
		Empresa fornecedor = manager.getReference(Empresa.class, producao.getIdEmpresaTransiente()); 
		Usuario usuario = manager.getReference(Usuario.class, producao.getIdUsuario());
		LocalEvento localEvento = localEventoDAO.ultimoLocalEvento(lista.getIdJob().getIdJob());
		ProdutoGrupo produtoGrupo = manager.getReference(ProdutoGrupo.class, idProdutoGrupo);

		Integer parcelas = producao.getNumParcelas();
		
		
		
		producao.setLista(lista);
		producao.setUsuario(usuario);
		producao.setIdEmpFornecedor(fornecedor);
		producao.setDataConsolidado(Calendar.getInstance());
		producao.setResponsavelContratacao(usuario.getNome());
		producao.setStatusProducao(StatusProducao.CONSOLIDADO);
		
		if(producao.getInfoPag().equals("0")){
			producao.setTemContratacao(false);
		}else{
			producao.setTemContratacao(true);
			
			if(producao.getInfoForn().equals("0")){
				producao.setTemMesmoFornecedor(true);
			}else{
				producao.setTemOutroFornecedor(true);
			}
		}

		List<ValorPgtoAux> pagamentos = new ArrayList<ValorPgtoAux>();
		
		//Preenche os pagamentos
		preencheValoresPagamentos.preencheValoresPagamentos(producao, parcelas, pagamentos); 
		
		producao.setPrazoEntrega(utilDatas.formataDatasStringParaCalendar(producao.getpEntrega()+" "+"08:00"));
		
		if(localEvento == null){
			producao.setPrazoPagamento(Calendar.getInstance().getTime());
		}else{
			Date dataTerminoDoEvento = localEvento.getLocalEventoDataTermino();
			producao.setPrazoPagamento(utilDatas.SomaDias(dataTerminoDoEvento, producao.getPrazo().get(0))); // Soma Termino do Evento + Dias para pagamento.
		}
		
		producao.setProdutoGrupo(produtoGrupo);
		producao.setValorItem(util.valoresEmReais(producao.getValorItemTrans()));
		producao.setValorContratacao(util.valoresEmReais(producao.getValorContratacaoTrans()));
		producao.setValorDePagamentoContratacao(util.valoresEmReais(producao.getValorDePagamentoContratacaoTrans()));
		
		producao.setImposto(util.converteStringParaDouble(producao.getImpostoTrans()));
		producao.setImpostoOutroForn(util.converteStringParaDouble(producao.getImpostoOutroTrans()));
		producao.setValorDePagamentoContratacaoOutroFornecedor(util.valoresEmReais(producao.getValorDePagamentoContratacaoOutroFornecedorTrans()));
		
		producao.setDiferenca(util.valoresEmReais(producao.getDiferencaTrans()));
		producao.setStatus(true);
		
		verificaQualLocalEvento(producao, localEvento);
		
		/**
		 * 
		 *  Verifica se item vai ser salvo ou atualizado
		 *  
		 */
		verificaSeSalvaOuAtualiza(producao, pagamentos, fornecedor);
	
		/**
		 * As chamadas abaixo verificam se 
		 * Pegar qtd produtoGrupo por fornecedor, por lista
		 * Pegar qtd de fornecedor por idlista em producao. 
		 *  
		 */
 		apoioCarta.verificaSeTeraCartaContratacao(producao, produtog);
		
		return "redirect:itemListaAjax?idFornecedor="+produtog.getEmpresa().getIdEmpresa()
		+"&idLista="+lista.getIdLista()+"&idProdutoGrupo="+producao.getProdutoGrupo().getIdProdutoGrupo()+"#CartaFornecedor";
	}


	private void verificaSeSalvaOuAtualiza(ProducaoP producao, List<ValorPgtoAux> pagamentos, Empresa fornecedor) {
		
		if(producao.getIdProducao() == null ){ // Será salvo
			
			manager.persist(producao);
			auxDAOProducao.salvaFornecedorFinanceiro(producao,pagamentos);
			
		}else{ 									// Será atualizado
			/*manager.detach(producao);*/
			manager.merge(producao);
			auxDAOProducao.apagaFornecedor(producao,fornecedor.getIdEmpresa(),pagamentos);
		}
	}

	
	

	private void verificaQualLocalEvento(ProducaoP producao, LocalEvento localEvento) {
		
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
	}


	/*private void preenchePagamentos(ProducaoP producao, Integer parcelas,List<ValorPgtoAux> pagamentos) {
	
		for(int i = 0; i <  parcelas;i++){
			
			BigDecimal valorItem = new BigDecimal("0.00");
			
			ValorPgtoAux valor = new ValorPgtoAux();
		
			valor.setParcela(producao.getParcela().get(i));
			valor.setPrazo(producao.getPrazo().get(i));
			valor.setData(util.formataDatasStringParaCalendar(producao.getData().get(i)+" 08:00"));
			
			if(producao.isTemContratacao() == true){
				valorItem = util.valoresEmReais(producao.getValorDePagamentoContratacaoTrans());
			}else{
				valorItem = util.valoresEmReais(producao.getValor().get(i));
			}
		
			valor.setValor(valorItem);
			pagamentos.add(valor);
		}
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
