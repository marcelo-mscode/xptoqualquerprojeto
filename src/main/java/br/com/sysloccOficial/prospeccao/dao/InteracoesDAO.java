package br.com.sysloccOficial.prospeccao.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.controllers.EmailController;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.Contato;
import br.com.sysloccOficial.model.Interacao;
import br.com.sysloccOficial.model.Usuario;
import br.com.sysloccOficial.model.prospeccao.InteracaoProspeccao;
import br.com.sysloccOficial.model.prospeccao.Prospeccao;


@Repository
@Transactional
public class InteracoesDAO {
	@Autowired Utilitaria util;
	@Autowired UtilitariaDatas utilDatas;
	@Autowired private EmailController email;
	@Autowired private UsuarioDAO usuarioDAO;
	@PersistenceContext private EntityManager manager;

	public List<InteracaoProspeccao> listaInteracoes(){
		String consulta = "FROM InteracaoProspeccao where automatico = 0 and idContato <> 0 order by dataInteracao desc";
		Query q = manager.createQuery(consulta);
		return q.getResultList();
	}
	
	public InteracaoProspeccao objetoModelo(){
		Contato c = manager.find(Contato.class, 1);
		InteracaoProspeccao i = new InteracaoProspeccao();
		i.setInteracao("Prospecão Criada.");
		i.setInteracaoOrigem("prospeccao");
		i.setInteracaoForma("Outro");
		i.setDataInteracao(Calendar.getInstance());
		i.setDataProximaInteracao(Calendar.getInstance().getTime());
		i.setAgendar(false);
		i.setInterno(false);
		i.setAutomatico("0");
		i.setIdContato(c);
		i.setIdContatoContato(c.getIdContato());
		return i;
	}
	
	public void salvaInteracaoProspeccao(InteracaoProspeccao i){
		Usuario u = util.retornaUsuarioLogado();
		Prospeccao p = manager.getReference(Prospeccao.class, i.getIdProspeccaoTrans());
		Contato c = manager.getReference(Contato.class, i.getIdContatoContato());
		
		if (i.isInterno() == true) {
			Usuario usuario1 = manager.getReference(Usuario.class, i.getIdContatoContato());
			i.setIdUsuarioInterno(usuario1);
		}
		
		i.setIdUsuario(u);
		i.setDataInteracao(Calendar.getInstance());
		i.setIdProspeccao(p);
		i.setIdContato(c);

		String proxInteracao = i.getPd() + " " + i.getPh();
		if(proxInteracao.equals("null null")){
			i.setDataProximaInteracao(utilDatas.dataParaCorrecaoBandoDadosAntigo());
		}else{
			i.setDataProximaInteracao(util.formataDatasStringParaCalendar(proxInteracao));
		}
		comunicarOutrosPorEmail(i);
		manager.persist(i);
	}
	
	private void comunicarOutrosPorEmail(InteracaoProspeccao interacao) {
		List<String> OutrosEmailsInternos;
		if(interacao.getMultiplo() != null){
			String subject = "Nova Interação da Prospecção: "+interacao.getIdProspeccao().getTitulo();
			
			String textAutomatico = ""
					+ "Nova Interação da Prospecção: "+interacao.getIdProspeccao().getTitulo()
					+ "\n\n"
					+ "Enviado por: "+ interacao.getIdUsuario().getNome()
					+ "\nEmail: "+interacao.getIdUsuario().getEmail()
					+ "\n\nDescrição da Interação: "+interacao.getInteracao()
					+ "\n\n-------------------------------------------------------------"
					+ "\nEnvio automático, não responda o email nesse endereço.";
			
			OutrosEmailsInternos = usuarioDAO.someEmailUser(interacao.getMultiplo());
			email.severalEmail(OutrosEmailsInternos, subject, textAutomatico);
		}
	}
	
	
	public List<Interacao> listar(Integer idProspeccao,String origem){
		
		String consulta = "select i from InteracaoProspeccao i  inner join fetch i.idContato where idOrigem=:pidProspeccao and interacaoOrigem=:origem and i.idContato > 1"
						+ "order by dataInteracao desc";
		Query query = manager.createQuery(consulta, InteracaoProspeccao.class);
		query.setParameter("pidProspeccao", idProspeccao);
		query.setParameter("origem", origem);
		
		return query.getResultList();
	}
	
	public List<InteracaoProspeccao> filtrolistar(String filtro, boolean dataConfere, String qtdDias){
		
		String consulta = "FROM InteracaoProspeccao i where interacaoOrigem = 'prospeccao' "+ filtro +""
						+ " and automatico = 0 and idContato <> 0 order by dataInteracao desc";
		Query query = manager.createQuery(consulta, InteracaoProspeccao.class);
		
		if(qtdDias.equals("")){
		}else{
			query.setMaxResults(Integer.parseInt(qtdDias));
		}
		return query.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	