package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RelatorioBVS {
	
	private Integer idFornecedor;
	private String nomeFornecedor;
	private BigDecimal valorFornecedor;
	private BigDecimal valorParaPagar;
	private BigDecimal diferenca;
	private BigDecimal valorTotalDiferenca;
	private BigDecimal giro;
	
	
	
// --------------------------------------------- //
	
	public BigDecimal calculaTotalDiferenca(List<RelatorioBVS> relatorioBVS){
		for (int i = 0; i < relatorioBVS.size(); i++) {
			valorTotalDiferenca = valorTotalDiferenca.add(relatorioBVS.get(i).getDiferenca());
		}
		return valorTotalDiferenca;
	}
	
	
	
	public BigDecimal getValorTotalDiferenca() {
		return valorTotalDiferenca;
	}
	
	public void setValorTotalDiferenca(BigDecimal valorTotalDiferenca) {
		this.valorTotalDiferenca = valorTotalDiferenca;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public BigDecimal getGiro() {
		return giro;
	}
	public void setGiro(BigDecimal giro) {
		this.giro = giro;
	}
	public Integer getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public BigDecimal getValorFornecedor() {
		return valorFornecedor;
	}
	public void setValorFornecedor(BigDecimal valorFornecedor) {
		this.valorFornecedor = valorFornecedor;
	}
	public BigDecimal getValorParaPagar() {
		return valorParaPagar;
	}
	public void setValorParaPagar(BigDecimal valorParaPagar) {
		this.valorParaPagar = valorParaPagar;
	}
	public BigDecimal getDiferenca() {
		return diferenca;
	}
	public void setDiferenca(BigDecimal diferenca) {
		this.diferenca = diferenca;
	}
	
	
}
