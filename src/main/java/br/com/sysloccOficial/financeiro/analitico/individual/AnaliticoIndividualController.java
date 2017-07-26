package br.com.sysloccOficial.financeiro.analitico.individual;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualDAO;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;
import br.com.sysloccOficial.financeiro.model.FinancEscritorio;
import br.com.sysloccOficial.financeiro.model.FinancTelefone;
import br.com.sysloccOficial.model.VideosYt;



@Controller
public class AnaliticoIndividualController {
	
	@Autowired private AnaliticoIndividualDAO analiticoIndDAO;
	
	@RequestMapping("analiticoIndividual")
	private ModelAndView analiticoIndividual(Integer idAnalitico){
		ModelAndView MV = new ModelAndView("financeiro/analitico/relatorio/analiticoindividual");
		
		FinancAnalitico analitico = analiticoIndDAO.carregaAnaliticoIndividual(idAnalitico);
		MV.addObject("InfoAnalitico",analitico);
		
		MV.addObject("escritorio",analiticoIndDAO.carregaAnaliticoIndividualEscritorio(idAnalitico));
		MV.addObject("telefone",analiticoIndDAO.carregaAnaliticoIndividualTelefones(idAnalitico));
		MV.addObject("folha",analiticoIndDAO.carregaAnaliticoIndividualFolha(idAnalitico));
		MV.addObject("despesas",analiticoIndDAO.carregaAnaliticoIndividualDespesas(idAnalitico));
		MV.addObject("outrasdespesas",analiticoIndDAO.carregaAnaliticoIndividualOutrasDespesas(idAnalitico));
		MV.addObject("impostos",analiticoIndDAO.carregaAnaliticoIndividualFinancImpostos(idAnalitico));
		
		return MV;
	}
	

	@RequestMapping("videos")
	public ModelAndView videos (){
		
		ModelAndView MV = new ModelAndView("videos/videos");
		
		
		
		return MV;
	}
	
	@RequestMapping("salvarVideos")
	public ModelAndView salvarVideos(VideosYt videos){
		ModelAndView MV = new ModelAndView("videos/videos");
		
		
		analiticoIndDAO.salvaVideo(videos);
		
		System.out.println("Teste NOVo");

		
		return MV;
	}
	
	
	
	
}
