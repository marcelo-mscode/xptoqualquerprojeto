package br.com.sysloccOficial.financeiro.analitico.novo;

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
		
		int mesReferenciaAnterior = analiticoNovo.getMesReferencia()  - 1;
		
		String buscaImpostos = "FROM FinancImpostos where analitico = "+mesReferenciaAnterior+" and fixo = true";
		
		
		List<FinancImpostos> list = novoMesAnterior.copiaOutrosImpostos(buscaImpostos, analiticoNovo.getIdAnalitico());
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getDescricao());
		}
		
		
	}
	
	
	
	

}
