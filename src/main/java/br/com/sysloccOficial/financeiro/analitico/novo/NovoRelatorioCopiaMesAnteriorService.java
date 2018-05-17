package br.com.sysloccOficial.financeiro.analitico.novo;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.financeiro.dao.NovoRelatorioCopiaMesAnteriorDAO;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;
import br.com.sysloccOficial.financeiro.model.FinancImpostos;



@Service
public class NovoRelatorioCopiaMesAnteriorService {
	
	@Autowired NovoRelatorioCopiaMesAnteriorDAO novoMesAnterior;
	
	public void copiaOutrosImpostos(FinancAnalitico analiticoNovo){
		List<FinancImpostos> list = novoMesAnterior.copiaOutrosImpostos(analiticoNovo.getIdAnalitico());
	
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setAnalitico(analiticoNovo);
			list.get(i).setData(Calendar.getInstance());
			novoMesAnterior.persisteFinancImposto(list.get(i));
		}
	}
	
	
	
	

}
