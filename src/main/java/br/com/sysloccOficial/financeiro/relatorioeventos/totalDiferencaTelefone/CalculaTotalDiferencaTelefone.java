package br.com.sysloccOficial.financeiro.relatorioeventos.totalDiferencaTelefone;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioBVS;

public class CalculaTotalDiferencaTelefone implements TotalDiferencaTelefone {
	
	@Override
	public BigDecimal totalDiferencaComTelefone( List<RelatorioBVS> relatorioBVS, BigDecimal fee,BigDecimal feeReduzido, BigDecimal impostoClienteDiferenca,BigDecimal margemContribuicao, BigDecimal valorTelefone, BigDecimal DespesasEvento) {
		BigDecimal totalDiferenca = new BigDecimal("0");
		for (int i = 0; i < relatorioBVS.size(); i++) {
			totalDiferenca = totalDiferenca.add(relatorioBVS.get(i).getDiferenca());
		}
		totalDiferenca = totalDiferenca.add(fee)
									   .add(feeReduzido)
									   .add(impostoClienteDiferenca)
									   .subtract(margemContribuicao)
									   .subtract(valorTelefone)
									   .subtract(DespesasEvento)
									   ;
		
		
		return totalDiferenca;
	}

	@Override
	public BigDecimal totalDiferencaSemTelefone(List<RelatorioBVS> relatorioBVS, BigDecimal fee,BigDecimal feeReduzido, BigDecimal impostoClienteDiferenca, BigDecimal DespesasEvento) {
		BigDecimal totalDiferenca = new BigDecimal("0");
		for (int i = 0; i < relatorioBVS.size(); i++) {
			totalDiferenca = totalDiferenca.add(relatorioBVS.get(i).getDiferenca());
		}
		totalDiferenca = totalDiferenca.add(fee)
									   .add(impostoClienteDiferenca)
									   .add(feeReduzido)
									   .subtract(DespesasEvento)
				;

		//totalDiferenca = totalDiferenca.add(fee).add(impostoClienteDiferenca).add(feeReduzido);
		
		
		
		
		return totalDiferenca;
	}
}
