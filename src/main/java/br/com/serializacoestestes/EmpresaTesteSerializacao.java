package br.com.serializacoestestes;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

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
		
		List<Empresa> pp = empresaDAO.listaTodasEmpresas();
		
		  /*try{
				
				*//**
				 * A Classe FileOutputStream é responsável por criar
				 * o arquivo fisicamente no disco, assim poderemos realizar a 
				 * escrita neste. 
				 *//*
				 
				 
				FileOutputStream fout = new FileOutputStream("K:\\cache\\empresas\\listaEmpresas.ser");
				
				*//**
				 * A Classe ObjectOutputStream escreve os objetos no FileOutputStream
				 *//*
				
				ObjectOutputStream oos = new ObjectOutputStream(fout);   
				
				*//**
				 * Veja aqui a mágica ocorrendo: Estamos gravando um objeto 
				 * do tipo Address no arquivo address.ser. Atenção: O nosso 
				 * objeto Address que está sendo gravado, já é gravado de forma 
				 * serializada
				 *//*
				oos.writeObject(pp);
				
				oos.close();
				System.out.println("Done");
		 
			   }catch(Exception ex){
				   ex.printStackTrace();
			   }*/
		for (int i = 0; i < pp.size(); i++) {
			System.out.println(pp.get(i).getEmpresa());
		}
		
		
		
		return "ok";
	}
	
	
	
}
