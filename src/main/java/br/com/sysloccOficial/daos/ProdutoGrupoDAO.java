package br.com.sysloccOficial.daos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.DeterQuantpadrao;
import br.com.sysloccOficial.model.DeterminaQuantidades;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.GrupoCategoriaBayer;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.OrcamentoFornecedor;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.producao.ProducaoP;



@Repository
public class ProdutoGrupoDAO {

	@PersistenceContext private EntityManager manager;
	@Autowired private Utilitaria util;
	
	
	public List<ProdutoGrupo> listaProdutoGrupoPorGrupo(Integer idGrupo){
		
		String consulta = "select pg from ProdutoGrupo pg where idGrupo="+idGrupo; 
		Query query = manager.createQuery(consulta, ProdutoGrupo.class);
		
		return query.getResultList();
	}
	
	public Lista pegaLista(Integer idLista){
		Lista e = manager.find(Lista.class, idLista);
		return e;
	}

	public Categoria pegaCategoria(Integer idLista){
		String consulta = "select pg from Categoria pg where idLista="+idLista+" order by idcategoria"; 
		TypedQuery<Categoria> query = manager.createQuery(consulta, Categoria.class).setMaxResults(1);
		return query.getSingleResult();
	}
	
	public Grupo listaGrupo(Integer idGrupo){

		Grupo grupo = manager.find(Grupo.class, idGrupo);
		return grupo;
	}
	
	public List<Categoria> pegaCategoriaPorIdGrupo(Integer idGrupo){
		
		Grupo grupo = manager.getReference(Grupo.class,idGrupo);
		
		String consultaIdLista= "select c from Categoria c where idLista="+grupo.getIdLista().getIdLista();
		
		Query query = manager.createQuery(consultaIdLista, Categoria.class);
		
		return query.getResultList();	
	}
	
	public List<GrupoCategoriaBayer> categoriaBayer(){
		TypedQuery<GrupoCategoriaBayer> g = manager.createQuery("from GrupoCategoriaBayer order by idGrupoCategoria", GrupoCategoriaBayer.class);
		return g.getResultList();
	}
	
	
	public Object[] pegaListaParaProdutoGrupo(Integer idGrupo){
		
		Grupo grupo = manager.getReference(Grupo.class,idGrupo);
		
		String consultaIdLista= "select l.lista,l.listaCod,l.revisao,l.idLista,l.idlistaEstatus from Lista l where idLista="+grupo.getIdLista().getIdLista();
		
		Query query = manager.createQuery(consultaIdLista);
		
		Object lista = query.getSingleResult();
		
		return (Object[]) lista;	
	}

	public ProdutoGrupo detalhesItem(Integer idProdutoGrupo) {
		String consulta= "select pg from ProdutoGrupo pg where idProdutoGrupo="+idProdutoGrupo;
		Query query = manager.createQuery(consulta, ProdutoGrupo.class);
		return (ProdutoGrupo) query.getSingleResult();
	}
	public double pegaQuantidades(Integer idProdutoGrupo, String campo){
		String consulta = "select "+ campo +" from ProdutoGrupo pg where idProdutoGrupo="+idProdutoGrupo;
		Query q = manager.createQuery(consulta);
		return (double) q.getSingleResult();
	}
	
	
	
	
	public String empresaLista(Integer idGrupo){
		Grupo grupo = manager.find(Grupo.class,idGrupo);
		Lista lista = manager.find(Lista.class, grupo.getIdLista().getIdLista());
		Integer idJob = +lista.getIdJob().getIdJob();
		
		String consulta = "select e.empresa from Job j left join j.empresa e where idJob="+idJob;
		
		Query query = manager.createQuery(consulta);
		
		return (String) query.getSingleResult();
	}
	
	public Object apenasNomeGrupo(Integer idGrupo){
		String consulta="select g.grupo,g.idCategoria from Grupo g where idGrupo="+idGrupo;
		Query query = manager.createQuery(consulta);
		
		Object itens = query.getSingleResult();
		
		
		
		return itens;
	}
	public Categoria impostoCategoria(Integer idGrupo){
		
		String consulta = "select idCategoria from Grupo g where g.idgrupo ="+ idGrupo;
		
		Query query = manager.createQuery(consulta);
		
		return (Categoria) query.getSingleResult();
		
	}
	
	public List<Object[]> produto(Integer idEmpresa, Integer idlista){
		String consulta = "select p.empresa.idEmpresa, p.empresa.empresa, p.produto, p.custoProduto, p.precoProduto,"
				+ "p.imposto, p.quantidade, p.quantidade2,p.diarias, p.idProdutoGrupo,p.status from ProdutoGrupo p where p.empresa.idEmpresa="+idEmpresa
				+" and p.idGrupo.idLista.idLista="+idlista;
		Query query = manager.createQuery(consulta);
		List<Object[]>lista = query.getResultList();
		return lista;
	}
	
	/**
	 * Seleciona um objeto DeterminaQuantidades pelo Id do Grupo.
	 * 
	 * Método que verifica se o grupo já tem um produtoGrupo com quantidade Determinante.
	 * 
	 * @param idGrupo
	 * @return
	 */
	public DeterminaQuantidades determinaQuant(Integer idGrupo){
		String consulta = "select d from DeterminaQuantidades d where grupo ="+idGrupo;
	
		try {
			Query q = manager.createQuery(consulta);
			return (DeterminaQuantidades) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	
	}

	/**
	 * Seleciona um objeto DeterQuantpadrao pelo Id do Grupo
	 * 
	 * 
	 * @param idGrupo
	 * @return
	 */
	public DeterQuantpadrao deterQuantPadrao(Integer idGrupo){
		String consulta = "select d from DeterQuantpadrao d where grupo ="+idGrupo;
		
		try {
			Query q = manager.createQuery(consulta);
			return (DeterQuantpadrao) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	/**
	 * Método para pegar a quantidade determinante
	 * 
	 * 
	 */
	public double pegaQuantidade(Integer idProdutoGrupo){
		String q = "select (quantidade * quantidade2) as mult from ProdutoGrupo where idprodutoGrupo="+ idProdutoGrupo;
		Query query = manager.createQuery(q);
		return (double) query.getSingleResult();
	}
	
	/**
	 * Método para pegar o total do preço da linha
	 * 
	 * 
	 */
	public double pegaPrecoGrupo(Integer idGrupo){
		String consulta = "select sum(precoProduto * (quantidade * quantidade2 * diarias)) from ProdutoGrupo where idGrupo="+idGrupo;
		Query query = manager.createQuery(consulta);
		return (double) query.getSingleResult();
	}
	
	/**
	 * Método para pegar a diaria do produtoGrupo escolhido como
	 * determinante
	 * 
	 * 
	 */
	public double pegaDiaria(Integer idProdutoGrupo){
		String consulta = "select diarias from ProdutoGrupo where idProdutoGrupo="+idProdutoGrupo;
		Query query = manager.createQuery(consulta);
		return (double) query.getSingleResult();
	}
	
	/**
	 * Método para pegar os valores de Quantidades Determinantes
	 * 
	 * 
	 */
	public DeterminaQuantidades quantDeterm(Integer idGrupo){
		String consulta = "SELECT d FROM DeterminaQuantidades d where grupo ="+idGrupo;
		
		try {
			Query query = manager.createQuery(consulta);
			return (DeterminaQuantidades) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Método para pegar o valor da Quantidade Determinante Padrao
	 * 
	 * 
	 */
	public DeterQuantpadrao quantDetermPadrao(Integer idGrupo){
		String consulta = "SELECT d FROM DeterQuantpadrao d where grupo ="+idGrupo;
		
		try {
			Query query = manager.createQuery(consulta);
			return (DeterQuantpadrao) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public OrcamentoFornecedor persisteUMOrcamento(OrcamentoFornecedor orcam, Grupo umGrupo) {
		
		OrcamentoFornecedor orcamento = new OrcamentoFornecedor();
		orcamento = orcam;
		orcamento.setIdOrcamento(null);
		orcamento.setGrupo(umGrupo);
		
		 try {
			 manager.detach(orcamento);
			 manager.persist(orcamento);
			 
			 
			 System.out.println(orcamento.getIdOrcamento());
			 
		} catch (Exception e) {
			System.out.println(e);
		}
		return orcamento;
	}
	
	public ProdutoGrupo persisteUMProdutoGrupoClonado(ProdutoGrupo prodg, Grupo umGrupo) {
		
		
		Integer idProdAntigo = prodg.getIdProdutoGrupo();
//		JOptionPane.showMessageDialog(null, "Id Prod antigo: "+idProdAntigo);
		
		ProdutoGrupo umProdutoGrupo;
		
		umProdutoGrupo = prodg;
		umProdutoGrupo.setIdProdutoGrupo(null);
		umProdutoGrupo.setIdGrupo(umGrupo);
		umProdutoGrupo.setTidProdutoGrupo(util.nomeAleatorio(12));
		umProdutoGrupo.setStatus(null);
		
		try {
			manager.detach(umProdutoGrupo);
			manager.persist(umProdutoGrupo);
		} catch (Exception es) {
			System.out.println(es);
		}
		
		try {
			
			String consulta  =  "from OrcamentoFornecedor where produto.idProdutoGrupo ="+idProdAntigo;
			
			 TypedQuery<OrcamentoFornecedor> orc = manager.createQuery(consulta,OrcamentoFornecedor.class);
			 OrcamentoFornecedor orcamentoAntigo = orc.getSingleResult();
			 
			 OrcamentoFornecedor novoOrcamento = new OrcamentoFornecedor();
			 novoOrcamento.setIdOrcamento(null);
			 novoOrcamento.setValorOrcamento(orcamentoAntigo.getValorOrcamento());
			 novoOrcamento.setGrupo(umGrupo);
			 novoOrcamento.setEmpresa(orcamentoAntigo.getEmpresa());
			 novoOrcamento.setProduto(umProdutoGrupo);
			 manager.detach(novoOrcamento);
			 manager.persist(novoOrcamento);
			 manager.close();
			 
		} catch (Exception xx) {
		  		//JOptionPane.showMessageDialog(null, "Erro Copiar Orçamento: "+xx);
		}
		
		return umProdutoGrupo;
	}
	
	
	public List<GrupoCategoriaBayer> grupoCategoriaBayer(){
		TypedQuery<GrupoCategoriaBayer> g = manager.createQuery("from GrupoCategoriaBayer", GrupoCategoriaBayer.class);
		return g.getResultList();
	}
	
	public List<ProdutoGrupo> listaProdutosPorIdLista(Integer idLista){
		try {
			String consulta = "select p FROM ProdutoGrupo p where p.idGrupo.idgrupo in ("
							+ "SELECT idgrupo FROM Grupo where idLista = "+idLista+")";
			TypedQuery<ProdutoGrupo> prodGrupo = manager.createQuery(consulta, ProdutoGrupo.class);
			return prodGrupo.getResultList();
			
		} catch (Exception e) {
			System.out.println("ProdutoGrupoDAO - Linha: 323\nErro listaProdutosPorIdLista : "+ e);
			return null;
		}
	}
	
	public BigDecimal calculaSomaFeeLista(Integer idLista){
		try {
			List<ProdutoGrupo> listaProdutos = listaProdutosPorIdLista(idLista);
			return calculaFee(listaProdutos);
		} catch (Exception e) {
			System.out.println("ProdutoGrupoDAO - Linha: 342\nDeu erro ao calcular Soma do Fee: "+e);
			return new BigDecimal("0");
		}
	}
	
	
	/**
	 * Faz a soma total do FeeReduzido da lista. 
	 * 
	 */
	public BigDecimal calculaFee(List<ProdutoGrupo> listaProdutos){
		
		BigDecimal totalItensSemImpostoFeeReduzido = new BigDecimal("0");
		BigDecimal apoioSoma = new BigDecimal("0");
		try {
			
			BigDecimal divideFeeReduzido = listaProdutos.get(0).getIdGrupo().getIdLista().getFeeReduzido().divide(
					new BigDecimal(100),12,RoundingMode.UP);
			for (int i = 0; i < listaProdutos.size(); i++) {

				if (listaProdutos.get(i).isImposto() == false
					 && listaProdutos.get(i).getIdGrupo().isFeeReduzido() == true) {
					
					double quant = listaProdutos.get(i).getQuantidade();
					double quant2 = listaProdutos.get(i).getQuantidade2();
					double diaria = listaProdutos.get(i).getDiarias();
					double quantTotal = quant*quant2*diaria;
					
					apoioSoma = apoioSoma.add(listaProdutos.get(i).getPrecoProduto().multiply
											  (new BigDecimal(quantTotal))) ;
				}
			}
			totalItensSemImpostoFeeReduzido = apoioSoma.multiply(divideFeeReduzido);
			return totalItensSemImpostoFeeReduzido;
		} catch (Exception e) {
			System.out.println("ProdutoGrupoDAO - Linha: 358\nNão tem item sem imposto e com feeReduzido.");
			return new BigDecimal("0");
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
