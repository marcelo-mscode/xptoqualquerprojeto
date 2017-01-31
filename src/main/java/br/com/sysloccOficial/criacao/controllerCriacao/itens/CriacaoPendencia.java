package br.com.sysloccOficial.criacao.controllerCriacao.itens;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.consultas.ConsultasGenericas;
import br.com.sysloccOficial.model.CriacaoItemHistorico;
import br.com.sysloccOficial.model.CriacaoItemPendencia;
import br.com.sysloccOficial.model.CriacaoItemLista;
import br.com.sysloccOficial.model.CriacaoItemPendenciaHistorico;
import br.com.sysloccOficial.model.CriacaoItemStatus;
import br.com.sysloccOficial.model.Usuario;

public class CriacaoPendencia {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired	private ConsultasGenericas consultaGenerica;
	@Autowired private Utilitaria util;
	ModelAndView MV = new ModelAndView();
	
	
	
	public List<Object[]> exibePendencias(String termo){
		String consulta="SELECT p FROM CriacaoItemPendencia p "+termo;
		return consultaGenerica.ListaDeObjetos(consulta);
	}

	public List<Object[]> exibeTarefas(String termo){
		String consulta="SELECT p FROM CriacaoItemLista p "+termo;
		return consultaGenerica.ListaDeObjetos(consulta);
	}
	
	public Object exibeDetalheItem(Integer idHistoricoItem){
		String consulta="SELECT p FROM CriacaoItemPendencia p where idCriacaoItemHistoricoPendencia ="+idHistoricoItem;
		Query query = manager.createQuery(consulta, CriacaoItemPendencia.class);

		return query.getSingleResult();
	}

	public Object exibeDetalhePendenciaHistorico(Integer idHistoricoItem){
		String consulta="SELECT p FROM CriacaoItemPendenciaHistorico p where idCriacaoItem ="+idHistoricoItem+" order by idItemHistoricoPendencia desc";
		Query query = manager.createQuery(consulta, CriacaoItemPendenciaHistorico.class).setMaxResults(1);
		return query.getSingleResult();
	}
	
	public List<CriacaoItemPendenciaHistorico>historicoCompleto(Integer idHistoricoItem){
		String consulta="SELECT p FROM CriacaoItemPendenciaHistorico p where idCriacaoItem ="+idHistoricoItem;
		Query query = manager.createQuery(consulta, CriacaoItemPendenciaHistorico.class);
		return query.getResultList();
	}

		
	
	
	public void iniciaTempo(Integer idCriacaoItemHistoricoPendencia){
		CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, 6);
		// Verificar se existe historico com o id do item. Dar select pegar o último registro.
		
		// Se existir e o status for igual 
		
		//CriacaoItemPendencia itemPendencia = manager.find(CriacaoItemPendencia.class, idCriacaoItemHistoricoPendencia);
		CriacaoItemPendenciaHistorico cItemPendenciaHistorico = manager.
				find(CriacaoItemPendenciaHistorico.class, idCriacaoItemHistoricoPendencia);
		cItemPendenciaHistorico.setDataInicio(Calendar.getInstance());
		cItemPendenciaHistorico.setCriacaoItemStatus(status);
		manager.merge(cItemPendenciaHistorico);
		
		Integer c =  cItemPendenciaHistorico.getCriacaoItemPendencia().getIdCriacaoItemHistoricoPendencia();
		CriacaoItemPendencia itemPend = manager.find(CriacaoItemPendencia.class, c);
		itemPend.setCriacaoItemStatus(status);

		manager.merge(itemPend);
		
	}

	public ModelAndView iniciaTempoAjax(Integer idCriacaoItemHistoricoPendencia){
		String consulta = "SELECT p.dataInicio FROM CriacaoItemPendenciaHistorico"
				+ " p where idItemHistoricoPendencia="+idCriacaoItemHistoricoPendencia;	

		MV.setViewName("criacao/detalhesTempo/inicia");
		MV.addObject("tempo", consultaGenerica.ObjetoCompleto(consulta));
		return MV;
	}
		
	public void terminaTempo(Integer idCriacaoItemHistoricoPendencia){
		
		CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, 6);
		
		CriacaoItemPendenciaHistorico cItemPendenciaHistorico = manager.
				find(CriacaoItemPendenciaHistorico.class, idCriacaoItemHistoricoPendencia);
		cItemPendenciaHistorico.setDataTermino(Calendar.getInstance());
		cItemPendenciaHistorico.setCriacaoItemStatus(status);
		
		manager.merge(cItemPendenciaHistorico);
	}
		
	public ModelAndView terminaTempoAjax(Integer idCriacaoItemHistoricoPendencia){
		MV.setViewName("criacao/detalhesTempo/inicia");
		String consulta = "SELECT p.dataTermino FROM CriacaoItemPendenciaHistorico"
				+ " p where idItemHistoricoPendencia="+idCriacaoItemHistoricoPendencia;	
		MV.addObject("tempo", consultaGenerica.ObjetoCompleto(consulta));
		return MV;
	}
	
	public void salvaPendencia(CriacaoItemPendencia cItemhPendencia){
		CriacaoItemPendencia pendencia = 
		manager.find(CriacaoItemPendencia.class, cItemhPendencia.getIdCriacaoItemHistoricoPendenciaTransiente());
		CriacaoItemStatus status = manager.find(CriacaoItemStatus.class,cItemhPendencia.getCriacaoItemStatusTransiente());
		CriacaoItemStatus statusAberto = manager.find(CriacaoItemStatus.class,1);
		CriacaoItemStatus statusPendenciaFinalizada = manager.find(CriacaoItemStatus.class,8);

		/**
		 * Finaliza o item pendente colocando o status como 2 ( finalizado )
		 * 
		 * Faz um merge na entidade ja criada
		 * 
		 */
		pendencia.setDataTermino(Calendar.getInstance());
		pendencia.setDataTermino(Calendar.getInstance());
		pendencia.setDataPendenciaResolvida(Calendar.getInstance());
		pendencia.setCriacaoItemStatus(status);
		manager.merge(cItemhPendencia);
		

		/**
		 * Muda o status do Item da lista para "Em aberto"
		 * 
		 * Tira o item de pendente
		 * 
		 */
		CriacaoItemLista itemLista = manager.find(CriacaoItemLista.class, cItemhPendencia.getCriacaoItemListaTransiente());
		itemLista.setCriacaoItemStatus(statusAberto);
		itemLista.setResponsavelItem(cItemhPendencia.getEnviadoPor());
		itemLista.setCriacaoItemStatus(statusPendenciaFinalizada);
		itemLista.setDependencia(false);
		
		// Esse Merge é necessário e não estava sendo feito no código
		manager.merge(itemLista);
		
		/**
		 * Cria um novo histórico para o item com as alterações da pendencia
		 * 
		 * 
		 */
		Calendar datahojeCalendar = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String a = s.format(datahojeCalendar.getTime());
        
		CriacaoItemHistorico itemHistorico = new CriacaoItemHistorico();
	    itemHistorico.setCriacaoItemLista(itemLista);
	    String msg = "<b>Pendencia resolvida em:</b></br> "+a+" - <b>por:</b> "+pendencia.getResponsavelItem().getNome();
		itemHistorico.setObs(msg);
		itemHistorico.setCriacaoItemStatus(statusPendenciaFinalizada);
		itemHistorico.setResponsavelItem(itemLista.getResponsavelItem());
		itemHistorico.setVersao(itemLista.getVersao());
		manager.persist(itemHistorico);
		//TODO Pegar User da sessão
		
		
		
		CriacaoItemPendenciaHistorico historico = manager.find(CriacaoItemPendenciaHistorico.class,
				cItemhPendencia.getIdItemHistoricoPendenciaTransiente());
		
		historico.setCriacaoItemStatus(status);
		historico.setObs(cItemhPendencia.getObsTransiente());
		manager.merge(cItemhPendencia);
		
		System.out.println("Salvo com sucesso !");
		
	}
	public void interrompePendencia (CriacaoItemPendencia cItemhPendencia){
		CriacaoItemPendencia item  = manager.find(CriacaoItemPendencia.class, cItemhPendencia.getIdCriacaoItemHistoricoPendenciaTransiente());
		CriacaoItemStatus status = manager.find(CriacaoItemStatus.class, cItemhPendencia.getCriacaoItemStatusTransiente());
		Usuario usuario = manager.find(Usuario.class, item.getResponsavelItem().getIdUsuario());	

		
		CriacaoItemPendenciaHistorico historico = manager.find(CriacaoItemPendenciaHistorico.class,
				cItemhPendencia.getIdItemHistoricoPendenciaTransiente());
		
		historico.setCriacaoItemStatus(status);
		historico.setObs(cItemhPendencia.getObsTransiente());
		manager.merge(cItemhPendencia);
		
		
		CriacaoItemPendenciaHistorico pendenciaHistorico = new CriacaoItemPendenciaHistorico();
		
		pendenciaHistorico.setVersao(item.getCriacaoItemLista().getVersao());
		pendenciaHistorico.setCriacaoItemPendencia(item);
		pendenciaHistorico.setCriacaoItemStatus(status);
		pendenciaHistorico.setResponsavelItem(usuario);
		manager.persist(pendenciaHistorico);
		
		item.setCriacaoItemStatus(status);
		manager.merge(item);
		
	}
	
	
	
	public List<CriacaoItemPendenciaHistorico> verificaHistoricoItemCompleto(Integer idCriacaoItem){
		String consulta =  "select h from CriacaoItemPendenciaHistorico h where idCriacaoItem="+idCriacaoItem+" order by idItemHistoricoPendencia desc";
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}
	
	
	public List<CriacaoItemPendenciaHistorico> verificaHistoricoItemCompletoPendencia(Integer idCriacaoItem){
		//	String consulta =  "select h from CriacaoItemHistorico h where idCriacaoItem="+idCriacaoItem+" order by idItemHistorico desc";

			String consulta =  "select h from CriacaoItemPendenciaHistorico h where idCriacaoItem= "+idCriacaoItem+" order by idItemHistoricoPendencia desc";
			
			Query query = manager.createQuery(consulta);
			return query.getResultList();
		}
	
	
	
	public List<Object> contaTempo(Integer idCriacaoItem){
		
		List<CriacaoItemPendenciaHistorico>  historicoItens = verificaHistoricoItemCompleto(idCriacaoItem);
		
		List<Object> dataFinal = new ArrayList<Object>();

		for (CriacaoItemPendenciaHistorico criacaoItemHistoricoPendenc : historicoItens) {
		
			if(criacaoItemHistoricoPendenc.getDataTermino() != null){
				ArrayList<Object> tempo2 = new ArrayList<Object>();		
				tempo2.add(criacaoItemHistoricoPendenc.getIdItemHistoricoPendencia());
				
				ArrayList<Integer> tempo = Utilitaria.diferencaEmMinutos(criacaoItemHistoricoPendenc.getDataInicio().getTime(),criacaoItemHistoricoPendenc.getDataTermino().getTime());
				
				tempo2.add(tempo);
				
				dataFinal.add(tempo2);
				
			}else{
				//JOptionPane.showMessageDialog(null, "Está vazio");
			}
		}
	
		return dataFinal;
	}
	
	/**
	 * Método para verificar a quantidade de tempo gasto no item em pendencia
	 * 
	 * 
	 * @param idCriacaoItem
	 * @return
	 */
	
	public List<Object> somaTempoTotalPendencia(Integer idCriacaoItem){
		
		List<Object> dataFinal = new ArrayList<Object>();
		List<CriacaoItemPendenciaHistorico>  historicoItens = verificaHistoricoItemCompletoPendencia(idCriacaoItem);
		
		Integer somaHora = 0;
		Integer pegaHora = 0;
		double t = 0;
		
		for (CriacaoItemPendenciaHistorico criacaoItemHistorico : historicoItens) {
			if(criacaoItemHistorico.getDataTermino() != null){
				Long pegaaHora =  Utilitaria.diferencaDatasEmMilisegundos(criacaoItemHistorico.getDataInicio().getTime(),criacaoItemHistorico.getDataTermino().getTime());
				pegaHora = util.converteLongParaInteger(pegaaHora);
				somaHora = somaHora + pegaHora;
			}else{
				//	
			}
		}
		
		t = somaHora;
		ArrayList<Integer> soma = util.somaTempoTotal(t);
		dataFinal.add(soma);
		return dataFinal;
	}
	
	
	
	
	
	
	
	
	
	
//	public void terminaTempo(){System.out.println("Terminando Tempo");}
	
	public void salvaHistorico(){System.out.println("Salvando historico");}
	public void mudaStatus(){System.out.println("Mudando de Status");}
	public void devolvePendencia(){System.out.println("Devolvendo Pendencia");}
	public void tiraPendenciaFila(){System.out.println("Tirando pendencia da fila");}
	public void fechaPendencia(){System.out.println("Fechando pendencia");}
	
	
}
