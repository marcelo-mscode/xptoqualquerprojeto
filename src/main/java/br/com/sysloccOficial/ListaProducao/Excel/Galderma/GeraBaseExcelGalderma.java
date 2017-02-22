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
	
	
	public String constroiExcel(Integer idLista) throws IOException{
		
		excelGalderma = new XSSFWorkbook();
		
		BaseGeraExcel base = new BaseGeraExcel("C:/SYSLOC/upload/excel/"+"Galderma");
		FileOutputStream out = base.caminhoeNomeDoArquivo();
		String downloadExcel = base.caminhoDownloadExcel("Galderma","upload/upload/excel/"+"Galderma");
		
		XSSFSheet consolidadoCriado = GeraCorpoConsolidado.geraCorpoAbaConsolidado(consolidado, excelGalderma,"Consolidado");

		
		//Lógica para pegar lista de ids da planilha mãe e filhas
		
		
		
		
		List<Integer> idsListas = new ArrayList<Integer>();
		idsListas.add(2479);
		idsListas.add(2497);
		
		List<LinhasConsolidado> linhasParaConsolidado = new ArrayList<LinhasConsolidado>();
		for (int i = 0; i < idsListas.size(); i++) {
			
			int numCenario = i +1;
			
			// Monta dados para um cenário por idLista
			List<GrupoCategoriaGalderma> categoriasGalderma = montaGrupos.categoriasGalderma(idsListas.get(i));
			List<Grupo> listaGrupos  = montaGrupos.listaGruposNAOOpcionais(idsListas.get(i));
			List<CorpoGrupoCategoriaGalderma> montaGruposParaExcel = montaCorpoCategorias.montaGruposParaExcel(listaGrupos);
			
			//Cria o Cenários passando os dados		
			int linhasConsolidado = GeraCorpoCenarios.geraCorpoAbaCenarios(cenario, excelGalderma,"Cenário 0"+numCenario,montaGruposParaExcel,categoriasGalderma);
			//Monta dados para Consolidado
			LinhasConsolidado linhas = new LinhasConsolidado();
			linhas.setNomeAba("Cenário ");
			linhas.setUltimaLinhaCalculos(linhasConsolidado);
			linhasParaConsolidado.add(linhas);

		}
	
		CorpoConsolidadoGalderma.corpoPlanilhaConsolidado(excelGalderma, consolidadoCriado,linhasParaConsolidado);		

		
		//GeraCorpoCenarios.geraCorpoAbaCenariosOpcionais(opcionais, excelGalderma,"Opcionais");
		
		/*List<Grupo> listaGruposOpcionais = montaGrupos.listaGruposOpcionais(2439);
		
		if(listaGruposOpcionais.isEmpty()){
			
		}else{
			List<CorpoGrupoCategoriaGalderma> montaGruposParaExcelOpcionais = montaCorpoCategorias.montaGruposParaExcel(listaGruposOpcionais);
			
			
			int linhasConsolidado3 = GeraCorpoCenarios.geraCorpoAbaCenarios(cenario, excelGalderma,"Opcionais",montaGruposParaExcelOpcionais,categoriasGalderma);
			
			
			linhas2.setNomeAba("Opcionais");linhas2.setUltimaLinhaCalculos(linhasConsolidado3);
			linhasParaConsolidado.add(linhas2);
		}*/

		base.fechaPlanilha(excelGalderma,out);
		return downloadExcel;
	}
}
