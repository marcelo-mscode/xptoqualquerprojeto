package br.com.sysloccOficial.ListaProducao.calcula.grupos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.ListaProducao.calcula.grupos.dao.ListaProducaoGrupoDAO;
import br.com.sysloccOficial.controllers.ValoresEmGrupo;
import br.com.sysloccOficial.controllers.ValoresNaLista;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.Lista;



@Controller
public class AtualizaGruposController {
	
	@Autowired private ValoresNaLista valoresNaLista;
	@Autowired private ValoresEmGrupo valoresEmGrupo;
	@Autowired private ListaProducaoGrupoDAO listaProducaoGrupoDAO;
	
	
	@PersistenceContext	private EntityManager manager;
	
	
	@RequestMapping("/atualizaDescricaoEmCategoria")
	public String atualizaDescricaoCategoria(Grupo grupo){
		
		Grupo grupo2 = listaProducaoGrupoDAO.atualizaGrupo(grupo);
		Lista lista = manager.find(Lista.class, grupo.getIdListaTransiente());
		
		valoresEmGrupo.atualizaImpostoGrupo(grupo2);// Setando valores no Grupo dos itens
		
		valoresNaLista.calculaValorLista(lista.getIdLista());// Setando os valores na Lista 
		
		return "redirect:editaLinha?idGrupo="+grupo.getIdgrupo();
	}


	@RequestMapping("salvaOrcamentoFornecedor")
	@ResponseBody
	private String salvaOrcamentoFornecedor(String valor, Integer idFornecedor,Integer idGrupo, Integer idProdutoGrupo){
		listaProducaoGrupoDAO.salvaNovoOrcamento(valor, idFornecedor,idGrupo,idProdutoGrupo);
		return "ok";
	}
	
	@RequestMapping("apagaOrcamentoFornecedor")
	private String apagaOrcamento(Integer idOrcamento, Integer idGrupo){
		
		listaProducaoGrupoDAO.apagaNovoOrcamento(idOrcamento);
		
		return "redirect:editaLinha?idGrupo="+idGrupo;
	}
	
	
	
	

}
