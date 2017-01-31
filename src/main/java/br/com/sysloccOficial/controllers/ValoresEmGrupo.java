package br.com.sysloccOficial.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.ProducaoDAO;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.ProdutoGrupo;


@Controller
@Transactional
public class ValoresEmGrupo {

	
	
		@Autowired private ProducaoDAO producaoDAO;
		@Autowired private ValoresNaLista valoresNaLista;
		@Autowired private Utilitaria util;
		@PersistenceContext	private EntityManager manager;

		BigDecimal zero = new BigDecimal(0);
		
	public void SomaTodosOsGrupoComValorIncideImposto(Integer idLista){
		String consulta = "from Grupo where idLista="+idLista;
		Query query = manager.createQuery(consulta);
		List<Grupo> grupo = query.getResultList();
		
		for (int i = 0;i < grupo.size(); i ++) {
			BigDecimal somaDeTodosOsGruposComValorIncideImposto = somaItensComImpostoPorIdGrupo(grupo.get(i).getIdgrupo());
		}
		
	}
		
	public BigDecimal somaItensComImpostoPorIdGrupo(Integer idGrupo){
		Grupo grupo = manager.find(Grupo.class, idGrupo);
		String grupoValorIncideImpostoSQL = " sum(grupoValorIncideImposto) ";
		BigDecimal somaDosValoresItensDeUmGrupo = calculaValoresItensPorIdGrupo(grupoValorIncideImpostoSQL,idGrupo);

		if(somaDosValoresItensDeUmGrupo.equals("0")){
			return somaDosValoresItensDeUmGrupo;
		}else{
			BigDecimal imposto = grupo.getIdCategoria().getImposto();
			String impostoz = imposto.toString();
			String ValorBrutoFormula =       util.formulaParaCalculoImposto(impostoz, somaDosValoresItensDeUmGrupo);
			BigDecimal ValorLiquidoFormula = util.formulaParaCalculoImposto2(ValorBrutoFormula, somaDosValoresItensDeUmGrupo);	
			return ValorLiquidoFormula;
		}
	}
		
	public BigDecimal somaItensNaoImpostoPorIdGrupo(Integer idGrupo){
		String grupoValorIncideImpostoSQL = " sum(grupoValorNaoIncideImposto) ";
		BigDecimal somaDosValoresItensDeUmGrupo = calculaValoresItensPorIdGrupo(grupoValorIncideImpostoSQL,idGrupo);
		return somaDosValoresItensDeUmGrupo;
	}	
		
		
	public BigDecimal calculaValoresItensPorIdGrupo(String termo, Integer idGrupo){
		String consulta = "select" + termo + "from Grupo where idGrupo=" + idGrupo + " and opcional = 0";	
		TypedQuery<BigDecimal>totalGrupo = manager.createQuery(consulta, BigDecimal.class);
		
		if(totalGrupo.getSingleResult() == null){
			BigDecimal zero = new BigDecimal("0");
			return zero;
		}else{
			return totalGrupo.getSingleResult();
		}
		
	 }
		
	public void loopGrupoAtualizaImpostos(Integer idLista){
	   Query query = manager.createQuery("from Grupo where idlista="+idLista);
	   List<Grupo> grupo2 = query.getResultList();
		for (Grupo grupoImposto : grupo2) {
			atualizaImpostoGrupo(grupoImposto);
	   }
	}


//Recalcula um grupo individual
public void atualizaImpostoGrupo(Grupo grupo){
	
	   grupo.getIdLista().getAdministracao();
	   BigDecimal zero = new BigDecimal("0");
	   BigDecimal apenasValorDoImpostoSobreItens = new BigDecimal("0");
	   BigDecimal fee = new BigDecimal("0");
	   
	   BigDecimal administracao = grupo.getIdLista().getAdministracao().divide(new BigDecimal("100")); // Base para imposto da Lista
	   
	   // Fee Reduzido			
		BigDecimal feeReduzido;

		if(grupo.getIdLista().getFeeReduzido() == null){
			feeReduzido = new BigDecimal("0.07");
		}else{
			feeReduzido = grupo.getIdLista().getFeeReduzido().divide(new BigDecimal("100"));
		}
		
		
	   String impostoDaLinha = grupo.getIdCategoria().getImposto().toString();
		
	   grupo.setGrupoCusto(zero);
	   grupo.setGrupoValorIncideImposto(zero);
	   grupo.setGrupoAdministracaoValor(zero); 
	   grupo.setGrupoImpostoValor(zero);
		
	   manager.merge(grupo);

/* ------------------------------------------------------------------------------------------------------------------------------------	*/
		
		List<ProdutoGrupo> itensProdutoGrupoQueincideImposto = producaoDAO.somaIncideImposto(grupo);
		
		
		
		/*grupo.setGrupoValorIncideImposto(zero);*/
		grupo.setGrupoValorNaoIncideImposto(zero);
		
		//Salva no campo Incide imposto somente os itens que tem imposto = true
		grupo.setGrupoValorIncideImposto(calculaPrecoProdutoGrupoPelaQuantidade(itensProdutoGrupoQueincideImposto));

		BigDecimal precoTotalItensDeUmGrupo = calculaPrecoProdutoGrupoPelaQuantidade(itensProdutoGrupoQueincideImposto);
		
		String precoTotalItensMaisImpostoPorFora = util.formulaParaCalculoImposto(impostoDaLinha,precoTotalItensDeUmGrupo); 
	
		apenasValorDoImpostoSobreItens = util.formulaParaCalculoImposto2(precoTotalItensMaisImpostoPorFora, precoTotalItensDeUmGrupo);
	
		
	
/* - ------------------------------------------------------------------------	----------------------	-----------------------------	*/
		List<ProdutoGrupo> NaoincideImposto = producaoDAO.somaNaoIncideImposto(grupo);
		//Salva no campo Nao Incide Imposto somente os itens que tem imposto = false
		grupo.setGrupoValorNaoIncideImposto(calculaPrecoProdutoGrupoPelaQuantidade(NaoincideImposto));
		
		//Calcula valor Total Fee
   	    List<ProdutoGrupo> ProdutoGrupo2 = producaoDAO.soma(grupo); // Pega todos os produtos associados daquele Grupo
		grupo.setGrupoCusto(somaValoresPrecoProduto(ProdutoGrupo2)); 

		
		
		fee = calculaValorFee(grupo, zero, administracao, feeReduzido, ProdutoGrupo2, fee);
		
		
		String FeeDivididoPelaFormula = util.formulaParaCalculoImposto(impostoDaLinha,fee);
		BigDecimal FeeDivididoPelaFormulaMenosValorFee = util.formulaParaCalculoImposto2(FeeDivididoPelaFormula, fee);

		 // Subtrair a diferença
		grupo.setGrupoImpostoValor(apenasValorDoImpostoSobreItens.add(FeeDivididoPelaFormulaMenosValorFee));
		
		manager.merge(grupo);

}

	private BigDecimal calculaValorFee(Grupo grupo, BigDecimal zero, BigDecimal administracao, BigDecimal feeReduzido, List<ProdutoGrupo> ProdutoGrupo2, BigDecimal fee) {

		if(grupo.isIncideAdministracao() == false){ // Se não tiver Fee insere o valor zero.
			grupo.setGrupoAdministracaoValor(zero);
			fee = grupo.getGrupoAdministracaoValor();
		}
		
		if(grupo.isIncideAdministracao() == true && grupo.isFeeReduzido() == false){
			grupo.setGrupoAdministracaoValor(calculaPrecoProdutoGrupoPelaQuantidade(ProdutoGrupo2).multiply(administracao));
			fee = grupo.getGrupoAdministracaoValor();
		}
	
		if(grupo.isFeeReduzido() == false){ // Se não tiver FeeReduzido insere o valor zero.
			
			grupo.setGrupoFeeReduzido(zero);
		}
		
		if(grupo.isFeeReduzido() == true){ // Se tiver FeeReduzido Calcula Valor.
			grupo.setGrupoAdministracaoValor(calculaPrecoProdutoGrupoPelaQuantidade(ProdutoGrupo2).multiply(feeReduzido));
			fee = grupo.getGrupoAdministracaoValor();
		}
		return fee;
	}

	// ------ Insere valores na Lista ------------- //

	public BigDecimal calculaPrecoProdutoGrupoPelaQuantidade(List<ProdutoGrupo> multiplica){ // Multiplica qtd por PrecoProduto
		
		BigDecimal soma2 = new BigDecimal("0");
				
		double mult, mult1, diaria, totals;

		for (ProdutoGrupo produtoGrupo2 : multiplica) {
			
			mult = produtoGrupo2.getQuantidade();
			mult1 = produtoGrupo2.getQuantidade2();
			diaria = produtoGrupo2.getDiarias();
			totals= mult*mult1*diaria;
	
			BigDecimal preco = produtoGrupo2.getPrecoProduto().multiply(BigDecimal.valueOf(totals));
			soma2 = soma2.add(preco);
		}
		return soma2;
	}
	
	public BigDecimal somaValoresPrecoProduto(List<ProdutoGrupo> multiplica){
		BigDecimal somaPrecoProduto = new BigDecimal("0");
		double mult, mult1, diaria, totals;
		
		for (ProdutoGrupo produtoGrupo2 : multiplica) {
			mult = produtoGrupo2.getQuantidade();
			mult1 = produtoGrupo2.getQuantidade2();
			diaria = produtoGrupo2.getDiarias();
			totals= mult*mult1*diaria;
			
			BigDecimal preco = produtoGrupo2.getCustoProduto().multiply(BigDecimal.valueOf(totals));
			
			somaPrecoProduto = somaPrecoProduto.add(preco);
		}
		return somaPrecoProduto;
	}

}
