package br.com.sysloccOficial.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Pagamento;
import br.com.sysloccOficial.model.TipoPagamento;


@Repository
public class PagamentoDAO {

	
	@PersistenceContext
	private EntityManager manager;
	
	
	public List<Pagamento> listaPagamento(Integer idEmpresa){
		Empresa empresa = manager.find(Empresa.class, idEmpresa);
		String consulta = "select p from Pagamento p join fetch p.idtipoPagamento pg  where p.idEmpresa="+empresa.getIdEmpresa();
		TypedQuery<Pagamento> query = manager.createQuery(consulta, Pagamento.class);
		return query.getResultList();
	}
	
	public List<TipoPagamento> listaTipoPagamento(){
		String consulta = "from TipoPagamento";
		TypedQuery<TipoPagamento> query = manager.createQuery(consulta, TipoPagamento.class);
		return query.getResultList();
	}
}
