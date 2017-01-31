package br.com.sysloccOficial.conf;

import java.util.ArrayList;

public class UtilitariaMsg {

	private String reponsavelNotificado = "Responsável notificado com sucesso!";

	private String dadosSalvo = "Dados salvos com sucesso !";
	
	private String erroEnviarEmails = "Houve um erro ao tentar enviar o email.<br /> Verifique os endereços de emails dos destinatários e tente novamente.";

	private String erroEnviarEmail =  "Houve um erro ao tentar enviar o email.<br /> Verifique o endereço de emails do destinatário e tente novamente.";

	private String comSucesso = "com sucesso !";
	
	private String concluiProspeccao = "Prospeccao Concluida com Sucesso !";

	private String cancelaConcluiProspeccao = "Conclui Prospeccao Cancelada com Sucesso !";
	
// ---------------------------------------------------------------------------- //
	
	public ArrayList<String> msgConfirmacaoDadosSalvos(){
   		    ArrayList<String> e = new ArrayList<String>();
			e.add("sucesso");
			e.add(dadosSalvo);
			return e;
	}
	
	public ArrayList<String> msgProspeccaoConfirmacaoDadosSalvos(){
		ArrayList<String> e = new ArrayList<String>();
		e.add("sucesso");
		e.add(concluiProspeccao);
		return e;
	}

	public ArrayList<String> msgProspeccaoCancelaDadosSalvos(){
		ArrayList<String> e = new ArrayList<String>();
		e.add("sucesso");
		e.add(cancelaConcluiProspeccao);
		return e;
	}

	public ArrayList<String> msgComSucesso(){
		ArrayList<String> e = new ArrayList<String>();
		e.add("sucesso");
		e.add(dadosSalvo);
		return e;
	}
	
	
	
// ---------------------------------------------------------------------------- //	
	
	
	
	public String getReponsavelNotificado() {
		return reponsavelNotificado;
	}

	public String getConcluiProspeccao() {
		return concluiProspeccao;
	}

	public String getCancelaConcluiProspeccao() {
		return cancelaConcluiProspeccao;
	}

	public String getDadosSalvo() {
		return dadosSalvo;
	}



	public void setDadosSalvo(String dadosSalvo) {
		this.dadosSalvo = dadosSalvo;
	}



	public String getComSucesso() {
		return comSucesso;
	}



	public void setComSucesso(String comSucesso) {
		this.comSucesso = comSucesso;
	}



	public String getSalvo() {
		return dadosSalvo;
	}

	public void setSalvo(String salvo) {
		this.dadosSalvo = salvo;
	}

	public void setReponsavelNotificado(String reponsavelNotificado) {
		this.reponsavelNotificado = reponsavelNotificado;
	}

	public String getErroEnviarEmails() {
		return erroEnviarEmails;
	}

	public void setErroEnviarEmails(String erroEnviarEmails) {
		this.erroEnviarEmails = erroEnviarEmails;
	}

	public String getErroEnviarEmail() {
		return erroEnviarEmail;
	}

	public void setErroEnviarEmail(String erroEnviarEmail) {
		this.erroEnviarEmail = erroEnviarEmail;
	}
	
	
	
	
	
	
	
	
}
