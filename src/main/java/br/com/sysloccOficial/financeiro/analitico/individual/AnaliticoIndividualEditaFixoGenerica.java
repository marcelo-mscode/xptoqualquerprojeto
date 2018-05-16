package br.com.sysloccOficial.financeiro.analitico.individual;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnaliticoIndividualEditaFixoGenerica {

	@RequestMapping("editaFixo")
	public void editaFixo(int idAnalitico, int idTabela, int chkFixo){
		analiticoIndDAO.editaFixo(idAnalitico, idTabela, chkFixo);
	}
	
	
}
