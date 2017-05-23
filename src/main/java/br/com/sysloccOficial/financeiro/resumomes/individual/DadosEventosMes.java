package br.com.sysloccOficial.financeiro.resumomes.individual;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;
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
		BigDecimal faturamentoMes = new BigDecimal("0");
		faturamentoMes = faturamentoEventos.multiply(new BigDecimal("0.1595")); 
		return faturamentoMes;
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
	
	
	
	
	
	
	
	
	
	
	
	
}
