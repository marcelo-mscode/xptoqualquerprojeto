package br.com.sysloccOficial.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.OrcamentoFornecedor;


@Repository
public class GrupoDAO {

	@Autowired private Utilitaria util;
	@PersistenceContext	private EntityManager manager;
	
	
	public List<Grupo> listaDeGrupos(Integer IdLista){
		String consulta = "from Grupo g where idLista="+IdLista;
		TypedQuery<Grupo>lista = manager.createQuery(consulta, Grupo.class);
		return lista.getResultList();
	}
	
	public Grupo persistiUMGrupoClonado(Lista _listaNova, Categoria umaCategoria,List<Grupo> grupos, int j) {
		 Grupo umGrupo;
		
		try {
			umGrupo = grupos.get(j);
			umGrupo.setIdgrupo(null);
			umGrupo.setCriacao(false);
			umGrupo.setIdLista(_listaNova);
			umGrupo.setIdCategoria(umaCategoria);
			
			manager.detach(umGrupo);
			manager.persist(umGrupo);
			manager.close();
			
			return umGrupo;
		} catch (Exception e) {
			
			System.out.println("Deu erro ao salvar um grupo clonado:" + e);
			return null;
		} 
		
	}
	
	public List<OrcamentoFornecedor> orcamentoFornecedor(Integer idGrupo){
		try {
			TypedQuery<OrcamentoFornecedor> orcamento = manager.createQuery("from OrcamentoFornecedor where grupo.idgrupo="+idGrupo,OrcamentoFornecedor.class);
			return orcamento.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public void salvaNovoOrcamento(String valor, Integer idFornecedor){
		Empresa empresa = manager.getReference(Empresa.class, idFornecedor);
		OrcamentoFornecedor orcamento = new OrcamentoFornecedor();
		
		orcamento.setValorOrcamento(new BigDecimal(util.formataValoresAprimorado(valor)));
		orcamento.setEmpresa(empresa);
		manager.persist(orcamento);
		manager.close();
	}
	
	public BigDecimal valorGrupoSemImposto(Integer idlista){
		try {
			String consulta = "SELECT sum(precoProduto*quantidade*quantidade2*diarias) FROM ProdutoGrupo where idGrupo in "
					+ "		  (SELECT idgrupo FROM Grupo where idlista = "+idlista+") and imposto = 0";
			Query valorTotal = manager.createQuery(consulta);
			
			double teste = (double) valorTotal.getSingleResult();
			
			BigDecimal testes = new BigDecimal(teste);
			
			return testes;
		} catch (Exception e) {
			
			//System.out.println("Não tem grupo sem imposto: "+e);
		
			return new BigDecimal("0");
		}
	}

	public BigDecimal valorGrupoComImposto(Integer idlista){
		try {
			String consulta = "SELECT sum(precoProduto*quantidade*quantidade2*diarias) FROM ProdutoGrupo where idGrupo in "
					+ "		  (SELECT idgrupo FROM Grupo where idlista = "+idlista+") and imposto = 1";
			Query valorTotal = manager.createQuery(consulta);
			
			double teste = (double) valorTotal.getSingleResult();
			
			BigDecimal testes = new BigDecimal(teste);
			
			return testes;
		} catch (Exception e) {
			
			//System.out.println("Não tem grupo sem imposto: "+e);
			
			return new BigDecimal("0");
		}
	}
	
	
}
