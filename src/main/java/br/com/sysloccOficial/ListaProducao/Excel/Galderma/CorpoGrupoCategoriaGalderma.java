package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.math.BigDecimal;

public class CorpoGrupoCategoriaGalderma {
	
	private int idCategoriaGalderma;
	private int idGrupo;
	private String infoGrupo;
	private String tipoServico;
	private boolean temImposto;
	private boolean naoTemImposto;
	private BigDecimal precoItem;
	private BigDecimal orcamento;
	private double quantidade;
	private double diaria;
	
// ----------------------------------------------------------------- //	
	
	public String getTipoServico() {
		return tipoServico;
	}
	public int getIdCategoriaGalderma() {
		return idCategoriaGalderma;
	}
	public void setIdCategoriaGalderma(int idCategoriaGalderma) {
		this.idCategoriaGalderma = idCategoriaGalderma;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getInfoGrupo() {
		return infoGrupo;
	}
	public void setInfoGrupo(String infoGrupo) {
		this.infoGrupo = infoGrupo;
	}
	public boolean isTemImposto() {
		return temImposto;
	}
	public void setTemImposto(boolean temImposto) {
		this.temImposto = temImposto;
	}
	public boolean isNaoTemImposto() {
		return naoTemImposto;
	}
	public void setNaoTemImposto(boolean naoTemImposto) {
		this.naoTemImposto = naoTemImposto;
	}
	public BigDecimal getPrecoItem() {
		return precoItem;
	}
	public void setPrecoItem(BigDecimal precoItem) {
		this.precoItem = precoItem;
	}
	public BigDecimal getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(BigDecimal orcamento) {
		this.orcamento = orcamento;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public double getDiaria() {
		return diaria;
	}
	public void setDiaria(double diaria) {
		this.diaria = diaria;
	}
	
	
	
	

}
