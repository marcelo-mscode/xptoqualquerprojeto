package br.com.sysloccOficial.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class CriacaoLista {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCriacaoLista;
	private String listaTitulo;
	private String tempoTotal;
	private Integer versao;
	
	
	
// ------------------- Relacionamentos ------------------------------ // 
 	@OneToMany(mappedBy="criacaoLista")	private List<CriacaoItemLista> criacaoItemLista;
 	@OneToOne @JoinColumn(name="idListaProducao") private Lista listaProducao;
 	@OneToOne @JoinColumn(name="enviadoParaCriacaoPor") Usuario usuario; 
 	@OneToOne @JoinColumn(name="responsavel") Usuario usuarioReponsável; 
 	@OneToOne @JoinColumn(name="parResponsavel") Usuario parReponsavel; 
 	@ManyToOne @JoinColumn(name="CriacaoStatus")private CriacaoStatus criacaoStatus;
 	
 	
// ----------------------- Datas ------------------------------------ //	
 	@Temporal(TemporalType.TIMESTAMP) private Calendar dataCriacao;
 	@Temporal(TemporalType.TIMESTAMP) private Calendar dataFechamento;
// -------------------- Transientes -------------------------------- //	
	
 	
	
	public Integer getIdCriacaoLista() {
		return idCriacaoLista;
	}
	public String getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(String tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	public Integer getVersao() {
		return versao;
	}
	public void setVersao(Integer versao) {
		this.versao = versao;
	}
	public Usuario getParReponsavel() {
		return parReponsavel;
	}
	public void setParReponsavel(Usuario parReponsavel) {
		this.parReponsavel = parReponsavel;
	}
	public Calendar getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Calendar dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Usuario getUsuarioReponsável() {
		return usuarioReponsável;
	}
	public void setUsuarioReponsável(Usuario usuarioReponsável) {
		this.usuarioReponsável = usuarioReponsável;
	}
	public String getListaTitulo() {
		return listaTitulo;
	}
	public void setListaTitulo(String listaTitulo) {
		this.listaTitulo = listaTitulo;
	}
	public CriacaoStatus getCriacaoStatus() {
		return criacaoStatus;
	}
	public void setCriacaoStatus(CriacaoStatus criacaoStatus) {
		this.criacaoStatus = criacaoStatus;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Lista getListaProducao() {
		return listaProducao;
	}
	public void setListaProducao(Lista listaProducao) {
		this.listaProducao = listaProducao;
	}
	public List<CriacaoItemLista> getCriacaoItemLista() {
		return criacaoItemLista;
	}
	public void setCriacaoItemLista(List<CriacaoItemLista> criacaoItemLista) {
		this.criacaoItemLista = criacaoItemLista;
	}
	public Calendar getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public void setIdCriacaoLista(Integer idCriacaoLista) {
		this.idCriacaoLista = idCriacaoLista;
	}
	
	
}
