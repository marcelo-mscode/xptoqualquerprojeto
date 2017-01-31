package br.com.sysloccOficial.model;

import javax.persistence.*;


@Entity
public class Estado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@OneToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Governador governador;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Governador getGovernador() {
		return governador;
	}

	public void setGovernador(Governador integer) {
		this.governador = integer;
	}
			
}
