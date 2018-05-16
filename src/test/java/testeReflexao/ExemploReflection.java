package testeReflexao;

import java.lang.reflect.Field;

import br.com.sysloccOficial.financeiro.model.FinancImpostos;

public class ExemploReflection {
	
	
	public static void main(String[] args) {        

        Class clazz = FinancImpostos.class;

        for (Field field : clazz.getDeclaredFields())
        {

            String campo = field.getName();
            Class tipo = field.getType();
            
            String nomeTipo = field.getName();

            System.out.println("Campo: " + campo + " ,  Tipo: " + tipo);            

        }

    }   
	
	

}
