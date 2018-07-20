package br.com.sysloccOficial.financeiro.relatorioeventos.totalPagarFornecedores;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioBVS;

public class CalculaTotalFornecedores implements TotalPagarFornecedores{

	@Override
	public BigDecimal totalAPagarFornecedores(List<RelatorioBVS> relatorioBVS) {
		BigDecimal total = new BigDecimal("0");
		for (int i = 0; i < relatorioBVS.size(); i++) {
			total =          total.add(relatorioBVS.get(i).getValorFornecedor()).subtract(relatorioBVS.get(i).getDiferenca());
		}
		return total;
	}

	@Override
	public BigDecimal totalAPagarFornecedoresComND(List<RelatorioBVS> relatorioBVS) {
		BigDecimal total = new BigDecimal("0");
		for (int i = 0; i < relatorioBVS.size(); i++) {
			total =          total.add(relatorioBVS.get(i).getValorFornecedor());
		}
		return total;	}

}
