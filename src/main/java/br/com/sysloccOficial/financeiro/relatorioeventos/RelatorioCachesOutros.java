package br.com.sysloccOficial.financeiro.relatorioeventos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.sysloccOficial.model.CachePadrao;

public class RelatorioCachesOutros {

	private BigDecimal equipInt;
	private BigDecimal equipExt;
	private BigDecimal diretoria1 = new BigDecimal("0");
	private BigDecimal diretoria2 = new BigDecimal("0");;
	private BigDecimal liqImpostoTotalPagto;
	private BigDecimal totalCachesIntExt;
	private BigDecimal total = new BigDecimal("0");
	private BigDecimal totalCaches = new BigDecimal("0");
	private BigDecimal determinaPorcentagem = new BigDecimal("100");
	private List<CachePadrao> relatorio;
	
	public RelatorioCachesOutros(){}
	
	public RelatorioCachesOutros(List<CachePadrao> relatorio){
		this.relatorio = relatorio;
	}
	public RelatorioCachesOutros(List<CachePadrao> relatorio, BigDecimal totalDif){
		this.relatorio = relatorio;
		this.liqImpostoTotalPagto = totalDif;
	}
	
// ---------------------------------------------------------------------------------- //	
	
	public BigDecimal calculaTotalDiretoria(TipoCache tipo){
		BigDecimal valorCalculado =  new BigDecimal("0");
		for (int i = 0; i < relatorio.size(); i++) {
			if(relatorio.get(i).getTipoCache().equals(tipo)){
				valorCalculado = valorCalculado.add(
						totalDoLiqMenosCahesFuncionarios()
						.multiply(new BigDecimal(relatorio.get(i).getPorcentagem())
						.divide(determinaPorcentagem))
				);
			}
		}
		return valorCalculado;
	}

	private BigDecimal totalDoLiqMenosCahesFuncionarios() {
		BigDecimal valorTotalFuncionBigDecimal;
		valorTotalFuncionBigDecimal = totalCacheIntExt();
		total = liqImpostoTotalPagto.subtract(valorTotalFuncionBigDecimal);
		return total;
	}
	
	private BigDecimal totalCacheIntExt() {
		BigDecimal valorTotalFuncionBigDecimal = new BigDecimal("0");
		for (int j = 0; j < relatorio.size(); j++) {
			if(relatorio.get(j).getTipoCache() ==  TipoCache.FUNCIONARIO){
				valorTotalFuncionBigDecimal = valorTotalFuncionBigDecimal.add(relatorio.get(j).getValorPorcentagem());
			}
		}
		return totalCachesIntExt = valorTotalFuncionBigDecimal;
	}
	
	public BigDecimal getDiretoria1() {
		diretoria1 = calculaTotalDiretoria(TipoCache.DIRETORIA1);
		return diretoria1;
	}

	public BigDecimal getDiretoria2() {
		diretoria2 = calculaTotalDiretoria(TipoCache.DIRETORIA2);
		return diretoria2;
	}
	
	public BigDecimal getEquipInt() {
		equipInt = totalCacheIntExt();
		return equipInt;
	}
	
	public BigDecimal getTotalCaches() {
		totalCaches = getDiretoria1().add(getDiretoria2()).add(totalCacheIntExt());
		return totalCaches;
	}

	public BigDecimal getLiqImpostoTotalPagto() {
		return liqImpostoTotalPagto;
	}
	
	public void setEquipInt(BigDecimal equipInt) {
		this.equipInt = equipInt;
	}
	public BigDecimal getEquipExt() {
		return equipExt;
	}
	public void setEquipExt(BigDecimal equipExt) {
		this.equipExt = equipExt;
	}
	public void setDiretoria1(BigDecimal diretoria1) {
		this.diretoria1 = diretoria1;
	}
	public void setDiretoria2(BigDecimal diretoria2) {
		this.diretoria2 = diretoria2;
	}
	public void setLiqImpostoTotalPagto(BigDecimal liqImpostoTotalPagto) {
		this.liqImpostoTotalPagto = liqImpostoTotalPagto;
	}
	public BigDecimal getTotalCachesIntExt() {
		return totalCachesIntExt;
	}
	public void setTotalCachesIntExt(BigDecimal totalCachesIntExt) {
		this.totalCachesIntExt = totalCachesIntExt;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
	
	
	
	
	
	
	
}
