package br.com.sysloccOficial.log4j;

import org.apache.log4j.Logger;

import br.com.sysloccOficial.controllers.ProdutoGrupoProducaoController;

public class TesteLog4j {
//	private static final Logger logger = Logger.getLogger(TesteLog4j.class);

	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger("lopes");
		  logger.info("Apenas outro testelko");
		  
		  TesteLog4j teste = new TesteLog4j();
		  
		  try {
			  teste.divide();
		} catch (Exception e) {
			logger.error("This is Error message", new Exception(e));
		}
		  
		  /*logger.debug("debug here");
		   Usuario usuario = new Usuario();
		   usuario.setNome("camilo");
		   usuario.setEmail("log4j");
		   UsuarioDao usuarioDao = new UsuarioDao();
		   usuarioDao.save(usuario);*/
		  //logger.info("usuario salvo no banco com sucesso");
	}
	
	private void divide(){
		
		int i = 10 /0;

	}
}
