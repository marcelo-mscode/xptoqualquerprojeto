package br.com.sysloccOficial.financeiro.analitico.novo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysloccOficial.financeiro.dao.NovoRelatorioCopiaMesAnteriorDAO;



@Service
public class NovoRelatorioCopiaMesAnteriorService {
	
	@Autowired NovoRelatorioCopiaMesAnteriorDAO novoMesAnterior;
	
	public void copiaOutrosImpostos(int idAnalitico){
		
		System.out.println("Copiando OutrosImpostos");
		
		novoMesAnterior.copiaOutrosImpostos(idAnalitico);
		
		
	}
	
	
	
	

}
