package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.CenariosGalderma;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.Lista;

@Service
public class AuxCarregaGrupos {
	
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@PersistenceContext	private EntityManager manager;
	
	
	public List<Grupo> listaGruposNAOOpcionais(Integer idLista){
		List<Grupo> listaGrupos = retornaGruposGalderma(idLista);
		return listaGrupos;
	}
	public List<Grupo> listaGruposOpcionais(List<Integer> idLista){
		List<Grupo> listaGrupos = retornaGruposOpcionais(idLista);
		return listaGrupos;
	}
	
	/**
	 * Método para retornar uma lista de Grupos de acordo com o idLista da Lista
	 * não Opcionais com Categorias Galderma selecionadas
	 */
	public List<Grupo> retornaGruposGalderma(Integer idLista){
		
		try {
			TypedQuery<Grupo> grupos = manager.createQuery(
					"from Grupo g where idLista ="+idLista+" and opcional = 0 and g.grupoCategoriaGalderma.idCategoriaGalderma > 1 order by g.ordemGrupo", Grupo.class);
			return grupos.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Método para retornar uma lista de Grupos de acordo com o idLista da Lista
	 * ------> Opcionais
	 */
	public List<Grupo> retornaGruposOpcionais(List<Integer> idLista){
		try {
			String consulta =  Utilitaria.limpaSqlComListStastico("from Grupo g where idLista in ("+idLista+") and opcional = 1 and categoriaGalderma != null order by idLista");
			TypedQuery<Grupo> grupos = manager.createQuery(consulta, Grupo.class);
			return grupos.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Método que retorna a lista de categorias Cadastradas da Galderma
	 * 
	 * @return
	 */
	public List<GrupoCategoriaGalderma> categoriasGalderma(Integer idLista) {
		
		try {
			
			TypedQuery<Integer> gruposIds = manager.createQuery(
					"SELECT distinct(g.grupoCategoriaGalderma.idCategoriaGalderma) FROM Grupo g where idlista= "+idLista+" and opcional = 0 order by categoriaGalderma", Integer.class);
			List<Integer> gruposIdsGalderma = gruposIds.getResultList();
			
			String consulta =  Utilitaria.limpaSqlComListStastico("from GrupoCategoriaGalderma where idCategoriaGalderma in ("+gruposIdsGalderma+")");
			
			TypedQuery<GrupoCategoriaGalderma> grupos = manager.createQuery(consulta, GrupoCategoriaGalderma.class);
			
			return grupos.getResultList();
			
		} catch (Exception e) {
			
			return null;
		}
	}
	
	/**
	 * Novo método para pegar as categorias dos Grupos
	 * 
	 * 
	 * @param integer
	 * @return
	 */
	public List<Categoria> categoriasDaLista(Integer idLista) {
		try {
			
			TypedQuery<Integer> gruposIds = manager.createQuery(
					"SELECT distinct(g.idCategoria.idCategoria) FROM Grupo g where idlista= "+idLista+" and opcional = 0 order by categoriaGalderma", Integer.class);
			List<Integer> gruposIdsGalderma = gruposIds.getResultList();
			
			String consulta =  Utilitaria.limpaSqlComListStastico("from Categoria where idCategoria in ("+gruposIdsGalderma+")");
			
			TypedQuery<Categoria> grupos = manager.createQuery(consulta, Categoria.class);
			
			return grupos.getResultList();
			
		} catch (Exception e) {
			
			return null;
		}
	}
	
	
	
	
	
	
	
	/**
	 * Pega lista de todas as categorias
	 * 
	 * Utilizado em opcionais Galderma
	 * 
	 * 
	 * 
	 */
	public List<GrupoCategoriaGalderma> pegaTodasCategoriasGalderma(){
		TypedQuery<GrupoCategoriaGalderma> categorias = manager.createQuery("from GrupoCategoriaGalderma where idCategoriaGalderma > 1 order by idCategoriaGalderma",GrupoCategoriaGalderma.class);
		return categorias.getResultList();
	}
	
	/**
	 * Pega Job da Lista
	 * 
	 */
	public Job pegaJob(Integer idLista){
		try {
			Lista lista = manager.find(Lista.class, idLista);
			return lista.getIdJob();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Monta Lista de String com informações Galderma de cada Lista 
	 * A lista começa pela mãe e depois pelas filhas em ordem de cenário
	 * 
	 */
	public List<String> listaInfoGalderma(Integer idLista){
		
		List<String> deadlines = new ArrayList<String>();
		

				String mae = "from CenariosGalderma where planilhaMae = "+idLista;
				
				TypedQuery<CenariosGalderma> cenarios = manager.createQuery(mae, CenariosGalderma.class);
				
				List<CenariosGalderma> listaCenariosGaldermas = cenarios.getResultList();
				
				
				if(!listaCenariosGaldermas.isEmpty()){

					Lista lista = manager.find(Lista.class, idLista);
					deadlines.add(lista.getInfoConsolidadoGalderma());
					
					
					for (int i = 0; i < listaCenariosGaldermas.size(); i++) {
						Lista listas = manager.find(Lista.class, listaCenariosGaldermas.get(i).getPlanilhaFilha());
						deadlines.add(listas.getInfoConsolidadoGalderma());
					}
					
					return deadlines;
					
				}else{
				
					
					
					try {

						String idPlanilhaMae = "select planilhaMae from CenariosGalderma where planilhaFilha = "+idLista+" order by cenarioFilha";
						TypedQuery<Integer> idPlanMae = manager.createQuery(idPlanilhaMae, Integer.class);
						Lista listaMae = manager.find(Lista.class, idPlanMae.getSingleResult());
						deadlines.add(listaMae.getInfoConsolidadoGalderma());
						
						TypedQuery<CenariosGalderma> cenarios2 = manager.createQuery("from CenariosGalderma where planilhaMae = "+idPlanMae.getSingleResult(), CenariosGalderma.class);
						List<CenariosGalderma> listaCenariosGaldermas2 = cenarios2.getResultList();
						
						for (int i = 0; i < listaCenariosGaldermas2.size(); i++) {
							Lista listas = manager.find(Lista.class, listaCenariosGaldermas2.get(i).getPlanilhaFilha());
							deadlines.add(listas.getInfoConsolidadoGalderma());
						}
						return deadlines;
					} catch (Exception e) {
						
						deadlines.add("Nenhuma Informação Cadastrada no cenário!");
						
//						JOptionPane.showMessageDialog(null, "AuxCarregaGrupos, linha 170: "+e);	// TODO: handle exception
						return deadlines;
					}
					
					
					
			}
			
	}

}
