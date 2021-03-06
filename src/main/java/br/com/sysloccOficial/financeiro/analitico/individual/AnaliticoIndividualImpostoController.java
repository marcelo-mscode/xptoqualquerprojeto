package br.com.sysloccOficial.financeiro.analitico.individual;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualEscritorioDAO;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualImpostoDAO;
import br.com.sysloccOficial.financeiro.model.FinancEscritorio;
import br.com.sysloccOficial.financeiro.model.FinancImpostos;

@Controller
public class AnaliticoIndividualImpostoController {
	
	
@Autowired private AnaliticoIndividualImpostoDAO analiticoIndDAO;
	
	@RequestMapping("salvaNovoImposto")
	@ResponseBody
	private ModelAndView salvaNovoImposto(Integer idAnalitico,String valor,String descricao, int chkFixoOutrosImpostos){
		
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/impostoAjax");
	    analiticoIndDAO.salvaNovoImposto(idAnalitico,valor,descricao,chkFixoOutrosImpostos);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancImpostos> analitico2 = analiticoIndDAO.carregaAnaliticoImposto(idAnalitico);
		MV.addObject("impostos",analitico2);
		
		return MV;
	}

	@RequestMapping("editaFinancImposto")
	@ResponseBody
	private ModelAndView editaEscritorio(Integer idTabela,String valor,String tipoCampo){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/impostoAjax");
		
		Integer idAnalitico = analiticoIndDAO.editaImposto(idTabela,valor,tipoCampo);
		
		MV.addObject("idAnalitico",idAnalitico);
		List<FinancImpostos> analitico2 = analiticoIndDAO.carregaAnaliticoImposto(idAnalitico);
		MV.addObject("impostos",analitico2);
		
		return MV;
	}
	
	@RequestMapping("editaImpostoFixo")
	public void editaImpostoFixo(int idAnalitico, int idTabela, int chkFixo){
		analiticoIndDAO.editaFixo(idAnalitico, idTabela, chkFixo);
	}

}
