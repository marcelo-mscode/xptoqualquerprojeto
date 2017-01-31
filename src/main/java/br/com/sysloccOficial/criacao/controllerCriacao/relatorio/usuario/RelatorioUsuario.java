package br.com.sysloccOficial.criacao.controllerCriacao.relatorio.usuario;

import java.util.ArrayList;
import java.util.List;

import br.com.sysloccOficial.model.criacao.CalculoHorasLista;

public abstract class RelatorioUsuario {

	
	
	/**
	 * Método que pegará os ids das Listas que tem no mínimo um
	 * item que o usuário esteja envolvido
	 * 
	 */
	public abstract List<Integer> pegaIdsListasPorUsuario (String idusuario,String mes, String ano);
	
	
	/**
	 * Método para pegar os ids dos itens que o usuário está envolvido
	 * 
	 * @param idsListasPeriodos
	 * @param idUsuario
	 * @return
	 */
	public abstract List<Integer> idsItensPorUsuario (List<Integer> idsListasPeriodos,String idUsuario);
	
	
	
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
	public abstract ArrayList<Integer> somaTempoItensTodasListaPeriodos(List<Integer> idCriacaoItem, String idUsuario);
	
	/**
	  * Método que retorna o tempo de cada item individualmente que o usuário esteve envolvido em cada lista.
	  * 
	  * @param idCriacaoItem
	  * @param idUsuario
	  * @return
	  */
	 public abstract List<CalculoHorasLista> somaTempoListaVariosItens(List<Integer> idCriacaoItem, Integer idUsuario);
	
	 
	 /**
	  * Método usado para somar o tempo item por item por usuário,
	  * usando o histórico do item e a pendência para isso. 
	  *
	  * @param idCriacaoItem
	  * @param usuario
	  * @return
	  */
	 public abstract double somaTempoTotalItemPorUsuario(Integer idCriacaoItem, Integer usuario);

	
	 /**
	  * Método que pega os itens da Lista, e soma o tempo de seus itens por usuario
	  * 
	  */
	 public abstract List<CalculoHorasLista> somaTempoItensPorLista(List<Integer> idsListasPeriodos, String idUsuario);
	
	
}
