package br.com.sysloccOficial.controllerProducao.salvaItem;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.controllerProducao.AuxDAOProducao;
import br.com.sysloccOficial.controllerProducao.carta.MenuProducaoControllerCarta;
import br.com.sysloccOficial.daos.ContatoDAO;
import br.com.sysloccOficial.model.Comunicador;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.producao.CartaContFornecedor;
import br.com.sysloccOficial.model.producao.FornecedorFinanceiro;
import br.com.sysloccOficial.model.producao.ProducaoP;

@Component
public class ApoioCartaSalvaItem {

	@Autowired private AuxDAOProducao auxDAOProducao;
	@Autowired private ContatoDAO contatoDAO;
	@Autowired private MenuProducaoControllerCarta menuCarta;
	@PersistenceContext	private EntityManager manager;
	
	
	public void verificaSeTeraCartaContratacao(ProducaoP producao, ProdutoGrupo produtog) throws ParseException {
		
		Integer idLista = producao.getIdLista();
		Integer qtdProdutoGrupo = auxDAOProducao.pegaTotalProdutoGrupoPorFornecedor(idLista, producao.getIdEmpFornecedor().getIdEmpresa());
		Integer qtdProducao = auxDAOProducao.pegaTotalFornecedorPorProducaoPorIdLista(idLista, producao.getIdEmpFornecedor().getIdEmpresa());
		
		CartaContFornecedor carta = new CartaContFornecedor();
		carta = VerificaSeCartaExiste(producao, carta);
		
		    if(qtdProdutoGrupo == qtdProducao){
			 	if(carta != null){
			 		manager.remove(carta);
			 	}
			
			 	Contato contato = null;
			 	List<Comunicador> comunicador = null;
			 
			 	Integer contatoFornecedor = producao.getContatoFornecedor();
			 	if(contatoFornecedor == null){
		
			 	}else{
			 		contato = manager.getReference(Contato.class, producao.getContatoFornecedor());
			 		comunicador = contatoDAO.buscaUltimoComunicador(producao.getContatoFornecedor());
			 	}
			 	
				menuCarta.geraNovaCarta(producao, produtog,comunicador,contato);

			}else{
				System.out.println("Não tem ");
			}
			
			//Verifica outro Fornecedor
			VerificaSeCartaOutroFornecedorExiste(producao, produtog, carta);
		
	}



	private void VerificaSeCartaOutroFornecedorExiste(ProducaoP producao, ProdutoGrupo produtog, CartaContFornecedor carta) {
		
		try {
			
			FornecedorFinanceiro outro = verificaTemOutroFornecedor(producao);

			CartaContFornecedor outraCarta = new CartaContFornecedor();
			
			outraCarta = VerificaSeCartaExisteOutroFornecedor(producao);
			
			if(outraCarta != null){
		 		manager.remove(carta);
		 	}
			
			if(producao.isTemOutroFornecedor() == true){
				Query com =	manager.createQuery("FROM Contato where idEmpresa ="+outro.getIdEmpresa().getIdEmpresa()).setMaxResults(1);
				Contato contato = (Contato) com.getSingleResult();
				
				List<Comunicador> comunicador = contatoDAO.buscaUltimoComunicador(contato.getIdContato());
				menuCarta.geraCartaOutroFornecedor(outro,producao, produtog, comunicador, contato);
			}
		} catch (Exception e) {
			System.out.println(e);// TODO: handle exception
		}
	}


	
	private FornecedorFinanceiro verificaTemOutroFornecedor(ProducaoP producao) {
		String outroForn = "FROM FornecedorFinanceiro where idProducao = "+producao.getIdProducao()+""
				         + " and idEmpresa <> "+producao.getIdEmpFornecedor().getIdEmpresa();
	
		try {
			Query q = manager.createQuery(outroForn);
			FornecedorFinanceiro outro = (FornecedorFinanceiro) q.getSingleResult();
			return outro;
		} catch (Exception e) {
			return null;// TODO: handle exception
		}
	}


	private CartaContFornecedor VerificaSeCartaExiste(ProducaoP producao,
			CartaContFornecedor carta) {
		try {
			String verificaCarta = "from CartaContFornecedor where fornecedor = "+producao.getIdEmpFornecedor().getIdEmpresa()+" and idLista ="+producao.getLista().getIdLista(); 
			Query q = manager.createQuery(verificaCarta);
			carta = (CartaContFornecedor) q.getSingleResult();
			return carta;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

	private CartaContFornecedor VerificaSeCartaExisteOutroFornecedor(ProducaoP producao) {
		
		CartaContFornecedor outraCarta = new CartaContFornecedor();
		try {
			
			
			String consulta = "from ProducaoP where idProducao="+ producao.getIdProducao();
			Query consultaq = manager.createQuery(consulta);
			ProducaoP pp = (ProducaoP) consultaq.getSingleResult();
			
			
			
			
			
			String outroForn = "FROM FornecedorFinanceiro where idProducao = "+producao.getIdProducao()+""
					         + " and idEmpresa <> "+producao.getIdEmpFornecedor().getIdEmpresa();
			Query qfs = manager.createQuery(outroForn);
			FornecedorFinanceiro outro = (FornecedorFinanceiro) qfs.getSingleResult();
			
			String verificaCarta = "from CartaContFornecedor where fornecedor = "
								+ ""+outro.getIdEmpresa().getIdEmpresa()+" and idLista ="+producao.getLista().getIdLista()+ 
								  " and produtoGrupo="+ producao.getProdutoGrupo().getIdProdutoGrupo(); 

			
			Query q = manager.createQuery(verificaCarta);
			outraCarta = (CartaContFornecedor) q.getSingleResult();
			return outraCarta;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
}
