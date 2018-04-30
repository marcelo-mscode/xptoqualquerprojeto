package br.com.sysloccOficial.financeiro.contaspagar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.financeiro.contaspagar.services.ServiceContasPagar;
import br.com.sysloccOficial.financeiro.dao.ContasPagarDAO;
import br.com.sysloccOficial.financeiro.dao.MontaContasPagarDAO;
import br.com.sysloccOficial.model.producao.ProducaoP;


@Controller
public class ContasPagarController {
	
	@Autowired ContasPagarDAO contasPagar;
	@Autowired InternaAuxiliar internaAuxiliar;
	@Autowired Utilitaria utildatas;
	@Autowired MontaContasPagarDAO montaObjeto;
	
	@Autowired ServiceContasPagar serviceContasPagar;
	
	
	
	@RequestMapping("contasPagar")
	public ModelAndView contasPagar(){
		ModelAndView MV = new ModelAndView("financeiro/contasPagar/contasPagar");
		
// ---- Listas do Mes Atual
		
		List<Object[]> idListas = serviceContasPagar.montaListaMesAtual(); 
		
		List<Object[]> listaAtual = serviceContasPagar.constroiObjeto(idListas);
		
		MV.addObject("novaLista", listaAtual);
		
		removeIdsVazios(idListas, listaAtual);
		MV.addObject("idListas",idListas);
		
// ---- Listas de meses anteriores
		List<Object[]> idListasAnteriores = montaObjeto.pegaListasMesAnterior(); 
		
		List<Object[]> listaAnteriores = montaObjeto.constroiObjeto();
		MV.addObject("listaAnteriores", listaAnteriores);
		
		removeIdsVazios(idListasAnteriores, listaAnteriores);
		MV.addObject("idListasAnteriores",idListasAnteriores);

		/*
// ---- Soma Total		
		BigDecimal somaTotal = montaObjeto.somaTotalMeses(listaAtual, listaAnteriores);
	    MV.addObject("somaTotal", somaTotal);
		*/
		return MV;
	}


	@RequestMapping("contasTeste")
	private ModelAndView contaTeste(){
		ModelAndView MV = new ModelAndView("menuProducao/testeContas");

		return MV;
	}
	
	@RequestMapping("pagaConta")
	public ModelAndView pagaConta(Integer idLista,Integer idFornecedor,Integer qtdDias,Integer idbanco){
		
		montaObjeto.pagaConta(idLista,idFornecedor,qtdDias,idbanco);
		
		ModelAndView MV = new ModelAndView("financeiro/contasPagar/contasPagarAjax");
	
		
		List<Object[]> idListas = montaObjeto.pegaListasIndividuais(); 
		MV.addObject("idListas",idListas);
		
		List<Object[]> lista = montaObjeto.constroiObjeto();
		
		
		
		
		MV.addObject("novaLista", lista);
		
		return MV;
	}
	
	
	private void removeIdsVazios(List<Object[]> idListas, List<Object[]> listaAtual) {
		HashSet<Integer> idsAtuais = new HashSet<Integer>();
		
		for (int i = 0; i < listaAtual.size(); i++) {
			idsAtuais.add((Integer) listaAtual.get(i)[3]);
		}
		
		for (int i = 0; i < idListas.size(); i++) {
			boolean apaga = false;
			for (Integer compara : idsAtuais) {
				if(idListas.get(i)[0].equals(compara)){
					apaga = true;
				}
			}
			if(apaga == false){
				idListas.remove(i);
				i--;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
