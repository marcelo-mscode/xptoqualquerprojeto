package br.com.sysloccOficial.Test.menuproducaoController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.sysloccOficial.model.prospeccao.Prospeccao;
import junit.framework.TestCase;

public class TestaRemoveItemRepetidoLista extends TestCase {

	
	public List<Object> removeValoresRepetidosListaQualquer(List<Object> p) {
		for (int i = 0; i < p.size(); i++) {
			Object a = p.get(i);
			for (int j = i+1; j < p.size(); j++) {
				Object b = p.get(j);
				if (a.equals(b)) {
					p.remove(j);
					j--;
				}
			}
		}
		return p;
	}
	
	
	@Test
	public void testa(){
		
		List<Prospeccao> p = new ArrayList<Prospeccao>();
		
		for (int i = 0; i < 10; i++) {
			Prospeccao pp = new Prospeccao();
			pp.setIdEmpresaTrans(i+1);
			pp.setIdProspeccao(i);
			p.add(pp);
		}

		for (int j = 0; j < 10; j++) {
			Prospeccao pp = new Prospeccao();
			pp.setIdProspeccao(j);
			pp.setIdEmpresaTrans(j+1);
			p.add(pp);
			j = j*2;
		}
		
		
		List<Object> list = new ArrayList<Object>(Arrays.asList(p));
		
		List<Object> list2 = removeValoresRepetidosListaQualquer(list);
			
		List<Prospeccao> list3 = new ArrayList<Prospeccao>();
		
		
		for (int i = 0; i < list2.size(); i++) {
			list3.add((Prospeccao) list2.get(i));
		}
		
		for (Prospeccao prospeccao : list3) {
			System.out.println(prospeccao.getIdProspeccao());
		}
		
	}
	
	
	
	
	
	
	
	
}
