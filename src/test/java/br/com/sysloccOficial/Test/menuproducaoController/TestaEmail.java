package br.com.sysloccOficial.Test.menuproducaoController;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.sysloccOficial.controllers.EmailController;
import br.com.sysloccOficial.model.prospeccao.InteracaoProspeccao;

public class TestaEmail {

	
	
	@Test
	public void comunicarOutrosPorEmail() {
		
		InteracaoProspeccao interacao = new InteracaoProspeccao();
		
		List<String> OutrosEmailsInternos = new ArrayList<String>();
		OutrosEmailsInternos.add("sisloc@loccoagencia.com.br");
		
		EmailController email = new EmailController();
		
		if(interacao.getMultiplo() != null){
			String subject = "Nova Interação da Prospecção: Teste de Email prospc";
			
			
			String textAutomatico = ""
					+ "\b{Esse é um email automatico, por favor não responda.}"
					+ "\n\n"
					+ "Enviado por: MArcelo Teste"
					+ "\n\rEmail: syslocc@loco.com"
					+ "\n\nTitulo da Interação: Apenas teste de envio"
					+ "\nDescrição da Interação: tetestestestsseste";
			
			/*OutrosEmailsInternos = usuarioDAO.someEmailUser(interacao.getMultiplo());*/
			email.severalEmail(OutrosEmailsInternos, subject, textAutomatico);
		}
	}
	
	
	
	
	
	
}
