package br.com.teste.datas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MesAnoAtual {

	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		
		
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        
        List<Integer[]>  data  = new ArrayList<Integer[]>();
        
        for (int i = 1; i <= month; i++) {
        	Integer[] datas = new Integer[2]; 
			
        	datas[0] = year;
        	datas[1] = i;
        	
        	data.add(datas);
		}
        
        
        List<Calendar> listaCal = new ArrayList<Calendar>();
        
        for (int i = 0; i <= month; i++) {

        	Calendar m = Calendar.getInstance();
            m.set(Calendar.MONTH, i);
            m.set(Calendar.YEAR, year);
        	
            listaCal.add(m);
            
		}
        
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        
        
        for (int i = 0; i < listaCal.size(); i++) {

        	String dataa = sdf.format(listaCal.get(i).getTime());
        	System.out.println(dataa);
		}
        
	}

}
