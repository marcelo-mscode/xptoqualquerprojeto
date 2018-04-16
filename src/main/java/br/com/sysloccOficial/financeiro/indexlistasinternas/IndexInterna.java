package br.com.sysloccOficial.financeiro.indexlistasinternas;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.financeiro.dao.InternaListasDAO;
import br.com.sysloccOficial.model.Lista;


@Controller
public class IndexInterna {

	@Autowired InternaListasDAO internaListasDAO;
	@Autowired private UtilitariaDatas utilDatas;

	@RequestMapping("listasInternas")
	public ModelAndView indexListasInternas(){
		ModelAndView MV = new ModelAndView("financeiro/interna/listasInternas/index");
		List<Lista> listaInternas = internaListasDAO.listasInternasComItensFechados();
		MV.addObject("listasInternas", listaInternas);
	
		MV.addObject("data", utilDatas.retornaAnoMesAtualEAnterioresCalendar());
		MV.addObject("mesesReferencia", utilDatas.mesesAno());
		MV.addObject("ano", Calendar.getInstance().get(Calendar.YEAR));
		
		return MV;
	}
	
}
