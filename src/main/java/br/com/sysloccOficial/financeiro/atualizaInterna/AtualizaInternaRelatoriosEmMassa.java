package br.com.sysloccOficial.financeiro.atualizaInterna;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.model.Lista;

public class AtualizaInternaRelatoriosEmMassa {

	@Autowired RelatorioEventoDAO relatorioDAO;
	@Autowired AtualizaRelatorioEventoApoio relatorioApoio;
	@Autowired UtilitariaDatas utildatas;
	
	public AtualizaInternaRelatoriosEmMassa() {
		super();
	}

	protected void atualizaInternaRelatoriosEventosEmMassa(LinkedHashSet<Integer> idsListas)throws ParseException {

				for (Integer integer : idsListas) {
					
					 Lista infoLista2 =  relatorioDAO.listaPorIdLista(integer);
					 ArrayList<String> datas2 =  relatorioDAO.dataRelatoriosEventosCadastrados(integer);
					 
					 String mes2 = datas2.get(1).toUpperCase().toString();
					 String ano2 = datas2.get(2).toUpperCase().toString();
					 
					 relatorioApoio.montaObjetoRelatorio(integer,infoLista2,mes2,ano2);
					 relatorioApoio.montaObjetoRelatorioNDNovaRegra(integer,infoLista2,mes2,ano2);
					 
				}

				/*for (Integer integer : idsListas) {
					
					Lista infoLista2 =  relatorioDAO.listaPorIdLista(integer);
					ArrayList<String> datas2 =  relatorioDAO.dataRelatoriosEventosCadastrados(integer);
					
					String mes2 = datas2.get(1).toUpperCase().toString();
					String ano2 = datas2.get(2).toUpperCase().toString();
					
					relatorioApoio.montaObjetoRelatorio(integer,infoLista2,mes2,ano2);
					// relatorioApoio.montaObjetoRelatorioND(integer,infoLista2,mes2,ano2);
					
				}*/
			}

}