package br.com.sysloccOficial.ListaProducao.DeterminaQuantidade.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.model.DeterQuantpadrao;

@Repository
@Transactional
public class BuscaQualquerQuantidadeDeterminantePadrao implements BuscaQualquerQuantidadeDeterminante{

	@PersistenceContext
	private EntityManager manager;
	
	
	/*private Integer _idGrupoNovoOuAntigo;
	private Integer _prodGrupoAntg;
	*/
	
	@Override
	public Object buscaQualquerQuantidadeDeterm(Integer _idGrupoNovoOuAntigo, Integer _prodGrupoAntg) {

			try {
				DeterQuantpadrao novo =  manager.createQuery("FROM DeterQuantpadrao where grupo =" + _idGrupoNovoOuAntigo, DeterQuantpadrao.class)
						.getSingleResult();
				return novo;
			} catch (Exception e) {
				return null;
			}
	}





	

	
	

}
