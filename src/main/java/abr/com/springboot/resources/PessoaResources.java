package abr.com.springboot.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import abr.com.springboot.domain.Pessoa;
import abr.com.springboot.services.PessoaService;


@RestController
@RequestMapping(value="/pessoateste")
public class PessoaResources {
	
	@Autowired private PessoaService service;
	
	@RequestMapping(value="/{id}",  method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		
		System.out.println("Aqui");
		
		Pessoa obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
