package br.com.sysloccOficial.financeiro.analitico.individual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sysloccOficial.financeiro.dao.AnaliticoEditaFixoDAO;

@Controller
public class AnaliticoIndividualEditaFixoGenerica {

	@Autowired AnaliticoEditaFixoDAO fixoDAO;
	
	@RequestMapping("editaFixo")
	public void editaFixo(int idAnalitico, int idTabela, int chkFixo,int codigo) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
		String testeNomeTabela = retornaNomeTabela(codigo);
		fixoDAO.editaFixo(idAnalitico, idTabela, chkFixo,testeNomeTabela);
	}
	
	
	private String retornaNomeTabela(int codigo){
		String nome = null;
		
		if(codigo == 075541){
			nome="FinancImpostos";
		}
		return nome;
	}
	
	
}
