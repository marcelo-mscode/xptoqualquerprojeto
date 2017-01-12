package br.com.sysloccOficial.conf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sysloccOficial.daos.JobStatusDAO;
import br.com.sysloccOficial.daos.ListaDAO;

public class UtilitariaDatas {
	@Autowired private JobStatusDAO statusDAO;
	@Autowired private ListaDAO listaDAO;
	
	
// ----------------Converte Valores de Reais para Dolar ---------------------//
				
	public static String converterInteiroParaMes(String mes)
			throws ParseException {
		// Instância o SimpleDateFormat com o formato MM (esse formato indica o
		// formato de numeros).
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		// Faz o parse ("transforma") a String que contêm o mês em um Date.
		Date mesDate = sdf.parse(mes);
		// Altera o pattern do SimpleDateFormat.
		sdf.applyPattern("MMMM");
		// Retorna o nome do Mês.
		return sdf.format(mesDate);
	}
		

// ---------------- Converte de String para Date ----------------------------//	
	public Date formataDatasStringParaCalendar(String data){
		
		String novaData = data.replace(",", "");
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
		try {
			c.setTime(df.parse(novaData));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return c.getTime();
	}

// -------------------- Formata hora ------------------------------------//	
	public Date formataHoraStringParaCalendar(String hora){
	     	Calendar c  = Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("HH:mm");
			try {
				c.setTime(df.parse(hora));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	return c.getTime();
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
public ArrayList<Integer> FormataDataJodaGenerica(Date data) throws ParseException{
		
		Calendar datahojeCalendar = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String a = s.format(datahojeCalendar.getTime());
		
        
     // constrói a data de Hoje
        DateFormat fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataHoje = (Date)fm.parse(a);
        
        
	    Date dataProposta = data;

	    SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String dataFormatada = ss.format(dataProposta.getTime());
	    
	    fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date data2 = (Date)fm.parse(dataFormatada);
		
	    long segundos = (data2.getTime() - dataHoje.getTime()) / 1000;
	    
	    int dias = (int)Math.floor(segundos / 86400);
	    segundos -= dias * 86400;
	      
	    int horas = (int)Math.floor(segundos / 3600);
	    segundos -= horas * 3600;
	      
	    int minutos = (int)Math.floor(segundos / 60);
	    segundos -= minutos * 60;
		
	    
	    
        ArrayList<Integer> prazoData = new ArrayList<Integer>();
        prazoData.add(minutos);
        prazoData.add(horas);
        prazoData.add(dias);

        
        return prazoData;
	}

	
	public static ArrayList<Integer> diferencaEmMinutos(Date dataInicial, Date dataFinal){  
		
		/** 
		 * Calcula a diferença de duas datas em minutos 
		 * <br> 
		 * <b>Importante:</b> Quando realiza a diferença em minutos entre duas datas, este método considera os segundos restantes e os converte em fração de minutos. 
		 * @param dataInicial 
		 * @param dataFinal 
		 * @return quantidade de minutos existentes entre a dataInicial e dataFinal. 
		 */  	
		
	    double result = 0;  
	    long diferenca = dataFinal.getTime() - dataInicial.getTime();  
	    
//	    JOptionPane.showMessageDialog(null, ""+diferenca);    
	    double diferencaEmMinutos = (diferenca /1000) / 60; //resultado é diferença entre as datas em minutos  
	    long segundosRestantes = (diferenca / 1000)%60; //calcula os segundos restantes  
	    result = diferencaEmMinutos + (segundosRestantes /60d); //transforma os segundos restantes em minutos  

	    double d = result/60;  
	    // vamos converter para segundos primeiro, e arredondando um pouco para evitar alguns problemas esquisitos  
		 long s  = Math.round (d * 3600);  
		 // Agora vamos calcular horas, minutos e segundos  
		 Long h = s / 3600;  
		 Long m = (s - 3600 * h) / 60;  
		 s = s % 60;   

		 Integer hora= Integer.valueOf(h.toString());  
		 Integer minuto= Integer.valueOf(m.toString());  
		 
		 ArrayList<Integer> tempo = new ArrayList<Integer>();
		 tempo.add(hora);
		 tempo.add(minuto);
		 
	    return tempo;  
	}  

	public Date subtraiDias(Date data){
		Calendar calendarData = Calendar.getInstance();  
		calendarData.setTime(data);  
		  
		int numeroDiasParaSubtrair = -3;  
		  
		calendarData.add(Calendar.DATE,numeroDiasParaSubtrair);  
		Date dataInicial = calendarData.getTime(); 
	return dataInicial;
	}
	
	public Calendar subtraiQtdDias(int dias){
		Calendar calendarData = Calendar.getInstance();  
		
		int numeroDiasParaSubtrair = -dias;  
		
		calendarData.add(Calendar.DATE,numeroDiasParaSubtrair);  
		Calendar dataInicial = calendarData; 
		return dataInicial;
	}
	
	public Date subtraiMeses(Integer qtdMeses){
		Calendar calendarData = Calendar.getInstance();  
		Integer mesesXDias = qtdMeses * 30;
		int numeroMesesParaSubtrair = -mesesXDias;  
		calendarData.add(Calendar.DATE,numeroMesesParaSubtrair);  
		Date dataInicial = calendarData.getTime(); 
	return dataInicial;
	}
	
	
	
	
	
	public Date SomaDias(Date data,Integer dia){
		Calendar calendarData = Calendar.getInstance();  
		calendarData.setTime(data);  
		  
		calendarData.add(Calendar.DATE,dia);  
		Date dataInicial = calendarData.getTime(); 
	return dataInicial;
	}
	
    public static Calendar checaFDS(Calendar data)
    {
    	/**
         * Verifica se data á sábado ou domingo e acrescenta dias conforme necessário p/ retornar dia de semana.
         *
         * @param   data        Um objeto Calendar
         * @return  Calendar
         */
    	
    	
        // se for domingo
        if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
        {
            data.add(Calendar.DATE, -2);
            System.out.println("Eh domingo, mudando data para 2 dias");
        }
        // se for sábado
        else if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
        {
            data.add(Calendar.DATE, -2);
            System.out.println("Eh sabado, mudando data para -2 dias");
        }
        else
        {
            System.out.println("Eh dia de semana, mantem data");
        }
        return data;
    }

	public static Long diferencaDatasEmMilisegundos(Date dataInicial, Date dataFinal){
		 long diferenca = dataFinal.getTime() - dataInicial.getTime();  
		return diferenca;
	}
	
	public ArrayList<Integer> somaTempoTotal(double tempoMilisegundos){
		
	    double result = 0;  
	    long diferenca = (long) tempoMilisegundos;  
	
		
		double diferencaEmMinutos = (diferenca /1000) / 60; //resultado é diferença entre as datas em minutos  
	    long segundosRestantes = (diferenca / 1000)%60; //calcula os segundos restantes  
	    result = diferencaEmMinutos + (segundosRestantes /60d); //transforma os segundos restantes em minutos  

	    double d = result/60;  
	    // vamos converter para segundos primeiro, e arredondando um pouco para evitar alguns problemas esquisitos  
		 long s  = Math.round (d * 3600);  
		 // Agora vamos calcular horas, minutos e segundos  
		 Long h = s / 3600;  
		 Long m = (s - 3600 * h) / 60;  
		 s = s % 60;   

		 Integer hora= Integer.valueOf(h.toString());  
		 Integer minuto= Integer.valueOf(m.toString()); 
		 
		 ArrayList<Integer> tempo = new ArrayList<Integer>();
		 tempo.add(hora);
		 tempo.add(minuto);
		 
	    return tempo;  
	}

	public SimpleDateFormat dataHojeString(){
	   Calendar c = Calendar.getInstance();
	   Date data = c.getTime();
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   sdf.format(data);
	   return sdf;
	}
	
	public ArrayList<String> dataHojeFormatada(){
	ArrayList<String> dataHoje = new ArrayList<String>();
	GregorianCalendar calendar = new GregorianCalendar();  
	Integer dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);  
	Integer ano = calendar.get(GregorianCalendar.YEAR); 
	
	String mesAt;    
    Date data = new Date();    
    DateFormat df = new SimpleDateFormat("MMMM");      
        
    mesAt = df.format(data); 
    dataHoje.add(dia.toString());
    dataHoje.add(mesAt);
    dataHoje.add(ano.toString());
    
    return dataHoje;
    
	}
	
	public String converteDateParaString (Date data) throws ParseException{
  	    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");  
        //vai te retorna uma String  
		String result = f.format(data);  
		return result;
	}

	public String converteDateParaStringSemCaracteresEspeciais (Date data) throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat("dd_MM_yyyy-HH-mm-ss");  
		//vai te retorna uma String  
		String result = f.format(data);  
		return result;
	}
	
	public String converteDateParaStringInternacional (Date data) throws ParseException{
  	    SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");  
        //vai te retorna uma String  
		String result = f.format(data);  
		return result;
	}
	
	public ArrayList<String> converteDateParaStringNacional (Date data) throws ParseException{
		/*SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");  
		//vai te retorna uma String  
		String result = f.format(data);  
		*/
		
		
		Calendar atual = Calendar.getInstance();
		int anol = atual.get(Calendar.YEAR);

		ArrayList<String> datas = new ArrayList<String>();
		
		Locale local = new Locale("pt","BR");
		DateFormat dia = new SimpleDateFormat("dd",local); 
		DateFormat mes = new SimpleDateFormat("MMMM",local); 
		DateFormat ano = new SimpleDateFormat("yyyy",local); 
		
		datas.add(dia.format(data));
		datas.add(mes.format(data));
		datas.add(ano.format(data));
		
		return datas;
	 
	}
	
	public Date dataParaCorrecaoBandoDadosAntigo(){
		Calendar calend = Calendar.getInstance();
		calend.set(Calendar.YEAR, 0001); 
		calend.set(Calendar.MONTH, Calendar.JANUARY); 
		calend.set(Calendar.DAY_OF_MONTH, 1);
	
		calend.set(Calendar.HOUR_OF_DAY, 00);
		calend.set(Calendar.MINUTE, 00);
		calend.set(Calendar.SECOND, 00);

		return calend.getTime();
	}
	
	public Integer referenciaMesAnalitico(String nomeMes){
		Integer numeroMes = 0;
		
		if(nomeMes.equals("JANEIRO")){
			numeroMes = 1;
		}
		if(nomeMes.equals("FEVEREIRO")){
			numeroMes = 2;
		}
		if(nomeMes.equals("MARÇO")){
			numeroMes = 3;
		}
		if(nomeMes.equals("ABRIL")){
			numeroMes = 4;
		}
		if(nomeMes.equals("MAIO")){
			numeroMes = 5;
		}
		if(nomeMes.equals("JUNHO")){
			numeroMes = 6;
		}
		if(nomeMes.equals("JULHO")){
			numeroMes = 7;
		}
		if(nomeMes.equals("AGOSTO")){
			numeroMes = 8;
		}
		if(nomeMes.equals("SETEMBRO")){
			numeroMes = 9;
		}
		if(nomeMes.equals("OUTUBRO")){
			numeroMes = 10;
		}
		if(nomeMes.equals("NOVEMBRO")){
			numeroMes = 11;
		}
		if(nomeMes.equals("DEZEMBRO")){
			numeroMes = 12;
		}
		return numeroMes;
	}
	
	public String nomeMesPorDigito(Integer digitoMes){
		String mes = "";
		if(digitoMes.equals(1)){
			mes = "JANEIRO";
		}
		if(digitoMes.equals(2)){
			mes = "FEVEREIRO";
		}
		if(digitoMes.equals(3)){
			mes = "MARÇO";
		}
		if(digitoMes.equals(4)){
			mes = "ABRIL";
		}
		if(digitoMes.equals(5)){
			mes = "MAIO";
		}
		if(digitoMes.equals(6)){
			mes = "JUNHO";
		}
		if(digitoMes.equals(7)){
			mes = "JULHO";
		}
		if(digitoMes.equals(8)){
			mes = "AGOSTO";
		}
		if(digitoMes.equals(9)){
			mes = "SETEMBRO";
		}
		if(digitoMes.equals(10)){
			mes = "OUTUBRO";
		}
		if(digitoMes.equals(11)){
			mes = "NOVEMBRO";
		}
		if(digitoMes.equals(12)){
			mes = "DEZEMBRO";
		}
		return mes;
	}
	
	
	
	
	public List<String> mesesAno(){
		List<String> meses = new ArrayList<String>();
		meses.add("JANEIRO");
		meses.add("FEVEREIRO");
		meses.add("MARÇO");
		meses.add("ABRIL");
		meses.add("MAIO");
		meses.add("JUNHO");
		meses.add("JULHO");
		meses.add("AGOSTO");
		meses.add("SETEMBRO");
		meses.add("OUTUBRO");
		meses.add("NOVEMBRO");
		meses.add("DEZEMBRO");
		return meses;
	}
	
	public static String converteDateParaStringStatic (Date data) throws ParseException{
  	    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");  
        //vai te retorna uma String  
		String result = f.format(data);  
		return result;
	}
	
	
	public static String pegaDataAtualEmStringPassandoFormato(String format){
        if (format.isEmpty()) {
            throw new NullPointerException("A pattern não pode ser NULL!");
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat(format);
        Date data = (Date) calendar.getTime();
        return formato.format(data);
	}
	
	
	
	
	
	
}
