package br.com.sysloccOficial.empresa.excel;

import java.io.IOException;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.empresas.services.MontaQueryCliente;
import br.com.sysloccOficial.model.Contato;


@Controller
public class EmpresaExcelController {

	@Autowired 	private EmpresaDAO empresaDAO;
	@Autowired 	private GeraExcelListagem geraExcel;
	
	
	
	@RequestMapping("excelEmpresas")
	public ModelAndView excelEmpresas(){
		ModelAndView MV = new ModelAndView("empresa/excelListagem/excelListagem");
	
		List<Object[]> listaEmpresas = empresaDAO.listaEmpresaTeste("cliente != 0");
		List<Object[]> listaContatos =  empresaDAO.listaContatos(listaEmpresas);
		List<Object[]> listaComunicador =  empresaDAO.listaComunicador(listaContatos);
		
		MV.addObject("empresa", listaEmpresas);
		MV.addObject("contato", listaContatos);
		MV.addObject("comunicador", listaComunicador);
		
	return MV;
	
	}
	
	@RequestMapping("buscaListagemEmpresasConsultas")
	public ModelAndView buscaListagemEmpresasConsultas(Integer tipo){
		
		ModelAndView MV = new ModelAndView("empresa/excelListagem/excelListagemAjax");
		
		String tipoConsulta = MontaQueryCliente.montaQueryCliente(tipo);
		
		List<Object[]> listaEmpresas = empresaDAO.listaEmpresaTeste(tipoConsulta);
		List<Object[]> listaContatos =  empresaDAO.listaContatos(listaEmpresas);
		List<Object[]> listaComunicador =  empresaDAO.listaComunicador(listaContatos);
		
		MV.addObject("empresa", listaEmpresas);
		MV.addObject("contato", listaContatos);
		MV.addObject("comunicador", listaComunicador);
		
		
		return MV;
	}
	
	

	@RequestMapping("geraExcelEmpresas")
	public ModelAndView geraExcelEmpresas(Integer tipo) throws IOException{
		ModelAndView MV = new ModelAndView("empresa/excelListagem/geraExcelEmpresas");
		
//		String downloadExcel = geraExcel.GeraListagem();
		String downloadExcel = geraExcel.GeraListagemNova(5);
		
		MV.addObject("nomeArquivo", downloadExcel);
		
		return MV;
	}
	
	
	
	
	@RequestMapping("testeListaEmpresa")
	public void testeListaEmpresa(){
		
		/*ModelAndView MV = new ModelAndView("empresa/excelListagem/testeListaEmpresas");
		List<Object[]> listaEmpresas = empresaDAO.listaEmpresaTeste();
		List<Object[]> listaContatos =  empresaDAO.listaContatos(listaEmpresas);
		List<Object[]> listaComunicador =  empresaDAO.listaComunicador(listaContatos);
		
		MV.addObject("empresa", listaEmpresas);
		MV.addObject("contato", listaContatos);
		MV.addObject("comunicador", listaComunicador);*/
	}
	
}
