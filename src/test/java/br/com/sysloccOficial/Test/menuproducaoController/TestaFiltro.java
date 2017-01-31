package br.com.sysloccOficial.Test.menuproducaoController;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.sysloccOficial.model.prospeccao.FiltraInteracao;

public class TestaFiltro {

	@Test
	public void test() {
		
		String consulta ="";
		
		FiltraInteracao f = new FiltraInteracao();
		f.setIdEmpresa("3254");
		f.setTitulo("Teste de Prospeccao");
		
		consulta = f.testaEmpresa(consulta, f);
		System.out.println("Consulta: "+consulta);

		consulta = f.testaTitulo(consulta, f);
		System.out.println("Consulta: "+consulta);
		
	//	assertEquals("", f.testaTitulo(consulta, f));
		
	}

	/*private String testaTitulo(String consulta, FiltraInteracao f) {
		if(f.getTitulo() != null){
			consulta = consulta + f.getTitulo();
		}
		return consulta;
	}*/

	/*private String testaEmpresa(String consulta, FiltraInteracao f) {
		if(f.getIdEmpresa()!=null){
			consulta = f.getIdEmpresa();
		}
		return consulta;
	}*/

}
