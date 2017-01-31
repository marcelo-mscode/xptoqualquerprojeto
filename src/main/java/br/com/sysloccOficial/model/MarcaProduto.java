package br.com.sysloccOficial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MarcaProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idmarcaProduto;
	
	
// --------- Relacionamentos ------------ //	
	
		@ManyToOne
		@JoinColumn(name="idMarca")
		private Marca idMarca;

		@ManyToOne
		@JoinColumn(name="idProduto")
		private Produto idProduto;

// -------------------------------------- //
		
		public Integer getIdmarcaProduto() {
			return idmarcaProduto;
		}

		public void setIdmarcaProduto(Integer idmarcaProduto) {
			this.idmarcaProduto = idmarcaProduto;
		}

		public Marca getIdMarca() {
			return idMarca;
		}

		public void setIdMarca(Marca idMarca) {
			this.idMarca = idMarca;
		}

		public Produto getIdProduto() {
			return idProduto;
		}

		public void setIdProduto(Produto idProduto) {
			this.idProduto = idProduto;
		}

}
