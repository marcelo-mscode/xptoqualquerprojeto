package br.com.sysloccOficial.controllerCartaContratacao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sysloccOficial.model.CartaContratacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;

@Controller
@Transactional
public class CartaContratacaoController {
	@PersistenceContext	private EntityManager manager;
	ModelAndView MV = new ModelAndView();
	
	
	@RequestMapping("/cartaContratacao")
	public ModelAndView carta(){
		
		MV.setViewName("menuProducao/carta/cartaContratacao");
		CartaContratacao carta = manager.find(CartaContratacao.class, 1);
		MV.addObject("carta", carta);
		
		GregorianCalendar calendar = new GregorianCalendar();  
		int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);  
		int ano = calendar.get(GregorianCalendar.YEAR); 
		
		
		  String mesAt;    
		  
	      Date data = new Date();    
	      DateFormat df = new SimpleDateFormat("MMMM");      
	        
	      mesAt = df.format(data); 
		
		
		
		MV.addObject("diahoje", dia);
		MV.addObject("mesAtual", mesAt);
		MV.addObject("anoAtual", ano);
		
		
		return MV;
	}
	
	@RequestMapping("/salvaCarta")
	public String salvaCarta(CartaContratacao carta){
		String mesAt;    
		
		GregorianCalendar calendar = new GregorianCalendar();  
		int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);  
        Date data = new Date();    
	    DateFormat df = new SimpleDateFormat("MMMM");      
        mesAt = df.format(data); 
	    int ano = calendar.get(GregorianCalendar.YEAR); 
		
		
		String dataHoje  = "São Paulo,"+dia+" de "+mesAt+" de "+ano;
		
		carta.setDataCabecalho(dataHoje);
		
		
		if(carta.getIdCarta() == null){
			manager.persist(carta);
		}else{
			manager.detach(carta);
			manager.merge(carta);
		}
		
		return "redirect:cartaContratacao";
	}
	
	
	/*public void salvaCartaNova(CartaContratacao carta){
		String mesAt;    
		
		GregorianCalendar calendar = new GregorianCalendar();  
		int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);  
        Date data = new Date();    
	    DateFormat df = new SimpleDateFormat("MMMM");      
        mesAt = df.format(data); 
	    int ano = calendar.get(GregorianCalendar.YEAR); 
		
		
		String dataHoje  = "São Paulo,"+dia+" de "+mesAt+" de "+ano;
		
		carta.setDataCabecalho(dataHoje);
		
		
		if(carta.getIdCarta() == null){
			manager.persist(carta);
		}else{
			manager.detach(carta);
			manager.merge(carta);
		}
		
	}*/
	
	
	
	
}
