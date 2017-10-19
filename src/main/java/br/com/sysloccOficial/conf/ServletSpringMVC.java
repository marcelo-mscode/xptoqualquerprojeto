package br.com.sysloccOficial.conf;
/*
 * ServletSpringMVC
 * 
 * 
 * Essa classe equivale a web.xml
 * 
 * 
 *
 */
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.sysloccOficial.ListaProducao.DeterminaQuantidade.dao.BuscaQualquerQuantidadeDeterminantePadrao;
import br.com.sysloccOficial.ListaProducao.DeterminaQuantidade.dao.DeterminaQuantidadesDAO;
import br.com.sysloccOficial.ListaProducao.Excel.Galderma.MontaGruposCategoriasGalderma;
import br.com.sysloccOficial.calculosProducao.CalculaValoresProdutoGrupo;
import br.com.sysloccOficial.consultas.consultasAvancadas.ConsultasPassaSql;
import br.com.sysloccOficial.controller.notificacao.ControllerNotificao;
import br.com.sysloccOficial.controllerAdmSistema.ControllerUpdates;
import br.com.sysloccOficial.controllerBugs.BugsController;
import br.com.sysloccOficial.controllerCartaContratacao.CartaContratacaoController;
import br.com.sysloccOficial.controllerExcel.AuxExcelEstilos;
import br.com.sysloccOficial.controllerExcel.AuxExcelMontaCategoria;
import br.com.sysloccOficial.controllerExcel.AuxExcelSQL;
import br.com.sysloccOficial.controllerExcel.ExcelController;
import br.com.sysloccOficial.controllerExcel.MontaCalculosFinais;
import br.com.sysloccOficial.controllerExcel.MontaCategorias;
import br.com.sysloccOficial.controllerExcel.MontaGrupos;
import br.com.sysloccOficial.controllerExcel.MontaValoresTotais;
import br.com.sysloccOficial.controllerExcel.TextosStaticosExcel;
import br.com.sysloccOficial.controllerProducao.AuxDAOProducao;
import br.com.sysloccOficial.controllerProducao.MenuProducaoController;
import br.com.sysloccOficial.controllerProducao.carta.GeraCartaWord;
import br.com.sysloccOficial.controllerProducao.carta.MenuProducaoControllerCarta;
import br.com.sysloccOficial.controllerProducao.salvaItem.ApoioCartaSalvaItem;
import br.com.sysloccOficial.controllerProducao.salvaItem.ControllerSalvaItem;
import br.com.sysloccOficial.criacao.controllerCriacao.dao.CriacaoDAO;
import br.com.sysloccOficial.daos.UsuarioDAO;
import br.com.sysloccOficial.financeiro.contaspagar.InternaAuxiliar;
import br.com.sysloccOficial.financeiro.dao.RelatorioEventoDAO;
import br.com.sysloccOficial.financeiro.relatorioeventos.RelatorioCaches;
import br.com.sysloccOficial.model.NotificaEquipe;

public class ServletSpringMVC extends
AbstractAnnotationConfigDispatcherServletInitializer {
		@Override
			protected Class<?>[] getRootConfigClasses() {
			return new Class[]{SecurityConfiguration.class,
							   AppWebConfiguration.class,
							   JPAConfiguration.class,
							   Utilitaria.class,
							   UtilitariaConversoes.class,
							   UtilitariaDatas.class,
							   UtilitariaValores.class,
							   MenuProducaoController.class,
							   MenuProducaoControllerCarta.class,
							   CartaContratacaoController.class,
							   UtilitariaMsg.class,
							   BugsController.class,
							   ControllerUpdates.class,
							   NotificaEquipe.class,
							   CalculaValoresProdutoGrupo.class,
							   ExcelController.class,
							   AuxExcelMontaCategoria.class,
							   AuxExcelEstilos.class,
							   AuxExcelSQL.class,
							   MontaCategorias.class,
							   MontaCalculosFinais.class,
							   MontaValoresTotais.class,
							   TextosStaticosExcel.class,
							   MontaGrupos.class,
							   ControllerNotificao.class,
							   ControllerSalvaItem.class,
							   AuxDAOProducao.class,
							   GeraCartaWord.class,
							   UsuarioDAO.class,
							   RelatorioEventoDAO.class,
							   CriacaoDAO.class,
							   ApoioCartaSalvaItem.class,
							   InternaAuxiliar.class,
							   DeterminaQuantidadesDAO.class,
							   RelatorioCaches.class,
							   BuscaQualquerQuantidadeDeterminantePadrao.class,
							   MontaGruposCategoriasGalderma.class,
							   ConsultasPassaSql.class};
		}
		@Override
			protected Class<?>[] getServletConfigClasses() {
			return new Class[]{/*AppWebConfiguration.class,JPAConfiguration.class,*//*Utilitaria.class*/};
		
		}
		@Override
			protected String[] getServletMappings() {
			return new String[] {"/"};
		}
		@Override
		protected void customizeRegistration(Dynamic registration){
			registration.setMultipartConfig(new MultipartConfigElement(""));
		}
		
		@Override
		protected Filter[] getServletFilters() {
			return new Filter[] { new OpenEntityManagerInViewFilter() };
		}
		
		
}
