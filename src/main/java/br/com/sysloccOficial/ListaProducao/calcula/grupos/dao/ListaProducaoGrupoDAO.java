package br.com.sysloccOficial.ListaProducao.calcula.grupos.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.Empresa;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.GrupoCategoriaBayer;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.OrcamentoFornecedor;
import br.com.sysloccOficial.model.ProdutoGrupo;



@Repository
@Transactional
public class ListaProducaoGrupoDAO {

	@PersistenceContext	private EntityManager manager;
	@Autowired private Utilitaria util;
	
	
	public Grupo atualizaGrupo(Grupo grupo) {

		Grupo grupo2 = manager.find(Grupo.class, grupo.getIdgrupo());
				
		Categoria categoria = manager.find(Categoria.class, grupo.getIdCategoriaTransiente());
		
		if(grupo.getIdgrupoCategoriaBayerTransiente() == null){

		}else{
			GrupoCategoriaBayer categoriaBayer = manager.find(GrupoCategoriaBayer.class, grupo.getIdgrupoCategoriaBayerTransiente());
			grupo2.setGrupoCategoriaBayer(categoriaBayer);
		}

		if(grupo.getIdCategoriaGaldermaTransiente() == null){
			
		}else{
			GrupoCategoriaGalderma categoriaGalderma = manager.find(GrupoCategoriaGalderma.class, grupo.getIdCategoriaGaldermaTransiente());
			grupo2.setGrupoCategoriaGalderma(categoriaGalderma);
		}
		
		Lista lista = manager.find(Lista.class, grupo.getIdListaTransiente());
		
		grupo2.setIdLista(lista);
		grupo2.setGrupo(grupo.getGrupo());
		grupo2.setInformacoes(grupo.getInformacoes());
		grupo2.setNecessidades(grupo.getNecessidades());
		
		grupo2.setIncideAdministracao(grupo.isIncideAdministracao());
		
		if(grupo.isFeeReduzido() == true){
			grupo2.setIncideAdministracao(true);
		}
		
		grupo2.setFeeReduzido(grupo.isFeeReduzido());
		grupo2.setOpcional(grupo.isOpcional());
		grupo2.setOpcionalGalderma(grupo.isOpcionalGalderma());
		
		
		
		grupo2.setTxISS(grupo.getTxISS());
		
		
		
		grupo2.setTxISS(grupo.getTxISS());
		
		
		
		grupo2.setTxServico(grupo.getTxServico());
		
		grupo2.setIdCategoria(categoria);
		manager.merge(grupo2);
		manager.close();
		
		return grupo2;
		
	}

	public void salvaNovoOrcamento(String valor,Integer idFornecedor,Integer idGrupo,Integer idProdutoGrupo){
	
			OrcamentoFornecedor orcamento = new OrcamentoFornecedor();
			setaOrcamento(orcamento, valor, idGrupo, idFornecedor,idProdutoGrupo);
			manager.persist(orcamento);
			manager.close();
		
	}
	
	public void setaOrcamento(OrcamentoFornecedor orcamento,String valor,Integer idGrupo,Integer idFornecedor,Integer idProdutoGrupo){
		try {
			TypedQuery<Empresa> e = manager.createQuery("select e from Empresa e where idEmpresa="+idFornecedor, Empresa.class); 
			Empresa empresa = e.getSingleResult();
			
			ProdutoGrupo produto = manager.getReference(ProdutoGrupo.class, idProdutoGrupo);
			Grupo grupo= manager.getReference(Grupo.class, idGrupo);
			
			orcamento.setValorOrcamento(new BigDecimal(util.formataValoresAprimorado(valor)));
			orcamento.setEmpresa(empresa);
			orcamento.setGrupo(grupo);
			orcamento.setProduto(produto);
		} catch (Exception e) {
			
		}
	}

	public void apagaNovoOrcamento(Integer idOrcamento) {
		OrcamentoFornecedor orcamento = manager.find(OrcamentoFornecedor.class, idOrcamento);
		manager.remove(orcamento);
	}
	
}
