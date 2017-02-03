package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			List<Grupo> grupo = grupos.getResultList();
			
			for (int i = 0; i < grupo.size(); i++) {
				System.out.println(grupo.get(i).getProdutoGrupo().get(0).getProduto());
			}
			
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
		TypedQuery<Grupo> grupos = manager.createQuery(
				"from Grupo where idLista ="+idLista+" and opcional = 1 and categoriaGalderma > 1 order by ordemGrupo", Grupo.class);
		return grupos.getResultList();
	}
	
	/**
	 * Método que retorna a lista de categorias Cadastradas da Galderma
	 * 
	 * @return
	 */
	public List<GrupoCategoriaGalderma> categoriasGalderma() {
		TypedQuery<GrupoCategoriaGalderma> grupos = manager.createQuery(
				"from GrupoCategoriaGalderma", GrupoCategoriaGalderma.class);
		return grupos.getResultList();
	}
	
	

}
