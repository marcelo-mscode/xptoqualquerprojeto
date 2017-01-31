package br.com.sysloccOficial.ListaProducao.DeterminaQuantidade.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.com.sysloccOficial.model.DeterminaQuantidades;

@Repository
public class BuscaQuantidadeDeterminante implements BuscaQualquerQuantidadeDeterminante{

	@PersistenceContext
	private EntityManager manager;
	
	private Integer _prodGrupoAntg;
	
	
	/*public BuscaQuantidadeDeterminante(Integer idGrupoAntigo,Integer prodGrupoAntg) {
		this._idGrupoAntigo = idGrupoAntigo;
		this._prodGrupoAntg = prodGrupoAntg;
	}*/

	@Override
	public Object buscaQualquerQuantidadeDeterm(Integer _idGrupoAntigo, Integer _prodGrupoAntg) {
		try {
			DeterminaQuantidades grupoAntigo =  manager.createQuery("FROM DeterminaQuantidades where grupo ="
					+ _idGrupoAntigo + " and produtoGrupo ="+_prodGrupoAntg,DeterminaQuantidades.class).getSingleResult();
			return grupoAntigo;
		} catch (Exception e) {
			System.out.println("Erro é certeiro: "+e);
			return null;
		}
		
	}
	
	
}
