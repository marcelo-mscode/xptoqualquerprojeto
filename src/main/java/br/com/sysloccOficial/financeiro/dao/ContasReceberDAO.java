package br.com.sysloccOficial.financeiro.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
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
	@Autowired Utilitaria util;
	
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

	public void recebeConta(Integer idLista, Integer tipoBanco,boolean ndnf)throws Exception {
		
		TypedQuery<InfoInterna> q = manager.createQuery("from InfoInterna where lista.idLista="+idLista,InfoInterna.class);
		InfoInterna infoInterna = q.getSingleResult();

		Calendar data = Calendar.getInstance();
		data.setTime(infoInterna.getDataPagamento());
		
		int mesVencimento = data.get(GregorianCalendar.MONTH) + 1;
		int anoVencimento = data.get(GregorianCalendar.YEAR);
		
		/**
		 * Tratar aqui o erro de um vencimento não ter um analítico cadastrado.
		 */
		FinancAnalitico analitico = pegaFinancAnaliticoPorAnoMesReferencia(anoVencimento, mesVencimento);
	
		if(analitico.equals(null)){
			throw new Exception();
		}else{

			
			TypedQuery<RelatorioEventos> s = manager.createQuery("from RelatorioEventos where idLista="+idLista+" and ndFatDireto = "+ndnf,RelatorioEventos.class);
			RelatorioEventos relatorio = s.getSingleResult();
			relatorio.setRecebido(true);
			relatorio.setDataRecebido(Calendar.getInstance());
			manager.merge(relatorio);
			manager.close();
			
			if(ndnf == false){
				infoInterna.setRecebido(true);
				infoInterna.setDataRecebido(Calendar.getInstance());
				manager.merge(infoInterna);
			}
			
			/**
			 * Chamada ao método usado para registrar as contas recebidas no painél do Análitico
			 * 
			 */
			salvaContasAnalitico(idLista, tipoBanco, infoInterna, relatorio,analitico);
		}	
			
		
	}

	/**
	 * Método usado para registrar as contas recebidas no painél do Análitico.
	 */
	private void salvaContasAnalitico(Integer idLista, Integer tipoBanco,InfoInterna infoInterna, RelatorioEventos relatorio, FinancAnalitico analitico) {
		
 		MovimentacaoBancos bancoItau = new MovimentacaoBancos();

		BancosAnalitico banco = manager.getReference(BancosAnalitico.class, tipoBanco);
		Lista lista = manager.find(Lista.class, idLista);
 
		String ndnf ="";
		
		if(relatorio.isNdFatDireto() == false){
			ndnf = " NF";
		}else{
			ndnf = " ND";
		}
		
		String ndounf = ndnf +" "+infoInterna.getNfInterna();
		
		bancoItau.setNdnf(ndounf);
		bancoItau.setData(infoInterna.getDataPagamento());
		bancoItau.setDescricao(lista.getLista());
		bancoItau.setValor(relatorio.getValorLoccoAgenc());
		bancoItau.setBanco(banco);

        bancoItau.setAnalitico(analitico);
		
		manager.persist(bancoItau);
	}
	
	public FinancAnalitico pegaFinancAnaliticoPorAnoMesReferencia(int anoVencimento, int mesVencimento)throws NullPointerException{
			try {
				TypedQuery<FinancAnalitico> analit = manager.createQuery("from FinancAnalitico where anoA="+anoVencimento+" and mesReferencia='"+mesVencimento+"'", FinancAnalitico.class);
				return analit.getSingleResult();
			} catch (Exception e) {
				return null;
			}
	}

	public void alterarDataVencimento(int idInfoInterna, String data) throws ParseException {
		
			String dataVencimento = data + " 00:00:00";
			
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dataConv = df.parse(dataVencimento);
			
			InfoInterna info = manager.find(InfoInterna.class, idInfoInterna);
			info.setDataPagamento(dataConv);
			manager.merge(info);
			manager.close();
	}
}
