package br.com.sysloccOficial.financeiro.atualizaInterna;

import br.com.sysloccOficial.model.RelatorioEventos;

public class VerificaSeRelatorioEventoExiste {

	
	public static Integer verificaSeRelatorioEventoExiste(RelatorioEventos relatorio, RelatorioEventos novoRelatorio) {
		Integer idRelatorioParaGiroTelefone;

		if(relatorio == null){
			idRelatorioParaGiroTelefone = novoRelatorio.getIdRelatorioEvento();
		}else{
			idRelatorioParaGiroTelefone = relatorio.getIdRelatorioEvento();
		}
		return idRelatorioParaGiroTelefone;
	}
	
	
}
