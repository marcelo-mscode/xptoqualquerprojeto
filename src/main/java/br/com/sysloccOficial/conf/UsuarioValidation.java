/*package br.com.sysloccOficial.conf;

import org.springframework.validation.Errors;




public class UsuarioValidation implements org.springframework.validation.Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	@Override
	public void validate(Object target, Errors errors) {
		
	//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");
		
		Usuario usuario = (Usuario) target;
		
		if(usuario.getNome().isEmpty()){
			errors.rejectValue("nome", "field.required");
		}
		
		if(usuario.getEmail().isEmpty()){
			errors.rejectValue("email", "field.required");
		}
		
	}

	
	
	
}
*/