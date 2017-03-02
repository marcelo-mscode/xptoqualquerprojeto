package br.com.sysloccOficial.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.ListaProducao.DeterminaQuantidade.dao.DeterminaQuantidadesDAO;
import br.com.sysloccOficial.ListaProducao.Excel.Galderma.DAO.ExcelGaldermaDAO;
import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.CenariosGalderma;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.Lista;
import br.com.sysloccOficial.model.ListaEstatus;
import br.com.sysloccOficial.model.OrcamentoFornecedor;
import br.com.sysloccOficial.model.ProdutoGrupo;
import br.com.sysloccOficial.model.producao.ProducaoP;

@Repository
public class ProducaoDAO {
	
	@PersistenceContext private EntityManager manager;
	@Autowired private DeterminaQuantidadesDAO deterQuantDAO;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@Autowired private GrupoDAO grupoDAO;
	@Autowired private CategoriaDAO categoriaDAO;
	@Autowired private Utilitaria util;
	
	
	
	@Autowired ExcelGaldermaDAO galdermaDAO;
	
	
	public List<Grupo> listaGrupoPorIdCategoria(Integer idCategoria){
		String consulta = "from Grupo where idCategoria="+idCategoria+ " order by ordemGrupo";
		Query query = manager.createQuery(consulta);
		
		return query.getResultList();
	}
	public List<Categoria> categoriaPorIdLista(Integer idLista){
		String consulta = "select c From Categoria c where idLista="+idLista+" order by categoriaOrdem";
		Query query = manager.createQuery(consulta);
		
		return query.getResultList();
	}
	
	public Categoria ordemCategoria(Integer idLista){
		String consulta = "from Categoria where idlista ="+idLista+"order by idcategoria desc";
		
		try {
			Query query = manager.createQuery(consulta).setMaxResults(1);
			Categoria ordem = (Categoria) query.getSingleResult();		
			return ordem;

		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Não tem resultado");
			return null;
		}
	}


	public BigDecimal somaValoresGrupo(Integer idGrupo){

		Grupo grupo = manager.find(Grupo.class, idGrupo);
		Lista lista = manager.find(Lista.class, grupo.getIdLista().getIdLista());
		String consulta ="select sum(grupoCusto) from Grupo where idLista="+lista.getIdLista();
		TypedQuery<BigDecimal>totalGrupo = manager.createQuery(consulta, BigDecimal.class);
		return totalGrupo.getSingleResult();
	}
	
	public BigDecimal somaValoresGrupoValoIncideImposto(Integer idGrupo){

		Grupo grupo = manager.find(Grupo.class, idGrupo);
		Lista lista = manager.find(Lista.class, grupo.getIdLista().getIdLista());
		String consulta ="select sum(grupoValorIncideImposto) from Grupo where idLista="+lista.getIdLista();
		TypedQuery<BigDecimal>totalGrupo = manager.createQuery(consulta, BigDecimal.class);
		return totalGrupo.getSingleResult();
	}

	public BigDecimal somaValoresGrupoValorNaoIncideImposto(Integer idGrupo){
		BigDecimal zero=new BigDecimal("0");
		Grupo grupo = manager.find(Grupo.class, idGrupo);
		Lista lista = manager.find(Lista.class, grupo.getIdLista().getIdLista());
		
		
			String consulta ="select sum(grupoValorNaoIncideImposto) from Grupo where idLista="+lista.getIdLista();
			TypedQuery<BigDecimal>totalGrupo = manager.createQuery(consulta, BigDecimal.class);
			
			if(totalGrupo.getSingleResult() == null){
				return zero;
			}else{
				return totalGrupo.getSingleResult();	
			}
	}
	
	public BigDecimal somaValoresGrupoImposto(Integer idGrupo){

		Grupo grupo = manager.find(Grupo.class, idGrupo);
		Lista lista = manager.find(Lista.class, grupo.getIdLista().getIdLista());
		
		String consulta ="select sum(grupoImpostoValor) from Grupo where idLista="+lista.getIdLista()+" and incideAdministracao = 1";
		TypedQuery<BigDecimal>totalGrupo = manager.createQuery(consulta, BigDecimal.class);
		return totalGrupo.getSingleResult();
	
	}
	
	public BigDecimal somaValoresProdutoGrupoPorGrupo(Grupo idGrupo){

		String consulta2 ="SELECT p FROM ProdutoGrupo p where idgrupo="+idGrupo.getIdgrupo();
		TypedQuery<BigDecimal>totalGrupo = manager.createQuery(consulta2, BigDecimal.class);
		return totalGrupo.getSingleResult();
	}
	
	public List<ProdutoGrupo>soma (Grupo idGrupo){
		String consulta = "select p from ProdutoGrupo p where idGrupo="+idGrupo.getIdgrupo();
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}
	
	public List<ProdutoGrupo>somaIncideImposto (Grupo idGrupo){
		String consulta = "select p from ProdutoGrupo p where idGrupo="+idGrupo.getIdgrupo()+" and imposto = 1";
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}
	
	public List<ProdutoGrupo>somaNaoIncideImposto (Grupo idGrupo){
		String consulta = "select p from ProdutoGrupo p where idGrupo="+idGrupo.getIdgrupo()+" and imposto = 0";
		Query query = manager.createQuery(consulta);
		return query.getResultList();
	}
	
	
	
	
	public void excluiVariosProdutosGrupo(Integer idGrupo){
		
		String consulta = "FROM ProdutoGrupo WHERE idGrupo= "+idGrupo;
		Query listaProduto = manager.createQuery(consulta, ProdutoGrupo.class);
		List<ProdutoGrupo> listaLista = listaProduto.getResultList();
		
		for(int i =0; i < listaLista.size();i++){
			ProdutoGrupo produtoGrupo = listaLista.get(i);
			manager.remove(produtoGrupo);
		}
		
	}

	public void clonaCategoria(Integer _idLista, Lista _listaNova){
		Categoria umaCategoria = new Categoria();
		List<Grupo> grupos = new ArrayList<Grupo>();
		Grupo umGrupo = new Grupo();
		List<Categoria> variasCategorias = categoriaDAO.categoria(_idLista);

		
		for(int i =0; i < variasCategorias.size(); i++){
		   umaCategoria = categoriaDAO.clonaUMACategoria(_listaNova, variasCategorias.get(i), i);
		  
			 grupos = umaCategoria.getGrupo();

			 for(int j =0; j < grupos.size(); j++){
			   umGrupo = clonaUMGrupo(_listaNova, umaCategoria, grupos, j);
			   
			   List<Integer> idsProdGrupo = new ArrayList<Integer>();

			   Set<ProdutoGrupo> listaDeProdutoGrupo = new HashSet<ProdutoGrupo>();
  				for(int p = 0; p < umGrupo.getProdutoGrupo().size();p++){
  					listaDeProdutoGrupo.add(umGrupo.getProdutoGrupo().get(p));
  					idsProdGrupo.add(umGrupo.getProdutoGrupo().get(p).getIdProdutoGrupo());
  				}
  				
  				
				Iterator<ProdutoGrupo> listaDeProdutoGrupoAsIterator = listaDeProdutoGrupo.iterator();
				while (listaDeProdutoGrupoAsIterator.hasNext()) {
					clonaUMProdutoGrupo(listaDeProdutoGrupoAsIterator.next(), umGrupo);
					
				}
			}

		 }
	}
	
	
	private void clonaUMOrcamento(OrcamentoFornecedor orcamento, Grupo umGrupo) {
		OrcamentoFornecedor orcam = produtoGrupoDAO.persisteUMOrcamento(orcamento, umGrupo);
	}

	private void clonaUMProdutoGrupo(ProdutoGrupo prodGrupo, Grupo umGrupo) {
		
		ProdutoGrupo umProdutoGrupo = produtoGrupoDAO.persisteUMProdutoGrupoClonado(prodGrupo, umGrupo);
		
		if(umGrupo.getDeterm() != null) {
			if(umGrupo.getDeterm().getProdutoGrupo().getIdProdutoGrupo() == prodGrupo.getIdProdutoGrupo())
				deterQuantDAO.clonaDeterminaQuantidades(umGrupo, umProdutoGrupo);
		}  
	}

	private Grupo clonaUMGrupo(Lista _listaNova, Categoria umaCategoria, List<Grupo> grupos, int j) {
		Grupo umGrupo = grupoDAO.persistiUMGrupoClonado(_listaNova, umaCategoria, grupos, j);

		if(umGrupo.getDeterm() == null){
		   if(umGrupo.getDetermPadrao() != null)
			deterQuantDAO.clonaDeterminaQuantPadrao(umGrupo);
		 }
		 return umGrupo;
	}
    
    public void mudaEstatusLista(Integer idLista){
    	Lista lista = manager.find(Lista.class, idLista);
    	ListaEstatus listaEstatusRevisada = manager.find(ListaEstatus.class, 4);
    	
    	lista.setIdlistaEstatus(listaEstatusRevisada);
    	manager.merge(lista);
    }
    
    public BigDecimal somaFatLocco(Integer idCategoria){
    	BigDecimal zero = new BigDecimal("0");
	    
	    	String consulta = "select sum(grupoValorIncideImposto) from Grupo where idCategoria="+idCategoria+" and opcional = 0";
	    	Query query = manager.createQuery(consulta);
	    if(query.getSingleResult() == " " || query.getSingleResult() == null){
	    	return zero;
	    }else{
	    	return (BigDecimal) query.getSingleResult();
	    }
    }
    
    public BigDecimal somaFatDireto(Integer idCategoria){
    	BigDecimal zero = new BigDecimal("0");
    	String consulta = "select sum(grupoValorNaoIncideImposto) from Grupo where idCategoria="+idCategoria+" and opcional = 0";
    	
    	Query query = manager.createQuery(consulta);
    	if(query.getSingleResult() == " " || query.getSingleResult() == null){
    		return zero;
    	}else{
    		return (BigDecimal) query.getSingleResult();
    	}	
    }
    
    public BigDecimal somaOpcional(Integer idCategoria){
    	BigDecimal zero = new BigDecimal("0");
    	String consulta = "select sum(grupoValorIncideImposto+grupoValornaoIncideImposto) from Grupo where idCategoria="+idCategoria+" and opcional = 1";
    	
    	Query query = manager.createQuery(consulta);
    	if(query.getSingleResult() == " " || query.getSingleResult() == null){
    		return zero;
    	}else{
    		return (BigDecimal) query.getSingleResult();
    	}	
    }

    public BigDecimal subTotalFatLocco(Integer idLista){
    	String consulta = "select sum(grupoValorIncideImposto) from Grupo where idLista="+idLista+" and opcional = 0";
    	Query query = manager.createQuery(consulta);
    	return (BigDecimal) query.getSingleResult();
    }
    
    public BigDecimal subTotalFatDireto(Integer idLista){
    	String consulta = "select sum(grupoValorNaoIncideImposto) from Grupo where idLista="+idLista+" and opcional = 0";
    	Query query = manager.createQuery(consulta);
    	return (BigDecimal) query.getSingleResult();
    }

    public Grupo pegaIdcategoriaPorIdGrupo(Integer idGrupo){
    	String consulta = "select g from Grupo g where idGrupo="+idGrupo;
    	
    	Query query = manager.createQuery(consulta);
    	return (Grupo) query.getSingleResult();
    }
    
    public ProducaoP buscaProducaoP(Integer idFornecedor, Integer idLista, Integer idProdutoGrupo){
		String trasProducao = "from ProducaoP p where idlista = "+idLista+" and idEmpFornecedor= "+idFornecedor+" and idprodutogrupo = "+idProdutoGrupo;
		TypedQuery<ProducaoP> producaoP  = manager.createQuery(trasProducao, ProducaoP.class);
		return producaoP.getSingleResult();
	}
	
    public List<ProducaoP> pegaListaItensDoMesmoFornecedor(Integer idFornecedor, Integer idLista) {
		Query query = manager.createQuery("from ProducaoP p where idlista = "+idLista+" and idEmpFornecedor= "+idFornecedor);
		List<ProducaoP> listaItensMesmoFornecedor = query.getResultList();
		return listaItensMesmoFornecedor;
	}
    
    public List<Integer> pegaDiasPagamentoDeUmFornecedor(String idsFornecedorFinanceiro) {
		String tes = "SELECT distinct(diasPrazoParaPagamento) FROM ValorPagtoFornecedor"
				   + " where idFornecedorFinanceiro in ("+ idsFornecedorFinanceiro +") order by diasPrazoParaPagamento";
		Query q = manager.createQuery(tes);
		List<Integer> diasPrazo = q.getResultList();
		return diasPrazo;
	}
    
    
    public Integer verificaSePlanilhaMae(Integer idLista){
    	
    	Integer idPlanilhaMae = 0;
    	Integer idPlanilhaFilha = 0;
    	Integer numeroCenario = 0;
    	Long num;
    	
    	
    	try { // planilha filha ?
    		
	    	CenariosGalderma  cenarioFilha = galdermaDAO.verificaPlanilhaFilha(idLista);
	    	idPlanilhaMae = cenarioFilha.getPlanilhaMae();
	    	idPlanilhaFilha = espelhamentoCenarioGalderna(idLista);
	    	num = galdermaDAO.qtdPlanilhasFilhas(idPlanilhaMae);
	    	numeroCenario = Integer.valueOf(num.toString())+2;
    		galdermaDAO.salvaidsNovoCenario(idPlanilhaMae, idPlanilhaFilha, numeroCenario);
    		
    		Lista listaFilha = manager.find(Lista.class, idPlanilhaFilha);
			listaFilha.setNumCenarioGalderma(numeroCenario);
			manager.merge(listaFilha);
    		
    		return idPlanilhaFilha;
    		
		} catch (Exception e) {//Não é filha
			
			try { // É planilha Mãe ? 
				
			CenariosGalderma  cenariosMae = galdermaDAO.verificaPlanilhaMae(idLista);
	    	idPlanilhaMae = cenariosMae.getPlanilhaMae();
	    	idPlanilhaFilha = espelhamentoCenarioGalderna(idPlanilhaMae);
	    	num = galdermaDAO.qtdPlanilhasFilhas(idPlanilhaMae);
	    	numeroCenario = Integer.valueOf(num.toString())+2;
			galdermaDAO.salvaidsNovoCenario(idPlanilhaMae, idPlanilhaFilha, numeroCenario);	
			
			// seta numero do cenário 1 para planilha mãe
			Lista listaMae = manager.find(Lista.class, idLista);
			listaMae.setNumCenarioGalderma(1);
			manager.merge(listaMae);
			// seta numero do cenário da planilha filha
			Lista listaFilha = manager.find(Lista.class, idPlanilhaFilha);
			listaFilha.setNumCenarioGalderma(numeroCenario);
			manager.merge(listaFilha);
			return idPlanilhaFilha;
			
		} catch (Exception e2) {//Não é mãe
				
			idPlanilhaMae = idLista;
			idPlanilhaFilha = espelhamentoCenarioGalderna(idLista);
			numeroCenario = 2;
			galdermaDAO.salvaidsNovoCenario(idPlanilhaMae, idPlanilhaFilha, numeroCenario);
			
			// seta numero do cenário 1 para planilha mãe
			Lista listaMae = manager.find(Lista.class, idLista);
			listaMae.setNumCenarioGalderma(1);
			manager.merge(listaMae);
			// seta numero do cenário da planilha filha
			Lista listaFilha = manager.find(Lista.class, idPlanilhaFilha);
			listaFilha.setNumCenarioGalderma(numeroCenario);
			manager.merge(listaFilha);
			
			return idPlanilhaFilha;
			}
		}
    }
    
    
    public Integer espelhamentoCenarioGalderna(Integer idLista){
		Lista lista = manager.find(Lista.class, idLista);
		//ListaEstatus listaEstatus = manager.find(ListaEstatus.class, 1);
		
		ModelAndView MV = new ModelAndView("producao/listaDuplicada");
		
		// Código Modificado para o modelo de
		// Quando for duplicar a lista ele assumirá o codigo do job
		// mais regra para LP normalmente.
		String codLista = util.montaCodigoDuplicaLista(lista.getIdJob().getIdJob(), lista.getIdJob().getCodJob());
		
		Lista listaDuplicada = new Lista();
		
		listaDuplicada = lista;
		listaDuplicada.setRevisao(0);
		listaDuplicada.setIdLista(null);
		listaDuplicada.setIdlistaEstatus(manager.find(ListaEstatus.class, 1));
	    listaDuplicada.setListaCod(codLista);

		manager.detach(listaDuplicada);
		manager.persist(listaDuplicada);
		Integer  idNovaLista = listaDuplicada.getIdLista();
		clonaCategoria(idLista,listaDuplicada);

		manager.clear();
		Lista novalista = manager.find(Lista.class, idNovaLista);
		
		MV.addObject("idNovaLista", novalista.getIdLista());
        return novalista.getIdLista();
	}
    
    public void editaInfoGalderma(Integer idLista, String texto){
    	try {
    		
    		Lista lista = manager.find(Lista.class, idLista);
    		lista.setInfoConsolidadoGalderma(texto);
    		manager.merge(lista);

    		System.out.println("Depois: "+lista.getInfoConsolidadoGalderma());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Deu erro em Editar a Lista"+e);
		}
    }
    
    
    
}




















