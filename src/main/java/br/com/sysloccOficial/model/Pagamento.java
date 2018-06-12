package br.com.sysloccOficial.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Pagamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idpagamento;
	private String banco;
	private String Agencia;
	private String conta;

	
	@Transient private Integer idTipoPagamentoTransiente;
	
	
// -------------------- Relacionamentos ----------------------- //	
	@OneToOne @JoinColumn(name="idEmpresa") private Empresa idEmpresa;
	@OneToOne(fetch = FetchType.LAZY) @JoinColumn(name="idtipoPagamento")private TipoPagamento idtipoPagamento;
// ------------------------------------------------------------ //	
	
	public Integer getIdpagamento() {
		return idpagamento;
	}

	public Integer getIdTipoPagamentoTransiente() {
		return idTipoPagamentoTransiente;
	}

	public void setIdTipoPagamentoTransiente(Integer idTipoPagamentoTransiente) {
		this.idTipoPagamentoTransiente = idTipoPagamentoTransiente;
	}

	public void setIdpagamento(Integer idpagamento) {
		this.idpagamento = idpagamento;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return Agencia;
	}

	public void setAgencia(String agencia) {
		Agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Empresa getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public TipoPagamento getIdtipoPagamento() {
		return idtipoPagamento;
	}

	public void setIdtipoPagamento(TipoPagamento idtipoPagamento) {
		this.idtipoPagamento = idtipoPagamento;
	}


	
	
	
}
