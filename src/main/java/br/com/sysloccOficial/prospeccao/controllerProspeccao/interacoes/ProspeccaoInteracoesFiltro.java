package br.com.sysloccOficial.prospeccao.controllerProspeccao.interacoes;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.model.prospeccao.FiltraInteracao;
import br.com.sysloccOficial.model.prospeccao.InteracaoProspeccao;
import br.com.sysloccOficial.prospeccao.dao.InteracoesDAO;

@Controller
public class ProspeccaoInteracoesFiltro {
	
	@Autowired private InteracoesDAO interacoesDAO;
	
	
	@RequestMapping("filtraInteracao")
	public ModelAndView filtraInteracao(FiltraInteracao f,HttpSession session) throws InterruptedException{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("prospeccao/interacao/filtroInteracao/filtroInteracao");	
		
		String consulta = "";
		
		consulta = f.testaEmpresa(consulta, f);
		consulta = f.testaTitulo(consulta, f);
		consulta = f.testaData(consulta, f);
		consulta = f.testaConcluido(consulta, f.isConcluidos());
		
		if(consulta.equals(null) || consulta == ""){
			
		}else{
			List<InteracaoProspeccao> interacoes = interacoesDAO.filtrolistar(consulta,f.isDataConfere(),f.getQtoDias());
			MV.addObject("interacoes", interacoes);
			
			if(f.getQtoDias().equals("")){
			}else{
				MV.addObject("qtdDias", f.getQtoDias());
			}
		}
		
		session.setAttribute("consultaInteracao", consulta);
		session.setAttribute("qtdDias", f.getQtoDias());
		session.setAttribute("dataConfere", f.isDataConfere());
		
		new Thread();
		Thread.sleep(1000);  
		
		return MV;
	}
	@RequestMapping("preencheTitulo")
	public ModelAndView preencheTitulo(HttpSession session){
		ModelAndView MV = new ModelAndView();
		MV.setViewName("prospeccao/interacao/filtroInteracao/preencheTitulo");	
		
		String consulta = (String)session.getAttribute("consultaInteracao");
		String qtdDias = (String)session.getAttribute("qtdDias");
		boolean dataConfere = (boolean)session.getAttribute("dataConfere");
		
		if(consulta.equals(null) || consulta == ""){
		}else{
			List<InteracaoProspeccao> interacoes = interacoesDAO.filtrolistar(consulta,dataConfere,qtdDias);
			session.setAttribute("consultaInteracao", "");
			session.setAttribute("dataConfere", "");
			session.setAttribute("qtdDias", "");
			MV.addObject("titulos", interacoes);
			
			if(qtdDias.equals("")){
			}else{
				MV.addObject("qtdDias", qtdDias);
			}
		}
		
		return MV;
	}
	
	
	
	
	
}
