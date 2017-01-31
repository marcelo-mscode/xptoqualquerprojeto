package br.com.sysloccOficial.listaproducao.testedeterminaquantidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import br.com.sysloccOficial.model.DeterminaQuantidades;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.ProdutoGrupo;

public class TestDeterminaQuantidade {

	private Grupo grupo;
	private Grupo grupoNaoTem;
	private Grupo grupoVazio;
	private ProdutoGrupo prod;
	private DeterminaQuantidades dt;
	private EntityManager manager;
	
	@Before
	public void inicializa(){
		
		
		
		this.grupoVazio = new Grupo();	

		this.grupo = new Grupo();	
		grupo.setIdgrupo(67192);

		this.grupoNaoTem = new Grupo();	
		grupoNaoTem.setIdgrupo(67193);
		
		this.prod = new ProdutoGrupo();
		prod.setIdProdutoGrupo(70690);

		this.dt = new DeterminaQuantidades();
		dt.setDiarias(1);
		dt.setPrecoTotal(new BigDecimal("490.000000000000"));
		dt.setQuantDeterm(1.000000000000);
		dt.setValorUnit(new BigDecimal("490.000000000000"));
		dt.setGrupo(grupo);
	}
	
	@Test
	public void temQuantidadeDeterminante() {
		VerificaQuantidadeDeterminante deterQuant = new VerificaQuantidadeDeterminante(grupo);
		boolean verifica = deterQuant.verifica(dt);
		assertEquals(true, verifica);
	}
	
	@Test
	public void naoTemQuantidadeDeterminante() {
		VerificaQuantidadeDeterminante deterQuant = new VerificaQuantidadeDeterminante(grupoNaoTem);
		boolean verifica = deterQuant.verifica(dt);
		assertEquals(false, verifica);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
