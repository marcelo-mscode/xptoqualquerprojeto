package br.com.sysloccOficial.listaproducao.testedeterminaquantidade;

import br.com.sysloccOficial.model.DeterQuantpadrao;
import br.com.sysloccOficial.model.Grupo;



/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfigurationTest.class,UsuarioDAO.class,JPAConfiguration.class})
@ActiveProfiles("test")*/
public class VerificaQuantidadeDeterminantePadrao {
	
	Grupo grupo;

	public VerificaQuantidadeDeterminantePadrao(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public boolean verifica(DeterQuantpadrao dt){
		String consulta = "";
		
		
		if(dt.getGrupo().getIdgrupo() == grupo.getIdgrupo())
			return true;
		return false;
	}

	

}
