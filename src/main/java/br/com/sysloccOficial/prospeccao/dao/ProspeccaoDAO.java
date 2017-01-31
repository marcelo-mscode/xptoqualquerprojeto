package br.com.sysloccOficial.prospeccao.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Pagamento;
import br.com.sysloccOficial.model.TipoPagamento;
import br.com.sysloccOficial.model.Usuario;
import br.com.sysloccOficial.model.prospeccao.InteracaoProspeccao;
import br.com.sysloccOficial.model.prospeccao.Prospeccao;


@Repository
@Transactional
public class ProspeccaoDAO {

@Autowired InteracoesDAO interacoesDAO;
@Autowired Utilitaria util;
@PersistenceContext private EntityManager manager;

	
	public void salvaProspeccaoNova(Prospeccao p){
		Empresa e = manager.find(Empresa.class, p.getIdEmpresaTrans());
		p.setIdEmpresa(e);
		manager.persist(p);
		Usuario usuario = util.retornaUsuarioLogado();
		InteracaoProspeccao i = interacoesDAO.objetoModelo();
		i.setIdProspeccao(p);
		i.setIdProspeccaoTrans(p.getIdProspeccao());
		i.setIdUsuario(usuario);
		interacoesDAO.salvaInteracaoProspeccao(i);
	}
	
	public void editaProspeccao(Prospeccao p){
		manager.merge(p);
	}

	public Prospeccao pegaProspeccao(Integer idProspeccao){
		Prospeccao p = manager.find(Prospeccao.class, idProspeccao);
		return p;
	}
	

	public List<Prospeccao> listaProspeccoes(){
		String consulta2 = "FROM  Prospeccao where idProspeccao in ("
				+ "SELECT distinct(idProspeccao) FROM InteracaoProspeccao where interacaoOrigem = 'prospeccao' order by dataInteracao) "
				+ "and concluido = 0 order by idProspeccao desc";	
		
		TypedQuery<Prospeccao> q = manager.createQuery(consulta2,Prospeccao.class);
		return q.getResultList();
	}
	
	public List<InteracaoProspeccao> filtrolistarProspeccao(String filtro, boolean dataConfere, String qtdDias){
		String consulta = "FROM  Prospeccao i join fetch i.interacao ii where idProspeccao > 0 "+ filtro;
		Query query = manager.createQuery(consulta, Prospeccao.class);
		if(qtdDias.equals("")){
		}else{
			query.setMaxResults(Integer.parseInt(qtdDias));
		}
		return query.getResultList();
	}

	public List<Prospeccao> filtrolistarProspeccao2(String filtro, boolean dataConfere, String qtdDias){
		String consulta = "FROM  Prospeccao i join fetch i.interacao ii where idProspeccao > 0 "+ filtro;
		Query query = manager.createQuery(consulta, Prospeccao.class);
		if(qtdDias.equals("")){
		}else{
			query.setMaxResults(Integer.parseInt(qtdDias));
		}
		return query.getResultList();
	}
	
	public void salvaEmpresaNova(Pagamento pagamento, Empresa e) {
		Calendar c = Calendar.getInstance();

		e.setDataCadastro(c);
		e.setDescricao("-");
		e.setLogotipo("-");

		e.setHabilitado(true);
		e.setCliente(true);
		
		if(e.isFornecedor() == true){
			TipoPagamento tipoPagamento = manager.find(TipoPagamento.class, pagamento.getIdTipoPagamentoTransiente());
			pagamento.setIdEmpresa(e);
			pagamento.setIdtipoPagamento(tipoPagamento);
			
			manager.persist(e);
			manager.persist(pagamento);
		}else{
			manager.persist(e);
		}
	}

	public void atualizaEmpresaProspeccao(Pagamento pagamento, Empresa e,Integer idEmpresaAtualiza) {
	
		e.setDataCadastro(Calendar.getInstance());
		e.setIdEmpresa(idEmpresaAtualiza);
		e.setDescricao(" - ");
		
		if(e.getIdEmpresaAtualiza()!= null && e.isFornecedor() == true && pagamento.getIdpagamento() == null){
			
			TipoPagamento tipoPagamento = manager.find(TipoPagamento.class, pagamento.getIdTipoPagamentoTransiente());
			pagamento.setIdEmpresa(e);
			pagamento.setIdtipoPagamento(tipoPagamento);
			
			manager.merge(e);
			manager.detach(e);
			
			manager.persist(pagamento);
			
		
	    }else if(e.getIdEmpresaAtualiza()!= null && e.isFornecedor() == true && pagamento.getIdpagamento() != null){
	    	
	    	TipoPagamento tipoPagamento = manager.find(TipoPagamento.class, pagamento.getIdTipoPagamentoTransiente());
	    	pagamento.setIdEmpresa(e);
			pagamento.setIdtipoPagamento(tipoPagamento);
			
			manager.merge(e);
			manager.merge(pagamento);
		}
	    else {
			manager.merge(e);
		}
	}
	
	public void concluiProspeccao(Integer idProspeccao){
		Prospeccao p = manager.getReference(Prospeccao.class, idProspeccao);
		p.setConcluido(true);
		manager.merge(p);
	}

	public void cancelaConcluiProspeccao(Integer idProspeccao){
		Prospeccao p = manager.getReference(Prospeccao.class, idProspeccao);
		p.setConcluido(false);
		manager.merge(p);
	}
	
	
	
	
}
