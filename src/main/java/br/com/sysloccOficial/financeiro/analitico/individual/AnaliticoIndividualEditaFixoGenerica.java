package br.com.sysloccOficial.financeiro.analitico.individual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sysloccOficial.financeiro.dao.AnaliticoEditaFixoDAO;

@Controller
public class AnaliticoIndividualEditaFixoGenerica {

	@Autowired AnaliticoEditaFixoDAO fixoDAO;
	
	@RequestMapping("editaFixo")
	public void editaFixo(int idAnalitico, int idTabela, int chkFixo){
		
		String nomeTabela = "FinancImpostos";
		
		fixoDAO.editaFixo(idAnalitico, idTabela, chkFixo,nomeTabela);
	}
	
	
}
