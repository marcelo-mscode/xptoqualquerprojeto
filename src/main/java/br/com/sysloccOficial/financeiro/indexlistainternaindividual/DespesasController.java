package br.com.sysloccOficial.financeiro.indexlistainternaindividual;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.sysloccOficial.financeiro.dao.InternaIndividualDAO;
import br.com.sysloccOficial.model.DesIntFinanc;


@Controller
public class DespesasController {
	
	@Autowired InternaIndividualDAO internaIndividualDAO;
	
	
	@RequestMapping("salvaDespesas")
	public String salvaDespesas(DesIntFinanc desp) throws ParseException{
		internaIndividualDAO.salvaDespesas(desp);
		return "redirect:internaIndividual?idLista="+desp.getIdListaTransiente();	
	}
	
	
	@RequestMapping("valoresDespesas")
	@ResponseBody
	public String valoresDespesas(String valor, Integer idDesp, String name){

		try {
			internaIndividualDAO.atualizaDespesasValores(valor, idDesp,name);
			return "ok";
		} catch (Exception e) {
			System.out.println(e);
			return "erro";
		}
	}
	
	
	
}
