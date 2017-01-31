package br.com.sysloccOficial.consultas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.controllers.ValoresEmGrupo;
import br.com.sysloccOficial.controllers.ValoresNaLista;
import br.com.sysloccOficial.controllers.ValoresProdutoGrupo;
import br.com.sysloccOficial.daos.AtuacaoDAO;
import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.daos.EstatusDAO;
import br.com.sysloccOficial.daos.ImpostoDAO;
import br.com.sysloccOficial.daos.JobDAO;
import br.com.sysloccOficial.daos.ListaDAO;
import br.com.sysloccOficial.daos.LocalEventoDAO;
import br.com.sysloccOficial.daos.ProducaoDAO;
import br.com.sysloccOficial.daos.ProdutoDAO;
import br.com.sysloccOficial.daos.ProdutoGrupoDAO;
import br.com.sysloccOficial.model.ListaConsulta;


@Controller
public class ListasConsultaAjax extends Consulta{

//	@PersistenceContext	private EntityManager manager;
	@Autowired private EmpresaDAO empresaDAO;
	@Autowired private JobDAO jobDAO;
	@Autowired private ListaDAO listaDAO;
	@Autowired private ProdutoGrupoDAO produtoGrupoDAO;
	@Autowired private AtuacaoDAO atuacaoDAO;
	@Autowired private ProdutoDAO produtoDAO;
	@Autowired private ImpostoDAO impostoDAO;
	@Autowired private ProducaoDAO producaoDAO;
	@Autowired private Utilitaria util;
	@Autowired private ValoresEmGrupo valoresEmGrupo;
	@Autowired private ValoresProdutoGrupo valoresProdutoGrupo;
	@Autowired private ValoresNaLista valoresNaLista;
	@Autowired private LocalEventoDAO localEventoDAO;
	@Autowired private EstatusDAO estatusDAO;
	@Autowired private UtilitariaDatas utilDatas;
	
	ModelAndView MV = new ModelAndView();
	

// ------------------------------------------------------------------------------------------------------------------------------------------------ //	
	
	String consulta = "select l.idLista,l.lista,l.revisao,l.idJob,l.idlistaEstatus,l.listaCod from Lista l ";

	
	@RequestMapping("/consultaListasAjax")
	public ModelAndView consultaListasAjax(String termo) throws InterruptedException{
		
		
		
		MV.setViewName("producao/consultaListasAjax");

		String ZA  = " order by lista desc";
		String AZ  = " order by lista";
		String empresaZA = " order by l.idJob.empresa.empresa desc";
		String empresaAZ = " order by l.idJob.empresa.empresa";
		String JobAZ = " order by l.idJob.titulo";
		String JobZA = " order by l.idJob.titulo desc";
		String codigoAZ = " order by l.listaCod ";
		String codigoZA = " order by l.listaCod desc";
		
		
		if(termo.equals("1")){
			MV.addObject("listaAjax", ListaDeObjetos(consulta+AZ));
		}else if(termo.equals("2")){
			MV.addObject("listaAjax", ListaDeObjetos(consulta+ZA));
		}else if(termo.equals("3")){
			MV.addObject("listaAjax", ListaDeObjetos(consulta+empresaAZ));
		}else if(termo.equals("4")){
			MV.addObject("listaAjax", ListaDeObjetos(consulta+empresaZA));
		}else if(termo.equals("5")){
			MV.addObject("listaAjax", ListaDeObjetos(consulta+JobAZ));
		}else if(termo.equals("6")){
			MV.addObject("listaAjax", ListaDeObjetos(consulta+JobZA));
		}else if(termo.equals("7")){
			MV.addObject("listaAjax", ListaDeObjetos(consulta+codigoAZ));
		}else if(termo.equals("8")){
			MV.addObject("listaAjax", ListaDeObjetos(consulta+codigoZA));
		}
		new Thread();Thread.sleep(50);  
		return MV;
	}

	
	
	/**
	 * Método para carregar Lista de produção por Ajax
	 * 
	 * @param termo
	 * @return
	 * @throws InterruptedException
	 * @throws ParseException 
	 */
	@RequestMapping("/consultaListasAjaxEmpresa")
	public ModelAndView consultaListasAjaxEmpresa(String termo, String base) throws InterruptedException, ParseException{
		
		Date dataMeses = utilDatas.subtraiMeses(18);
		String data = utilDatas.converteDateParaStringInternacional(dataMeses);
		
		String montaConsulta = "";
		
		
		if(base.equals("base")){
			montaConsulta = termo + " and dataCriacao > '"+data+"'";
		}else{
			montaConsulta = termo + " and dataCriacao < '"+data+"'";
		}
		
		
		
		if(termo.equals("selecione")){
			MV.setViewName("producao/consultasAjax/todosclientes");
			String empresaZA = " order by dataCriacao desc";
			MV.addObject("listaAjax", ListaDeObjetos(consulta+empresaZA));
		}else{
		
		List<String> codListas = listaDAO.t(montaConsulta);
		
		List<Integer> idList2 = new ArrayList<Integer>();
		
		
		for(int i =0; i< codListas.size();i++){
			String codList = codListas.get(i);
			List<Integer> codigos = listaDAO.retornaCodListas(codList);
			
			for(int x = 0; x < codigos.size();x++){
				idList2.add(codigos.get(x));
			}
		}

		
		List<ListaConsulta> listas = new ArrayList<ListaConsulta>();
		for(int i = 0; i < idList2.size(); i++){
			Integer idLista = idList2.get(i);
			ListaConsulta l = new ListaConsulta();

			l.setLista((String)listaDAO.condicao(idLista,"l.lista"));
			l.setIdLista((Integer)listaDAO.condicao(idLista,"l.idLista"));
			l.setRevisao((Integer)listaDAO.condicao(idLista,"l.revisao"));
			l.setEmpresa((String)listaDAO.condicao(idLista,"l.idJob.empresa.empresa"));
			l.setJob((String)listaDAO.condicao(idLista,"l.idJob.titulo"));
			l.setStatus((String)listaDAO.condicao(idLista,"l.idlistaEstatus.listaEstatus"));
			l.setListaCod((String)listaDAO.condicao(idLista,"l.listaCod"));
			listas.add(l);
		}
		
// ----------------------------------------------------------------//
		
			MV.setViewName("producao/consultaListasAjax");
			MV.addObject("listaAjax", listas);

			/*String empresaZA = " where l.idJob.empresa.idEmpresa ="+termo + "order by listaCod desc";
			String teste = " where l.idJob.empresa.idEmpresa ="+termo + "order by listaCod desc";*/
//			MV.addObject("listaAjax", ListaDeObjetos(consulta+teste));
		}
		
		new Thread();Thread.sleep(875);  
		return MV;
	}

	
	
	
	
	@RequestMapping("/consultaListasAjaxStatus")
	public ModelAndView consultaListasAjaxStatus(String termo) throws InterruptedException{
		MV.setViewName("producao/consultaListasAjax");
	
		if(termo.equals("selecione")){
			String empresaZA = " order by dataCriacao desc";
		    MV.addObject("listaAjax", ListaDeObjetos(consulta+empresaZA));
		}else{	
		    String empresaZA = " where l.idlistaEstatus.idlistaEstatus ="+termo+" order by dataCriacao desc";
		    MV.addObject("listaAjax", ListaDeObjetos(consulta+empresaZA));
		}
		
		new Thread();Thread.sleep(875);  
		return MV;
	}
	
	@RequestMapping("/consultaListasAjaxAvancada")
	public ModelAndView consultaListasAjaxAvancada(String idEmpresa, String base) throws InterruptedException{
		MV.setViewName("producao/consultaListasAvancada");
	
		
		if(idEmpresa.equals("selecione")){
			String empresaZA = " order by dataCriacao desc";
		    MV.addObject("listasAjax", ListaDeObjetos(consulta+empresaZA));
		}else{	
		    String empresaZA = " where l.idJob.empresa.idEmpresa ="+idEmpresa+" order by l.listaCod desc";
		    MV.addObject("listasAjax", teste(idEmpresa, base));
//		    MV.addObject("listasAjax", ListaDeObjetos(consulta+empresaZA));
		}
		
		new Thread();Thread.sleep(875);  
		return MV;
	}
	
	@RequestMapping("/consultaListasAjaxAvancadaLista")
	public ModelAndView consultaListasAjaxAvancadaLista(String idLista) throws InterruptedException{
		MV.setViewName("producao/consultaListasAvancadaListas");
	
		
		if(idLista.equals("selecione")){
			String empresaZA = " order by dataCriacao desc";
		    MV.addObject("listasAjax", ListaDeObjetos(consulta+empresaZA));
		}else{	
		    String empresaZA = " where l.idLista ="+idLista+" order by l.listaCod desc";
		    MV.addObject("lista", Objeto(consulta+empresaZA));
		}
		
		new Thread();Thread.sleep(875);  
		return MV;
	}
	
	@RequestMapping("/consultaAvancadaListasStatusAjaxlista")
	public ModelAndView consultaAvancadaListasStatusAjaxlista(Integer idlistaEstatus,Integer idEmpresa) throws InterruptedException{
		MV.setViewName("producao/consultaListasEstatusAjax");
	
		if(idlistaEstatus.equals("selecione")){
			String empresaZA = " order by dataCriacao desc";
		    MV.addObject("listasAjax", listaDAO.listasAjaxConsulta(empresaZA));
		}else{	
		    String empresaZA = " where l.idJob.empresa.idEmpresa="+idEmpresa+" and l.idlistaEstatus ="+idlistaEstatus+"  order by l.listaCod desc";
		    MV.addObject("listas", listaDAO.listasAjaxConsultaAvancada(empresaZA));
		}
		tempo();
		return MV;
	}

	
	/**
	 * Método auxiliar do filtro por Ajax da Lista de Produção
	 * 
	 */
	public List<ListaConsulta> teste(String termo, String base){
		
		
		String montaConsulta = "";
		
		
		if(base.equals("base")){
			montaConsulta = termo + " and dataCriacao > '2015-01-01'";
		}else{
			montaConsulta = termo + " and dataCriacao < '2015-01-01'";
		}
		
		
		
		List<String> codListas = listaDAO.t(montaConsulta);
		
		List<Integer> idList2 = new ArrayList<Integer>();
		
		
		for(int i =0; i< codListas.size();i++){
			String codList = codListas.get(i);
			List<Integer> codigos = listaDAO.retornaCodListas(codList);
			
			for(int x = 0; x < codigos.size();x++){
				idList2.add(codigos.get(x));
			}
		}

		
		List<ListaConsulta> listas = new ArrayList<ListaConsulta>();
		for(int i = 0; i < idList2.size(); i++){
			Integer idLista = idList2.get(i);
			ListaConsulta l = new ListaConsulta();

			l.setLista((String)listaDAO.condicao(idLista,"l.lista"));
			l.setIdLista((Integer)listaDAO.condicao(idLista,"l.idLista"));
			l.setRevisao((Integer)listaDAO.condicao(idLista,"l.revisao"));
			l.setEmpresa((String)listaDAO.condicao(idLista,"l.idJob.empresa.empresa"));
			l.setJob((String)listaDAO.condicao(idLista,"l.idJob.titulo"));
			l.setStatus((String)listaDAO.condicao(idLista,"l.idlistaEstatus.listaEstatus"));
			l.setListaCod((String)listaDAO.condicao(idLista,"l.listaCod"));
			listas.add(l);
		}
		
		return listas;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
