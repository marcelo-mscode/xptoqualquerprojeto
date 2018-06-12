package br.com.serializacoestestes;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sysloccOficial.daos.EmpresaDAO;
import br.com.sysloccOficial.model.Empresa;


@Controller
public class EmpresaTesteSerializacao {
	
	
	@Autowired 	private EmpresaDAO empresaDAO;
	

	
	@RequestMapping("empresaSerializaTeste")
	public String empresaSerializaTeste(){
		
		List<Tuple> pp = empresaDAO.listaTodasEmpresas();
		
		 /* try{
				
				FileOutputStream fout = new FileOutputStream("K:\\cache\\empresas\\listaEmpresas.ser");
				
				
				ObjectOutputStream oos = new ObjectOutputStream(fout);   
				
				oos.writeObject(pp);
				
				oos.close();
				System.out.println("Done");
		 
			   }catch(Exception ex){
				   ex.printStackTrace();
			   }*/
		
		return "ok";
	}
	
	
	
}
