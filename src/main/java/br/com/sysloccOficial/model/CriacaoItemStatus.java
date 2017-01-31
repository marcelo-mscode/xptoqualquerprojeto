/*

1	Em aberto
2	Fechado
3	Interrompido
4	Excluido do Job
5	Pendente
6	Em Execução
7	Excluído
8	Pendencia Finalizada

*/



package br.com.sysloccOficial.model;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CriacaoItemStatus {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCriacaoStatus;
	private String Status;

// ----------------------- Relacionamento ----------------------------- //
	@OneToMany( mappedBy="criacaoItemStatus") List<CriacaoItemStatus> criacaoItemStatus;
	@OneToMany( mappedBy="criacaoItemStatus") List<CriacaoItemPendencia> criacaoHistoricoStatus;
	@OneToMany( mappedBy="criacaoItemStatus") List<CriacaoItemHistorico> criacaoItemHistorico;
	@OneToMany( mappedBy="criacaoItemStatus") List<CriacaoItemPendenciaHistorico> criacaoItemPenciaHistorico;
// -------------------------------------------------------------------- //	
	
	public Integer getIdCriacaoStatus() {
		return idCriacaoStatus;
	}
	public List<CriacaoItemPendenciaHistorico> getCriacaoItemPenciaHistorico() {
		return criacaoItemPenciaHistorico;
	}
	public void setCriacaoItemPenciaHistorico(
			List<CriacaoItemPendenciaHistorico> criacaoItemPenciaHistorico) {
		this.criacaoItemPenciaHistorico = criacaoItemPenciaHistorico;
	}
	public List<CriacaoItemHistorico> getCriacaoItemHistorico() {
		return criacaoItemHistorico;
	}
	public void setCriacaoItemHistorico(
			List<CriacaoItemHistorico> criacaoItemHistorico) {
		this.criacaoItemHistorico = criacaoItemHistorico;
	}
	public List<CriacaoItemPendencia> getCriacaoHistoricoStatus() {
		return criacaoHistoricoStatus;
	}
	public void setCriacaoHistoricoStatus(
			List<CriacaoItemPendencia> criacaoHistoricoStatus) {
		this.criacaoHistoricoStatus = criacaoHistoricoStatus;
	}
	public List<CriacaoItemStatus> getCriacaoItemStatus() {
		return criacaoItemStatus;
	}
	public void setCriacaoItemStatus(List<CriacaoItemStatus> criacaoItemStatus) {
		this.criacaoItemStatus = criacaoItemStatus;
	}
	public void setIdCriacaoStatus(Integer idCriacaoStatus) {
		this.idCriacaoStatus = idCriacaoStatus;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
