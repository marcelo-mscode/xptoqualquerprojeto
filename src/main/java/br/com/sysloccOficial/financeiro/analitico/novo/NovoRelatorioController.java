package br.com.sysloccOficial.financeiro.analitico.novo;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.dao.AnaliticoDAO;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;


@Controller
public class NovoRelatorioController {
	
	@Autowired private AnaliticoDAO analiticoDAO;
	@Autowired private UtilitariaDatas utilDatas;
	@Autowired private Utilitaria util;
	@Autowired private NovoRelatorioCopiaMesAnteriorService CopiaRelatorioAnterior;
	
	
	@RequestMapping("novoAnalitico")
	public ModelAndView novoAnalitico(){
		ModelAndView MV = new ModelAndView("financeiro/analitico/novo/analiticonovo");
		Calendar cal = Calendar.getInstance();
		int ano = cal.get(Calendar.YEAR);
		MV.addObject("ano", ano);
		
		List<String> mesesRemovidos = util.removerDuplicadosDuasListas(utilDatas.mesesAno(),analiticoDAO.indexAnaliticoPorMesAno(ano));
		MV.addObject("mesesAno", mesesRemovidos);
		
		return MV;
	}
	
	@RequestMapping("criaNovo")
	public String criaNovo(FinancAnalitico novoAnalitico) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		
		FinancAnalitico novoAnaliticoPersistido = analiticoDAO.salvaNovoAnalitico(novoAnalitico);
//		Integer idAnalitico = 15;
		
		CopiaRelatorioAnterior.copiaOutrosImpostosReflection(novoAnaliticoPersistido);
		
		
		return "redirect:analiticoIndividual?idAnalitico="+novoAnaliticoPersistido.getIdAnalitico();
	}
	
}
