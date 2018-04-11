package abr.com.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abr.com.springboot.domain.Pessoa;
import abr.com.springboot.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired PessoaRepository repo;
	
	public Pessoa buscar(Integer id){
		System.out.println("Pessoa Service");
		
		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}
