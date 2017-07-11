package br.com.sysloccOficial.conf;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class UtilitariaConversoes {

// ---------------- Converte Long para Integer ---------------------//
	public Integer converteLongParaInteger(Long num){
		Integer hora= Integer.valueOf(num.toString());  
		return hora;
	}
	
// ---------------- Converte DateParaCalendar ---------------------//
	public static Calendar DateToCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	}
// ---------------- Converte de String para Calendar ----------------------------//	
	public static Calendar conveterStringParaCalendar(String data){ 
		 try {
				/*data = data + " 00:00:00";*/
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(data));
				return cal;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
	}
// ---------------- Converte de String para Date ----------------------------//	
	public Date converteDatasStringParaCalendar(String data){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new Locale("pt", "BR"));
		try {
			c.setTime(df.parse(data));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return c.getTime();
	}

// ---------------- Converte Valores de Dolar para Real ---------------------//
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
	
	public double converterStringParaDouble(String s){
		double d = Double.parseDouble(s);
		return d;
	}
	
	public String converterVirgulaParaPonto(String s){
		return s.replace(",", ".");
	}
	
	public double converteBigDecimalParaDouble(BigDecimal b){
		double num = b.doubleValue();
		JOptionPane.showMessageDialog(null, ""+num);
		return num;
	}
	
	public BigDecimal converteDoubleParaBigDecimal(double valor){
		BigDecimal nova = new BigDecimal(valor);
		return null;
	}
	
}
