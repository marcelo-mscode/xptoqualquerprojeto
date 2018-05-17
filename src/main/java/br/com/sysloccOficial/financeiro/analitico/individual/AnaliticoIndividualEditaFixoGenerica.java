package br.com.sysloccOficial.financeiro.analitico.individual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sysloccOficial.financeiro.dao.AnaliticoEditaFixoDAO;

@Controller
public class AnaliticoIndividualEditaFixoGenerica {

	@Autowired AnaliticoEditaFixoDAO fixoDAO;
	
	@RequestMapping("editaFixo")
	public String editaFixo(int idAnalitico, int idTabela, int chkFixo,int codigo) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
		String testeNomeTabela = retornaNomeTabela(codigo);
		fixoDAO.editaFixo(idAnalitico, idTabela, chkFixo,testeNomeTabela);
		return "ok";
	}
	
	private String retornaNomeTabela(int codigo){
		String nome = null;
		if(codigo == 075541){ nome="FinancImpostos"; }
		if(codigo == 253652){ nome="FinancEscritorio"; }
		if(codigo == 3225519){ nome="FinancTelefone"; }
		if(codigo == 222354){ nome="FinancFolhaPgto"; }
		if(codigo == 52251){ nome="FinancOutrasDespesas"; }
		return nome;
	}
}
