package br.com.sysloccOficial.consultas.consultasAvancadas;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface Consultas {
	
	public String chamaQuery(String consulta);
	
	public Integer chamaQueryInteger(String consulta);
	
	public Date chamaQueryDate(String consulta);

	public Calendar chamaQueryCalendar(String consulta);
	
	public List<Object[]> ListaDeObjetos(String termo);
	
	public Object[] Objeto(String termo);

	public Object ObjetoCompleto(String termo);

	public Object ObjetoCompletoRetornaLista(String termo);
	
}
