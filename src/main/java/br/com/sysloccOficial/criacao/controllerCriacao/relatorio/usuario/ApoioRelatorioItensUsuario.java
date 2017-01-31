package br.com.sysloccOficial.criacao.controllerCriacao.relatorio.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import schemasMicrosoftComOfficeOffice.ForcedashAttribute;
import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.criacao.controllerCriacao.dao.RelatorioDAO;
import br.com.sysloccOficial.criacao.controllerCriacao.dao.RelatorioUsuarioDAO;
import br.com.sysloccOficial.criacao.controllerCriacao.relatorio.apoio.ApoioRelatorioSomaTempo;
import br.com.sysloccOficial.model.CriacaoItemHistorico;
import br.com.sysloccOficial.model.CriacaoItemLista;
import br.com.sysloccOficial.model.CriacaoItemPendencia;
import br.com.sysloccOficial.model.criacao.CalculoHorasLista;

@Component
public class ApoioRelatorioItensUsuario extends RelatorioUsuario{
	
	@Autowired private  ApoioRelatorioSomaTempo somaTempo;
	@Autowired private RelatorioUsuarioDAO relatorioUsuarioDAO;
	@Autowired private RelatorioDAO relatorioDAO;
	@Autowired private Utilitaria util;
	
	
	/**
	 * Método para pegar ids das Lista que tem no mínimo um item com envolvimento
	 * do Usuário
	 */
	@Override
	public List<Integer> pegaIdsListasPorUsuario(String idusuario,String mes, String ano) {
		List<Integer> idsListasPeriodos = relatorioUsuarioDAO.idsListaPorUsuario(idusuario,mes,ano);
		return idsListasPeriodos;
	}
	
	/**
	 * Método para pegar os ids dos itens que o usuário está envolvido
	 * 
	 * @param idsListasPeriodos
	 * @param idUsuario
	 * @return
	 */
	@Override
	public List<Integer> idsItensPorUsuario(List<Integer> idsListasPeriodos, String idUsuario) {
		List<Integer> idsItensPeriodos = relatorioUsuarioDAO.idsItensPorUsuario(idsListasPeriodos,idUsuario);
		return idsItensPeriodos;
	}
	
	/**
	 * Método para somar o tempo de todos os itens, de todas as listas
	 * que o usuario esteve envolvindo.
	 * Esse método devolverá a soma do tempo gasto total que cada usuário teve em todas as listas 
	 * no período informado.   
	 *   
	 * @param idCriacaoItem
	 * @param idUsuario
	 * @return
	 */
	public ArrayList<Integer> somaTempoItensTodasListaPeriodos(List<Integer> idCriacaoItem, String idUsuario){
    	double t = 0;
    	
    	for (int i = 0; i < idCriacaoItem.size(); i++) {
    		t = t + somaTempoTotalItemPorUsuario(idCriacaoItem.get(i),Integer.parseInt(idUsuario));
		}
    	ArrayList<Integer> tempo = util.somaTempoTotal(t);
    	return tempo;
    }
	
	 /**
	  * Método que retorna o tempo de cada item individualmente que o usuário esteve envolvido em cada lista.
	  * 
	  * @param idCriacaoItem
	  * @param idUsuario
	  * @return
	  */
	 public List<CalculoHorasLista> somaTempoListaVariosItens(List<Integer> idCriacaoItem, Integer idUsuario){
	    	
	    	List<CalculoHorasLista> dataFinalTeste = new ArrayList<CalculoHorasLista>();
	    	
	    	
	    	for (int i = 0; i < idCriacaoItem.size(); i++) {
	    		double t = 0;
	    		CalculoHorasLista novo = new CalculoHorasLista();
	    		
	    		t = t + somaTempoTotalItemPorUsuario(idCriacaoItem.get(i),idUsuario);

	    		novo.setTeste(util.somaTempoTotal(t));
	    		novo.setIdCriacaoLista(idCriacaoItem.get(i));
	    		dataFinalTeste.add(novo);
			}
	    	
	    	return dataFinalTeste;
	    }
	
	 
	 /**
	  * Método usado para somar o tempo item por item por usuário,
	  * usando o histórico do item e a pendência para isso. 
	  *
	  * @param idCriacaoItem
	  * @param usuario
	  * @return
	  */
	 public double somaTempoTotalItemPorUsuario(Integer idCriacaoItem, Integer usuario){
	    	
	    	// Verificar se esse item teve pendencia
	    	// Se tiver calcular esse tempo junto com o tempo regular.
	    	
	    	List<CriacaoItemHistorico>  historicoItens = relatorioDAO.verificaHistoricoItemCompletoPorusuario(idCriacaoItem,usuario);
	    	List<CriacaoItemPendencia>  histPendItens = relatorioDAO.verifHistItemPendencPorUsuario(idCriacaoItem,usuario);
	    	
	    	Integer somaHoraItem = 0;
	    	Integer somaHoraPendencia = 0;
	    	double t = 0;
	    	
	    	//Pega as datas inicio e data do fim do item, converte para milisegundos e vai somando
	    	//na variável somaHora
	    	somaHoraItem = somaTempo.somaHoraHistoricoItem(historicoItens, somaHoraItem);
	    	somaHoraPendencia = somaTempo.somaPendenciaItem(histPendItens, somaHoraPendencia);
	    	
	    	t = somaHoraItem + somaHoraPendencia;
	    	
	    	return t;
	    }

	@Override
	public List<CalculoHorasLista> somaTempoItensPorLista(List<Integer> idsListasPeriodos, String idUsuario) {
		List<CalculoHorasLista> dataFinalTeste = new ArrayList<CalculoHorasLista>();
		for (int i = 0; i < idsListasPeriodos.size(); i++) {
			List<Integer> listaDeItens = relatorioUsuarioDAO.idsItensPorLista(idsListasPeriodos.get(i));
				double t = 0;
				for (int j = 0; j < listaDeItens.size(); j++) {
					t = t + somaTempoTotalItemPorUsuario(listaDeItens.get(j), Integer.parseInt(idUsuario));
				}
				CalculoHorasLista novo = new CalculoHorasLista();
	    		novo.setTeste(util.somaTempoTotal(t));
	    		novo.setIdCriacaoLista(idsListasPeriodos.get(i));
	    		dataFinalTeste.add(novo);
		}
		return dataFinalTeste;
	}
	
}
