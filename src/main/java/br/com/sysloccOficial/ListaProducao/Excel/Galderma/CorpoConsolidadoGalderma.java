package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.text.ParseException;
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
												List<LinhasConsolidado> linhasParaConsolidado,Job job,List<String> infoGalderma) throws ParseException{
		//Cabeçalho estático 
		CorpoConsolidadoGaldermaTopo.geraCabecalhoEstatico(excelGalderma, cenario, 17);
		int linhaComecaConteudo = 18;
		int numCenario = 1;
		int qtsPessoasDias = 0;
		
		for (int i = 0; i < job.getLocalEvento().size() ; i++) {
			qtsPessoasDias = job.getLocalEvento().get(i).getLocalEventoQtdPessoas();
		}
			
		for (int i = 0; i < linhasParaConsolidado.size(); i++) {
			
			
			String infoLista = "";
			
			if(infoGalderma == null){
				
			}else{
				infoLista = infoGalderma.get(i);
			}
			
			
			
			GeraConteudoConsolidado.geraConteudo(excelGalderma, cenario,linhasParaConsolidado.get(i).getNomeAba(),linhaComecaConteudo,
												 linhasParaConsolidado.get(i).getUltimaLinhaCalculos(),numCenario
												 ,qtsPessoasDias,infoLista, UtilitariaDatas.converteDateParaStringStatic(job.getPropostaData()));
			linhaComecaConteudo++;
			numCenario++;
		}
		
	}
}
