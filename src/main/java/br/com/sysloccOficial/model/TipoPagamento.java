package br.com.sysloccOficial.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class TipoPagamento {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer	idTipoPagamento;
private String	tipoPagamento;

//-------------------- Relacionamentos ----------------------- //	
@OneToMany(mappedBy="idtipoPagamento")
public List<Pagamento> pagamento;

// ----------------------------------------------------------- //

public Integer getIdTipoPagamento() {
	return idTipoPagamento;
}
public List<Pagamento> getPagamento() {
	return pagamento;
}
public void setPagamento(List<Pagamento> pagamento) {
	this.pagamento = pagamento;
}
public void setIdTipoPagamento(Integer idTipoPagamento) {
	this.idTipoPagamento = idTipoPagamento;
}
public String getTipoPagamento() {
	return tipoPagamento;
}
public void setTipoPagamento(String tipoPagamento) {
	this.tipoPagamento = tipoPagamento;
}



	
	
}
