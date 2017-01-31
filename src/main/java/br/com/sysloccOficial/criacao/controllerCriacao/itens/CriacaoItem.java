package br.com.sysloccOficial.criacao.controllerCriacao.itens;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.consultas.ConsultasGenericas;
import br.com.sysloccOficial.criacao.controllerCriacao.relatorio.apoio.ApoioRelatorio;
import br.com.sysloccOficial.model.CriacaoItemHistorico;
import br.com.sysloccOficial.model.CriacaoItemLista;
import br.com.sysloccOficial.model.CriacaoItemPendencia;
import br.com.sysloccOficial.model.CriacaoItemPendenciaHistorico;
import br.com.sysloccOficial.model.CriacaoItemStatus;
import br.com.sysloccOficial.model.CriacaoLista;
import br.com.sysloccOficial.model.CriacaoStatus;
import br.com.sysloccOficial.model.Usuario;

public class CriacaoItem{

	@PersistenceContext	private EntityManager manager;
	@Autowired private ConsultasGenericas consultaGenerica;
	@Autowired private ApoioRelatorio apoioRelatorio;
	@Autowired private Utilitaria util;
	ModelAndView MV = new ModelAndView();
	
	
	public List<CriacaoItemPendencia> CriacaoItemPendencia (Integer idCriacaoLista){
		
		String consulta ="SELECT c FROM CriacaoItemPendencia c where idCriacaoItem =:idCriacaoItemLista order by IdCriacaoItemHistoricoPendencia desc";
		Query query = manager.createQuery(consulta,CriacaoItemPendencia.class)
				.setMaxResults(1)
				.setParameter("idCriacaoItemLista", idCriacaoLista);
		
		if(query.getResultList().isEmpty()){
			System.out.println("Não tenho item criado, em criação item pendência.");
			//TODO Dependência com nome
		}else{
		}
		return query.getResultList();
	}
	
	public List<Object[]> exibeItensCriacao(Integer idCriacaoLista){
		String consulta="select i from CriacaoItemLista i left join i.responsavelItem where idCriacaoLista="+idCriacaoLista+ " order by i.apres";
		return consultaGenerica.ListaDeObjetos(consulta);
	}

	public List<CriacaoItemLista> exibeItensCriacaoTeste(Integer idCriacaoLista){
		String consulta="select i from CriacaoItemLista i left join i.responsavelItem where idCriacaoLista="+idCriacaoLista+ " order by i.apres";
		Query q = manager.createQuery(consulta);
		return q.getResultList();
	}
	
	public List<Object[]> fechaLista(Integer idCriacaoLista){
		String consulta="SELECT c.idCriacaoItemLista FROM CriacaoItemLista c where"
								+ " idCriacaoLista=:idCriacaoLista and idCriacaoItemStatus !=7 and"
								+ " idCriacaoLista=:idCriacaoLista and idCriacaoItemStatus !=4 and"
								+ " idCriacaoLista=:idCriacaoLista and idCriacaoItemStatus !=2";
		
		Query query = manager.createQuery(consulta).setParameter("idCriacaoLista", idCriacaoLista);
		List<Object[]> lista = query.getResultList();
		return lista;
	}
	
	
	public CriacaoItemLista exibeDetalheItem(Integer idCriacaoItem){
		CriacaoItemLista criacaoItem = manager.find(CriacaoItemLista.class, idCriacaoItem);
		return criacaoItem;
	}
	
	public CriacaoItemHistorico iniciaTempo(Integer idCriacaoItem){
		
		/**
		 * Pega o historico do item pendente
		 * 
		 */
		String conHistPendenc = "select c from CriacaoItemPendenciaHistorico"
			      + " c where c.criacaoItemPendencia.criacaoItemLista.idCriacaoItemLista = "+idCriacaoItem+" order by idItemHistoricoPendencia desc";
		Query q = manager.createQuery(conHistPendenc).setMaxResults(1);
		List<CriacaoItemPendenciaHistorico> histPend = q.getResultList();

		/**
		 * Pega o id do historico do Item
		 * Pega o id do Status do Item
		 */
		Integer statusHistorico = 0;
		for(CriacaoItemPendenciaHistorico teste : histPend){
			statusHistorico =  teste.getCriacaoItemStatus().getIdCriacaoStatus();
		}
		
		/**
		 * Pega o ÚLTIMO histórico do Item e adiciona na lista: List<CriacaoItemHistorico> Ihistorico
		 * 
		 * 
		 */
		String consulta = "select c from CriacaoItemHistorico c where idCriacaoItem ="+idCriacaoItem+" order by idItemHistorico desc";
		Query query = manager.createQuery(consulta).setMaxResults(1);
		
		List<CriacaoItemHistorico> Ihistorico = query.getResultList();
		
		Integer idCriacaoItemStatus = null; 
		Integer idItemHistorico = null; 
		
		/**
		 * Pega o id do historico do Item
		 * Pega o id do Status do Item
		 */
		for(CriacaoItemHistorico teste : Ihistorico){
			idItemHistorico =  teste.getIdItemHistorico();
			idCriacaoItemStatus =  teste.getCriacaoItemStatus().getIdCriacaoStatus();
		}
		
		/**
		 * Verifica se existe histórico ou se o item está em aberto 
		 *
		 * 
		 */
		if(Ihistorico.isEmpty() || idCriacaoItemStatus == 1	){
			
			/**
			 * Se NÃO existir histórico ou o item estiver com o status "Em aberto"  
			 * 
			 */
			
			CriacaoItemLista item = manager.find(CriacaoItemLista.class, idCriacaoItem);
			CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, 6);
			
			/**
			 * Atualiza o item
			 * guarda a data e hora de inicio 
			 * muda o status do item para "Em execução"
			 */
			item.setInicio(Calendar.getInstance());
			item.setCriacaoItemStatus(status);
			manager.merge(item);
			
			/**
			 * Cria um novo histórico para o item 
			 * 
			 * 
			 */
			CriacaoItemHistorico itemhistorico = new CriacaoItemHistorico();

			itemhistorico.setDataInicio(Calendar.getInstance());
			itemhistorico.setCriacaoItemLista(item);
			itemhistorico.setCriacaoItemStatus(status);
			itemhistorico.setIniciadoPor(util.retornaUsuarioLogado());
			itemhistorico.setResponsavelItem(item.getResponsavelItem());
			
			manager.persist(itemhistorico);
			return itemhistorico;
		}else{
			
			/**
			 * Se EXISTIR histórico ou o Status do item FOR DIFERENTE de "Em aberto"
			 * 
			 * 
			 */
			CriacaoItemLista item = manager.find(CriacaoItemLista.class, idCriacaoItem);
			CriacaoItemHistorico itemhistorico = manager.find(CriacaoItemHistorico.class, idItemHistorico);
			CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, 6);
			
			/**
			 * Atualiza o item
			 * guarda a data e hora de inicio 
			 * muda o status do item para "Em execução"
			 */
			item.setInicio(Calendar.getInstance());
			item.setCriacaoItemStatus(status);
			manager.merge(item);
			
			
			/**
			 * Verifica se o item tem historico em pendencia e se a pendencia está
			 * fechada.
			 * 
			 */
			if(histPend.isEmpty() || statusHistorico != 2){
				/**
				 * Atualiza o histórico
				 * 
				 * 
				 */
				itemhistorico.setDataInicio(Calendar.getInstance());
				itemhistorico.setCriacaoItemLista(item);
				itemhistorico.setCriacaoItemStatus(status);
				itemhistorico.setResponsavelItem(item.getResponsavelItem());
				manager.merge(itemhistorico);
				
			}else{
				
				
				CriacaoItemHistorico itemhistoricoNovo = new CriacaoItemHistorico();

				itemhistoricoNovo.setDataInicio(Calendar.getInstance());
				itemhistoricoNovo.setCriacaoItemLista(item);
				itemhistoricoNovo.setCriacaoItemStatus(status);
				itemhistoricoNovo.setResponsavelItem(item.getResponsavelItem());
				
				manager.persist(itemhistoricoNovo);
				
			}
			
			return itemhistorico;
		}
		
		
	}
	
	public CriacaoItemHistorico terminaTempo(Integer idCriacaoItem,Integer idHistoricoItem){
		CriacaoItemLista item = manager.find(CriacaoItemLista.class, idCriacaoItem);
		CriacaoItemHistorico itemhistorico = manager.find(CriacaoItemHistorico.class, idHistoricoItem);
		CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, 6);
		
		item.setTermino(Calendar.getInstance());
		item.setCriacaoItemStatus(status);
		manager.merge(item);
		
		itemhistorico.setDataTermino(Calendar.getInstance());
		itemhistorico.setFinalizadoPor(util.retornaUsuarioLogado());
		itemhistorico.setCriacaoItemStatus(status);
		itemhistorico.setVersao(item.getVersao());
		manager.merge(itemhistorico);

		System.out.println("Tempo Iniciado e guardado no historico.");

		
		return itemhistorico;
	}
	
	public void MudaStatus(CriacaoItemLista criacaoItemlista){									       		 
		CriacaoItemHistorico itemhistorico = manager.find(CriacaoItemHistorico.class, criacaoItemlista.getCriacaoItemHistoricoTransiente());
		CriacaoItemLista item = manager.find(CriacaoItemLista.class, criacaoItemlista.getIdCriacaoItemLista());
		CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, criacaoItemlista.getCriacaoItemStatusTransiente());
		
		
		//item.setTempoTotalItem.
		
		ArrayList<Integer> tempo = apoioRelatorio.somaTempoItemIndividual(criacaoItemlista.getIdCriacaoItemLista());
		
	
		
		if(criacaoItemlista.getCriacaoItemStatusTransiente() == 2 || criacaoItemlista.getCriacaoItemStatusTransiente() == 7 ){
			// status 2 = Fechado 
			// status 7 = Excluido
			
			item.setTempoTotalItem(tempo.get(0).toString()+"Hs"+tempo.get(1).toString()+"Min");
			item.setCriacaoItemStatus(status);
			item.setObs(criacaoItemlista.getObs());
			item.setApres(1);
			manager.merge(item);
			
			itemhistorico.setObs(criacaoItemlista.getObs());
			itemhistorico.setCriacaoItemStatus(status);
			manager.merge(itemhistorico);

		}else if(criacaoItemlista.getCriacaoItemStatusTransiente() == 3){
			// status 3 = Interrompido
			item.setTempoTotalItem(tempo.get(0).toString()+"Hs"+tempo.get(1).toString()+"Min");
			item.setCriacaoItemStatus(status);
			item.setObs(criacaoItemlista.getObs());
			manager.merge(item);
			
			itemhistorico.setObs(criacaoItemlista.getObs());
			itemhistorico.setCriacaoItemStatus(status);
			manager.merge(itemhistorico);
			
			CriacaoItemHistorico historicoItemNovo = new CriacaoItemHistorico();
			CriacaoItemStatus statusInterrompido = manager.find(CriacaoItemStatus.class, criacaoItemlista.getCriacaoItemStatusTransiente());
			
			
			historicoItemNovo.setCriacaoItemLista(item);
			historicoItemNovo.setCriacaoItemStatus(statusInterrompido);
			manager.persist(historicoItemNovo);

		}
		
		System.out.println("Status Mudado com Sucesso");
	}

	
	
	
	
	public void delegaItem(CriacaoItemLista criacaoItemlista){
	
		CriacaoItemLista item = manager.find(CriacaoItemLista.class, criacaoItemlista.getIdCriacaoItemLista());
		CriacaoItemHistorico itemhistorico = new CriacaoItemHistorico();
		
		Usuario usuario = manager.find(Usuario.class, criacaoItemlista.getResponsavelItemTransiente());
		
		Calendar datahojeCalendar = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String a = s.format(datahojeCalendar.getTime());
		
		item.setResponsavelItem(usuario);
		item.setItemDelegado(true);
		manager.merge(item);
		
		Usuario usuarioResponsavel = util.retornaUsuarioLogado();
		
		itemhistorico.setObs("Item Delegado para: "+usuario.getNome()+" em "+a);
		itemhistorico.setCriacaoItemLista(item);
		itemhistorico.setCriacaoItemStatus(item.getCriacaoItemStatus());
		itemhistorico.setResponsavelItem(usuarioResponsavel);
		itemhistorico.setVersao(item.getVersao());
		manager.persist(itemhistorico);
	}
	
	public void dependenciaItem(CriacaoItemLista criacaoItemlista, CriacaoItemPendencia criacaoItemPendencia){
		
		CriacaoItemLista item = manager.find(CriacaoItemLista.class, criacaoItemlista.getIdCriacaoItemLista());
		CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, 5);
		CriacaoItemStatus statusEmAberto = manager.find(CriacaoItemStatus.class, 1);
		Usuario usuario = manager.find(Usuario.class, criacaoItemlista.getResponsavelItemTransiente());	
		
		/**
         * Coloca o item com status pendente
         * e seta o responsável pela pendência
         * 
         */
		item.setCriacaoItemStatus(status);
		item.setResponsavelItem(usuario);
		item.setDependencia(true); // Indica que o item está pendente.
		manager.merge(item);


		/**
		 * Cria um novo Histório para o item 
		 * 			
		 */
		CriacaoItemHistorico itemhistorico = new CriacaoItemHistorico();

		Calendar datahojeCalendar = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String a = s.format(datahojeCalendar.getTime());

		String dataLimite = criacaoItemPendencia.getDataLimiteTransiente() + " " + criacaoItemPendencia.getHoraLimiteTransiente();
		
		Usuario u = util.retornaUsuarioLogado();
		String usuarioLogado = "&nbsp" + u.getNome();
		
		itemhistorico.setObs("<b>Descrição da Pendência:</b>"
				+ criacaoItemPendencia.getDescPendencia()+"<br>"
				+ "<b>Enviado de pendência para:</b> "+usuario.getNome()+"<br>"+
				  "<b>Por:</b>"+ usuarioLogado +"<br>"+
				" <b>em:</b> "+a+"<br><b>Data Limite:</b> "+dataLimite);
		
		itemhistorico.setCriacaoItemLista(item);
		itemhistorico.setCriacaoItemStatus(status);
		itemhistorico.setResponsavelItem(usuario);
		itemhistorico.setVersao(item.getVersao());
		manager.merge(itemhistorico);
		
		/**
		 * Cria um novo registro para pendencia desse item 
		 * 
		 * 	
		 */
		String prazoProposta = criacaoItemPendencia.getDataLimiteTransiente() + " " + criacaoItemPendencia.getHoraLimiteTransiente();

		criacaoItemPendencia.setDataEnvioPendencia(Calendar.getInstance());
		criacaoItemPendencia.setDataLimite(util.formataDatasStringParaCalendar(prazoProposta));
		criacaoItemPendencia.setCriacaoItemLista(item);
		criacaoItemPendencia.setResponsavelItem(usuario);
		criacaoItemPendencia.setEnviadoPor(u);
		criacaoItemPendencia.setCriacaoItemStatus(statusEmAberto);
		manager.persist(criacaoItemPendencia);
		
		/**
		 * Cria um histórico para o item pendente
		 * 
		 * 
		 */
		CriacaoItemPendenciaHistorico pendenciaHistorico = new CriacaoItemPendenciaHistorico();
		
		pendenciaHistorico.setVersao(item.getVersao());
		pendenciaHistorico.setCriacaoItemPendencia(criacaoItemPendencia);
		pendenciaHistorico.setCriacaoItemStatus(status);
		pendenciaHistorico.setResponsavelItem(usuario);
		manager.persist(pendenciaHistorico);
		
		// Vai para fila de pendencias do user 

		//TODO Carrega página de pendencia do user da sessão 

		/* Quando carregar a página de pendencias, o sistema pega o id do usuário da sessão e faz
		 * uma varredura no banco procurando o item que está com pendencia e com o id do usuário,
		 * carregando a página de pendencias. 
	    */
		// Entra no histórico do item como 'Item pendente' com data de envio para pendencia e nome do usuario que vai resolver
		// Retorna para para o "Histórico do item" como "Pendencia resolvida" e para o status do Item como "Em aberto".
		
	}
	
	public List<CriacaoItemHistorico> verificaHistoricoItem(Integer idCriacaoItem){
		String consulta =  "select h from CriacaoItemHistorico h where idCriacaoItem="+idCriacaoItem+" order by idItemHistorico desc";
	
		Query query = manager.createQuery(consulta).setMaxResults(1);
		return query.getResultList();
	}

	public List<CriacaoItemHistorico> verificaHistoricoItemCompleto(Integer idCriacaoItem){
		String consulta =  "select h from CriacaoItemHistorico h where idCriacaoItem="+idCriacaoItem+" order by idItemHistorico desc";
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}

	public List<CriacaoItemPendencia> verifHistItemPendenc(Integer idCriacaoItem){
		String consulta =  "select h from CriacaoItemPendencia h where idCriacaoItem="+idCriacaoItem;
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}

	
	
	
	
	public Integer novaVersao(Integer idItemLista){
		
		CriacaoItemLista item = manager.find(CriacaoItemLista.class, idItemLista);
		CriacaoItemLista itemNovo = new CriacaoItemLista();
		CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, 1);
		
		itemNovo = item;
		itemNovo.setVersao(item.getVersao() + 1);
		itemNovo.setCriacaoItemStatus(status);
		manager.persist(itemNovo);
		
		CriacaoItemHistorico historicoItemNovo = new CriacaoItemHistorico();
		historicoItemNovo.setCriacaoItemLista(itemNovo);
		historicoItemNovo.setCriacaoItemStatus(status);		
		manager.persist(historicoItemNovo);
		
		CriacaoLista criacaoLista = manager.find(CriacaoLista.class, item.getCriacaoLista().getIdCriacaoLista());
		CriacaoStatus criacaoStatus = manager.find(CriacaoStatus.class, 1);
		
		if (criacaoLista.getCriacaoStatus().getIdCriacaoStatus() == 2){
			criacaoLista.setCriacaoStatus(criacaoStatus);
			criacaoLista.setVersao(criacaoLista.getVersao()+1);
		}else{
		}
		
		return itemNovo.getIdCriacaoItemLista();
	}
	
	public Integer SalvaDI(CriacaoItemLista novoItem){
		
		Calendar c = Calendar.getInstance();
		CriacaoItemLista item = new CriacaoItemLista();
		
		CriacaoLista criacaoLista = manager.find(CriacaoLista.class, novoItem.getIdCriacaoListaTransiente());
		CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, 1);
		Usuario usuario = manager.find(Usuario.class, novoItem.getResponsavelItemTransiente());
		Usuario usersessao = util.retornaUsuarioLogado();
		
		
		
		item.setTituloItem(novoItem.getTituloItem());
		item.setCriacaoLista(criacaoLista);
	 	item.setDataCriacao(c);
		item.setInformaoesItem(novoItem.getInformaoesItem());
		item.setCriacaoItemStatus(status);
		item.setVersao(1);
		item.setLiderCriacao(usuario);
		item.setQuantidade(novoItem.getQuantidadeTransiente());
		item.setDependencia(false);
		item.setDemandaInterna(true);
		item.setCriadoPor(usersessao);
		item.setApres(1);
		
		manager.detach(novoItem);
		manager.persist(item);
		
		return item.getCriacaoLista().getIdCriacaoLista();
	}

	public Integer Cria3D(Integer idCriacaoItemLista){
		
		CriacaoItemLista item = new CriacaoItemLista();

		CriacaoItemLista itemLista = manager.find(CriacaoItemLista.class, idCriacaoItemLista);
		
		CriacaoLista criacaoLista = manager.find(CriacaoLista.class, itemLista.getCriacaoLista().getIdCriacaoLista());
		CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, 1);
		
		Usuario usuario = manager.find(Usuario.class, criacaoLista.getUsuarioReponsável().getIdUsuario());
		
		Usuario usersessao = util.retornaUsuarioLogado();
		
		/**
		 * Pegar usuario responsavel do 3D
		 */
		Usuario usuario3D = manager.find(Usuario.class, 45);
		
		item.setTituloItem("3D - "+itemLista.getTituloItem());
		item.setCriacaoLista(criacaoLista);
		item.setDataCriacao(Calendar.getInstance());
		item.setInformaoesItem(itemLista.getInformaoesItem());
		item.setCriacaoItemStatus(status);
		item.setVersao(1);
		item.setLiderCriacao(usuario);
		item.setResponsavelItem(usuario3D);
		item.setCriadoPor(usersessao);
		item.setQuantidade(1);
		item.setDependencia(false);
		item.setDemandaInterna(true);
		item.setItemDelegado(true);
		item.setItem3D(true);
		item.setApres(1);
		
		manager.persist(item);
		
		return item.getCriacaoLista().getIdCriacaoLista();
	}
	
	
	public List<Object> contaTempo(Integer idCriacaoItem){
		
		/**
		 * Pega todo o histórico do item
		 * 
		 */
		List<CriacaoItemHistorico>  historicoItens = verificaHistoricoItemCompleto(idCriacaoItem);
		
		
		List<Object> dataFinal = new ArrayList<Object>();

		for (CriacaoItemHistorico criacaoItemHistorico : historicoItens) {
			
			/**
			 * Pega somente os itens que tem uma data finalizada
			 * 
			 */
			if(criacaoItemHistorico.getDataTermino() != null){
				ArrayList<Object> tempo2 = new ArrayList<Object>();		
				tempo2.add(criacaoItemHistorico.getIdItemHistorico());
				
				ArrayList<Integer> tempo = Utilitaria.diferencaEmMinutos(criacaoItemHistorico.getDataInicio().getTime(),criacaoItemHistorico.getDataTermino().getTime());
				
				tempo2.add(tempo);

				dataFinal.add(tempo2);
				
			}else{

			}
		}
	
		return dataFinal;
	}
	
	public List<Object> somaTempoTotal(Integer idCriacaoItem){
		
		// Verificar se esse item teve pendencia
		// Se tiver calcular esse tempo junto com o tempo regular.
		
		List<CriacaoItemHistorico>  historicoItens = verificaHistoricoItemCompleto(idCriacaoItem);
		List<CriacaoItemPendencia>  histPendItens = verifHistItemPendenc(idCriacaoItem);
		
		Integer somaHora = 0;
		Integer pegaHora = 0;
		Integer pegaHoraPendencica = 0;
		double t = 0;
		
		
		if(historicoItens.isEmpty()){
			
		}else{
			for (CriacaoItemHistorico criacaoItemHistorico : historicoItens) {
				if(criacaoItemHistorico.getDataTermino() != null){
					Long pegaaHora =  Utilitaria.diferencaDatasEmMilisegundos(criacaoItemHistorico.getDataInicio().getTime(),criacaoItemHistorico.getDataTermino().getTime());
					pegaHora = util.converteLongParaInteger(pegaaHora);
					somaHora = somaHora + pegaHora;
				}else{
					//	
				}
			}
		}
		
		if(histPendItens.isEmpty()){
			
		}else{
			/**
			 * Somar horas de historico Pendencia + historico do item
			 */
			for(int i = 0; i < histPendItens.size();i++ ){
				
				for(int j = 0; j < histPendItens.get(i).getCriacaoItemPendenciaHistorico().size();j++){
					
					if(histPendItens.get(i).getCriacaoItemPendenciaHistorico().get(j).getDataTermino() != null){
						Long pegaaHora =  Utilitaria.diferencaDatasEmMilisegundos(
								histPendItens.get(i).getCriacaoItemPendenciaHistorico().get(j).getDataInicio().getTime()
								,histPendItens.get(i).getCriacaoItemPendenciaHistorico().get(j).getDataTermino().getTime());
						pegaHoraPendencica = util.converteLongParaInteger(pegaaHora);
						somaHora = somaHora + pegaHoraPendencica;
					}else{
					
					}
				}
				
			}
		}
		
		t = somaHora;
		//Envia o tempo somando em milisegundos e o método converte em [1, 29] (1 Hora, 29 seg ex.)
		ArrayList<Integer> soma = util.somaTempoTotal(t);
		
		List<Object> dataFinal = new ArrayList<Object>();
		dataFinal.add(soma);
		return dataFinal;
	}

	
	public List<CriacaoItemLista> filtroItemCriacao(Integer idCriacaoLista){
		
		String select = "select c from CriacaoItemLista c where ";
		String condicao = "c.criacaoLista.idCriacaoLista= "+idCriacaoLista;
		String status = " and c.criacaoItemStatus.idCriacaoStatus =";
		String orderBy = " order by c.idCriacaoItemLista";

		String conAberto = select + condicao + status +  "1" + orderBy;
		String conPendente = select + condicao + status +  "8" + orderBy;
		String conexecuc = select + condicao + status +  "6" + orderBy;
		String cnInterro = select + condicao + status +  "3" + orderBy;
		String cnpendent = select + condicao + status +  "5" + orderBy;
		String cnexcluid = select + condicao + status +  "7" + orderBy;
		String cnexcluidJob = select + condicao + status +  "4" + orderBy;
		String cnfechado = select + condicao + status +  "2" + orderBy;
	
		
		List<CriacaoItemLista> criacaoItem = new ArrayList<CriacaoItemLista>();
		
		preencheItem(conAberto, criacaoItem);
		preencheItem(conPendente, criacaoItem);
		preencheItem(conexecuc, criacaoItem);
		preencheItem(cnInterro, criacaoItem);
		preencheItem(cnpendent, criacaoItem);
		preencheItem(cnexcluid, criacaoItem);
		preencheItem(cnexcluidJob, criacaoItem);
		
		preencheItem(cnfechado, criacaoItem);

		return criacaoItem;
		
	}

	public List<CriacaoItemLista> testeVazioConsulta(String consulta){
		Query q = manager.createQuery(consulta);
		List<CriacaoItemLista> teste = q.getResultList();
		return teste;
	}
	
	public void preencheItem(String consulta, List<CriacaoItemLista> lista ){
		List<CriacaoItemLista> item = testeVazioConsulta(consulta);
		if(item.isEmpty()){
		}else{
			for(int i = 0; i < item.size(); i++){
				lista.add(item.get(i));
			}	
		}
	}
	
	
	
	
	
	
	
	public void verificaDependencia(){System.out.println(" ???? ");}
	public void salvahistóricoItem(){System.out.println("Histórico Salvo com Sucesso.");}

	
}
