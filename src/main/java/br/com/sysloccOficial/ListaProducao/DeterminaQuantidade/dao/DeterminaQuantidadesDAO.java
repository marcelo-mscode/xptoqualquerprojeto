package br.com.sysloccOficial.ListaProducao.DeterminaQuantidade.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.com.sysloccOficial.model.DeterQuantpadrao;
import br.com.sysloccOficial.model.DeterminaQuantidades;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.ProdutoGrupo;


@Repository
public class DeterminaQuantidadesDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void clonaDeterminaQuantidades(Grupo umGrupo, ProdutoGrupo umProdutoGrupo) {
		DeterminaQuantidades determinaQuantidades = new DeterminaQuantidades();
		 determinaQuantidades = umGrupo.getDeterm();
		 determinaQuantidades.setIdDeterm(null);
		 determinaQuantidades.setProdutoGrupo(umProdutoGrupo);
		 manager.detach(determinaQuantidades);
		 manager.persist(determinaQuantidades);
	}
	
	
	public void clonaDeterminaQuantPadrao(Grupo umGrupo) {
		DeterQuantpadrao determinaQuantPadrao = new DeterQuantpadrao();
		 determinaQuantPadrao = umGrupo.getDetermPadrao();
		 determinaQuantPadrao.setIdDetermPadrao(null);
		 manager.detach(determinaQuantPadrao);
		 manager.persist(determinaQuantPadrao);
	}

}
