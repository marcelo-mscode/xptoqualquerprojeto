package br.com.sysloccOficial.model.producao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class DtPgtoFornecedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dtPagtoForncedor;

	@Enumerated(EnumType.STRING)
    private StatusFinanceiro Status;	

	
// ------------------------------------------------------ //	
	@OneToOne @JoinColumn(name="idValorPgForn") private ValorPagtoFornecedor valorPgtoForn;
// ------------------------------------------------------ //	
	@Temporal(TemporalType.DATE) private Date dataPagar;
// ------------------------------------------------------ //	
	
	public Integer getDtPagtoForncedor() {
		return dtPagtoForncedor;
	}
	public void setDtPagtoForncedor(Integer dtPagtoForncedor) {
		this.dtPagtoForncedor = dtPagtoForncedor;
	}

	public StatusFinanceiro getStatus() {
		return Status;
	}
	public void setStatus(StatusFinanceiro status) {
		Status = status;
	}
	public ValorPagtoFornecedor getValorPgtoForn() {
		return valorPgtoForn;
	}
	public void setValorPgtoForn(ValorPagtoFornecedor valorPgtoForn) {
		this.valorPgtoForn = valorPgtoForn;
	}
	public Date getDataPagar() {
		return dataPagar;
	}
	public void setDataPagar(Date dataPagar) {
		this.dataPagar = dataPagar;
	}
	
	
// ------------------------------------------------------ //	

}
