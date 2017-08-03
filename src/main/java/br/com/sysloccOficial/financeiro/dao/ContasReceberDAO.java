package br.com.sysloccOficial.financeiro.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.financeiro.model.MovimentacaoBancos;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;

@Repository
@Transactional
public class ContasReceberDAO {
	
	@PersistenceContext	private EntityManager manager;
	public List<RelatorioEventos> relatorioEventos(){
		try {
			String consulta = "from RelatorioEventos where recebido <> 1";
			TypedQuery<RelatorioEventos> q = manager.createQuery(consulta,RelatorioEventos.class);
			return q.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<InfoInterna> inforInterna(){
		try {
			String consulta = "from InfoInterna where recebido <> 1 order by dataPagamento";
			TypedQuery<InfoInterna> q = manager.createQuery(consulta,InfoInterna.class);
			return q.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void recebeConta(Integer idLista) {
		
		
		MovimentacaoBancos bancoItau = new MovimentacaoBancos();
		
		
		TypedQuery<InfoInterna> q = manager.createQuery("from InfoInterna where lista.idLista="+idLista,InfoInterna.class);
		InfoInterna infoInterna = q.getSingleResult();
		infoInterna.setRecebido(true);
		infoInterna.setDataRecebido(Calendar.getInstance());
		manager.merge(infoInterna);
		
		TypedQuery<RelatorioEventos> s = manager.createQuery("from RelatorioEventos where idLista="+idLista,RelatorioEventos.class);
		RelatorioEventos relatorio = s.getSingleResult();
		relatorio.setRecebido(true);
		relatorio.setDataRecebido(Calendar.getInstance());
		manager.merge(relatorio);
		manager.close();
		
		
		Lista lista = manager.find(Lista.class, idLista);
		
		
		
		bancoItau.setNdnf(infoInterna.getNfInterna());
		bancoItau.setData(infoInterna.getDataPagamento());
		bancoItau.setDescricao(lista.getLista());
		
		
		
		
		
	}
	
	
	
	

}
