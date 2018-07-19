package br.com.sysloccOficial.financeiro.atualizaInterna;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.model.Lista;

@Controller
public class AtualizaInternaController extends AtualizaInternaRelatoriosEmMassa {

	@Autowired UtilitariaDatas utildatas;
	
	@RequestMapping("atualizaInterna")
	public String atualizaInterna(Integer idLista) throws ParseException{
		
		 ArrayList<String> datas =  relatorioDAO.dataRelatoriosEventosCadastrados(idLista);
		 String mes = datas.get(1).toUpperCase().toString();
		 String ano = datas.get(2).toUpperCase().toString();
		 
	
		 //Verifica se tem algum relatório ja cadastrado no sistema
		 
		 Lista infoLista =  relatorioDAO.listaPorIdLista(idLista);
		 relatorioApoio.montaObjetoRelatorio(idLista,infoLista,mes,ano);

		 LinkedHashSet<Integer> listaIdsRelatoriosEventosCadastrados = relatorioDAO.idsListaRelatoriosEventosPorMesAno(mes, ano);
		 
		 /**
		  *	Método herdado que Faz a atualização em massa dos relatórios de eventos passando os ids das listas
		  * 
		  */
			 
		 atualizaInternaRelatoriosEventosEmMassa(listaIdsRelatoriosEventosCadastrados);
		 
		 
		 /**
		  * Método para gerar um relatório com base de ND
		  */
		 relatorioApoio.montaObjetoRelatorioNDNovaRegra(idLista,infoLista,mes,ano);
		 //relatorioApoio.montaObjetoRelatorioND(idLista,infoLista,mes,ano);
		 
		 /*//Verifica se tem algum relatório ja cadastrado no sistema
		 LinkedHashSet<Integer> listaIdsRelatoriosEventosCadastrados = relatorioDAO.idsListaRelatoriosEventosPorMesAno(mes, ano);

		 if(listaIdsRelatoriosEventosCadastrados.isEmpty()){
			 Lista infoLista =  relatorioDAO.listaPorIdLista(idLista);
			 relatorioApoio.montaObjetoRelatorio(idLista,infoLista,mes,ano);
		 }else{
			 *//**
			  * 
			  * Método herdado que Faz a atualização em massa dos relatórios de eventos passando os ids das listas
			  *//*
			 atualizaInternaRelatoriosEventosEmMassa(listaIdsRelatoriosEventosCadastrados);
		 }
		 
		 */
		 
 		 //Se datas retornar erro, indica que não tem uma data do evento cadastrada na Lista
 		 //Criar lightbox pedindo para financeiro cadastrar uma data manualmente.
 		 //Depois que a data for inserida no lightbox, cadastrar a data e chamar essa action novamente.
 		 
 		 
		 relatorioDAO.UltimaAtualizacao(idLista);
		 
		return "redirect:internaIndividual?idLista="+idLista;
	}
	
	
	
	
	
	
	
	
	
}
