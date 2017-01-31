package br.com.sysloccOficial.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Governador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String governadorNome;
	
	@OneToOne(mappedBy = "governador") 
	private Estado estado;
	
	
	
	
	public String getGovernadorNome() {
		return governadorNome;
	}
	public void setGovernadorNome(String governadorNome) {
		this.governadorNome = governadorNome;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGovernador() {
		return governadorNome;
	}
	public void setGovernador(String governador) {
		this.governadorNome = governador;
	}
	
	
	
}
