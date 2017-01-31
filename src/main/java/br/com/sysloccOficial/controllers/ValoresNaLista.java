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
import br.com.sysloccOficial.model.Lista;


@Controller
@Transactional
public class ValoresNaLista {
	
	
	@Autowired private ProducaoDAO producaoDAO;
	@Autowired private Utilitaria util;
	@PersistenceContext	private EntityManager manager;

		
	
	
	public void InsereValoresLista(Grupo grupo,Lista lista){
		BigDecimal impostoValor= new BigDecimal("0");
		
		
		BigDecimal total = 	producaoDAO.somaValoresGrupo(grupo.getIdgrupo());//ok
		lista.setSubTotalCusto(total);//ok	
		
		BigDecimal SomaGrupoValorIncideImposto = 	producaoDAO.somaValoresGrupoValoIncideImposto(grupo.getIdgrupo());

		
		
		BigDecimal SomaGrupoValorNaoIncideImposto = 	producaoDAO.somaValoresGrupoValorNaoIncideImposto(grupo.getIdgrupo());
		
		BigDecimal precoTotal = SomaGrupoValorIncideImposto.add(SomaGrupoValorNaoIncideImposto);
		
		
		BigDecimal testaFee = 	  producaoDAO.somaValoresGrupoImposto(grupo.getIdgrupo());
		
		if(testaFee != null){
			impostoValor = testaFee;
		}else{

		}
		
		// Base para imposto da Lista
		BigDecimal divisor = new BigDecimal("100");
		BigDecimal administracao = lista.getAdministracao().divide(divisor);
		BigDecimal AdministracaoValor = precoTotal.multiply(administracao);
		
		BigDecimal valorTotal = SomaGrupoValorIncideImposto
							    .add(SomaGrupoValorNaoIncideImposto
							    .add(AdministracaoValor)
							    .add(impostoValor));
		
		lista.setSubTotalVendaIncideImposto(SomaGrupoValorIncideImposto);
		lista.setSubTotalVendaNaoIncideImposto(SomaGrupoValorNaoIncideImposto);
		lista.setAdministracaoValor(precoTotal.multiply(administracao));
		lista.setImpostoValor(impostoValor);
		
		
		lista.setValorTotal(valorTotal);
		
		manager.merge(lista);
		
	}
	
	public void calculaValorLista(Integer idLista){
//		BigDecimal grupoValorNaoIncideImposto = calculaValores(grupoValorNaoIncideImpostoSQL, idLista);  Desuso

		
		Lista lista = manager.find(Lista.class, idLista);

		BigDecimal feeComum = lista.getAdministracao().divide(new BigDecimal("100"));
		// Fee Reduzido			
		BigDecimal feeReduzido;

		if(lista.getFeeReduzido() == null){
			feeReduzido = new BigDecimal("0.07");
		}else{
			feeReduzido = lista.getFeeReduzido().divide(new BigDecimal("100"));
		}
		
		BigDecimal zero = new BigDecimal("0");
		
		String grupoValorIncideImpostoSQL = " sum(grupoValorIncideImposto) ";

		String grupoValorNaoIncideImpostoSQL = " sum(grupoValorNaoIncideImposto) ";
		
		String CalculaAdministracao = " sum(grupoValorIncideImposto+grupoValorNaoIncideImposto) ";
		
		String somaGrupoImpostoValorSQL = " sum(grupoImpostoValor) ";
		String subTotalCusto = " sum(grupoCusto) ";
	
		
		BigDecimal grupoValorIncideImposto = calculaValores(grupoValorIncideImpostoSQL, idLista);
		
		
		
		if(grupoValorIncideImposto == null){
			
			lista.setSubTotalCusto(zero);
			lista.setSubTotalVendaNaoIncideImposto(zero);
			lista.setSubTotalVendaIncideImposto(zero);
			lista.setAdministracaoValor(zero);
			lista.setImpostoValor(zero);
			lista.setValorTotal(zero);
			
		}

		if(grupoValorIncideImposto != null){
		
	//	BigDecimal subTotalCustoTotal= calculaValores(subTotalCusto, idLista);
		
		lista.setSubTotalCusto(calculaValores(subTotalCusto, idLista));
		lista.setSubTotalVendaNaoIncideImposto(calculaValores(grupoValorNaoIncideImpostoSQL,idLista));
		lista.setSubTotalVendaIncideImposto(calculaValores(grupoValorIncideImpostoSQL,idLista));

//		BigDecimal AdministracaoCalculada = calculaAdministracao(CalculaAdministracao, idLista, feeComum,feeReduzido);
		
		lista.setAdministracaoValor(calculaAdministracao(CalculaAdministracao, idLista, feeComum,feeReduzido));
		
//		BigDecimal somaGrupoImpostoValorSQLzz = calculaValores(somaGrupoImpostoValorSQL,idLista);

		lista.setImpostoValor(calculaValores(somaGrupoImpostoValorSQL,idLista));
		
		lista.setValorTotal(lista.getSubTotalVendaNaoIncideImposto()
				.add(lista.getSubTotalVendaIncideImposto())
				.add(lista.getAdministracaoValor())
				.add(lista.getImpostoValor()));
		
		manager.merge(lista);
		manager.close();
		}
	}
	
	public BigDecimal calculaValores(String termo,Integer idLista){
		BigDecimal zero = new BigDecimal("0");
		
		String consulta = "select" + termo + "from Grupo where opcional != 1 and idLista=" + idLista;	
	
		
		TypedQuery<BigDecimal>totalGrupo = manager.createQuery(consulta, BigDecimal.class);
		if(totalGrupo.getSingleResult() == null){
			return zero;
		}else{
			return totalGrupo.getSingleResult();
			
		}
		
	}

	public BigDecimal calculaAdministracao(String termo,Integer idLista,BigDecimal feeComum, BigDecimal feeReduzido){ // Para calculo especial de FEE 
			BigDecimal zero = new BigDecimal("0");
			BigDecimal feeCalculado = new BigDecimal("0");
			
			String calculaFee = "select g from Grupo g where opcional != 1 and incideAdministracao != 0 and idLista=" + idLista;

			TypedQuery<Grupo> g = manager.createQuery(calculaFee, Grupo.class);
			List<Grupo> grupos = g.getResultList();
			
			for (int i = 0; i < grupos.size(); i++) {
				
					if(grupos.get(i).isIncideAdministracao() == true && grupos.get(i).isFeeReduzido()== false){
						feeCalculado = calculaValorFeePorTipoDeFee(feeComum, feeCalculado, grupos, i);
					}
				
					if(grupos.get(i).isFeeReduzido() == true){
						feeCalculado = calculaValorFeePorTipoDeFee(feeReduzido, feeCalculado, grupos, i);
					}
			}
			
			if(feeCalculado == zero){
				return zero;
			}else{
				return feeCalculado;
			}
		
	}

	
	private BigDecimal calculaValorFeePorTipoDeFee(BigDecimal administracao, BigDecimal feeCalculado, List<Grupo> grupos, int i) {
		BigDecimal calculo1 = new BigDecimal("0.00");
		BigDecimal calculo2 = new BigDecimal("0.00");
		BigDecimal calculo3 = new BigDecimal("0.00");
		BigDecimal total = new BigDecimal("0.00");
		
		calculo1 = grupos.get(i).getGrupoValorIncideImposto();
		calculo2 = grupos.get(i).getGrupoValorNaoIncideImposto();
		calculo3 = calculo1.add(calculo2);
		total = calculo3.multiply(administracao);
		feeCalculado = feeCalculado.add(total);
		return feeCalculado;
	}

	public BigDecimal calculaGrupoFeeReduzido(String termo,Integer idLista){ // Para calculo especial de FEE 
		BigDecimal zero = new BigDecimal("0");
		
		String consulta = "select" + termo + "from Grupo where opcional != 1 and idLista=" + idLista;	
		TypedQuery<BigDecimal>totalGrupo = manager.createQuery(consulta, BigDecimal.class);
		
		if(totalGrupo.getSingleResult() == null){
			return zero;
		}else{
			return totalGrupo.getSingleResult();
		}
		
	}
	
	public boolean verificaSeTemFeeReduzido(String termo, Integer idLista){
		String consulta = "select" + termo + "from Grupo where opcional != 1 and idLista=" + idLista;	
		Query s = manager.createQuery(consulta);
		boolean feeReduzido = (boolean) s.getSingleResult();
		return feeReduzido;
	}
	
	
	
	
	
	
	
	
	
}
