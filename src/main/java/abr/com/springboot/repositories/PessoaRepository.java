package abr.com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import abr.com.springboot.domain.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
}
