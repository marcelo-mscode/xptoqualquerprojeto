package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.BaseGeraExcel;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;

@Component
public class GeraBaseExcelGalderma {
	
	@Autowired private AuxCarregaGrupos montaGrupos;
	@Autowired private MontaGruposCategoriasGalderma montaCorpoCategorias;
	
	
	static XSSFWorkbook excelGalderma;
	static XSSFSheet consolidado;
	static XSSFSheet cenario;
	static XSSFSheet cenario2;
	static XSSFSheet opcionais;
	
	
	public String constroiExcel() throws IOException{
		
		excelGalderma = new XSSFWorkbook();
		
		BaseGeraExcel base = new BaseGeraExcel("C:/SYSLOC/upload/excel/"+"Galderma");
		FileOutputStream out = base.caminhoeNomeDoArquivo();
		String downloadExcel = base.caminhoDownloadExcel("Galderma","upload/upload/excel/"+"Galderma");
		
		List<GrupoCategoriaGalderma> categoriasGalderma = montaGrupos.categoriasGalderma(2439);
		
		List<Grupo> listaGrupos  = montaGrupos.listaGruposNAOOpcionais(2439);
		List<CorpoGrupoCategoriaGalderma> montaGruposParaExcel = montaCorpoCategorias.montaGruposParaExcel(listaGrupos);
		
		
		
		
		XSSFSheet consolidadoCriado = GeraCorpoConsolidado.geraCorpoAbaConsolidado(consolidado, excelGalderma,"Consolidado");

		LinhasConsolidado linhas = new LinhasConsolidado();
		LinhasConsolidado linhas1 = new LinhasConsolidado();
		LinhasConsolidado linhas2 = new LinhasConsolidado();
		List<LinhasConsolidado> linhasParaConsolidado = new ArrayList<LinhasConsolidado>();
		
		int linhasConsolidado = GeraCorpoCenarios.geraCorpoAbaCenarios(cenario, excelGalderma,"Cenário 01",montaGruposParaExcel,categoriasGalderma);
		int linhasConsolidado2 = GeraCorpoCenarios.geraCorpoAbaCenarios(cenario, excelGalderma,"Cenário 02",montaGruposParaExcel,categoriasGalderma);

		
		List<Grupo> listaGruposOpcionais = montaGrupos.listaGruposOpcionais(2439);
		
		if(listaGruposOpcionais.isEmpty()){
			
		}else{
			List<CorpoGrupoCategoriaGalderma> montaGruposParaExcelOpcionais = montaCorpoCategorias.montaGruposParaExcel(listaGruposOpcionais);
			
			
			int linhasConsolidado3 = GeraCorpoCenarios.geraCorpoAbaCenarios(cenario, excelGalderma,"Opcionais",montaGruposParaExcelOpcionais,categoriasGalderma);
			
			
			linhas2.setNomeAba("Opcionais");linhas2.setUltimaLinhaCalculos(linhasConsolidado3);
			linhasParaConsolidado.add(linhas2);
		}
		
		linhas.setNomeAba("Cenário ");linhas.setUltimaLinhaCalculos(linhasConsolidado);
		linhas1.setNomeAba("Cenário ");linhas1.setUltimaLinhaCalculos(linhasConsolidado2);
		
		linhasParaConsolidado.add(linhas);
		linhasParaConsolidado.add(linhas1);
		
		
	
		
		CorpoConsolidadoGalderma.corpoPlanilhaConsolidado(excelGalderma, consolidadoCriado,linhasParaConsolidado);		
		
		

		//GeraCorpoCenarios.geraCorpoAbaCenariosOpcionais(opcionais, excelGalderma,"Opcionais");
		
		base.fechaPlanilha(excelGalderma,out);
		
		return downloadExcel;
	}
}
