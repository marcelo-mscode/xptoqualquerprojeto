	package br.com.sysloccOficial.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	
    public String uploadFile(String codJob, MultipartFile arquivoParaSalvar,String prefixoArquivo){
	
		String retorno = null;
		String CaminhoParaSalvar = ("C:/SYSLOC/"+codJob);
		String path = "/"+prefixoArquivo+arquivoParaSalvar.getOriginalFilename().replace(",", "");
		
		String arvore = CaminhoParaSalvar/*+"/pasta2"*/;
		
		try {
			 if( VerificaTamanho(arquivoParaSalvar) == true){
				 
				 
				String novoCaminho =  criaArvoreDiretorios(arvore);
				 
				 arquivoParaSalvar.transferTo(new File(novoCaminho+path));
				 
			 } 
			   else {
				    System.out.println("Arquivo Maior que 50MB.");
				    retorno = "0";
			   }
				
			   retorno = "upload/"+codJob+"/";
			   
		//	   VerificaPastas("C:/SYSLOC/1504015/");
			   
			   return retorno;
			
		     } catch (IOException e) {
			
		    	 	throw new RuntimeException(e);
		     }
		
		
		
		
	   }


    
	public String[] VerificaPastas(String caminho){
		//System.out.println("Caminhos: "+caminho);
		
		caminho = "C:/SYSLOC/"+caminho;
	//    JOptionPane.showMessageDialog(null, "Estou em Verifica Pastas. Caminho: "+caminho);
		
		
		File dir = new File(caminho);
			String[] teste = dir.list();
		
	  	    return teste;
	}
	
	public void criaDiretorio(String CaminhoParaCriar){
		 
		 File dir = new File(CaminhoParaCriar);  
		
		 if(dir.exists()){
			 
		 }else if (dir.mkdir()) {  
		     System.out.println("Diretorio criado com sucesso!");  
		 } 
		 
		 
		 
		 
	}
	
	public String criaArvoreDiretorios(String arvoreDiretorios){
		 
        String caminho = arvoreDiretorios.replace(",", "");
		
        
		File dir = new File(caminho);  
		 
		 if(!dir.exists()){
			   
		  	  dir.mkdirs();
		  	  return caminho;
		  	  
		 }else{
             
			return caminho;
		 }
		
	}
	
	
	
	public boolean VerificaTamanho(MultipartFile arquivoParaSalvar){
		 long i = arquivoParaSalvar.getSize();
		 
		 if(i > 1524288000  ){  //Se for maior que 50MB
			return false;
		 }else {
		
			return true;
	    }
		 
  	}
	
	
	public String LogoEmpresa(MultipartFile arquivoParaSalvar){
		
		String retorno = null;
		String CaminhoParaSalvar = ("C:/SYSLOC/upload/logoEmpresas");
		String CaminhoParaExibir = ("/upload/logoEmpresas/");
		
		try {
			 if( VerificaTamanho(arquivoParaSalvar) == true){
				 
				 
				 arquivoParaSalvar.transferTo(new File(CaminhoParaSalvar+"/"+arquivoParaSalvar.getOriginalFilename()));
				 
			 } 
			   else {
				    System.out.println("Arquivo Maior que 50MB.");
				    retorno = "0";
			   }
				
			   retorno = arquivoParaSalvar.getOriginalFilename();
			   
			   return retorno;
			
		     } catch (IOException e) {
			
		    	 	throw new RuntimeException(e);
		     }
		
	   }

}


//retorno =CaminhoParaSalvar+"/";*/				 
//String CaminhoParaSalvar = ("/"+baseFolder);
