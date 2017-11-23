package br.com.sysloccOficial.ListaProducao.Excel.Galderma;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sysloccOficial.Excel.BaseGeraExcel;
import br.com.sysloccOficial.model.Categoria;
import br.com.sysloccOficial.model.Grupo;
import br.com.sysloccOficial.model.GrupoCategoriaGalderma;
import br.com.sysloccOficial.model.Job;

@Component
public class GeraBaseExcelGalderma {
	
	@Autowired private AuxCarregaGrupos montaGrupos;
	@Autowired private MontaGruposCategoriasGalderma montaCorpoCategorias;
	
	
	static XSSFWorkbook excelGalderma;
	static XSSFSheet consolidado;
	static XSSFSheet cenario;
	static XSSFSheet cenario2;
	static XSSFSheet opcionais;
	
	
	public String constroiExcel(Integer idLista) throws IOException, ParseException{
		
		excelGalderma = new XSSFWorkbook();
		
		BaseGeraExcel base = new BaseGeraExcel("C:/SYSLOC/upload/excel/"+"Galderma");
		FileOutputStream out = base.caminhoeNomeDoArquivo();
		String downloadExcel = base.caminhoDownloadExcel("Galderma","upload/upload/excel/"+"Galderma");
		
		Job jobDaLista =  montaGrupos.pegaJob(idLista);
		
		
		List<String> infoGalderma = montaGrupos.listaInfoGalderma(idLista);
		

		XSSFSheet consolidadoCriado = GeraCorpoConsolidado.geraCorpoAbaConsolidado(consolidado, excelGalderma,"Consolidado",jobDaLista);

		
		//Lógica para pegar lista de ids da planilha mãe e filhas
		/**
		 * Quando não tem cenário criado pegar apenas o Id da planilha clicada
		 * 
		 */
		List<Integer> pegaIdsCenarios = montaCorpoCategorias.pegaIdsCenarios(idLista);
		
		
		
		
		
		List<LinhasConsolidado> linhasParaConsolidado = new ArrayList<LinhasConsolidado>();
		
		
		
		for (int i = 0; i < pegaIdsCenarios.size(); i++) {
			
			int numCenario = i +1;
			
			// Monta dados para um cenário por idLista
			/**
			 * Pega as Categorias Galderma usadas nos Grupos
			 */
			List<GrupoCategoriaGalderma> categoriasGalderma = montaGrupos.categoriasGalderma(pegaIdsCenarios.get(i));
			
			List<Categoria> categoriasDaLista = montaGrupos.categoriasDaLista(pegaIdsCenarios.get(i));
			
			
			
			
			/**
			 * Retorna lista de Grupos com categorias Galderma selecionada
			 */
			List<Grupo> listaGrupos  = montaGrupos.listaGruposNAOOpcionais(pegaIdsCenarios.get(i));
			
			//removeGruposVazios(categoriasGalderma, listaGrupos);
			  removeGruposVazios(categoriasDaLista, listaGrupos);
			
			/**
			 * Monta os Grupos com as informações necessárias para o Excel da Galderma
			 * 
			 */
			List<CorpoGrupoCategoriaGalderma> montaGruposParaExcel = montaCorpoCategorias.montaGruposParaExcel(listaGrupos);
			
			
			//Cria o Cenários passando os dados	
			/**
			 * Não recebe mais categorias Galderma, recebe agora as categorias da lista
			 */
			//int linhasConsolidado = GeraCorpoCenarios.geraCorpoAbaCenarios(cenario, excelGalderma,"Cenário 0"+numCenario,montaGruposParaExcel,categoriasGalderma,jobDaLista);
			int linhasConsolidado = GeraCorpoCenarios.geraCorpoAbaCenarios(cenario, excelGalderma,"Cenário 0"+numCenario,montaGruposParaExcel,categoriasDaLista,jobDaLista);
			
			
			//Monta dados para Consolidado
			LinhasConsolidado linhas = new LinhasConsolidado();
			linhas.setNomeAba("Cenário ");
			linhas.setUltimaLinhaCalculos(linhasConsolidado);
			linhasParaConsolidado.add(linhas);

		}
		
		/*CorpoConsolidadoGalderma.corpoPlanilhaConsolidado(excelGalderma, consolidadoCriado,linhasParaConsolidado);	*/	
		
		List<GrupoCategoriaGalderma> categoriasGaldermaOpc = montaGrupos.pegaTodasCategoriasGalderma();
		List<Grupo> listaGruposOpcionais = montaGrupos.listaGruposOpcionais(pegaIdsCenarios);
		
	
		
		LinhasConsolidado linhas2 = new LinhasConsolidado();

		if(listaGruposOpcionais.isEmpty()){
			
		}else{
			
			List<GrupoCategoriaGalderma> catOpc =  removeCategoriasOpcionaisGaldermaNaoUsadas(categoriasGaldermaOpc, listaGruposOpcionais);
			List<GrupoCategoriaGalderma> catOpc2 =  removeCategoriasOpcionaisGaldermaNaoUsadas(catOpc, listaGruposOpcionais);
			List<GrupoCategoriaGalderma> catOpc3 =  removeCategoriasOpcionaisGaldermaNaoUsadas(catOpc2, listaGruposOpcionais);
			
			List<CorpoGrupoCategoriaGalderma> montaGruposParaExcelOpcionais = montaCorpoCategorias.montaGruposParaExcel(listaGruposOpcionais);

			
/////////// MUDAR ESSE MÉTODO DEPOIS			
			int linhasConsolidado3 = GeraCorpoCenarios.geraCorpoAbaCenariosTESTE(cenario, excelGalderma,"Opcionais",montaGruposParaExcelOpcionais,catOpc,jobDaLista);
			
			
			linhas2.setNomeAba("Opcionais");
			linhas2.setUltimaLinhaCalculos(linhasConsolidado3);
			linhasParaConsolidado.add(linhas2);
		}

		
		CorpoConsolidadoGalderma.corpoPlanilhaConsolidado(excelGalderma, consolidadoCriado,linhasParaConsolidado,jobDaLista, infoGalderma);	
		
		base.fechaPlanilha(excelGalderma,out);
		return downloadExcel;
	}


	private List<GrupoCategoriaGalderma> removeCategoriasOpcionaisGaldermaNaoUsadas(List<GrupoCategoriaGalderma> categoriasGaldermaOpc,List<Grupo> listaGruposOpcionais) {
		
		for (int i = 0; i < categoriasGaldermaOpc.size(); i++) {
			boolean confere = false;
			
			int g = categoriasGaldermaOpc.get(i).getIdCategoriaGalderma();
	
			for (int j = 0; j < listaGruposOpcionais.size(); j++) {
				
				int l = 1;
				
				if(listaGruposOpcionais.get(j).getGrupoCategoriaGalderma() == null){
				
					System.out.println("Sou nulão !!!");
					
					GrupoCategoriaGalderma novo = new GrupoCategoriaGalderma();
					novo.setCategoria("SEM CATEGORIA");
					novo.setIdCategoriaGalderma(1);
					listaGruposOpcionais.get(j).setGrupoCategoriaGalderma(novo);
					
				}else{
					l = listaGruposOpcionais.get(j).getGrupoCategoriaGalderma().getIdCategoriaGalderma();
				}
				
				
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

	
	
	
	
	private void removeGruposVaziosGalderma(List<GrupoCategoriaGalderma> categoriasGalderma, List<Grupo> listaGrupos) {
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

	private void removeGruposVazios(List<Categoria> categoriasDaLista, List<Grupo> listaGruposDaCategoria) {
		for (int j = 0; j < listaGruposDaCategoria.size(); j++) {
			if(listaGruposDaCategoria.get(j).getProdutoGrupo().size() == 0){
				listaGruposDaCategoria.remove(j); 
			}
		}
		
		for (int j = 0; j < categoriasDaLista.size(); j++) {
			boolean verifica = false;
			for (int j2 = 0; j2 < listaGruposDaCategoria.size(); j2++) {
				
				/*System.out.println(categoriasDaLista.get(j).getIdcategoria());
				System.out.println(listaGruposDaCategoria.get(j2).getIdCategoria().getIdcategoria());
				*/
				if(categoriasDaLista.get(j).getIdcategoria() == listaGruposDaCategoria.get(j2).getIdCategoria().getIdcategoria()){
					verifica = true;
				}
			}
			if(verifica == false){
				categoriasDaLista.remove(j);
			}
		}
	}
}
