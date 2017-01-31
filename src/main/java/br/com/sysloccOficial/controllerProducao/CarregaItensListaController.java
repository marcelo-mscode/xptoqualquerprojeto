package br.com.sysloccOficial.controllerProducao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.GrupoDAO;
import br.com.sysloccOficial.daos.ListaDAO;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.Grupo;


@Controller
@Transactional
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarregaItensListaController {
	
	
	@Autowired private ListaDAO listaDAO;
	@Autowired private EmpresaDAO empresaDAO;
	@Autowired private GrupoDAO grupoDAO;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	
	
	@RequestMapping("/itensProducao")
	public ModelAndView itensProducao(Integer idLIsta) {
		
		ModelAndView MV = new ModelAndView("menuProducao/itensLista");
		MV.addObject("lista", listaDAO.editaLista(idLIsta));
		MV.addObject("categoria", listaDAO.listadeCategorias(idLIsta));
		MV.addObject("grupos", listaDAO.listaDeGrupos(idLIsta));
		MV.addObject("fornecedoresLista", empresaDAO.listaFornecedores());
	
		List<Grupo> listaDeGrupos = grupoDAO.listaDeGrupos(idLIsta);
		List<Integer> guardaListasidEmpresas = new ArrayList<Integer>();
		pegaIdFornecedor(listaDeGrupos, guardaListasidEmpresas);
		return MV;
	}


	private void pegaIdFornecedor(List<Grupo> listaDeGrupos, List<Integer> idEmpresas) {
		int guardaIdFornecedor;
		for(int i = 0; i< listaDeGrupos.size();i++){ 
			for(int j = 0; j < listaDeGrupos.get(i).getProdutoGrupo().size(); j++){ 
				try {//Pode existir grupo sem item cadastrados
					guardaIdFornecedor = listaDeGrupos.get(i).getProdutoGrupo().get(j).getEmpresa().getIdEmpresa();
				} catch (Exception e) {
					guardaIdFornecedor = 1234567890;
				}
				if(idEmpresas.contains(guardaIdFornecedor)){ }
				else{
					idEmpresas.add(guardaIdFornecedor);
				}
		   }
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
