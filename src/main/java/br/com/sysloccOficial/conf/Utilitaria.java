package br.com.sysloccOficial.conf;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.sysloccOficial.daos.JobDAO;
import br.com.sysloccOficial.daos.ListaDAO;
import br.com.sysloccOficial.model.Usuario;


public class Utilitaria {
	
	
	@Autowired private ListaDAO listaDAO;
	@Autowired private JobDAO jobDAO;
	@PersistenceContext	private EntityManager manager;
	
	
	
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

		public String formataValoresAprimorado(String i){
			String j;
			if(i.equals("") || i.equals(" ") || i.equals(null)){
				j = "0.00";
			}else{
				j = i.replace('.', ' ').replace(',', '.').replaceAll(" ", "");	
			}
			return j;
		}

		
		
		public Double converteStringParaDouble(String valor){
			Double teste = 0.0;
			if(valor.equals(null) || valor.equals("") || valor.equals(" ")){
			}else{
				BigDecimal z = new BigDecimal("0");	
				z = (new BigDecimal(valor));	
				teste = Double.valueOf(z.doubleValue());
			}
			
			return teste;
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

// ---------------- Converte de String para Date ----------------------------//	
	public Date formataDatasStringParaCalendar(String data){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
		try {
			c.setTime(df.parse(data));
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

// -------------- Verifica se boolean é false ou true --------------------//
	public boolean verificaBoolean(Boolean b) {

		if (b == null) {
			b = false;
		} else {
			b = true;
		}
		return b;
	}
	
// -------------- Faz split para numero de DI -------------------------------//	
	public String splitDI(String d) {
		
			
		
		String[] as = d.split("-DI");

		String t = as[1];

		int codInterno = Integer.parseInt(t) + 1;
		String codInternoConvertido = null;

		if (codInterno <= 9) {
			codInternoConvertido = "-DI00" + Integer.toString(codInterno);
		} else if (codInterno > 9 && codInterno < 99) {
			codInternoConvertido = "-DI0" + Integer.toString(codInterno);
		}else{
			codInternoConvertido = "-DI" + Integer.toString(codInterno);
		}
		
		return codInternoConvertido;
	}
// ------------- Prefixo para Anexo --------------------//
	public String PrefixoAnexo(Integer i){
	
	String AnexoCodigo = "";
	
	
	if(i == 0){
		AnexoCodigo = AnexoCodigo + "-AN001";
	}
	
	else if(i > 0 && i < 9){
		i = i + 1;
		AnexoCodigo = AnexoCodigo + "-AN00"+i;
	}
	
	else if(i >= 9 && i <= 99){
		i = i + 1;
		AnexoCodigo = AnexoCodigo + "-AN0"+i;
	}
	else if(i >= 99){
		i = i + 1;
		AnexoCodigo = AnexoCodigo + "-AN"+i;
	}	
	return AnexoCodigo;
}	
	
	public ArrayList<Integer> FormataDataJoda(Date dataProposta) throws ParseException{
		
		Calendar datahojeCalendar = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String a = s.format(datahojeCalendar.getTime());
		
        
     // constrói a data de Hoje
        DateFormat fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataHoje = (Date)fm.parse(a);
        
        
	 //   Date dataProposta = statusDAO.data(idJob);
	    SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String data = ss.format(dataProposta.getTime());
	    
	    
	    
	    fm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date data2 = (Date)fm.parse(data);
        
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
	
	
	
	
	// -----------------------------------------------------//	
		public String montaCodigoLista(Integer idJob){
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("yyMM");  
	    String anoMes = s.format(c.getTime());
		
	    Number i =  listaDAO.codLista(); // Quantidade de lista do mês atual
	    
	    Integer codLista = i.intValue();
		String codListaNovo = numeroMaiorQue(codLista);
		
		anoMes = anoMes+codListaNovo+codLista;
		
		Number codigoLista = listaDAO.qtdJobsPorLista(idJob);
		codigoLista = codigoLista.intValue();  
		
		String numero = String.valueOf(codigoLista);

		Integer f = Integer.parseInt(numero);
		Integer numFinal = f +1 ;
		
		
		String ff = String.valueOf(numeroMaiorQue(f));
		
		// substituir anoMes por Codigo do Job
		String codListaFinal = anoMes+"-LP"+ff+numFinal;
		
		String codJob = listaDAO.pegaCodigoJob(idJob);
		
 //		String codListaFinal = anoMes+"-LP"+ff+numFinal;
		
		return codJob+"-LP"+ff+numFinal;
	}	
	// -----------------------------------------------------//	
	public String montaCodigoDuplicaLista(Integer idJob,Integer codJob){
		
		Number codigoLista = listaDAO.qtdJobsPorLista(idJob);
		codigoLista = codigoLista.intValue();
		
		String numero = String.valueOf(codigoLista);

		Integer f = Integer.parseInt(numero);
		Integer numFinal = f +1 ;
		
		String ff = String.valueOf(numeroMaiorQue(f));
		
		String codJobDuplicado = listaDAO.pegaCodigoJob(idJob)+"-LP"+ff+numFinal;
		
		return codJobDuplicado;
	}			

	public String montaCodigoDuplicaLista2(Integer idJob,Integer codJob){
		
		try {
			Number codigoLista = listaDAO.qtdJobsPorLista(idJob);
			codigoLista = codigoLista.intValue();
			
			String numero = String.valueOf(codigoLista);
			
			Integer f = Integer.parseInt(numero);
			Integer numFinal = f +1 ;
			
			String ff = String.valueOf(numeroMaiorQue(f));
			
			String codJobDuplicado = listaDAO.pegaCodigoJob(idJob)+"-LP"+ff+numFinal;
			
			return codJobDuplicado;
		} catch (Exception e) {
			System.out.println("Deu erro ao Montar um código !"+e);
			return null;

		}
	}			
	
	public String numeroMaiorQue(Integer numero){
		
		if(numero == 0){
		 return "00";
		}	
		
		else if(numero > 0 && numero < 9){
			return "00";
		}
		
		else {
			return "0";
		}
		
	}
	
	public String formulaParaCalculoImposto(String imposto, BigDecimal valorParaCalcular){
    /** Formula para cálculo do imposto por Fora ( parte I )	
	 *
	 *  100 - 22,9 = X
	 *  Valor preço Item / X = Y
	 *
     **/
		
		BigDecimal cem = new BigDecimal("100"); 
        BigDecimal porcentagemDeImposto = new BigDecimal(imposto);
        BigDecimal resultado = cem.subtract(porcentagemDeImposto);
       
        BigDecimal resultadoFormula = resultado.divide(cem);
        BigDecimal resultadoFinal = valorParaCalcular.divide(resultadoFormula,14,RoundingMode.UP);
		
        String retorno = resultadoFinal.toString();
		
		
		return retorno;
	}
	
	public BigDecimal formulaParaCalculoImposto2(String ValorParteI, BigDecimal valorPrecoTotalItem){
		/** Fórmula para cálculo de imposto por Fora ( parte II )
		 * 
		 *  Y - valor preco item = B
		 *  ValorParteI - valorPrecoTotalItem = Valor Real do imposto
		 *  
		 **/
		
		BigDecimal V = (new BigDecimal(ValorParteI));
		BigDecimal Vz = V.subtract(valorPrecoTotalItem);
		
		return Vz;
		
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

	//    Date dataProposta = statusDAO.data(idJob);
	    
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

	/** 
	 * Calcula a diferença de duas datas em minutos 
	 * <br> 
	 * <b>Importante:</b> Quando realiza a diferença em minutos entre duas datas,
	 *    este método considera os segundos restantes e os converte em fração de minutos. 
	 * @param dataInicial 
	 * @param dataFinal 
	 * @return quantidade de minutos existentes entre a dataInicial e dataFinal. 
	 */  	
	public static ArrayList<Integer> diferencaEmMinutos(Date dataInicial, Date dataFinal){  
		
			
		
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
	
	public Date SomaDias(Date data,Integer dia){
		Calendar calendarData = Calendar.getInstance();  
		calendarData.setTime(data);  
		  
		calendarData.add(Calendar.DATE,dia);  
		Date dataInicial = calendarData.getTime(); 
	return dataInicial;
	}
	
	
	
	
    public static Calendar checaFDS(Calendar data){
    	
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
//            System.out.println("Eh domingo, mudando data para 2 dias");
        }
        // se for sábado
        else if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
        {
            data.add(Calendar.DATE, -2);
 //           System.out.println("Eh sabado, mudando data para -2 dias");
        }
        else
        {
//            System.out.println("Eh dia de semana, mantem data");
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
	
	
	
	public Integer converteLongParaInteger(Long num){
		Integer hora= Integer.valueOf(num.toString());  
		return hora;
	}
	
	public static Calendar DateToCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	}
	
	public String hashSenha(String senha){
		
		String password = senha;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		return hashedPassword;
	}
	
	public String usuarioLogado(){
	 String u = SecurityContextHolder.getContext().getAuthentication().getName();
	return u;
	
	}

	
	
	public Usuario retornaUsuarioLogado(){
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (usuarioLogado  instanceof UserDetails ) {
		   username = ( (UserDetails)usuarioLogado).getUsername();
		} else {
		   username = usuarioLogado .toString();
		}
		Query q = manager.createQuery("from Usuario u where userNovo ='"+username+"'");
		Usuario u = (Usuario) q.getSingleResult();
		return u;
	}
	
	private static Random rand = new Random();
	private static char[] numeros = "1234567890".toCharArray();
//	private static char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ".toCharArray();
	public static String nomeAleatorio (int nCaracteres) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < nCaracteres; i++) {
	        int ch = rand.nextInt (numeros.length);
	        sb.append (numeros [ch]);
	    }    
	    return sb.toString();    
	}
	
	public String primeiraLetraMaiuscula(String palavra){
		String p = palavra;
		return p.substring(0,1).toUpperCase()+palavra.substring(1);   
	}
	
	public int confereSeExisteNoBanco(String nome, String tiraEspaco, String consulta){
		String confere = "";
		if(nome == "" || nome == null || nome == " "){
			confere = "anular";
		}else{
			confere = "ok";
		}
		manager.createNativeQuery(tiraEspaco).executeUpdate();
		String limpaProduto = nome.trim();
		consulta = consulta + limpaProduto+"'";
		Query query = manager.createQuery(consulta);
		if(query.getResultList().isEmpty() &&confere == "ok"){
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * Verifica se um numero é inteiro
	 * 	
	 */
	public boolean inteiro(double num) {
	
		int aux = (int)num;
		return (((double)aux) == num);
	}
	
	public Object teste (double numero){
		if(inteiro(numero) == true){
			int qt12 = (int) numero;
			return qt12;
		}else{
			String aa = String.valueOf(numero);
			return aa;
		}
		
	}

	
	/**
	 * Método para veriricar a quantidade de Jobs do mês atual
	 * 
	 * 
	 * 
	 */
public Integer novoCodJob(){
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("yyMM");  
	    String anoMes = s.format(c.getTime());
		
	    Number i =  jobDAO.codJob();
		Integer codJob = i.intValue();
		Integer f = 0;
	    
		if(codJob == 0){
			anoMes = anoMes+ "001";
			f = Integer.parseInt(anoMes);
			
			
//			j.setCodJob(f);
			
			
		}
		else if(codJob > 0 && codJob < 9){
			codJob = codJob + 1;
			anoMes = anoMes+ "00";
			anoMes = anoMes+Integer.toString(codJob);
			
			f = Integer.parseInt(anoMes);
			
//			j.setCodJob(f);
		}
		else if(codJob >= 9 && codJob <= 99){
			codJob = codJob + 1;
			anoMes = anoMes+ "0";
			anoMes = anoMes+Integer.toString(codJob);
			
			f = Integer.parseInt(anoMes);
			
//			j.setCodJob(f);
		}
		else if(codJob >= 99){
			codJob = codJob + 1;
			anoMes = anoMes+Integer.toString(codJob);
			
			f = Integer.parseInt(anoMes);
			
//			j.setCodJob(f);
		}
		
		return f;
	}
	
	public String removerAcentos(String acentuada) {
	    CharSequence cs = new StringBuilder(acentuada);
	    return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	public String removerNaoAlfhanumericos(String texto) {
		return texto.replaceAll("[^A-Za-z0-9 ]", "");
	}
	
	public void gravaErros(Exception e) throws ParseException, IOException{
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		UtilitariaDatas util = new UtilitariaDatas();
		String data  = util.converteDateParaStringSemCaracteresEspeciais(Calendar.getInstance().getTime());
		File erro = new File("C:/SYSLOC/upload/logs/"+data+".log");
		FileWriter filew = new FileWriter(erro);
		BufferedWriter bf = new BufferedWriter(filew);
		bf.write(errors.toString());
		bf.close();
	}
	
	public <E> List<E> removerDuplicados(List<E> combinacoes) {
		for (int i = 0; i < combinacoes.size(); i++) {
			Object a = combinacoes.get(i);
			for (int j = i+1; j < combinacoes.size(); j++) {
				Object b = combinacoes.get(j);
				if (a.equals(b)) {
					combinacoes.remove(j);
					j--;
				}
			}
		}
		return combinacoes;
	}
	
	
	public <E> List<E> removerDuplicadosDuasListas(List<E> combinacoesA,List<E> combinacoesB) {
		for (int i = 0; i < combinacoesA.size(); i++) {
			for (int j = 0; j < combinacoesB.size(); j++) {
				if(combinacoesA.get(i).equals(combinacoesB.get(j))){
					combinacoesA.remove(i);
				}
		    }
	   }
		return combinacoesA;
   }
	
	public String limpaSqlComList(String consulta){
		String c2 = consulta.replace("[", "").replace("]", "");
		return c2;
	}
	
	public static String limpaSqlComListStastico(String consulta){
		String c2 = consulta.replace("[", "").replace("]", "");
		return c2;
	}
	
	public static String retiraUltimoCaracter(String formulaInicial) {
		int tamanho = formulaInicial.length();
		formulaInicial = formulaInicial.substring(0, tamanho-1);
		return formulaInicial;
	}
	
}
