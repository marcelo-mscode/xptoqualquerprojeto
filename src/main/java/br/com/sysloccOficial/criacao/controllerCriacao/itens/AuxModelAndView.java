package br.com.sysloccOficial.criacao.controllerCriacao.itens;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;
import br.com.sysloccOficial.model.CriacaoLista;

@Repository
public class AuxModelAndView extends Criacao{

	ModelAndView MV = new ModelAndView();
	
	 	
	public ModelAndView listaAddUmObjeto(String viewName,String objetoParaJSP, List<CriacaoLista> ObjetoParaAdicionar) {
		MV.setViewName(viewName);
		MV.addObject(objetoParaJSP, ObjetoParaAdicionar);
		return MV;
	}
	
	public ModelAndView listaAddUmObjetoGenerico(String viewName,String objetoParaJSP, Object ObjetoParaAdicionar) {
		MV.setViewName(viewName);
		MV.addObject(objetoParaJSP, ObjetoParaAdicionar);
		return MV;
	}

	public ModelAndView listaAddVariosObjetosGenericos(String viewName,String objetoParaJSP, List<Object[]> ObjetoParaAdicionar) {
		MV.setViewName(viewName);
		MV.addObject(objetoParaJSP, ObjetoParaAdicionar);
		return MV;
	}

	public ModelAndView listaAddVariosObjeto(String viewName,String objetoParaJSP, List<String> VariosObjetoParaAdicionar) {
		MV.setViewName(viewName);
		for(String objetos : VariosObjetoParaAdicionar){
			MV.addObject(objetoParaJSP, objetos);
		}
		System.out.println("Adicionei Vários");
		return MV;
	}

	public ModelAndView lista(String viewName) {
		MV.setViewName(viewName);
		System.out.println("Exibi a página");
		return MV;
	}

	
	
}
