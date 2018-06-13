package br.com.sysloccOficial.daos;

import java.awt.Window.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.EmpresaAtuacao;

@Repository
public class EmpresaDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void salva(Empresa e){
        manager.persist(e);
	}
	
	public void Atualiza(Empresa e){
		manager.merge(e);
	}
	
	public List<Empresa> mostra(Integer first, Integer max) {
       return manager.createQuery("select e from Empresa e order by habilitado, cliente,empresa",Empresa.class)
        .setMaxResults(max)
        .setFirstResult(first)  
        .getResultList();
       
    }
	
	//Lista empresas para o Job sem limite 
	public List<Object[]> mostraEmpresaJob() {
		    String consulta = "select e.idEmpresa, e.empresa from Empresa e where e.habilitado != 0 and cliente!= 0 order by empresa";
			Query query = manager.createQuery(consulta);	
			List<Object[]> lista = query.getResultList();
			return lista;
   }

	//Lista empresas para o Job sem limite 
	public List<Object[]> mostraEmpresaInteracao() {
		String consulta = "select e.idEmpresa, e.empresa FROM Empresa e where habilitado != 0 and fornecedor = 0 order by empresa";
		
		Query query = manager.createQuery(consulta);	
		List<Object[]> lista = query.getResultList();
		return lista;
	}
	
	public List<String> mostraEmpresasJob(){
		String consulta = "select e.empresa from Empresa e where e.habilitado !=0 order by empresa";
		TypedQuery<String> query = manager.createQuery(consulta, String.class);
		return query.getResultList();
		
	}
	
	public Empresa infoempresa(Integer id) {
		
		long tempoInicio = System.currentTimeMillis();
		
		Empresa e = manager.find(Empresa.class, id);
		JOptionPane.showMessageDialog(null, "Tempo de execução: "+ Utilitaria.calculaTempoExecucaoSegundos(tempoInicio)+" segundos.");
		return e;
	}
	
	public List<Empresa> mostraPorNome(String nome) {
		return manager.createQuery("select e from Empresa e where e.empresa like '"+nome+"%'",Empresa.class)
		/*return manager.createQuery("select e from Empresa e where idEmpresa=195",Empresa.class)*/
		/*.setMaxResults(20)*/
		.getResultList();
    }

	public List<EmpresaAtuacao> listaEmpresaAtuacao(Integer idEmpresa) {
		String consulta = "select e from EmpresaAtuacao e join fetch e.idAtuacao atuacao where e.idEmpresa="+idEmpresa+" order by atuacao.atuacao";
		
		Query query = manager.createQuery(consulta, EmpresaAtuacao.class);
		/*query.setParameter("idProduto", idProduto);*/
		
		return query.getResultList();
	}
	public String pegaLogotipo(Integer id){
		String consulta ="select e.logotipo from Empresa e where idEmpresa=:id";
		TypedQuery<String> query = manager.createQuery(consulta, String.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}

	public List<String> listaEmpresaPorNome() {
		TypedQuery<String> listaEmpresa = manager.createQuery("select e.empresa from Empresa e where habilitado != 0 and cliente!= 0 order by empresa", String.class);
		return listaEmpresa.getResultList();
	}
	
	public Empresa infoEmpresaProducao(Integer idEmpresa){
		String consulta = "from Empresa e where idEmpresa="+idEmpresa;
		Query query = manager.createQuery(consulta);
		
		try {  
			return (Empresa) query.getSingleResult();
	    } catch ( NoResultException nre ) {  
	        return null;  
	    }  
	}

	public List<Object[]> listaFornecedores(){
		String consulta = "select e.idEmpresa,e.empresa from Empresa e where fornecedor <> 0 and habilitado != 0 order by empresa";
		Query query = manager.createQuery(consulta);
		List<Object[]> lista = query.getResultList();
		return lista;
	}

	public Object[] listaFornecedoresPorId(Integer idEmpresa){
		String consulta = "select e.idEmpresa,e.empresa from Empresa e where idEmpresa="+ idEmpresa;
		Query query = manager.createQuery(consulta);
		Object[] lista = (Object[]) query.getSingleResult();
		return lista;
	}
	
	public List<Object[]> listaEmpresasClientes(){
		String consulta = "select e.idEmpresa,e.empresa from Empresa e where habilitado != 0 and cliente != 0 order by empresa";
		Query query = manager.createQuery(consulta);
		List<Object[]> lista = query.getResultList();
		return lista;
	}
	
	public List<Object[]> listaContatoEmpresa(Integer idEmpresa){
		String consulta = "select c.idContato,c.contato from Contato c where idEmpresa=:idEmpresa";
		Query query = manager.createQuery(consulta).setParameter("idEmpresa", idEmpresa);
		List<Object[]> lista = query.getResultList();
		return lista;
	}
	
	
	public void deletaEmpresa(Integer idEmpresa){
		Empresa empresa = manager.find(Empresa.class, idEmpresa);
		manager.remove(empresa);
	}
	
	public List<Object[]> listaBuscaAjaxEmpresas(String nome){
		
		if(nome==""){  return null;	}else
		{
		String consulta = "select c from Empresa c where empresa like '"+nome+"%' order by habilitado, cliente,empresa";
		
		Query lista = manager.createQuery(consulta);
		return lista.getResultList();
		}
	}

	public List<Tuple> listaBuscaAjaxEmpresasPorNome(String nome){
		
		if(nome==""){  return null;	}else
		{
			
			CriteriaBuilder cb = manager.getCriteriaBuilder();
			CriteriaQuery<Tuple> c = cb.createQuery(Tuple.class);
			Root<Empresa> l = c.from(Empresa.class);
			c.multiselect(
					l.<String>get("idEmpresa").alias("empresa.idEmpresa"),
					l.<String>get("empresa").alias("empresa.empresa"),
					l.<String>get("cliente").alias("empresa.cliente"),
					l.<String>get("fornecedor").alias("empresa.fornecedor"),
					l.<String>get("prospect").alias("empresa.prospect"),
					l.<String>get("habilitado").alias("empresa.habilitado")
					);
			
			Predicate perdicate = cb.like(l.<String>get("empresa"), nome+"%");
			c.where(perdicate);
			c.orderBy(cb.asc(l.<Boolean>get("habilitado")),cb.asc(l.<Boolean>get("cliente")),cb.asc(l.<Boolean>get("empresa")));
			
			TypedQuery<Tuple> query = manager.createQuery(c);
			return query.getResultList();
		}
	}
	
	public Empresa retornaEmpresa(int idEmpresa){
		return manager.find(Empresa.class, idEmpresa);
	}
	
	
	public List<Contato> listaEmpresasParaExcel(){
//		String consulta = "from Empresa e join fetch e.contato c where e.empresa like 'bayer%' and e.habilitado <> 0";
//		String consulta = "from Contato c join fetch c.empresa e where c.empresa.empresa like 'bayer%' or c.empresa.empresa like 'covestro%' and c.habilitado <> 0 order by e.empresa.empresa";
		String consulta = "from Contato c join fetch c.empresa e where c.habilitado <> 0 and cliente <> 0 order by e.empresa.empresa";
		TypedQuery<Contato> lista = manager.createQuery(consulta,Contato.class);
		return lista.getResultList();
	}
	
	public List<Tuple> listaTodasEmpresas(Integer first, Integer max){
		try {
			CriteriaBuilder cb = manager.getCriteriaBuilder();
			CriteriaQuery<Tuple> c = cb.createQuery(Tuple.class);
			Root<Empresa> l = c.from(Empresa.class);
			c.multiselect(
					l.<String>get("idEmpresa").alias("empresa.idEmpresa"),
					l.<String>get("empresa").alias("empresa.empresa"),
					l.<String>get("cliente").alias("empresa.cliente"),
					l.<String>get("fornecedor").alias("empresa.fornecedor"),
					l.<String>get("prospect").alias("empresa.prospect"),
					l.<String>get("habilitado").alias("empresa.habilitado")
					);
			
			c.orderBy(cb.asc(l.<Boolean>get("habilitado")),cb.asc(l.<Boolean>get("cliente")),cb.asc(l.<Boolean>get("empresa")));
			
			TypedQuery<Tuple> query = manager.createQuery(c);
			List<Tuple> resultado = query
			.setMaxResults(max)
	        .setFirstResult(first)  
	        .getResultList();

			return resultado;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Object[]> buscaListaClienteFornecedorProspect(String criterio){
		String consulta = "SELECT e.idEmpresa,"
								+ " e.empresa, "
								+ " e.cliente,"
								+" e.fornecedor,"
								+" e.prospect,"
								+" e.habilitado "
								+" from Empresa e where "+criterio+" order by habilitado,empresa";
		try{
		TypedQuery<Object[]> query = manager.createQuery(consulta,Object[].class);
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Deu erro ao buscar Listas Cliente: "+e);
			
			return null;
		}
	}
	
		
}
