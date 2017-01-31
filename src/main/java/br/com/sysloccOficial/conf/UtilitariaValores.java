package br.com.sysloccOficial.conf;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import br.com.caelum.stella.inwords.FormatoDeReal;
import br.com.caelum.stella.inwords.NumericToWordsConverter;

public class UtilitariaValores {
	
	
	// ----------------Converte Valores de Reais para Dolar ---------------------//
			public BigDecimal valoresEmReais(String valor){
				if (valor == null || valor.isEmpty()) {
					valor = "0";
					BigDecimal v = new BigDecimal(formataValores(valor));
					return v;
				} else {
					BigDecimal v = new BigDecimal(formataValores(valor));
					return v;
				}
			}
		
			public String formataValores(String i){
				String j;
				j = i.replace('.', ' ').replace(',', '.').replaceAll(" ", "");	
				return j;
			}

	// ----------------Converte Valores de Dolar para Real ---------------------//
			public String ConverteDolarParaReal(String ValorEmDolar){
				
				final Locale BRAZIL = new Locale("pt","BR");
				final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);    
				final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00",REAL); 
				
				Double ValorEmDolarParaDouble = Double.parseDouble(ValorEmDolar);
				
				String ValorConvertidoEmReal = mascaraDinheiro(ValorEmDolarParaDouble, DINHEIRO_REAL);
				ValorConvertidoEmReal = ValorConvertidoEmReal.replace("R$ ", "");
				
				return ValorConvertidoEmReal;
			}
			
			public static String mascaraDinheiro(double valor, DecimalFormat moeda){    
				         return moeda.format(valor);    
			} 

		
		public String converteValoresPorExtenso(BigDecimal valorEmDolar){
			
			NumericToWordsConverter converter;  
			converter = new NumericToWordsConverter(new FormatoDeReal());  
			double numero = valorEmDolar.doubleValue();  
			String extenso = converter.toWords(numero); 
			
			return extenso;
		}
			
		public String limpaConsultaRetorno(String limpa){
			String retorno ="";
			String g = limpa.replace("[", "");
			retorno = g.replace("]", "");
			return retorno;
		}

}
