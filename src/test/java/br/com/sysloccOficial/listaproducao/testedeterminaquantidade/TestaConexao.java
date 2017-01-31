package br.com.sysloccOficial.listaproducao.testedeterminaquantidade;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import br.com.sysloccOficial.conf.DataSourceConfigurationTest;
import br.com.sysloccOficial.conf.JPAConfiguration;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfigurationTest.class,UsuarioDAO.class,JPAConfiguration.class})
@ActiveProfiles("test")
public class TestaConexao {
	
	@Autowired UsuarioDAO usuarioDAO;
	
	
	@Transactional
	@Test
	public void test() {
		Usuario u = usuarioDAO.retornaUmUsuario(43);
		assertEquals("Marcelo Souza", u.getNome());
	}

}
