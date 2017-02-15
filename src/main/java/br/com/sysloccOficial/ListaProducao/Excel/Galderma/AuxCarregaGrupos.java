package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;

@Service
public class AuxCarregaGrupos {
	
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@PersistenceContext	private EntityManager manager;
	
	
	public List<Grupo> listaGruposNAOOpcionais(Integer idLista){
		List<Grupo> listaGrupos = retornaGruposGalderma(idLista);
		return listaGrupos;
	}
	public List<Grupo> listaGruposOpcionais(Integer idLista){
		List<Grupo> listaGrupos = retornaGruposOpcionais(idLista);
		return listaGrupos;
	}
	
	/**
	 * Método para retornar uma lista de Grupos de acordo com o idLista da Lista
	 * não Opcionais
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
	public List<Grupo> retornaGruposOpcionais(Integer idLista){
		try {
			TypedQuery<Grupo> grupos = manager.createQuery(
					"from Grupo g where idLista ="+idLista+" and opcional = 1 and g.grupoCategoriaGalderma.idCategoriaGalderma > 1 order by g.ordemGrupo", Grupo.class);
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
		
		
		TypedQuery<Integer> gruposIds = manager.createQuery(
				"SELECT distinct(g.grupoCategoriaGalderma.idCategoriaGalderma) FROM Grupo g where idlista= "+idLista+" and g.grupoCategoriaGalderma.idCategoriaGalderma > 1 order by categoriaGalderma", Integer.class);
		List<Integer> gruposIdsGalderma = gruposIds.getResultList();
		
		String consulta =  Utilitaria.limpaSqlComListStastico("from GrupoCategoriaGalderma where idCategoriaGalderma in ("+gruposIdsGalderma+")");
		
		TypedQuery<GrupoCategoriaGalderma> grupos = manager.createQuery(consulta, GrupoCategoriaGalderma.class);
		
		return grupos.getResultList();
	}
	
	

}
