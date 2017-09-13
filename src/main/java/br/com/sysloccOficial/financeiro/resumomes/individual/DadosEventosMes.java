package br.com.sysloccOficial.financeiro.resumomes.individual;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import br.com.sysloccOficial.financeiro.model.FinancImpostos;
import br.com.sysloccOficial.model.RelatorioEventos;

@Component
public class DadosEventosMes {
	
	
	public BigDecimal somaTotalEventos(List<RelatorioEventos> infoEvento) {
		BigDecimal pgtoExt = new BigDecimal("0");
		for (int i = 0; i < infoEvento.size(); i++) {
			pgtoExt = pgtoExt.add(infoEvento.get(i).getServicos());
		}
		return pgtoExt;
	}
	
	public BigDecimal custoTerceiros (List<RelatorioEventos> infoEvento){
		BigDecimal custoTerceiros = new BigDecimal("0");
		for (int i = 0; i < infoEvento.size(); i++) {
			custoTerceiros = custoTerceiros.add(infoEvento.get(i).getTotalAPagarFornecedores());
		}
		return custoTerceiros;
	}

	public BigDecimal pgtoExternas(List<RelatorioEventos> infoEvento) {
		BigDecimal pgtoExt = new BigDecimal("0");
		for (int i = 0; i < infoEvento.size(); i++) {
			pgtoExt = pgtoExt.add(infoEvento.get(i).getPgtoExternas());
		}
		return pgtoExt;
	}

	public BigDecimal faturamentoMes(BigDecimal faturamentoEventos, BigDecimal pgtoExternas) {
		BigDecimal faturamentoMes = new BigDecimal("0");
		
		faturamentoMes = faturamentoEventos.subtract(pgtoExternas); 

		return faturamentoMes;
	}

	public BigDecimal impostos(BigDecimal faturamentoEventos) {
		
		//somar todos os impostosSobreValorLoccoAgencia
		
		
		BigDecimal faturamentoMes = new BigDecimal("0");
		faturamentoMes = faturamentoEventos.multiply(new BigDecimal("0.1595")); 
		return faturamentoMes;
	}

	public BigDecimal impostosSobreValorLoccoAgencia(List<RelatorioEventos> infoEvento) {
		try {
			BigDecimal pgtoExt = new BigDecimal("0");
			for (int i = 0; i < infoEvento.size(); i++) {
				pgtoExt = pgtoExt.add(infoEvento.get(i).getImpostoSobreValorLoccoAgencia());
			}
			return pgtoExt;
		} catch (Exception e) {
			return new BigDecimal("0.00");
		}
	}

	
	
	
	public BigDecimal totalCustosFaturamentos(BigDecimal faturamento, BigDecimal impostos) {
		BigDecimal totalCustosFaturamentos = faturamento.subtract(impostos);
		return totalCustosFaturamentos;
	}

	public BigDecimal somaCacheEquipe(List<RelatorioEventos> infoEvento) {
		BigDecimal somaCacheEquipe = new BigDecimal("0");

		for (int i = 0; i < infoEvento.size(); i++) {
			somaCacheEquipe = somaCacheEquipe.add(infoEvento.get(i).getCacheEquipIn()).add(infoEvento.get(i).getDiretoria2());
		}
		return somaCacheEquipe;
	}

	
	public BigDecimal somaCacheDiretoria(List<RelatorioEventos> infoEvento) {
		BigDecimal somaCacheDiretoria = new BigDecimal("0");

		for (int i = 0; i < infoEvento.size(); i++) {
			somaCacheDiretoria = somaCacheDiretoria.add(infoEvento.get(i).getDiretoria1());
		}
		return somaCacheDiretoria;
	}

	public BigDecimal somaCacheTotal(BigDecimal cacheEquipe, BigDecimal cacheDiretoria) {
		BigDecimal somaCache = new BigDecimal("0");
		somaCache = cacheEquipe.add(cacheDiretoria);
		return somaCache;
	}
	
	public BigDecimal lucroOperacional(BigDecimal FaturamentoMes, BigDecimal impostos, BigDecimal totalCache) {
		BigDecimal lucroOperacional = new BigDecimal("0");
		lucroOperacional = FaturamentoMes.subtract(impostos).subtract(totalCache);
		return lucroOperacional;
	}

	public BigDecimal SomaDespFixas(BigDecimal outrosImpostos, BigDecimal outrosEscritorios,
									BigDecimal outrosTelefones,BigDecimal outrosFolhaPgto) {
		try {
			BigDecimal soma = outrosImpostos.add(outrosEscritorios).add(outrosTelefones).add(outrosFolhaPgto);
			return soma;
		} catch (Exception e) {
			BigDecimal soma = new BigDecimal("0");
			return soma;
		}
	}

	public BigDecimal SomaDespVariaveis(BigDecimal DespBancarias, BigDecimal despCaixaProjetos,
			BigDecimal outrosVariaveis) {
		
		BigDecimal soma = new BigDecimal("0.00");
		
		try {
		 soma = DespBancarias.add(despCaixaProjetos).add(outrosVariaveis);
		 return soma;
			
		} catch (Exception e) {
			return soma;
		}
	}

	public BigDecimal somaCreditosAplicacoes(BigDecimal despFixas,BigDecimal despVariaveis) {
		BigDecimal soma = new BigDecimal("0");
		soma  = soma.add(despFixas).add(despVariaveis);
		return soma;
	}
	
	public BigDecimal giroDeficitAvit(BigDecimal lucroOperacional, BigDecimal creditoAplic, BigDecimal margemOperacional) {
		BigDecimal soma = new BigDecimal("0");
		// Lucro Operacional - CreditosAplicacoes + MO
		try {
			soma = soma.add(lucroOperacional).subtract(creditoAplic).add(margemOperacional);
			return soma;
		} catch (Exception e) {
			System.out.println("erro SomaImpostos: "+e);
			return null;
		}
	}

	public BigDecimal totalPagar(BigDecimal eventosContasPagar,BigDecimal totalSalarios, BigDecimal totalCaches,
								 BigDecimal totalImpostosNF, BigDecimal OutrosImpostos) {
		try {
			
			BigDecimal somaTotalPagar = new BigDecimal("0");
			somaTotalPagar = somaTotalPagar.add(eventosContasPagar).add(totalSalarios).add(totalCaches).
						   add(totalImpostosNF).add(OutrosImpostos);
			return somaTotalPagar;
			
		} catch (Exception e) {
			BigDecimal somaTotalpagar = new BigDecimal("0");
			return somaTotalpagar;
		}
	}
	
	
}
