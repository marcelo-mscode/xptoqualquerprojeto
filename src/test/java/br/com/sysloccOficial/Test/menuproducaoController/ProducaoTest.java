package br.com.sysloccOficial.Test.menuproducaoController;

import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.sysloccOficial.conf.JPAConfiguration;
import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.controllerProducao.AuxDAOProducao;
import br.com.sysloccOficial.controllerProducao.AuxProducao;
import br.com.sysloccOficial.daos.JobStatusDAO;
import br.com.sysloccOficial.model.Producao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AuxDAOProducao.class,Producao.class,JPAConfiguration.class,Utilitaria.class,AuxProducao.class,JobStatusDAO.class,JobStatusDAO.class})
public class ProducaoTest {


	
}
