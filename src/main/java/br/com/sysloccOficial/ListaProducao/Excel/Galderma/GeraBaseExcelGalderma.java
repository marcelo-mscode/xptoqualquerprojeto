package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
		List<Integer> pegaIdsCenarios = montaCorpoCategorias.pegaIdsCenarios(idLista);
		
		List<LinhasConsolidado> linhasParaConsolidado = new ArrayList<LinhasConsolidado>();
		for (int i = 0; i < pegaIdsCenarios.size(); i++) {
			
			int numCenario = i +1;
			
			// Monta dados para um cenário por idLista
			List<GrupoCategoriaGalderma> categoriasGalderma = montaGrupos.categoriasGalderma(pegaIdsCenarios.get(i));
			List<Grupo> listaGrupos  = montaGrupos.listaGruposNAOOpcionais(pegaIdsCenarios.get(i));
			
			removeGruposVazios(categoriasGalderma, listaGrupos);
			
			List<CorpoGrupoCategoriaGalderma> montaGruposParaExcel = montaCorpoCategorias.montaGruposParaExcel(listaGrupos);
			
			//Cria o Cenários passando os dados		
			int linhasConsolidado = GeraCorpoCenarios.geraCorpoAbaCenarios(cenario, excelGalderma,"Cenário 0"+numCenario,montaGruposParaExcel,categoriasGalderma);
			//Monta dados para Consolidado
			LinhasConsolidado linhas = new LinhasConsolidado();
			linhas.setNomeAba("Cenário ");
			linhas.setUltimaLinhaCalculos(linhasConsolidado);
			linhasParaConsolidado.add(linhas);

		}
		
		
		
		/*CorpoConsolidadoGalderma.corpoPlanilhaConsolidado(excelGalderma, consolidadoCriado,linhasParaConsolidado);	*/	
		
		List<GrupoCategoriaGalderma> categoriasGaldermaOpc = montaGrupos.pegaTodasCategoriasGalderma();
		List<Grupo> listaGruposOpcionais = montaGrupos.listaGruposOpcionais(pegaIdsCenarios);
		
		
		
		List<GrupoCategoriaGalderma> catOpc =  removeCategoriasOpcionais(categoriasGaldermaOpc, listaGruposOpcionais);
		List<GrupoCategoriaGalderma> catOpc2 =  removeCategoriasOpcionais(catOpc, listaGruposOpcionais);
		List<GrupoCategoriaGalderma> catOpc3 =  removeCategoriasOpcionais(catOpc2, listaGruposOpcionais);
	
		
		LinhasConsolidado linhas2 = new LinhasConsolidado();
		if(listaGruposOpcionais.isEmpty()){
			
		}else{
			
			
			List<CorpoGrupoCategoriaGalderma> montaGruposParaExcelOpcionais = montaCorpoCategorias.montaGruposParaExcel(listaGruposOpcionais);
			
			int linhasConsolidado3 = GeraCorpoCenarios.geraCorpoAbaCenarios(cenario, excelGalderma,"Opcionais",montaGruposParaExcelOpcionais,catOpc3);
			
			
			linhas2.setNomeAba("Opcionais");
			linhas2.setUltimaLinhaCalculos(linhasConsolidado3);
			linhasParaConsolidado.add(linhas2);
		}

		
		CorpoConsolidadoGalderma.corpoPlanilhaConsolidado(excelGalderma, consolidadoCriado,linhasParaConsolidado);	
		
		base.fechaPlanilha(excelGalderma,out);
		return downloadExcel;
	}


	private List<GrupoCategoriaGalderma> removeCategoriasOpcionais(List<GrupoCategoriaGalderma> categoriasGaldermaOpc,List<Grupo> listaGruposOpcionais) {
		
		for (int i = 0; i < categoriasGaldermaOpc.size(); i++) {
			boolean confere = false;
			
			int g = categoriasGaldermaOpc.get(i).getIdCategoriaGalderma();
			for (int j = 0; j < listaGruposOpcionais.size(); j++) {
				int l = listaGruposOpcionais.get(j).getGrupoCategoriaGalderma().getIdCategoriaGalderma();
				if(g == l){
					confere = true;
				}
			}
						
			if(confere == false){
				categoriasGaldermaOpc.remove(i);
			}
		}
		
		return categoriasGaldermaOpc;
	}

	
	
	
	
	private void removeGruposVazios(List<GrupoCategoriaGalderma> categoriasGalderma,
			List<Grupo> listaGrupos) {
		for (int j = 0; j < listaGrupos.size(); j++) {
			if(listaGrupos.get(j).getProdutoGrupo().size() == 0){
							listaGrupos.remove(j); 
			}
		}
		
		for (int j = 0; j < categoriasGalderma.size(); j++) {
			boolean verifica = false;
			for (int j2 = 0; j2 < listaGrupos.size(); j2++) {
				if(categoriasGalderma.get(j).getIdCategoriaGalderma() == listaGrupos.get(j2).getGrupoCategoriaGalderma().getIdCategoriaGalderma()){
					verifica = true;
				}
			}
			if(verifica == false){
				categoriasGalderma.remove(j);
			}
		}
	}
}
