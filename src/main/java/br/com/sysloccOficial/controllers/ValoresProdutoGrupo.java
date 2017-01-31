package br.com.sysloccOficial.controllers;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.daos.ProducaoDAO;
import br.com.sysloccOficial.model.ProdutoGrupo;

@Controller
@Transactional
public class ValoresProdutoGrupo {

	
	@Autowired private ProducaoDAO producaoDAO;
	@Autowired private Utilitaria util;
	
	@PersistenceContext	private EntityManager manager;
	
	BigDecimal zero = new BigDecimal(0);
	
	public void custoComBvFornecedorIncluso(ProdutoGrupo produtoGrupo){
		
		if(produtoGrupo.getBvFornecedorTransiente() == null || produtoGrupo.getBvTransiente() == "" ||produtoGrupo.getBvTransiente() =="0,00"){
			produtoGrupo.setBvFornecedor(zero);
		}
		else {
				
				BigDecimal bvFornecedor = new BigDecimal(util.formataValores(produtoGrupo.getBvFornecedorTransiente()));
				produtoGrupo.setBvFornecedor(bvFornecedor);
				produtoGrupo.setCustoComBvFornecedor(true);
				
				if(produtoGrupo.isBvFornecedorEmPorcentagem() == true){
					BigDecimal bvFornecedorCalculado = bvFornecedor.multiply(new BigDecimal("0.2")); // porque multiplica por 20% ??????????
					bvFornecedorCalculado = bvFornecedor.add(bvFornecedorCalculado); 
					produtoGrupo.setBvFornecedorValor(bvFornecedorCalculado);
				}else{
					produtoGrupo.setBvFornecedorValor(bvFornecedor);
				}
			}
	}
	
	public void BvVenda(ProdutoGrupo produtoGrupo,BigDecimal custoProduto,Integer idGrupo){
			
		if(produtoGrupo.getBvTransiente() == null || produtoGrupo.getBvTransiente() == ""){
			
			produtoGrupo.setBv(zero);
			produtoGrupo.setPrecoProduto(custoProduto.add(new BigDecimal("0")));
			
		}
		
		else{
			
			//Salva o valor do Bv
			BigDecimal bv = new BigDecimal(util.formataValores(produtoGrupo.getBvTransiente()));
			produtoGrupo.setBv(bv);
			
			BigDecimal bvFornecedorValor = new BigDecimal(util.formataValores(produtoGrupo.getBvTransiente()));
			BigDecimal custoMaisBv = custoProduto.add(bvFornecedorValor); //Custo do produto + valor de BV
			
				if(produtoGrupo.isImposto() == true){
				
					produtoGrupo.setPrecoProduto(custoMaisBv);

				}else{
					
					produtoGrupo.setPrecoProduto(custoProduto.add(bv));
				}
		}
	}
	
	
}	
	
	
	
	
	
	
	
	
	
	
