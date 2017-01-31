package br.com.sysloccOficial.criacao.controllerCriacao.itens;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.IdClass;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.consultas.ConsultasGenericas;
import br.com.sysloccOficial.criacao.controllerCriacao.dao.CriacaoDAO;
import br.com.sysloccOficial.criacao.controllerCriacao.relatorio.apoio.ApoioRelatorio;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.CriacaoItemLista;
import br.com.sysloccOficial.model.CriacaoItemStatus;
import br.com.sysloccOficial.model.CriacaoLista;
import br.com.sysloccOficial.model.CriacaoStatus;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.Usuario;
import br.com.sysloccOficial.model.criacao.CalculoHorasLista;

public class Criacao{
	
	@PersistenceContext	private EntityManager manager;
	@Autowired	private ConsultasGenericas consultaGenerica;
	@Autowired	private UsuarioDAO usuarioDAO;
	@Autowired	private CriacaoDAO criacaoDAO;
	@Autowired	private ApoioRelatorio apoioRelatorio;
	@Autowired	private Utilitaria util;
	ModelAndView MV = new ModelAndView();
	
	
	
	
	
	public void verificaSeListaExiste(Integer idLista,Integer idGrupo){ 
		String consulta = " select c from CriacaoLista c join fetch c.listaProducao join fetch c.criacaoItemLista where idListaProducao="+idLista;
		String consultaId = "select c.idCriacaoLista from CriacaoLista c where idListaProducao="+idLista;
		
		Grupo grupo = manager.getReference(Grupo.class, idGrupo);
		
		List<CriacaoLista>lista = criacaoDAO.ListaDeObjetos(consulta);
		
		if(lista.isEmpty()){ // Se lista vazia
			Integer idCriacaoLista =  novaListaCriacao(idLista);
			criaItem(idCriacaoLista,idGrupo);
			criaApresentacao(idCriacaoLista,idGrupo);
		}else{
			criaItem(consultaGenerica.consultaPorId(consultaId),idGrupo);
		}
		
		
		/**
		 * Único ponto de relacionamento entre o grupo da Lista de produção e 
		 * o item na criação 
		 * 
		 */
		
		grupo.setCriacao(true);
		manager.merge(grupo);
		
	}
	
	
	public Integer novaListaCriacao(Integer idLista){
		CriacaoLista criacaoLista = new CriacaoLista();
		Lista lista = manager.find(Lista.class, idLista);
		
		Usuario u = util.retornaUsuarioLogado();
		
		Usuario usuario = manager.find(Usuario.class, u.getIdUsuario());

		Usuario usuarioResponsavel = usuarioDAO.usuarioPorNivel(1);
		
		CriacaoStatus criacaoStatus = manager.find(CriacaoStatus.class, 1);
		
		criacaoLista.setListaTitulo(lista.getLista());
		criacaoLista.setDataCriacao(Calendar.getInstance());
		criacaoLista.setListaProducao(lista);
		criacaoLista.setUsuario(usuario); 
		
		criacaoLista.setVersao(1); // Versão
		
		criacaoLista.setUsuarioReponsável(usuarioResponsavel);
		criacaoLista.setCriacaoStatus(criacaoStatus);//Status da NovaLista em Aberto
		
		manager.persist(criacaoLista);
		return criacaoLista.getIdCriacaoLista();
	}

	
	public void criaItem(Integer idLista,Integer idGrupo){
		
		
		CriacaoItemLista itemLista = new CriacaoItemLista();
		CriacaoLista criacaoLista = manager.find(CriacaoLista.class, idLista);
		Grupo grupo = manager.find(Grupo.class, idGrupo);
		CriacaoItemStatus itemStatus = manager.find(CriacaoItemStatus.class, 1); //Status Inicial do Item
		
		itemLista.setTituloItem(grupo.getGrupo());
		
		itemLista.setInformaoesItem(grupo.getIdLista().getListaCod()+"."+grupo.getIdLista().getRevisao()+"<br>"+grupo.getInformacoes());
		
		itemLista.setCriacaoLista(criacaoLista);
		
		/**
		 * Único ponto de relacionamento entre Item da produção e 
		 * grupo da Lista de Produção
		 */
		itemLista.setGrupo(grupo);
		

		itemLista.setDataCriacao(Calendar.getInstance());
		itemLista.setCriacaoItemStatus(itemStatus);
		itemLista.setVersao(1); // Versão do Item
		itemLista.setApres(1);
		
		manager.persist(itemLista);
		
		
		if(criacaoLista.getCriacaoStatus().getIdCriacaoStatus() == 2){
			CriacaoStatus criacaoStatusAberto = manager.find(CriacaoStatus.class, 1);
			criacaoLista.setCriacaoStatus(criacaoStatusAberto);
			criacaoLista.setVersao(criacaoLista.getVersao()+1);
			manager.merge(criacaoLista);
		}
		
		
		
	}
	
	public void criaApresentacao(Integer idLista,Integer idGrupo){
		CriacaoItemLista itemLista = new CriacaoItemLista();
		CriacaoLista criacaoLista = manager.find(CriacaoLista.class, idLista);
//		Grupo grupo = manager.find(Grupo.class, idGrupo);
		CriacaoItemStatus itemStatus = manager.find(CriacaoItemStatus.class, 1); //Status Inicial do Item
		
		
		CriacaoItemLista template = new CriacaoItemLista();
		
		Integer apres = 5;
		
		template.setTituloItem("Template");
		template.setCriacaoLista(criacaoLista);
//		template.setGrupo(grupo);
		template.setDataCriacao(Calendar.getInstance());
		template.setCriacaoItemStatus(itemStatus);
		template.setVersao(1);
		itemLista.setApres(apres);
		manager.persist(template);

		CriacaoItemLista CDTransfer = new CriacaoItemLista();
		
		CDTransfer.setTituloItem("CD/Tranfer");
		CDTransfer.setCriacaoLista(criacaoLista);
//		CDTransfer.setGrupo(grupo);
		CDTransfer.setDataCriacao(Calendar.getInstance());
		CDTransfer.setCriacaoItemStatus(itemStatus);
		CDTransfer.setVersao(1);
		itemLista.setApres(5);
		manager.persist(CDTransfer);
		
		CriacaoItemLista PPT = new CriacaoItemLista();
		
		PPT.setTituloItem("PPT de apresentação");
		PPT.setCriacaoLista(criacaoLista);
//		PPT.setGrupo(grupo);
		PPT.setDataCriacao(Calendar.getInstance());
		PPT.setCriacaoItemStatus(itemStatus);
		PPT.setVersao(1);
		itemLista.setApres(5);
		manager.persist(PPT	);
		
	}
	
	public void fechaListaCriacao(Integer idCriacaoLista) throws ParseException{
		String consulta = "select c from CriacaoItemLista c where"
				+ " idCriacaoLista="+idCriacaoLista+" and idCriacaoItemStatus != 7 and"
				+ " idCriacaoLista="+idCriacaoLista+" and idCriacaoItemStatus != 4 and"
				+ " idCriacaoLista="+idCriacaoLista+" and idCriacaoItemStatus != 2";
		
		if(consultaGenerica.ListaDeObjetos(consulta).isEmpty()){
			CriacaoLista criacaoLista = manager.find(CriacaoLista.class, idCriacaoLista);
			CriacaoStatus criacaoStatus = manager.find(CriacaoStatus.class, 2);
			criacaoLista.setCriacaoStatus(criacaoStatus);
			criacaoLista.setDataFechamento(Calendar.getInstance());
			
			List<Integer> passaLista = new ArrayList<Integer>();
			passaLista.add(idCriacaoLista);
			String tempo  = "";
			
			List<CalculoHorasLista> horasCalculadas = apoioRelatorio.somaTempoTotalListaIndividual(passaLista);
			
			for (CalculoHorasLista calculoHorasLista : horasCalculadas) {
					tempo = calculoHorasLista.getTeste().get(0).toString()+"Hs"+calculoHorasLista.getTeste().get(1).toString()+"Min";
			}
			criacaoLista.setTempoTotal(tempo);
			manager.merge(criacaoLista);
			
		}else{
			System.out.println("Existe algum item em aberto !");
		}
	}
	
	public List<Object[]> exibeListas(){
		String Consulta = "select c from CriacaoLista c order by c.listaProducao.idJob.propostaData";
		return consultaGenerica.ListaDeObjetos(Consulta);
	}

	public List<CriacaoLista> exibeCriacaoListas(){
		String Consulta = "select c from CriacaoLista c  left join c.listaProducao order by c.listaProducao.idJob.propostaData";
		Query query = manager.createQuery(Consulta,CriacaoLista.class);
		return query.getResultList();
	}
	
	
	
	
	public List<CriacaoLista> exibeCriacaoListasFechadas(){
		String Consulta = "select c from CriacaoLista c  left join c.listaProducao order by dataFechamento desc";
		Query query = manager.createQuery(Consulta,CriacaoLista.class);
		return query.getResultList();
	}
	
	public List<CriacaoLista> exibeCriacaoListasFechadasPorEmpresa(Integer idEmpresa){
		String Consulta = "select c from CriacaoLista c  left join c.listaProducao where c.listaProducao.idJob.empresa.idEmpresa=:idEmpresa order by dataFechamento desc";
		Query query = manager.createQuery(Consulta,CriacaoLista.class)
				.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

	
	
	public Usuario userNivel(){
		return usuarioDAO.usuarioPorNivel(1);
		//TODO Usuario cadastrado como nivel 1
	}
	public void mudaResponsavel(Integer idResponsavel,Integer idListaCriacao){
		CriacaoLista criacaoLista = manager.find(CriacaoLista.class, idListaCriacao);
		Usuario usuarioResponsavel = manager.find(Usuario.class, idResponsavel);		
		criacaoLista.setUsuarioReponsável(usuarioResponsavel);
		manager.merge(criacaoLista);
	}

	public void mudaPar(Integer idResponsavel,Integer idListaCriacao){
		CriacaoLista criacaoLista = manager.find(CriacaoLista.class, idListaCriacao);
		Usuario parResponsavel = manager.find(Usuario.class, idResponsavel);		
		criacaoLista.setParReponsavel(parResponsavel);
		manager.merge(criacaoLista);
		
		
		String consulta = "from CriacaoItemLista where criacaoLista="+idListaCriacao;
		Query query = manager.createQuery(consulta, CriacaoItemLista.class);
		List<CriacaoItemLista>itemLista = query.getResultList();
		
		
		for(CriacaoItemLista itemLista2: itemLista){
			if(itemLista2.getResponsavelItem() == null){
				itemLista2.setResponsavelItem(parResponsavel);
				manager.merge(itemLista2);
			
			}else{
				/*JOptionPane.showMessageDialog(null, "Não ta Vazio !");*/
			}
		}
		
		
		
	}

	public List<Object[]> dataJobProposta(){
		String consulta = "select c.listaProducao.idJob, c.listaProducao.propostaData from CriacaoLista c  left join c.listaProducao order by c.listaProducao.idJob.propostaData";
		return consultaGenerica.ListaDeObjetos(consulta);
	}
	
	
	
	
}
