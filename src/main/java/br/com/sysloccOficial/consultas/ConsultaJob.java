package br.com.sysloccOficial.consultas;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

public class ConsultaJob extends Consulta{

	String consultaGeral = "select j from Job j join fetch j.contatos where";
	String consultaCodEListaCod = "select j.idJob,j.codJob from Job j where ";
	
	public ModelAndView setaParametro(String termo){
		return setaParametroHerdado("job/teste", "jobs",termo, consultaGeral);
	}

	public ModelAndView setaParametroAjax(String termo){
		return setaParametroHerdado("job/listaJobAjax","jobs",termo, consultaCodEListaCod);
	}
	
	public ModelAndView teste(String termo){
		String consulta = "select j from Job j join fetch j.contatos where ";
		return setaParametroHerdado("job/teste","jobs",termo, consulta);
	}
	
	public ModelAndView re(String termo){
		String consulta = "select j from Job j join fetch j.contatos where ";
		return setaParametroHerdado("job/teste","jobs",termo, consulta);
	}
	
	
	
	
	public String montaConsulta(String teste, String termo){
		String condicao[] = teste.split(",");  
		String consulta2 ="";
		int num= 0;
		for(int i = 0; i < condicao.length; i++){
			num = num + 1;
		}
		num = num-1;
		for(int i=0; i < condicao.length; i++){
			consulta2 = consulta2+ termo +condicao[i];
			System.out.println(consulta2);
			if( i < num){
				consulta2 = consulta2+" or ";
			}
		}
		 
		
		return consulta2;
	}
	
	
	public String montaConsultaInteger(List<Integer> idsJobs, String termo){
		
		String t = "";
//		String termoCOnsulta = "idJob = ";
		int num = 0;
		
		
		for (int i = 0 ; i < idsJobs.size(); i++){
			num = num +1;
		}
		
		num = num - 1;
		
		for (int i = 0 ; i < idsJobs.size(); i++){
			
			String s = idsJobs.get(i).toString();
			
			t = " "+t + termo + s +" ";
			
			if(i < num){
				t = t + " or ";
			}
			
			
		}
		
		return t;
	}
	
	
	
	
}
 