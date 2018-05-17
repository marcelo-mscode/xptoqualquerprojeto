package br.com.sysloccOficial.financeiro.analitico.novo;

import java.lang.reflect.Field;
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
			FinancImpostos novoFinanc = new FinancImpostos();
			novoFinanc.setData(Calendar.getInstance());
			novoFinanc.setDescricao(list.get(i).getDescricao());
			novoFinanc.setValor(list.get(i).getValor());
			novoFinanc.setAnalitico(analiticoNovo);
			novoFinanc.setFixo(list.get(i).isFixo());
			novoMesAnterior.persisteFinancImposto(novoFinanc);
		}
	}

	public void copiaOutrosImpostosReflection(Object analiticoNovo) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		
		Field idAnalitico = analiticoNovo.getClass().getDeclaredField("idAnalitico") ;
		idAnalitico.setAccessible(true);
		
		Integer idTeste = (Integer) idAnalitico.get(analiticoNovo);
		
		List<Object> list = novoMesAnterior.copiaOutrosImpostosReflection(idTeste,"FinancImpostos");
		
		for (int i = 0; i < list.size(); i++) {
			Field f = list.get(i).getClass().getDeclaredField("descricao");
			
			f.setAccessible(true);
			System.out.println(f.get(list.get(i)));
			
			FinancImpostos novoFinanc = new FinancImpostos();
			novoFinanc.setData(Calendar.getInstance());
			novoFinanc.setDescricao((String) f.get(list.get(i)));
			
			novoFinanc.setValor(list.get(i).getValor());
			novoFinanc.setAnalitico(analiticoNovo);
			novoFinanc.setFixo(list.get(i).isFixo());
			novoMesAnterior.persisteFinancImposto(novoFinanc);
			
			
			
			
			
		}
		
		
		/*
		Object teste = analiticoNovo;
		
		
		for (int i = 0; i < list.size(); i++) {
			FinancImpostos novoFinanc = new FinancImpostos();
			novoFinanc.setData(Calendar.getInstance());
			novoFinanc.setDescricao(list.get(i).getDescricao());
			novoFinanc.setValor(list.get(i).getValor());
			novoFinanc.setAnalitico(analiticoNovo);
			novoFinanc.setFixo(list.get(i).isFixo());
			novoMesAnterior.persisteFinancImposto(novoFinanc);
		}*/
	}
	
	
	
	

}
