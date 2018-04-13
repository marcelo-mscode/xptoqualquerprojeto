package br.com.sysloccOficial.financeiro.relatorioeventos.totalDiferencaTelefone;

import java.math.BigDecimal;
import java.util.List;

import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioBVS;

public interface TotalDiferencaTelefone {
	public BigDecimal totalDiferencaComTelefone(List<RelatorioBVS> relatorioBVS,BigDecimal fee,BigDecimal feeReduzido,BigDecimal impostoClienteDiferenca,BigDecimal margemContribuicao,BigDecimal valorTelefone, BigDecimal DespesasEvento);
	public BigDecimal totalDiferencaSemTelefone(List<RelatorioBVS> relatorioBVS,BigDecimal fee,BigDecimal feeReduzido, BigDecimal impostoClienteDiferenca, BigDecimal DespesasEvento);
}
