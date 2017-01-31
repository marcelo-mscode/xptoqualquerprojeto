package br.com.sysloccOficial.daos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.consultas.consultasAvancadas.ConsultasPassaSql;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.GrupoCategoriaBayer;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.prospeccao.Prospeccao;



@Repository
public class ListaDAO {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private ConsultasPassaSql c;
	@Autowired private UtilitariaDatas utilDatas;
	
	
	public void SalvaLista(Lista l){
		manager.persist(l);
	}

	public List<Lista> listaProducao(){
		String consulta = "select distinct(l) from Lista l left join l.idJob.contatos order by idLista desc";
		Query query = manager.createQuery(consulta, Lista.class).setMaxResults(21); 
		return query.getResultList();
	}
	
	
	public List<Integer>idListas() throws ParseException{
		Date dataMeses = utilDatas.subtraiMeses(6);
		String data = utilDatas.converteDateParaStringInternacional(dataMeses); 
		Query query = manager.createQuery("select l.idLista from Lista l where dataCriacao > '"+ data +"' order by listaCod,revisao desc");
		return query.getResultList();
	}

	/**
	 * Método para retornar apenas os listaCod das Listas
	 * de até 6 meses atras
	 * 
	 * Esse método retorna sem repetição os listaCod que 
	 * será usado para montar o filtro das Listas por ordem
	 * de listaCod e Revisão .
	 * 
	 * 
	 * 
	 */
	public List<Object[]>idListasTeste() throws ParseException{
		Date dataMeses = utilDatas.subtraiMeses(6);
		
		String data = utilDatas.converteDateParaStringInternacional(dataMeses);
		Query query = manager.createQuery("select distinct(l.listaCod),l.listaCod from Lista l where dataCriacao > '"+ data +"' order by listaCod desc");
		return query.getResultList();
	}
	
	/**
	 * Método que retorna os idLista por codLista e Revisão do mais novo
	 * pro mais antigo.
	 * 
	 * Ele recebe um listaCod e busca todos os ids de Lista por essa refencia
	 * ordenado por revisao do mais novo para o mais antigo
	 * 
	 * 
	 * @param codListas
	 * @return
	 */
	
	public List<Integer> retornaCodListas(String codListas){
		String consulta = "select idLista from Lista where listaCod like '%"+codListas+"%' order by revisao desc";
		TypedQuery<Integer> q = manager.createQuery(consulta, Integer.class);
		return q.getResultList();
	}
	
	
	/**
	 * Teste para montar lista Ajax por empresa Em Lista de produção 
	 * 
	 * 
	 *
	 */
	public List<String> t (String termo){
		String t = "select distinct(l.listaCod) from Lista l where l.idJob.empresa.idEmpresa = "+termo + " order by listaCod desc";
		
		Query q = manager.createQuery(t);
		return q.getResultList();
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public List<Object[]> Listas(List<Integer> idLista){
		String consultas = "select l.idLista,l.lista,l.revisao,l.idJob,l.idlistaEstatus,l.listaCod from Lista l"
				+ " where l.idLista in "+idLista;
		Query q = manager.createQuery(consultas);
		return q.getResultList();
		
	}
	
	
	
	
	public Object condicao(Integer idLista, String condicao) {
		String consulta = "select "+ condicao +" from Lista l where l.idLista =" +idLista+" order by revisao desc";
		return c.retorna(consulta);
	}
	
		
// ------------------------------------------------------------------------------------------------------------------------------ //	
	
	
	public List<Lista> editaLista(Integer idLista){
		
		String consulta = "select l from Lista l where idLista=:idLista";
		
		TypedQuery<Lista> lista = manager.createQuery(consulta, Lista.class);
		lista.setParameter("idLista", idLista);
		
		return lista.getResultList();
	}
	
	
	
	public List<Categoria> listadeCategorias(Integer idLista){
		String consulta = "select c from Categoria c where idLista="+idLista+" order by categoriaOrdem";
		Query query = manager.createQuery(consulta, Categoria.class);
		List<Categoria> t = removeValoresRepetidosLista(query.getResultList());
		return query.getResultList();
	}

	public List<GrupoCategoriaGalderma> listadeCategoriasGalderma(){
		String consulta = "from GrupoCategoriaGalderma";
		TypedQuery<GrupoCategoriaGalderma> query = manager.createQuery(consulta, GrupoCategoriaGalderma.class);
		return query.getResultList();
	}

	public List<GrupoCategoriaBayer> listadeCategoriasBayer(){
		String consulta = "from GrupoCategoriaBayer";
		TypedQuery<GrupoCategoriaBayer> query = manager.createQuery(consulta, GrupoCategoriaBayer.class);
		return query.getResultList();
	}
	
	
	
	
	
	public List<Categoria> removeValoresRepetidosLista(List<Categoria> categoria) {
		for (int i = 0; i < categoria.size(); i++) {
			for (int j = 0; j < categoria.get(i).getGrupo().size(); j++) {
				for (int j2 = 0; j2 < categoria.get(i).getGrupo().get(j).getProdutoGrupo().size(); j2++) {
					Object a = categoria.get(i).getGrupo().get(j).getProdutoGrupo().get(j2);
					for (int j3 = j2+1; j3 < categoria.get(i).getGrupo().get(j).getProdutoGrupo().size(); j3++) {
						Object b = categoria.get(i).getGrupo().get(j).getProdutoGrupo().get(j3);
						if (a.equals(b)) {
							categoria.get(i).getGrupo().get(j).getProdutoGrupo().remove(j3);
							j3--;
						}
					}
				}
			}
		}
		return categoria;
	}
	
	
	
	
	
	public List<Lista> listaMenuProducao(){
		String consulta = "select distinct(l) from Lista l where idlistaEstatus = 5 order by dataAprovacao";
		Query query = manager.createQuery(consulta, Lista.class); 
		return query.getResultList();
	}
	
	public List<Grupo> listaDeGrupos(Integer IdLista){
		String consulta = "from Grupo g where idLista="+IdLista;
		TypedQuery<Grupo>lista = manager.createQuery(consulta, Grupo.class);
		return lista.getResultList();
	}
	
public Object listadeCategoriaPorId(Integer idcategoria){
		
		String consulta = "select c from Categoria c where idcategoria="+idcategoria;
		Query query = manager.createQuery(consulta, Categoria.class);
		
		return query.getSingleResult();
	}

	public List<Object[]> listasAjaxConsulta(String tipo){
		String consulta = "select l.idLista,l.lista,l.revisao,l.idJob,l.idlistaEstatus,l.listaCod from Lista l "+ tipo;
		Query query = manager.createQuery(consulta); 
		List<Object[]> lista = query.getResultList();
		return lista;
	}
	
	
	public Object[] listasAjaxConsultaLista(String tipo){
		String consulta = "select l.idLista,l.lista,l.revisao,l.idJob,l.idlistaEstatus,l.listaCod from Lista l "+ tipo;
		Query query = manager.createQuery(consulta); 
		Object[] lista = (Object[]) query.getSingleResult();
		return lista;
	}
	
	
	public List<Object[]> listasAjaxConsultaAvancada(String tipo){
		String consulta = "select l.idLista,l.lista,l.revisao,l.idJob,l.idlistaEstatus,l.listaCod from Lista l "+ tipo;
		
		Query query = manager.createQuery(consulta); 
		List<Object[]> lista = query.getResultList();
		return lista;
	}
	
	
/* ------------------------------------------ Monta código da Lista ---------------------------------------------------------- */

	
	// Quantidade de lista do mês atual
	public Number codLista(){
		
		Calendar c= Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM");  
	    String a = s.format(c.getTime());
		
		
		String consulta =  "Select count(l) FROM Lista l where dataCriacao like '%"+a+"%'";
		
		TypedQuery<Number> query1 = manager.createQuery(consulta, Number.class);
		
		Number result = query1.getSingleResult();
		
		return result;
	}
	
	public Number qtdJobsPorLista(Integer idJob){
		String consulta =  "Select count(l) FROM Lista l where idJob="+idJob +"and revisao = 0";
		TypedQuery<Number> query1 = manager.createQuery(consulta, Number.class);
		
		Number result = query1.getSingleResult();
		
		return result;
    }
	
	public String pegaCodigoJob(Integer idJob){
		TypedQuery<Integer> q = manager.createQuery("select codJob from Job where idJob="+idJob, Integer.class);
		Integer cod = q.getSingleResult();
		String CodJob = cod.toString();
		return CodJob;
	}
	
	

	public List<Object[]> listasPorJob(Integer idJob){
		String consulta  = "select l.idLista, l.listaCod, l.lista from Lista l where idJob="+idJob;
		Query query = manager.createQuery(consulta);
		
		List<Object[]> lista = query.getResultList();		
		return lista;
	}
	
	public List<Object[]> listasPorIdLista(Integer idLista){
		String consulta  = "select c.idcategoria, c.categoria from Categoria c where idLista="+idLista+" order by categoriaOrdem";
		Query query = manager.createQuery(consulta);
		
		List<Object[]> lista = query.getResultList();		
		return lista;
	}
	
	public List<Integer> idListasPorIdLista(Integer idLista){
		String consulta  = "select c.idcategoria from Categoria c where idLista="+idLista+" order by categoriaOrdem";
		TypedQuery<Integer> lista = manager.createQuery(consulta,Integer.class);
		
//		List<Object[]> lista = query.getResultList();		
		return lista.getResultList();
	}
	
	

	public List<Categoria> listasPorIdListaCompleto(Integer idLista){
		String consulta  = "select c from Categoria c where idLista="+idLista+" order by categoriaOrdem";
	    Query query = manager.createQuery(consulta, Categoria.class);
	    
		return query.getResultList();
	}
	
	
	public Categoria c (Integer idCategoria, Integer idLista){
		String consulta = "from Categoria c where idcategoria="+ idCategoria;
		TypedQuery<Categoria> query = manager.createQuery(consulta,Categoria.class);
		return query.getSingleResult();
	}

	public Grupo g (Integer idGrupo){
		String consulta = "from Grupo c where idgrupo="+ idGrupo;
		TypedQuery<Grupo> query = manager.createQuery(consulta,Grupo.class);
		return query.getSingleResult();
	}
	
	
	public void mergeCategoria(Integer idCategoria, Integer novaOrdem, Integer idLista){
		Categoria categoria = new Categoria();
		
		categoria = c(idCategoria, idLista);
		
		categoria.setCategoriaOrdem(novaOrdem);
		manager.merge(categoria);
	}


	public void mergeGrupo(Integer idGrupo, Integer novaOrdem){
		Grupo grupo = new Grupo();
		grupo = g(idGrupo);
		grupo.setOrdemGrupo(novaOrdem);
		manager.merge(grupo);
	}


	public List<Grupo> listadeGruposPorIdLista(Integer idLista) {
		String consulta = "from Grupo g where idLista ="+idLista+" order by ordemGrupo";
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}
	
	public Grupo ordemGrupo(Integer idcategoria){
		String consulta = "from Grupo where idcategoria ="+idcategoria+" order by IdGrupo desc";
		
		try {
			Query query = manager.createQuery(consulta).setMaxResults(1);
			Grupo ordem = (Grupo) query.getSingleResult();		
			
			if(ordem.getOrdemGrupo() == null){
				colocaOrdemEmGruposAnterioresAoSitemaNovo(idcategoria);
			}
			return ordem;

		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Não tem resultado");
			return null;
		}
	}
	
	/**
	 * Método usado para colocar os grupos antigos em ordem de exibição na lista de produção.
	 * Esse método é necessário para o caso de Listas anteriores ao sistema novo quando
	 * não existia uma ordem para os grupos. 
	 */
	public void colocaOrdemEmGruposAnterioresAoSitemaNovo(Integer idcategoria){
		String consulta = "from Grupo where idcategoria ="+idcategoria+" order by IdGrupo";
		try {
			Query q = manager.createQuery(consulta);
			List<Grupo> listaGruposSemOrdem = q.getResultList();
			
			for (int i = 0; i < listaGruposSemOrdem.size(); i++) {
				listaGruposSemOrdem.get(i).setOrdemGrupo(i);
			}
			
			listadeGruposPorIdLista(idcategoria);
		} catch (Exception e) {

		}
	}

}
