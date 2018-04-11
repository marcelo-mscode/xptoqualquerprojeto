package br.com.sysloccOficial.conf;

import java.util.Properties;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import abr.com.springboot.repositories.PessoaRepository;
import abr.com.springboot.resources.PessoaResources;
import abr.com.springboot.services.PessoaService;
import br.com.sysloccOficial.ListaProducao.DeterminaQuantidade.dao.BuscaQualquerQuantidadeDeterminantePadrao;
import br.com.sysloccOficial.ListaProducao.DeterminaQuantidade.dao.BuscaQuantidadeDeterminante;
import br.com.sysloccOficial.ListaProducao.Excel.ExcelNovoBayerController;
import br.com.sysloccOficial.ListaProducao.Excel.Galderma.ExcelGaldermaController;
import br.com.sysloccOficial.ListaProducao.calcula.grupos.AtualizaGruposController;
import br.com.sysloccOficial.ListaProducao.calcula.grupos.dao.ListaProducaoGrupoDAO;
import br.com.sysloccOficial.consultas.Consulta;
import br.com.sysloccOficial.controllerProducao.CarregaItensListaController;
import br.com.sysloccOficial.controllerProducao.carta.ApoioCartaUtil;
import br.com.sysloccOficial.controllers.FileSaver;
import br.com.sysloccOficial.controllers.HomeController;
import br.com.sysloccOficial.criacao.controllerCriacao.dao.RelatorioDAO;
import br.com.sysloccOficial.criacao.controllerCriacao.itens.Criacao;
import br.com.sysloccOficial.criacao.controllerCriacao.relatorio.apoio.ApoioRelatorio;
import br.com.sysloccOficial.criacao.controllerCriacao.relatorio.listas.CriacaoRelatorioControllerListas;
import br.com.sysloccOficial.criacao.controllerCriacao.relatorio.usuario.ApoioRelatorioItensUsuario;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.empresa.excel.EmpresaExcelController;
import br.com.sysloccOficial.empresa.excel.GeraExcelListagem;
import br.com.sysloccOficial.financeiro.analitico.index.AnaliticoIndexController;
import br.com.sysloccOficial.financeiro.analitico.individual.AnaliticoIndividualController;
import br.com.sysloccOficial.financeiro.analitico.novo.NovoRelatorioController;
import br.com.sysloccOficial.financeiro.atualizaInterna.AtualizaInternaController;
import br.com.sysloccOficial.financeiro.calculadora.Calculadora;
import br.com.sysloccOficial.financeiro.contaspagar.ContasPagarController;
import br.com.sysloccOficial.financeiro.contaspagar.Interna;
import br.com.sysloccOficial.financeiro.contaspagar.InternaAuxiliar;
import br.com.sysloccOficial.financeiro.contasreceber.ContasReceberController;
import br.com.sysloccOficial.financeiro.dao.AnaliticoIndividualMovimentoFinanceiro;
import br.com.sysloccOficial.financeiro.dao.ContasPagarDAO;
import br.com.sysloccOficial.financeiro.dao.InternaListasDAO;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.financeiro.funcionario.cache.CachePadraoController;
import br.com.sysloccOficial.financeiro.indexlistainternaindividual.IndexInternaIndividual;
import br.com.sysloccOficial.financeiro.indexlistasinternas.IndexInterna;
import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioCaches;
import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioEventoIndividualApoio;
import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioEventoIndividualController;
import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioEventosIndexController;
import br.com.sysloccOficial.financeiro.resumomes.index.ResumoMesIndex;
import br.com.sysloccOficial.financeiro.resumomes.individual.ResumoMesIndividualController;
import br.com.sysloccOficial.model.prospeccao.Prospeccao;
import br.com.sysloccOficial.prospeccao.controllerProspeccao.editaProspeccao.ProspeccaoEditaController;
import br.com.sysloccOficial.prospeccao.controllerProspeccao.index.ProspeccaoIndexController;
import br.com.sysloccOficial.prospeccao.controllerProspeccao.interacoes.ProspeccaoInteracoes;
import br.com.sysloccOficial.prospeccao.controllerProspeccao.novaProspeccao.ProspeccaoNovaController;
import br.com.sysloccOficial.prospeccao.dao.ProspeccaoDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class,
								       UsuarioDAO.class,
								        FileSaver.class,
								      EnviaEmails.class,
								         Consulta.class,
								          Criacao.class,
								          RelatorioDAO.class,
								          ApoioRelatorio.class,
								          CriacaoRelatorioControllerListas.class,
								          ApoioRelatorioItensUsuario.class,
								          ProspeccaoIndexController.class,
								          ProspeccaoNovaController.class,
								          ProspeccaoEditaController.class,
								          Prospeccao.class,
								          Utilitaria.class,
								          ProspeccaoDAO.class,
								          ListaProducaoGrupoDAO.class,
								          ProspeccaoInteracoes.class,
								          ProspeccaoInteracoes.class,
								          BuscaQuantidadeDeterminante.class,
								          IndexInterna.class,
								          IndexInternaIndividual.class,
								          AtualizaInternaController.class,
								          InternaAuxiliar.class,
								          ContasPagarDAO.class,
								          Interna.class,
								          InternaListasDAO.class,
								          BuscaQualquerQuantidadeDeterminantePadrao.class,
								          Calculadora.class,
								          CarregaItensListaController.class,
								          ContasPagarController.class,
								          ContasReceberController.class,
								          ResumoMesIndex.class,
								          ResumoMesIndividualController.class,
								          RelatorioEventosIndexController.class,
								          RelatorioEventoIndividualController.class,
								          AtualizaGruposController.class,
								          AnaliticoIndexController.class,
								          AnaliticoIndividualMovimentoFinanceiro.class,
								          NovoRelatorioController.class,
								          RelatorioCaches.class,
								          RelatorioEventoIndividualApoio.class,
								          GeraExcelListagem.class,
								          ExcelNovoBayerController.class,
								          ExcelGaldermaController.class,
								          EmpresaExcelController.class,
								     //    CalculaaaaCachesSemTelefone.class,
								          CachePadraoController.class,
								          RelatorioEventoDAO.class,
								          AnaliticoIndividualController.class,
								          PessoaService.class,
								          PessoaRepository.class,
								          /* PessoaResources.class,
								          */
								          ApoioCartaUtil.class})
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager();
	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
	InternalResourceViewResolver resolver =	new InternalResourceViewResolver();
	resolver.setPrefix("/WEB-INF/views/");
	resolver.setSuffix(".jsp");
	return resolver;
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**")
                    .addResourceLocations("/resources/");
    }
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
		
	};
	
	
	@Bean
	public FormattingConversionService mvcConserConversionService(){
		
		DefaultFormattingConversionService conversionService = 
				new DefaultFormattingConversionService(true);
		
		DateFormatterRegistrar registrar =
				new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyy-MM-dd"));
		registrar.registerFormatters(conversionService);

		return conversionService;
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
	//	bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;
	}
	
	@Bean
        public MailSender mailSender() {
	
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		/*javaMailSenderImpl.setHost("smtp.gmail.com");*/
		javaMailSenderImpl.setHost("mail.loccoagencia.com.br");
		javaMailSenderImpl.setPassword("Locco1234");
		/*javaMailSenderImpl.setPort(587);*/
		javaMailSenderImpl.setPort(26);
		javaMailSenderImpl.setUsername("sisloc@loccoagencia.com.br");
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);
		javaMailSenderImpl.setJavaMailProperties(mailProperties);
		
		return javaMailSenderImpl;
	}
	

}
