package br.com.sysloccOficial.financeiro.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.model.BancosAnalitico;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;
import br.com.sysloccOficial.financeiro.model.MovimentacaoBancos;
import br.com.sysloccOficial.model.InfoInterna;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.RelatorioEventos;

@Repository
@Transactional
public class ContasReceberDAO {
	
	@Autowired UtilitariaDatas utilDatas;
	
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

	public void recebeConta(Integer idLista, Integer tipoBanco) {
		
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
		
		/**
		 * Chamada ao método usado para registrar as contas recebidas no painél do Análitico
		 * 
		 */
		salvaContasAnalitico(idLista, tipoBanco, bancoItau, infoInterna, relatorio);
	}

	/**
	 * Método usado para registrar as contas recebidas no painél do Análitico.
	 *
	 * Quando não existir um análitico criado no mês que a conta for recebida, 
	 * o sistema irá registrar os valores no último análitico criado.
	 * 
	 * @param idLista
	 * @param tipoBanco
	 * @param bancoItau
	 * @param infoInterna
	 * @param relatorio
	 */
	private void salvaContasAnalitico(Integer idLista, Integer tipoBanco,MovimentacaoBancos bancoItau, InfoInterna infoInterna,
									  RelatorioEventos relatorio) {
		
		ArrayList<String> datasHoje = utilDatas.dataHojeFormatada();

		BancosAnalitico banco = manager.getReference(BancosAnalitico.class, tipoBanco);
		Lista lista = manager.find(Lista.class, idLista);
		
		bancoItau.setNdnf(infoInterna.getNfInterna());
		bancoItau.setData(infoInterna.getDataPagamento());
		bancoItau.setDescricao(lista.getLista());
		bancoItau.setValor(relatorio.getValorLoccoAgenc());
		bancoItau.setBanco(banco);

		try {
			TypedQuery<FinancAnalitico> analit = manager.createQuery("from FinancAnalitico where anoA="+datasHoje.get(2)+" and mesA='"+datasHoje.get(1)+"'", FinancAnalitico.class);
			FinancAnalitico analitico = analit.getSingleResult();
			bancoItau.setAnalitico(analitico);
		
		} catch (Exception e) {
			TypedQuery<FinancAnalitico> ultimoAnalitico = manager.createQuery("from FinancAnalitico order by idAnalitico desc",FinancAnalitico.class).setMaxResults(1);
			FinancAnalitico ultimo = ultimoAnalitico.getSingleResult();
			bancoItau.setAnalitico(ultimo);
		}
		
		manager.persist(bancoItau);
	}
	
	
	
	

}
