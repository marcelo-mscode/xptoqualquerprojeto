package br.com.teste.datas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MesAnoAtual {

	public static void main(String[] args) {
Calendar cal = Calendar.getInstance();
		
		
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        System.out.println("Month: " + month);
        System.out.println("Year: " + year);
        
        
        List<Integer[]>  data  = new ArrayList<Integer[]>();
        
        for (int i = 1; i <= month; i++) {
        	Integer[] datas = new Integer[2]; 
			
        	datas[0] = year;
        	datas[1] = i;
        	
        	data.add(datas);
		}
        
        System.out.println();
        for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i)[0] + " - " + data.get(i)[1]);
		}
        
        
        List<Calendar> calendario = new ArrayList<Calendar>(); 
        
        int mes = cal.get(Calendar.MONTH) + 1;
        
        
	}

}
