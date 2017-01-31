package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.controllerExcel.AuxExcelSQL;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.Grupo;

@Service
public class AuxCarregaGrupos {
	
	@Autowired private static AuxExcelSQL sql;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@PersistenceContext	private EntityManager manager;
	
	
	public static List<Grupo> listaGruposNAOOpcionais(Integer idLista){
		List<Grupo> listaGrupos = sql.retornaGruposGalderma(idLista);
		return listaGrupos;
	}

	public static List<Grupo> listaGruposOpcionais(Integer idLista){
		List<Grupo> listaGrupos = sql.retornaGruposOpcionaisGalderma(idLista);
		return listaGrupos;
	}
	
	
	

}
