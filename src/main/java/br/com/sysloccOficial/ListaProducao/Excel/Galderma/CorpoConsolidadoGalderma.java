package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.conf.UtilitariaDatas;
import br.com.sysloccOficial.model.Job;
import br.com.sysloccOficial.model.LocalEvento;

@Component
public class CorpoConsolidadoGalderma {
	
	public static void corpoPlanilhaConsolidado(XSSFWorkbook excelGalderma,XSSFSheet cenario,
												List<LinhasConsolidado> linhasParaConsolidado,Job job,List<String> infoGalderma){
		//Cabeçalho estático 
		CorpoConsolidadoGaldermaTopo.geraCabecalhoEstatico(excelGalderma, cenario, 17);
		int linhaComecaConteudo = 18;
		int numCenario = 1;
		int qtsPessoasDias = 0;
		String deadLine = "";
		
		
		try {
			deadLine = UtilitariaDatas.converteDateParaStringStatic(job.getPropostaData());
		} catch (Exception e) {
			
		}
		
		for (int i = 0; i < job.getLocalEvento().size() ; i++) {
			qtsPessoasDias = job.getLocalEvento().get(i).getLocalEventoQtdPessoas();
		}
			
		for (int i = 0; i < linhasParaConsolidado.size(); i++) {
			GeraConteudoConsolidado.geraConteudo(excelGalderma, cenario,linhasParaConsolidado.get(i).getNomeAba(),linhaComecaConteudo,
												 linhasParaConsolidado.get(i).getUltimaLinhaCalculos(),numCenario
												 ,qtsPessoasDias,infoGalderma.get(i));
			linhaComecaConteudo++;
			numCenario++;
		}
		
	}
}
