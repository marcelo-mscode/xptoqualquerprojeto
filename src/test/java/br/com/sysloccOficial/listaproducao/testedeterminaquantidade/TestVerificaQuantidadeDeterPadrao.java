package br.com.sysloccOficial.listaproducao.testedeterminaquantidade;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import br.com.sysloccOficial.conf.DataSourceConfigurationTest;
import br.com.sysloccOficial.conf.JPAConfiguration;
import br.com.sysloccOficial.daos.ProducaoDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfigurationTest.class,ProducaoDAO.class,JPAConfiguration.class})
@ActiveProfiles("test")
public class TestVerificaQuantidadeDeterPadrao {

	
	@Autowired ProducaoDAO producaoDAO;
	
	@Transactional
	@Test
	public void temQuantidadeDeterminante() {
		
		/*Lista lista  = new Lista();
		lista.setIdLista(2186);
		Lista listaDuplicada = new Lista();
		
		listaDuplicada = lista;
		listaDuplicada.setRevisao(lista.getRevisao()+1);
		listaDuplicada.setIdLista(2187);
		listaDuplicada.setListaCod(lista.getListaCod());
		
		
		
		boolean verifica = producaoDAO.clonaCategoria(2186,listaDuplicada);
		assertEquals(true, verifica);*/
	}

	/*	@Test
	public void naoQuantidadeDeterminante() {
		boolean verifica = determinaQuantidadeDAO.buscaQuantPorGrupo(69075);
		assertEquals(true, verifica);
	}*/

	/*@Test
	public void QuantidadeDeterminantePadrao() {
		boolean verifica = determinaQuantidadeDAO.buscaQuantPadraoPorGrupo(64676,1);
		assertEquals(true, verifica);
	}

	@Test
	public void naoQuantidadeDeterminantePadrao() {
		boolean verifica = determinaQuantidadeDAO.buscaQuantPadraoPorGrupo(62224676,1);
		assertEquals(false, verifica);
	}*/
	
	
}
