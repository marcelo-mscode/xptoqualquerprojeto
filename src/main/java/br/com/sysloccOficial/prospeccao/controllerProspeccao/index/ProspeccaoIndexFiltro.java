package br.com.sysloccOficial.prospeccao.controllerProspeccao.index;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.model.prospeccao.FiltraProspeccao;
import br.com.sysloccOficial.model.prospeccao.Prospeccao;
import br.com.sysloccOficial.prospeccao.dao.ProspeccaoDAO;

@Controller
public class ProspeccaoIndexFiltro {

	@Autowired ProspeccaoDAO prospeccaoDAO;
	@Autowired Utilitaria util;
	
	@RequestMapping("filtraProspeccao")
	public ModelAndView filtraProspeccao(FiltraProspeccao f,HttpSession session) throws InterruptedException, ParseException{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("prospeccao/index/filtroProspeccao/filtroProspeccao");	
		
		String consulta = "";
		
		consulta = f.testaCampos(f, consulta);
		
		if(consulta.equals(null) || consulta == ""){
			
		}else{
			
			List<Prospeccao> prospeccoes2 = 
			f.removeValoresRepetidosLista(prospeccaoDAO.filtrolistarProspeccao2(consulta,f.isDataConfere(),f.getQtoDias()));
		
			MV.addObject("prospeccoes", prospeccoes2);
			
			if(f.getQtoDias().equals("")){
	
			}else{
				MV.addObject("qtdDias", f.getQtoDias());
			}
		}
		
		session.setAttribute("consultaInteracao", consulta);
		session.setAttribute("qtdDias", f.getQtoDias());
		session.setAttribute("dataConfere", f.isDataConfere());
		
		new Thread(); Thread.sleep(1000);  
		
		return MV;
	}
	
}
