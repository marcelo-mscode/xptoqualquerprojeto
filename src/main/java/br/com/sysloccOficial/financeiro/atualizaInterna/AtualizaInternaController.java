package br.com.sysloccOficial.financeiro.atualizaInterna;

import java.text.ParseException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.model.Lista;

@Controller
public class AtualizaInternaController {

	@Autowired RelatorioEventoDAO relatorioDAO;
	@Autowired AtualizaRelatorioEventoApoio relatorioApoio;
	@Autowired UtilitariaDatas utildatas;
	
	@RequestMapping("atualizaInterna")
	public String atualizaInterna(Integer idLista) throws ParseException{
		
		 Lista infoLista =  relatorioDAO.listaPorIdLista(idLista);
			
		 ArrayList<String> datas = utildatas.converteDateParaStringNacional(infoLista.getDataDoEvento().getTime());
		 
		 String mes = datas.get(1).toUpperCase().toString();
		 String ano = datas.get(2).toUpperCase().toString();
		
 		 relatorioApoio.montaObjetoRelatorio(idLista,infoLista,mes,ano);
	
 		 //Se datas retornar erro, indica que não tem uma data do evento cadastrada na Lista
 		 //Criar lightbox pedindo para financeiro cadastrar uma data manualmente.
 		 //Depois que a data for inserida no lightbox, cadastrar a data e chamar essa action novamente.
 		 
 		 
 		 
 		 
		return "redirect:internaIndividual?idLista="+idLista;
		
	}
	
	
	
	
	
	
	
	
	
}
