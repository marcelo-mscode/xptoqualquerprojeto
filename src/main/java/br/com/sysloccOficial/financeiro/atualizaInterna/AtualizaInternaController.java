package br.com.sysloccOficial.financeiro.atualizaInterna;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sysloccOficial.financeiro.dao.InternaIndividualDAO;
import br.com.sysloccOficial.financeiro.model.CalculoValoresInterna;
import br.com.sysloccOficial.model.producao.ProducaoP;
import br.com.sysloccOficial.model.producao.ProducaoPDespesas;

@Controller
public class AtualizaInternaController {

	@Autowired InternaIndividualDAO internaIndividualDAO;
	@Autowired AtualizaRelatorioEventoApoio relatorioApoio;
	
	@RequestMapping("atualizaInterna")
	public String atualizaInterna(Integer idLista){
		
		
		String mes = "MAIO";
		String ano = "2016";
		
		relatorioApoio.montaObjetoRelatorio(idLista,mes,ano);
	
		return "redirect:internaIndividual?idLista="+idLista;
		
	}
	
	
	
	
	
	
	
	
	
}
