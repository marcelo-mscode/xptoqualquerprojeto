package abr.com.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abr.com.springboot.domain.Pessoa;
import abr.com.springboot.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired PessoaRepository repo;
	
	public Pessoa buscar(Integer idPessoa){
		Optional<Pessoa> obj = repo.findById(idPessoa);
		return obj.orElse(null);
	}
	
}
