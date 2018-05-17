package br.com.sysloccOficial.financeiro.analitico.novo;

import java.lang.reflect.Field;
import java.math.BigDecimal;
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

	public void copiaOutrosImpostosReflection(Object analiticoNovo) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		
//		String nomeDaTabela = "FinancImpostos";
		String nomeDaTabela = "br.com.sysloccOficial.financeiro.model.FinancImpostos";
		
		Field idAnalitico = analiticoNovo.getClass().getDeclaredField("idAnalitico") ;
		idAnalitico.setAccessible(true);
		
		Integer idTeste = (Integer) idAnalitico.get(analiticoNovo);
		
		List<Object> list = novoMesAnterior.copiaOutrosImpostosReflection(idTeste, nomeDaTabela);
		
		for (int i = 0; i < list.size(); i++) {
			
			Object novo = Class.forName(nomeDaTabela).newInstance();
		
			Field descricaoN = novo.getClass().getDeclaredField("descricao");
			descricaoN.setAccessible(true);
			descricaoN.set(novo, (String) descricaoN.get(list.get(i)));
			
			Field valorN = novo.getClass().getDeclaredField("valor");
			valorN.setAccessible(true);
			valorN.set(novo, (BigDecimal) valorN.get(list.get(i)));
			
			Field analiticoN = novo.getClass().getDeclaredField("analitico");
			analiticoN.setAccessible(true);
			analiticoN.set(novo, (FinancAnalitico) analiticoN.get(list.get(i)));
			
			Field fixoN = novo.getClass().getDeclaredField("fixo");
			fixoN.setAccessible(true);
			fixoN.set(novo, (boolean)fixoN.get(list.get(i)));
			
			novoMesAnterior.persisteFinancImpostoReflection(novo);
			
/*			Field valorN = novo.getClass().getDeclaredField("valor");valorN.setAccessible(true);
			
			
			novo.setData(Calendar.getInstance());
			novo.setValor((BigDecimal) valor.get(list.get(i)));
			novo.setFixo((boolean)fixo.get(list.get(i)));
			novoMesAnterior.persisteFinancImposto(novoFinanc);
*/			
			
			
			/*FinancImpostos novoFinanc = new FinancImpostos();

			
			Field descricao = list.get(i).getClass().getDeclaredField("descricao");descricao.setAccessible(true);
			Field valor = list.get(i).getClass().getDeclaredField("valor");valor.setAccessible(true);
			Field fixo = list.get(i).getClass().getDeclaredField("fixo");fixo.setAccessible(true);
			
			System.out.println(descricao.get(list.get(i)));
			
			novoFinanc.setData(Calendar.getInstance());
			novoFinanc.setDescricao((String) descricao.get(list.get(i)));
			novoFinanc.setValor((BigDecimal) valor.get(list.get(i)));
			novoFinanc.setFixo((boolean)fixo.get(list.get(i)));
			novoFinanc.setAnalitico((FinancAnalitico)analiticoNovo);
			novoMesAnterior.persisteFinancImposto(novoFinanc);*/
			
		}
	}
	
}
