package br.com.sysloccOficial.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.UtilitariaValores;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.ContatoInfoBasica;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.EmpresaAtuacao;
import br.com.sysloccOficial.model.EmpresaInfoBasica;

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
		
		Empresa e = manager.find(Empresa.class, id);
	//	System.out.println("Nome da empresa: "+e.getEmpresa());
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
		TypedQuery<Object[]> query = manager.createQuery(consulta,Object[].class);
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
		TypedQuery<Object[]> query = manager.createQuery(consulta, Object[].class);
		return query.getResultList();
	}
	
	public List<Object[]> listaContatoEmpresa(Integer idEmpresa){
		String consulta = "select c.idContato,c.contato from Contato c where idEmpresa=:idEmpresa";
		TypedQuery<Object[]> query = manager.createQuery(consulta, Object[].class).setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}
	
	
	public void deletaEmpresa(Integer idEmpresa){
		Empresa empresa = manager.find(Empresa.class, idEmpresa);
		manager.remove(empresa);
	}
	
	public List<Object[]> listaBuscaAjaxEmpresas(String nome){
		
		if(nome==""){  return null;	}else
		{
		String consulta = "select c from Empresa c where empresa like '"+nome+"%' order by habilitado, cliente,empresa";
		TypedQuery<Object[]> lista = manager.createQuery(consulta,Object[].class);
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
		String consulta = "from Contato c join fetch c.empresa e where c.habilitado <> 0 and cliente <> 0 and fornecedor <> 0 order by e.empresa.empresa";
		TypedQuery<Contato> lista = manager.createQuery(consulta,Contato.class);
		manager.close();
		
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
	
	
	public List<Object[]> listaEmpresaTeste(String parteConsulta){
		try {
			String consulta = "SELECT idEmpresa, empresa, telefone FROM Empresa where habilitado <> 0 and "+parteConsulta+" order by empresa";
			TypedQuery<Object[]> query = manager.createQuery(consulta, Object[].class);
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao carregar Empresas: "+e);
			return null;
		}
	}
	
	public List<EmpresaInfoBasica> listaEmpresaTesteRefatorada(String parteConsulta){
		try {
			
			List<EmpresaInfoBasica> empresaInfo = new ArrayList<EmpresaInfoBasica>();
			
			String consulta = "SELECT idEmpresa, empresa, telefone FROM Empresa where habilitado <> 0 and "+parteConsulta+" order by empresa";
			TypedQuery<Object[]> query = manager.createQuery(consulta, Object[].class);
			List<Object[]> lista = query.getResultList();
			
			for (int i = 0; i < lista.size(); i++) {
				EmpresaInfoBasica info = new EmpresaInfoBasica();
				info.setIdEmpresa((Integer) lista.get(i)[0]);
				info.setEmpresa((String) lista.get(i)[1]);
				info.setTelefone((String)lista.get(i)[2]);
				empresaInfo.add(info);
			}
			
			return empresaInfo;
		} catch (Exception e) {
			System.out.println("Erro ao carregar Empresas: "+e);
			return null;
		}
	}
	
	public List<Object[]> listaContatos(List<Object[]> listaEmpresa){
		try {
			List<Integer> idEmpresas = new ArrayList<Integer>();
			for (int i = 0; i < listaEmpresa.size(); i++) {
				idEmpresas.add((Integer) listaEmpresa.get(i)[0]);
			}		
			String consulta = "SELECT idContato, contato, empresa.idEmpresa from Contato c where idEmpresa IN ("+idEmpresas+")";
			String consultaLimpa = UtilitariaValores.limpaConsultaRetornoStatico(consulta);
			TypedQuery<Object[]> query = manager.createQuery(consultaLimpa, Object[].class);
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro carregar Contatos: "+e);
			return null;
		}
	}

	public List<ContatoInfoBasica> listaContatosRefatorada(List<EmpresaInfoBasica> listaEmpresa){
		try {
			List<Integer> idEmpresas = new ArrayList<Integer>();
			List<ContatoInfoBasica> listaContatos = new ArrayList<ContatoInfoBasica>();
			
			for (int i = 0; i < listaEmpresa.size(); i++) {
				idEmpresas.add((Integer) listaEmpresa.get(i).getIdEmpresa());
			}		
		
			String consulta = "SELECT idContato, contato, empresa.idEmpresa from Contato c where idEmpresa IN ("+idEmpresas+") and habilitado <> 0";
			String consultaLimpa = UtilitariaValores.limpaConsultaRetornoStatico(consulta);
			TypedQuery<Object[]> query = manager.createQuery(consultaLimpa, Object[].class);
			List<Object[]> lista = query.getResultList();
			
			
			for (int i = 0; i < lista.size(); i++) {
				ContatoInfoBasica contato = new ContatoInfoBasica();
				contato.setIdContato((Integer) lista.get(i)[0]);
				contato.setContato((String) lista.get(i)[1]);
				contato.setIdEmpresa((Integer) lista.get(i)[2]);
				listaContatos.add(contato);
			}
			return listaContatos;
		} catch (Exception e) {
			System.out.println("Erro carregar Contatos: "+e);
			return null;
		}
	}

	public List<Object[]> listaComunicador(List<Object[]> listaContatos){
		try {
			List<Integer> idContatos = new ArrayList<Integer>();
			for (int i = 0; i < listaContatos.size(); i++) {
				idContatos.add((Integer) listaContatos.get(i)[0]);
			}		
			
		//	JOptionPane.showMessageDialog(null, "ListaContatos : "+ listaContatos.size());
			
			String consulta = "SELECT comunicador, contato.idContato from Comunicador c where contato IN ("+idContatos+")";
			String consultaLimpa = UtilitariaValores.limpaConsultaRetornoStatico(consulta);
			TypedQuery<Object[]> query = manager.createQuery(consultaLimpa, Object[].class);
		
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao carregar Comunicadores: "+e);
			return null;
		}
	}
	public List<Object[]> listaComunicadorRefatorada(List<ContatoInfoBasica> listaContatos){
		try {
			List<Integer> idContatos = new ArrayList<Integer>();
			for (int i = 0; i < listaContatos.size(); i++) {
				idContatos.add(listaContatos.get(i).getIdContato());
			}		
			
			String consulta = "SELECT comunicador, contato.idContato from Comunicador c where contato IN ("+idContatos+")";
			String consultaLimpa = UtilitariaValores.limpaConsultaRetornoStatico(consulta);
			TypedQuery<Object[]> query = manager.createQuery(consultaLimpa, Object[].class);
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao carregar Comunicadores: "+e);
			return null;
		}
	}
	
	
		
}
