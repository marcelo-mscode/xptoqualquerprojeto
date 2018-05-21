package br.com.sysloccOficial.financeiro.analitico.novo;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sysloccOficial.NovoExcelBayer;
import br.com.sysloccOficial.financeiro.dao.NovoRelatorioCopiaMesAnteriorDAO;
import br.com.sysloccOficial.financeiro.model.EmprestimoBancario;
import br.com.sysloccOficial.financeiro.model.FinancAnalitico;



@Service
public class NovoRelatorioCopiaMesAnteriorService {
	
	@Autowired NovoRelatorioCopiaMesAnteriorDAO novoMesAnterior;
	
	public void copiaAnaliticoReflection(FinancAnalitico novoAnaliticoPersistido) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		copiaPorReflection(novoAnaliticoPersistido, "FinancImpostos");
		copiaPorReflection(novoAnaliticoPersistido, "FinancEscritorio");
		copiaPorReflection(novoAnaliticoPersistido, "FinancTelefone");
		copiaPorReflection(novoAnaliticoPersistido, "FinancFolhaPgto");
		copiaPorReflection(novoAnaliticoPersistido, "FinancOutrasDespesas");
		copiaTabelaEmprestimos(novoAnaliticoPersistido);
	}
	
	public void copiaPorReflection(FinancAnalitico novoAnaliticoPersistido,String nomeTabela) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException{
		
		String nomeDaTabela = "br.com.sysloccOficial.financeiro.model."+nomeTabela;
		
		List<Object> list = novoMesAnterior.copiaOutrosImpostosReflection(novoAnaliticoPersistido.getIdAnalitico(), nomeDaTabela);
		
		for (int i = 0; i < list.size(); i++) {
			
			Object novo = Class.forName(nomeDaTabela).newInstance();
			
			Field dataN = novo.getClass().getDeclaredField("data");
			dataN.setAccessible(true);
			
			if(nomeTabela == "FinancOutrasDespesas"){
				dataN.set(novo, (Date) Calendar.getInstance().getTime());
			}else{
				dataN.set(novo, (Calendar) Calendar.getInstance());
			}
			
			Field descricaoN = novo.getClass().getDeclaredField("descricao");
			descricaoN.setAccessible(true);
			descricaoN.set(novo, (String) descricaoN.get(list.get(i)));
			
			Field valorN = novo.getClass().getDeclaredField("valor");
			valorN.setAccessible(true);
			valorN.set(novo, (BigDecimal) valorN.get(list.get(i)));
			
			Field analiticoN = novo.getClass().getDeclaredField("analitico");
			analiticoN.setAccessible(true);
			analiticoN.set(novo, (FinancAnalitico) novoAnaliticoPersistido);
			
			Field fixoN = novo.getClass().getDeclaredField("fixo");
			fixoN.setAccessible(true);
			fixoN.set(novo, (boolean)fixoN.get(list.get(i)));
			
			novoMesAnterior.persisteFinancImpostoReflection(novo);
		}
	}
	
	public void copiaTabelaEmprestimos (FinancAnalitico novoAnaliticoPersistido){
		
		List<EmprestimoBancario> lista = novoMesAnterior.copiaTabelaEmprestimos(novoAnaliticoPersistido.getIdAnalitico());				
		
		System.out.println(novoAnaliticoPersistido.getIdAnalitico());
		
		for (int i = 0; i < lista.size(); i++) {
			
			if(lista.get(i).isPago() == false){
				EmprestimoBancario novo = new EmprestimoBancario();
				novo.setDataPrimeiroPagamento(lista.get(i).getDataPrimeiroPagamento());
				novo.setDescricao(lista.get(i).getDescricao());
				novo.setDiaMesPagamento(lista.get(i).getDiaMesPagamento());
				novo.setPago(lista.get(i).isPago());
				novo.setQuantidadeParcelas(lista.get(i).getQuantidadeParcelas());
				novo.setValorParcela(lista.get(i).getValorParcela());
				novo.setAnalitico(novoAnaliticoPersistido);
				novo.setBanco(lista.get(i).getBanco());
				novoMesAnterior.persisteEmprestimos(novo);
			}
		}
	}
}
