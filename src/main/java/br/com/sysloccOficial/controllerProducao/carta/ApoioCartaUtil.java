package br.com.sysloccOficial.controllerProducao.carta;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sysloccOficial.model.Comunicador;
import br.com.sysloccOficial.model.producao.CartaContFornecedor;

@Component
public class ApoioCartaUtil {

	public String preencheListaComunicadores(List<Comunicador> comunicador,CartaContFornecedor cartaContratacao, String comunicadores) {
		
		if(comunicador == null){
		
			return comunicadores;

		}else{
			
			for (int i = 0; i < comunicador.size(); i++) {
				
				if(comunicador.get(i).getComunicador().contains("@")){
					cartaContratacao.setFornecedorContatoEmail(comunicador.get(i).getComunicador());
				}else{
					comunicadores = comunicadores + comunicador.get(i).getComunicador()+" / ";
				}
				
			}
			return comunicadores;
		}
		
	}

	
	
	
}
