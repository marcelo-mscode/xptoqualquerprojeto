package br.com.sysloccOficial.criacao.controllerCriacao.itens;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

public interface ExibeJSP {
	ModelAndView MV = new ModelAndView();
	public ModelAndView lista(String viewName);
	public ModelAndView listaAddUmObjeto(String viewName,String objetoParaJSP,String ObjetoParaAdicionar);
	public ModelAndView listaAddVariosObjeto(String viewName,String objetoParaJSP,List<String> VariosObjetoParaAdicionar);
}
