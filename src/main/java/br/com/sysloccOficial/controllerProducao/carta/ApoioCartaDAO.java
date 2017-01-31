package br.com.sysloccOficial.controllerProducao.carta;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.UtilitariaValores;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.producao.CartaContFornecedor;
import br.com.sysloccOficial.model.producao.FornecedorFinanceiro;
import br.com.sysloccOficial.model.producao.ProducaoP;

public class ApoioCartaDAO {

	@Autowired private UtilitariaValores utilvalores;
	@PersistenceContext	private EntityManager manager;

	
	public String somaTotalValoresFornecedorFinanceiro(String gt) {
		String somaTotalValores = "SELECT sum(valor) FROM ValorPagtoFornecedor where idFornecedorFinanceiro in("+gt+")";
		TypedQuery<BigDecimal> total = (TypedQuery<BigDecimal>) manager.createQuery(somaTotalValores);
		String vv = total.getSingleResult().toString();
		return vv;
	}

	
	
	

	public String PegaIDFornecedorFinanceiroComProducaoPRelacionado(String g, Integer idFornecedor) {
		String idsFornFinanceiros = "SELECT idFornecedor FROM FornecedorFinanceiro where idProducao in("+g+") and idEmpresa ="+idFornecedor;
		Query qst = manager.createQuery(idsFornFinanceiros);
		List<Integer> qts = qst.getResultList();
		String gt = utilvalores.limpaConsultaRetorno(qts.toString());
		return gt;
	}
	
	public List<FornecedorFinanceiro> PegaFornFinanceiroCompletoComProducaoPRelacionado(String g) {
		String idsFornFinanceiros = "FROM FornecedorFinanceiro f join fetch f.idValorPgtoFornecedor  where idProducao in("+g+")";
		Query qst = manager.createQuery(idsFornFinanceiros);
		List<FornecedorFinanceiro> qts = qst.getResultList();
		return qts;
	}


	public String pegaIdsProducaoPComFornecedorRelacionado(ProducaoP producao) {
		String idsProducao = "SELECT idProducao FROM ProducaoP where idEmpFornecedor = "+producao.getIdEmpFornecedor().getIdEmpresa()+" and idlista = "+producao.getIdLista();
		Query q = manager.createQuery(idsProducao);
		List<Integer> qs = q.getResultList();
		String g = utilvalores.limpaConsultaRetorno(qs.toString());
		return g;
	}

	public String pegaValorTotalFornecedorNovoMetodo(ProducaoP producao) {
		String idsProducao = "SELECT sum(valorItem) FROM ProducaoP where idEmpFornecedor = "+producao.getIdEmpFornecedor().getIdEmpresa()+" and idlista = "+producao.getIdLista();
		Query q = manager.createQuery(idsProducao);
		List<Integer> qs = q.getResultList();
		String g = utilvalores.limpaConsultaRetorno(qs.toString());
		return g;
	}

	public String pegaIdsProducaoPComFornecedorRelacionado(Integer idFornecedor, Integer idLista) {
		String idsProducao = "SELECT idProducao FROM ProducaoP where idEmpFornecedor = "+idFornecedor+" and idlista = "+idLista;
		Query q = manager.createQuery(idsProducao);
		List<Integer> qs = q.getResultList();
		String g = utilvalores.limpaConsultaRetorno(qs.toString());
		return g;
	}

	
	/**
	 * Método usado para verificar se existe uma carta salva no sistema.
	 * Se existir, vai excluir a Carta,
	 * Se não existir vai criar uma nova
	 * 
	 * 
	 * 
	 * @param producao
	 * @param produtog
	 * @param verificaCarta
	 * @param fornecedor
	 * @return
	 */

	public boolean verificaSeExistecartaSalva(ProducaoP producao, ProdutoGrupo produtog,
			boolean verificaCarta, Empresa fornecedor) {
		try {
			String consulta = "from CartaContFornecedor where fornecedor ="+ fornecedor.getIdEmpresa()+ " and produtoGrupo="+ produtog.getIdProdutoGrupo()
					         +" and idProducao="+ producao.getIdProducao();
			Query q = manager.createQuery(consulta,CartaContFornecedor.class);
			List<CartaContFornecedor> c = q.getResultList();
			if(c != null){
				
				for (int i = 0; i < c.size(); i++) {
					CartaContFornecedor cc = manager.getReference(CartaContFornecedor.class, c.get(i).getIdCartaForn());
					manager.remove(cc);
				}
				verificaCarta = true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return verificaCarta;
	}
	
	/**
	 * Método usado para verificar se item tem outro fornecedor na contratação
	 * 
	 */
	public FornecedorFinanceiro verificaTemOutroFornecedor(ProducaoP producao) {
		String outroForn = "FROM FornecedorFinanceiro where idProducao = "+producao.getIdProducao()+""
				         + " and idEmpresa <> "+producao.getIdEmpFornecedor().getIdEmpresa();
		Query q = manager.createQuery(outroForn);
		FornecedorFinanceiro outro = (FornecedorFinanceiro) q.getSingleResult();
		return outro;
	}
	
	public void verificaSetemCartaContrataçãoOutroFornecedor(Integer idFornecedor, Integer idLista,Integer idProdutoGrupo,ModelAndView MV) {
		try {
	    	
	    	String trasProducao = "from ProducaoP p where idlista = "+idLista+" and idEmpFornecedor= "+idFornecedor+" and idprodutogrupo = "+idProdutoGrupo;
			TypedQuery<ProducaoP> PS  = manager.createQuery(trasProducao, ProducaoP.class);
			
	    	ProducaoP producaoPExistente = PS.getSingleResult();	
	    	FornecedorFinanceiro outroFornecedorFinanceiro = verificaTemOutroFornecedor(producaoPExistente);
	    	MV.addObject("outroFornx", outroFornecedorFinanceiro);
	    	
	    	CartaContFornecedor outrof = verificaSeexistiCarta(outroFornecedorFinanceiro.getIdEmpresa().getIdEmpresa(), idProdutoGrupo);
	    	MV.addObject("CartaOutroFornecedor", outrof);
	    	
		} catch (Exception e) {
			System.out.println("Não tem ");
		}
	}
	
	public CartaContFornecedor verificaSeexistiCarta(Integer idFornecedor, Integer idProdutoGrupo) {
		Query q = manager.createQuery("from ProducaoP where idProdutoGrupo=" +idProdutoGrupo);
		ProducaoP p = (ProducaoP) q.getSingleResult(); 
		String consultaCarta = "from CartaContFornecedor where fornecedor = "+idFornecedor
				+ " and idLista = "+ p.getLista().getIdLista()+" and produtoGrupo ="+idProdutoGrupo;
		TypedQuery<CartaContFornecedor> c = manager.createQuery(consultaCarta, CartaContFornecedor.class);
		CartaContFornecedor cc = c.getSingleResult();
		return cc;
	}
    
	
	
	
}
