package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
		
		
		List<Grupo> listaGrupos  = montaGrupos.listaGruposNAOOpcionais(2439);
		List<GrupoCategoriaGalderma> categoriasGalderma = montaGrupos.categoriasGalderma();
	
		List<CorpoGrupoCategoriaGalderma> montaGruposParaExcel = montaCorpoCategorias.montaGruposParaExcel(listaGrupos);
		
		
		GeraCorpoCenarios.geraCorpoAbaCenarios(cenario, excelGalderma,"Cenário 01",montaGruposParaExcel,categoriasGalderma);
		
		
		
		//GeraCorpoConsolidado.geraCorpoAbaConsolidado(consolidado, excelGalderma,"Consolidado");

		/*GeraCorpoCenarios.geraCorpoAbaCenarios(cenario2, excelGalderma,"Cenário 02");
		GeraCorpoCenarios.geraCorpoAbaCenariosOpcionais(opcionais, excelGalderma,"Opcionais");*/
		
		base.fechaPlanilha(excelGalderma,out);
		
		return downloadExcel;
	}
}
