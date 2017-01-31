package br.com.sysloccOficial.listaproducao.testedeterminaquantidade;

import br.com.sysloccOficial.model.DeterminaQuantidades;
import br.com.sysloccOficial.model.Grupo;

public class VerificaQuantidadeDeterminante {
	
	Grupo grupo;

	public VerificaQuantidadeDeterminante(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public boolean verifica(DeterminaQuantidades dt){
		if(dt.getGrupo().getIdgrupo() == grupo.getIdgrupo())
			return true;
		return false;
	}
	
	
	
}	

