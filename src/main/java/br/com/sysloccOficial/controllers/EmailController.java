package br.com.sysloccOficial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;


@Controller
@Transactional
public class EmailController {

	
	@Autowired private MailSender mailer;
	
	public boolean singleEmail(String to, String subject,String text){
		
	//	String msg = "";
		boolean msg = false;     
	try {
		
			SimpleMailMessage email = new SimpleMailMessage();
			
			email.setFrom("sisloc@loccoagencia.com.br");
			email.setTo(to);
			email.setSubject(subject);
			email.setText(text);
			System.out.println("Enviando email para: "+to);
			mailer.send(email);
			
			msg = true;
			 
			System.out.println("Email enviado com sucesso.");
			return msg;
			
	} catch (Exception e) {
		//JOptionPane.showMessageDialog(null, ""+e);
		//msg = "Houve um erro ao tentar enviar o email.<br /> Verifique o endereço de email do destinatário e tente novamente.";
		msg = false;
		return msg;
	}
			
	}

	public boolean severalEmail(List<String> string2, String subject,String text){
	/**
	 * Ao receber a lista de email para envio, o sistema ja garantiu, via consulta no banco,
	 * que não receberá nenhum email em branco.
	 * 
	 * Ver sql do método AllEmailUsers() em UsuarioDAO
	 * 
	 */
		
			boolean msg = false;

			SimpleMailMessage email = new SimpleMailMessage();
			
			email.setFrom("sisloc@loccoagencia.com.br");
			String emailErro = "";
			try {
				
				for (String string : string2) {
					
					email.setTo(string);
					email.setSubject(subject);
					email.setText(text);
					System.out.println("Enviando email para: "+string);
					mailer.send(email);
					
					emailErro = string;
					
				}	
				
				msg = true;
				
				return msg;
				
			} catch (Exception e) {
				System.out.println("Erro ao enviar o Email: "+emailErro);
				msg = false;
				return msg;
			}
						

	
	
	}

	
	
	
	
	
}
