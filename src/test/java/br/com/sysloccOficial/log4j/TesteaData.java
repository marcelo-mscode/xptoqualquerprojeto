package br.com.sysloccOficial.log4j;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import br.com.sysloccOficial.conf.Utilitaria;

public class TesteaData {

	
	
	
	@Test
	public void test() throws ParseException, IOException {
	
		try {
			int sa =10;
			int b =0;
			
			int cs = sa/b;
			
		} catch (Exception e) {
			Utilitaria util = new Utilitaria();
			util.gravaErros(e);
		}
		
	}

}
