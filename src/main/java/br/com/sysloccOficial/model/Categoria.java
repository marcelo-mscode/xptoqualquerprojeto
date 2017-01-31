package br.com.sysloccOficial.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
public class Categoria {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer idcategoria;
  private String categoria;
 
  private String impostoTitulo;
  //private Integer idImposto;
  private Integer categoriaOrdem;
  private BigDecimal imposto;
		
  
// ----------------- Transiente ------------------------------ //
  @Transient private Integer idListaTransiente;
  @Transient private Integer idImpostoTrasiente;
  
  
// ---------------- Relacionamentos ------------------------- //  
  @OneToMany(mappedBy="idCategoria", fetch=FetchType.EAGER)
  @OrderBy(value="ordemGrupo")private List<Grupo> grupo;
 
  @ManyToOne @JoinColumn(name="idLista") private Lista idLista;
  @ManyToOne @JoinColumn(name="idImposto") private Imposto idImposto;
  
// ---------------------------------------------------------- //  
  
  	public Integer getIdcategoria() {
		return idcategoria;
	}
	public String getImpostoTitulo() {
		return impostoTitulo;
	}
	public void setImpostoTitulo(String impostoTitulo) {
		this.impostoTitulo = impostoTitulo;
	}
	public Integer getIdImpostoTrasiente() {
		return idImpostoTrasiente;
	}
	public void setIdImpostoTrasiente(Integer idImpostoTrasiente) {
		this.idImpostoTrasiente = idImpostoTrasiente;
	}
	public Imposto getIdImposto() {
		return idImposto;
	}
	public void setIdImposto(Imposto idImposto) {
		this.idImposto = idImposto;
	}
	public Integer getIdListaTransiente() {
		return idListaTransiente;
	}
	public void setIdListaTransiente(Integer idListaTransiente) {
		this.idListaTransiente = idListaTransiente;
	}
	public Lista getIdLista() {
		return idLista;
	}
	public void setIdLista(Lista idLista) {
		this.idLista = idLista;
	}
	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getCategoriaOrdem() {
		return categoriaOrdem;
	}
	public void setCategoriaOrdem(Integer categoriaOrdem) {
		this.categoriaOrdem = categoriaOrdem;
	}
	public BigDecimal getImposto() {
		return imposto;
	}
	public void setImposto(BigDecimal imposto) {
		this.imposto = imposto;
	}
	public List<Grupo> getGrupo() {
		return grupo;
	}
	public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}	
	
	
  
}
