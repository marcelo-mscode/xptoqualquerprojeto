package br.com.sysloccOficial.financeiro.relatorioeventos.totalDiferencaTelefone;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioBVS;

public class CalculadoraDiferencaTelefone {
	
	public static BigDecimal totalDiferencaComTelefone( List<RelatorioBVS> relatorioBVS, BigDecimal fee,BigDecimal feeReduzido, BigDecimal impostoClienteDiferenca,BigDecimal margemContribuicao, BigDecimal valorTelefone) {
		TotalDiferencaTelefone calculaTotalCom = new CalculaTotalDiferencaTelefone();
		return calculaTotalCom.totalDiferencaComTelefone(relatorioBVS, fee, feeReduzido, impostoClienteDiferenca, margemContribuicao, valorTelefone);
	}
	
	public static BigDecimal totalDiferencaSemTelefone(List<RelatorioBVS> relatorioBVS, BigDecimal fee,BigDecimal feeReduzido, BigDecimal impostoClienteDiferenca) {
		TotalDiferencaTelefone calculaTotalSem = new CalculaTotalDiferencaTelefone();
		return calculaTotalSem.totalDiferencaSemTelefone(relatorioBVS, fee, feeReduzido, impostoClienteDiferenca);
	}

}
