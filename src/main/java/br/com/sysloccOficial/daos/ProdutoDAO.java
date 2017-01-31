package br.com.sysloccOficial.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.Atuacao;
import br.com.sysloccOficial.model.Departamento;
import br.com.sysloccOficial.model.Genero;
import br.com.sysloccOficial.model.MarcaProduto;
import br.com.sysloccOficial.model.ProdutoAtuacao;
import br.com.sysloccOficial.model.Produto_old;
import br.com.sysloccOficial.model.Produto;
import br.com.sysloccOficial.model.Unidade;
import br.com.sysloccOficial.model.Usuario;

@Repository
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void salva(Produto p){
		Unidade unidade = manager.find(Unidade.class, p.getIdUnid());
		p.setUnidade(unidade);
		
		Genero g = manager.find(Genero.class, p.getIdGenero());
		p.setGenero(g);
		
	    manager.persist(p);
	    
	    // Deixa primeira letra produto 
	    Query q = manager.createNativeQuery("UPDATE produto SET produto = CONCAT(UCASE(SUBSTRING(produto, 1, 1)),LCASE(SUBSTRING(produto, 2))) where idProduto > 1;");
	    q.executeUpdate();
	}
	
	public List<ProdutoAtuacao> atualiza(Produto p){
		Unidade unidade = manager.find(Unidade.class, p.getIdUnid());
		p.setUnidade(unidade);
		
		Genero g = manager.find(Genero.class, p.getIdGenero());
		p.setGenero(g);
		
		
		List<ProdutoAtuacao>produtoAtuacao = listaProdutoAtuacao(p.getIdproduto());
		
		
		manager.merge(p);
		
		return produtoAtuacao;
		
	}

	public void remove(Integer p) {
		Produto produto = manager.find(Produto.class, p);
		manager.remove(produto);
	}

	
	public List<Produto> mostra() {
		
		return manager.createQuery("from Produto p order by produto",Produto.class)
		/*return manager.createQuery("from Produto p join fetch p.genero",Produto.class)*/
		/*.setMaxResults(10)*/
		.getResultList();
		
    }

	public List<Produto> listaPorId(Integer id, String tabela) {
		return manager.createQuery("from Produto p join fetch p."+tabela+" where p.idproduto="+id,Produto.class)
		.getResultList();
	}

	

	
	
	
	public List<ProdutoAtuacao> listaProdutoAtuacao(Integer idProduto) {
		String consulta = "select a from ProdutoAtuacao a join fetch a.idAtuacao atuacao where a.idProduto="+idProduto+" order by atuacao.atuacao";
		
		Query query = manager.createQuery(consulta, ProdutoAtuacao.class);
		/*query.setParameter("idProduto", idProduto);*/
		
		return query.getResultList();
	}

	public List<MarcaProduto> listaProdutomarca(Integer idProduto) {
		String consulta = "select m from MarcaProduto m join fetch m.idMarca marca where m.idProduto="+idProduto+" order by marca.marca";
		
		Query query = manager.createQuery(consulta, MarcaProduto.class);
		/*query.setParameter("idProduto", idProduto);*/
		
		return query.getResultList();
	}
	public List<Object[]>listaidNomeProdutos(){
		String consulta = "select p.idproduto,p.produto from Produto p order by produto";
		
		Query query = manager.createQuery(consulta);
		List<Object[]> lista = query.getResultList();
		return lista;
		
	}
	public List<ProdutoAtuacao> listaFornecedorPorAtuacao(Integer idAtuacao){
		String consulta = "select p from ProdutoAtuacao p join fetch p.produtoAtucao a where p.idAtuacao= "+idAtuacao+" order by a.produto";
		Query query = manager.createQuery(consulta);
		return query.getResultList();
		
	}
	
	public List<Produto> listaProdutosPorTag(Integer idAtuacao){
		
		String consulta = "select p from Produto p join fetch p.produtoAtuacao a where a.idAtuacao = "+ idAtuacao +" order by p.produto asc";
		Query query = manager.createQuery(consulta);
		
		return query.getResultList();
	}
	
	public BigDecimal custoproduto(Integer idProduto){
		String consulta="select p.custoPadrao from Produto p where idProduto="+idProduto;
		TypedQuery<BigDecimal> custo = (TypedQuery<BigDecimal>) manager.createQuery(consulta);
		return custo.getSingleResult();
	}

	public void atualizaProdutoAtucao(List<ProdutoAtuacao> lista, Integer idProduto){

		Produto produto = manager.find(Produto.class, idProduto);
		
		for (ProdutoAtuacao  produtoAtuacao2: lista) {
			/*Atuacao atuacao = manager.getReference(Atuacao.class, produtoAtuacao2.getIdAtuacao().getIdAtuacao());
			
			
			ProdutoAtuacao pAtuacao = new ProdutoAtuacao();
			pAtuacao.setIdProduto(produto);
			pAtuacao.setIdAtuacao(atuacao);*/
			
			manager.detach(produtoAtuacao2);
		}
		
	}
	
	
	public Produto itemdetalhe(Integer idProduto){
		
		
		return null;
	}
		
}
