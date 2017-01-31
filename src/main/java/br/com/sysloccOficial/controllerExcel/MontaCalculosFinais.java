package br.com.sysloccOficial.controllerExcel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sysloccOficial.model.LocalEvento;

@Repository
@Transactional
public class MontaCalculosFinais {
	
	@PersistenceContext	private EntityManager manager;
	@Autowired private TextosStaticosExcel textosEstaticos;
	

	/**
	 * Método para Inserir os dados de cálculos finais na Planilha
	 * 
	 */
	public void calculosFinais(Integer idLista,XSSFRow rowSubTotal, XSSFWorkbook workbook, int posicaoLinha,XSSFSheet planilha){
		
		CalculoValoresFinais calcFinal = new CalculoValoresFinais();
		
		Query q = manager.createQuery("select sum(grupoValorIncideImposto) from Grupo where idLista="+idLista
		+" and opcional = 0 and incideAdministracao = true");
		
		BigDecimal sss = (BigDecimal) q.getSingleResult();
		
		
		calcFinal.setTotalServicosSubcontratados(sss);
		
		montaCelulasValores(workbook, rowSubTotal, 7, calcFinal.getTotalServicosSubcontratados());
		
		
		//cell.setCellValue(calcFinal.getTotalServicosSubcontratados());
		textosEstaticos.celulasEmBranco(0,6, rowSubTotal, workbook);
		
	//	escreveRodapePlanilha(idLista, workbook, rowSubTotal,posicaoLinha,planilha);
	
	}
	
	public void escreveRodapePlanilha(Integer idLista,XSSFWorkbook workbook,XSSFRow rowSubTotal, int linha,XSSFSheet planilha /*,RodapePanilha rodape*/){
		
		XSSFRow rowCalculosFinais = planilha.createRow(linha+5);
		Cell cell = rowCalculosFinais.createCell(7);
		cell.setCellStyle(AuxExcelEstilos.estiloCategoria(workbook,0,255,0));
		cell.setCellValue("Segunda linha");
	
	}

	/**
	 * Método para Criar as células e já imprimir na tabela
	 * 
	 */
	public void montaCelulasValores(XSSFWorkbook workbook,XSSFRow rowSubTotal,int posicao, BigDecimal valor){
	
			double num = valor.doubleValue();
			DecimalFormat df = new DecimalFormat();  
	        df.applyPattern("#,##0.00");
		
	        Cell cell = rowSubTotal.createCell(posicao);
			cell.setCellStyle(AuxExcelEstilos.SubTotalGeral(workbook));
			cell.setCellValue(df.format(num));
	}	
	
	
	
	
	/**
	 * Método para somar todos os Custos da Agência da Lista
	 * 
	 * @return BigDecimal
	 */	
	public BigDecimal SomaSubContratados(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		try {
			TypedQuery<BigDecimal> bg = manager.createQuery(
					"select sum(grupoValorIncideImposto) from Grupo where idLista="+idLista
					+" and opcional = 0 and incideAdministracao = true", BigDecimal.class);
			result = bg.getSingleResult();
			return result;
		} catch (Exception e) {
			return result;
		}
	}
	
	/**
	 * Método para somar todos os valores de Faturamento direto dos Grupos
	 * 
	 * Faturamento direto são os valores de grupoValorNaoIncideImposto que
	 * incidem administração( incideAdministracao = true )
	 * 
	 * @param idLista
	 * @return
	 */
	public BigDecimal SomaFatDireto(Integer idLista){
		BigDecimal result = new BigDecimal("0");
		TypedQuery<BigDecimal> bg = manager.createQuery(
				"select sum(grupoValorNaoIncideImposto) from Grupo  where idlista = "+idLista+""
	  		  + " and opcional = 0 and incideAdministracao = true", BigDecimal.class);
		
		if(bg.getSingleResult() == null){
			return result;
		}else{
			result = bg.getSingleResult();
			return result;
		}
	}
	

}
