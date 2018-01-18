/*
* JPAConfiguration 
* 
* Essa classe equivale a persistence.xml
*  
* 
* 
*/

package br.com.sysloccOficial.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[]{
				"br.com.sysloccOficial.model", "br.com.sysloccOficial.financeiro.model" }
		);
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
	return em;
	}
	
		
	@Bean
	@Profile("dev")
	public DataSource dataSource(){
	DriverManagerDataSource dataSource =
	new DriverManagerDataSource();
	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
/*	dataSource.setUrl("jdbc:mysql://localhost:3306/loco");*/
    dataSource.setUrl("jdbc:mysql://localhost:3306/locomotivos");
//	dataSource.setUrl("jdbc:mysql://localhost:3306/locomotivos1");

// Mensagens de email 
// INSERT INTO `user` VALUES('admin','Administrador','$2a$10$EDPGgiRCxt01Jw5gGKdwleQ.j2wG3cRCmGUg87VCEC21ZByh8FNgi');
// INSERT INTO `user_role` VALUES('admin','ROLE_ADMIN');
// INSERT INTO `user_role` VALUES('admin','ROLE_DESENVOLVEDOR');
// INSERT INTO `locomotivos`.`usuario` (`nome`, `email`, `ramal`, `habilitado`, `usuario`, `senha`, `idDepartamento`, `cargo`, `nivel`, `userNovo`) VALUES ('Administrador', 'sisloc@loccoagencia.com.br', '0', '1', 'admin', '@locco1@novosistema#', '4', 'Administrador do Sistema', '2', 'admin');
// INSERT INTO `criacaoitemstatus` VALUES (1,'Em aberto'),(2,'Fechado'),(3,'Interrompido'),(4,'Excluido do Job'),(5,'Pendente'),(6,'Em Execução'),(7,'Excluído'),(8,'Pendencia Finalizada');	
// UPDATE `locomotivos`.`criacaostatus` SET `Status`='Abertos' WHERE `idCriacaoStatus`='1';
// UPDATE `locomotivos`.`criacaostatus` SET `Status`='Fechados' WHERE `idCriacaoStatus`='2';	
	
	
	
	
	
	
	dataSource.setUsername( "root" );
	dataSource.setPassword( "root" );
	return dataSource;
	}
	
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto","update");
		properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
	//	properties.setProperty("hibernate.generate_statistics", "true");
		/*properties.setProperty("hibernate.search.default.directory_provider", "filesystem");
		properties.setProperty("hibernate.search.default.indexBase", "/sysloccOficial/indexes");*/
		return properties;
	}
	
	
	
	@Bean
	public PlatformTransactionManager transactionManager
	(EntityManagerFactory emf){
	JpaTransactionManager transactionManager =
	new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(emf);
	
	return transactionManager;
	}
	
	
	
	
	
	
	
	
	
}
