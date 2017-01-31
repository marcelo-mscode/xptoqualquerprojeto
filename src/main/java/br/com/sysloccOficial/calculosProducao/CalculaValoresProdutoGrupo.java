package br.com.sysloccOficial.calculosProducao;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.conf.Utilitaria;
import br.com.sysloccOficial.conf.UtilitariaConversoes;

public class CalculaValoresProdutoGrupo {
	
	@Autowired private Utilitaria util;
	@Autowired private UtilitariaConversoes utilConv;
	
	
	
	public String calculaValorFinal(BigDecimal custoTotal, BigDecimal q1,BigDecimal q2,BigDecimal ds, String precoUnitFinal){
		
		//Chama método que multiplica as quantidades pelo custo
		custoTotal = multiplicaQuantidadeComCusto(custoTotal, q1, q2, ds);
		
		String convertido = custoTotal.toString();
        convertido = util.ConverteDolarParaReal(convertido);
		return convertido;
	}
	
   public String naoSei(String custoItemunitario,BigDecimal custoTotal, BigDecimal q1,BigDecimal q2,BigDecimal ds,String precoUnitFinal){
	   
	   if(custoItemunitario.equals("zero")){
			custoItemunitario = "0,00";
			return custoItemunitario;
		}else{
		    BigDecimal CustoFinalTotal = new BigDecimal(util.formataValores(custoItemunitario)); 
			CustoFinalTotal = CustoFinalTotal.multiply(q1.multiply(q2).multiply(ds));
			String CustoFinalTotalconvertido = custoTotal.toString();
			CustoFinalTotalconvertido = util.ConverteDolarParaReal(CustoFinalTotalconvertido);
			return CustoFinalTotalconvertido;
		}
	   	
   }
	
   public String custoTotal(String custoItemunitario,BigDecimal q1,BigDecimal q2,BigDecimal ds){
	   BigDecimal CustounitarioTotal = new BigDecimal(util.formataValores(custoItemunitario));
       CustounitarioTotal = multiplicaQuantidadeComCusto(CustounitarioTotal, q1, q2, ds);
	   String CustounitarioTotalconvertido = CustounitarioTotal.toString();
	   CustounitarioTotalconvertido = util.ConverteDolarParaReal(CustounitarioTotalconvertido);
	   return CustounitarioTotalconvertido;
   }
   
   
   
   
   
   
   //Multiplica as quantidades pelo custo
   public BigDecimal multiplicaQuantidadeComCusto(BigDecimal paraCalcular,BigDecimal q1,BigDecimal q2, BigDecimal ds){
	   paraCalcular = paraCalcular.multiply(q1.multiply(q2).multiply(ds));
	   return paraCalcular;
	   
   }
   
   public String convertBigDecimalParaString(BigDecimal converter){
	   String convertido = converter.toString();
	   return convertido;
   }
   
   
	
}
