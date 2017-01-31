package br.com.sysloccOficial.consultas.consultasAvancadas;

/**
 * Classe de apoio por onde passará o resultado da consulta de cada consulta individual
 * 
 */
public class ConsultasPassaSql extends ConsultasApoio{

	public Object retorna(String consulta) {
		return ObjetoCompleto(consulta);
	}

	public Object retornaLista(String consulta) {
		return ObjetoCompletoRetornaLista(consulta);
	}
	
}
